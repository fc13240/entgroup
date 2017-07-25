package com.entgroup.adms.listener;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.model.system.OrderAd;
import com.entgroup.adms.model.system.OrderFinishedInfo;
import com.entgroup.adms.service.*;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.DecimalCalculate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author guofei
 * @ClassName: GetAdCosume.java
 * @Description: 查询订单前一天的消费金额 修改订单 并判断订单是否交易完成 石英调度 每天3点
 * @date 2017-4-18
 */

public class GetAdCosume {
	@Resource
	private AdDisplayCountService adDisplayCountService;
	@Resource
	private AdOrderService adOrderService;
	@Resource
	private OrderAdService orderAdService;
	@Resource
	private AdSlotService adSlotService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private AdService adService;
	@Resource
	private OrderFinishedInfoService orderFinishedInfoService;

	private Logger log = LoggerFactory.getLogger(GetAdCosume.class);

	public void executeGetAdCosume() {
		log.info("executeGetAdCosume.........");
		String beforeDay = DateUtils.getBeforeDay((long) 1);
		List<DisplayCountDTO> adDisplayCountList = adDisplayCountService
				.staOrderCosumeList(beforeDay);
		List<AdOrder> adOrderList = new ArrayList<>();
		List<String> orderIdList = new ArrayList<>();
		List<String> adIdList = new ArrayList<>();
		boolean update = false;
		for (DisplayCountDTO adDisplayCount : adDisplayCountList) {
			// 获取前一天的消费金额
			Double totalPrice = adDisplayCount.getOrderTotalPrice();
			String orderId = adDisplayCount.getOrderId();
			AdOrder selectOneOrder = adOrderService
					.selectOne(new EntityWrapper<AdOrder>().eq("id", orderId)
							.eq("status", 1));
			if (selectOneOrder != null) {
				// 修改消费金额
				Integer cosumeMoney = selectOneOrder.getCosumeMoney();
				double doubleValue = cosumeMoney.doubleValue();
				double add = DecimalCalculate.add(doubleValue, totalPrice);

				Integer totalMoney = selectOneOrder.getTotalMoney();
				// 判断订单金额消费完成 取消订单
				if (add >= totalMoney) {
					selectOneOrder.setStatus(2);
					selectOneOrder.setSlotCount(0);
					noticeService.addAdOrderNotice(selectOneOrder);
					orderIdList.add(orderId); // 把orderId放入list集合中 批量修改
					//根据orderId 获取adId
					List<OrderAd> OrderAdList= orderAdService.selectList(new EntityWrapper<OrderAd>().eq("order_id", orderId)
							.eq("deleted", 0));
					for (OrderAd orderAd : OrderAdList) {
						Long adId = orderAd.getAdId();
						adIdList.add(adId.toString());
					}
				}
				// 设置修改后的金额
				selectOneOrder.setCosumeMoney((int) add);

				adOrderList.add(selectOneOrder);// 将所有的订单对象放入list中
			}
		}
		if(orderIdList.size()!=0){
			
			try {
				boolean updateAd=adService.updateBatchAd(adIdList);
				if(updateAd){
					log.debug("广告可以重新关联");
				}
			} catch (Exception e2) {
				log.error("广告重启失败");
			}
			try {
				//记录已完成订单广告位
				for (String s : orderIdList) {
					Wrapper<AdSlot> adSlotWrapper = new EntityWrapper<>();
					adSlotWrapper.eq("order_id",s);
					List<AdSlot> adSlotList = adSlotService.selectList(adSlotWrapper);
					String adSlotIds = "";
					for (AdSlot adSlot : adSlotList) {
						adSlotIds+=adSlot.getId()+",";
					}
					adSlotIds = StringUtils.removeEnd(adSlotIds, ",");
					if ("".equals(adSlotIds)) {
						adSlotIds = "0";
					}
					OrderFinishedInfo orderFinishedInfo = new OrderFinishedInfo();
					orderFinishedInfo.setOrderId(s);
					orderFinishedInfo.setAdSlotIds(adSlotIds);
					orderFinishedInfo.setEntryTime(new Date());
					orderFinishedInfoService.insert(orderFinishedInfo);
				}
				boolean updateSlotBatch = adSlotService
						.updateAdByOrderId(orderIdList);
				if (updateSlotBatch) {
					log.debug("广告位和广告关联表已删除");
				}
			} catch (Exception e1) {
				log.error("广告位和广告关联表删除失败");
			}
			try {
				// 批量删除订单广告的关联
				boolean updateBatchById = orderAdService.deleteOrderAd(orderIdList);

				if (updateBatchById) {
					log.debug("订单和广告关联删除");

				}
			} catch (Exception e) {
				log.debug("删除失败");
			}
		}
		if(adOrderList.size()!=0){
			try {
				update = adOrderService.updateBatchById(adOrderList);

			} catch (Exception e) {
				log.error("数据金额过大，算不出来");
			}
			if (update) {
				log.debug("更新成功");
			} else {
				log.error("更新失败");
			}
		}
		if (log.isDebugEnabled()) {
			log.info("executeGetAdCosume......");
			log.debug("executeGetAdCosume...adDisplayCountList.size:{}", adDisplayCountList.size());
		}
	}

}
