package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AtupTestCaseListInfo extends AtupInfo {
    private static final long serialVersionUID = 1L;
    private List<AtupTestCaseInfo> testCaseList;

    public AtupTestCaseListInfo(List<AtupTestCaseInfo> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public AtupTestCaseListInfo() {
    }

    public AtupTestCaseListInfo(String errorInfo, Integer statusCode) {
        super(errorInfo, statusCode);
    }

    @XmlElement
    public List<AtupTestCaseInfo> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<AtupTestCaseInfo> testCaseList) {
        this.testCaseList = testCaseList;
    }
}
