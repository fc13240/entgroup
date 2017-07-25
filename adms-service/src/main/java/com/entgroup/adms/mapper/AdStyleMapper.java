package com.entgroup.adms.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.model.system.AdStyle;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 广告样式表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdStyleMapper extends BaseMapper<AdStyle> {
	/**
	 * 
	 * @Title: selectStyleByCompanyId  
	 * @Description: 根据用户id获取对应的样式下拉框 
	 * @param companyId
	 * @param adOrderId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdAndStyleByCompanyDTO> selectStyleByCompanyId(@Param("companyId")String companyId,@Param("adOrderId")String adOrderId);
	/**
	 * 
	 * @Title: selectAdStyle  
	 * @Description:获取订单对应广告样式 
	 * @param orderId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdStyle> selectAdStyle(String orderId);
	/**
	 * 
	 * @Title: selectStyleByOrderId  
	 * @Description: 根据公司 订单 查询样式下拉框 
	 * @param companyId
	 * @param adOrderId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdAndStyleByCompanyDTO> selectStyleByOrderId(String companyId, String adOrderId);

}
