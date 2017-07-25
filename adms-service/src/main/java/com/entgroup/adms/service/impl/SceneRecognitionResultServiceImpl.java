package com.entgroup.adms.service.impl;

import com.entgroup.adms.model.system.SceneRecognitionResult;
import com.entgroup.adms.mapper.SceneRecognitionResultMapper;
import com.entgroup.adms.service.SceneRecognitionResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 场景识别结果表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class SceneRecognitionResultServiceImpl extends ServiceImpl<SceneRecognitionResultMapper, SceneRecognitionResult> implements SceneRecognitionResultService {

    /**
     * @param videoId
     * @param sceneId
     *
     * @return List<SceneRecognitionResult>
     *
     * @throws
     * @title getSceneRecognitionResultList
     * @description TODO 获取视频场景识别结果
     * @author mxy
     * @date 2017-03-20 20:01
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<SceneRecognitionResult> getSceneRecognitionResultList(
            Long videoId, Long sceneId
    ) {
        return baseMapper.getSceneRecognitionResultList(videoId, sceneId);
    }
}

