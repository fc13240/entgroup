package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entgroup.adms.model.system.SceneRecognitionResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 场景识别结果表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface SceneRecognitionResultMapper extends BaseMapper<SceneRecognitionResult> {

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
    List<SceneRecognitionResult> getSceneRecognitionResultList(@Param("videoId") Long videoId, @Param("sceneId") Long sceneId);
}
