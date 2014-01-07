package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AtupTestCaseService {
    @Autowired
    org.feuyeux.jaxrs2.atup.core.dao.AtupTestCaseDao dao;
    @Autowired
    org.feuyeux.jaxrs2.atup.core.dao.AtupTestSuiteDao suiteDao;

    public boolean deleteTestCase(final Integer testCaseId) {
        return dao.remove(testCaseId);
    }

    public AtupTestCase createTestCase(final AtupTestCase testCase) {
        return dao.save(testCase);
    }

    public AtupTestCase updateTestCase(final AtupTestCase testCase) {
        return dao.update(testCase);
    }

    public AtupTestCaseListInfo getCases(final Integer start, final Integer size) {
        AtupTestCaseListInfo result;
        final List<AtupTestCase> list = dao.findAll(start, size);
        if (list != null && list.size() > 0) {
            result = new AtupTestCaseListInfo(list);
        } else {
            result = new AtupTestCaseListInfo();
        }
        return result;
    }

    public AtupTestCase createSuite(final AtupTestCase testCase) {
        final AtupTestSuite editingTestSuite = suiteDao.findById(testCase.getSuite().getSuiteId());
        testCase.setSuite(editingTestSuite);
        final Date time = Calendar.getInstance().getTime();
        testCase.setCreateTime(time);
        testCase.setUpdateTime(time);
        return dao.save(testCase);
    }

    public AtupTestCase getCaseByName(final String caseName) {
        return dao.findByName(caseName);
    }

    public AtupTestCase updateSuite(final AtupTestCase testCase) {
        final AtupTestSuite editingTestSuite = suiteDao.findById(testCase.getSuite().getSuiteId());
        testCase.setSuite(editingTestSuite);
        return dao.update(testCase);
    }
}
