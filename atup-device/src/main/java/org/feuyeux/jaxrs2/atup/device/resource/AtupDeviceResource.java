package org.feuyeux.jaxrs2.atup.device.resource;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.device.service.AtupDeviceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(AtupApi.DEVICE_PATH)
public class AtupDeviceResource {
    private static final Logger LOGGER = Logger.getLogger(AtupDeviceResource.class);
    @Autowired
    private AtupDeviceService service;

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public AtupDevice getDevice(@PathParam("id") final Integer deviceId) {
        AtupUser atupUser = new AtupUser(9527, AtupParam.USER_ADMIN, "Eric");
        AtupDevice atupDevice = new AtupDevice(deviceId, atupUser, "192.168.0.166", "QUICK-Tester",
                AtupParam.DEVICE_IDLE, AtupParam.DEVICE_SPEED);
        LOGGER.info(atupDevice);
        return atupDevice;
    }
}
