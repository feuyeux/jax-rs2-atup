package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.constant.AtupParam;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestCaseDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupTestSuiteDao;
import org.feuyeux.jaxrs2.atup.core.dao.AtupUserDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.fake.CreatTestSuite;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestCase;
import org.feuyeux.jaxrs2.atup.core.fake.CreateTestResult;
import org.feuyeux.jaxrs2.atup.core.fake.CreateUser;
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
public class TUTestResultDao {
    public static final String CASE_NAME = "TEST2014" + System.nanoTime();
    @Autowired
    AtupTestResultDao dao;
    @Autowired
    AtupTestSuiteDao sdao;
    @Autowired
    AtupTestCaseDao cdao;
    @Autowired
    AtupUserDao udao;

    static AtupTestSuite suite;
    static AtupTestCase one;
    static AtupUser user;
    static boolean saved = false;

    @Before
    public void tearUp() {
        if (!saved) {
            user = CreateUser.buildUser();
            udao.save(user);
            String suiteName = "Test_Suite_N" + System.nanoTime();
            suite = CreatTestSuite.buildOneTestSuite(suiteName);
            sdao.save(suite);
            one = CreateTestCase.buildOneTestCase(suite, CASE_NAME);
            cdao.save(one);
            saved = true;
        }
    }

    @Test
    public void testCreateResult() {
        final AtupTestResult entity = CreateTestResult.buildTestResult(one, user);
        final AtupTestResult savedEntity = dao.save(entity);
        Assert.assertEquals(CASE_NAME, savedEntity.getTestCase().getCaseName());
        final boolean deleted = dao.remove(savedEntity.getResultId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testUpdateTestResult() {
        final AtupTestResult entity = CreateTestResult.buildTestResult(one, user);
        final AtupTestResult savedEntity = dao.save(entity);
        savedEntity.setResultStatus(AtupParam.RESULT_FAILED);
        final AtupTestResult updatedEntity = dao.update(savedEntity);
        Assert.assertEquals(AtupParam.RESULT_FAILED, updatedEntity.getResultStatus());
        final boolean deleted = dao.remove(updatedEntity.getResultId());
        Assert.assertEquals(true, deleted);
    }

    @Test
    public void testFindByStatus() {
        final List<AtupTestResult> resultList = dao.findByStatus(AtupParam.RESULT_SUCCESS);
        checkResult(resultList);
    }

    private void checkResult(final List<AtupTestResult> resultList) {
        if (!resultList.isEmpty()) {
            Assert.assertEquals(AtupParam.RESULT_SUCCESS, resultList.get(0).getResultStatus());
        }
    }

    @Test
    public void testFindByTestResult() {
        final AtupTestSuite suite = CreatTestSuite.buildTestSuite();
        final List<AtupTestResult> resultList = dao.findByTestCase(CreateTestCase.buildTestCase(suite));
        checkResult(resultList);
    }

    @Test
    public void testFindByUser() {
        final List<AtupTestResult> resultList = dao.findByUser(CreateUser.buildUser().getUserId(), 0, 100);
        checkResult(resultList);
    }
}