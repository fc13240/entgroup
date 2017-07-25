package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author ShiXinPeng
 * @since 2017-06-12
 */
@TableName("sys_video_play_record_note")
public class VideoPlayRecordNote extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	private Long id;
    /**
     * 视频id
     */
	@TableField("video_id")
	private Long videoId;
    /**
     * 该视频播放统计的总数
     */
	@TableField("play_record_count")
	private Integer playRecordCount;
    /**
     * 视频播放时间统计的集合 0,0,0…… 24个用逗号隔开的字符串 每位代表当前时间段的播放次数
     */
	private String times;
    /**
     * 备用字段1
     */
    @TableField("entry_time")
	private Date entryTime;
    /**
     * 备用字段2
     */
	private String spare2;


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

	public Integer getPlayRecordCount() {
		return playRecordCount;
	}

	public void setPlayRecordCount(Integer playRecordCount) {
		this.playRecordCount = playRecordCount;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	/*public Date getSpare1() {
		return spare1;
	}

	public void setSpare1(Date spare1) {
		this.spare1 = spare1;
	}*/

	// edited by xiaokun on 2017-06-22 16:21 begin
	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	// edited by xiaokun on 2017-06-22 16:21 end

	public String getSpare2() {
		return spare2;
	}

	public void setSpare2(String spare2) {
		this.spare2 = spare2;
	}

}
