package org.feuyeux.jaxrs2.atup.user.service;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtupUserService {

    @Autowired
    org.feuyeux.jaxrs2.atup.core.dao.AtupUserDao dao;

    public AtupUser createUser(final AtupUserInfo userInfo) {
        final AtupUser user = new AtupUser(userInfo);
        return dao.save(user);
    }

    public AtupUser getUser(Integer id) {
        return dao.findById(id);
    }

    public AtupUser getUser(final String userName) {
        return dao.findByName(userName);
    }

    public List<AtupUserInfo> getUserList() {
        final List<AtupUser> list = dao.findAll();
        final List<AtupUserInfo> result = new ArrayList<>();
        for (AtupUser aList : list) {
            result.add(new AtupUserInfo(aList));
        }
        return result;
    }

    public AtupUser updateUser(final AtupUserInfo userInfo) {
        final AtupUser updatedUser = dao.update(new AtupUser(userInfo));
        if (updatedUser == null) {
            //TODO
        }
        return updatedUser;
    }
}