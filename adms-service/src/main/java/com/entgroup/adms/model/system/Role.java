package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 角色表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_role")
public class Role extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Integer id;
    /**
     * 角色名称
     */
	private String name;
    /**
     * 角色唯一标识
     */
	@TableField("role_identity")
	private String roleIdentity;
    /**
     * 角色描述
     */
	private String description;
    /**
     * 是否删除（1-是，0--否，默认为0）
     */
	private Short deleted;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Integer parentId;
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
     * 所属公司
     */
	@TableField("company_id")
	private Long companyId;

	// edited by xiaokun on 2017-06-12 16:06 begin
	@TableField("role_level")
	private Integer roleLevel;
	// edited by xiaokun on 2017-06-12 16:06 end

	/**
	 * 权限ids
	 */
	@TableField(exist = false)
	private String authorityIds;

	/**
	 * 权限names
	 * 仅一级菜单
	 */
	@TableField(exist = false)
	private String authorityNames;

	//edited by xiaokun on 2017-04-24 09:38 begin
	/**
	 * 内部权限标识
	 */
	@TableField(exist = false)
	private Integer admin;

	@TableField(exist = false)
	private String authorityRemarks;

	public String getAuthorityRemarks() {
		return authorityRemarks;
	}

	public void setAuthorityRemarks(String authorityRemarks) {
		this.authorityRemarks = authorityRemarks;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	//edited by xiaokun on 2017-04-24 09:38 end

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleIdentity() {
		return roleIdentity;
	}

	public void setRoleIdentity(String roleIdentity) {
		this.roleIdentity = roleIdentity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getDeleted() {
		return deleted;
	}

	public void setDeleted(Short deleted) {
		this.deleted = deleted;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(String authorityIds) {
		this.authorityIds = authorityIds;
	}

	public String getAuthorityNames() {
		return authorityNames;
	}

	public void setAuthorityNames(String authorityNames) {
		this.authorityNames = authorityNames;
	}

	// edited by xiaokun on 2017-06-12 16:07 begin
	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
// edited by xiaokun on 2017-06-12 16:07 end
}
