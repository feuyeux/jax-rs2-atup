package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestJobListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestJobInfo> jobs;

    public AtupTestJobListInfo() {
    }

    public AtupTestJobListInfo(final List<AtupTestJobInfo> jobs) {
        this.jobs = jobs;
    }

    public List<AtupTestJobInfo> getJobs() {
        return jobs;
    }

    public void setJobs(List<AtupTestJobInfo> jobs) {
        this.jobs = jobs;
    }
}
