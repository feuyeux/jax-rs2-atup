package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupUserListInfo extends AtupInfo {

    private List<AtupUserInfo> userList;

    public List<AtupUserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<AtupUserInfo> userList) {
        this.userList = userList;
    }
}
