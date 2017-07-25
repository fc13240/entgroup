package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.model.BaseObject;

import java.util.List;


/**
 * <p>
 * 明星表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_person")
public class Person extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 名称
     */
	private String name;
    /**
     * 源id
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
	//edited by mxy on 2017-03-26 15:26 begin
	/**
	 * 明星广告位合集
	 */
	@TableField(exist = false)
	private List<AdSlotDTO> adSlotDTOList;

	public List<AdSlotDTO> getAdSlotDTOList() {
		return adSlotDTOList;
	}

	public void setAdSlotDTOList(List<AdSlotDTO> adSlotDTOList) {
		this.adSlotDTOList = adSlotDTOList;
	}
	//edited by mxy on 2017-03-26 15:27 end
}

