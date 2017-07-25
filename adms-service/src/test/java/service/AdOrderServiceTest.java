package service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.dto.AdOrderListDTO;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.service.AdOrderService;
import org.junit.Test;

import java.util.List;

public class AdOrderServiceTest extends BaseServiceTest<AdOrderService> {

	/**
	 * 根据广告ID修改订单的广告位数量 liuxiaolong
	 */
	@Test
	public void updateAdOrderSlotCount() {
		System.out.println("========================"
				+ service.updateAdOrderSlotCount(1L));
	}

	/**
	 * 
	 * @method: insertAdOrder
	 * @Description: 添加订单
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Test
	public void insertAdOrder() {
		AdOrder adOrder = new AdOrder();
		adOrder.setId("1111");
		adOrder.setCompanyId((long) 1);
		adOrder.setAdCount(12321);
		adOrder.setSlotCount(123);
		adOrder.setTotalMoney(12312);
		adOrder.setCosumeMoney(21);
		adOrder.setStatus(3);
		adOrder.setRemark("郭飞测试数据");
		boolean insertAdOrder = service.insertAdOrder(adOrder);
		System.out.println(insertAdOrder);
	}
	
	@Test
	public void testSelectOneOrder(){
		AdOrder selectOneOrder = service
				.selectOne(new EntityWrapper<AdOrder>().eq("id", "27120170415181941")
						.eq("status", 1));
		System.out.println(selectOneOrder);
	}
	@Test
	public void testSelectAdOrderPage(){
		Page<AdOrderListDTO> selectAdOrderPage = service.selectAdOrderPage(1, 10, null, null, "1",null, "1");
		List<AdOrderListDTO> records = selectAdOrderPage.getRecords();
		System.out.println(records);
	}

	@Test
	public void selectOneTest() {
		AdOrder adOrder = service.selectOne(new EntityWrapper<AdOrder>().eq("id", "27120170415181941"));
	}
}
