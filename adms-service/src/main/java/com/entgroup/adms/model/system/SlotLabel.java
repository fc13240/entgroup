package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 广告位标签
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_slot_label")
public class SlotLabel extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	@TableField("parent_id")
	private Long parentId;


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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}

