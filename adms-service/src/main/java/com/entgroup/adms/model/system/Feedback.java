package com.entgroup.adms.model.system;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entgroup.adms.model.BaseObject;


/**
 * <p>
 * 用户反馈表
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@TableName("sys_feedback")
public class Feedback extends BaseObject {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String content;
	@TableField("publish_date")
	private Date publishDate;
	@TableField("user_id")
	private Long userId;
	private Integer read;
	//edited by xiaokun on 2017-03-30 09:45 begin
	@TableField(exist = false)
	private User user;
	@TableField(exist = false)
	private Company company;
	//edited by xiaokun on 2017-03-30 09:45 end

	//edited by xiaokun on 2017-04-09 21:16 begin
	@TableField(exist = false)
	private String formatPublishDate;
	//edited by xiaokun on 2017-04-09 21:16 end

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	//edited by xiaokun on 2017-03-30 09:46 begin
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	//edited by xiaokun on 2017-03-30 09:46 end

	//edited by xiaokun on 2017-04-09 21:17 begin

	public String getFormatPublishDate() {
		return formatPublishDate;
	}

	public void setFormatPublishDate(String formatPublishDate) {
		this.formatPublishDate = formatPublishDate;
	}
	//edited by xiaokun on 2017-04-09 21:17 end
}

