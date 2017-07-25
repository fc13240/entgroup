package com.entgroup.adms.model.system;

import java.util.Date;
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
 * @since 2017-06-29
 */
@TableName("sys_order_finished_info")
public class OrderFinishedInfo extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 已完成订单信息记录表
     */
	private Long id;
    /**
     * 订单id
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 0-无广告位，1,2,3,4...表示关联1,2,3,4广告位
     */
	@TableField("ad_slot_ids")
	private String adSlotIds;
    /**
     * 0-未删除，1-已删除
     */
	private Integer deleted;
    /**
     * 入库时间
     */
	@TableField("entry_time")
	private Date entryTime;


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

	public String getAdSlotIds() {
		return adSlotIds;
	}

	public void setAdSlotIds(String adSlotIds) {
		this.adSlotIds = adSlotIds;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

}
