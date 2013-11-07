package org.feuyeux.jaxrs2.atup.user.dao;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.user.CreateUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUAtupUserDao {
    @Autowired
    private AtupUserDao dao;

    @Test
    public void testCreateUser() {
        AtupUser user = CreateUser.buildUser();
        AtupUser newUser = dao.save(user);
        Assert.assertEquals(user.getUserName(), newUser.getUserName());
    }
}
