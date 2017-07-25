package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.model.system.Feedback;
import com.entgroup.adms.model.system.Help;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xiaokun
 * @className FeedbackController
 * @description 反馈管理
 * @create 2017/3/30 9:12
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "反馈管理";

    /**
     * @title feedbackList
     * @description TODO 获取反馈列表
     * @author xiaokun
     * @date 2017-03-30 09:17
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param model
     * @return String
     * @throws
     */
    @RequestMapping("/feedbackList")
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.VIEW_PERSONALCENTER_FEEDBACKLIST}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取反馈列表")
    public String feedbackList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
                               @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
                               @ModelAttribute("feedback") Feedback feedback,
                               @ModelAttribute("help") Help help, Model model) {

        log.info("feedbackList......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        Integer admin = shiroUser.getAdmin();

        List<Feedback> feedbackList;

        // 超级管理员查看所有反馈/广告主查看企业反馈
        if (admin != 1) {
            feedback.setUserId(userId);
        } else if (companyId != 1L) {
            feedback.setCompany(companyService.selectById(companyId));
        }

        Page<Feedback> feedbackPage = new Page<>(pageNum, pageSize);
        feedbackPage.setOrderByField("id");
        feedbackPage.setAsc(false);
        feedbackPage = feedbackService.getAllFeedbacks(feedbackPage, feedback);
        feedbackList = feedbackPage.getRecords();

        PageInfo<Feedback> page = new PageInfo<>(feedbackPage);
        model.addAttribute("page", page);
        model.addAttribute("feedbackList", feedbackList);

        if (log.isDebugEnabled()) {
            log.info("feedbackList......");
            log.debug("feedbackList...param pageNum:{}", pageNum);
            log.debug("feedbackList...param pageSize:{}", pageSize);
            log.debug("feedbackList...param feedback toString:{}", feedback.toString());
            log.debug("feedbackList...result feedbackList size:{}", feedbackList.size());
            log.debug("feedbackList...result page total:{}", page.getTotal());
        }
        return "personalCenter/feedbackList";
    }

    /**
     * @title addFeedback
     * @description TODO 新增反馈
     * @author xiaokun
     * @date 2017-03-30 11:05
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param feedback
     * @return JsonResult
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_FEEDBACKLIST_ADDFEEDBACK}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping("/addFeedback")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "新增反馈")
    public JsonResult addFeedback(@ModelAttribute("feedback") Feedback feedback) {

        log.info("addFeedback......");

        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();

        // 初始化赋值
        feedback.setPublishDate(TimeStamp.getCurrentTime().getDate());
        feedback.setUserId(userId);
        feedback.setRead(0);
        try {
            feedbackService.insert(feedback);
            jr = renderSuccess("反馈成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("反馈失败");
        }

        if (log.isDebugEnabled()) {
            log.info("addFeedback......");
            log.debug("addFeedback...param feedback toString:{}", feedback.toString());
            log.debug("addFeedback...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title feedbackDetail
     * @description TODO 获取详情
     * @author xiaokun
     * @date 2017-04-09 15:23
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param id
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/feedbackDetail")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_FEEDBACKLIST_FEEDBACKDETAIL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取详情")
    public JsonResult feedbackDetail(@RequestParam("id") Long id) {

        log.info("feedbackDetail......");

        Feedback feedback = new Feedback();
        try {
            feedback = feedbackService.selectById(id);
            User user = userService.selectById(feedback.getUserId());
            feedback.setUser(user);
            Company company = companyService.selectById(user.getCompanyId());
            feedback.setCompany(company);
            feedback.setFormatPublishDate(DateUtils.format(feedback.getPublishDate(), "yyyy/MM/dd"));
            jr = renderSuccess("获取详情成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("获取详情失败");
        }

        jr.setData("feedback", feedback);

        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("feedbackDetail......");
            log.debug("feedbackDetail...param id:{}", id);
            log.debug("feedbackDetail...result feedback toString:{}", feedback.toString());
            log.debug("feedbackDetail...result jr toString:{}", jr.toString());
        }
        return jr;
    }
}
