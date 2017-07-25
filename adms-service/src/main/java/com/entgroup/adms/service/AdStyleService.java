package com.entgroup.adms.service;

import java.util.List;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.model.system.AdStyle;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 广告样式表 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface AdStyleService extends IService<AdStyle> {
	/**
	 * 
	 * @method: selectStyleByCompanyId
	 * @description: 根据公司id获取对应的广告样式 （只有id和name）
	 * @param companyId
	 * @param adOrderId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	List<AdAndStyleByCompanyDTO> selectStyleByCompanyId(String companyId,String adOrderId);

	/**
	 * 
	 * @method: selectAdStyle
	 * @description: 根据订单id获取对应的广告样式
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017-4-18
	 */
	List<AdStyle> selectAdStyle(String orderId);


}
