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
 * 09/09/2013
 */
@Entity
@Table(name = "test_case")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "findByStatus", query = "SELECT testCase FROM AtupTestCase testCase WHERE testCase.caseStatus= :caseStatus"),
        @NamedQuery(name = "findByName", query = "SELECT testCase FROM AtupTestCase testCase WHERE testCase.caseName= :caseName")})
public class AtupTestCase implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer caseId;
    private String caseName;
    private AtupTestSuite suite;
    private String caseBody;
    private Date createTime;
    private Date updateTime;
    private Integer caseStatus;

    public AtupTestCase() {
    }

    public AtupTestCase(final String caseName, final AtupTestSuite suite, final String caseBody, final Date createTime, final Date updateTime,
                        final Integer caseStatus) {
        this.caseName = caseName;
        this.suite = suite;
        this.caseBody = caseBody;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.caseStatus = caseStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "case_id")
    @XmlAttribute
    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(final Integer caseId) {
        this.caseId = caseId;
    }

    @Column(name = "case_name", unique = true)
    @XmlAttribute
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(final String caseName) {
        this.caseName = caseName;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "suiteId")
    @XmlElement
    public AtupTestSuite getSuite() {
        return suite;
    }

    public void setSuite(final AtupTestSuite suite) {
        this.suite = suite;
    }

    @Column(name = "case_body")
    @XmlAttribute
    public String getCaseBody() {
        return caseBody;
    }

    public void setCaseBody(final String caseBody) {
        this.caseBody = caseBody;
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

    @Column(name = "case_status")
    @XmlAttribute
    public Integer getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(final Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("AtupTestCase[");
        if (caseId != null) {
            builder.append("caseId=").append(caseId);
        } else {
            builder.append("caseId=nil");
        }
        if (caseName != null && !caseName.isEmpty()) {
            builder.append(",caseName=").append(caseName);
        } else {
            builder.append(",caseName=nil");
        }
        if (suite != null) {
            builder.append(",suite=").append(suite);
        } else {
            builder.append(",suite=nil");
        }
        if (caseBody != null && !caseBody.isEmpty()) {
            builder.append(",caseBody=").append(caseBody);
        } else {
            builder.append(",caseBody=nil");
        }
        if (caseStatus != null) {
            builder.append(",caseStatus=").append(caseStatus);
        } else {
            builder.append(",caseStatus=nil");
        }
        return builder.append("]").toString();
    }
}
