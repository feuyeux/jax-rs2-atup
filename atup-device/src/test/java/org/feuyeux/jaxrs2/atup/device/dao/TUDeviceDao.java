package org.feuyeux.jaxrs2.atup.device.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUDeviceDao {
    private final static Logger LOGGER = Logger.getLogger(TUDeviceDao.class);
    @Autowired
    AtupDeviceDao deviceDao;

    @Test
    public void testFindAll() {
        LOGGER.info(">>Test find all device list");
        deviceDao.findAll();
        LOGGER.info("<<Test find all device list");
    }
}
