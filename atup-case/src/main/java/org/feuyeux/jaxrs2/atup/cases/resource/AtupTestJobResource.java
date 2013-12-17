package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.service.JobLaunchService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Path(AtupApi.TEST_JOB_PATH)
public class AtupTestJobResource {
    private final org.apache.logging.log4j.Logger log =
            org.apache.logging.log4j.LogManager.getLogger(AtupTestJobResource.class.getName());
    private final AtomicInteger JOB_ID = new AtomicInteger();

    @Autowired
    private JobLaunchService jobLaunchService;

    public AtupTestJobResource() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createJob(@Context final HttpHeaders headers, final AtupTestJobInfo jobInfo) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        jobInfo.setUserId(Integer.valueOf(userId));
        jobInfo.setJobId(JOB_ID.getAndIncrement());
        final String key = jobInfo.getUserId() + "-" + jobInfo.getCaseId() + "-" + jobInfo.getDeviceIp();
        jobLaunchService.addJob(jobInfo, key);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestJobListInfo retrieveRunningJobs() {
        return jobLaunchService.getJobs();
    }

    @POST
    @Path("launch")
    public void launchJob() throws ExecutionException, InterruptedException {
        jobLaunchService.launch();
    }
}