package com.entgroup.adms.service;

import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.SceneRecognitionResult;

import java.util.List;

/**
 * <p>
 * 场景识别结果表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface SceneRecognitionResultService extends IService<SceneRecognitionResult> {

    /**
     *
     * @param videoId
     * @param sceneId
     * @return List<SceneRecognitionResult>
     *
     * @title getSceneRecognitionResultList
     * @description TODO 获取视频场景识别结果
     * @throws
     * @author mxy
     * @date 2017-03-20 20:01
     * @modifier
     * @remark
     * @version V1.0
     */
    List<SceneRecognitionResult> getSceneRecognitionResultList(Long videoId, Long sceneId);
}
