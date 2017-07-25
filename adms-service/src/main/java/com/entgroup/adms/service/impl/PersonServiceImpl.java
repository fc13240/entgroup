package com.entgroup.adms.service.impl;

import com.entgroup.adms.model.system.Person;
import com.entgroup.adms.mapper.PersonMapper;
import com.entgroup.adms.service.PersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 明星表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    /**
     * @param videoId
     * @param personId
     * @param adId
     *
     * @return List<Person>
     *
     * @title getPerson4AdsList
     * @description TODO 根据获取明星相关广告位
     * @author mxy
     * @date 2017-03-22 09:45
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<Person> getPerson4AdsList(Long videoId, Long personId, Long adId) {
        return baseMapper.getPerson4AdsList(videoId, personId, adId);
    }
    
    
    /**
     * @Title: selectSlotPersonByIds
     * @Description: 通过广告位中的明星ID获取明星集合 
     * @param list ids
     * @author liuxiaolong
     * @date 2017/4/14
     * @return 广告场景集合
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<Person> selectSlotPersonByIds(List list){
    	return baseMapper.selectSlotPersonByIds(list);
    }
}

