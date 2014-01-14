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
import java.util.Arrays;
import java.util.Date;
import java.util.PriorityQueue;

@Service
public class JobLaunchService {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(JobLaunchService.class.getName());
    private final PriorityQueue<AtupTestJobInfo> jobQueue;
    private static final String SEG = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IP_PATTERN = "^" + SEG + "\\." + SEG + "\\." + SEG + "\\." + SEG + "$";

    //private final ScheduledExecutorService launchTask;

    @Autowired
    AtupDeviceDao deviceDao;
    @Autowired
    AtupTestResultDao resultDao;
    @Autowired
    AtupTestCaseDao testCaseDao;
    @Autowired
    AtupUserDao userDao;

    public JobLaunchService() {
        //launchTask = new ScheduledThreadPoolExecutor(1);
        jobQueue = new PriorityQueue<>();
    }

    public synchronized AtupTestJobInfo addJob(final AtupTestJobInfo jobInfo) {
        if (jobQueue.equals(jobInfo))
            return null;
        if (jobQueue.add(jobInfo)) {
            return jobInfo;
        } else {
            return null;
        }
    }

    public synchronized void removeJob(final AtupTestJobInfo jobInfo) {
        jobQueue.remove(jobInfo);
    }

    public AtupTestJobListInfo getJobs() {
        AtupTestJobInfo[] jobs = jobQueue.toArray(new AtupTestJobInfo[]{});
        if (jobs.length > 0) {
            Arrays.sort(jobs);
        }
        return new AtupTestJobListInfo(jobs);
    }

//    public void launch() throws InterruptedException, ExecutionException {
//        launchTask.scheduleWithFixedDelay(new Runnable() {
//        }, 0, 10, TimeUnit.SECONDS);
//    }

    public void launch() {
        final AtupTestJobInfo jobInfo = jobQueue.peek();
        if (jobInfo != null) {
            final String deviceIp = jobInfo.getDeviceIp();
            if (deviceIp != null && deviceIp.matches(IP_PATTERN)) {
                final AtupDevice testDevice = deviceDao.findByIp(deviceIp);
                final Integer deviceStatus = testDevice.getDeviceStatus();
                log.info("Device[" + deviceIp + "] status = " + deviceStatus);
                if (deviceStatus.equals(AtupParam.DEVICE_IDLE)) {
                    final String launchPath = AtupApi.PROTOCOL + deviceIp + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
                    final AtupTestCase testCase = testCaseDao.findById(jobInfo.getCaseId());
                    jobQueue.poll();
                    final Integer status = restPost(launchPath, testCase);
                    final AtupUser user = userDao.findById(jobInfo.getUserId());
                    final Date createTime = new Date();
                    final AtupTestResult testResult = new AtupTestResult(testCase, user, testDevice, status, "", createTime, createTime);
                    resultDao.save(testResult);
                }
            }
        }
    }

    private Integer restPost(String launchPath, AtupTestCase testCase) {
        final AtupRequest<AtupTestCase, Integer> request = new AtupRequest<>();
        return request.rest(AtupRequest.POST, launchPath, MediaType.APPLICATION_JSON_TYPE, testCase, Integer.class);
    }
}
