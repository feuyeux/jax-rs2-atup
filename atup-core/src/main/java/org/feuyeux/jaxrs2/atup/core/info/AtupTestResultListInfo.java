package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;

import java.util.List;

public class AtupTestResultListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestResult> resultList;

    public AtupTestResultListInfo() {
    }

    public AtupTestResultListInfo(final List<AtupTestResult> resultList) {
        this.resultList = resultList;
    }

    public AtupTestResultListInfo(final String errorInfo, final Integer statusCode) {
        super(errorInfo, statusCode);
    }

    public List<AtupTestResult> getResultList() {
        return resultList;
    }

    public void setResultList(final List<AtupTestResult> resultList) {
        this.resultList = resultList;
    }
}
