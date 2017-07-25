package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 广告样式表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_style")
public class AdStyle extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private BigDecimal price;
	@TableField("price_unit")
	private Integer priceUnit;
	
	

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(Integer priceUnit) {
		this.priceUnit = priceUnit;
	}

}

