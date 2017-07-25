package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

/**
 * <p>
 * 订单广告表
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_order_ad")
public class OrderAd extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("order_id")
	private String orderId;
	@TableField("ad_id")
	private Long adId;

	/**
	 * 关联是否已删除
	 */
	private Integer deleted;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
