package org.feuyeux.jaxrs2.atup.device.resource;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupDeviceInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.feuyeux.jaxrs2.atup.device.service.AtupDeviceService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createDevice(AtupDeviceInfo deviceInfo) {
        try {
            AtupDevice atupDevice = service.createDevice(deviceInfo);
            AtupDeviceInfo result = new AtupDeviceInfo(atupDevice);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            AtupUserInfo result = new AtupUserInfo(e.getMessage(), AtupErrorCode.PERSIST_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }
}
