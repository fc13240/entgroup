/**     
 * @Project: adms-service  
 * @Title: AddressInfoDTO.java
 * @Description: TODO(用一句话描述该文件做什么)   
 * @Author: mqc
 * @Date: 2016-6-5 下午5:00:03   
 * @Version: V1.0     
 */
package com.entgroup.adms.dto;

/**
 * @author mxy
 * @ClassName: LabelDTO
 * @Description: 标签信息
 * @date 2017-03-26 11:36
 */
public class LabelDTO {

	/**
	 * 标签类型 1-场景，2-明星，3-物品，4-其他。
	 */
	private Integer labelType;

	/**
	 * 标签id
	 */
	private Long labelId;

	/**
	 * 标签名
	 */
	private String labelName;

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Integer getLabelType() {
		return labelType;
	}

	public void setLabelType(Integer labelType) {
		this.labelType = labelType;
	}
}
