/**     
 * @Project: adms-service  
 * @Title: AddressInfoDTO.java
 * @Description: TODO(用一句话描述该文件做什么)   
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 * @Version: V1.0     
 */
package com.entgroup.adms.dto;

import java.util.List;

/**
 * @author mxy
 * @ClassName: AdSlotDTO
 * @Description: 广告位信息
 * @date 2017-03-24 20:48
 */
public class AdSlotDTO {

	/**
	 * 入库时间
	 */
	private String created;

	/**
	 * 视频id
	 */
	private Long videoId;

	/**
	 * 视频名称
	 */
	private String videoName;

	/**
	 * 视频时间点(s)
	 */
	private String videoPosition;

	/**
	 * 画面所在服务器
	 */
	private String imageServer;

	/**
	 * 状态 0-无广告位，1-有广告位，2-广告位已有广告。
	 */
	private Integer status = 0;

	/**
	 * 标签集合
	 */
	private List<LabelDTO> labelDTOList;


	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}

	public String getImageServer() {
		return imageServer;
	}

	public void setImageServer(String imageServer) {
		this.imageServer = imageServer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<LabelDTO> getLabelDTOList() {
		return labelDTOList;
	}

	public void setLabelDTOList(List<LabelDTO> labelDTOList) {
		this.labelDTOList = labelDTOList;
	}

	//edited by mxy on 2017-03-29 15:59 begin
	/**
	 * 图片地址
	 */
	private String pictureAddress;

	/**
	 * 视频时间点(mm:ss)
	 */
	private String videoPositionTime;

	public String getPictureAddress() {
		return pictureAddress;
	}
	public void setPictureAddress(String pictureAddress) {
		this.pictureAddress = pictureAddress;
	}

	public String getVideoPositionTime() {
		return videoPositionTime;
	}

	public void setVideoPositionTime(String videoPositionTime) {
		this.videoPositionTime = videoPositionTime;
	}
	//edited by mxy on 2017-03-29 15:59 end
	//edited by mxy on 2017-03-30 10:05 begin
	/**
	 * 广告位id
	 */
	private Long adSlotId;

	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}
	//edited by mxy on 2017-03-30 10:06 end
	
	//edited by mxy on 2017-06-28 14:12 begin
	/**
	 * 广告ID
	 */
	private Long adId ;

	/**
	 * 订单ID
	 */
	private Long orderId ;

	/**
	 * 订单广告位关联ID
	 */
	private Long orderAdSlotPreviewId ;

	/**
	 * 关联是否已删除
	 */
	private Integer oaspDeleted;

	/**
	 * 节目id
	 */
	private Long programId;

	/**
	 * 节目名称
	 */
	private String programName;
	/**
	 * 节目等级id
	 */
	private Long programLevelId;

	/**
	 * 节目类型id
	 */
	private Long programTypeId;

	/**
	 * 节目类型名称
	 */
	private String programTypeName;

	/**
	 * 视频平台id
	 */
	private Long companyId;

	/**
	 * 视频平台名称
	 */
	private String companyName;

	/**
	 * 标签集合
	 */
	private String labels;

	/**
	 * 标签名集合
	 */
	private String[] labelNames;

	/**
	 * 视频广告位
	 */
	private List<VideoAdSlotDTO> videoAdSlot;

    /**
     * 演员
     */
    private String actors;

    /**
     * 预计播放量
     */
    private String showNum;

    /**
     * 昨日消费
     */
    private String expense;

    /**
     * 上映时间
     */
    private String showTime;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderAdSlotPreviewId() {
		return orderAdSlotPreviewId;
	}

	public void setOrderAdSlotPreviewId(Long orderAdSlotPreviewId) {
		this.orderAdSlotPreviewId = orderAdSlotPreviewId;
	}

	public Integer getOaspDeleted() {
		return oaspDeleted;
	}

	public void setOaspDeleted(Integer oaspDeleted) {
		this.oaspDeleted = oaspDeleted;
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public Long getProgramLevelId() {
		return programLevelId;
	}

	public void setProgramLevelId(Long programLevelId) {
		this.programLevelId = programLevelId;
	}

	public Long getProgramTypeId() {
		return programTypeId;
	}

	public void setProgramTypeId(Long programTypeId) {
		this.programTypeId = programTypeId;
	}

	public String getProgramTypeName() {
		return programTypeName;
	}

	public void setProgramTypeName(String programTypeName) {
		this.programTypeName = programTypeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String[] getLabelNames() {
		return labelNames;
	}

	public void setLabelNames(String[] labelNames) {
		this.labelNames = labelNames;
	}

	public List<VideoAdSlotDTO> getVideoAdSlot() {
		return videoAdSlot;
	}

	public void setVideoAdSlot(List<VideoAdSlotDTO> videoAdSlot) {
		this.videoAdSlot = videoAdSlot;
	}

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getShowNum() {
        return showNum;
    }

    public void setShowNum(String showNum) {
        this.showNum = showNum;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    //edited by mxy on 2017-06-28 14:13 end
}
