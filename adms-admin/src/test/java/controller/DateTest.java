package controller;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.entgroup.adms.util.DateUtils;

public class DateTest {
	/**
	 * 
	 * @method: test1  
	 * @Function: 获取坐标轴x轴数据 
	 * @author guofei 
	 * @date 2017-4-18
	 */
	@Test
	public void  test1(){
		Date date=new Date();//取时间 
		Calendar   calendar   =   new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(calendar.DATE,-(2));//把日期往后增加一天.整数往后推,负数往前移动 
		date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
		String format = DateUtils.format(date, "MM-dd");
		String substring = format;
		boolean startsWith = format.startsWith("0");
		if(startsWith){
			 substring = format.substring(1);
		}
		System.out.println(substring);
	}
}
