package org.feuyeux.jaxrs2.atup.cases.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.feuyeux.jaxrs2.atup.cases.service.JobLaunchService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Singleton
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
        final String key = jobInfo.getUserId() + "-" + jobInfo.getCaseId() + "-" + jobInfo.getDeviceId();
        jobLaunchService.addJob(jobInfo, key);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestJobListInfo retrieveRunningJobs() {
        return jobLaunchService.getJobs();
    }

    @POST
    public void launchJob() throws ExecutionException, InterruptedException {
        jobLaunchService.launch();
    }
}
