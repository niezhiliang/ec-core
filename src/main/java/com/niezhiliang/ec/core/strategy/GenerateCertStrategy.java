package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.context.EcContext;
import com.niezhiliang.ec.core.entity.CertParams;
import com.niezhiliang.ec.core.entity.KestoreParams;
import com.niezhiliang.ec.core.entity.KestoreReturn;
import com.niezhiliang.ec.core.pattern.Behave;

public class GenerateCertStrategy implements Behave<CertParams,KestoreReturn>{


    @Override
    public KestoreReturn doSomeJob(CertParams certParams) {
        KestoreReturn parentReturn = new KestoreReturn();
         parentReturn.setA("哈哈");
        return parentReturn;
    }

    public static void main(String[] args) {
        EcContext ecContext = new EcContext(new GenerateKstoreStrategy());
        KestoreReturn kestoreReturn = (KestoreReturn) ecContext.doJob(new KestoreParams());
        System.out.println(kestoreReturn.getA());
    }
}
