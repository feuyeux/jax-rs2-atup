package org.feuyeux.jaxrs2.atup.device.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.info.AtupDeviceInfo;
import org.feuyeux.jaxrs2.atup.device.dao.AtupDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AtupDeviceService {

    @Autowired
    AtupDeviceDao dao;

    public AtupDevice createDevice(AtupDeviceInfo deviceInfo) {
        AtupDevice device = new AtupDevice(deviceInfo);
        Date now = Calendar.getInstance().getTime();
        device.setCreateTime(now);
        device.setUpdateTime(now);
        return dao.save(device);
    }
}
