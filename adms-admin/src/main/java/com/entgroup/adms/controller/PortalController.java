package com.entgroup.adms.controller;

import com.entgroup.adms.aop.SystemControllerLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hpb
 * @ClassName: PortalController
 * @Description: 官网
 * @date 2017/2/28
 */
@Controller("/portal")
public class PortalController extends BaseController {

    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "网站门户";

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: portal
     * @Description: 首页
     */
    @RequestMapping(value = "/")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "访问网站首页")
    public String portal() {
        return "login";
    }


    @RequestMapping(value = "/contact")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "浏览联系我们页面")
    public String contact() {
        return "portal/contact";
    }

    @RequestMapping(value = "/about")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "浏览关于我们页面")
    public String about() {
        return "portal/about";
    }

    /**
     * @param @return
     * @return String
     * @throws
     * @Title: help
     * @Description: 帮助中心
     */
    @RequestMapping(value = "/help")
    public String help() {
        return "portal/help";
    }

}