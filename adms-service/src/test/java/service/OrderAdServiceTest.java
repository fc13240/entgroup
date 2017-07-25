package service;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.dto.OrderAdListDTO;
import com.entgroup.adms.service.OrderAdService;

public class OrderAdServiceTest extends BaseServiceTest<OrderAdService> {
	/**
	 * 
	 * @method: getAllAdByOrderPageTest
	 * @Description: 获取订单列表
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void getAllAdByOrderPageTest() {
		Page<OrderAdListDTO> allAdByOrderPage = service.selectAllAdByOrderPage(
				1, 3, "1", null);
		List<OrderAdListDTO> records = allAdByOrderPage.getRecords();
		System.out.println(records);
	}

	/**
	 * 
	 * @method: getAllSlotListByAdIdTest
	 * @Description: 根据广告id获取广告位
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void getAllSlotListByAdIdTest() {
		List<OrderAdListDTO> allSlotListByAdId = service
				.selectAllSlotListByAdId(1);
		System.out.println(allSlotListByAdId);
	}

	/**
	 * 
	 * @method: selectCountForSlotTest
	 * @Description: 根据广告查询广告位数量
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void selectCountForSlotTest() {
		int selectCountForSlot = service.selectCountForSlot("1");
		System.out.println(selectCountForSlot);
	}

	/**
	 * 
	 * @method: testDeleteOrderAd
	 * @description: 批量删除订单广告关联
	 * @author guofei
	 * @date 2017-4-18
	 */
	@Test
	public void testDeleteOrderAd() {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		boolean deleteOrderAd = service.deleteOrderAd(list);
		System.out.println(deleteOrderAd);
	}

}
