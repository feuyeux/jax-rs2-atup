package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupDeviceListInfo extends AtupInfo {

    private List<AtupDeviceInfo> deviceList;

    public List<AtupDeviceInfo> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<AtupDeviceInfo> deviceList) {
        this.deviceList = deviceList;
    }
}
