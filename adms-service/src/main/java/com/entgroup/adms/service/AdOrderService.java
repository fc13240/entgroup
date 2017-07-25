package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdOrderListDTO;
import com.entgroup.adms.model.system.AdOrder;

import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface AdOrderService extends IService<AdOrder> {
	
	/**
	 * 
	 * @method: selectAdOrderPage  
	 * @Description: 获取所有订单列表 
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param slotCount
	 * @param status
	 * @param orderName
	 * @param proceed
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	Page<AdOrderListDTO> selectAdOrderPage(Integer pageNum, Integer pageSize,
			String companyId, String slotCount, String status, String orderName, String proceed);

	/**
	 * 
	 * @method: updateAdOrderSlotCount
	 * @description: 修改订单 的广告位总数
	 * @param adId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	public boolean updateAdOrderSlotCount(Long adId);

	/**
	 * 
	 * @method: insertAdOrder
	 * @description: 添加订单
	 * @param adOrder
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	boolean insertAdOrder(AdOrder adOrder);
	/**
	 * 
	 * @method: selectOrderList  
	 * @Description: 获取订单下拉框  统计页面
	 * @param companyId
	 * @return
	 * @author guofei 
	 * @date 2017年4月28日
	 */
	List<AdAndStyleByCompanyDTO> selectOrderList(String companyId);

	boolean insertAdOrderTemp(AdOrder adOrder);

	Page<AdOrder> adOrderList(Page<AdOrder> page, AdOrder adOrder);
}
