package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestCase;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestResult;
import org.feuyeux.jaxrs2.atup.core.fake.CreateUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUTestResultDao {
    @Autowired
    AtupTestResultDao dao;

    @Test
    public void testCreateResultCase() {
        AtupTestResult entity = CreateTestResult.buildTestResult();
        AtupTestResult savedEntity = dao.save(entity);
        Assert.assertEquals(CreateTestCase.CASE_NAME, savedEntity.getTestCase().getCaseName());
    }

    @Test
    public void testUpdateTestCase() {
        AtupTestResult entity = CreateTestResult.buildTestResult();
        AtupTestResult savedEntity = dao.save(entity);
        savedEntity.setResultStatus(AtupParam.RESULT_ERROR);
        AtupTestResult updatedEntity = dao.store(savedEntity);
        Assert.assertEquals(AtupParam.RESULT_ERROR, updatedEntity.getResultStatus());
    }

    @Test
    public void testDeleteTestCase() {
        AtupTestResult entity = CreateTestResult.buildTestResult();
        AtupTestResult savedEntity = dao.save(entity);
        boolean deleted = dao.remove(savedEntity.getResultId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testFindByStatus() {
        List<AtupTestResult> caseList = dao.findByStatus(AtupParam.RESULT_OK);
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.RESULT_OK, caseList.get(0).getResultStatus());
        }
    }

    @Test
    public void testFindByTestCase() {
        List<AtupTestResult> caseList = dao.findByTestCase(CreateTestCase.buildTestCase());
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.RESULT_OK, caseList.get(0).getResultStatus());
        }
    }

    @Test
    public void testFindByUser() {
        List<AtupTestResult> caseList = dao.findByUser(CreateUser.buildUser(), 0, 100);
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.RESULT_OK, caseList.get(0).getResultStatus());
        }
    }
}
