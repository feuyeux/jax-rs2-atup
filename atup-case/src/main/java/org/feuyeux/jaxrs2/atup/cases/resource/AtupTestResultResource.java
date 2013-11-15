package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestResultService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(AtupApi.TEST_RESULT_PATH)
public class AtupTestResultResource {
    @Autowired
    AtupTestResultService service;

    @GET
    @Path("results")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestResultListInfo getResults(AtupUser user, @QueryParam("start") Integer start,
                                             @QueryParam("size") Integer size) {
        if (start == null || size == null) {
            return new AtupTestResultListInfo(AtupErrorInfo.INVALID_PARAM, AtupErrorCode.INVALID_PARAM);
        }
        AtupTestResultListInfo result = service.getResults(user, start, size);
        return result;
    }
}
