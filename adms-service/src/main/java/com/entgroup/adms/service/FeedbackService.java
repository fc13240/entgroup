package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Feedback;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户反馈表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface FeedbackService extends IService<Feedback> {
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
    public Page<Feedback> getAllFeedbacks(Page<Feedback> page, Feedback feedback);
}
