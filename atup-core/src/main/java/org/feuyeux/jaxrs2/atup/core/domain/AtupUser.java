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
 * 09/09/2013
 */
@Entity
@Table(name = "atup_user")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "findByUserName", query = "SELECT atupUser FROM AtupUser atupUser WHERE atupUser.userName= :userName"),})
public class AtupUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer userRole;
    private String userName;
    private String passWord;

    public AtupUser() {

    }

    public AtupUser(final Integer userId, final Integer userRole, final String userName) {
        this.userId = userId;
        this.userRole = userRole;
        this.userName = userName;
    }

    public AtupUser(final Integer userRole, final String userName, final String passWord) {
        this.userRole = userRole;
        this.userName = userName;
        this.passWord = passWord;
    }

    public AtupUser(final AtupUserInfo userInfo) {
        userId = userInfo.getUserId();
        userRole = userInfo.getUserRole();
        userName = userInfo.getUserName();
        passWord = userInfo.getPassWord();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "user_id")
    @XmlAttribute
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    @Column(name = "user_role")
    @XmlAttribute
    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(final Integer userRole) {
        this.userRole = userRole;
    }

    @Column(name = "user_name", unique = true)
    @XmlAttribute
    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    @Column(name = "user_pwd")
    @XmlAttribute
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(final String passWord) {
        this.passWord = passWord;
    }
}