package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

import java.util.Date;

public class CreateTestCase {
    public static AtupTestCase one;
    public static final String CASE_NAME = "Test case name_";
    private static final String CASE_BODY = "Test case body";

    public static AtupTestCase buildTestCase(final AtupTestSuite suite, final String caseName) {
        final String caseBody = CreateTestCase.CASE_BODY;
        final Date createTime = new Date();
        final Date updateTime = new Date();
        final Integer caseStatus = AtupParam.NORMAL_CASE;
        return new AtupTestCase(caseName, suite, caseBody, createTime, updateTime, caseStatus);
    }

    public static AtupTestCase buildOneTestCase(final AtupTestSuite suite, final String caseName) {
        if (one == null) {
            final String caseBody = CreateTestCase.CASE_BODY;
            final Date createTime = new Date();
            final Date updateTime = new Date();
            final Integer caseStatus = AtupParam.NORMAL_CASE;
            one = new AtupTestCase(caseName, suite, caseBody, createTime, updateTime, caseStatus);
        }
        return one;
    }

    public static AtupTestCase buildTestCase(final AtupTestSuite suite) {
        return buildTestCase(suite, CreateTestCase.CASE_NAME);
    }
}
