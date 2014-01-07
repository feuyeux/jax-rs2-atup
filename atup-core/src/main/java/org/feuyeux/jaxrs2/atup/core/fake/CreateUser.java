package org.feuyeux.jaxrs2.atup.core.fake;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;
import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;

public class CreateUser {

    private static final String TEST_NAME = "eric";
    public static final String TEST_NAME1 = "mars";

    public static AtupUserInfo buildUserInfo() {
        final AtupUser user = buildUser();
        return new AtupUserInfo(user);
    }

    public static AtupUser buildUser() {
        final Integer userRole = 1;
        final String userName = CreateUser.TEST_NAME + System.nanoTime();
        final String passWord = "han";
        return new AtupUser(userRole, userName, passWord);
    }
}