package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.dao.AtupTestResultDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.info.AtupTestResultListInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtupTestResultService {
    @Autowired
    AtupTestResultDao dao;

    public AtupTestResultListInfo getResults(final Integer userId, final Integer start, final Integer size) {
        AtupTestResultListInfo result;
        final List<AtupTestResult> list = dao.findByUser(userId, start, size);
        if (list != null && list.size() > 0) {
            result = new AtupTestResultListInfo(list);
        } else {
            result = new AtupTestResultListInfo();
        }
        return result;
    }
}
