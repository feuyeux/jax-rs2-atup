package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestSuite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtupTestSuiteDao extends AtupDao<AtupTestSuite> {
    public AtupTestSuiteDao() {
        super();
    }

    public List<AtupTestSuite> findByStatus(Integer suiteStatus) {
        return entityManager.createNamedQuery("findBySuiteStatus").setParameter("suiteStatus", suiteStatus).getResultList();
    }

    public List<AtupTestSuite> findBySuiteName(String suiteName) {
        return entityManager.createNamedQuery("findBySuiteName").setParameter("suiteName", suiteName).getResultList();
    }
}
