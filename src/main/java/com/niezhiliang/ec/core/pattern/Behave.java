package com.niezhiliang.ec.core.pattern;

import com.niezhiliang.ec.core.entity.ParentParams;

public interface Behave<T extends ParentParams,K > {

    K doSomeJob(T t);
}
