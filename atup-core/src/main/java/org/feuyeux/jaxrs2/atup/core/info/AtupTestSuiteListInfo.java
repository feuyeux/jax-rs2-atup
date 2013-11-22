package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestSuiteListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;

    private List<AtupTestSuiteInfo> suiteList;

    public AtupTestSuiteListInfo() {
    }

    public AtupTestSuiteListInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    public List<AtupTestSuiteInfo> getSuiteList() {
        return suiteList;
    }

    public void setSuiteList(List<AtupTestSuiteInfo> suiteList) {
        this.suiteList = suiteList;
    }
}
