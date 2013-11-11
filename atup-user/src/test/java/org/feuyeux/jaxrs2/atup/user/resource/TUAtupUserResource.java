package org.feuyeux.jaxrs2.atup.user.resource;

import org.feuyeux.jaxrs2.atup.core.fake.CreateUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUAtupUserResource {
    @Autowired
    AtupUserResource restApi;

    @Test
    public void testCreateUser() {
        AtupUserInfo user = CreateUser.buildUserInfo();
        Response response = restApi.createUser(user);
        Assert.assertEquals(response.getStatus(), 200);
    }
}
