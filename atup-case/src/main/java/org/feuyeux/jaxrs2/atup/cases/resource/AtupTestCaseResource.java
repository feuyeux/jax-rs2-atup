package org.feuyeux.jaxrs2.atup.cases.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestCaseService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path(AtupApi.TEST_CASE_PATH)
public class AtupTestCaseResource {
    private final org.apache.logging.log4j.Logger LOGGER = 
            org.apache.logging.log4j.LogManager.getLogger(AtupTestCaseResource.class.getName());
    @Autowired
    AtupTestCaseService service;

    public AtupTestCaseResource() {

    }

    @GET
    @Path("cases")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestCaseListInfo getCases(@QueryParam("start") final Integer start, @QueryParam("size") final Integer size) {
        if (start == null || size == null) {
            return new AtupTestCaseListInfo(AtupErrorInfo.INVALID_PARAM, AtupErrorCode.INVALID_PARAM);
        }
        final AtupTestCaseListInfo result = service.getCases(start, size);
        return result;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestCase createSuite(final AtupTestCase testCase) {
        try {
            return service.createSuite(testCase);
        } catch (final Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    @GET
    @Path("{caseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestCase getCases(@PathParam("caseName") final String caseName) {
        try {
            final AtupTestCase existingTestCase = service.getCaseByName(caseName);
            return existingTestCase;
        } catch (final Exception e) {
            return null;
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestCase updateSuite(final AtupTestCase testCase) {
        try {
            return service.updateSuite(testCase);
        } catch (final Exception e) {
            LOGGER.error(e);
            return null;
        }
    }
}
