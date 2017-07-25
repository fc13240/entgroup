package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * <p/>
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_notice")
public class Notice extends BaseObject {

    private static final long serialVersionUID = 1L;
    public static final int TYPE_REVIEW = 1;//广告审核,创建广告用户可见
    public static final int TYPE_ORDER = 2;//订单,相关企业用户可见
    public static final int TYPE_SYSTEM = 3;//系统,所用用户可见

    private Long id;
    private String title;
    private String content;
    @TableField("publish_date")
    private Date publishDate;
    private Integer type;

    @TableField("to_uid")
    private Long toUid;
    @TableField("read")
    private Integer read;

    //edited by xiaokun on 2017-04-10 20:46 begin
    @TableField("company_id")
    private Long companyId;
    //edited by xiaokun on 2017-04-10 20:46 end

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    //edited by xiaokun on 2017-04-10 20:47 begin
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    //edited by xiaokun on 2017-04-10 20:47 end
}

