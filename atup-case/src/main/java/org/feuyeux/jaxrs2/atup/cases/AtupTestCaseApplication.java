package org.feuyeux.jaxrs2.atup.cases;

import org.feuyeux.jaxrs2.atup.cases.resource.AtupTestCaseResource;
import org.feuyeux.jaxrs2.atup.cases.resource.AtupTestJobResource;
import org.feuyeux.jaxrs2.atup.cases.resource.AtupTestResultResource;
import org.feuyeux.jaxrs2.atup.cases.resource.AtupTestSuiteResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest-api/*")
class AtupTestCaseApplication extends ResourceConfig {
    public AtupTestCaseApplication() {
        register(AtupTestSuiteResource.class);
        register(AtupTestCaseResource.class);
        register(AtupTestResultResource.class);
        register(AtupTestJobResource.class);
    }
}