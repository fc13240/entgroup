package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_display_record")
public class AdDisplayRecord extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 广告位id
     */
	@TableField("slot_id")
	private Long slotId;
    /**
     * 广告id
     */
	@TableField("ad_id")
	private Long adId;
    /**
     * 节目id
     */
	@TableField("program_id")
	private Long programId;
	@TableField("order_id")
	private String orderId;
    /**
     * 设备编码
     */
	@TableField("device_id")
	private String deviceId;
    /**
     * 曝光时间
     */
	@TableField("display_time")
	private Date displayTime;
    /**
     * 平台类型Android/Ios
     */
	@TableField("device_platform")
	private Integer devicePlatform;
	private String ip;
    /**
     * 设备型号
     */
	@TableField("device_type")
	private String deviceType;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getDisplayTime() {
		return displayTime;
	}

	public void setDisplayTime(Date displayTime) {
		this.displayTime = displayTime;
	}

	public Integer getDevicePlatform() {
		return devicePlatform;
	}

	public void setDevicePlatform(Integer devicePlatform) {
		this.devicePlatform = devicePlatform;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}

