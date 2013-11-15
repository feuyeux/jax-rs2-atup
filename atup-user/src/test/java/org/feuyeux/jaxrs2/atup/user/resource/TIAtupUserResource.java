package org.feuyeux.jaxrs2.atup.user.resource;


import org.apache.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.constant.AtupApi;
import org.feuyeux.jaxrs2.atup.core.fake.CreateUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupErrorCode;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TIAtupUserResource extends JerseyTest {
    private static final Logger LOG = Logger.getLogger(TIAtupUserResource.class);

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(AtupUserResource.class);
    }

    @Test
    public void testCreateUser() {
        AtupUserInfo user = CreateUser.buildUserInfo();
        Entity<AtupUserInfo> e = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        Response response = target().path(AtupApi.USER_PATH).request().post(e);
        AtupUserInfo result = response.readEntity(AtupUserInfo.class);
        if (result.getStatusCode() == AtupErrorCode.NONE) {

            Assert.assertEquals(user.getUserName(), result.getUserName());
        } else {
            Assert.fail();
            LOG.info(result.getErrorInfo());
        }
    }
}