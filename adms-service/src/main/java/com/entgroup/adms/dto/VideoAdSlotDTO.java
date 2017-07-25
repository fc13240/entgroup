package com.entgroup.adms.dto;

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
public class VideoAdSlotDTO {

	/**
	 * 广告ID
	 */
	private Long adId ; 
	
	/**
	 * 广告位ID
	 */
	private Long adSlotId ;
	
	/**
	 * 广告位视频时间点
	 */
	private String videoPosition;
	
	/**
	 * 广告名称
	 */
	private String adName;
	//edited by liuxl on 2017/4/20 begin
	/**
	 * 广告位标签
	 */
	private List<SlotLabel> slotLabels;
	
	/**
	 * 广告位场景
	 */
	private List<Scene> scenes;
	
	/**
	 * 广告位明星
	 */
	private List<Person> persons;
	
	
	public List<SlotLabel> getSlotLabels() {
		return slotLabels;
	}

	public void setSlotLabels(List<SlotLabel> slotLabels) {
		this.slotLabels = slotLabels;
	}

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
	//edited by liuxl on 2017/4/20 end
	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}

	public String getVideoPosition() {
		return videoPosition;
	}

	public void setVideoPosition(String videoPosition) {
		this.videoPosition = videoPosition;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	
}
