package org.feuyeux.jaxrs2.atup.core.rest;

public class AtupRequestParam {
    private String key;
    private Object value;

    public AtupRequestParam(String key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
