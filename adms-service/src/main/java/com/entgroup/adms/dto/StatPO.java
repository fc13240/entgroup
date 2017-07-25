package com.entgroup.adms.dto;

public class StatPO {

	private String days;//按天统计
	private String month;//按月统计
	
	// added by mqc on 20160224 begin
	/**
	 * 统计时间，可以是小时或天或月等
	 */
	private String dateFmt;
	// added by mqc on 20160224 end	

	private Object count;

	public String getDays() {
		return days;
	}

	public void setDay(String days) {
		this.days = days;
	}

	public Object getCount() {
		return count;
	}

	public void setCount(Object count) {
		this.count = count;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDateFmt() {
		return dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}

	

}
