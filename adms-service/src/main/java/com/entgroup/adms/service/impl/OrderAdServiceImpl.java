package com.entgroup.adms.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.dto.OrderAdListDTO;
import com.entgroup.adms.mapper.OrderAdMapper;
import com.entgroup.adms.model.system.OrderAd;
import com.entgroup.adms.service.OrderAdService;

/**
 * <p>
 * 订单广告表 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class OrderAdServiceImpl extends ServiceImpl<OrderAdMapper, OrderAd>
		implements OrderAdService {
	/**
	 * 
	 * @method: selectAllAdByOrderPage
	 * @Description: 分页查询获取所有OrderAd（sys_order_ad），以及ad_id对应的广告sys_ad表，null为查询所有
	 * @param pageNum
	 * @param pageSize
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public Page<OrderAdListDTO> selectAllAdByOrderPage(Integer pageNum,
			Integer pageSize, String orderId, String styleId) {
		Pagination pagination = new Pagination(pageNum, pageSize);
		List<OrderAdListDTO> allOrderAd = baseMapper.getAllAdByOrderPage(
				pagination, orderId, styleId);
		Page<OrderAdListDTO> page = new Page<OrderAdListDTO>();
		page.setRecords(allOrderAd);
		page.setTotal(pagination.getTotal());
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		return page;
	}

	/**
	 * 
	 * @method: selectAllSlotListByAdId
	 * @Description: 根据adid获取所有的坐标
	 * @param adId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<OrderAdListDTO> selectAllSlotListByAdId(Integer adId) {
		List<OrderAdListDTO> allSlotListByAdId = baseMapper
				.getAllSlotListByAdId(adId);

		return allSlotListByAdId;
	}

	/**
	 * 
	 * @method: selectCountForSlot
	 * @Description: 获取订单对应广告位数量
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public int selectCountForSlot(String orderId) {

		return baseMapper.selectCountForSlot(orderId);
	}

	/**
	 * 
	 * @method: deleteOrderAd
	 * @Description: 批量删除订单广告关联表
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public boolean deleteOrderAd(List<String> orderIdList) {
		int rows = baseMapper.updateBatchDeleted(orderIdList);
		if (rows > 0) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @method: selectAllAdPage  
	 * @Description: 获取完成订单的广告详情
	 * @param pageNum
	 * @param pageSize
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Override
	public Page<OrderAdListDTO> selectAllAdPage(Integer pageNum, Integer pageSize, String orderId, String styleId) {
		Pagination pagination = new Pagination(pageNum, pageSize);
		List<OrderAdListDTO> allOrderAd = baseMapper.selectAllAdPage(
				pagination, orderId, styleId);
		for (OrderAdListDTO orderAdListDTO : allOrderAd) {
			orderAdListDTO.setAdSlotCount(0);
		}
		Page<OrderAdListDTO> page = new Page<OrderAdListDTO>();
		page.setRecords(allOrderAd);
		page.setTotal(pagination.getTotal());
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		return page;
	}

}
