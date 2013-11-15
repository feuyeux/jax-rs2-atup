package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestCaseService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(AtupApi.TEST_CASE_PATH)
public class AtupTestCaseResource {
    @Autowired
    AtupTestCaseService service;

    public AtupTestCaseResource() {

    }

    @GET
    @Path("cases")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestCaseListInfo getCases(@QueryParam("start") Integer start,
                                         @QueryParam("size") Integer size) {
        if (start == null || size == null) {
            return new AtupTestCaseListInfo(AtupErrorInfo.INVALID_PARAM, AtupErrorCode.INVALID_PARAM);
        }
        AtupTestCaseListInfo result = service.getCases(start, size);
        return result;
    }
}

