package com.entgroup.adms.dto;

import java.util.Date;
/**
 * 
 * @author guofei
 * @ClassName: DisplayCountDTO.java
 * @Description: 统计模块对象
 * @date 2017-4-18
 */
public class DisplayCountDTO {

	private String id;
	private String orderId;
	private String adOrderId;
	private Long adId;
	private Long programId;
	private Long adStyleId;
	private Long companyId;
	private String companyName;
	private String adName;
	private String adStyleName;
	private String orderName; // 订单名称
	private Integer slotCount; // 广告位总数
	private String programName; // 节目类型名称
	//公司统计量  统计主页
	private Integer userCount;
	private Integer showCount;
	private Integer clickCount;
	private String beginTime;
	private String endTime;
	private Integer totalMoney;
	private Integer cosumeMoney;
	
	private String mUserCount;
	private String mShowCount;
	private Long lUserCount;
	private Long lShowCount;
	private Date dayTime;
	private Date dateTime;
	private Long countId;
	private Long slotId;
	//公司在平台的统计量
	private Integer totalUserCount;
	private Integer totalShowCount;
	private Double dUserCount;
	private Double dShowCount;
	
	//订单统计前一天消费金额
	private Double orderTotalPrice;
	//颜色
	private String color;
	//折线图曝光量
	private String sumShowCount;
	//折线图用户量
	private String sumUserCount;
	//点击量
	private String sumClickCount;
	//消费金额
	private String sumCosumeMoney;
	//总计金额
	private String sumTotalMoney;
	//ctr
	private String ctr;

	public String getId() {return  id;}
	public void setId(String id) { this.id = id;}
	public String getAdOrderId() {
		return adOrderId;
	}
	public void setAdOrderId(String adOrderId) {
		this.adOrderId = adOrderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Long getlUserCount() {
		return lUserCount;
	}
	public void setlUserCount(Long lUserCount) {
		this.lUserCount = lUserCount;
	}
	public Long getlShowCount() {
		return lShowCount;
	}
	public void setlShowCount(Long lShowCount) {
		this.lShowCount = lShowCount;
	}
	public String getmUserCount() {
		return mUserCount;
	}
	public void setmUserCount(String mUserCount) {
		this.mUserCount = mUserCount;
	}
	public String getmShowCount() {
		return mShowCount;
	}
	public void setmShowCount(String mShowCount) {
		this.mShowCount = mShowCount;
	}
	public Long getCountId() {
		return countId;
	}
	public void setCountId(Long countId) {
		this.countId = countId;
	}
	public Long getSlotId() {
		return slotId;
	}
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	public String getProgramName() { return programName; }
	public void setProgramName(String programName) { this.programName = programName;}
	public Integer getSlotCount() { return slotCount; }
	public void setSlotCount(Integer slotCount) { this.slotCount = slotCount; }
	public Double getdUserCount() {
		return dUserCount;
	}
	public void setdUserCount(Double dUserCount) {
		this.dUserCount = dUserCount;
	}
	public Double getdShowCount() {
		return dShowCount;
	}
	public void setdShowCount(Double dShowCount) {
		this.dShowCount = dShowCount;
	}
	public String getSumShowCount() {
		return sumShowCount;
	}
	public void setSumShowCount(String sumShowCount) {
		this.sumShowCount = sumShowCount;
	}
	public String getSumUserCount() {
		return sumUserCount;
	}
	public void setSumUserCount(String sumUserCount) {
		this.sumUserCount = sumUserCount;
	}
	public String getSumClickCount() {return sumClickCount;}
	public void setSumClickCount(String sumClickCount) {this.sumClickCount = sumClickCount;}
	public String getSumCosumeMoney() { return sumCosumeMoney; }
	public void setSumCosumeMoney(String sumCosumeMoney) { this.sumCosumeMoney = sumCosumeMoney; }
	public String getSumTotalMoney() { return sumTotalMoney;}
	public void setSumTotalMoney(String sumTotalMoney) { this.sumTotalMoney = sumTotalMoney;}
	public String getCtr() { return ctr; }
	public void setCtr(String ctr) { this.ctr = ctr; }
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getAdStyleId() {
		return adStyleId;
	}
	public void setAdStyleId(Long adStyleId) {
		this.adStyleId = adStyleId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdStyleName() {
		return adStyleName;
	}
	public void setAdStyleName(String adStyleName) {
		this.adStyleName = adStyleName;
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Integer getShowCount() {
		return showCount;
	}
	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}
	public Integer getClickCount() {
		return clickCount;
	}
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getTotalMoney() {return  totalMoney;}
	public void setTotalMoney(Integer totalMoney){this.totalMoney = totalMoney;}
	public Integer getCosumeMoney() {return cosumeMoney;}
	public void setCosumeMoney(Integer cosumeMoney){this.cosumeMoney = cosumeMoney;}

	public Date getDayTime() {
		return dayTime;
	}
	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}
	public Integer getTotalUserCount() {
		return totalUserCount;
	}
	public void setTotalUserCount(Integer totalUserCount) {
		this.totalUserCount = totalUserCount;
	}
	public Integer getTotalShowCount() {
		return totalShowCount;
	}
	public void setTotalShowCount(Integer totalShowCount) {
		this.totalShowCount = totalShowCount;
	}
	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}
	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	@Override
	public String toString() {
		return "DisplayCountDTO [totalMoney="+ totalMoney +"cosumeMoney="+cosumeMoney+"beginTime="+ beginTime +"endTime="+endTime+"clickCount=" + clickCount +" orderId=" + orderId + ", adOrderId=" + adOrderId + ", adId=" + adId + ", programId="
				+ programId + ", adStyleId=" + adStyleId + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", adName=" + adName + ", adStyleName=" + adStyleName + ", userCount=" + userCount + ", showCount="
				+ showCount + ", mUserCount=" + mUserCount + ", mShowCount=" + mShowCount + ", lUserCount=" + lUserCount
				+ ", lShowCount=" + lShowCount + ", dayTime=" + dayTime + ", dateTime=" + dateTime + ", countId="
				+ countId + ", slotId=" + slotId + ", totalUserCount=" + totalUserCount + ", totalShowCount="
				+ totalShowCount + ", dUserCount=" + dUserCount + ", dShowCount=" + dShowCount + ", orderTotalPrice="
				+ orderTotalPrice + ", color=" + color + ", sumShowCount=" + sumShowCount + ", sumUserCount="
				+ sumUserCount + "]";
	}

	

	
	
	
	
	
}
