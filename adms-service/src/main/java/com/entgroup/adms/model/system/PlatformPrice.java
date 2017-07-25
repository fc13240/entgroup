package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 
 * </p>
 *
 * @author MaxNull
 * @since 2017-05-05
 */
@TableName("sys_platform_price")
public class PlatformPrice extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 价位系数
     */
	@TableField("price_factor")
	private Double priceFactor;
    /**
     * 平台id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 节目等级id
     */
	@TableField("level_id")
	private Integer levelId;
    /**
     * 是否删除（1-是，0-否，默认为0）
     */
	private Integer deleted;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPriceFactor() {
		return priceFactor;
	}

	public void setPriceFactor(Double priceFactor) {
		this.priceFactor = priceFactor;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
