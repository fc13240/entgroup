package com.entgroup.adms.service.impl;

import java.util.List;

import com.entgroup.adms.model.system.SlotLabel;
import com.entgroup.adms.mapper.SlotLabelMapper;
import com.entgroup.adms.service.SlotLabelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告位标签 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class SlotLabelServiceImpl extends ServiceImpl<SlotLabelMapper, SlotLabel> implements SlotLabelService {
    
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
	@Override
	public List<SlotLabel> selectSlotLableByIds(List list) {
		return baseMapper.selectSlotLableByIds(list);
	}
	
	
}

