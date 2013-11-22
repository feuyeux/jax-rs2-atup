package org.feuyeux.jaxrs2.atup.cases.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtupTestCaseDao extends AtupDao<AtupTestCase> {
    public AtupTestCaseDao() {
        super();
    }

    public List<AtupTestCase> findByStatus(Integer caseStatus) {
        return entityManager.createNamedQuery("findByStatus", AtupTestCase.class).setParameter("caseStatus", caseStatus).getResultList();
    }

    public List<AtupTestCase> findByName(String caseName) {
        return entityManager.createNamedQuery("findByName", AtupTestCase.class).setParameter("caseName", caseName).getResultList();
    }
}
