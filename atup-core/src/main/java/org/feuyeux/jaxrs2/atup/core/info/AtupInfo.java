package org.feuyeux.jaxrs2.atup.core.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * ATUP Device Info
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@XmlRootElement
public abstract class AtupInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String errorInfo;
    protected Integer statusCode = AtupErrorCode.NONE;

    public AtupInfo() {
    }

    public AtupInfo(final String errorInfo, final Integer statusCode) {
        this.errorInfo = errorInfo;
        this.statusCode = statusCode;
    }

    @XmlAttribute
    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(final String errorInfo) {
        this.errorInfo = errorInfo;
    }

    @XmlAttribute
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }
}
