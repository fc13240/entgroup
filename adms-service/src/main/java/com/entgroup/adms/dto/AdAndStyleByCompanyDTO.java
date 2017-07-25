package com.entgroup.adms.dto;
/**
 * 
 * @author guofei
 * @ClassName: AdAndStyleByCompanyDTO.java
 * @Description: 统计模块下拉框
 * @date 2017-4-18
 */
public class AdAndStyleByCompanyDTO {
	//查询样式下拉框
	private Long adStyleId;
	private String adStyleName;
	
	//查询广告下拉框
	private Long adId;
	private String adName;
	//订单下拉框
	private Long adOrderId;
	
	private String sAdOrderId;
	
	public String getsAdOrderId() {
		return sAdOrderId;
	}
	public void setsAdOrderId(String sAdOrderId) {
		this.sAdOrderId = sAdOrderId;
	}
	public Long getAdOrderId() {
		return adOrderId;
	}
	public void setAdOrderId(Long adOrderId) {
		this.adOrderId = adOrderId;
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
	public Long getAdStyleId() {
		return adStyleId;
	}
	public void setAdStyleId(Long adStyleId) {
		this.adStyleId = adStyleId;
	}
	public String getAdStyleName() {
		return adStyleName;
	}
	public void setAdStyleName(String adStyleName) {
		this.adStyleName = adStyleName;
	}
	@Override
	public String toString() {
		return "AdAndStyleByCompanyDTO [adStyleId=" + adStyleId + ", adStyleName=" + adStyleName + ", adId=" + adId
				+ ", adName=" + adName + ", adOrderId=" + adOrderId + "]";
	}

	
}
