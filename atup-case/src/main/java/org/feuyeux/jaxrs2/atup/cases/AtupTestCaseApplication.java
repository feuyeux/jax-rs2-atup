package org.feuyeux.jaxrs2.atup.cases;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest-api/*")
public class AtupTestCaseApplication extends ResourceConfig {
    public AtupTestCaseApplication() {
        packages("org.feuyeux.jaxrs2.atup.cases.resource");
    }
}