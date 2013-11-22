package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestResultListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestResultInfo> resultList;

    public AtupTestResultListInfo() {
    }

    public AtupTestResultListInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    public List<AtupTestResultInfo> getResultList() {
        return resultList;
    }

    public void setResultList(List<AtupTestResultInfo> resultList) {
        this.resultList = resultList;
    }
}
