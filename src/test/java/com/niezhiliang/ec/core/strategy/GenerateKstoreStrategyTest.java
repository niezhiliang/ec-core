package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.Application;
import com.niezhiliang.ec.core.context.EcExecute;
import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.KestoreParams;
import com.niezhiliang.ec.core.entity.SignParams;
import com.niezhiliang.ec.core.entity.SignPortParms;
import com.niezhiliang.ec.core.test.Sign2;
import com.niezhiliang.ec.core.test.SignInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class GenerateKstoreStrategyTest {
    @Autowired
    private GenerateKstoreStrategy generateKstoreStrategy;
    @Autowired
    private GenerateCertStrategy generateCertStrategy;
    @Autowired
    private SignStrategy signStrategy;

    @Value("${ks.path}")
    private String keyStorePath;
    @Value("${ks.pdfdir}")
    private String pdfdir;
    @Value("${ks.pdf}")
    private String pdfPath;
    @Value("${ks.img}")
    private String imgPath;

    /**
     * 生成keystore文件
     */
    @Test
    public void doSomeJob() {
        KestoreParams kestoreParams = new KestoreParams();
        kestoreParams.setKeyStoreName(keyStorePath+"demo.ks");
        kestoreParams.setKeyStorePd("123456");
        kestoreParams.setFcName("聂志良");
        kestoreParams.setFcProvince("浙江");
        kestoreParams.setFcCity("杭州");
        kestoreParams.setFcCountry("中国");
        kestoreParams.setFcAlias("first-certificate");
        kestoreParams.setFcPassword("123456");

        EcExecute ecExecute = new EcExecute(generateKstoreStrategy);
        ecExecute.doJob(kestoreParams);

    }

    /**
     * 生成2048位的证书
     */
    @Test
    public void generateCert() {
        EcExecute ecExecute = new EcExecute(generateCertStrategy);
        CertParams certParams = new CertParams();
        certParams.setKeyStorePath(keyStorePath+"demo.ks");
        certParams.setKeyLength(2048);
        certParams.setAlias("362202199509052515");
        certParams.setCerPassword("123456");
        certParams.setCity("杭州");
        certParams.setProvince("浙江");
        certParams.setCountry("中国");
        certParams.setRealName("苏苏苏");
        certParams.setKeyStorePassword("123456");

        ecExecute.doJob(certParams);
    }

    @Test
    public void sign() {
        SignParams signParams = new SignParams();
        signParams.setAlias("2037088e9d844f9d02c2832b368ba949c1f07eee");
        signParams.setPdfDir(pdfdir);
        signParams.setImgPath(imgPath);
        signParams.setReason("正常签署");
        signParams.setFileName(pdfPath);
        signParams.setKeyStorePath(keyStorePath+"personal.ks");
        signParams.setKeyStorePwd("123456");
        List<SignPortParms> list = new ArrayList<SignPortParms>();
        SignPortParms signPortParms = new SignPortParms();
        signPortParms.setPageno(0);
        signPortParms.setLeftButtomX(new BigDecimal(60));
        signPortParms.setLeftButtomY(new BigDecimal(60));
        signPortParms.setRightTopX(new BigDecimal(85));
        signPortParms.setRightTopY(new BigDecimal(85));
        signParams.setList(list);
        signParams.setLocation("111");
        list.add(signPortParms);

        EcExecute ecExecute = new EcExecute(signStrategy);
        ecExecute.doJob(signParams);

    }


}