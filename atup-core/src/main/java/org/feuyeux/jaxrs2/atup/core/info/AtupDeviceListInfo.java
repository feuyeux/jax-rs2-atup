package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;

public class AtupDeviceListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupDevice> deviceList;

    public AtupDeviceListInfo(List<AtupDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public AtupDeviceListInfo() {
    }

    public List<AtupDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<AtupDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
