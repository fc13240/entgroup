package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.conf.NoticeConfig;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.Notice;
import com.entgroup.adms.mapper.NoticeMapper;
import com.entgroup.adms.service.NoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

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
    public Page<Notice> getAllNotices(Page<Notice> page, Map map) {
        page.setRecords(baseMapper.getAllNotices(page, map));
        return page;
    }

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
    public void addAdOrderNotice(AdOrder adOrder) {
        // 获取通知参数
        NoticeConfig.INoticeConfig config = NoticeConfig.config;
        // 通知模板
        String orderContent = config.orderContent();
        // 订单通知内容
        String orderCreated = config.created();
        String orderFinished = config.orderFinished();
        // 订单标题
        String orderTit = config.orderTit();
        // 初始化通知体对象
        Notice notice = new Notice();
        // 订单状态通知
        notice.setTitle(orderTit);
        notice.setCompanyId(adOrder.getCompanyId());
        notice.setToUid(adOrder.getCompanyId());
        notice.setType(Notice.TYPE_ORDER);
        notice.setContent(String.format(orderContent, adOrder.getId(), adOrder.getStatus() == 1 ? orderCreated : orderFinished));
        notice.setPublishDate(TimeStamp.getCurrentTime().getDate());
        notice.setRead(0);
        // 通知存储
        try {
            baseMapper.insert(notice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

