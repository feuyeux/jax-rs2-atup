package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;

public class CreatTestSuite {
    public static final String SUITE_NAME = "FUNCTION TEST";
    public static AtupTestSuite one;
    public static AtupTestSuite other;

    public static AtupTestSuite buildTestSuite() {
        return new AtupTestSuite(CreatTestSuite.SUITE_NAME + System.nanoTime(), AtupParam.NORMAL_SUITE, AtupParam.NORMAL_CASE);
    }

    public static AtupTestSuite buildOneTestSuite() {
        if (one == null) {
            one = new AtupTestSuite(CreatTestSuite.SUITE_NAME + System.nanoTime(), AtupParam.NORMAL_SUITE, AtupParam.NORMAL_CASE);
        }
        return one;
    }

    public static AtupTestSuite buildOneTestSuite(String suiteName) {
        if (other == null) {
            other = new AtupTestSuite(suiteName, AtupParam.NORMAL_SUITE, AtupParam.NORMAL_CASE);
        }
        return other;
    }

    public static void removeOne() {
        one = null;
    }
}
