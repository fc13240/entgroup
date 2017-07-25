package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 视频类型
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_program_type")
public class ProgramType extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Integer id;
    /**
     * 分类标识码
     */
	private String code;
    /**
     * 分类名称
     */
	private String name;
    /**
     * 父类型id
     */
	@TableField("parent_id")
	private Integer parentId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	//edited by mxy on 2017-06-16 17:02 begin
	/**
	 * 选择标记
	 */
	@TableField(exist = false)
	private Boolean selected = false;

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	//edited by mxy on 2017-06-16 17:03 end
}

