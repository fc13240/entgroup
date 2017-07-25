package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;

/**
 * <p>
 * 日曝光统计
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_display_count")
public class AdDisplayCount extends BaseObject {

	private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("order_id")
	private String orderId;
	/**
	 * 广告id
	 */
	@TableField("ad_id")
	private Long adId;

	/**
	 * 对应节目id
	 */
	@TableField("program_id")
	private Long programId;
	/**
	 * 曝光量
	 */
	@TableField("show_count")
	private Integer showCount;
	/**
	 * 曝光人数
	 */
	@TableField("user_count")
	private Integer userCount;
	/**
	 * 曝光日期（yyyy-MM-DD）
	 */
	@TableField("day_time")
	private Date dayTime;
	/**
	 * 统计时间
	 */
	private Date created;

	/**
	 *公司id
	 */
	@TableField(exist = false)
	private long companyId;

	/**
	 * 昨天日期
	 */
	@TableField(exist = false)
	private String beforeDay;

	/**
	 * 当前日期
	 */
	@TableField(exist = false)
	private String nowDay;

	/**
	 * 页数
	 */
	@TableField(exist = false)
	private Integer pageNum;

	/**
	 * 订单名称
	 */
	@TableField(exist = false)
	private String orderName;

	/**
	 * 曝光周期
	 */
	@TableField(exist = false)
	private Integer dayPeriod;


	// edited by quminghui on 2017-06-13 15:54 begin
	/**
	 * 点击次数
	 */
	@TableField("click_count")
	private Integer clickCount;

	/**
	 * 用户id
	 */
	private  Integer userId;
	// edited by quminghui on 2017-06-13 15:54 end

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

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
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

	public Integer getShowCount() {
		return showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}

	public Date getDayTime() {
		return dayTime;
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public Integer getUserId() { return userId; }

	public void setUserId(Integer userId) { this.userId = userId; }

	public long getCompanyId() {return companyId;}

	public void setCompanyId(long companyId) {this.companyId = companyId; }

	public String getBeforeDay() {return beforeDay; }

	public void setBeforeDay(String beforeDay) {this.beforeDay = beforeDay; }

	public String getNowDay() {return  nowDay; }

	public void setNowDay(String nowDay) {this.nowDay = nowDay; }

	public Integer getPageNum() {return pageNum;}

	public void  setPageNum(Integer pageNum) {this.pageNum = pageNum;}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Integer getDayPeriod() { return dayPeriod;}

	public void setDayPeriod(Integer dayPeriod) { this.dayPeriod = dayPeriod; }

	//edited by mxy on 2017-04-19 16:38 end
	@TableField(exist = false)
	private AdStyle adStyle;

	@TableField(exist = false)
	private Company company;

	/**
	 * 统计数据ids
	 */
	@TableField(exist = false)
	private String ids;

	public AdStyle getAdStyle() {
		return adStyle;
	}

	public void setAdStyle(AdStyle adStyle) {
		this.adStyle = adStyle;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
