package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_reason")
public class AdReason extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("ad_id")
	private Long adId;
	@TableField("reason_ids")
	private String reasonIds;
	@TableField("other_reason")
	private String otherReason;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getReasonIds() {
		return reasonIds;
	}

	public void setReasonIds(String reasonIds) {
		this.reasonIds = reasonIds;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

}

