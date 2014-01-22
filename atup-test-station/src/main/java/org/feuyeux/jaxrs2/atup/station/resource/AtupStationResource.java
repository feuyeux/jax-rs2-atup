package org.feuyeux.jaxrs2.atup.station.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.constant.AtupVariable;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Singleton
@Component
@Path(AtupApi.TEST_STATION_PATH)
public class AtupStationResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupStationResource.class.getName());
    private Integer status = AtupParam.DEVICE_IDLE;

    public AtupStationResource() {

    }

    @GET
    public Integer keepALive(@Context HttpServletRequest request) {
        //log.info("Request host=" + request.getRemoteHost());
        if (request != null) {
            log.info("keep-a-live :: Request host =" + request.getRemoteAddr());
        }
        return status;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestResult test(@Context HttpServletRequest request, AtupTestCase testCase) throws InterruptedException {
        if (request != null) {
            log.info("Test Case Launched :: Request host =" + request.getRemoteAddr());
        }
        if (testCase != null) {
            log.info("Test Case Launched :: TestCase =" + testCase);
        }
        Date createTime = new Date();
        long s = System.nanoTime();
        status = AtupParam.DEVICE_RUNNING;
        final AtupTestResult testResult = new AtupTestResult(testCase, AtupParam.RESULT_SUCCESS, Long.toString(System.nanoTime() - s), createTime, createTime);
        Thread.sleep(AtupVariable.TESTING_TIMEOUT);
        status = AtupParam.DEVICE_IDLE;
        return testResult;
    }
}
