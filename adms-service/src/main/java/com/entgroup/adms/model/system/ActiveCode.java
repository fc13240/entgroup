package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import javax.persistence.Transient;


/**
 * <p>
 * 激活验证表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_active_code")
public class ActiveCode extends BaseObject {

	@Transient
    private static final long serialVersionUID = 1L;
	@TableField
	private Long id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 验证码
     */
	@TableField("active_code")
	private String activeCode;
    /**
     * 验证类型：1-邮件,2-手机
     */
	private Integer type;
    /**
     * 验证码是否已使用
     */
	@TableField("used")
	private Integer used;
    /**
     * 过期时间
     */
	@TableField("expires_time")
	private Date expiresTime;
	@TableField("create_time")
	private Date createTime;


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

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Date getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

