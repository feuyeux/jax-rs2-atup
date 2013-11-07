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
 * ATUP Test Case Entity
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 *        09/09/2013
 */
@Entity
@Table(name = "test_case")
@XmlRootElement
public class AtupTestCase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer caseId;
    private String caseName;
    private AtupTestSuite suite;
    private AtupDevice device;
    private String caseBody;
    private Date createTime;
    private Date updateTime;
    private Integer caseStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "case_id")
    @XmlAttribute
    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    @Column(name = "case_name")
    @XmlAttribute
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    @ManyToOne
    @JoinColumn(name = "suiteId")
    @XmlElement
    public AtupTestSuite getSuite() {
        return suite;
    }

    public void setSuite(AtupTestSuite suite) {
        this.suite = suite;
    }

    @ManyToOne
    @JoinColumn(name = "deviceId")
    @XmlElement
    public AtupDevice getDevice() {
        return device;
    }

    public void setDevice(AtupDevice device) {
        this.device = device;
    }

    @Column(name = "case_body")
    @XmlAttribute
    public String getCaseBody() {
        return caseBody;
    }

    public void setCaseBody(String caseBody) {
        this.caseBody = caseBody;
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

    @Column(name = "case_status")
    @XmlAttribute
    public Integer getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }
}
