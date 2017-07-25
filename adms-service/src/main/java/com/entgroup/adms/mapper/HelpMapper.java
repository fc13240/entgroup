package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Help;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author xiaokun
 * @since 2017-04-14
 */
public interface HelpMapper extends BaseMapper<Help> {
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
     * @return List<Notice>
     * @throws
     */
    List<Help> getAllHelps(Pagination page, Help help);
}
