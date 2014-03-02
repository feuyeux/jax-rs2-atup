package org.feuyeux.jaxrs2.atup.device.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDeviceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:applicationContext2.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUDeviceDao {
    private final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TUDeviceDao.class.getName());
    @Autowired
    AtupDeviceDao deviceDao;

    @Test
    public void testFindAll() {
        log.info(">>Test find all device list");
        deviceDao.findAll();
        log.info("<<Test find all device list");
    }
}
