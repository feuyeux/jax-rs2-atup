package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestJobListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestJobInfo> jobList;

    public AtupTestJobListInfo() {
    }

    public AtupTestJobListInfo(final List<AtupTestJobInfo> jobList) {
        this.jobList = jobList;
    }

    public List<AtupTestJobInfo> getJobList() {
        return jobList;
    }

    public void setJobList(final List<AtupTestJobInfo> jobList) {
        this.jobList = jobList;
    }
}
