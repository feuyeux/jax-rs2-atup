package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ATUP Test Job Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */

@XmlRootElement
public class AtupTestJobInfo extends AtupInfo implements Comparable<AtupTestJobInfo> {
    private Integer jobId;
    private Integer userId;
    private String deviceIp;
    private Integer caseId;
    private Integer priority;
    private Long generatedTime;

    public AtupTestJobInfo() {

    }

    public AtupTestJobInfo(Integer userId, String deviceIp, Integer caseId, Integer priority, Long generatedTime) {
        this.userId = userId;
        this.deviceIp = deviceIp;
        this.caseId = caseId;
        this.priority = priority;
        this.generatedTime = generatedTime;
    }

    @XmlAttribute
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(final Integer jobId) {
        this.jobId = jobId;
    }

    @XmlAttribute
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    @XmlAttribute
    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(final Integer caseId) {
        this.caseId = caseId;
    }

    @XmlAttribute
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer priority) {
        this.priority = priority;
    }

    @XmlAttribute
    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(final String deviceIp) {
        this.deviceIp = deviceIp;
    }

    @XmlAttribute
    public Long getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(Long generatedTime) {
        this.generatedTime = generatedTime;
    }

    @Override
    public String toString() {
        return "job[" + jobId + "] priority:" + priority + ",generatedTime:" + generatedTime + ",userId=" + userId + ",deviceIp=" + deviceIp + ",caseId=" + caseId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass().equals(AtupTestJobInfo.class)) {
            AtupTestJobInfo other = (AtupTestJobInfo) obj;
            return other.deviceIp.equals(this.deviceIp) && other.userId.equals(this.userId) && other.caseId.equals(this.caseId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return jobId.hashCode() + userId.hashCode();
    }

    @Override
    public int compareTo(AtupTestJobInfo other) {
        int result = this.priority.compareTo(other.priority);
        if (result == 0) {
            return this.generatedTime.compareTo(other.generatedTime);
        } else {
            return result;
        }
    }
}
