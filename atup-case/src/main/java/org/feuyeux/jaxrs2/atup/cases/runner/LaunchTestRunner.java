package org.feuyeux.jaxrs2.atup.cases.runner;

import org.feuyeux.jaxrs2.atup.cases.service.JobLaunchService;

import java.util.concurrent.Callable;

public class LaunchTestRunner implements Callable<java.lang.String> {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LaunchTestRunner.class.getName());
    private final JobLaunchService service;

    public LaunchTestRunner(JobLaunchService service) {
        this.service = service;
    }

    @Override
    public String call() throws Exception {
        try {
            service.launch();
            return "DONE";
        } catch (final Exception e) {
            log.error(e);
            return "FAILED";
        }
    }
}
