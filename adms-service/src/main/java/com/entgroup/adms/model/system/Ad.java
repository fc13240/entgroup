package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 广告表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad")
public class Ad extends BaseObject {

    public static final int STATUS_WAITING_PASS=1;//待审核
    public static final int STATUS_PASSED=2;//已审核
    public static final int STATUS_NOT_PASSED=3;//未通过
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 广告名称
     */
    @TableField("name")
    private String name;
    /**
     * 广告链接
     */
    @TableField("link")
    private String link;

    /**
     * 移动端广告链接
     */
    @TableField("link_mobile")
    private String linkMobile;

    /**
     * 广告图片路径
     */
    @TableField("image_path")
    private String imagePath;
    /**
     * 对应企业id
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 1-已关联订单,0-未关联订单
     */
    @TableField("order_online")
    private Integer orderOnline;
    /**
     * 创建人id
     */
    @TableField("creator_id")
    private Long creatorId;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 开始投放时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 结束投放时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 投放优先级
     */
    private Integer priority;
    /**
     * 广告类型
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 审核状态,1-待审核,2-已审核,3-未通过
     */
    private Integer status;
    /**
     * 更新时间即审核时间
     */
    @TableField("review_time")
    private Date reviewTime;
    /**
     * 广告样式
     */
    @TableField("style_id")
    private Long styleId;

    // edited by xiaokun on 2017-06-12 18:15 begin
    /**
     * 监测代码
     */
    @TableField("tracking_code")
    private String trackingCode;
    // edited by xiaokun on 2017-06-12 18:15 end

    //add by sunzhen 2017/4/14
    //非持久
    @TableField(exist = false)
    private Long videoId;
    @TableField(exist = false)
    private Integer showTime;
    @TableField(exist = false)
    private Long slotId;

    //edited by mxy on 2017-04-19 14:57 begin
    @TableField(exist = false)
    private AdStyle adStyle;

    @TableField(exist = false)
    private Company company;

    @TableField(exist = false)
    private OrderAd orderAd;

    /**
     * 统计数据ids
     */
    @TableField(exist = false)
    private String addcIds;

    /**
     * 曝光量
     */
    @TableField(exist = false)
    private Integer showCount;

    /**
     * 曝光人数
     */
    @TableField(exist = false)
    private Integer userCount;

    // edited by xiaokun on 2017-06-28 11:46 begin
    @TableField(exist = false)
    private String createdString;

    public String getCreatedString() {
        return createdString;
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }
    // edited by xiaokun on 2017-06-28 11:46 end

    // edited by xiaokun on 2017-06-28 17:21 begin
    @TableField(exist = false)
    private String adOrderId;

    public String getAdOrderId() {
        return adOrderId;
    }

    public void setAdOrderId(String adOrderId) {
        this.adOrderId = adOrderId;
    }
    // edited by xiaokun on 2017-06-28 17:21 end

    // edited by xiaokun on 2017-06-29 11:07 begin
    @TableField(exist = false)
    private String adOrderName;

    public String getAdOrderName() {
        return adOrderName;
    }

    public void setAdOrderName(String adOrderName) {
        this.adOrderName = adOrderName;
    }
    // edited by xiaokun on 2017-06-29 11:07 end

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setShowTime(Integer showTime) {
        this.showTime = showTime;
    }

    public Integer getShowTime() {
        return showTime;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getSlotId() {
        return slotId;
    }

    //add end
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkMobile() {
        return linkMobile;
    }

    public void setLinkMobile(String linkMobile) {
        this.linkMobile = linkMobile;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getOrderOnline() {
        return orderOnline;
    }

    public void setOrderOnline(Integer orderOnline) {
        this.orderOnline = orderOnline;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

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

    public OrderAd getOrderAd() {
        return orderAd;
    }

    public void setOrderAd(OrderAd orderAd) {
        this.orderAd = orderAd;
    }

    public String getAddcIds() {
        return addcIds;
    }

    public void setAddcIds(String addcIds) {
        this.addcIds = addcIds;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
    //edited by mxy on 2017-04-19 16:37 end

    // edited by xiaokun on 2017-06-12 18:16 begin
    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
    // edited by xiaokun on 2017-06-12 18:16 end
}

