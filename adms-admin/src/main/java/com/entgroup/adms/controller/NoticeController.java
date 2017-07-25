package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.conf.NoticeConfig;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaokun
 * @className NoticeController
 * @description 系统通知
 * @create 2017/3/29 16:46
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "系统通知";

    /**
     * @title noticeList
     * @description TODO 获取通知列表
     * @author xiaokun
     * @date 2017-03-31 10:29
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return String
     * @throws
     */
    @RequestMapping("/noticeList")
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.VIEW_PERSONALCENTER_NOTICELIST}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取通知列表")
    public String noticeList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
                             @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
                             Model model) {
        // 日志记录
        log.info("noticeList......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Integer admin = shiroUser.getAdmin();
        Long companyId = shiroUser.getCompanyId();

        /* 通知 Notice 注意事项：
         *----------------------------------------------------------
         *      TYPE      TO_UID        DESCRIPTION     FOR
         *      1         user_id       广告审核通知    user only
         *      2         company_id    订单通知        company only
         *      3         NULL          系统通知        all
         */
        // 初始化查询参数
        List<Notice> noticeList = Lists.newArrayList();
        // 查询parameterMap生成
        /*
        * parameterMap:
        *   type        : 直接按消息类型查取(type = 1,2,3)
        *   userId      : 按针对用户查取(type = 1,3)
        *   companyId   : 按针对客户查取(type = 2,3)
        */
        Map<String, Object> parameterMap = new HashMap<>();
        // 艺恩超级管理员可见所有通知——备用添加筛选
        // 广告运营人员可见 广告审核
        if (companyId != 1L && admin != 1) {
            parameterMap.put("userId", userId);
        // 广告主可见 订单通知 & 广告审核
        } else if (companyId != 1L && admin == 1) {
            parameterMap.put("companyId", companyId);
        // 艺恩 可见 系统通知
        } else if (companyId == 1L) {
            parameterMap.put("type", 3);
        }

        // 分页查询
        Page<Notice> noticePage = new Page<>(pageNum, pageSize);
        noticePage.setOrderByField("id");
        noticePage.setAsc(false);
        noticePage = noticeService.getAllNotices(noticePage, parameterMap);
        noticeList = noticePage.getRecords();

        PageInfo<Notice> page = new PageInfo<>(noticePage);
        model.addAttribute("page", page);
        model.addAttribute("noticeList", noticeList);

        if (log.isDebugEnabled()) {
            log.info("noticeList......");
            log.debug("param pageNum:{}", pageNum);
            log.debug("param pageSize:{}", pageSize);
            log.debug("param parameterMap toString:{}", parameterMap.toString());
            log.debug("result noticeList size:{}", noticeList.size());
            log.debug("result page total:{}", page.getTotal());
        }
        return "personalCenter/noticeList";
    }

    /**
     * @title addNotice
     * @description TODO 新增通知
     * @author xiaokun
     * @date 2017-03-31 16:46
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param adId      广告ID ——用于生成广告审核通知( adId 不为 null 为广告审核通知 )
     * @param adOrderId 订单ID ——用于生成订单状态通知( adOrderId 不为 null 为订单状态通知 )
     * @param status    状态码 ——用于显示 广告审核/订单状态
     * @param content    系统通知内容( notice 不为 null 为系统通知 )
     *                  adId \ adOrderId \ notice 只能有一个赋值
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/addNotice")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_NOTICELIST_ADDNOTICE}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "添加通知")

    public JsonResult addNotice(@RequestParam(required = false, value = "adId") Long adId,
                                   @RequestParam(required = false, value = "adOrderId") String adOrderId ,
                                   @RequestParam(required = false, value = "status") Integer status,
                                   @RequestParam(required = false, value = "content") String content) {

        log.info("addNotice......");

        User shiroUser = getShiroUser();
        Long companyId = shiroUser.getCompanyId();

        // 获取通知参数
        NoticeConfig.INoticeConfig config = NoticeConfig.config;
        // 通知模板
        String auditContent = config.auditContent();
        String orderContent = config.orderContent();
        // 广告审核内容
        String adPassed = config.passed();
        String adNoPass = config.noPass();
        // 订单通知内容
        String orderCreated = config.created();
        String orderFinished = config.orderFinished();
        // 订单标题
        String adTit = config.adTit();
        String orderTit = config.orderTit();
        String sysTit = config.sysTit();

        // 初始化content参量
        String noticeBody = null;
        String noticeParam1 = null;
        String noticeParam2 = null;

        // 初始化通知体对象
        Ad ad = null;
        AdOrder adOrder = null;
        Notice notice = new Notice();

        // 广告审核通知
        if (null != adId && null == adOrderId && null == content && null != status) {
            ad = adService.selectById(adId);
            notice.setTitle(adTit);
            notice.setCompanyId(ad.getCompanyId());
            notice.setToUid(ad.getCreatorId());
            notice.setType(Notice.TYPE_REVIEW);

            noticeParam1 = ad.getName();
            switch (status) {
                case 2 : noticeParam2 = adPassed;break;
                case 3 : noticeParam2 = adNoPass;break;
                default: jr = renderError("广告状态值错误");return jr;
            }
            noticeBody = auditContent;
            notice.setContent(String.format(noticeBody, noticeParam1, noticeParam2));
        // 订单状态通知
        } else if (null == adId && null != adOrderId && null == content && null != status) {
            adOrder = adOrderService.selectById(adOrderId);
            notice.setTitle(orderTit);
            notice.setCompanyId(adOrder.getCompanyId());
            notice.setToUid(adOrder.getCompanyId());
            notice.setType(Notice.TYPE_ORDER);

            noticeParam1 = adOrderId;
            noticeParam2 = status == 1 ? orderCreated : orderFinished;
            noticeBody = orderContent;
            notice.setContent(String.format(noticeBody, noticeParam1, noticeParam2));
        // 系统通知
        } else if (null == adId && null == adOrderId && null != content) {
            notice.setTitle(sysTit);
            notice.setCompanyId(0L);
            notice.setContent(content);
            notice.setType(Notice.TYPE_SYSTEM);
        } else {
            jr = renderError("通知信息混乱或未传入状态参数");
            return jr;
        }
        notice.setPublishDate(TimeStamp.getCurrentTime().getDate());
        notice.setRead(0);
        // 通知存储
        try {
            noticeService.insert(notice);
            jr = renderSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("保存失败");
        }
        if (log.isDebugEnabled()) {
            log.info("addNotice......");
            log.debug("addNotice...param notice toString:{}", notice.toString());
            log.debug("addNotice...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title noticeDetail
     * @description TODO 获取详情
     * @author xiaokun
     * @date 2017-04-10 09:24
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param id
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/noticeDetail")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_NOTICELIST_NOTICEDETAIL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取详情")
    public JsonResult noticeDetail(@RequestParam("id") Long id) {

        log.info("noticeDetail......");

        Notice notice = new Notice();
        try {
            notice = noticeService.selectById(id);
            jr = renderSuccess("获取详情成功");
            jr.setData("notice", notice);
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("获取详情失败");
            return jr;
        }
        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("noticeDetail......");
            log.debug("noticeDetail...param id:{}", id);
            log.debug("noticeDetail...result notice toString:{}", notice.toString());
            log.debug("noticeDetail...result jr toString:{}", jr.toString());
        }
        return jr;
    }
}
