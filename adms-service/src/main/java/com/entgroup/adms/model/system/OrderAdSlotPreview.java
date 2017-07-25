package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;
import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author cuixiaokun
 * @since 2017-06-28
 */
@TableName("sys_order_ad_slot_preview")
public class OrderAdSlotPreview extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 订单与广告位预览表
     */
	private Long id;
    /**
     * 关联订单
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 关联广告位
     */
	@TableField("ad_slot_id")
	private Long adSlotId;
    /**
     * 关联是否已删除
     */
	private Integer deleted;
    /**
     * 关联是否已使用
     */
	private Integer used;


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

	public Long getAdSlotId() {
		return adSlotId;
	}

	public void setAdSlotId(Long adSlotId) {
		this.adSlotId = adSlotId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

}
