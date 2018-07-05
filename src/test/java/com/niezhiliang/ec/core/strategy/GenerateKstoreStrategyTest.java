package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.Application;
import com.niezhiliang.ec.core.context.EcContext;
import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.KestoreParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class GenerateKstoreStrategyTest {
    @Autowired
    private GenerateKstoreStrategy generateKstoreStrategy;
    @Autowired
    private GenerateCertStrategy generateCertStrategy;

    @Value("${ks.path}")
    private String keyStorePath;

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

        EcContext ecContext = new EcContext(generateKstoreStrategy);
        ecContext.doJob(kestoreParams);

    }

    /**
     * 生成2048位的证书
     */
    @Test
    public void generateCert() {
        EcContext ecContext = new EcContext(generateCertStrategy);
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

        ecContext.doJob(certParams);
    }
}