package com.entgroup.adms.model.system;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author ShiXinPeng
 * @since 2017-06-08
 */
@TableName("sys_video_play_record")
public class VideoPlayRecord extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("video_id")
	private Long videoId;
	@TableField("platform_id")
	private Long platformId;
	@TableField("source_id")
	private String sourceId;
	@TableField("device_id")
	private String deviceId;
	@TableField("play_time")
	private Date playTime;
	@TableField("device_platform")
	private Integer devicePlatform;
	private String ip;
	@TableField("region_code")
	private String regionCode;
	@TableField("city_code")
	private String cityCode;
	@TableField("county_code")
	private String countyCode;
	private String browser;
	@TableField("device_type")
	private String deviceType;
	private String pn;
	private Integer net;
	private String osv;
	private String bn;
	private Integer sw;
	private Integer sh;
	@TableField("sdk_version")
	private String sdkVersion;

	public VideoPlayRecord() {
		super();
	}


	public VideoPlayRecord(Long videoId, String sourceId, String deviceId, Date playTime, Integer devicePlatform, String ip, String regionCode, String cityCode, String deviceType, Integer net, String osv, String bn, Integer sw, Integer sh, String sdkVersion, Long platformId) {
		this.videoId = videoId;
		this.sourceId = sourceId;
		this.deviceId = deviceId;
		this.playTime = playTime;
		this.devicePlatform = devicePlatform;
		this.ip = ip;
		this.regionCode = regionCode;
		this.cityCode = cityCode;
		this.deviceType = deviceType;
		this.net = net;
		this.osv = osv;
		this.bn = bn;
		this.sw = sw;
		this.sh = sh;
		this.sdkVersion = sdkVersion;
		this.platformId = platformId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
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

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public Integer getNet() {
		return net;
	}

	public void setNet(Integer net) {
		this.net = net;
	}

	public String getOsv() {
		return osv;
	}

	public void setOsv(String osv) {
		this.osv = osv;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public Integer getSw() {
		return sw;
	}

	public void setSw(Integer sw) {
		this.sw = sw;
	}

	public Integer getSh() {
		return sh;
	}

	public void setSh(Integer sh) {
		this.sh = sh;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

}
