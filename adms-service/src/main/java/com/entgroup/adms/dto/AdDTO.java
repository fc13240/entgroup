package com.entgroup.adms.dto;

import java.util.Date;
import java.util.List;

import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.AdReasonTemplate;

/**
 * @author liuxiaolong
 * @ClassName: AdDTO
 * @Description: 广告信息
 * @date 2017-03-28
 */
public class AdDTO {

	/**
	 * 广告ID
	 */
	private Long adId;
	
	/**
	 * 广告样式ID
	 */
	private Long styleId;
	
	/**
	 * 广告类型ID
	 */
	private Long typeId;
	
	/**
	 * 广告订单ID
	 */
	private Long orderId;
	
	/**
	 * 入库时间
	 */
	private Date created;
	
	/**
	 * 广告名称
	 */
	private String adName;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 广告样式名称
	 */
	private String styleName;
	
	/**
	 * 广告类型名称
	 */
	private String typeName ;
	
	/**
	 * 广告广告位数量
	 */
	private String adSlotNum ;
	
	/**
	 * 广告状态
	 */
	private String adStatus;
	
	/**
	 * 广告审核未通过原因
	 */
	private String adReasonTemplates;
	/**
	 * 格式化的日期
	 */
	private String date;
	
	private Date newDate;

	// edited by xiaokun on 2017-06-13 17:07 begin
	private AdOrder bindOrder;
	// edited by xiaokun on 2017-06-13 17:07 end
	
	
	
	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getStyleId() {
		return styleId;
	}

	public void setStyleId(Long styleId) {
		this.styleId = styleId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAdSlotNum() {
		return adSlotNum;
	}

	public void setAdSlotNum(String adSlotNum) {
		this.adSlotNum = adSlotNum;
	}

	public String getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(String adStatus) {
		this.adStatus = adStatus;
	}

	public String getAdReasonTemplates() {
		return adReasonTemplates;
	}

	public void setAdReasonTemplates(String adReasonTemplates) {
		this.adReasonTemplates = adReasonTemplates;
	}

	// edited by xiaokun on 2017-06-13 17:07 begin
	public AdOrder getBindOrder() {
		return bindOrder;
	}

	public void setBindOrder(AdOrder bindOrder) {
		this.bindOrder = bindOrder;
	}
	// edited by xiaokun on 2017-06-13 17:07 end
}
