package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 场景识别结果表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_scene_recognition_result")
public class SceneRecognitionResult extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 视频id
     */
	@TableField("video_id")
	private Long videoId;
    /**
     * 识别场景id
     */
	@TableField("scene_id")
	private Long sceneId;
    /**
     * 时间点,以逗号分隔
     */
	@TableField("video_position")
	private String videoPosition;
    /**
     * 是否已挑选 
     */
	private Integer selected;
    /**
     * 图片所在服务器
     */
	@TableField("image_server")
	private String imageServer;
    /**
     * 创建时间
     */
	private Date created;


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

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public String getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public String getImageServer() {
		return imageServer;
	}

	public void setImageServer(String imageServer) {
		this.imageServer = imageServer;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	//edited by mxy on 2017-03-20 20:06 begin
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
	//edited by mxy on 2017-03-20 20:07 end
	//edited by mxy on 2017-03-26 11:01 begin
	/**
	 * 待筛选场景合集
	 */
	@TableField(exist = false)
	private List<AdSlotDTO> adSlotDTOList;

	public List<AdSlotDTO> getAdSlotDTOList() {
		return adSlotDTOList;
	}

	public void setAdSlotDTOList(List<AdSlotDTO> adSlotDTOList) {
		this.adSlotDTOList = adSlotDTOList;
	}
	//edited by mxy on 2017-03-26 11:10 end
}

