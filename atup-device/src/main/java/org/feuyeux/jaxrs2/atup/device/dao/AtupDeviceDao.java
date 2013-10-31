package org.feuyeux.jaxrs2.atup.device.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
import org.springframework.stereotype.Repository;

@Repository
public class AtupDeviceDao extends AtupDao<AtupDevice> {
    public AtupDeviceDao() {
        super();
    }
}
