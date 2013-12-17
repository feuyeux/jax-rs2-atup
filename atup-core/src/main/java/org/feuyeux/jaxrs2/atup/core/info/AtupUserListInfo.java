package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupUserListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;

    private List<AtupUserInfo> userList;

    public AtupUserListInfo() {
    }

    public AtupUserListInfo(final List<AtupUserInfo> userList) {
        this.userList = userList;
    }

    public List<AtupUserInfo> getUserList() {
        return userList;
    }

    public void setUserList(final List<AtupUserInfo> userList) {
        this.userList = userList;
    }
}
