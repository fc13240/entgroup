package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.math.BigDecimal;


/**
 * <p>
 * 节目等级表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_program_level")
public class ProgramLevel extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
    /**
     * 价格（人民币）
     */
	private BigDecimal price;


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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}

