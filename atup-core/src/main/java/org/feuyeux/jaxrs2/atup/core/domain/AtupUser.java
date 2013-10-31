package org.feuyeux.jaxrs2.atup.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

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

    @Column(name = "user_name")
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