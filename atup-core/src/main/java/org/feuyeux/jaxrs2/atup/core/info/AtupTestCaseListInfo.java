package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AtupTestCaseListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestCase> testCaseList;

    public AtupTestCaseListInfo(final List<AtupTestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public AtupTestCaseListInfo() {
    }

    public AtupTestCaseListInfo(final String errorInfo, final Integer statusCode) {
        super(errorInfo, statusCode);
    }

    @XmlElement
    public List<AtupTestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(final List<AtupTestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }
}
