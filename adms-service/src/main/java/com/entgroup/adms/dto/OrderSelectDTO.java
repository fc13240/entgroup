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
public class OrderSelectDTO {

	/**
	 * 视频id
	 */
	private Long programId;

	/**
	 * 上映时间
	 */
	private String showTime;

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
	 * 视频播放数
	 */
	private Integer playNum;
	/**
	 * 价位
	 */
	private String actors;

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

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
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

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
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

	//edited by mxy on 2017-06-16 17:02 begin
	/**
	 * 选择标记
	 */
	private Boolean selected = false;

	/**
	 * 所属订单关联ID
	 */
	private Long oppId;

	/**
	 * 广告位数
	 */
	private Integer adSlotNum;

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Long getOppId() {
		return oppId;
	}

	public void setOppId(Long oppId) {
		this.oppId = oppId;
	}

	public Integer getAdSlotNum() {
		return adSlotNum;
	}

	public void setAdSlotNum(Integer adSlotNum) {
		this.adSlotNum = adSlotNum;
	}
	//edited by mxy on 2017-06-16 17:03 end
}
