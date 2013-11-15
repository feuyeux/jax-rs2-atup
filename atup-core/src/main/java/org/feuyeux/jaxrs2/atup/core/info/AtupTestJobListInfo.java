package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestJobListInfo extends AtupInfo {

    private List<AtupTestJobInfo> jobList;

    public List<AtupTestJobInfo> getJobList() {
        return jobList;
    }

    public void setJobList(List<AtupTestJobInfo> jobList) {
        this.jobList = jobList;
    }
}
