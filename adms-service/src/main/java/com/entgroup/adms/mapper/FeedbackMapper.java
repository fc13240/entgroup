package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Feedback;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 用户反馈表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * @title getAllFeedbacks
     * @description TODO 获取反馈列表
     * @author xiaokun
     * @date 2017-03-30 10:35
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param feedback
     * @return List<Feedback>
     * @throws
     */
    List<Feedback> getAllFeedbacks(Pagination page, Feedback feedback);
}
