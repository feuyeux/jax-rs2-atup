package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.cases.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtupTestResultService {
    @Autowired
    AtupTestResultDao dao;

    public AtupTestResultListInfo getResults(AtupUser user, Integer start, Integer size) {
        AtupTestResultListInfo result = null;
        List<AtupTestResult> list = dao.findByUser(user, start, size);
        if (list != null && list.size() > 0) {
            List<AtupTestResultInfo> caseInfoList = new ArrayList<>(list.size());
            for (AtupTestResult testResult : list) {
                AtupTestResultInfo resultInfo = new AtupTestResultInfo(testResult);
                caseInfoList.add(resultInfo);
            }
            result = new AtupTestResultListInfo();
            result.setResultList(caseInfoList);
        } else {
            result = new AtupTestResultListInfo();
        }
        return result;
    }
}
