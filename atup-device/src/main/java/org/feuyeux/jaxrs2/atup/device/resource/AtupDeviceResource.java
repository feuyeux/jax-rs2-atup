package org.feuyeux.jaxrs2.atup.device.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupDeviceListInfo;
import org.feuyeux.jaxrs2.atup.device.service.AtupDeviceService;
import org.feuyeux.jaxrs2.atup.device.service.StationDetectService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path(AtupApi.DEVICE_PATH)
public class AtupDeviceResource {
    private final Logger log = LogManager.getLogger(AtupDeviceResource.class.getName());
    @Autowired
    private AtupDeviceService service;
    @Autowired
    private StationDetectService detectService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupDevice createDevice(@Context final HttpHeaders headers, final AtupDevice deviceInfo) {
        try {
            fillUser(headers, deviceInfo);
            final AtupDevice atupDevice = service.createDevice(deviceInfo);
            return atupDevice;
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupDevice updateDevice(@Context final HttpHeaders headers, final AtupDevice deviceInfo) {
        try {
            if (deviceInfo.getUser() == null) {
                fillUser(headers, deviceInfo);
            }
            final AtupDevice atupDevice = service.updateDevice(deviceInfo);
            return atupDevice;
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }

    private void fillUser(final HttpHeaders headers, final AtupDevice deviceInfo) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        final AtupUser currentUser = new AtupUser();
        currentUser.setUserId(Integer.valueOf(userId));
        deviceInfo.setUser(currentUser);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupDeviceListInfo getDevicesByUser(@Context final HttpHeaders headers) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        final List<AtupDevice> devices = service.getDeviceList(Integer.valueOf(userId));
        final AtupDeviceListInfo result = new AtupDeviceListInfo(devices);
        return result;
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupDeviceListInfo getDevices() {
        final List<AtupDevice> devices = service.getDeviceList();
        final AtupDeviceListInfo result = new AtupDeviceListInfo(devices);
        return result;
    }

    @POST
    @Path("detect")
    public void detect() throws ExecutionException, InterruptedException {
        detectService.detect();
    }
}
