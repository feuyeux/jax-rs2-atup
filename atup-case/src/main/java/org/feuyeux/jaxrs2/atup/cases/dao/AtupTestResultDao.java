package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;

import java.util.Calendar;
import java.util.List;

@Repository
public class AtupTestResultDao extends AtupDao<AtupTestResult> {

    public AtupTestResultDao() {
    }

    public List<AtupTestResult> findByStatus(Integer resultStatus) {
        return entityManager.createNamedQuery("findResultByStatus", AtupTestResult.class).setParameter("resultStatus", resultStatus).getResultList();
    }

    public List<AtupTestResult> findByUser(AtupUser user, Integer start, Integer size) {
        TypedQuery<AtupTestResult> query = entityManager.createNamedQuery("findResultByUser", AtupTestResult.class).setParameter("user", user);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public List<AtupTestResult> findByTestCase(AtupTestCase testCase) {
        return entityManager.createNamedQuery("findResultByTestCase", AtupTestResult.class).setParameter("testCase", testCase).getResultList();
    }

    @Transactional
    public AtupTestResult update(AtupTestResult entity) {
        entity.setUpdateTime(Calendar.getInstance().getTime());
        //entity.setCreateTime(updateEntity.getCreateTime());
        return entityManager.merge(entity);
    }
}
