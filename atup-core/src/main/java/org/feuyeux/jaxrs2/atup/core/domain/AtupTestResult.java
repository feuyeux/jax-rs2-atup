package org.feuyeux.jaxrs2.atup.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.feuyeux.jaxrs2.atup.core.util.JaxbDateSerializer;

/**
 * ATUP Test Result Entity
 * 
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@Entity
@Table(name = "test_result")
@XmlRootElement
public class AtupTestResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer resultId;
    private AtupTestCase testCase;
    private AtupUser user;
    private Integer resultStatus;
    private String resultBody;
    private Date createTime;
    private Date updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "result_id")
    @XmlAttribute
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    @ManyToOne
    @JoinColumn(name = "caseId")
    @XmlElement
    public AtupTestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(AtupTestCase testCase) {
        this.testCase = testCase;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    @XmlElement
    public AtupUser getUser() {
        return user;
    }

    public void setUser(AtupUser user) {
        this.user = user;
    }

    @Column(name = "result_status")
    @XmlAttribute
    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    @Column(name = "result_body")
    @XmlAttribute
    public String getResultBody() {
        return resultBody;
    }

    public void setResultBody(String resultBody) {
        this.resultBody = resultBody;
    }

    @Column(name = "create_time")
    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}