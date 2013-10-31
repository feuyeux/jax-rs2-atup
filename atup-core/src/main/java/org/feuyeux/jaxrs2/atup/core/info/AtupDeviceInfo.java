package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;

/**
 * ATUP Device Info
 * 
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@XmlRootElement
public class AtupDeviceInfo {
    private AtupDevice entity;
    private String errorInfo;
    private Integer statusCode;

    public AtupDeviceInfo() {
    }

    public AtupDeviceInfo(AtupDevice entity) {
        this.entity = entity;
    }

    public AtupDeviceInfo(AtupDevice entity, String errorInfo, Integer statusCode) {
        this.entity = entity;
        this.errorInfo = errorInfo;
        this.statusCode = statusCode;
    }

    @XmlElement
    public AtupDevice getEntity() {
        return entity;
    }

    public void setEntity(AtupDevice entity) {
        this.entity = entity;
    }

    @XmlAttribute
    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @XmlAttribute
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
