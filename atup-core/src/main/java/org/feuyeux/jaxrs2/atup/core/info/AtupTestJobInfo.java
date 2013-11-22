package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * ATUP Test Job Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 *        09/09/2013
 */

@XmlRootElement
public class AtupTestJobInfo {

    public static final Integer HIGH = 3;
    public static final Integer MEDIUM = 2;
    public static final Integer LOW = 1;
    private Integer jobId;
    private Integer userId;
    private Integer deviceId;
    private Integer caseId;
    private Integer suiteId;
    private Integer priority;

    public AtupTestJobInfo() {
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(Integer suiteId) {
        this.suiteId = suiteId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
