package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;

public class CreateUser {

    public static final String TEST_NAME = "eric";
    public static final String TEST_NAME1 = "mars";

    public static AtupUserInfo buildUserInfo() {
        AtupUser user = buildUser();
        AtupUserInfo userInfo = new AtupUserInfo(user);
        return userInfo;
    }

    public static AtupUser buildUser() {
        Integer userRole = 1;
        String userName = TEST_NAME + System.nanoTime();
        String passWord = "han";
        return new AtupUser(userRole, userName, passWord);
    }
}