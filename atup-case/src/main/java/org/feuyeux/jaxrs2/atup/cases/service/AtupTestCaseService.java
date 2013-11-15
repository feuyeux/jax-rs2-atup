package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestCaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}