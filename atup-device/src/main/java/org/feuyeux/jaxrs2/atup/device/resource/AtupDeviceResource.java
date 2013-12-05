package org.feuyeux.jaxrs2.atup.device.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupDeviceListInfo;
import org.feuyeux.jaxrs2.atup.device.service.AtupDeviceService;
import org.springframework.beans.factory.annotation.Autowired;

@Path(AtupApi.DEVICE_PATH)
public class AtupDeviceResource {
    private static final Logger LOGGER = Logger.getLogger(AtupDeviceResource.class);
    @Autowired
    private AtupDeviceService service;

    @Path("{id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AtupDevice getDevice(@PathParam("id") final Integer deviceId) {
        AtupUser atupUser = new AtupUser(9527, AtupParam.USER_ADMIN, "Eric");
        AtupDevice atupDevice = new AtupDevice(deviceId, atupUser, "192.168.0.166", "QUICK-Tester", AtupParam.DEVICE_IDLE, AtupParam.DEVICE_SPEED);
        LOGGER.info(atupDevice);
        return atupDevice;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupDevice createDevice(@Context final HttpHeaders headers, AtupDevice deviceInfo) {
        try {
            String userId = headers.getRequestHeader("Atup-User").get(0);
            AtupUser currentUser = new AtupUser();
            currentUser.setUserId(Integer.valueOf(userId));
            deviceInfo.setUser(currentUser);
            AtupDevice atupDevice = service.createDevice(deviceInfo);
            return atupDevice;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupDevice updateDevice(@Context final HttpHeaders headers, AtupDevice deviceInfo) {
        try {
            String userId = headers.getRequestHeader("Atup-User").get(0);
            AtupUser currentUser = new AtupUser();
            currentUser.setUserId(Integer.valueOf(userId));
            deviceInfo.setUser(currentUser);
            AtupDevice atupDevice = service.updateDevice(deviceInfo);
            return atupDevice;
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupDeviceListInfo getDevicesByUser(@Context final HttpHeaders headers) {
        String userId = headers.getRequestHeader("Atup-User").get(0);
        List<AtupDevice> devices = service.getDeviceList(Integer.valueOf(userId));
        AtupDeviceListInfo result = new AtupDeviceListInfo(devices);
        return result;
    }
}
