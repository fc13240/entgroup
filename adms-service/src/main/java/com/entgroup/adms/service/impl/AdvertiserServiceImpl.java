package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Advertiser;
import com.entgroup.adms.mapper.AdvertiserMapper;
import com.entgroup.adms.service.AdvertiserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MaxNull
 * @since 2017-05-05
 */
@Service
public class AdvertiserServiceImpl extends ServiceImpl<AdvertiserMapper, Advertiser> implements AdvertiserService {

	/**
	 * @param page
	 * @param id
	 * @param name
	 * @param statuses
	 * @param ids
	 * @param companyVocationId
	 * @param companyId
	 * @param userId
	 *
	 * @return Page<Advertiser>
	 *
	 * @throws @title
	 *             getAdvertiserList
	 * @description TODO 根据获取广告主列表
	 * @author mxy
	 * @date 2017-05-05 11:59
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Advertiser> getAdvertiserList(Page<Advertiser> page, Long id, String name, List<String> statuses,
			List<String> ids, Long companyVocationId, Long companyId, Long userId) {
		page.setRecords(
				baseMapper.getAdvertiserList(page, id, name, statuses, ids, companyVocationId, companyId, userId));
		return page;
	}
}
