package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_role_authority")
public class RoleAuthority extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Integer id;
    /**
     * 角色id，对应sys_role表id
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 权限id
     */
	@TableField("authority_id")
	private Integer authorityId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

}

