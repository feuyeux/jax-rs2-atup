package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestSuiteService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path(AtupApi.TEST_SUITE_PATH)
public class AtupTestSuiteResource {

    @Autowired
    AtupTestSuiteService service;

    @GET
    @Path("suites")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestSuiteListInfo getCases(@QueryParam("start") Integer start,
                                          @QueryParam("size") Integer size) {
        AtupTestSuiteListInfo result = service.getSuites(start, size);
        return result;
    }
}
