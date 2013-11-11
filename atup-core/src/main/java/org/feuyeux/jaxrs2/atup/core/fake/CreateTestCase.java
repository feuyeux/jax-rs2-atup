package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

import java.util.Date;

public class CreateTestCase {

    public static final String CASE_NAME = "Test case name";
    public static final String CASE_BODY = "Test case body";

    public static AtupTestCase buildTestCase() {
        String caseName = CASE_NAME;
        AtupTestSuite suite = CreatTestSuite.buildTestSuite();
        AtupDevice device = CreateDevice.buildAtupDevice();
        String caseBody = CASE_BODY;
        Date createTime = new Date();
        Date updateTime = new Date();
        Integer caseStatus = AtupParam.NORMAL_CASE;
        AtupTestCase testCase = new AtupTestCase(caseName, suite, device, caseBody, createTime, updateTime, caseStatus);
        return testCase;
    }
}
