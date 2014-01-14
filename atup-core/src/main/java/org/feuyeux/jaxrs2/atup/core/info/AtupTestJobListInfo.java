package org.feuyeux.jaxrs2.atup.core.info;

public class AtupTestJobListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private AtupTestJobInfo[] jobs;

    public AtupTestJobListInfo() {
    }

    public AtupTestJobListInfo(final AtupTestJobInfo[] jobs) {
        this.jobs = jobs;
    }

    public AtupTestJobInfo[] getJobs() {
        return jobs;
    }

    public void setJobs(AtupTestJobInfo[] jobs) {
        this.jobs = jobs;
    }
}
