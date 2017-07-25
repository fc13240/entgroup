package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 订单
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_order")
public class AdOrder extends BaseObject {

    private static final long serialVersionUID = 1L;
	public static final int STATUS_ON=1;//进行中
	public static final int STATUS_COMPLETE=2;//交易完成

    /**
     * 订单编号(生成规则：companyId+userId+时间20170314012323)
     */
	private String id;
	@TableField("company_id")
	private Long companyId;
    /**
     * 广告总数
     */
	@TableField("ad_count")
	private Integer adCount;
    /**
     * 广告位总数
     */
	@TableField("slot_count")
	private Integer slotCount;
    /**
     * 总金额（元）
     */
	@TableField("total_money")
	private Integer totalMoney;
    /**
     * 消费金额,每天统计进行累计
     */
	@TableField("cosume_money")
	private Integer cosumeMoney;
    /**
     * 1-进行中，2-交易完成
     */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date created;
	/**
	 * 订单开始投放时间
	 */
	@TableField("begin_time")
	private Date beginTime;
	/**
	 * 订单结束投放时间
	 */
	@TableField("end_time")
	private Date endTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 订单名称
	 */
	@TableField("order_name")
	private String orderName;
	/**
	 * 监测代码
	 */
	@TableField("tracking_code")
	private String trackingCode;

	/**
	 * 0-未完成选择广告位，1-完成选择广告位
	 */
	private Integer selected;

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	@TableField(exist=false)
	private String adIds;

	// edited by xiaokun on 2017-06-28 15:20 begin
	@TableField(exist = false)
	private Long adId;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}
	// edited by xiaokun on 2017-06-28 15:20 end

	// edited by xiaokun on 2017-06-28 21:01 begin
	/**
	 * 剩余天数
	 */
	@TableField(exist = false)
	private Integer lastDate;
	/**
	 * 广告位信息
	 */
	@TableField(exist = false)
	private String slotCounts;
	/**
	 * 昨日消费
	 */
	@TableField(exist = false)
	private Double orderTotalprice;

	public Integer getLastDate() {
		return lastDate;
	}

	public void setLastDate(Integer lastDate) {
		this.lastDate = lastDate;
	}

	public String getSlotCounts() {
		return slotCounts;
	}

	public void setSlotCounts(String slotCounts) {
		this.slotCounts = slotCounts;
	}

	public Double getOrderTotalprice() {
		return orderTotalprice;
	}

	public void setOrderTotalprice(Double orderTotalprice) {
		this.orderTotalprice = orderTotalprice;
	}
	// edited by xiaokun on 2017-06-28 21:01 end

	// edited by xiaokun on 2017-06-28 23:24 begin
	@TableField(exist = false)
	private String manageCompanyIds;

	public String getManageCompanyIds() {
		return manageCompanyIds;
	}

	public void setManageCompanyIds(String manageCompanyIds) {
		this.manageCompanyIds = manageCompanyIds;
	}
	// edited by xiaokun on 2017-06-28 23:24 end

	// edited by xiaokun on 2017-06-28 21:07 begin
	/**
	 * 预投放广告位数量
	 */
	@TableField(exist = false)
	private Integer slotCountPreview;
	/**
	 * 广告位总数(预投放 + 已投放)
	 */
	@TableField(exist = false)
	private Integer slotCountAll;

	public Integer getSlotCountPreview() {
		return slotCountPreview;
	}

	public void setSlotCountPreview(Integer slotCountPreview) {
		this.slotCountPreview = slotCountPreview;
	}

	public Integer getSlotCountAll() {
		return slotCountAll;
	}

	public void setSlotCountAll(Integer slotCountAll) {
		this.slotCountAll = slotCountAll;
	}
	// edited by xiaokun on 2017-06-28 21:07 end

	// edited by qmh on 2017-06-30 09:15 begin
	/**
	 * 日期间隔
	 */
	@TableField(exist = false)
	private Integer dayPeriod;

	public Integer getDayPeriod() { return dayPeriod; }

	public void setDayPeriod(Integer dayPeriod) { this.dayPeriod = dayPeriod; }
	// edited by qmh on 2017-06-30 09:15 end

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdIds() {
		return adIds;
	}

	public void setAdIds(String adIds) {
		this.adIds = adIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getAdCount() {
		return adCount;
	}

	public void setAdCount(Integer adCount) {
		this.adCount = adCount;
	}

	public Integer getSlotCount() {
		return slotCount;
	}

	public void setSlotCount(Integer slotCount) {
		this.slotCount = slotCount;
	}

	public Integer getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Integer totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getCosumeMoney() {
		return cosumeMoney;
	}

	public void setCosumeMoney(Integer cosumeMoney) {
		this.cosumeMoney = cosumeMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


	public Date getBeginTime(){
		return beginTime;
	}
	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}

	public Date getEndTime(){
		return endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}


	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

}

