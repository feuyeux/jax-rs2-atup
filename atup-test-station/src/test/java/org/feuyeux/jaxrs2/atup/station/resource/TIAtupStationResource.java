package org.feuyeux.jaxrs2.atup.station.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TIAtupStationResource extends JerseyTest {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TIAtupStationResource.class.getName());
    private final String resourcePath = "http://localhost:9998/station/";

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceConfig cfg = new ResourceConfig();
        cfg.packages("org.feuyeux.jaxrs2.atup.station.resource");
        return cfg;
    }

    @Test
    public void testKeepAlive() {
        final AtupRequest<AtupTestCase, Integer> request = new AtupRequest<>();
        final Integer result = request.rest(AtupRequest.GET, resourcePath, Integer.class);
        log.debug("testKeepAlive:" + result);
    }

    @Test
    public void testLaunch() {
        //http://localhost:9998/station/
        AtupTestCase testCase = new AtupTestCase();
        final Entity<AtupTestCase> e = Entity.entity(testCase, MediaType.APPLICATION_JSON_TYPE);
        final Response response = target().path(AtupApi.TEST_STATION_PATH).request().post(e);
        final AtupTestResult result = response.readEntity(AtupTestResult.class);
        log.debug("testLaunch:" + result);
    }

    @Test
    public void testLaunch2() {
        AtupTestCase testCase = new AtupTestCase();
        final AtupRequest<AtupTestCase, AtupTestResult> request = new AtupRequest<>();
        final AtupTestResult result = request.rest(AtupRequest.POST, resourcePath, null, null, MediaType.APPLICATION_JSON_TYPE, testCase, AtupTestResult.class);
        log.debug("testLaunch2:" + result);
    }

    @Test
    public void testDeviceStatus() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        ExecutorService executorService2 = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    testKeepAlive();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        executorService2.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    testLaunch2();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread.sleep(120000);
    }
}
