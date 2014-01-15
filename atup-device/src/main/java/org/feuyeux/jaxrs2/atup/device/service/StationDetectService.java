package org.feuyeux.jaxrs2.atup.device.service;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.constant.AtupVariable;
import org.feuyeux.jaxrs2.atup.core.dao.AtupDeviceDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class StationDetectService {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(StationDetectService.class.getName());
    private List<AtupDevice> deviceList;
    @Autowired
    AtupDeviceDao dao;
    private final ScheduledExecutorService detectTask = new ScheduledThreadPoolExecutor(1);

    public void detect() {
        detectTask.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    deviceList = dao.findAll();
                    for (final AtupDevice atupDevice : deviceList) {
                        final String detectPath = AtupApi.PROTOCOL + atupDevice.getDeviceHost() + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
                        final AtupRequest<String, Integer> request = new AtupRequest<>();
                        request.timeout(AtupVariable.DETECT_CONNECT_TIMEOUT, 0);
                        try {
                            final Integer result = request.rest(AtupRequest.GET, detectPath, Integer.class);
                            log.debug("detecting " + atupDevice.getDeviceHost() + " :" + result);
                            if (!atupDevice.getDeviceStatus().equals(result)) {
                                atupDevice.setDeviceStatus(result);
                                dao.update(atupDevice);
                            }
                        } catch (final Exception e) {
                            log.error(e);
                            atupDevice.setDeviceStatus(AtupParam.DEVICE_ERROR);
                            dao.update(atupDevice);
                        }
                    }

                } catch (final Exception e) {
                    log.error(e);
                }
            }
        }, 0, AtupVariable.DETECT_INTERVAL, TimeUnit.SECONDS);
    }
}
