package com.entgroup.adms.mapper;

import java.util.List;

import com.entgroup.adms.model.system.SlotLabel;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  * 广告位标签 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface SlotLabelMapper extends BaseMapper<SlotLabel> {

	 /**
     * @Title: selectSlotLableByIds
     * @Description: 通过广告位中的标签ID获取标签集合 
     * @param list ids
     * @author liuxiaolong
     * @date 2017/4/7
     * @return 广告标签集合
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
	List<SlotLabel> selectSlotLableByIds(List list);
	
	
}
