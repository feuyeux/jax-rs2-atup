package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

public class AtupTestSuiteInfo extends AtupInfo {
    private Integer suiteId;
    private String suiteName;
    private Integer suiteType;
    private Integer suiteStatus;

    public AtupTestSuiteInfo(AtupTestSuite testSuite) {
        suiteId = testSuite.getSuiteId();
        suiteName = testSuite.getSuiteName();
        suiteStatus = testSuite.getSuiteStatus();
        suiteType = testSuite.getSuiteType();
    }

    public AtupTestSuiteInfo() {
    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Integer suiteId) {
        this.suiteId = suiteId;
    }

    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(String suiteName) {
        this.suiteName = suiteName;
    }

    public Integer getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(Integer suiteType) {
        this.suiteType = suiteType;
    }

    public Integer getSuiteStatus() {
        return suiteStatus;
    }

    public void setSuiteStatus(Integer suiteStatus) {
        this.suiteStatus = suiteStatus;
    }
}
