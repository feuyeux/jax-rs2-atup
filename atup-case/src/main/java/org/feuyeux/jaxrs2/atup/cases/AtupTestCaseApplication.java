package org.feuyeux.jaxrs2.atup.cases;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class AtupTestCaseApplication extends ResourceConfig {
    public AtupTestCaseApplication() {
        register(AtupTestCaseResource.class);
    }
}