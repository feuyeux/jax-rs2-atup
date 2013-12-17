package org.feuyeux.jaxrs2.atup.core.dao;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Repository
public class AtupTestCaseDao extends AtupDao<AtupTestCase> {
    public AtupTestCaseDao() {
        super();
    }

    public List<AtupTestCase> findByStatus(final Integer caseStatus) {
        return entityManager.createNamedQuery("findByStatus", AtupTestCase.class).setParameter("caseStatus", caseStatus).getResultList();
    }

    public AtupTestCase findByName(final String caseName) {
        return entityManager.createNamedQuery("findByName", AtupTestCase.class).setParameter("caseName", caseName).getSingleResult();
    }

    @Transactional
    public AtupTestCase update(final AtupTestCase entity) {
        final AtupTestCase updateEntity = findByName(entity.getCaseName());
        if (updateEntity != null) {
            entity.setCaseId(updateEntity.getCaseId());
            entity.setUpdateTime(Calendar.getInstance().getTime());
            entity.setCreateTime(updateEntity.getCreateTime());
            return entityManager.merge(entity);
        } else {
            return null;
        }
    }

    @Transactional
    public AtupTestCase update2(final AtupTestCase entity) {
        return entityManager.merge(entity);
    }
}
