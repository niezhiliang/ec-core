package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.entity.KestoreParams;
import com.niezhiliang.ec.core.entity.KestoreReturn;
import com.niezhiliang.ec.core.pattern.Behave;

public class GenerateKstoreStrategy implements Behave<KestoreParams,KestoreReturn> {

    @Override
    public KestoreReturn doSomeJob(KestoreParams kestoreParams) {
        System.out.println("生成kestore");
        KestoreReturn kestoreReturn =
                new KestoreReturn();
        kestoreReturn.setA("java");
        return kestoreReturn;
    }

}
