package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 不合格理由模板表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_ad_reason_template")
public class AdReasonTemplate extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 固定不合格理由
     */
	private String content;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

