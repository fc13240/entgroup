package com.entgroup.adms.service;

import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Person;

import java.util.List;

/**
 * <p>
 * 明星表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface PersonService extends IService<Person> {

    /**
     *
     * @param videoId
     * @param personId
     * @param adId
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
    List<Person> getPerson4AdsList(Long videoId, Long personId, Long adId);
    
    
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
    List<Person> selectSlotPersonByIds(List list);
}
