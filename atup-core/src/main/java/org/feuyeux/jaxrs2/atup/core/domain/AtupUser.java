package org.feuyeux.jaxrs2.atup.core.domain;

import org.feuyeux.jaxrs2.atup.core.info.AtupUserInfo;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ATUP User Entity
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 *        09/09/2013
 */
@Entity
@Table(name = "atup_user")
@XmlRootElement
public class AtupUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer userRole;
    private String userName;
    private String passWord;

    public AtupUser() {

    }

    public AtupUser(Integer userId, Integer userRole, String userName) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
    }

    public AtupUser(Integer userRole, String userName, String passWord) {
        this.userRole = userRole;
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtupUser(AtupUserInfo userInfo) {
        this.userId = userInfo.getUserId();
        this.userRole = userInfo.getUserRole();
        this.userName = userInfo.getUserName();
        this.passWord = userInfo.getPassWord();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "user_id")
    @XmlAttribute
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "user_role")
    @XmlAttribute
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Column(name = "user_name", unique = true)
    @XmlAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_pwd")
    @XmlAttribute
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}