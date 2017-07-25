package com.entgroup.adms.dto;
/**
 * 
 * @author guofei
 * @ClassName: OrderAdListDTO.java
 * @Description:  订单详情列表（订单对应广告）
 * @date 2017-4-18
 */
public class OrderAdListDTO {
	private Long orderId; 
	/**
	 * 广告id
	 */
	private Long adId;
	/**
	 * 广告名称
	 */
	private String adName;
	/**
	 * 广告位的数量
	 */
	private Integer adSlotCount;
	/**
	 * 广告样式名称
	 */
	private String adStyleName;
	/**
	 * 广告位 下5个
	 */
	private Long adSlotId;
	private Integer videoPosition;
	private String videoName;
	private Long companyId;
	private String companyName;
	private String slotTime;
	
	
	
	public String getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}
	public Integer getAdSlotCount() {
		return adSlotCount;
	}
	public void setAdSlotCount(Integer adSlotCount) {
		this.adSlotCount = adSlotCount;
	}
	public Long getAdSlotId() {
		return adSlotId;
	}
	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}
	public Integer getVideoPosition() {
		return videoPosition;
	}
	public void setVideoPosition(Integer videoPosition) {
		this.videoPosition = videoPosition;
	}
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdStyleName() {
		return adStyleName;
	}
	public void setAdStyleName(String adStyleName) {
		this.adStyleName = adStyleName;
	}
	
	
}
