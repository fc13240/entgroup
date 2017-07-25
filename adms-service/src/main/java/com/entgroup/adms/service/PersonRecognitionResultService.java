package com.entgroup.adms.service;

import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.PersonRecognitionResult;

import java.util.List;

/**
 * <p>
 * 明星识别结果表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface PersonRecognitionResultService extends IService<PersonRecognitionResult> {

    /**
     *
     * @param videoId
     * @param personId
     * @return List<PersonRecognitionResult>
     *
     * @title getPersonRecognitionResultList
     * @description TODO 获取视频场景识别结果
     * @throws
     * @author mxy
     * @date 2017-03-20 20:01
     * @modifier
     * @remark
     * @version V1.0
     */
    List<PersonRecognitionResult> getPersonRecognitionResultList(Long videoId, Long personId);
}
