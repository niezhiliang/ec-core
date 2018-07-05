package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.KestoreReturn;
import com.niezhiliang.ec.core.keystore.entity.Resource;
import com.niezhiliang.ec.core.keystore.service.KeyStoreAdapter;
import com.niezhiliang.ec.core.keystore.service.KeyTools;
import com.niezhiliang.ec.core.pattern.Behave;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class GenerateCertStrategy implements Behave<CertParams,KestoreReturn>{

    @Value("${ks.path}")
    private String keyStorePath;

    @Override
    public KestoreReturn doSomeJob(CertParams certParams) {

        try {
        KeyStoreAdapter keyStoreAdapter = KeyTools.keyStoreFrom(Resource.from(keyStorePath+"demo.ks") , "123456");

            keyStoreAdapter.newKeyPair()
                    .keyLength(2048)
                    .generateWithCertificate()
                    .withValidity(1 , ChronoUnit.YEARS)
                    .withDistinguishName()
                    .commonName("苏雨先生")
                    .state("杭州")
                    .locality("浙江")
                    .country("中国")
                    .build()
                    .createInKeyStore("362202122362164266" , "123456");
            FileOutputStream out = new FileOutputStream(keyStorePath+"demo.ks");
            keyStoreAdapter.writeTo(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将证书放到keystore中
     * @param originalTrustFolder
     * @param jksTrustStoreLocation
     * @param password
     */
    public static void createTrustJKSKeyStore(final String originalTrustFolder,
                                              final String jksTrustStoreLocation, final String password) {
        File keyStoreFile = new File(jksTrustStoreLocation);
        if (!keyStoreFile.exists()) {
            try {
                KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
                keystore.load(null, password.toCharArray());
                File trustedFolder = new File(originalTrustFolder);
                File[] certs = trustedFolder.listFiles();
                if (certs != null) {
                    for (File cert : certs) {
                        CertificateFactory factory = CertificateFactory.getInstance("X.509");
                        try {
                            X509Certificate certificate = (X509Certificate) factory.generateCertificate(new FileInputStream(cert));
                            X500Principal principal = certificate.getSubjectX500Principal();
                            LdapName ldapDN = new LdapName(principal.getName());
                            List<Rdn> rdns = ldapDN.getRdns();
                            for (Rdn rdn : rdns) {
                                String type = rdn.getType();
                                if (type.equals("CN")) {
                                    keystore.setCertificateEntry((String) rdn.getValue(),certificate);
                                    break;
                                }
                            }
                        } catch (Exception ex) {
                            continue;
                        }
                    }
                }
                FileOutputStream fos = new FileOutputStream(jksTrustStoreLocation);
                keystore.store(fos, password.toCharArray());
                fos.close();
            } catch (Exception exp) {
            }
        }
    }

}
