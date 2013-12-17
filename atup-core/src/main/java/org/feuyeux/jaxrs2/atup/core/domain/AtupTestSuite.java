package org.feuyeux.jaxrs2.atup.core.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ATUP Test Suite Entity
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@Entity
@Table(name = "test_suite")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "findBySuiteStatus", query = "SELECT testSuite FROM AtupTestSuite testSuite WHERE testSuite.suiteStatus= :suiteStatus"),
        @NamedQuery(name = "findBySuiteName", query = "SELECT testSuite FROM AtupTestSuite testSuite WHERE testSuite.suiteName= :suiteName")})
public class AtupTestSuite implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer suiteId;
    private String suiteName;
    private Integer suiteType;
    private Integer suiteStatus;

    public AtupTestSuite() {
    }

    public AtupTestSuite(final String suiteName, final Integer suiteType, final Integer suiteStatus) {
        this.suiteName = suiteName;
        this.suiteType = suiteType;
        this.suiteStatus = suiteStatus;
    }

    public AtupTestSuite(final Integer suiteId) {
        this.suiteId = suiteId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "suite_id")
    @XmlAttribute
    public Integer getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(final Integer suiteId) {
        this.suiteId = suiteId;
    }

    @Column(name = "suite_name", unique = true)
    @XmlAttribute
    public String getSuiteName() {
        return suiteName;
    }

    public void setSuiteName(final String suiteName) {
        this.suiteName = suiteName;
    }

    @Column(name = "suite_type")
    @XmlAttribute
    public Integer getSuiteType() {
        return suiteType;
    }

    public void setSuiteType(final Integer suiteType) {
        this.suiteType = suiteType;
    }

    @Column(name = "suite_status")
    @XmlAttribute
    public Integer getSuiteStatus() {
        return suiteStatus;
    }

    public void setSuiteStatus(final Integer suiteStatus) {
        this.suiteStatus = suiteStatus;
    }
}