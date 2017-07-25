package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.Notice;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface NoticeService extends IService<Notice> {
    /**
     * @title getAllNotices
     * @description TODO 获取通知列表
     * @author xiaokun
     * @date 2017-03-31 09:24
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param map
     * @return Page<Notice>
     * @throws
     */
    public Page<Notice> getAllNotices(Page<Notice> page, Map map);

    /**
     * @title addAdOrderNotice
     * @description TODO 新增订单状态通知
     * @author xiaokun
     * @date 2017-04-11 15:19
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param adOrder 这里需要传入AdOrder对象
     * @return void
     * @throws
     */
    public void addAdOrderNotice(AdOrder adOrder);
}
