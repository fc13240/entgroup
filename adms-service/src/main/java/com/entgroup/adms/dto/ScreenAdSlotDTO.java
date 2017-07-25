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
 * @ClassName: ScreenAdSlotDTO
 * @Description: 广告位筛选信息
 * @date 2017-03-23 15:19
 */
public class ScreenAdSlotDTO {

	/**
	 * 视频id
	 */
	private Long videoId;

	/**
	 * 入库时间
	 */
	private String created;

	/**
	 * 视频平台
	 */
	private String videoPlatform;

	/**
	 * 视频名称
	 */
	private String videoName;

	/**
	 * 视频类型
	 */
	private String videoType;

	/**
	 * 画面数（已挑选/总数）
	 */
	private String imagesNum;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 是否已筛选
	 */
	private Integer statusSelect;

	/**
	 * 平台id
	 */
	private Long companyId;

	/**
	 * 类型id
	 */
	private Long typeId;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getVideoPlatform() {
		return videoPlatform;
	}

	public void setVideoPlatform(String videoPlatform) {
		this.videoPlatform = videoPlatform;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getImagesNum() {
		return imagesNum;
	}

	public void setImagesNum(String imagesNum) {
		this.imagesNum = imagesNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusSelect() {
		return statusSelect;
	}

	public void setStatusSelect(Integer statusSelect) {
		this.statusSelect = statusSelect;
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
