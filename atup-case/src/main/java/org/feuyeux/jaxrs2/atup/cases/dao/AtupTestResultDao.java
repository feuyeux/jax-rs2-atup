package org.feuyeux.jaxrs2.atup.cases.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AtupTestResultDao extends AtupDao<AtupTestResult> {

    public AtupTestResultDao() {
    }

    public List<AtupTestResult> findByStatus(final Integer resultStatus) {
        return entityManager.createNamedQuery("findResultByStatus", AtupTestResult.class).setParameter("resultStatus", resultStatus).getResultList();
    }

    public List<AtupTestResult> findByUser(final Integer userId, final Integer start, final Integer size) {
        final TypedQuery<AtupTestResult> query = entityManager.createNamedQuery("findResultByUser", AtupTestResult.class).setParameter("userId", userId);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public List<AtupTestResult> findByTestCase(final AtupTestCase testCase) {
        return entityManager.createNamedQuery("findResultByTestCase", AtupTestResult.class).setParameter("testCase", testCase).getResultList();
    }

    @Transactional
    public AtupTestResult update(final AtupTestResult entity) {
        entity.setUpdateTime(Calendar.getInstance().getTime());
        //entity.setCreateTime(updateEntity.getCreateTime());
        return entityManager.merge(entity);
    }
}
