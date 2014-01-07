package org.feuyeux.jaxrs2.atup.device.service;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDeviceDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AtupDeviceService {
    @Autowired
    AtupDeviceDao dao;

    public AtupDevice createDevice(final AtupDevice device) {
        final Date now = Calendar.getInstance().getTime();
        device.setCreateTime(now);
        device.setUpdateTime(now);
        return dao.save(device);
    }

    public AtupDevice updateDevice(final AtupDevice device) {
        final Date now = Calendar.getInstance().getTime();
        device.setUpdateTime(now);
        return dao.update(device);
    }

    public List<AtupDevice> getDeviceList(final Integer userId) {
        return dao.findDevicesByUser(userId);
    }

    public List<AtupDevice> getDeviceList() {
        return dao.findAll();
    }
}
