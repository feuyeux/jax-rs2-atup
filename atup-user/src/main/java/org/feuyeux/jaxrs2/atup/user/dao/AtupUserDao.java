package org.feuyeux.jaxrs2.atup.user.dao;

import org.feuyeux.jaxrs2.atup.core.dao.AtupDao;
import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.springframework.stereotype.Repository;

@Repository
public class AtupUserDao extends AtupDao<AtupUser> {
    public AtupUserDao() {
        super();
    }
}
