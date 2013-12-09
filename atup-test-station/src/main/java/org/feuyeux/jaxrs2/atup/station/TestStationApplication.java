package org.feuyeux.jaxrs2.atup.station;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest-api/*")
public class TestStationApplication extends ResourceConfig {
    public TestStationApplication() {
        packages("org.feuyeux.jaxrs2.atup.station.resource");
    }
}