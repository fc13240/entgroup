package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Notice;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * @title getAllNotices
     * @description TODO 获取通知列表
     * @author xiaokun
     * @date 2017-03-31 09:23
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param map
     * @return List<Notice>
     * @throws
     */
    List<Notice> getAllNotices(Pagination page, Map map);
}
