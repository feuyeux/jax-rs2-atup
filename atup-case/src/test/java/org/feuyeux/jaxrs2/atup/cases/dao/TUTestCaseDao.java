package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
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
        AtupTestCase entity = CreateTestCase.buildTestCase();
        AtupTestCase savedEntity = dao.save(entity);
        Assert.assertEquals(CreateTestCase.CASE_NAME, savedEntity.getCaseName());
    }

    @Test
    public void testUpdateTestCase() {
        AtupTestCase entity = CreateTestCase.buildTestCase();
        AtupTestCase savedEntity = dao.save(entity);
        savedEntity.setCaseStatus(AtupParam.DISABLED_CASE);
        AtupTestCase updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.DISABLED_CASE, updatedEntity.getCaseStatus());
    }

    @Test
    public void testDeleteTestCase() {
        AtupTestCase entity = CreateTestCase.buildTestCase();
        AtupTestCase savedEntity = dao.save(entity);
        boolean deleted = dao.remove(savedEntity.getCaseId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void findByName() {
        List<AtupTestCase> caseList = dao.findByName(CreateTestCase.CASE_NAME);
        if (!caseList.isEmpty()) {
            Assert.assertEquals(CreateTestCase.CASE_NAME, caseList.get(0).getCaseName());
        }
    }

    @Test
    public void findByStatus() {
        List<AtupTestCase> caseList = dao.findByStatus(AtupParam.NORMAL_CASE);
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.NORMAL_CASE, caseList.get(0).getCaseStatus());
        }
    }
}
