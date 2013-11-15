package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;

import java.util.Date;

public class AtupTestResultInfo extends AtupInfo {
    private Integer resultId;
    private AtupTestCaseInfo testCase;
    private AtupUserInfo user;
    private Integer resultStatus;
    private String resultBody;
    private Date createTime;
    private Date updateTime;

    public AtupTestResultInfo(AtupTestResult testResult) {
        resultId = testResult.getResultId();
        user = new AtupUserInfo(testResult.getUser());
        resultBody = testResult.getResultBody();
        resultStatus = testResult.getResultStatus();
        testCase = new AtupTestCaseInfo(testResult.getTestCase());
        createTime = testResult.getCreateTime();
        updateTime = testResult.getUpdateTime();
    }

    public AtupTestResultInfo() {
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public AtupTestCaseInfo getTestCase() {
        return testCase;
    }

    public void setTestCase(AtupTestCaseInfo testCase) {
        this.testCase = testCase;
    }

    public AtupUserInfo getUser() {
        return user;
    }

    public void setUser(AtupUserInfo user) {
        this.user = user;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getResultBody() {
        return resultBody;
    }

    public void setResultBody(String resultBody) {
        this.resultBody = resultBody;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
