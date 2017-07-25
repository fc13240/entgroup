package quartz;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.OrderAd;
import com.entgroup.adms.service.AdDisplayCountService;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.DecimalCalculate;
import service.BaseServiceTest;


/**
 * 
 * @author guofei
 * @ClassName: GetAdCosumeTest.java
 * @Description: 统计订单消费金额测试类
 * @date 2017年4月25日
 */
public class GetAdCosumeTest extends BaseServiceTest<AdDisplayCountService> {

	private Logger log = LoggerFactory.getLogger(GetAdCosumeTest.class);
	@Test
	public void testAdCosume() {
		String beforeDay = DateUtils.getBeforeDay((long) 1);
		List<DisplayCountDTO> adDisplayCountList = service.staOrderCosumeList(beforeDay);
		List<AdOrder> adOrderList = new ArrayList<>();
		List<String> orderIdList = new ArrayList<>();
		List<String> adIdList = new ArrayList<>();
		boolean update = false;
		for (DisplayCountDTO adDisplayCount : adDisplayCountList) {
			// 获取前一天的消费金额
			Double totalPrice = adDisplayCount.getOrderTotalPrice();
			String orderId = adDisplayCount.getOrderId();
			AdOrder selectOneOrder=service.selectOneAdOrder(orderId);
			
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
					service.addAdOrderNotice(selectOneOrder);
					orderIdList.add(orderId); // 把orderId放入list集合中 批量修改
					//根据orderId 获取adId
					List<OrderAd> OrderAdList= service.selectOrderAdList(orderId);
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
				boolean updateAd=service.updateBatchAd(adIdList);
				if(updateAd){
					log.debug("广告可以重新关联");
				}
			} catch (Exception e2) {
				log.error("广告重启失败");
			}
			try {
				boolean updateSlotBatch = service
						.updateAdByOrderId(orderIdList);
				if (updateSlotBatch) {
					log.debug("广告位和广告关联表已删除");
				}
			} catch (Exception e1) {
				log.error("广告位和广告关联表删除失败");
			}
			try {
				// 批量删除订单广告的关联
				boolean updateBatchById = service.deleteOrderAd(orderIdList);

				if (updateBatchById) {
					log.debug("订单和广告关联删除");

				}
			} catch (Exception e) {
				log.debug("订单和广告关联删除失败");
			}
		}
		if(adOrderList.size()!=0){
			try {
				update = service.updateBatchAdOrderById(adOrderList);

			} catch (Exception e) {
				log.error("数据金额过大，算不出来");
			}
			if (update) {
				log.debug("更新成功");
			} else {
				log.error("更新失败");
			}
		}
	}

}
