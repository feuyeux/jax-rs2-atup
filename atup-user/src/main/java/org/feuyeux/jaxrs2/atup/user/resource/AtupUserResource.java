package org.feuyeux.jaxrs2.atup.user.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserListInfo;
import org.feuyeux.jaxrs2.atup.user.service.AtupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path(AtupApi.USER_PATH)
@Component
public class AtupUserResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupUserResource.class.getName());
    @Autowired
    AtupUserService service;

    public AtupUserResource() {

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createUser(@Context final HttpHeaders headers, final AtupUserInfo userInfo) {
        Response response = checkRole(headers, AtupParam.USER_ADMIN);
        if (response == null) {
            return createUser(userInfo);
        } else {
            return response;
        }
    }

    public Response checkRole(HttpHeaders headers, Integer role) {
        Integer userId = Integer.valueOf(headers.getRequestHeader(AtupApi.ATUP_USER_HEAD).get(0));
        Integer userRole = Integer.valueOf(headers.getRequestHeader(AtupApi.ATUP_USER_ROLE_HEAD).get(0));
        if (userId == null) {
            final AtupUserInfo result = new AtupUserInfo("No user info found.", AtupErrorCode.UNAUTHORIZED_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        } else {
            final AtupUser user = service.getUser(userId);
            if (!user.getUserRole().equals(role) || !userRole.equals(role)) {
                final AtupUserInfo result = new AtupUserInfo("No permission for this request.", AtupErrorCode.FORBIDDEN_ERROR);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
            } else {
                return null;
            }
        }
    }

    private Response createUser(AtupUserInfo userInfo) {
        try {
            final AtupUser newUser = service.createUser(userInfo);
            final AtupUserInfo result = new AtupUserInfo(newUser);
            return Response.ok().entity(result).build();
        } catch (final Exception e) {
            final AtupUserInfo result = new AtupUserInfo(e.getMessage(), AtupErrorCode.PERSIST_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response updateUser(final AtupUserInfo userInfo) {
        try {
            final AtupUser newUser = service.updateUser(userInfo);
            final AtupUserInfo result = new AtupUserInfo(newUser);
            return Response.ok().entity(result).build();
        } catch (final Exception e) {
            final AtupUserInfo result = new AtupUserInfo(e.getMessage(), AtupErrorCode.PERSIST_ERROR);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }

    @GET
    @Path("{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserInfo getUser(@PathParam("user") final String userName) {
        final AtupUser user = service.getUser(userName);
        return new AtupUserInfo(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserListInfo getUsers() {
        final List<AtupUserInfo> users = service.getUserList();
        return new AtupUserListInfo(users);
    }

    @GET
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupUserInfo getUser(@QueryParam("user") final String userName, @QueryParam("password") final String password) {
        try {
            final AtupUser user = service.getUser(userName.trim());
            final AtupUserInfo result = new AtupUserInfo(user);
            if (result.getPassWord().equals(password)) {
                return result;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new AtupUserInfo("Not found this user, please try again.", AtupErrorCode.PERSIST_ERROR);
        }
        return null;
    }
}