package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.context.EcContext;
import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.CertReturn;
import com.niezhiliang.ec.core.entity.KestoreParams;
import com.niezhiliang.ec.core.entity.KestoreReturn;
import com.niezhiliang.ec.core.pattern.Behave;

public class GenerateCertStrategy implements Behave<CertParams,CertReturn> {
    @Override
    public CertReturn doSomeJob(CertParams certParams) {
       CertReturn certReturn = new CertReturn();
       certReturn.setA("hello");
       certReturn.setB("World");
       return certReturn;
    }

    public static void main(String[] args) {
        EcContext ecContext = new EcContext(new GenerateKstoreStrategy());
        KestoreReturn kestoreReturn = ecContext.doJob(new KestoreParams());

        ecContext = new EcContext(new GenerateCertStrategy());
        ecContext.doJob(new CertParams());
    }
}
