package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_user_role")
public class UserRole extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 用户id，对应sys_user表id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 角色id，对应sys_role表id
     */
	@TableField("role_id")
	private Integer roleId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}

