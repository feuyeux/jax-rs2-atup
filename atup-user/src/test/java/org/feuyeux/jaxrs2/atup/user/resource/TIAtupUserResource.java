package org.feuyeux.jaxrs2.atup.user.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ContextConfiguration(locations = {"classpath:applicationContext.xml.bk"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TIAtupUserResource extends JerseyTest {
    private final Logger log = LogManager.getLogger(TIAtupUserResource.class.getName());

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(AtupUserResource.class);
    }

    @Test
    public void testCreateUser() {
        final AtupUserInfo user = CreateUser.buildUserInfo();
        final Entity<AtupUserInfo> e = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        final Response response = target().path(AtupApi.USER_PATH).request().post(e);
        final AtupUserInfo result = response.readEntity(AtupUserInfo.class);
        if (result.getStatusCode() == AtupErrorCode.NONE) {
            Assert.assertEquals(user.getUserName(), result.getUserName());
        } else {
            Assert.fail();
            log.info(result.getErrorInfo());
        }
    }

    public void testGetUser() {
        String userName = null;
        final AtupUserInfo user = CreateUser.buildUserInfo();
        final Entity<AtupUserInfo> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
        final Invocation.Builder invocationBuilder = target().path(AtupApi.USER_PATH).request();
        final Response response = invocationBuilder.post(userEntity);
        final AtupUserInfo result = response.readEntity(AtupUserInfo.class);
        if (result.getStatusCode() == AtupErrorCode.NONE) {
            userName = result.getUserName();
            Assert.assertEquals(user.getUserName(), result.getUserName());
        } else {
            Assert.fail();
            log.info(result.getErrorInfo());
        }

        if (userName != null) {
            final AtupUserInfo atupUserInfo = target().path(AtupApi.USER_PATH).path(userName).request().get(AtupUserInfo.class);
            Assert.assertEquals(userName, atupUserInfo.getUserName());
        }
    }

}