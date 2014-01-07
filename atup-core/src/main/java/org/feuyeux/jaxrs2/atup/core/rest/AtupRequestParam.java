package org.feuyeux.jaxrs2.atup.core.rest;

class AtupRequestParam {
    private String key;
    private Object value;

    public AtupRequestParam(final String key, final Object value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(final Object value) {
        this.value = value;
    }

}
