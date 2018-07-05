package com.niezhiliang.ec.core.entity;

import java.security.PrivateKey;
import java.security.cert.Certificate;

public class SignInitParams {
    private PrivateKey pk;

    private Certificate[] chain;

    public PrivateKey getPk() {
        return pk;
    }

    public void setPk(PrivateKey pk) {
        this.pk = pk;
    }

    public Certificate[] getChain() {
        return chain;
    }

    public void setChain(Certificate[] chain) {
        this.chain = chain;
    }
}
