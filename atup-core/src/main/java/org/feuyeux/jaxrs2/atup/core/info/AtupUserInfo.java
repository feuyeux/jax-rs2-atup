package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ATUP Device Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@XmlRootElement
public class AtupUserInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer userRole;
    private String userName;
    private String passWord;

    public AtupUserInfo() {
    }

    public AtupUserInfo(final Integer userId) {
        this.userId = userId;
    }

    public AtupUserInfo(final Integer userRole, final String userName, final String passWord) {
        this.userRole = userRole;
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtupUserInfo(final AtupUser userDomain) {
        userId = userDomain.getUserId();
        userName = userDomain.getUserName();
        passWord = userDomain.getPassWord();
        userRole = userDomain.getUserRole();
    }

    public AtupUserInfo(final String errorInfo, final Integer statusCode) {
        super(errorInfo, statusCode);
    }

    @XmlAttribute
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    @XmlAttribute
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(final Integer userRole) {
        this.userRole = userRole;
    }

    @XmlAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @XmlAttribute
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(final String passWord) {
        this.passWord = passWord;
    }
}
