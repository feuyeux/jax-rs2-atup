package org.feuyeux.jaxrs2.atup.core.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class AtupUserFilter implements ContainerRequestFilter, ContainerResponseFilter {   
    @Override
    public void filter(ContainerRequestContext creq, ContainerResponseContext cres) throws IOException {
        String userId=creq.getHeaders().getFirst("Atup-User");
        cres.getHeaders().add("Atup-User", userId);
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cres.getHeaders().add("Access-Control-Max-Age", "1209600");       
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
      System.err.println(requestContext);
    }
}