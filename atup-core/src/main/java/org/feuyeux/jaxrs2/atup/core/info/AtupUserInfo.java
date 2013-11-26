package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupUser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ATUP Device Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 *        09/09/2013
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

    public AtupUserInfo(Integer userRole, String userName, String passWord) {
        this.userRole = userRole;
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtupUserInfo(AtupUser userDomain) {
        this.userId = userDomain.getUserId();
        this.userName = userDomain.getUserName();
        this.passWord = userDomain.getPassWord();
        this.userRole = userDomain.getUserRole();
    }

    public AtupUserInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    @XmlAttribute
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @XmlAttribute
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @XmlAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @XmlAttribute
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
