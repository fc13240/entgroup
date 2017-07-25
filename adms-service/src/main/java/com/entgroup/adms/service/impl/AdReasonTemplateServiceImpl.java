package com.entgroup.adms.service.impl;

import java.util.List;

import com.entgroup.adms.model.system.AdReasonTemplate;
import com.entgroup.adms.mapper.AdReasonTemplateMapper;
import com.entgroup.adms.service.AdReasonTemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 不合格理由模板表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AdReasonTemplateServiceImpl extends ServiceImpl<AdReasonTemplateMapper, AdReasonTemplate> implements AdReasonTemplateService {

	/**
     * @Title: selectAdReasonListByAdid
     * @Description: 根据广告ID查询广告未通过原因
     * @author liuxiaolong
     * @date 2017/3/21
     * @param  adId 广告id
     * @return 
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
	@Override
	public List<AdReasonTemplate> selectAdReasonListByAdid(Long adId){
		return baseMapper.selectAdReasonListByAdid(adId);
	}
}

