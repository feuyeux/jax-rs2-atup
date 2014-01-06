package org.feuyeux.jaxrs2.atup.core.constant;

public interface AtupParam {
    Integer DEVICE_IDLE = 0;
    Integer DEVICE_RUNNING = 1;
    Integer DEVICE_ERROR = 2;

    Integer DEVICE_SPEED = 11;
    Integer DEVICE_QUALITY = 21;

    Integer RESULT_OK = 0;
    Integer RESULT_ERROR = 1;
    Integer RESULT_UNKOWN = 2;

    Integer NORMAL_CASE = 0;
    Integer DISABLED_CASE = 1;

    Integer NORMAL_SUITE = 1;
    Integer PERFORMANCE_SUITE = 2;
    Integer EDGE_SUITE = 3;

    Integer HIGH = 3;
    Integer MEDIUM = 2;
    Integer LOW = 1;

    Integer USER_ADMIN = 1;
    Integer USER_JOB_KILLER = 2;
    Integer USER_DEVICE_MANAGER = 3;
    Integer USER_USER = 4;
}
