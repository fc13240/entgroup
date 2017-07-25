package com.entgroup.adms.service.impl;

import java.util.List;

import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.model.system.AdStyle;
import com.entgroup.adms.mapper.AdStyleMapper;
import com.entgroup.adms.service.AdStyleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告样式表 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AdStyleServiceImpl extends ServiceImpl<AdStyleMapper, AdStyle>
		implements AdStyleService {

	/**
	 * 
	 * @method: selectStyleByCompanyId
	 * @Description: 根据公司id获取对应的广告样式 （只有id和name）
	 * @param companyId
	 * @param adOrderId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<AdAndStyleByCompanyDTO> selectStyleByCompanyId(String companyId,String adOrderId) {

		return baseMapper.selectStyleByCompanyId(companyId,adOrderId);
	}

	/**
	 * 
	 * @method: selectAdStyle
	 * @Description: 根据订单id获取对应的广告样式
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<AdStyle> selectAdStyle(String orderId) {

		return baseMapper.selectAdStyle(orderId);
	}
	

}
