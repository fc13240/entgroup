package service;

import org.junit.Test;

import com.entgroup.adms.util.DateUtils;

public class DateUtilsTest {
	/**
	 * 
	 * @method: test1
	 * @Description: 得到当前日期
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void test1() {
		String date = DateUtils.getDate();

		System.out.println(date);

	}

	/**
	 * 
	 * @method: test2
	 * @Description: 得到钱几天的日期
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void test2() {
		String beforeDay = DateUtils.getBeforeDay((long) 0);
		System.out.println(beforeDay);
	}

	/**
	 * 
	 * @method: test3
	 * @Description: 得到前几天的日期
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void test3() {
		String beforeDayStart = DateUtils.getOneDayStart((long) 1);
		System.out.println(beforeDayStart);
	}
}
