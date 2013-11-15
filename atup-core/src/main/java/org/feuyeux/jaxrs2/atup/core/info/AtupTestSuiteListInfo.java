package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestSuiteListInfo extends AtupInfo {

    private List<AtupTestSuiteInfo> suiteList;

    public List<AtupTestSuiteInfo> getSuiteList() {
        return suiteList;
    }

    public void setSuiteList(List<AtupTestSuiteInfo> suiteList) {
        this.suiteList = suiteList;
    }
}
