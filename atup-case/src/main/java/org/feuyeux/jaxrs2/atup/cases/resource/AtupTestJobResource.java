package org.feuyeux.jaxrs2.atup.cases.resource;

import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;

@Singleton
@Path(AtupApi.TEST_JOB_PATH)
public class AtupTestJobResource {
    private static final Logger LOGGER = Logger.getLogger(AtupTestJobResource.class);

    public AtupTestJobResource() {

    }

    private final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> mediumJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> lowJobMap = new ConcurrentHashMap<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createJob(AtupTestJobInfo jobInfo) {
        String key = jobInfo.getCaseId() + "-" + jobInfo.getDeviceId();
        switch (jobInfo.getPriority()) {
            case 3:
                highJobMap.put(key, jobInfo);
                LOGGER.info("New High priority Job joint");
                break;
            case 2:
                mediumJobMap.put(key, jobInfo);
                LOGGER.info("New Medium priority Job joint");
                break;
            case 1:
                lowJobMap.put(key, jobInfo);
                LOGGER.info("New Low priority Job joint");
                break;
            default:
                break;
        }
    }
}
