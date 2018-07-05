package com.niezhiliang.ec.core.strategy;

import com.niezhiliang.ec.core.entity.SignParams;
import com.niezhiliang.ec.core.pattern.Behave;
import org.springframework.stereotype.Component;

@Component
public class SignStrategy implements Behave<SignParams> {

    @Override
    public void doSomeJob(SignParams signParams) {

    }
}
