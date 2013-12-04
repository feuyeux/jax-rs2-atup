//package org.feuyeux.jaxrs2.atup.core.info;
//
//import org.feuyeux.jaxrs2.atup.core.domain.AtupDevice;
//import org.feuyeux.jaxrs2.atup.core.util.JaxbDateSerializer;
//
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import java.util.Date;
//
//@XmlRootElement
//public class AtupDeviceInfo extends AtupInfo {
//    private static final long serialVersionUID = 1L;
//
//    private String deviceHost;
//    private String deviceName;
//    private Integer deviceStatus;
//    private Integer deviceType;
//    private Integer deviceId;
//    private AtupUserInfo userInfo;
//
//    private Date createTime;
//    private Date updateTime;
//
//    public AtupDeviceInfo() {
//    }
//
//    public AtupDeviceInfo(String errorInfo, Integer statusCode) {
//        super(errorInfo, statusCode);
//    }
//
//    public AtupDeviceInfo(String deviceHost, String deviceName, Integer deviceType) {
//        this.deviceHost = deviceHost;
//        this.deviceName = deviceName;
//        this.deviceType = deviceType;
//    }
//
//    public AtupDeviceInfo(AtupDevice atupDevice) {
//        deviceId = atupDevice.getDeviceId();
//        deviceHost = atupDevice.getDeviceHost();
//        deviceName = atupDevice.getDeviceName();
//        deviceStatus = atupDevice.getDeviceStatus();
//        deviceType = atupDevice.getDeviceType();
//        createTime = atupDevice.getCreateTime();
//        updateTime = atupDevice.getUpdateTime();
//        userInfo = new AtupUserInfo(atupDevice.getUser());
//    }
//
//    @XmlAttribute
//    public String getDeviceHost() {
//        return deviceHost;
//    }
//
//    public void setDeviceHost(String deviceHost) {
//        this.deviceHost = deviceHost;
//    }
//
//    @XmlAttribute
//    public String getDeviceName() {
//        return deviceName;
//    }
//
//    public void setDeviceName(String deviceName) {
//        this.deviceName = deviceName;
//    }
//
//    @XmlAttribute
//    public Integer getDeviceStatus() {
//        return deviceStatus;
//    }
//
//    public void setDeviceStatus(Integer deviceStatus) {
//        this.deviceStatus = deviceStatus;
//    }
//
//    @XmlAttribute
//    public Integer getDeviceType() {
//        return deviceType;
//    }
//
//    public void setDeviceType(Integer deviceType) {
//        this.deviceType = deviceType;
//    }
//
//    @XmlAttribute
//    public Integer getDeviceId() {
//        return deviceId;
//    }
//
//    public void setDeviceId(Integer deviceId) {
//        this.deviceId = deviceId;
//    }
//
//    @XmlAttribute
//    public AtupUserInfo getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(AtupUserInfo userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    @XmlAttribute
//    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    @XmlAttribute
//    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//}
