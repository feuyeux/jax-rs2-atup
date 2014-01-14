package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestResultDao;
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
        final AtupTestResult entity = CreateTestResult.buildTestResult();
        final AtupTestResult savedEntity = dao.save(entity);
        Assert.assertEquals(CreateTestCase.CASE_NAME, savedEntity.getTestCase().getCaseName());
    }

    @Test
    public void testUpdateTestCase() {
        final AtupTestResult entity = CreateTestResult.buildTestResult();
        final AtupTestResult savedEntity = dao.save(entity);
        savedEntity.setResultStatus(AtupParam.RESULT_FAILED);
        final AtupTestResult updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.RESULT_FAILED, updatedEntity.getResultStatus());
    }

    @Test
    public void testDeleteTestCase() {
        final AtupTestResult entity = CreateTestResult.buildTestResult();
        final AtupTestResult savedEntity = dao.save(entity);
        final boolean deleted = dao.remove(savedEntity.getResultId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testFindByStatus() {
        final List<AtupTestResult> caseList = dao.findByStatus(AtupParam.RESULT_SUCCESS);
        checkResult(caseList);
    }

    private void checkResult(final List<AtupTestResult> caseList) {
        if (!caseList.isEmpty()) {
            Assert.assertEquals(AtupParam.RESULT_SUCCESS, caseList.get(0).getResultStatus());
        }
    }

    @Test
    public void testFindByTestCase() {
        final List<AtupTestResult> caseList = dao.findByTestCase(CreateTestCase.buildTestCase());
        checkResult(caseList);
    }

    @Test
    public void testFindByUser() {
        final List<AtupTestResult> caseList = dao.findByUser(CreateUser.buildUser().getUserId(), 0, 100);
        checkResult(caseList);
    }
}
