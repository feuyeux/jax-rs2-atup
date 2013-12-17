package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestSuiteService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path(AtupApi.TEST_SUITE_PATH)
public class AtupTestSuiteResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupTestSuiteResource.class.getName());
    @Autowired
    private AtupTestSuiteService service;

    @GET
    @Path("suites")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestSuiteListInfo getCases(@QueryParam("start") final Integer start, @QueryParam("size") final Integer size) {
        final AtupTestSuiteListInfo result = service.getSuites(start, size);
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestSuite createSuite(final AtupTestSuite testSuite) {
        try {
            return service.createSuite(testSuite);
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestSuite updateSuite(final AtupTestSuite testSuite) {
        try {
            return service.updateSuite(testSuite);
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }
}
