package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.model.BaseObject;

import java.util.List;


/**
 * <p>
 * 场景表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_scene")
public class Scene extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	@TableId
	private Long id;
    /**
     * 场景名称
     */
	@TableField
	private String name;
    /**
     * 场景标识
     */
	@TableField("scene_identify")
	private String sceneIdentify;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 205分类源id
     */
	@TableField("source_id")
	private String sourceId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSceneIdentify() {
		return sceneIdentify;
	}

	public void setSceneIdentify(String sceneIdentify) {
		this.sceneIdentify = sceneIdentify;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	//edited by mxy on 2017-03-22 09:31 begin
	/**
	 * 广告位合集
	 */
	@TableField(exist = false)
	private List<AdSlot> adSlots;

	public List<AdSlot> getAdSlots() {
		return adSlots;
	}

	public void setAdSlots(List<AdSlot> adSlots) {
		this.adSlots = adSlots;
	}
	//edited by mxy on 2017-03-22 09:33 end
	//edited by mxy on 2017-03-26 11:01 begin
	/**
	 * 场景广告位合集
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
	//edited by mxy on 2017-04-21 11:03 begin
	/**
	 * 场景广告位合集
	 */
	@TableField(exist = false)
	private List<Scene> scenes;

	public List<Scene> getScenes() {
		return scenes;
	}

	public void setScenes(List<Scene> scenes) {
		this.scenes = scenes;
	}
	//edited by mxy on 2017-04-21 11:04 end
}

