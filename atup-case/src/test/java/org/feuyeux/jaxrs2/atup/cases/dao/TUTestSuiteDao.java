package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestSuiteDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.fake.CreatTestSuite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUTestSuiteDao {
    @Autowired

    AtupTestSuiteDao dao;

    @Test
    public void testCreateTestSuite() {
        final AtupTestSuite entity = CreatTestSuite.buildTestSuite();
        dao.save(entity);
    }

    @Test
    public void testUpdateTestSuite() {
        final AtupTestSuite entity = CreatTestSuite.buildTestSuite();
        final AtupTestSuite savedEntity = dao.save(entity);
        savedEntity.setSuiteType(AtupParam.EDGE_SUITE);
        final AtupTestSuite updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.EDGE_SUITE, updatedEntity.getSuiteType());
    }

    @Test
    public void testDeleteTestSuite() {
        final AtupTestSuite entity = CreatTestSuite.buildTestSuite();
        final AtupTestSuite savedEntity = dao.save(entity);
        final boolean deleted = dao.remove(savedEntity.getSuiteId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testFindByName() {
        final AtupTestSuite suite = dao.findBySuiteName(CreatTestSuite.SUITE_NAME);
        Assert.assertEquals(CreatTestSuite.SUITE_NAME, suite.getSuiteName());
    }

    @Test
    public void testFindByStatus() {
        final List<AtupTestSuite> suiteList = dao.findByStatus(AtupParam.NORMAL_SUITE);
        Assert.assertEquals(AtupParam.NORMAL_SUITE, suiteList.get(0).getSuiteStatus());
    }
}
