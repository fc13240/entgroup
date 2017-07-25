package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Feedback;
import com.entgroup.adms.mapper.FeedbackMapper;
import com.entgroup.adms.service.FeedbackService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户反馈表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    /**
     * @title getAllFeedbacks
     * @description TODO 获取反馈列表
     * @author xiaokun
     * @date 2017-03-30 10:40
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param feedback
     * @return Page<Feedback>
     * @throws
     */
    public Page<Feedback> getAllFeedbacks(Page<Feedback> page, Feedback feedback) {
        page.setRecords(baseMapper.getAllFeedbacks(page, feedback));
        return page;
    }
}

