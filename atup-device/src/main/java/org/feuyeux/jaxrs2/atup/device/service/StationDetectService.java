package org.feuyeux.jaxrs2.atup.device.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.feuyeux.jaxrs2.atup.device.dao.AtupDeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationDetectService {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(StationDetectService.class.getName());
    private static final String PROTOCOL = "http://";
    private static final String SERVICE_PORT = "8080";
    private static final String SERVICE_PATH = "/atup-test-station/rest-api/station";
    private List<AtupDevice> deviceList;
    @Autowired
    AtupDeviceDao dao;
    ScheduledExecutorService detectTask = new ScheduledThreadPoolExecutor(1);

    public void detect() throws InterruptedException, ExecutionException {
        detectTask.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    detecting();
                } catch (Exception e) {
                    log.error(e);
                }
                //log.debug("test");
            }

            private void detecting() {
                deviceList = dao.findAll();
                for (AtupDevice atupDevice : deviceList) {
                    String detectPath = PROTOCOL + atupDevice.getDeviceHost() + ":" + SERVICE_PORT + SERVICE_PATH;
                    AtupRequest<Integer> request = new AtupRequest<>();
                    try {
                        Integer result = request.rest(AtupRequest.GET, detectPath, Integer.class);
                        log.debug("detecting " + atupDevice.getDeviceHost() + " :" + result);
                        if (!atupDevice.getDeviceStatus().equals(result)) {
                            atupDevice.setDeviceStatus(result);
                            dao.update(atupDevice);
                        }
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        StationDetectService test = new StationDetectService();
        test.detect();
    }
}
