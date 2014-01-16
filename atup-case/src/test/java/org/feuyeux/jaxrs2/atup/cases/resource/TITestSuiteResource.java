package org.feuyeux.jaxrs2.atup.cases.resource;

import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteListInfo;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TITestSuiteResource extends JerseyTest {
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(AtupTestSuiteResource.class);
    }

    @Test
    public void testGetList() {
        final WebTarget webTarget = target(AtupApi.TEST_RESULT_PATH).path("results").queryParam("start", 0).queryParam("size", 100);
        final Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON_TYPE);
        final AtupTestSuiteListInfo result = request.get(AtupTestSuiteListInfo.class);
        final List<AtupTestSuiteInfo> testSuiteList = result.getSuiteList();
        if (testSuiteList != null && testSuiteList.size() > 0) {
            Assert.assertNotNull(testSuiteList.get(0).getSuiteId());
        } else {
            Assert.assertTrue(testSuiteList == null || result.getErrorInfo() != null);
        }
    }
}
