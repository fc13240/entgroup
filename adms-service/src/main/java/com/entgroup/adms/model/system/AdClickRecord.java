package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 广告点击表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_click_record")
public class AdClickRecord extends BaseObject {

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
    /**
     * 设备编码
     */
	@TableField("device_id")
	private String deviceId;
    /**
     * 点击时间
     */
	@TableField("click_time")
	private Date clickTime;
    /**
     * 设备平台
     */
	@TableField("device_platform")
	private Integer devicePlatform;
	private String ip;
    /**
     * 设备型号
     */
	@TableField("device_type")
	private String deviceType;
	@TableField("order_id")
	private String orderId;


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

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getClickTime() {
		return clickTime;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}

