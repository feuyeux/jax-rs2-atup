package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.constant.AtupVariable;
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
import java.util.HashMap;
import java.util.PriorityQueue;

@Service
public class JobLaunchService {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(JobLaunchService.class.getName());
    private final PriorityQueue<AtupTestJobInfo> jobQueue;
    private final HashMap<String, Short> lockMap = new HashMap<>();
    private static final String SEG = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String IP_PATTERN = "^" + SEG + "\\." + SEG + "\\." + SEG + "\\." + SEG + "$";

    @Autowired
    AtupDeviceDao deviceDao;
    @Autowired
    AtupTestResultDao resultDao;
    @Autowired
    AtupTestCaseDao testCaseDao;
    @Autowired
    AtupUserDao userDao;

    public JobLaunchService() {
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

    public boolean launch() {
        final AtupTestJobInfo jobInfo;
        final String deviceIp;
        synchronized (jobQueue) {
            jobInfo = jobQueue.poll();
            deviceIp = jobInfo.getDeviceIp();
            if (deviceIp == null) {
                return false;
            }
            if (null == lockMap.get(deviceIp)) {
                lockMap.put(deviceIp, new Short("1"));
            }
        }
        if (jobInfo != null) {
            synchronized (lockMap.get(deviceIp)) {
                if (deviceIp.matches(IP_PATTERN) || deviceIp.toUpperCase().equals("LOCALHOST")) {
                         /*1.fetch device from DB*/
                    final AtupDevice testDevice = fetchDeviceFromDB(deviceIp);
                    Integer deviceStatus = testDevice.getDeviceStatus();
                    log.debug("**** 1 fetch-Device-FromDB **** " + jobInfo + " deviceStatus=" + deviceStatus);
                    if (AtupParam.DEVICE_IDLE.equals(deviceStatus)) {
                            /*2. fetch test case from DB*/
                        log.debug("**** 2 fetch-TestCase-FromDB **** " + jobInfo);
                        final AtupTestCase testCase = fetchTestCaseFromDB(jobInfo.getCaseId());
                            /*3. launch test case and get the asynchronous result, then save it*/
                        log.debug("**** 3 launchTest **** " + jobInfo);
                        launchTest(jobInfo.getUserId(), testDevice, testCase);
                        return true;
                    }
                }
            }
        }
        if (jobInfo != null) {
            log.debug("**** 5 offer queue> " + jobInfo);
            synchronized (jobQueue) {
                jobQueue.offer(jobInfo);
            }
            return false;
        } else {
            return true;
        }
    }

    Integer fetchDeviceStatus(String deviceIp) {
        final String detectPath = AtupApi.PROTOCOL + deviceIp + ":" + AtupApi.SERVICE_PORT + AtupApi.SERVICE_PATH;
        final AtupRequest<String, Integer> request = new AtupRequest<>();
        request.timeout(AtupVariable.DETECT_CONNECT_TIMEOUT, 0);
        try {
            return request.rest(AtupRequest.GET, detectPath, Integer.class);
        } catch (final Exception e) {
            log.error(e);
            return AtupParam.DEVICE_ERROR;
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
        final AtupRequest<AtupTestCase, AtupTestResult> request = new AtupRequest<>();
        request.setAsync(true);
        return request.rest(AtupRequest.POST, launchPath, MediaType.APPLICATION_JSON_TYPE, testCase, AtupTestResult.class);
    }

    private void addTestResultToDB(Integer userId, AtupTestResult testResult) {
        final AtupUser user = userDao.findById(userId);
        testResult.setUser(user);
        resultDao.save(testResult);
    }
}
