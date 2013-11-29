package org.feuyeux.jaxrs2.atup.user.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserListInfo;
import org.feuyeux.jaxrs2.atup.user.service.AtupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response updateUser(AtupUserInfo userInfo) {
        try {
            AtupUser newUser = service.updateUser(userInfo);
            AtupUserInfo result = new AtupUserInfo(newUser);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            AtupUserInfo result = new AtupUserInfo(e.getMessage(), AtupErrorCode.PERSIST_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }

    @GET
    @Path("{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserInfo getUser(@PathParam("user") String userName) {
        AtupUser user = service.getUser(userName);
        AtupUserInfo result = new AtupUserInfo(user);
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserListInfo getUsers() {
        List<AtupUserInfo> users = service.getUserList();
        AtupUserListInfo result = new AtupUserListInfo(users);
        return result;
    }

    @GET
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserInfo getUser(@QueryParam("user") String userName, @QueryParam("password") String password) {
        AtupUser user = service.getUser(userName);
        AtupUserInfo result = new AtupUserInfo(user);
        if (result.getPassWord().equals(password)) {
            return result;
        }
        return null;
    }
}