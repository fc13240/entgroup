package com.entgroup.adms.service;

import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Scene;

import java.util.List;

/**
 * <p>
 * 场景表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface SceneService extends IService<Scene> {

    /**
     *
     * @param videoId
     * @param sceneId
     * @param adId
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
    List<Scene> getScene4AdsList(Long videoId, Long sceneId, Long adId);

    /**
     * @param sceneId
     * @param sceneName
     * @return List<Scene>
     * @throws
     *
     * @title getScenes
     * @description TODO 获取场景（分级）
     * @author mxy
     * @date 2017-04-21 11:09
     * @modifier
     * @remark
     * @version V1.0
     */
    List<Scene> getScenes(Long sceneId, String sceneName);

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
    List<Scene> selectSlotScenceByIds(List list);
}
