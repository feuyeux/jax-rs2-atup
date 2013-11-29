package org.feuyeux.jaxrs2.atup.core.info;

import java.util.Date;

public class AtupDeviceInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;

    private String deviceHost;
    private String deviceName;
    private Integer deviceStatus;
    private Integer deviceType;
    private Integer deviceId;
    private AtupUserInfo userInfo;

    private Date createTime;
    private Date updateTime;

    public AtupDeviceInfo() {
    }

    public AtupDeviceInfo(String deviceHost, String deviceName, Integer deviceType) {
        this.deviceHost = deviceHost;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
    }

    public String getDeviceHost() {
        return deviceHost;
    }

    public void setDeviceHost(String deviceHost) {
        this.deviceHost = deviceHost;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public AtupUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(AtupUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
