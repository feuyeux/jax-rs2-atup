package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

public class CreatTestSuite {

    public static final String SUITE_NAME = "FUNCTION TEST";

    public static AtupTestSuite buildTestSuite() {
        final AtupTestSuite entity = new AtupTestSuite(CreatTestSuite.SUITE_NAME, AtupParam.NORMAL_SUITE, AtupParam.NORMAL_CASE);
        return entity;
    }
}
