package org.feuyeux.jaxrs2.atup.device.resource;

import javax.ws.rs.core.MediaType;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TSDeviceResource {
    public static final String BASE_URI = "http://localhost:8080/atup-device/restapi/";
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TSDeviceResource.class.getName());
    @Test
    public void testFindAll() {
        AtupRequest<AtupDevice> request = new AtupRequest<AtupDevice>();
        AtupDevice device = request.rest("GET", BASE_URI + AtupApi.DEVICE_PATH + "/1", null, null, MediaType.APPLICATION_JSON_TYPE, AtupDevice.class);
        log.info(device);
    }
}
