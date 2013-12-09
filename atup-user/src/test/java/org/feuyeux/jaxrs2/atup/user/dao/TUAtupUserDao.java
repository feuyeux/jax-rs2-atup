package org.feuyeux.jaxrs2.atup.user.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.fake.CreateUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:applicationContext0.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TUAtupUserDao {
    private final Logger log = LogManager.getLogger(TUAtupUserDao.class.getName());

    @Autowired
    private AtupUserDao dao;

    @Test
    public void testCreateUser() {
        AtupUser user = CreateUser.buildUser();
        AtupUser newUser = dao.save(user);
        log.info(newUser);
        Assert.assertEquals(user.getUserName(), newUser.getUserName());
    }

    @Test
    public void testUpdateAndDeleteUser() {
        AtupUser user = CreateUser.buildUser();
        AtupUser newUser = dao.save(user);
        newUser.setUserName(CreateUser.TEST_NAME1);
        AtupUser updatedUser = dao.update(newUser);
        Assert.assertEquals(CreateUser.TEST_NAME1, updatedUser.getUserName());

        boolean deleted = dao.remove(updatedUser.getUserId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testFindById() {
        AtupUser user = CreateUser.buildUser();
        AtupUser newUser = dao.save(user);
        AtupUser findUser = dao.findById(newUser.getUserId());
        Assert.assertEquals(user.getUserName(), findUser.getUserName());
    }

    @Test
    public void testFindByName() {
        AtupUser user = CreateUser.buildUser();
        AtupUser newUser = dao.save(user);
        log.info(newUser);
        AtupUser findUser = dao.findByName(newUser.getUserName());
        Assert.assertEquals(user.getUserName(), findUser.getUserName());
    }
}
