package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

import java.util.Date;

public class CreateTestCase {

    public static final String CASE_NAME = "Test case name";
    public static final String CASE_BODY = "Test case body";

    public static AtupTestCase buildTestCase() {
        final String caseName = CreateTestCase.CASE_NAME;
        final AtupTestSuite suite = CreatTestSuite.buildTestSuite();
        final String caseBody = CreateTestCase.CASE_BODY;
        final Date createTime = new Date();
        final Date updateTime = new Date();
        final Integer caseStatus = AtupParam.NORMAL_CASE;
        final AtupTestCase testCase = new AtupTestCase(caseName, suite, caseBody, createTime, updateTime, caseStatus);
        return testCase;
    }
}
