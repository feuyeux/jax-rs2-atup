package org.feuyeux.jaxrs2.atup.station.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.constant.AtupVariable;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;

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
        try {
            log.info("keep-a-live :: Request host =" + request.getRemoteAddr());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return status;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestResult test(@Context HttpServletRequest request, AtupTestCase testCase) throws InterruptedException {
        Date createTime = new Date();
        long s = System.nanoTime();
        status = AtupParam.DEVICE_RUNNING;
        try {
            log.info("testing :: Request host =" + request.getRemoteAddr());
            log.info("testing :: testCase =" + testCase);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        final AtupTestResult testResult = new AtupTestResult(testCase, AtupParam.RESULT_SUCCESS, Long.toString(System.nanoTime() - s), createTime, createTime);
        Thread.sleep(AtupVariable.TESTING_TIMEOUT);
        status = AtupParam.DEVICE_IDLE;
        return testResult;
    }
}
