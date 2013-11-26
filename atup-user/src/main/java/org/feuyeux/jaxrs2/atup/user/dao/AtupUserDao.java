package org.feuyeux.jaxrs2.atup.user.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.springframework.stereotype.Repository;

@Repository
public class AtupUserDao extends AtupDao<AtupUser> {
    public AtupUserDao() {
        super();
    }

    public AtupUser findByName(String userName) {
        return entityManager.createNamedQuery("findByUserName", AtupUser.class).setParameter("userName", userName).getSingleResult();
    }

    @Override
    public AtupUser update(AtupUser entity) {
        AtupUser updateEntity = findByName(entity.getUserName());
        if (updateEntity != null) {
            entity.setUserId(updateEntity.getUserId());
            return super.update(entity);
        } else {
            return null;
        }
    }
}
