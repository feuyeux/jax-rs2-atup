package org.feuyeux.jaxrs2.atup.cases.service;

import java.util.ArrayList;
import java.util.List;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestSuiteListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupTestSuiteService {

    @Autowired
    org.feuyeux.jaxrs2.atup.cases.dao.AtupTestSuiteDao dao;

    public AtupTestSuiteListInfo getSuites(final Integer start, final Integer size) {
        AtupTestSuiteListInfo result = null;
        final List<AtupTestSuite> list = dao.findAll(start, size);
        if (list != null && list.size() > 0) {
            final List<AtupTestSuiteInfo> suiteInfoList = new ArrayList<>(list.size());
            for (final AtupTestSuite testSuite : list) {
                final AtupTestSuiteInfo caseInfo = new AtupTestSuiteInfo(testSuite);
                suiteInfoList.add(caseInfo);
            }
            result = new AtupTestSuiteListInfo();
            result.setSuiteList(suiteInfoList);
        } else {
            result = new AtupTestSuiteListInfo();
        }
        return result;
    }

    public AtupTestSuite createSuite(final AtupTestSuite testSuite) {
        return dao.save(testSuite);
    }

    public AtupTestSuite updateSuite(final AtupTestSuite testSuite) {
        return dao.update(testSuite);
    }
}