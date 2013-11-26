package org.feuyeux.jaxrs2.atup.user;

import javax.ws.rs.ApplicationPath;

import org.feuyeux.jaxrs2.atup.user.resource.AtupUserResource;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest-api/*")
public class AtupUserApplication extends ResourceConfig {
    public AtupUserApplication() {
        register(AtupUserResource.class);
        register(org.feuyeux.jaxrs2.atup.core.util.AtupCrossDomainFilter.class);
    }
}