/**     
 * @Project: adms-service  
 * @Title: AddressInfoDTO.java
 * @Description: TODO(用一句话描述该文件做什么)   
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 * @Version: V1.0     
 */
package com.entgroup.adms.dto;

/**
 * @author mxy
 * @ClassName: AdjustPriceDTO
 * @Description: 广告位价格信息
 * @date 2017-03-26 16:50
 */
public class AdjustPriceDTO {

	/**
	 * 视频id
	 */
	private Long programId;

	/**
	 * 入库时间
	 */
	private String created;

	/**
	 * 视频平台
	 */
	private String programPlatform;

	/**
	 * 视频名称
	 */
	private String programName;

	/**
	 * 视频类型
	 */
	private String programType;

	/**
	 * 集数
	 */
	private Integer videoNum;

	/**
	 * 广告位数量
	 */
	private Integer adSlotNum;

	/**
	 * 价位
	 */
	private String price;

	/**
	 * 价位等级id
	 */
	private Integer levelId;

	/**
	 * 平台id
	 */
	private Long companyId;

	/**
	 * 类型id
	 */
	private Long typeId;

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getProgramPlatform() {
		return programPlatform;
	}

	public void setProgramPlatform(String programPlatform) {
		this.programPlatform = programPlatform;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public Integer getAdSlotNum() {
		return adSlotNum;
	}

	public void setAdSlotNum(Integer adSlotNum) {
		this.adSlotNum = adSlotNum;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
