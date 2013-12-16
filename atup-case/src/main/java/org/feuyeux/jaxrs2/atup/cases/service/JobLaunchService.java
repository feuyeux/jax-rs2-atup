package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service()
public class JobLaunchService {
    private final org.apache.logging.log4j.Logger log =
            org.apache.logging.log4j.LogManager.getLogger(JobLaunchService.class.getName());
    private final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> mediumJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> lowJobMap = new ConcurrentHashMap<>();

    ScheduledExecutorService launchTask = new ScheduledThreadPoolExecutor(1);

    public void addJob(AtupTestJobInfo jobInfo, String key) {
        switch (jobInfo.getPriority()) {
            case 3:
                highJobMap.put(key, jobInfo);
                log.info("New High priority Job joint: " + jobInfo);
                break;
            case 2:
                mediumJobMap.put(key, jobInfo);
                log.info("New Medium priority Job joint: " + jobInfo);
                break;
            case 1:
                lowJobMap.put(key, jobInfo);
                log.info("New Low priority Job joint: " + jobInfo);
                break;
            default:
                break;
        }
    }

    public AtupTestJobListInfo getJobs() {
        final List<AtupTestJobInfo> jobList = getJobList();
        return new AtupTestJobListInfo(jobList);
    }

    private List<AtupTestJobInfo> getJobList() {
        final List<AtupTestJobInfo> jobList = new ArrayList<>();
        jobList.addAll(highJobMap.values());
        jobList.addAll(mediumJobMap.values());
        jobList.addAll(lowJobMap.values());
        return jobList;
    }

    public void launch() throws InterruptedException, ExecutionException {
        launchTask.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    detecting();
                } catch (Exception e) {
                    log.error(e);
                }
                //log.debug("test");
            }

            private void detecting() {
                List<AtupTestJobInfo> jobList = getJobList();
                for (AtupTestJobInfo jobInfo : jobList) {
                    jobInfo.getDeviceId();
                    try {

                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}
