package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 明星识别结果表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_person_recognition_result")
public class PersonRecognitionResult extends BaseObject {

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
     * 视频时间点,以逗号分隔
     */
	@TableField("video_position")
	private String videoPosition;
	@TableField("person_id")
	private Long personId;
    /**
     * 图片所在服务器
     */
	@TableField("image_server")
	private String imageServer;
    /**
     * 创建时间
     */
	private Date created;
    /**
     * 是否已挑选
     */
	private Integer selected;


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

	public String getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	//edited by mxy on 2017-03-20 20:52 begin
	/**
	 * 所属视频
	 */
	@TableField(exist = false)
	private Video video;

	/**
	 * 所属明星
	 */
	@TableField(exist = false)
	private Person person;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	//edited by mxy on 2017-03-20 20:52 end
}

