package com.entgroup.adms.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 
 * @ClassName: TreeNode 
 * @Description: 节点树
 * @author mengqch 
 * @date 2015-10-1
 */
public class TreeNode implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6398879603451465683L;

	/**
	 * 节点数据中保存唯一标识的属性名称
	 */
	private Integer id;

	/**
	 * 节点数据中保存其父节点唯一标识的属性名称
	 */
	private Integer pId;

	/**
	 * zTree 节点数据保存节点名称的属性名称
	 */
	private String name;

	/**
	 * zTree 节点的 checkBox / radio 的 勾选状态
	 */
	private boolean checked = Boolean.FALSE;

	/**
	 * zTree节点图标
	 */
	private String icon;

	/**
	 * zTree节点展开图标
	 */
	private String iconOpen;

	/**
	 * zTree节点折叠图标
	 */
	private String iconClose;

	/**
	 * 是否处于开启状态
	 */
	private boolean open = Boolean.FALSE;

	public TreeNode() {
	}

	public TreeNode(Integer id, Integer pId, String name) {
		this.id = id;
		this.pId = pId;
		this.name = name;
	}

	//edited by mxy on 2017-03-23 09:15 begin
	public TreeNode(Integer id, Integer pId, String name, boolean checked) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
	}
	public TreeNode(Integer id, Integer pId, String name, boolean checked, boolean open) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.checked = checked;
		this.open = open;
	}
	//edited by mxy on 2017-03-23 09:15 end
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconOpen() {
		return iconOpen;
	}

	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	public String getIconClose() {
		return iconClose;
	}

	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}