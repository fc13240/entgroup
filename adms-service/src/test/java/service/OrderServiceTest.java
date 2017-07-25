package service;

import org.junit.Test;

import com.entgroup.adms.service.AdOrderService;

public class OrderServiceTest extends BaseServiceTest<AdOrderService> {

	/**
	 * 根据广告ID修改订单的广告位数量 liuxiaolong
	 */
	@Test
	public void updateAdOrderSlotCount() {
		System.out.println("========================"
				+ service.updateAdOrderSlotCount(1L));
	}

}
