package com.entgroup.adms.service.impl;

import com.entgroup.adms.model.system.Scene;
import com.entgroup.adms.mapper.SceneMapper;
import com.entgroup.adms.service.SceneService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 场景表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements SceneService {

    /**
     * @param videoId
     * @param sceneId
     * @param adId
     *
     * @return List<Scene>
     *
     * @title getScene4AdsList
     * @description TODO 根据获取场景相关广告位
     * @author mxy
     * @date 2017-03-22 09:45
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<Scene> getScene4AdsList(Long videoId, Long sceneId, Long adId) {
        return baseMapper.getScene4AdsList(videoId, sceneId, adId);
    }

    /**
     * @param sceneId
     * @param sceneName
     *
     * @return List<Scene>
     *
     * @throws
     * @title getScenes
     * @description TODO 获取场景（分级）
     * @author mxy
     * @date 2017-04-21 11:09
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<Scene> getScenes(Long sceneId, String sceneName) {
        return baseMapper.getScenes(sceneId, sceneName);
    }


    /**
     * @Title: selectSlotScenceByIds
     * @Description: 通过广告位中的场景ID获取场景集合 
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
    public List<Scene> selectSlotScenceByIds(List list){
    	return baseMapper.selectSlotScenceByIds(list);
    }
}

