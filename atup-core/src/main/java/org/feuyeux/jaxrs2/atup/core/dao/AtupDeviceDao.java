package org.feuyeux.jaxrs2.atup.core.dao;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

@Repository
public class AtupDeviceDao extends AtupDao<AtupDevice> {
    public AtupDeviceDao() {
        super();
    }

    public AtupDevice findByIp(final String deviceHost) {
        final TypedQuery<AtupDevice> atupDeviceTypedQuery = entityManager.createNamedQuery("findByDeviceIp", AtupDevice.class).setParameter("deviceHost",
                deviceHost);
        atupDeviceTypedQuery.setHint("javax.persistence.cache.storeMode", "REFRESH");
        return atupDeviceTypedQuery.getSingleResult();
    }

    public List<AtupDevice> findDevicesByUser(final Integer userId) {
        return entityManager.createNamedQuery("findDevicesByUser", AtupDevice.class).setParameter("userId", userId).getResultList();
    }

    @Transactional
    public AtupDevice update(final AtupDevice entity) {
        final AtupDevice updateEntity = findByIp(entity.getDeviceHost());
        if (updateEntity != null) {
            entity.setDeviceId(updateEntity.getDeviceId());
            entity.setUpdateTime(Calendar.getInstance().getTime());
            entity.setCreateTime(updateEntity.getCreateTime());
            return entityManager.merge(entity);
        } else {
            return null;
        }
    }
}
