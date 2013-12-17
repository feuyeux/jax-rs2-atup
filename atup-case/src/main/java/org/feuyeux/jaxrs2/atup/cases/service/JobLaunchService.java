package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.cases.dao.AtupTestCaseDao;
import org.feuyeux.jaxrs2.atup.cases.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.feuyeux.jaxrs2.atup.device.dao.AtupDeviceDao;
import org.feuyeux.jaxrs2.atup.user.dao.AtupUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.concurrent.*;

@Service
public class JobLaunchService {
    private final org.apache.logging.log4j.Logger log =
            org.apache.logging.log4j.LogManager.getLogger(JobLaunchService.class.getName());
    private final ConcurrentHashMap<String, AtupTestJobInfo> highJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> mediumJobMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, AtupTestJobInfo> lowJobMap = new ConcurrentHashMap<>();

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
                    testing(highJobMap);
                    testing(mediumJobMap);
                    testing(lowJobMap);
                } catch (Exception e) {
                    log.error(e);
                }
                //log.debug("test");
            }

            private void testing(ConcurrentHashMap<String, AtupTestJobInfo> highJobMap) {
                Iterator<Map.Entry<String, AtupTestJobInfo>> highIterator = highJobMap.entrySet().iterator();
                while (highIterator.hasNext()) {
                    try {
                        Map.Entry<String, AtupTestJobInfo> currentJobKV = highIterator.next();
                        AtupTestJobInfo jobInfo = currentJobKV.getValue();
                        String deviceIp = jobInfo.getDeviceIp();
                        AtupDevice testDevice = deviceDao.findByIp(deviceIp);
                        Integer deviceStatus = testDevice.getDeviceStatus();
                        log.info("Device[" + deviceIp + "] status = " + deviceStatus);
                        if (deviceStatus.equals(AtupParam.DEVICE_IDLE)) {
                            String launchPath = AtupApi.PROTOCOL + deviceIp + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
                            AtupTestCase testCase = testCaseDao.findById(jobInfo.getCaseId());
                            AtupRequest<AtupTestCase, Integer> request = new AtupRequest<>();
                            Integer status = request.rest(AtupRequest.POST, launchPath, null, null, MediaType.APPLICATION_JSON_TYPE, testCase, Integer.class);
                            AtupUser user = userDao.findById(jobInfo.getUserId());
                            Date createTime = new Date();
                            AtupTestResult testResult = new AtupTestResult(testCase, user, status, "", createTime, createTime);
                            resultDao.save(testResult);
                            highJobMap.remove(currentJobKV.getKey());
                        }
                    } catch (Exception e) {
                        log.error(e);
                    }
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}
