package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import java.util.Date;

public class CreateDevice {
    public static AtupDevice buildAtupDevice() {
        AtupUser user = CreateUser.buildUser();
        String deviceHost = "10.12.13.14";
        String deviceName = "Test Station";
        Integer deviceStatus = AtupParam.DEVICE_IDLE;
        Integer deviceType = AtupParam.DEVICE_QUALITY;
        Date createTime = new Date();
        Date updateTime = new Date();
        AtupDevice device = new AtupDevice(user, deviceHost, deviceName, deviceStatus, deviceType, createTime, updateTime);
        return device;
    }
}
