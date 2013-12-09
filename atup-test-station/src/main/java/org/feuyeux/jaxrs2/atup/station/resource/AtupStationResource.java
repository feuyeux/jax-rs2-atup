package org.feuyeux.jaxrs2.atup.station.resource;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Singleton
@Path("station")
public class AtupStationResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupStationResource.class.getName());

    public AtupStationResource() {

    }

    @GET
    public String keepALive(@Context HttpServletRequest request) {
        //log.info("Request host=" + request.getRemoteHost());
        log.info("Request host=" + request.getRemoteAddr());        
        return "OK";
    }
}
