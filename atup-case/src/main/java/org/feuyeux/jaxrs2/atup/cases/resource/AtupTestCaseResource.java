package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestCaseService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@Component
@Path(AtupApi.TEST_CASE_PATH)
public class AtupTestCaseResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupTestCaseResource.class.getName());
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
        return service.getCases(start, size);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestCase createSuite(final AtupTestCase testCase) {
        try {
            return service.createSuite(testCase);
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }

    @GET
    @Path("{caseName}")
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestCase getCases(@PathParam("caseName") final String caseName) {
        try {
            return service.getCaseByName(caseName);
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
            log.error(e);
            return null;
        }
    }
}
