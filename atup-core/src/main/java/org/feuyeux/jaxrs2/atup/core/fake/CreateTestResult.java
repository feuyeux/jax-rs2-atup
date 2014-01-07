package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import java.util.Date;

public class CreateTestResult {
    private static final String CASE_RESULT_BODY = "Test case body";

    public static AtupTestResult buildTestResult() {
        final AtupTestCase testCase = CreateTestCase.buildTestCase();
        final AtupUser user = CreateUser.buildUser();
        final Integer resultStatus = AtupParam.RESULT_OK;
        final String resultBody = CreateTestResult.CASE_RESULT_BODY;
        final Date createTime = new Date();
        final Date updateTime = new Date();
        return new AtupTestResult(testCase, user, null, resultStatus, resultBody, createTime, updateTime);
    }
}
