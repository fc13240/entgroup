package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_authority")
public class Authority extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Integer id;
    /**
     * 权限名称
     */
	private String name;
    /**
     * 权限类型（1-超级管理员权限，2-企业管理员权限，3-开放权限）
     */
	private Integer type;
    /**
     * 权限唯一标识
     */
	@TableField("authority_identity")
	private String authorityIdentity;
    /**
     * 菜单图标
     */
	private String icon;
    /**
     * 展开时的图标
     */
	@TableField("icon_open")
	private String iconOpen;
    /**
     * 收起时的图标
     */
	@TableField("icon_close")
	private String iconClose;
    /**
     * 是否展开（1-是，0-否，默认为0）
     */
	@TableField("open")
	private Integer open;
    /**
     * 描述
     */
	private String description;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Integer parentId;
    /**
     * 是否删除（1-是，0-否，默认为0）
     */
	private Integer deleted;
    /**
     * 备注
     */
	private String remark;


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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAuthorityIdentity() {
		return authorityIdentity;
	}

	public void setAuthorityIdentity(String authorityIdentity) {
		this.authorityIdentity = authorityIdentity;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

