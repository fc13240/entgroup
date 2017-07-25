/**     
 * @Project: adms-service  
 * @Title: AddressInfoDTO.java
 * @Description: TODO(用一句话描述该文件做什么)   
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 * @Version: V1.0     
 */
package com.entgroup.adms.dto;

import java.util.Date;

/**
 * @author mxy
 * @ClassName: ScreenAdSlotDTO
 * @Description: 广告位筛选信息
 * @date 2017-03-23 15:19
 */
public class AdvertisersDTO {

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 简称
	 */
	private String shortName;
	/**
	 * 审核状态,1-待审核,2-已过审,3-未通过
	 */
	private Integer status;
	/**
	 * 类型id
	 */
	private Long companyVocationId;
	/**
	 * 社会信用代码
	 */
	private String socialCreditCode;
	/**
	 * 监测代码
	 */
	private String monitoringCode;
	/**
	 * 营业执照图片地址
	 */
	private String businessLicenseImagePath;
	/**
	 * 是否删除（1-是，0-否，默认为0）
	 */
	private Integer deleted;
	/**
	 * 录入公司id
	 */
	private Long companyId;
	/**
	 * 录入用户id
	 */
	private Long userId;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 类型名称
	 */
	private String companyVocationName;
	/**
	 * 录入公司名称
	 */
	private String companyName;
	/**
	 * 录入用户名称
	 */
	private String userName;
	/**
	 * 审核状态名称
	 */
	private String statusName;
	/**
	 * 审核状态集合
	 */
	private String statuses;
	/**
	 * id集合
	 */
	private String ids;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCompanyVocationId() {
		return companyVocationId;
	}

	public void setCompanyVocationId(Long companyVocationId) {
		this.companyVocationId = companyVocationId;
	}

	public String getSocialCreditCode() {
		return socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}

	public String getMonitoringCode() {
		return monitoringCode;
	}

	public void setMonitoringCode(String monitoringCode) {
		this.monitoringCode = monitoringCode;
	}

	public String getBusinessLicenseImagePath() {
		return businessLicenseImagePath;
	}

	public void setBusinessLicenseImagePath(String businessLicenseImagePath) {
		this.businessLicenseImagePath = businessLicenseImagePath;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCompanyVocationName() {
		return companyVocationName;
	}

	public void setCompanyVocationName(String companyVocationName) {
		this.companyVocationName = companyVocationName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
