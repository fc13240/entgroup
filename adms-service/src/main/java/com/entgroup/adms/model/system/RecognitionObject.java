package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 物品表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_object")
public class RecognitionObject extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 物品名称
     */
	private String name;
    /**
     * 物品标识
     */
	@TableField("object_identify")
	private String objectIdentify;
    /**
     * 父类id
     */
	@TableField("parent_id")
	private Long parentId;
    /**
     * 物品分类源id
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

	public String getObjectIdentify() {
		return objectIdentify;
	}

	public void setObjectIdentify(String objectIdentify) {
		this.objectIdentify = objectIdentify;
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

}

