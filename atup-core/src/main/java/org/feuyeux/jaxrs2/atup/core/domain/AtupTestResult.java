package org.feuyeux.jaxrs2.atup.core.domain;

import org.feuyeux.jaxrs2.atup.core.util.JaxbDateSerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

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
@NamedQueries({@NamedQuery(name = "findResultByTestCase", query = "SELECT testResult FROM AtupTestResult testResult WHERE testResult.testCase= :testCase"),
        @NamedQuery(name = "findResultByStatus", query = "SELECT testResult FROM AtupTestResult testResult WHERE testResult.resultStatus= :resultStatus"),
        @NamedQuery(name = "findResultByUser", query = "SELECT testResult FROM AtupTestResult testResult WHERE testResult.user.userId= :userId")})
public class AtupTestResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer resultId;
    private AtupTestCase testCase;
    private AtupUser user;
    private Integer resultStatus;
    private String resultBody;
    private AtupDevice device;
    private Date createTime;
    private Date updateTime;

    public AtupTestResult() {
    }

    public AtupTestResult(final AtupTestCase testCase, final AtupUser user, final Integer resultStatus, final String resultBody, final Date createTime,
                          final Date updateTime) {
        this.testCase = testCase;
        this.user = user;
        this.resultStatus = resultStatus;
        this.resultBody = resultBody;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "result_id")
    @XmlAttribute
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(final Integer resultId) {
        this.resultId = resultId;
    }

    @ManyToOne
    @JoinColumn(name = "caseId")
    @XmlElement
    public AtupTestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(final AtupTestCase testCase) {
        this.testCase = testCase;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    @XmlElement
    public AtupUser getUser() {
        return user;
    }

    public void setUser(final AtupUser user) {
        this.user = user;
    }

    @Column(name = "result_status")
    @XmlAttribute
    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(final Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    @Column(name = "result_body")
    @XmlAttribute
    public String getResultBody() {
        return resultBody;
    }

    public void setResultBody(final String resultBody) {
        this.resultBody = resultBody;
    }

    @Column(name = "create_time")
    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time")
    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }

    @ManyToOne
    @JoinColumn(name = "deviceId")
    @XmlElement
    public AtupDevice getDevice() {
        return device;
    }

    public void setDevice(final AtupDevice device) {
        this.device = device;
    }
}