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
@TableName("sys_order_program_preview")
public class OrderProgramPreview extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 订单选择节目记录表
     */
	private Long id;
    /**
     * 关联订单
     */
	@TableField("order_id")
	private String orderId;
    /**
     * 关联节目
     */
	@TableField("program_id")
	private Long programId;
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

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

}
