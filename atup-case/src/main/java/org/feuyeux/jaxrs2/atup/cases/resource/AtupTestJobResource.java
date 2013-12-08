package org.feuyeux.jaxrs2.atup.cases.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;

@Singleton
@Path(AtupApi.TEST_JOB_PATH)
public class AtupTestJobResource {
    private static final Logger LOGGER = Logger.getLogger(AtupTestJobResource.class);
    private final AtomicInteger JOB_ID = new AtomicInteger();

    public AtupTestJobResource() {

    }

    private final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> mediumJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> lowJobMap = new ConcurrentHashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createJob(@Context final HttpHeaders headers, final AtupTestJobInfo jobInfo) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        jobInfo.setUserId(Integer.valueOf(userId));
        jobInfo.setJobId(JOB_ID.getAndIncrement());
        final String key = jobInfo.getUserId() + "-" + jobInfo.getCaseId() + "-" + jobInfo.getDeviceId();
        switch (jobInfo.getPriority()) {
            case 3:
                highJobMap.put(key, jobInfo);
                AtupTestJobResource.LOGGER.info("New High priority Job joint");
                break;
            case 2:
                mediumJobMap.put(key, jobInfo);
                AtupTestJobResource.LOGGER.info("New Medium priority Job joint");
                break;
            case 1:
                lowJobMap.put(key, jobInfo);
                AtupTestJobResource.LOGGER.info("New Low priority Job joint");
                break;
            default:
                break;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestJobListInfo retrieveRunningJobs() {
        final List<AtupTestJobInfo> jobList = new ArrayList<>();
        jobList.addAll(highJobMap.values());
        jobList.addAll(mediumJobMap.values());
        jobList.addAll(lowJobMap.values());
        return new AtupTestJobListInfo(jobList);
    }
}
