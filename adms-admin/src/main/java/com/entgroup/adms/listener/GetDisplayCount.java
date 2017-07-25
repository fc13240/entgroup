package com.entgroup.adms.listener;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.service.AdDisplayCountService;
import com.entgroup.adms.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @author guofei
 * @ClassName: GetDisplayCount.java
 * @Description: 统计前一天的曝光量、用户量、点击次数 添加到曝光表中
 * @date 2017-4-18
 */

public class GetDisplayCount {

	private Logger log = LoggerFactory.getLogger(GetDisplayCount.class);
	@Resource
	private AdDisplayCountService adDisplayCountService;

	public void executeGetDisplayCount() {
		log.info("executeGetDisplayCount..............");
		// 获取前一天的日期
		String beforeDay = DateUtils.getBeforeDay((long) 1);
		Date parseDate = DateUtils.parseDate(beforeDay);
		// 获取当前日期
		String nowDay = DateUtils.getBeforeDay((long) 0);
		// 从记录`sys_ad_display_record` 中获取对应广告的用户量、曝光量，相同设备为同一个用户 （********）
		List<AdDisplayCount> displayCountList = adDisplayCountService
				.selectDisplayCount(beforeDay, nowDay);
		// 从记录`sys_ad_click_record`中获取对应广告的点击次数
		List<AdDisplayCount> displayCountClickList = adDisplayCountService
				.selectDisplayClickCount(beforeDay,nowDay);
		//将点击次数添加进displayCountList中
		for (AdDisplayCount adDisplayCount : displayCountList) {
			boolean judge = true;
			for (AdDisplayCount click : displayCountClickList) {
				judge = true;
				if (click.getOrderId().equals(adDisplayCount.getOrderId())
						&& click.getProgramId().equals(adDisplayCount.getProgramId())) {
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
			boolean insertBatch = adDisplayCountService
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
