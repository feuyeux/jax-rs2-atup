package org.feuyeux.jaxrs2.atup.core.domain;

import org.feuyeux.jaxrs2.atup.core.util.JaxbDateSerializer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;

/**
 * ATUP Device Entity
 *
 * @author feuyeux@gmail.com
 * @since 1.0
 * 09/09/2013
 */
@Entity
@Table(name = "atup_device")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "findByDeviceIp", query = "SELECT atupDevice FROM AtupDevice atupDevice WHERE atupDevice.deviceHost= :deviceHost"),
        @NamedQuery(name = "findDevicesByUser", query = "SELECT atupDevice FROM AtupDevice atupDevice WHERE atupDevice.user.userId= :userId")})
public class AtupDevice implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer deviceId;
    private AtupUser user;
    private String deviceHost;
    private String deviceName;
    private Integer deviceStatus;
    private Integer deviceType;
    private Date createTime;
    private Date updateTime;

    public AtupDevice() {
    }

    public AtupDevice(final Integer deviceId, final AtupUser user, final String deviceHost, final String deviceName, final Integer deviceStatus,
                      final Integer deviceType) {
        super();
        this.deviceId = deviceId;
        this.user = user;
        this.deviceHost = deviceHost;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
        this.deviceType = deviceType;
    }

    public AtupDevice(final AtupUser user, final String deviceHost, final String deviceName, final Integer deviceStatus, final Integer deviceType,
                      final Date createTime, final Date updateTime) {
        this.user = user;
        this.deviceHost = deviceHost;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
        this.deviceType = deviceType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    //    public AtupDevice(AtupDeviceInfo deviceInfo) {
    //        deviceId = deviceInfo.getDeviceId();
    //        deviceHost = deviceInfo.getDeviceHost();
    //        deviceName = deviceInfo.getDeviceName();
    //        deviceStatus = deviceInfo.getDeviceStatus();
    //        deviceType = deviceInfo.getDeviceType();
    //        createTime = deviceInfo.getCreateTime();
    //        updateTime = deviceInfo.getUpdateTime();
    //        user = new AtupUser(deviceInfo.getUserInfo());
    //    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EMP_SEQ")
    @SequenceGenerator(name = "EMP_SEQ")
    @Column(unique = true, nullable = false, name = "device_id")
    @XmlAttribute
    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(final Integer deviceId) {
        this.deviceId = deviceId;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    //@XmlElement
    public AtupUser getUser() {
        return user;
    }

    public void setUser(final AtupUser user) {
        this.user = user;
    }

    @Column(name = "device_host", unique = true)
    @XmlAttribute
    public String getDeviceHost() {
        return deviceHost;
    }

    public void setDeviceHost(final String deviceHost) {
        this.deviceHost = deviceHost;
    }

    @Column(name = "device_name", unique = true)
    @XmlAttribute
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

    @Column(name = "device_status")
    @XmlAttribute
    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(final Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    @Column(name = "device_type")
    @XmlAttribute
    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(final Integer deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "create_time")
    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateSerializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return this.createTime;
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

    @Override
    public String toString() {
        return deviceId + "-" + deviceHost + "-" + deviceName + "-" + deviceStatus + "-" + deviceType + "-" + user;
    }
}