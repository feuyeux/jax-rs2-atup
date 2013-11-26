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
    org.feuyeux.jaxrs2.atup.user.dao.AtupUserDao dao;

    public AtupUser createUser(AtupUserInfo userInfo) {
        AtupUser user = new AtupUser(userInfo);
        return dao.save(user);
    }

    public AtupUser getUser(String userName) {
        return dao.findByName(userName);
    }

    public List<AtupUserInfo> getUserList() {
        List<AtupUser> list = dao.findAll();
        List<AtupUserInfo> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            result.add(new AtupUserInfo(list.get(i)));
        }
        return result;
    }

    public AtupUser updateUser(AtupUserInfo userInfo) {
        AtupUser updatedUser = dao.update(new AtupUser(userInfo));
        if (updatedUser == null) {
            //TODO
        }
        return updatedUser;
    }
}