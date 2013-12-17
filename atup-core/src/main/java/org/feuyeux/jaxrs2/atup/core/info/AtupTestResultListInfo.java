package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;

import java.util.List;

public class AtupTestResultListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestResult> resultList;

    public AtupTestResultListInfo() {
    }

    public AtupTestResultListInfo(List<AtupTestResult> resultList) {
        this.resultList = resultList;
    }

    public AtupTestResultListInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    public List<AtupTestResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<AtupTestResult> resultList) {
        this.resultList = resultList;
    }
}
