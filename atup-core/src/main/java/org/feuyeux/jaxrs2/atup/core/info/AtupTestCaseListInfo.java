package org.feuyeux.jaxrs2.atup.core.info;

import org.feuyeux.jaxrs2.atup.core.domain.AtupTestCase;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AtupTestCaseListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestCase> testCaseList;

    public AtupTestCaseListInfo(List<AtupTestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public AtupTestCaseListInfo() {
    }

    public AtupTestCaseListInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    @XmlElement
    public List<AtupTestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<AtupTestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }
}
