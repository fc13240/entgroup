package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdOrderListDTO;
import com.entgroup.adms.model.system.AdOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 订单 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdOrderMapper extends BaseMapper<AdOrder> {
	/**
	 * 
	 * @Title: selectAdOrderPage  
	 * @Description: 获取订单列表 
	 * @param pagination
	 * @param companyId
	 * @param slotCount
	 * @param status
	 * @param orderName
	 * @param proceed
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdOrderListDTO> selectAdOrderPage(Pagination pagination, @Param("companyId")String companyId, @Param("slotCount")String slotCount,@Param("status") String status,@Param("orderName") String orderName,@Param("proceed") String proceed);

	
	/**
	 * 
	 * @Title: updateAdOrderSlotCount  
	 * @Description: 修改广告订单 的广告位总数  
	 * @param adId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean updateAdOrderSlotCount(Long adId);

	/**
	 * 
	 * @Title: insertAdOrder  
	 * @Description: 添加订单  
	 * @param adOrder
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	int insertAdOrder(AdOrder adOrder);

	/**
	 * 
	 * @Title: selectOrderList  
	 * @Description:  获取统计页面订单下拉框  
	 * @param companyId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdAndStyleByCompanyDTO> selectOrderList(@Param("companyId")String companyId);

	boolean insertAdOrderTemp(AdOrder adOrder);

	/**
	 * @title adOrderList
	 * @description TODO 获取订单列表信息
	 * @author xiaokun
	 * @date 2017-06-28 22:58
	 * @modifier
	 * @remark
	 * @version V1.0
	 *
	 * @param page
	 * @param adOrder
	 * @return List<AdOrder>
	 * @throws
	 */
	List<AdOrder> adOrderList(Pagination page, AdOrder adOrder);
}
