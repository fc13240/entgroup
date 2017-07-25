package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_user")
public class User extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
    private Long id;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 真实姓名/联系人姓名
     */
    @TableField("real_name")
    private String realName;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 固定电话
     */
    @TableField("phone_number")
    private String phoneNumber;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * QQ号码
     */
    private String qq;
    /**
     * 所属公司
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 是否为管理员（1-是，0-否，默认为0）
     */
    @TableField("admin")
    private Integer admin;
    /**
     * 账号是否启用（1-是，0-否，默认为1）
     */
    private Short enabled;
    /**
     * 是否删除（1-是，0-否，默认为0）
     */
    private Short deleted;
    /**
     * 备注
     */
    private String remark;
    /**
     * 用于加密的盐
     */
    private String salt;
    /**
     * 最后登录时间
     */
    @TableField("last_login_date")
    private Date lastLoginDate;
    /**
     * 所属管理员/合作伙伴
     */
    @TableField("manager_id")
    private Long managerId;
    /**
     * 创建人
     */
    @TableField("creator_id")
    private Long creatorId;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 修改人
     */
    @TableField("modifier_id")
    private Long modifierId;
    /**
     * 修改时间
     */
    private Date updated;
    /**
     * 用户头像
     */
    @TableField("head_portrait")
    private String headPortrait;
    /**
     * 上次登录ip
     */
    @TableField("last_login_ip")
    private String lastLoginIp;

    // 非持久化属性
    @TableField(exist=false)
    private Role role;

    @TableField(exist=false)
    private List<Role> roles;

    @TableField(exist=false)
    private List<Authority> authorities;

    @TableField(exist=false)
    private Integer roleId;

    @TableField(exist=false)
    private String roleName;

    @TableField(exist=false)
    private Company company;

    // edited by xiaokun on 2017-06-14 15:56 begin
    @TableField(exist = false)
    private Integer roleLevel;
    // edited by xiaokun on 2017-06-14 15:56 end

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    // edited by xiaokun on 2017-06-14 15:57 begin
    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }
    // edited by xiaokun on 2017-06-14 15:57 end
}

