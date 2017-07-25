package quartz;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.service.AdDisplayCountService;
import com.entgroup.adms.util.DateUtils;

import service.BaseServiceTest;
/**
 * 
 * @author guofei
 * @ClassName: GetDisplayCountTest.java
 * @Description: 统计前一天的点击量和用户量  生成   AdDisplayCount
 * @date 2017年4月25日
 */
public class GetDisplayCountTest extends BaseServiceTest<AdDisplayCountService> {
	private Logger log = LoggerFactory.getLogger(GetDisplayCountTest.class);
	@Test
	public void executeGetDisplayCount() {
		// 获取前一天的日期
		String beforeDay = DateUtils.getBeforeDay((long) 0);
		Date parseDate = DateUtils.parseDate(beforeDay);
		// 获取当前日期
		String nowDay = DateUtils.getBeforeDay((long) -1);
		// 从记录`sys_ad_display_record` 中获取对应广告的用户量和曝光量，相同设备为同一个用户 （********）
		List<AdDisplayCount> displayCountList = service.selectDisplayCount(beforeDay, nowDay);
		// 从记录`sys_ad_click_record`中获取对应广告的点击次数
		List<AdDisplayCount> displayCountClickList = service
				.selectDisplayClickCount(beforeDay,nowDay);
		//将点击次数添加进displayCountList中
		for (AdDisplayCount adDisplayCount : displayCountList) {
			boolean judge = true;
			for (AdDisplayCount click : displayCountClickList) {
				judge = true;
				if (click.getOrderId().equals(adDisplayCount.getOrderId())
						&& click.getProgramId().equals(adDisplayCount.getProgramId())) {
					System.out.println("00>" + click.getClickCount());
					adDisplayCount.setClickCount(click.getClickCount());
					break;
				}
				judge = false;
			}
			if (judge == false) {
				adDisplayCount.setClickCount(0);
			}
			adDisplayCount.setDayTime(parseDate);
		}
		/*try {
			// 批量添加统计表
			boolean insertBatch = service
					.insertBatch(displayCountList);
			if (insertBatch) {
				log.debug("添加数据统计成功");
			}
		} catch (Exception e) {
			log.error("添加数据统计失败");
		}*/
		
	}


	@Test
	public void testData(){

		log.info("executeGetDisplayCount..............");
		// 获取前一天的日期
		String beforeDay = DateUtils.getBeforeDay((long) 1);
		Date parseDate = DateUtils.parseDate(beforeDay);
		// 获取当前日期
		String nowDay = DateUtils.getBeforeDay((long) 0);

		// 从记录`sys_ad_display_record` 中获取对应广告的用户量、曝光量，相同设备为同一个用户 （********）
		List<AdDisplayCount> displayCountList = service
				.selectDisplayCount(beforeDay, nowDay);
		// 从记录`sys_ad_click_record`中获取对应广告的点击次数
		List<AdDisplayCount> displayCountClickList = service
				.selectDisplayClickCount(beforeDay,nowDay);
		//将点击次数添加进displayCountList中
		for (AdDisplayCount adDisplayCount : displayCountList) {
			boolean judge = true;
			for (AdDisplayCount click : displayCountClickList) {
				judge = true;
				if (click.getOrderId().equals(adDisplayCount.getOrderId())) {
					adDisplayCount.setClickCount(click.getClickCount());
					break;
				}
				judge = false;
			}
			if (judge == false) {
				adDisplayCount.setClickCount(0);
			}
			adDisplayCount.setDayTime(parseDate);
		}
		try {
			// 批量添加统计表
			boolean insertBatch = service
					.insertBatch(displayCountList);
			if (insertBatch) {
				log.debug("添加数据统计成功");
			}
		} catch (Exception e) {
			log.error("添加数据统计失败");
		}
		if (log.isDebugEnabled()) {
			log.info("executeGetDisplayCount......");
			log.debug("executeGetDisplayCount...displayCountList.size:{}", displayCountList.size());
		}
	}




}




