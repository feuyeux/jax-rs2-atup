package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.cases.runner.LaunchTestRunner;
import org.feuyeux.jaxrs2.atup.cases.service.JobLaunchService;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.constant.AtupVariable;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobListInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Path(AtupApi.TEST_JOB_PATH)
public class AtupTestJobResource {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AtupTestJobResource.class.getName());
    private static final AtomicInteger JOB_ID = new AtomicInteger();

    @Autowired
    private JobLaunchService jobLaunchService;

    public AtupTestJobResource() {

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AtupTestJobListInfo retrieveRunningJobs() {
        return jobLaunchService.getJobs();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AtupTestJobInfo createJob(final AtupTestJobInfo jobInfo) {
        jobInfo.setJobId(JOB_ID.getAndIncrement());
        return jobLaunchService.addJob(jobInfo);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeJob(@Context final HttpHeaders headers, final AtupTestJobInfo jobInfo) {
        final String userId = headers.getRequestHeader("Atup-User").get(0);
        final String userRole = headers.getRequestHeader("Atup-UserRole").get(0);
        boolean isJobKiller = userRole.equals(AtupParam.USER_JOB_KILLER);
        if (isJobKiller || userId.equals(jobInfo.getUserId())) {
            jobLaunchService.removeJob(jobInfo);
        } else {
            final AtupUserInfo result = new AtupUserInfo("No permission for this request.", AtupErrorCode.FORBIDDEN_ERROR);
            Response.noContent().status(Response.Status.INTERNAL_SERVER_ERROR).entity(result).build();
        }
    }

    @POST
    @Path("launch")
    public void launchJob(@Suspended final AsyncResponse asyncResponse) throws ExecutionException, InterruptedException {
        final int concurrentNumber = 10;
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < concurrentNumber; i++) {
            Callable<String> launchTask = new LaunchTestRunner(jobLaunchService);
            tasks.add(launchTask);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(concurrentNumber);
        List<Future<String>> resultFutures = executorService.invokeAll(tasks);
        StringBuilder launchResult = new StringBuilder();
        try {
            for (int i = 0; i < concurrentNumber; i++) {
                launchResult.append(resultFutures.get(i).get());
            }
            asyncResponse.resume(launchResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            log.error(e.getMessage());
        }
    }

    private void configResponse(final AsyncResponse asyncResponse) {
        asyncResponse.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable throwable) {
                if (throwable == null) {
                    log.info("CompletionCallback-onComplete: OK");
                } else {
                    log.info("CompletionCallback-onComplete: ERROR: " + throwable.getMessage());
                }
            }
        });

        asyncResponse.register(new ConnectionCallback() {
            @Override
            public void onDisconnect(AsyncResponse disconnected) {
                //Status.GONE=410
                log.info("ConnectionCallback-onDisconnect");
                disconnected.resume(Response.status(Response.Status.GONE).entity("disconnect!").build());
            }
        });

        asyncResponse.setTimeoutHandler(new TimeoutHandler() {
            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //Status.SERVICE_UNAVAILABLE=503
                log.info("TIMEOUT");
                asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("Operation time out.").build());
            }
        });
        asyncResponse.setTimeout(AtupVariable.ASYNC_LAUNCH_TEST_TIMEOUT, TimeUnit.SECONDS);
    }
}