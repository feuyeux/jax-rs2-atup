package org.feuyeux.jaxrs2.atup.station.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.rest.AtupRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TIAtupStationResource extends JerseyTest {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TIAtupStationResource.class.getName());

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        ResourceConfig cfg = new ResourceConfig();
        cfg.packages("org.feuyeux.jaxrs2.atup.station.resource");
        return cfg;
    }

    @Test
    public void testLaunch() {
        //http://localhost:9998/station/
        AtupTestCase testCase = new AtupTestCase();
        final Entity<AtupTestCase> e = Entity.entity(testCase, MediaType.APPLICATION_JSON_TYPE);
        final Response response = target().path(AtupApi.TEST_STATION_PATH).request().post(e);
        final Integer result = response.readEntity(Integer.class);
        log.debug(result);
    }

    @Test
    public void testLaunch2() {
        AtupTestCase testCase = new AtupTestCase();
        final AtupRequest<AtupTestCase, Integer> request = new AtupRequest<>();
        String launchPath = "http://localhost:9998/station/";
        final Integer result = request.rest(AtupRequest.POST, launchPath, null, null, MediaType.APPLICATION_JSON_TYPE, testCase, Integer.class);
        log.debug(result);
    }
}
