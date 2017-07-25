package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author xiaokun
 * @Title CompanyVocation
 * @description 企业类型-描述企业所属行业
 * @create 2017/3/23
 * @param:
 * @return
 * @throws
 */
@TableName("sys_company_vocation")
public class CompanyVocation {
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
     * 描述
     */
    private String content;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
