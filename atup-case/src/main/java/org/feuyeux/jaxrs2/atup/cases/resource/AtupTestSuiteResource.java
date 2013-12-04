package org.feuyeux.jaxrs2.atup.cases.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.cases.service.AtupTestSuiteService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteListInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path(AtupApi.TEST_SUITE_PATH)
public class AtupTestSuiteResource {
    private static final Logger LOGGER = Logger.getLogger(AtupTestSuiteResource.class);
    @Autowired
    AtupTestSuiteService service;

    @GET
    @Path("suites")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestSuiteListInfo getCases(@QueryParam("start") Integer start, @QueryParam("size") Integer size) {
        AtupTestSuiteListInfo result = service.getSuites(start, size);
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestSuite createSuite(AtupTestSuite testSuite) {
        try {
            return service.createSuite(testSuite);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestSuite updateSuite(AtupTestSuite testSuite) {
        try {
            return service.updateSuite(testSuite);
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }
}
