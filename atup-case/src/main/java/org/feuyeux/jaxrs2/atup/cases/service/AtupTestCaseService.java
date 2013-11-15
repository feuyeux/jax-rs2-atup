package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AtupTestCaseService {
    @Autowired
    org.feuyeux.jaxrs2.atup.cases.dao.AtupTestCaseDao dao;

    public boolean deleteTestCase(Integer testCaseId) {
        return dao.remove(testCaseId);
    }

    public AtupTestCase createTestCase(AtupTestCaseInfo testCaseInfo) {
        AtupTestCase testCase = new AtupTestCase(testCaseInfo);
        return dao.save(testCase);
    }

    public AtupTestCase updateTestCase(AtupTestCaseInfo testCaseInfo) {
        AtupTestCase testCase = new AtupTestCase(testCaseInfo);
        return dao.store(testCase);
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
        }
        return result;
    }
}
