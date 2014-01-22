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
        if (jobQueue.contains(jobInfo)) {
            return null;
        }
        if (jobQueue.offer(jobInfo)) {
            return jobInfo;
        } else {
            return null;
        }
    }

    public synchronized void removeJob(final AtupTestJobInfo jobInfo) {
        jobQueue.remove(jobInfo);
    }

    public AtupTestJobListInfo getJobs() {
        AtupTestJobInfo[] jobs = jobQueue.toArray(new AtupTestJobInfo[jobQueue.size()]);
        if (jobs.length > 0) {
            Arrays.sort(jobs);
        }
        return new AtupTestJobListInfo(Arrays.asList(jobs));
    }

//    public void launch() throws InterruptedException, ExecutionException {
//        launchTask.scheduleWithFixedDelay(new Runnable() {
//        }, 0, 10, TimeUnit.SECONDS);
//    }

    public void launch() {
        final AtupTestJobInfo jobInfo;
        synchronized (jobQueue) {
            jobInfo = jobQueue.poll();
        }
        if (jobInfo != null) {
            final String deviceIp = jobInfo.getDeviceIp();
            if (deviceIp != null && (deviceIp.matches(IP_PATTERN) || deviceIp.toUpperCase().equals("LOCALHOST"))) {
                /*1.fetch device from DB*/
                final AtupDevice testDevice = fetchDeviceFromDB(deviceIp);
                if (AtupParam.DEVICE_IDLE.equals(testDevice.getDeviceStatus())) {
                    /*2. fetch test case from DB*/
                    final AtupTestCase testCase = fetchTestCaseFromDB(jobInfo.getCaseId());
                    /*3. launch test case and get the asynchronous result, then save it*/
                    launchTest(jobInfo.getUserId(), testDevice, testCase);
                } else {
                    synchronized (jobQueue) {
                        jobQueue.offer(jobInfo);
                    }
                }
            } else {
                synchronized (jobQueue) {
                    jobQueue.offer(jobInfo);
                }
            }
        }
    }

    private void launchTest(Integer userId, AtupDevice testDevice, AtupTestCase testCase) {
        AtupTestResult testResult = restAsyncPost(testDevice.getDeviceHost(), testCase);
        testResult.setDevice(testDevice);
        addTestResultToDB(userId, testResult);
    }

    private AtupTestCase fetchTestCaseFromDB(Integer testCaseId) {
        AtupTestCase atupTestCase = testCaseDao.findById(testCaseId);
        log.info("fetchTestCaseFromDB :: " + atupTestCase);
        return atupTestCase;
    }

    private AtupDevice fetchDeviceFromDB(String deviceIp) {
        final AtupDevice testDevice = deviceDao.findByIp(deviceIp);
        log.info(String.format("fetchDeviceFromDB :: Device[%s] status = %d", deviceIp, testDevice.getDeviceStatus()));
        return testDevice;
    }

    private AtupTestResult restAsyncPost(String deviceIp, AtupTestCase testCase) {
        final String launchPath = AtupApi.PROTOCOL + deviceIp + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
        final AtupAsyncRequest<AtupTestCase, AtupTestResult> request = new AtupAsyncRequest<>();
        return request.rest(AtupRequest.POST, launchPath, MediaType.APPLICATION_JSON_TYPE, testCase, AtupTestResult.class);
    }

    private void addTestResultToDB(Integer userId, AtupTestResult testResult) {
        final AtupUser user = userDao.findById(userId);
        testResult.setUser(user);
        resultDao.save(testResult);
    }
}
