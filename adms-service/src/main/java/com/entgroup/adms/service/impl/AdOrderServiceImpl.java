package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdOrderListDTO;
import com.entgroup.adms.mapper.AdOrderMapper;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.service.AdOrderService;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AdOrderServiceImpl extends ServiceImpl<AdOrderMapper, AdOrder>
		implements AdOrderService {

	/**
	 * 
	 * @method: selectAdOrderPage  
	 * @Description:获取所有的订单列表 
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param slotCount
	 * @param status
	 * @param proceed
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Override
	public Page<AdOrderListDTO> selectAdOrderPage(Integer pageNum,
			Integer pageSize, String companyId, String slotCount, String status, String orderName, String proceed) {
		Pagination pagination = new Pagination(pageNum, pageSize);
		List<AdOrderListDTO> allAdOrder = baseMapper.selectAdOrderPage(
				pagination, companyId,slotCount,status,orderName,proceed);
		Page<AdOrderListDTO> page = new Page<AdOrderListDTO>();
		page.setRecords(allAdOrder);
		page.setTotal(pagination.getTotal());
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		return page;
	}

	/**
	 * @Title: updateAdOrderSlotCount
	 * @Description: 修改广告订单 的广告位总数
	 * @param adId
	 *            广告ID
	 * @author liuxiaolong
	 * @date 2017/3/28
	 * @return
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	public boolean updateAdOrderSlotCount(Long adId) {
		return baseMapper.updateAdOrderSlotCount(adId);
	}

	/**
	 * 
	 * @method: insertAdOrder
	 * @Description: 添加订单
	 * @param adOrder
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public boolean insertAdOrder(AdOrder adOrder) {
		int row = baseMapper.insertAdOrder(adOrder);
		if (row > 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 
	 * @method: selectOrderList  
	 * @Description: 获取订单下拉框 
	 * @param companyId
	 * @return
	 * @author guofei 
	 * @date 2017年4月28日
	 */
	@Override
	public List<AdAndStyleByCompanyDTO> selectOrderList(String companyId) {
		return baseMapper.selectOrderList(companyId);
	}

	@Override
	public boolean insertAdOrderTemp(AdOrder adOrder) {
		return baseMapper.insertAdOrderTemp(adOrder);
	}

	@Override
	public Page<AdOrder> adOrderList(Page<AdOrder> page, AdOrder adOrder) {
		List<AdOrder> adOrderList = baseMapper.adOrderList(page, adOrder);
		for (AdOrder order : adOrderList) {
			String slotCounts = order.getSlotCounts();
			order.setSlotCountAll(StringUtils.countMatches(slotCounts, "-"));
			order.setSlotCountPreview(StringUtils.countMatches(slotCounts, "-0"));
		}
		page.setRecords(adOrderList);
		return page;
	}
}
