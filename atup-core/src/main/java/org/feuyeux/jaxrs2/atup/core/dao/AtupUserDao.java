package org.feuyeux.jaxrs2.atup.core.dao;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AtupUserDao extends AtupDao<AtupUser> {
    public AtupUserDao() {
        super();
    }

    public AtupUser findByName(final String userName) {
        return entityManager.createNamedQuery("findByUserName", AtupUser.class).setParameter("userName", userName).getSingleResult();
    }

    @Transactional
    public AtupUser update(final AtupUser entity) {
        final AtupUser updateEntity = findByName(entity.getUserName());
        if (updateEntity != null) {
            entity.setUserId(updateEntity.getUserId());
            return entityManager.merge(entity);
        } else {
            return null;
        }
    }
}
