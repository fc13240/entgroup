package com.entgroup.adms.dto;


/**
 * @author liuxiaolong
 * @ClassName: AdSlotSearchDTO
 * @Description: 广告投放广告位的查询下拉框
 * @date 2017-04-20
 */
public class AdSlotSearchDTO {

	/**
	 * 搜索框类型
	 */
	private Integer searchType;
	
	/**
	 * 搜索框下拉ID
	 */
	private Integer id;
	
	/**
	 * 搜索框下拉名称
	 */
	private String name;

	public Integer getSearchType() {
		return searchType;
	}

	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}

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
	
	
}
