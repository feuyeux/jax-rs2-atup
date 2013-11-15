package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.AtupTestCaseService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Path;

@Path(AtupApi.TEST_CASE_PATH)
public class AtupTestCaseResource {
    @Autowired
    AtupTestCaseService service;

    public AtupTestCaseResource() {

    }
}

