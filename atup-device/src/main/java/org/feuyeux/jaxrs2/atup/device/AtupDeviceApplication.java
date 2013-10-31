package org.feuyeux.jaxrs2.atup.device;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api/*")
public class AtupDeviceApplication extends ResourceConfig {
    public AtupDeviceApplication() {
        register(org.feuyeux.jaxrs2.atup.device.resource.AtupDeviceResource.class);
    }
}