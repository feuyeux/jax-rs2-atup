package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;

import java.util.List;

public class AtupDeviceListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupDevice> deviceList;

    public AtupDeviceListInfo(final List<AtupDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public AtupDeviceListInfo() {
    }

    public List<AtupDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(final List<AtupDevice> deviceList) {
        this.deviceList = deviceList;
    }
}
