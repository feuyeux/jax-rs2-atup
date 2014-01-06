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
        final AtupUser updateEntity;
        try {
            if (entity.getUserId() != null) {
                updateEntity = findById(entity.getUserId());
            } else {
                updateEntity = findByName(entity.getUserName());
            }

            if (updateEntity != null) {
                if (entity.getStatus() != null) {
                    updateEntity.setStatus(entity.getStatus());
                }
                if (entity.getUserName() != null) {
                    updateEntity.setUserName(entity.getUserName());
                }
                if (entity.getPassWord() != null) {
                    updateEntity.setPassWord(entity.getPassWord());
                }
                if (entity.getUserRole() != null) {
                    updateEntity.setUserRole(entity.getUserRole());
                }
                return entityManager.merge(updateEntity);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
