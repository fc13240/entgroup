package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 节目表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_program")
public class Program extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
	private Long id;
    /**
     * 名称
     */
	private String name;
    /**
     * 节目分类
     */
    @TableField("type_id")
	private Integer typeId;
    /**
     * 企业id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 源id
     */
	@TableField("source_id")
	private Integer sourceId;
    /**
     * 地区
     */
	private String region;
    /**
     * 节目等级id
     */
	@TableField("level_id")
	private Integer levelId;
    /**
     * 年代
     */
	@TableField("show_time")
	private String showTime;
    /**
     * 所属分类id集合
     */
	@TableField("category_ids")
	private String categoryIds;
    /**
     * 所属分类名称集合
     */
	@TableField("category_names")
	private String categoryNames;
	/**
	 * 多个导演，用“,”相隔
	 */
	private String directors;
	/**
	 * 多个演员，用“,”相隔
	 */
	private String actors;
    /**
     * 创建时间
     */
	private Date created;


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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}

	public String getCategoryNames() {
		return categoryNames;
	}

	public void setCategoryNames(String categoryNames) {
		this.categoryNames = categoryNames;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	//edited by mxy on 2017-03-21 10:08 begin
	/**
	 * 广告位数
	 */
	@TableField(exist = false)
	private Integer adSlotNum;

	/**
	 * 所属订单
	 */
	@TableField(exist = false)
	private String orderId;

	/**
	 * 所属订单关联ID
	 */
	@TableField(exist = false)
	private Long oppId;

	/**
	 * 关联是否已删除
	 */
	@TableField(exist = false)
	private Integer oppDeleted;

	/**
	 * 视频数
	 */
	@TableField(exist = false)
	private Integer videoNum;

	/**
	 * 视频播放数
	 */
	@TableField(exist = false)
	private Integer playNum;


	/**
	 * 视频id合集
	 */
	@TableField(exist = false)
	private String videoIds;


	/**
	 * 广告位id合集
	 */
	@TableField(exist = false)
	private String adSlotIds;

	/**
	 * 所属公司
	 */
	@TableField(exist = false)
	private Company company;

	/**
	 * 所属类型
	 */
	@TableField(exist = false)
	private ProgramType programType;

	/**
	 * 所属级别
	 */
	@TableField(exist = false)
	private ProgramLevel programLevel;

	public Integer getAdSlotNum() {
		return adSlotNum;
	}

	public void setAdSlotNum(Integer adSlotNum) {
		this.adSlotNum = adSlotNum;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public String getVideoIds() {
		return videoIds;
	}

	public void setVideoIds(String videoIds) {
		this.videoIds = videoIds;
	}

	public String getAdSlotIds() {
		return adSlotIds;
	}

	public void setAdSlotIds(String adSlotIds) {
		this.adSlotIds = adSlotIds;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ProgramType getProgramType() {
		return programType;
	}

	public void setProgramType(ProgramType programType) {
		this.programType = programType;
	}

	public ProgramLevel getProgramLevel() {
		return programLevel;
	}

	public void setProgramLevel(ProgramLevel programLevel) {
		this.programLevel = programLevel;
	}

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getOppId() {
		return oppId;
	}

	public void setOppId(Long oppId) {
		this.oppId = oppId;
	}

	public Integer getOppDeleted() {
		return oppDeleted;
	}

	public void setOppDeleted(Integer oppDeleted) {
		this.oppDeleted = oppDeleted;
	}


	//edited by mxy on 2017-03-21 10:24 end
}

