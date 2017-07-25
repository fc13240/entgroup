package com.entgroup.adms.vo;


/**
 * @author mxy
 * @ClassName: DataStatisticsVO
 * @Description: TODO 数据统计查询条件
 * @date 2017-04-18 17:02
 */
public class DataStatisticsVO {
    /**
     * 客户id
     */
    private Long companyId;

    /**
     * 样式id
     */
    private Long adStyleId;

    /**
     * 广告id
     */
    private Long adId;

    /**
     *
     */
    private String type;

    /**
     * 时间范围
     */
    private Integer days;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getAdStyleId() {
        return adStyleId;
    }

    public void setAdStyleId(Long adStyleId) {
        this.adStyleId = adStyleId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "DataStatisticsVO{" + "companyId=" + companyId + ", adStyleId=" + adStyleId + ", adId=" + adId + ", type='" + type + '\'' + ", days=" + days + '}';
    }
}
