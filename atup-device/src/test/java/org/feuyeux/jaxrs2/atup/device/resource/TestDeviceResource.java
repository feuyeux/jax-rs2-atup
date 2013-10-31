package org.feuyeux.jaxrs2.atup.device.resource;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDeviceResource {
    private final static Logger LOGGER = Logger.getLogger(TestDeviceResource.class);

    @Test
    public void testFindAll() {
        AtupRequest<AtupDevice> request = new AtupRequest<AtupDevice>();
        AtupDevice device = request.rest("GET", "http://localhost:8080/device/api/devices/1", null, null, MediaType.APPLICATION_JSON_TYPE, AtupDevice.class);
        LOGGER.info(device);
    }
}
