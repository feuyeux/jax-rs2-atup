package org.feuyeux.jaxrs2.atup.user.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.feuyeux.jaxrs2.atup.user.service.AtupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(AtupApi.USER_PATH)
@Component
public class AtupUserResource {

    @Autowired
    AtupUserService service;

    public AtupUserResource() {

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createUser(AtupUserInfo userInfo) {
        try {
            AtupUser newUser = service.createUser(userInfo);
            AtupUserInfo result = new AtupUserInfo(newUser);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            AtupUserInfo result = new AtupUserInfo(e.getMessage(), AtupErrorCode.PERSIST_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
    }
}