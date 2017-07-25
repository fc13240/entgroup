package com.entgroup.adms.service;

import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.dto.OrderAdListDTO;
import com.entgroup.adms.model.system.OrderAd;

/**
 * <p>
 * 订单广告表 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface OrderAdService extends IService<OrderAd> {
	/**
	 * 
	 * @method: selectAllAdByOrderPage
	 * @description: 分页查询获取所有OrderAd（sys_order_ad），以及ad_id对应的广告sys_ad表，null为查询所有
	 * @param pageNum
	 * @param pageSize
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	Page<OrderAdListDTO> selectAllAdByOrderPage(Integer pageNum,
			Integer pageSize, String orderId, String styleId);

	/**
	 * 
	 * @method: selectAllSlotListByAdId
	 * @description:根据adid获取所有的坐标
	 * @param adId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	List<OrderAdListDTO> selectAllSlotListByAdId(Integer adId);

	/**
	 * 
	 * @method: selectCountForSlot
	 * @description: 获取订单对应广告位数量
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	int selectCountForSlot(String orderId);

	/**
	 * 
	 * @method: deleteOrderAd
	 * @description: 批量删除订单广告关联表
	 * @param orderAdIdList
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	boolean deleteOrderAd(List<String> orderAdIdList);
	/**
	 * 
	 * @method: selectAllAdPage  
	 * @Description: 获取完成订单的订单详情
	 * @param pageNum
	 * @param pageSize
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	Page<OrderAdListDTO> selectAllAdPage(Integer pageNum, Integer pageSize, String orderId, String styleId);

}
