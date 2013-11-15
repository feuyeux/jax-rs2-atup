package org.feuyeux.jaxrs2.atup.core.info;

import java.util.List;

public class AtupTestResultListInfo extends AtupInfo {

    private List<AtupTestResultInfo> resultList;

    public List<AtupTestResultInfo> getResultList() {
        return resultList;
    }

    public void setResultList(List<AtupTestResultInfo> resultList) {
        this.resultList = resultList;
    }
}
