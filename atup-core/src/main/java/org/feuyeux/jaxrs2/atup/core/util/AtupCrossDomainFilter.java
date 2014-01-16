package org.feuyeux.jaxrs2.atup.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AtupCrossDomainFilter implements ContainerRequestFilter, ContainerResponseFilter {
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private final Logger log = LogManager.getLogger(AtupCrossDomainFilter.class.getName());

    @Override
    public void filter(final ContainerRequestContext creq, final ContainerResponseContext cres) throws IOException {
        cres.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");
    }

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        log.debug(requestContext.getHeaders().getFirst(ACCESS_CONTROL_ALLOW_ORIGIN));
    }
}