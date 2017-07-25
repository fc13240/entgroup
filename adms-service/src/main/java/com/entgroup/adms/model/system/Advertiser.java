package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author MaxNull
 * @since 2017-05-05
 */
@TableName("sys_advertiser")
public class Advertiser extends BaseObject {

    private static final long serialVersionUID = 1L;

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
	@TableField("short_name")
	private String shortName;
    /**
     * 审核状态,1-待审核,2-已过审,3-未通过
     */
	private Integer status;
    /**
     * 类型id
     */
	@TableField("company_vocation_id")
	private Long companyVocationId;
    /**
     * 社会信用代码
     */
	@TableField("social_credit_code")
	private String socialCreditCode;
    /**
     * 监测代码
     */
	@TableField("monitoring_code")
	private String monitoringCode;
    /**
     * 营业执照图片地址
     */
	@TableField("business_license_image_path")
	private String businessLicenseImagePath;
    /**
     * 是否删除（1-是，0-否，默认为0）
     */
	private Integer deleted;
	/**
	 * 录入公司id
	 */
	@TableField("company_id")
	private Long companyId;
	/**
	 * 录入用户id
	 */
	@TableField("user_id")
	private Long userId;
	/**
	 * 创建时间
	 */
	private Date created;


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
	//edited by mxy on 2017-05-05 11:38 begin
	//非持久化对象
	@TableField(exist = false)
	private CompanyVocation companyVocation;
	@TableField(exist = false)
	private Company company;
	@TableField(exist = false)
	private User user;

	public CompanyVocation getCompanyVocation() {
		return companyVocation;
	}

	public void setCompanyVocation(CompanyVocation companyVocation) {
		this.companyVocation = companyVocation;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	//edited by mxy on 2017-05-05 11:40 end
}
