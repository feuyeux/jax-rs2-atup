package org.feuyeux.jaxrs2.atup.device.resource;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.MediaType;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TSDeviceResource {
    private final static Logger LOGGER = Logger.getLogger(TSDeviceResource.class);

    @Test
    public void testFindAll() {
        AtupRequest<AtupDevice> request = new AtupRequest<AtupDevice>();
        AtupDevice device = request.rest("GET", "http://localhost:8080/device/api/devices/1", null, null, MediaType.APPLICATION_JSON_TYPE, AtupDevice.class);
        LOGGER.info(device);
    }
}
