package org.feuyeux.jaxrs2.atup.cases.runner;

import org.feuyeux.jaxrs2.atup.cases.service.JobLaunchService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;

import java.util.concurrent.Callable;

/**
 * Created by hanl on 12/18/13.
 */
public class LaunchTestRunner implements Callable<java.lang.String> {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LaunchTestRunner.class.getName());
    private JobLaunchService service;

    public LaunchTestRunner(JobLaunchService service) {
        this.service = service;
    }

    @Override
    public String call() throws Exception {
        try {
            service.launch(AtupParam.HIGH);
            service.launch(AtupParam.MEDIUM);
            service.launch(AtupParam.LOW);
            return "DONE";
        } catch (final Exception e) {
            log.error(e);
            return "FAILED";
        }
    }
}
