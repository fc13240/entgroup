package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_type")
public class AdType extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 广告类型名
     */
	private String name;


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

}

