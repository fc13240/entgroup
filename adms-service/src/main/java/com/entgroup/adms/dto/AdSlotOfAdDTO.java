package com.entgroup.adms.dto;

import java.util.Date;
import java.util.List;

import com.entgroup.adms.model.system.Person;
import com.entgroup.adms.model.system.Scene;
import com.entgroup.adms.model.system.SlotLabel;

/**
 * @author liuxiaolong
 * @ClassName: AdSlotOfAdDTO
 * @Description: 广告信息
 * @date 2017-03-28
 */
public class AdSlotOfAdDTO {
    
	
	/**
	 * 广告位ID
	 */
	private Long adSlotId;
	
	/**
	 * 广告ID
	 */
	private Long adId ;
	
	/**
	 * 视频ID
	 */
	private Long videoId;
	
	/**
	 * 广告名字
	 */
    private String adName;	
	
	/**
	 * 广告位视频时间点
	 */
	private String videoPosition;
	
	/**
	 * 视频名字
	 */
	private String videoName;
	
	/**
	 * 视频平台
	 */
	private String videoPlatform;
	
	/**
	 * 节目类型
	 */
	private String programType;
	
	/**
	 * 视频投放时间
	 */
	private Date startTime ;
	
	/**
	 * 广告订单公司名称
	 */
	private String orderCompany;
	
	/**
	 * 广告样式名称
	 */
	private String styleName;
	
	/**
	 * 广告类型名称
	 */
	private String typeName ;	
	
	/**
	 * 视频广告位
	 */
	private List<VideoAdSlotDTO> videoAdSlot;
    
    
  	//edited by liuxiaolong on 2017/4/15 begin
  	private String pictureAdress ;
  	

	public String getPictureAdress() {
		return pictureAdress;
	}

	public void setPictureAdress(String pictureAdress) {
		this.pictureAdress = pictureAdress;
	}
    
	//edited by liuxiaolong on 2017/4/15 end
	
	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	
	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoPlatform() {
		return videoPlatform;
	}

	public void setVideoPlatform(String videoPlatform) {
		this.videoPlatform = videoPlatform;
	}

	public String getProgramType() {
		return programType;
	}

	public void setProgramType(String programType) {
		this.programType = programType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getOrderCompany() {
		return orderCompany;
	}

	public void setOrderCompany(String orderCompany) {
		this.orderCompany = orderCompany;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<VideoAdSlotDTO> getVideoAdSlot() {
		return videoAdSlot;
	}

	public void setVideoAdSlot(List<VideoAdSlotDTO> videoAdSlot) {
		this.videoAdSlot = videoAdSlot;
	}

	
	
	
	
}
