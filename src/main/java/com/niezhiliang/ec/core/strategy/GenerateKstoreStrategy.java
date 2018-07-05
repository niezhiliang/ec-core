package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.entity.KestoreParams;
import com.niezhiliang.ec.core.keystore.service.KeyTools;
import com.niezhiliang.ec.core.pattern.Behave;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

@Component
public class GenerateKstoreStrategy implements Behave<KestoreParams> {


    @Override
    public void doSomeJob(KestoreParams kestoreParams) {

        try {
            //创建ks文件
            FileOutputStream out = new FileOutputStream(kestoreParams.getKeyStoreName());
            KeyTools.newKeyStore(kestoreParams.getKeyStorePd())
                    .newKeyPair()
                    .keyLength(kestoreParams.getKeyLength())
                    .generateWithCertificate()
                    .withValidity(1, ChronoUnit.YEARS)
                    .withDistinguishName()
                    .commonName(kestoreParams.getFcName())
                    .state(kestoreParams.getFcCity())
                    .locality(kestoreParams.getFcProvince())
                    .country(kestoreParams.getFcCountry())
                    .build()
                    .createInKeyStore(kestoreParams.getFcAlias(), kestoreParams.getFcPassword())
                    .writeTo(out);
            out.close();

            /**
             * 查看证书的别名之类的
             */
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(kestoreParams.getKeyStoreName()), "123456".toCharArray());
            //ks.setCertificateEntry();

            Enumeration<String> tmp = ks.aliases();

            HashMap<String, String> map = new HashMap<>();
            for (String alias : Collections.list(ks.aliases())) {

                Certificate c = ks.getCertificate(alias);

                X509Certificate cert = (X509Certificate) c;
                System.out.println("-------" + alias);
                BigInteger serialNumber = cert.getSerialNumber();
                String s = serialNumber.toString(16);
                map.put(s, alias);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
