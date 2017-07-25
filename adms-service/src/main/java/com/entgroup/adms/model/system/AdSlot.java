package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 广告位
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_slot")
public class AdSlot extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 视频id
     */
	@TableField("video_id")
	private Long videoId;
    /**
     * 视频时间点(秒)
     */
	@TableField("video_position")
	private Integer videoPosition;
	/**
     * 对应场景id
     */
	@TableField("scene_ids")
	private String sceneIds="0";
	/**
     * 对应多个personId,以逗号分隔
     */
	@TableField("person_ids")
	private String personIds="0";
	/**
     * 广告id
     */
	@TableField("ad_id")
	private Long adId=0L;
	/**
	 * 订单id
	 */
	@TableField("order_id")
	private String orderId="0";
	/**
     * 物品id,以逗号分隔
     */
	@TableField("object_ids")
	private String objectIds="0";
	/**
	 * 标签id,以逗号分隔
	 */
	@TableField("slot_label_ids")
	private String slotLabelIds="0";
	//edited by mxy on 2017-03-21 20:38 begin
	/**
	 * 用于记录初始标签，格式如：0,10
	 */
	@TableField("initial_label")
	private String initialLabel="0";
	/**
	 * 创建时间
	 */
	private Date created;

	public String getInitialLabel() {
		return initialLabel;
    }

	public void setInitialLabel(String initialLabel) {
		this.initialLabel = initialLabel;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	//edited by mxy on 2017-03-21 20:40 end
	//edited by mxy on 2017-04-14 15:37 begin
	/**
	 * 图片所在服务器
	 */
	@TableField("image_server")
	private String imageServer;

	public String getImageServer() {
		return imageServer;
	}

	public void setImageServer(String imageServer) {
		this.imageServer = imageServer;
	}
	//edited by mxy on 2017-04-14 15:37 end

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Integer getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(Integer videoPosition) {
		this.videoPosition = videoPosition;
	}

	public String getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(String sceneIds) {
		this.sceneIds = sceneIds;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(String objectIds) {
		this.objectIds = objectIds;
	}

	public String getSlotLabelIds() {
		return slotLabelIds;
	}

	public void setSlotLabelIds(String slotLabelIds) {
		this.slotLabelIds = slotLabelIds;
	}

	//edited by mxy on 2017-03-22 09:35 begin
	/**
     * 所属视频
     */
    @TableField(exist = false)
    private Video video;

    /**
     * 所属场景
     */
    @TableField(exist = false)
    private Scene scene;
    /**
     * 包含广告
     */
    @TableField(exist = false)
    private Ad ad;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
    //edited by mxy on 2017-03-22 09:39 end
	//edited by mxy on 2017-03-27 11:40 begin
	/**
	 * 广告位标签
	 */
	@TableField(exist=false)
	private List<Scene> scenes;
	/**
	 * 广告位标签
	 */
	@TableField(exist=false)
	private List<Person> persons;
	/**
	 * 广告位标签
	 */
	@TableField(exist=false)
	private List<RecognitionObject> objects;
	/**
	 * 广告位标签
	 */
	@TableField(exist=false)
	private List<SlotLabel> slotLabels;

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<RecognitionObject> getObjects() {
		return objects;
	}

	public void setObjects(List<RecognitionObject> objects) {
		this.objects = objects;
	}

	public List<SlotLabel> getSlotLabels() {
		return slotLabels;
	}

	public void setSlotLabels(List<SlotLabel> slotLabels) {
		this.slotLabels = slotLabels;
	}
	//edited by mxy on 2017-03-27 11:48 end
}

