package org.feuyeux.jaxrs2.atup.user.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtupUserService {

    @Autowired
    org.feuyeux.jaxrs2.atup.user.dao.AtupUserDao dao;

    public AtupUser createUser(AtupUserInfo userInfo) {
        AtupUser user = new AtupUser(userInfo);
        return dao.save(user);
    }
}