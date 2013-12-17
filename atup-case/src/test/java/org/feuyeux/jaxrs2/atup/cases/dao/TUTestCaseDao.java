package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestCaseDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUTestCaseDao {
    @Autowired
    AtupTestCaseDao dao;

    @Test
    public void testCreateTestCase() {
        final AtupTestCase entity = CreateTestCase.buildTestCase();
        final AtupTestCase savedEntity = dao.save(entity);
        Assert.assertEquals(CreateTestCase.CASE_NAME, savedEntity.getCaseName());
    }

    @Test
    public void testUpdateTestCase() {
        final AtupTestCase entity = CreateTestCase.buildTestCase();
        final AtupTestCase savedEntity = dao.save(entity);
        savedEntity.setCaseStatus(AtupParam.DISABLED_CASE);
        final AtupTestCase updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.DISABLED_CASE, updatedEntity.getCaseStatus());
    }

    @Test
    public void testDeleteTestCase() {
        final AtupTestCase entity = CreateTestCase.buildTestCase();
        final AtupTestCase savedEntity = dao.save(entity);
        final boolean deleted = dao.remove(savedEntity.getCaseId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void findByName() {
        final AtupTestCase testCase = dao.findByName(CreateTestCase.CASE_NAME);
        Assert.assertEquals(CreateTestCase.CASE_NAME, testCase.getCaseName());
    }

    @Test
    public void findByStatus() {
        final List<AtupTestCase> caseList = dao.findByStatus(AtupParam.NORMAL_CASE);
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.NORMAL_CASE, caseList.get(0).getCaseStatus());
        }
    }
}
