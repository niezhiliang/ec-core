package com.niezhiliang.ec.core.pattern;

import com.niezhiliang.ec.core.entity.ParentParams;
import com.niezhiliang.ec.core.entity.ParentReturn;

public interface Behave<T extends ParentParams,K extends ParentReturn> {

    K doSomeJob(T t);
}
