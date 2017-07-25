package com.entgroup.adms.listener;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.service.AdOrderService;
import com.entgroup.adms.service.OrderAdService;

/**
 * 
 * @author guofei
 * @ClassName: GetSlotCount.java
 * @Description: 定时查询订单的广告位数量
 * @date 2017-4-18
 */

public class GetSlotCount {

	private Logger log = LoggerFactory.getLogger(GetSlotCount.class);
	@Resource
	private AdOrderService adOrderService;
	@Resource
	private OrderAdService orderAdService;

	public void executeGetSlotCount() {
		log.info("executeGetSlotCount........");
		// 获取所有的订单
		Wrapper<AdOrder> adOrderwrapper = new EntityWrapper<AdOrder>();
		adOrderwrapper.eq("status", 1);
		List<AdOrder> selectObjs = adOrderService.selectList(adOrderwrapper);
		for (AdOrder adOrder : selectObjs) {
			// 根据订单获取对应的订单id
			String OrderId = adOrder.getId();
			// 根据订单id获取广告位数量
			int countForOrderSlot = orderAdService.selectCountForSlot(OrderId);
			adOrder.setSlotCount(countForOrderSlot);
		}
		boolean updateBatchById = adOrderService.updateBatchById(selectObjs);
		if (updateBatchById) {
			log.debug("更新成功");
		} else {
			log.error("更新失败");
		}
		if (log.isDebugEnabled()) {
			log.info("executeGetSlotCount......");
			log.debug("executeGetSlotCount...selectObjs.size:{}", selectObjs.size());
		}
	}
}
