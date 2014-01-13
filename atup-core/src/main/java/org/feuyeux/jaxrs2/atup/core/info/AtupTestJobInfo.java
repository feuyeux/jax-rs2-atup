package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * ATUP Test Job Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */

@XmlRootElement
public class AtupTestJobInfo implements Comparable<AtupTestJobInfo> {
    private Integer jobId;
    private Integer userId;
    private String deviceIp;
    private Integer caseId;
    private Integer priority;
    private Date generatedTime;

    private AtupTestJobInfo() {
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(final Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(final Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(final Integer priority) {
        this.priority = priority;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(final String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public Date getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(Date generatedTime) {
        this.generatedTime = generatedTime;
    }

    @Override
    public String toString() {
        return "jobId=" + jobId + ",userId=" + userId + ",deviceIp=" + deviceIp + ",caseId=" + caseId + ",priority=" + priority;
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
            return other.jobId.equals(this.jobId) && other.userId.equals(this.userId);
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
