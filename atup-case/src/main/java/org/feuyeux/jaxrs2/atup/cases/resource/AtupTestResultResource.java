package org.feuyeux.jaxrs2.atup.cases.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestResultService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultListInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path(AtupApi.TEST_RESULT_PATH)
public class AtupTestResultResource {
    @Autowired
    AtupTestResultService service;

    @GET
    @Path("results")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestResultListInfo getResultsByUser(@Context final HttpHeaders headers, @QueryParam("start") final Integer start,
            @QueryParam("size") final Integer size) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        if (start == null || size == null) {
            return new AtupTestResultListInfo(AtupErrorInfo.INVALID_PARAM, AtupErrorCode.INVALID_PARAM);
        }
        final AtupTestResultListInfo result = service.getResults(Integer.valueOf(userId), start, size);
        return result;
    }
}
