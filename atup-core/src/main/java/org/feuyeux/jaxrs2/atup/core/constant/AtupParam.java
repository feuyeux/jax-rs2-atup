package org.feuyeux.jaxrs2.atup.core.constant;

public interface AtupParam {
    /*Test Suite*/
    Integer NORMAL_SUITE = 1;
    Integer PERFORMANCE_SUITE = 2;
    Integer EDGE_SUITE = 3;

    /*Test Case*/
    Integer NORMAL_CASE = 0;
    Integer DISABLED_CASE = 1;

    /*User*/
    Integer USER_ADMIN = 1;
    Integer USER_JOB_KILLER = 2;
    Integer USER_DEVICE_MANAGER = 3;
    Integer USER_USER = 4;

    Integer NORMAL_USER = 0;
    Integer DISABLED_USER = 1;

    /*Device */
    Integer DEVICE_IDLE = 0;
    Integer DEVICE_RUNNING = 1;
    Integer DEVICE_ERROR = 2;

    Integer DEVICE_SPEED = 11;
    Integer DEVICE_QUALITY = 21;

    /*Test Result*/
    Integer RESULT_SUCCESS = 0;
    Integer RESULT_FAILED = 1;
    Integer RESULT_UNKOWN = 2;

    /*Test Job*/
    Integer HIGH = 0;
    Integer MEDIUM = 1;
    Integer LOW = 2;
}
