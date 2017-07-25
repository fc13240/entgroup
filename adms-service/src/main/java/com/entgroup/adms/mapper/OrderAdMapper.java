package com.entgroup.adms.mapper;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.dto.OrderAdListDTO;
import com.entgroup.adms.model.system.OrderAd;

/**
 * <p>
 * 订单广告表 Mapper 接口
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface OrderAdMapper extends BaseMapper<OrderAd> {
	/**
	 * 
	 * @Title: getAllAdByOrderPage
	 * @Description: 获取订单下的广告及广告样式
	 * @param pagination
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei
	 * @date 2017年5月5日
	 */

	List<OrderAdListDTO> getAllAdByOrderPage(Pagination pagination, @Param("orderId") String orderId,
			@Param("styleId") String styleId);

	/**
	 * 
	 * @Title: getAllSlotListByAdId
	 * @Description: 根据adId获取对应的广告位
	 * @param adId
	 * @return
	 * @author guofei
	 * @date 2017年5月5日
	 */
	List<OrderAdListDTO> getAllSlotListByAdId(@Param("adId") Integer adId);

	/**
	 * 
	 * @Title: selectCountForSlot
	 * @Description:根据orderId获取对应的广告位 
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017年5月5日
	 */
	int selectCountForSlot(String orderId);

	/**
	 * 
	 * @Title: updateBatchDeleted
	 * @Description:批量修改订单广告关联表
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017年5月5日
	 */
	int updateBatchDeleted(@Param("orderIdList") List<String> orderIdList);

	/**
	 * 
	 * @Title: selectAllAdPage
	 * @Description: 获取完成订单的订单详情
	 * @param pagination
	 * @param orderId
	 * @param styleId
	 * @return
	 * @author guofei
	 * @date 2017年5月5日
	 */
	List<OrderAdListDTO> selectAllAdPage(Pagination pagination, @Param("orderId") String orderId,
			@Param("styleId") String styleId);

}
