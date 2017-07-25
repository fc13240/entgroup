package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.Help;
import com.entgroup.adms.model.system.User;
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
 * @className HelpController
 * @description 帮助管理模块
 * @create 2017/4/15 20:57
 */
@Controller
@RequestMapping("/help")
public class HelpController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "帮助管理";

    /**
     * @title helpList
     * @description TODO 获取帮助列表
     * @author xiaokun
     * @date 2017-04-18 08:57
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return String
     * @throws
     */
    @RequestMapping("/helpList")
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.VIEW_PERSONALCENTER_HELPLIST}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取帮助列表")
    public String helpList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
                           @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
                           @ModelAttribute("help") Help help, Model model) {

        log.info("helpList......");

        List<Help> helpList = Lists.newArrayList();
        Page<Help> helpPage = new Page<>(pageNum, pageSize);
        helpPage.setOrderByField("id");
        helpPage.setAsc(false);
        helpPage = helpService.getAllHelps(helpPage, help);
        helpList = helpPage.getRecords();

        PageInfo<Help> page = new PageInfo<>(helpPage);
        model.addAttribute("helpList", helpList);
        model.addAttribute("page", page);
        if (log.isDebugEnabled()) {
            log.info("helpList......");
            log.debug("helpList...param help toString:{}", help.toString());
            log.debug("helpList...result helpList size:{}", helpList.size());
            log.debug("helpList...result page total:{}", page.getTotal());
        }
        return "personalCenter/helpList";
    }

    /**
     * @title saveHelp
     * @description TODO 保存帮助信息
     * @author xiaokun
     * @date 2017-04-18 09:06
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param help
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/saveHelp")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_HELPLIST_SAVEHELP}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存帮助信息")
    public JsonResult saveHelp(@ModelAttribute("help") Help help) {

        log.info("saveHelp......");

        // 获取当前登陆用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();

        // 非空校验
        String msg = helpIsNull(help);
        if (null != msg) {
            jr = renderError(msg);
            return jr;
        }
        // 根据id区分新增/修改
        // 修改
        if (null != help.getId()) {
            try {
                helpService.updateById(help);
                jr = renderSuccess("修改帮助信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("修改帮助信息失败");
                return jr;
            }
        // 新增
        } else {
            help.setCreatorId(userId);
            help.setPublishDate(TimeStamp.getCurrentTime().getDate());
            try {
                helpService.insert(help);
                jr = renderSuccess("新增帮助信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("新增帮助信息失败");
                return jr;
            }
        }
        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("saveHelp......");
            log.debug("saveHelp...param help toString:{}", help.toString());
            log.debug("saveHelp...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title helpDetail
     * @description TODO 获取帮助详情
     * @author xiaokun
     * @date 2017-04-18 09:07
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param id
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/helpDetail")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_HELPLIST_HELPDETAIL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取帮助详情")
    public JsonResult helpDetail(@RequestParam("id") Integer id) {

        log.info("helpDetail......");

        Help help;
        try {
            help = helpService.selectById(id);
            jr = renderSuccess("获取帮助信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("获取帮助信息失败");
            return jr;
        }
        jr.setData("help", help);
        if (log.isDebugEnabled()) {
            log.info("helpDetail......");
            log.debug("helpDetail...param id:{}", id);
            log.debug("helpDetail...result help toString:{}", help.toString());
            log.debug("helpDetail...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title helpIsNull
     * @description TODO 帮助信息非空校验
     * @author xiaokun
     * @date 2017-04-18 17:02
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param help
     * @return String
     * @throws
     */
    public String helpIsNull(Help help) {
        String msg = null;
        if (null == help.getTitle()) {
            msg = "请输入帮助标题";
        } else if (null == help.getContent()) {
            msg = "请输入帮助内容";
        }
        return msg;
    }
}
