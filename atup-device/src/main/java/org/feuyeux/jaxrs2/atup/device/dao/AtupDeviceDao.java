package org.feuyeux.jaxrs2.atup.device.dao;

import java.util.Calendar;
import java.util.List;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AtupDeviceDao extends AtupDao<AtupDevice> {
    public AtupDeviceDao() {
        super();
    }

    public AtupDevice findByIp(String deviceHost) {
        return entityManager.createNamedQuery("findByDeviceIp", AtupDevice.class).setParameter("deviceHost", deviceHost).getSingleResult();
    }

    public List<AtupDevice> findDevicesByUser(Integer userId) {
        return entityManager.createNamedQuery("findDevicesByUser", AtupDevice.class).setParameter("userId", userId).getResultList();
    }

    @Transactional
    public AtupDevice update(AtupDevice entity) {
        AtupDevice updateEntity = findByIp(entity.getDeviceHost());
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
