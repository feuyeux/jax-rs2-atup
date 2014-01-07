package org.feuyeux.jaxrs2.atup.station;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest-api/*")
class TestStationApplication extends ResourceConfig {
    public TestStationApplication() {
        packages("org.feuyeux.jaxrs2.atup.station.resource");
    }
}