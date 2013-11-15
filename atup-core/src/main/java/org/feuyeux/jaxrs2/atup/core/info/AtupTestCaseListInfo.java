package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class AtupTestCaseListInfo extends AtupInfo {
    private List<AtupTestCaseInfo> testCaseList;

    @XmlElement
    public List<AtupTestCaseInfo> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<AtupTestCaseInfo> testCaseList) {
        this.testCaseList = testCaseList;
    }
}
