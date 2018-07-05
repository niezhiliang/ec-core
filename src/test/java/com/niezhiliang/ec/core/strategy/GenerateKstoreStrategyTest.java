package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.Application;
import com.niezhiliang.ec.core.context.EcContext;
import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.KestoreParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 生成keystore文件
     */
    @Test
    public void doSomeJob() {
        KestoreParams kestoreParams = new KestoreParams();
        kestoreParams.setKeyStoreName("demo.ks");
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

    @Test
    public void generateCert() {
        EcContext ecContext = new EcContext(generateCertStrategy);
        ecContext.doJob(new CertParams());
    }
}