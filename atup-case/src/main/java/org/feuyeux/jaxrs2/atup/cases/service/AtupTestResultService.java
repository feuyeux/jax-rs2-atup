package org.feuyeux.jaxrs2.atup.cases.service;

import java.util.ArrayList;
import java.util.List;

import org.feuyeux.jaxrs2.atup.cases.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultInfo;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupTestResultService {
    @Autowired
    AtupTestResultDao dao;

    public AtupTestResultListInfo getResults(final Integer userId, final Integer start, final Integer size) {
        AtupTestResultListInfo result = null;
        final List<AtupTestResult> list = dao.findByUser(userId, start, size);
        if (list != null && list.size() > 0) {
            final List<AtupTestResultInfo> caseInfoList = new ArrayList<>(list.size());
            for (final AtupTestResult testResult : list) {
                final AtupTestResultInfo resultInfo = new AtupTestResultInfo(testResult);
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
