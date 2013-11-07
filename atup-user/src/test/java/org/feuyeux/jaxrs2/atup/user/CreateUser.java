package org.feuyeux.jaxrs2.atup.user;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;

public class CreateUser {
    public static AtupUserInfo buildUserInfo() {
        AtupUser user = buildUser();
        AtupUserInfo userInfo = new AtupUserInfo(user);
        return userInfo;
    }

    public static AtupUser buildUser() {
        Integer userRole = 1;
        String userName = "eric";
        String passWord = "han";
        return new AtupUser(userRole, userName, passWord);
    }
}