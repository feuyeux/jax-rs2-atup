package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestCaseService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path(AtupApi.TEST_CASE_PATH)
public class AtupTestCaseResource {
    @Autowired
    AtupTestCaseService service;

    public AtupTestCaseResource() {

    }

    @Path("cases")
    public AtupTestCaseListInfo getCases(@QueryParam("start") Integer start,
                                         @QueryParam("size") Integer size) {
        AtupTestCaseListInfo result = service.getCases(start, size);
        return result;
    }
}

