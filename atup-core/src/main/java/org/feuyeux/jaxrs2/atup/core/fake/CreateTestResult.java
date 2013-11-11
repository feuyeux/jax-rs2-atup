package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import java.util.Date;

public class CreateTestResult {
    public static final String CASE_RESULT_BODY = "Test case body";

    public static AtupTestResult buildTestResult() {
        AtupTestCase testCase = CreateTestCase.buildTestCase();
        AtupUser user = CreateUser.buildUser();
        Integer resultStatus = AtupParam.RESULT_OK;
        String resultBody = CASE_RESULT_BODY;
        Date createTime = new Date();
        Date updateTime = new Date();
        AtupTestResult testResult = new AtupTestResult(testCase, user, resultStatus, resultBody, createTime, updateTime);
        return testResult;
    }
}
