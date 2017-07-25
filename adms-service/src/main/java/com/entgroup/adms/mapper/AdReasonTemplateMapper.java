package com.entgroup.adms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entgroup.adms.model.system.AdReasonTemplate;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 不合格理由模板表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdReasonTemplateMapper extends BaseMapper<AdReasonTemplate> {

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
     List<AdReasonTemplate> selectAdReasonListByAdid(@Param("adId") Long adId);
}
