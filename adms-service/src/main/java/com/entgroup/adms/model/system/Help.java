package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;

import java.util.Date;

/**
 * @author xiaokun
 * @className Help
 * @description 帮助实体类
 * @create 2017/4/14 20:16
 */
@TableName("sys_help")
public class Help extends BaseObject {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键，自增
     */
    private Integer id;
    /**
     * 帮助标题
     */
    private String title;
    /**
     * 帮助内容
     */
    private String content;
    /**
     * 发布时间
     */
    @TableField("publish_date")
    private Date publishDate;
    /**
     * 发布者id
     */
    @TableField("creator_id")
    private Long creatorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
