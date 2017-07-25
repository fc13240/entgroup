package com.entgroup.adms.service;

import java.util.List;

import com.entgroup.adms.model.system.AdReasonTemplate;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 不合格理由模板表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdReasonTemplateService extends IService<AdReasonTemplate> {
	
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
	public List<AdReasonTemplate> selectAdReasonListByAdid(Long adId);
}
