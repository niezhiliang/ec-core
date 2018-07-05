package com.niezhiliang.ec.core.context;

import com.niezhiliang.ec.core.entity.ParentParams;
import com.niezhiliang.ec.core.pattern.Behave;

public class EcContext<T extends ParentParams> {
    private Behave behave;

    public EcContext(Behave behave) {
        this.behave = behave;
    }

    public <K>K doJob(T t) {
        return (K) behave.doSomeJob(t);
    }
}

