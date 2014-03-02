package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestCaseDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestSuiteDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.fake.CreatTestSuite;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath:applicationContext2.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TUTestCaseDao {
    @Autowired
    AtupTestSuiteDao sdao;
    @Autowired
    AtupTestCaseDao dao;

    static final AtupTestSuite one = CreatTestSuite.buildOneTestSuite();
    static boolean saved = false;

    @Before
    public void tearUp() {
        if (!saved) {
            sdao.save(one);
            saved=true;
        }
    }

    @Test
    public void testCreateTestCase() {
        String caseName = "TEST35" + System.nanoTime();
        final AtupTestCase entity = CreateTestCase.buildTestCase(one, caseName);
        final AtupTestCase savedEntity = dao.save(entity);
        Assert.assertEquals(caseName, savedEntity.getCaseName());
        final boolean deleted = dao.remove(savedEntity.getCaseId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testUpdateTestCase() {
        String caseName = "TEST35" + System.nanoTime();
        final AtupTestCase entity = CreateTestCase.buildTestCase(one, caseName);
        final AtupTestCase savedEntity = dao.save(entity);
        savedEntity.setCaseStatus(AtupParam.DISABLED_CASE);
        final AtupTestCase updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.DISABLED_CASE, updatedEntity.getCaseStatus());
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
