package com.entgroup.adms.dto;

import java.util.Date;
/**
 * 
 * @author guofei
 * @ClassName: DisplayRecordDTO.java
 * @Description: 曝光量和用户量的添加
 * @date 2017-4-18
 */
public class DisplayRecordDTO {
	private Long adId;
	private Long orderId;
	private Long programId;
	private Date displayTime;
	private Integer showCount;
	private Integer userCount;
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Date getDisplayTime() {
		return displayTime;
	}
	public void setDisplayTime(Date displayTime) {
		this.displayTime = displayTime;
	}
	public Integer getShowCount() {
		return showCount;
	}
	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
	
	
}
