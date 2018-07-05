package com.niezhiliang.ec.core.context;

import com.niezhiliang.ec.core.entity.ParentParams;
import com.niezhiliang.ec.core.pattern.Behave;

public class EcExecute<T extends ParentParams> {
    private Behave behave;

    public EcExecute(Behave behave) {
        this.behave = behave;
    }

    public void doJob(T t) {
         behave.doSomeJob(t);
    }
}

