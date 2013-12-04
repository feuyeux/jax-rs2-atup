package org.feuyeux.jaxrs2.atup.device.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.device.dao.AtupDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupDeviceService {

    @Autowired
    AtupDeviceDao dao;

    public AtupDevice createDevice(AtupDevice device) {
        Date now = Calendar.getInstance().getTime();
        device.setCreateTime(now);
        device.setUpdateTime(now);
        return dao.save(device);
    }

    public AtupDevice updateDevice(AtupDevice device) {
        Date now = Calendar.getInstance().getTime();
        device.setUpdateTime(now);
        return dao.update(device);
    }

    public List<AtupDevice> getDeviceList(Integer userId) {
        return dao.findDevicesByUser(userId);
    }
}
