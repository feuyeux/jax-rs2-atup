package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupDeviceDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestCaseDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupUserDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class JobLaunchService {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(JobLaunchService.class.getName());
    private final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> mediumJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> lowJobMap = new ConcurrentHashMap<>();
    private static final String SEG = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IP_PATTERN = "^" + SEG + "\\." + SEG + "\\." + SEG + "\\." + SEG + "$";

    final ScheduledExecutorService launchTask;

    @Autowired
    AtupDeviceDao deviceDao;
    @Autowired
    AtupTestResultDao resultDao;
    @Autowired
    AtupTestCaseDao testCaseDao;
    @Autowired
    AtupUserDao userDao;

    public JobLaunchService() {
        launchTask = new ScheduledThreadPoolExecutor(1);
    }

    public AtupTestJobInfo addJob(final String key, final AtupTestJobInfo jobInfo) {
        if (contains(key))
            return null;
        switch (jobInfo.getPriority()) {
            case 3:
                log.info("New High priority Job joint: " + jobInfo);
                return highJobMap.put(key, jobInfo);

            case 2:
                log.info("New Medium priority Job joint: " + jobInfo);
                return mediumJobMap.put(key, jobInfo);

            case 1:
                log.info("New Low priority Job joint: " + jobInfo);
                return lowJobMap.put(key, jobInfo);
        }
        return null;
    }

    public void removeJob(final Integer jobId) {
        remove(highJobMap, jobId);
        remove(mediumJobMap, jobId);
        remove(lowJobMap, jobId);
    }

    boolean contains(String key) {
        if (highJobMap.get(key) != null) {
            return true;
        }
        if (mediumJobMap.get(key) != null) {
            return true;
        }
        if (lowJobMap.get(key) != null) {
            return true;
        }
        return false;
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

//    public void launch() throws InterruptedException, ExecutionException {
//        launchTask.scheduleWithFixedDelay(new Runnable() {
//        }, 0, 10, TimeUnit.SECONDS);
//    }

    public void launch(Integer level) {
        switch (level) {
            case 3:
                launch(highJobMap);
                break;
            case 2:
                launch(mediumJobMap);
                break;
            case 1:
                launch(lowJobMap);
                break;
        }
    }

    private void launch(final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap) {
        final Iterator<Map.Entry<String, AtupTestJobInfo>> highIterator = highJobMap.entrySet().iterator();
        while (highIterator.hasNext()) {
            final Map.Entry<String, AtupTestJobInfo> currentJobKV = highIterator.next();
            try {
                final AtupTestJobInfo jobInfo = highJobMap.remove(currentJobKV.getKey());
                if (jobInfo != null) {
                    final String deviceIp = jobInfo.getDeviceIp();
                    if (deviceIp != null && deviceIp.matches(IP_PATTERN)) {
                        final AtupDevice testDevice = deviceDao.findByIp(deviceIp);
                        final Integer deviceStatus = testDevice.getDeviceStatus();
                        log.info("Device[" + deviceIp + "] status = " + deviceStatus);
                        if (deviceStatus.equals(AtupParam.DEVICE_IDLE)) {
                            final String launchPath = AtupApi.PROTOCOL + deviceIp + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
                            final AtupTestCase testCase = testCaseDao.findById(jobInfo.getCaseId());
                            final Integer status = restPost(launchPath, testCase);
                            final AtupUser user = userDao.findById(jobInfo.getUserId());
                            final Date createTime = new Date();
                            final AtupTestResult testResult = new AtupTestResult(testCase, user, testDevice, status, "", createTime, createTime);
                            resultDao.save(testResult);
                        }
                    }
                }
            } catch (final Exception e) {
                log.error(e);
                highJobMap.put(currentJobKV.getKey(), currentJobKV.getValue());
            }
        }
    }

    private Integer restPost(String launchPath, AtupTestCase testCase) {
        final AtupRequest<AtupTestCase, Integer> request = new AtupRequest<>();
        return request.rest(AtupRequest.POST, launchPath, null, null, MediaType.APPLICATION_JSON_TYPE, testCase, Integer.class);
    }

    private void remove(final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap, Integer jobId) {
        final Iterator<Map.Entry<String, AtupTestJobInfo>> highIterator = highJobMap.entrySet().iterator();
        while (highIterator.hasNext()) {
            final Map.Entry<String, AtupTestJobInfo> currentJobKV = highIterator.next();
            if (currentJobKV.getValue().getJobId().equals(jobId)) {
                highJobMap.remove(currentJobKV.getKey());
                break;
            }
        }
    }
}
