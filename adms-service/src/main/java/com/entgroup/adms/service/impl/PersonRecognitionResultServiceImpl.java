package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.PersonRecognitionResultMapper;
import com.entgroup.adms.model.system.PersonRecognitionResult;
import com.entgroup.adms.service.PersonRecognitionResultService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 明星识别结果表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class PersonRecognitionResultServiceImpl extends ServiceImpl<PersonRecognitionResultMapper, PersonRecognitionResult> implements PersonRecognitionResultService {

    /**
     * @param videoId
     * @param personId
     *
     * @return List<PersonRecognitionResult>
     *
     * @throws
     * @title getPersonRecognitionResultList
     * @description TODO 获取视频场景识别结果
     * @author mxy
     * @date 2017-03-20 20:01
     * @modifier
     * @remark
     * @version V1.0
     */
    @Override
    public List<PersonRecognitionResult> getPersonRecognitionResultList(
            Long videoId, Long personId
    ) {
        return baseMapper.getPersonRecognitionResultList(videoId, personId);
    }
}

