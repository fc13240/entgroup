package com.entgroup.adms.dto;

import com.entgroup.adms.model.BaseObject;

/**
 * Created by Administrator on 2017/6/29.
 */
public class DisplayCountResultDTO extends BaseObject {
    /**
     * 平台id
     */
    private Long platformId;
    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 节目类型id
     */
    private Long programTypeId;
    /**
     * 节目类型名称
     */
    private String programTypeName;
    /**
     * 线上广告位ids
     */
    private String slotOnline;
    /**
     * 曾用广告位ids
     */
    private String slotOffline;
    /**
     * 曝光量
     */
    private Double showCounts;
    /**
     * 点击量
     */
    private Double clickCounts;
    /**
     * 花费
     */
    private Double cash;

    // edited by qmh on 2017-06-30 06/30 begin

    /**
     * 总金额
     */
    private Double totalMoney;

    /**
     * 花费金额 -直接取用dispalyCount表中数据
     */
    private Double cosumeMoney;


    /**
     * 订单名称
     */
     private String orderName;

    /**
     * 开始时间
     */
     private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;

    public Double getTotalMoney() { return totalMoney ; }

    public void setTotalMoney(Double totalMoney) { this.totalMoney = totalMoney; }

    public Double getCosumeMoney() { return cosumeMoney ; }

    public void setCosumeMoney(Double cosumeMoney) { this.cosumeMoney = cosumeMoney; }

    public String getOrderName() { return orderName ; }

    public void setOrderName(String orderName) { this.orderName = orderName; }

    public String getBeginTime() { return beginTime ; }

    public void setBeginTime(String beginTime) { this.beginTime = beginTime; }

    public String getEndTime() { return endTime ; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    // edited by qmh on 2017-06-30 06/30 end

    public Long getPlatformId() {
        return platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public Long getProgramTypeId() {
        return programTypeId;
    }

    public String getProgramTypeName() {
        return programTypeName;
    }

    public String getSlotOnline() {
        return slotOnline;
    }

    public String getSlotOffline() {
        return slotOffline;
    }

    public Double getShowCounts() {
        return showCounts;
    }

    public Double getClickCounts() {
        return clickCounts;
    }

    public Double getCash() {
        return cash;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public void setProgramTypeId(Long programTypeId) {
        this.programTypeId = programTypeId;
    }

    public void setProgramTypeName(String programTypeName) {
        this.programTypeName = programTypeName;
    }

    public void setSlotOnline(String slotOnline) {
        this.slotOnline = slotOnline;
    }

    public void setSlotOffline(String slotOffline) {
        this.slotOffline = slotOffline;
    }

    public void setShowCounts(Double showCounts) {
        this.showCounts = showCounts;
    }

    public void setClickCounts(Double clickCounts) {
        this.clickCounts = clickCounts;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    /**
     * 显示用参数
     *     ctr
     */
    private Double ctr;
    /**
     * 显示用参数
     *     slotCount
     */
    private Integer slotCount;

    public Double getCtr() {
        return ctr;
    }

    public void setCtr(Double ctr) {
        this.ctr = ctr;
    }

    public Integer getSlotCount() {
        return slotCount;
    }

    public void setSlotCount(Integer slotCount) {
        this.slotCount = slotCount;
    }

    /**
     * slot_ids初级查询获取/获取字段
     *     slotIds
     */
    private String slotIds;
    /**
     * slot_ids初级查询获取字段
     *     orderId
     */
    private String orderId;
    /**
     * slot_ids初级查询获取字段
     *     orderStatus
     */
    private Integer orderStatus;
    /**
     * slot_ids初级查询获取字段
     *     companyId
     */
    private Long companyId;
    /**
     * slot_ids初级查询获取字段
     *     groupBy
     */
    private String groupBy;

    public String getSlotIds() {
        return slotIds;
    }

    public void setSlotIds(String slotIds) {
        this.slotIds = slotIds;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }
}
