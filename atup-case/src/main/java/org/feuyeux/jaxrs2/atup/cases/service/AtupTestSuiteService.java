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

    public AtupTestSuiteListInfo getSuites(Integer start, Integer size) {
        AtupTestSuiteListInfo result = null;
        List<AtupTestSuite> list = dao.findAll(start, size);
        if (list != null && list.size() > 0) {
            List<AtupTestSuiteInfo> suiteInfoList = new ArrayList<>(list.size());
            for (AtupTestSuite testSuite : list) {
                AtupTestSuiteInfo caseInfo = new AtupTestSuiteInfo(testSuite);
                suiteInfoList.add(caseInfo);
            }
            result = new AtupTestSuiteListInfo();
            result.setSuiteList(suiteInfoList);
        } else {
            result = new AtupTestSuiteListInfo();
        }
        return result;
    }

    public AtupTestSuite createSuite(AtupTestSuite testSuite) {
        return dao.save(testSuite);
    }

    public AtupTestSuite updateSuite(AtupTestSuite testSuite) {
        return dao.update(testSuite);
    }
}