package com.entgroup.adms.dto;

import com.entgroup.adms.model.system.AdOrder;

/**
 * 
 * @author guofei
 * @ClassName: AdOrderListDTO.java
 * @Description: 订单列表
 * @date 2017-4-18
 */
public class AdOrderListDTO extends AdOrder {


	private static final long serialVersionUID = 1L;
	
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	
	
	
}
