package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;


/**
 * <p>
 * 视频表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_video")
public class Video extends BaseObject {

    //matchStatus字段含义
    public static final int MATCH_UNMATCHED = -1;//未处理
    public static final int MATCH_INVALID_SOURCE = 1;//无效的来源
    public static final int MATCH_DOWNLOADING = 0;//正在下载

    public static final int MATCH_DOWNLOADED = 3;//已下载但未处理
    public static final int MATCH_EXTRACTED_FRAMES = 4;//正在提取
    public static final int MATCH_SCENE = 2;//场景识别
    public static final int MATCH_FACE = 8;//人脸识别
    public static final int MATCH_ALL = MATCH_FACE + MATCH_SCENE;//场景和人脸识别都进行

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
     * 所属公司id（不知何故同平台id相同）
     */
    @TableField("company_id")
    private Long companyId;
    /**
     * 源视频id
     */
    @TableField("source_id")
    private String sourceId;
    /**
     * 父节目id
     */
    @TableField("program_id")
    private Long programId;
    /**
     * 特征处理标示:
     */
    @TableField("match_status")
    private Integer matchStatus;
    @TableField("extra_match_info")
    private String extraMatchInfo;
    /**
     * 视频时长
     */
    private Long duration;
    /**
     * web地址或播放地址
     */
    @TableField("web_url")
    private String webUrl;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 是否已筛选
     */
    @TableField("status_select")
    private Integer statusSelect;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getExtraMatchInfo() {
        return extraMatchInfo;
    }

    public void setExtraMatchInfo(String extraMatchInfo) {
        this.extraMatchInfo = extraMatchInfo;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getStatusSelect() {
        return statusSelect;
    }

    public void setStatusSelect(Integer statusSelect) {
        this.statusSelect = statusSelect;
    }
    //edited by mxy on 2017-03-20 15:21 begin
    /**
     * 广告位统计
     */
    @TableField(exist = false)
    private String ads;
    
    /**
     * 场景识别结果
     */
    @TableField(exist = false)
    private String srr;

    /**
     * 明星识别结果
     */
    @TableField(exist = false)
    private String prr;

    /**
     * 已挑选场景识别结果
     */
//    @TableField(exist = false)
//    private String selectedSrr;

    /**
     * 已挑选明星识别结果
     */
//    @TableField(exist = false)
//    private String selectedPrr;

    /**
     * 所属节目
     */
    @TableField(exist = false)
    private Program program;

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

    public String getSrr() {
        return srr;
    }

    public void setSrr(String srr) {
        this.srr = srr;
    }

    public String getPrr() {
        return prr;
    }

    public void setPrr(String prr) {
        this.prr = prr;
    }

    public String getAds() {
        return ads;
    }

    public void setAds(String ads) {
        this.ads = ads;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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
    //edited by mxy on 2017-03-20 19:38 end
    //edited by mxy on 2017-03-21 14:38 begin
    /**
     * 广告位数
     */
    @TableField(exist = false)
    private Integer adsNum;

    /**
     * 广告位id合集
     */
    @TableField(exist = false)
    private String adsIds;

    /**
     * 初始标签合集
     */
    @TableField(exist = false)
    private String adsILs;

    /**
     * 场景id合集
     */
    @TableField(exist = false)
    private String adsSids;

    /**
     * 明星id合集
     */
    @TableField(exist = false)
    private String adsPids;

    /**
     * 物品id合集
     */
    @TableField(exist = false)
    private String adsOids;

    /**
     * 标签id合集
     */
    @TableField(exist = false)
    private String adsLids;

    public Integer getAdsNum() {
        return adsNum;
    }

    public void setAdsNum(Integer adsNum) {
        this.adsNum = adsNum;
    }

    public String getAdsIds() {
        return adsIds;
    }

    public void setAdsIds(String adsIds) {
        this.adsIds = adsIds;
    }

    public String getAdsILs() {
        return adsILs;
    }

    public void setAdsILs(String adsILs) {
        this.adsILs = adsILs;
    }

    public String getAdsSids() {
        return adsSids;
    }

    public void setAdsSids(String adsSids) {
        this.adsSids = adsSids;
    }

    public String getAdsPids() {
        return adsPids;
    }

    public void setAdsPids(String adsPids) {
        this.adsPids = adsPids;
    }

    public String getAdsOids() {
        return adsOids;
    }

    public void setAdsOids(String adsOids) {
        this.adsOids = adsOids;
    }

    public String getAdsLids() {
        return adsLids;
    }

    public void setAdsLids(String adsLids) {
        this.adsLids = adsLids;
    }
    //edited by mxy on 2017-03-21 14:43 end
}

