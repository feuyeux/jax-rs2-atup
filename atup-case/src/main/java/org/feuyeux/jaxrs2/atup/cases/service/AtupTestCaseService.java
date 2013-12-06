package org.feuyeux.jaxrs2.atup.cases.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupTestCaseService {
    @Autowired
    org.feuyeux.jaxrs2.atup.cases.dao.AtupTestCaseDao dao;
    @Autowired
    org.feuyeux.jaxrs2.atup.cases.dao.AtupTestSuiteDao suiteDao;

    public boolean deleteTestCase(Integer testCaseId) {
        return dao.remove(testCaseId);
    }

    public AtupTestCase createTestCase(AtupTestCaseInfo testCaseInfo) {
        AtupTestCase testCase = new AtupTestCase(testCaseInfo);
        return dao.save(testCase);
    }

    public AtupTestCase updateTestCase(AtupTestCaseInfo testCaseInfo) {
        AtupTestCase testCase = new AtupTestCase(testCaseInfo);
        return dao.update(testCase);
    }

    public AtupTestCaseListInfo getCases(Integer start, Integer size) {
        AtupTestCaseListInfo result = null;
        List<AtupTestCase> list = dao.findAll(start, size);
        if (list != null && list.size() > 0) {
            List<AtupTestCaseInfo> caseInfoList = new ArrayList<>(list.size());
            for (AtupTestCase testCase : list) {
                AtupTestCaseInfo caseInfo = new AtupTestCaseInfo(testCase);
                caseInfoList.add(caseInfo);
            }
            result = new AtupTestCaseListInfo(caseInfoList);
        } else {
            result = new AtupTestCaseListInfo();
        }
        return result;
    }

    public AtupTestCase createSuite(AtupTestCase testCase) {
        AtupTestSuite editingTestSuite = suiteDao.findById(testCase.getSuite().getSuiteId());
        testCase.setSuite(editingTestSuite);
        final Date time = Calendar.getInstance().getTime();
        testCase.setCreateTime(time);
        testCase.setUpdateTime(time);
        return dao.save(testCase);
    }

    public AtupTestCase getCaseByName(String caseName) {
        return dao.findByName(caseName);
    }

    public AtupTestCase updateSuite(AtupTestCase testCase) {
        AtupTestSuite editingTestSuite = suiteDao.findById(testCase.getSuite().getSuiteId());
        testCase.setSuite(editingTestSuite);
        return dao.update(testCase);
    }
}
