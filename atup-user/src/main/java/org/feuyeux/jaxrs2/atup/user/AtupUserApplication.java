package org.feuyeux.jaxrs2.atup.user;

import org.feuyeux.jaxrs2.atup.user.resource.AtupUserResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest-api/*")
class AtupUserApplication extends ResourceConfig {
    public AtupUserApplication() {
        register(AtupUserResource.class);
        register(org.feuyeux.jaxrs2.atup.core.util.AtupCrossDomainFilter.class);
    }
}