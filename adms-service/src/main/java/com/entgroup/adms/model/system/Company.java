package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 企业表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_company")
public class Company extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 公司名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 联系地址
     */
	private String address;
    /**
     * 邮政编码
     */
	private Integer postcode;
    /**
     * 联系电话
     */
	@TableField("phone_number")
	private String phoneNumber;
    /**
     * 审核状态（1-未审核，2-审核中，3-审核通过，4-审核未通过）
     */
	private Integer status;
    /**
     * 应用密钥
     */
	@TableField("secret_key")
	private String secretKey;
    /**
     * 是否删除（1-是，0-否，默认为0）
     */
	private Integer deleted;
    /**
     * 创建时间
     */
	private Date created;
    /**
     * 公司简称
     */
	@TableField("short_name")
	private String shortName;
    /**
     * 平台类型：1-内容平台，2-广告平台
     */
	@TableField("company_type")
	private Integer companyType;
    /**
     * 联系人
     */
	private String contact;
    /**
     * 邮箱 
     */
	private String email;
    /**
     * 等级
     */
	private Integer rank;

	// edited by xiaokun on 2017-06-13 15:38 begin
	/**
	 * 开户许可证路径
	 */
	@TableField("open_account_permit_path")
	private String openAccountPermitPath;
	/**
	 * 组织机构代码证路径
	 */
	@TableField("organization_code_path")
	private String organizationCodePath;
	// edited by xiaokun on 2017-06-13 15:38 end

	/**
	 * 节目
	 */
    @TableField(exist=false)
    private List<Program> programs;
    /**
	 * 统计表
	 */
    @TableField(exist=false)
    private List<AdDisplayCount> adDisplayCounts;
    /**
	 * 广告
	 */
    @TableField(exist=false)
    private List<Ad> ads;
    /**
	 * 广告样式
	 */
    @TableField(exist=false)
    private AdStyle adStyle;
    /**
     * 曝光人数统计
     */
    @TableField(exist=false)
    private Integer sumusercount;
    /**
     * 曝光量统计
     */
    @TableField(exist=false)
    private Integer sumshowcount;
    
	public Integer getSumusercount() {
		return sumusercount;
	}

	public void setSumusercount(Integer sumusercount) {
		this.sumusercount = sumusercount;
	}

	public Integer getSumshowcount() {
		return sumshowcount;
	}

	public void setSumshowcount(Integer sumshowcount) {
		this.sumshowcount = sumshowcount;
	}

	/**
	 * 企业领域
	 */
	@TableField("company_vocation_id")
	private Long companyVocationId;

	/**
	 * 所属管理员id
	 */
	@TableField("user_id")
	private Long userId;

	// edited by xiaokun on 2017-06-15 10:11 begin
	/**
	 * 公司网址
	 */
	@TableField("internet_address")
	private String internetAddress;

	public String getInternetAddress() {
		return internetAddress;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	// edited by xiaokun on 2017-06-15 10:11 end

	// 非持久化属性
	//edited by xiaokun on 2017-04-14 16:55 begin
	/**
	 * 所属行业
	 */
	@TableField(exist=false)
	private String companyVocationName;

	/**
	 * 订单
	 */
	@TableField(exist = false)
	private String orderIds;
	/**
	 * 订单总数
	 */
	@TableField(exist = false)
	private Integer orderCount;

	/**
	 * 订单状态(status串)
	 */
	@TableField(exist = false)
	private String orderStatuses;
	/**
	 * 处理中订单总数
	 */
	@TableField(exist = false)
	private Integer orderOnDeal;

	/**
	 * 用户ids
	 */
	@TableField(exist = false)
	private String userIds;
	/**
	 * 用户总数
	 */
	@TableField(exist = false)
	private Integer userCount;

	/**
	 * 管理员账号
	 */
	@TableField(exist = false)
	private User admin;
	//edited by xiaokun on 2017-04-14 17:13 end


	// edited by xiaokun on 2017-06-19 20:39 begin
	/**
	 * 运营人员相关属性
	 */
	/**
	 * 登录账号
	 */
	@TableField(exist = false)
	private String operatorLoginName;
	/**
	 * 登录密码
	 */
	@TableField(exist = false)
	private String operatorPassword;
	/**
	 * 联系电话
	 */
	@TableField(exist = false)
	private String operatorMobile;

	public String getOperatorLoginName() {
		return operatorLoginName;
	}

	public void setOperatorLoginName(String operatorLoginName) {
		this.operatorLoginName = operatorLoginName;
	}

	public String getOperatorPassword() {
		return operatorPassword;
	}

	public void setOperatorPassword(String operatorPassword) {
		this.operatorPassword = operatorPassword;
	}

	public String getOperatorMobile() {
		return operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}
	// edited by xiaokun on 2017-06-19 20:39 end


	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}

	public List<AdDisplayCount> getAdDisplayCounts() {
		return adDisplayCounts;
	}

	public void setAdDisplayCounts(List<AdDisplayCount> adDisplayCounts) {
		this.adDisplayCounts = adDisplayCounts;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public AdStyle getAdStyle() {
		return adStyle;
	}

	public void setAdStyle(AdStyle adStyle) {
		this.adStyle = adStyle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPostcode() {
		return postcode;
	}

	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getCompanyVocationId() {
		return companyVocationId;
	}

	public void setCompanyVocationId(Long companyVocationId) {
		this.companyVocationId = companyVocationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	//edited by xiaokun on 2017-04-14 17:13 begin

	public String getCompanyVocationName() {
		return companyVocationName;
	}

	public void setCompanyVocationName(String companyVocationName) {
		this.companyVocationName = companyVocationName;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderStatuses() {
		return orderStatuses;
	}

	public void setOrderStatuses(String orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getOrderOnDeal() {
		return orderOnDeal;
	}

	public void setOrderOnDeal(Integer orderOnDeal) {
		this.orderOnDeal = orderOnDeal;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	//edited by xiaokun on 2017-04-14 17:13 end

	// edited by xiaokun on 2017-06-13 15:41 begin
	public String getOpenAccountPermitPath() {
		return openAccountPermitPath;
	}

	public void setOpenAccountPermitPath(String openAccountPermitPath) {
		this.openAccountPermitPath = openAccountPermitPath;
	}

	public String getOrganizationCodePath() {
		return organizationCodePath;
	}

	public void setOrganizationCodePath(String organizationCodePath) {
		this.organizationCodePath = organizationCodePath;
	}
	// edited by xiaokun on 2017-06-13 15:41 end
	//edited by mxy on 2017-06-16 17:02 begin
	/**
	 * 选择标记
	 */
	@TableField(exist = false)
	private Boolean selected = false;

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	//edited by mxy on 2017-06-16 17:03 end
}

