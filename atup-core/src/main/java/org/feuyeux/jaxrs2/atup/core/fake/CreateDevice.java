package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import java.util.Date;

public class CreateDevice {
    public static AtupDevice buildAtupDevice() {
        final AtupUser user = CreateUser.buildUser();
        final String deviceHost = "10.12.13.14";
        final String deviceName = "Test Station";
        final Integer deviceStatus = AtupParam.DEVICE_IDLE;
        final Integer deviceType = AtupParam.DEVICE_QUALITY;
        final Date createTime = new Date();
        final Date updateTime = new Date();
        final AtupDevice device = new AtupDevice(user, deviceHost, deviceName, deviceStatus, deviceType, createTime, updateTime);
        return device;
    }
}
