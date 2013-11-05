package org.feuyeux.jaxrs2.atup.device.service;

import org.feuyeux.jaxrs2.atup.device.dao.AtupDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupDeviceService {

    @Autowired
    AtupDeviceDao dao;
}
