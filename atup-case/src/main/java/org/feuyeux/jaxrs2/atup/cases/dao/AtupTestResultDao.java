package org.feuyeux.jaxrs2.atup.cases.dao;


import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestResult;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AtupTestResultDao extends AtupDao<AtupTestResult> {

    public AtupTestResultDao() {
    }

    public List<AtupTestResult> findByStatus(Integer resultStatus) {
        return entityManager.createNamedQuery("findResultByStatus").setParameter("resultStatus", resultStatus).getResultList();
    }

    public List<AtupTestResult> findByUser(AtupUser user, Integer start, Integer size) {
        Query query = entityManager.createNamedQuery("findResultByUser").setParameter("user", user);
        query.setFirstResult(start);
        query.setMaxResults(size);
        return query.getResultList();
    }

    public List<AtupTestResult> findByTestCase(AtupTestCase testCase) {
        return entityManager.createNamedQuery("findResultByTestCase").setParameter("testCase", testCase).getResultList();
    }
}
