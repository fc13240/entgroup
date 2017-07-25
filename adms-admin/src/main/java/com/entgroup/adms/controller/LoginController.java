/**
 * @Title: HomeController.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @author mengqch
 * @date 2015年9月4日
 * @version V1.0
 */
package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.conf.AdmsConstants;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.util.PageInfo;
import com.entgroup.adms.vo.DataStatisticsVO;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author hpb
 * @Description:
 * @date 2017/2/28
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        log.debug("login...");
        // 如果用户直接到登录页面 先退出一下
        // 原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            if (log.isDebugEnabled()) {
                log.debug("logout....");
            }
            removeSession(AdmsConstants.SESSION_USER_KEY);
            subject.logout();
        }
        return "login";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model,
                       @ModelAttribute DataStatisticsVO dataStatisticsVO,
                       @ModelAttribute("user") User user,
                       @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
                       @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(required = false, value = "orderId") String orderId,
                       HttpServletRequest request) {

        log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        //前一天日期--格式：yyyy-mm-dd
        Date beforeDateTemp = getDateBefore(new Date(), 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beforeDateStr = dateFormat.format(beforeDateTemp);
        // 将首页上方的所有信息计算开始
        // 昨日订单上方总计
        double homeDisplayTotalArr[] = {1, 2, 3, 4, 5};
        double showCount = 0;
        double clickCount = 0;
        double totalMoney = 0;
        double cosumeMoney = 0;
        double ctr = 0;
        AdOrder adOrder = new AdOrder();
        adOrder.setCompanyId(companyId);
        List<DisplayCountResultDTO> homeList = adDisplayCountService.selectHomeDisplayCountList(adOrder);
        for (DisplayCountResultDTO rdto:homeList){
            if(null != rdto.getTotalMoney()){
                totalMoney = totalMoney + rdto.getTotalMoney();
            }
            if(null != rdto.getCosumeMoney()){
                cosumeMoney = cosumeMoney + rdto.getCosumeMoney();
            }
        }
        //这是昨日订单上方余额
        totalMoney = totalMoney - cosumeMoney;
        cosumeMoney = 0;
        //昨日花费
        List<DisplayCountDTO> cosumeList = adDisplayCountService.staOrderCosumeList(beforeDateStr);
        for(DisplayCountResultDTO rdto:homeList) {
            for (DisplayCountDTO ddto : cosumeList) {
                if(rdto.getOrderId().equals(ddto.getOrderId())) {
                    if (null != ddto.getOrderTotalPrice()) {
                        cosumeMoney = cosumeMoney + ddto.getOrderTotalPrice();
                    }
                }
            }
        }
        // 昨日曝光量与昨日点击量
        List<DisplayCountDTO> showAndClickList = adDisplayCountService.selectHomeDisplayCountPage(beforeDateStr,companyId);
        for(DisplayCountDTO ddto:showAndClickList){
            for(DisplayCountResultDTO rdto:homeList){
                if(ddto.getOrderId().equals(rdto.getOrderId())){
                    if(null != ddto.getSumShowCount()) {
                        showCount = showCount + Double.parseDouble(ddto.getSumShowCount());

                    }
                    if(null != ddto.getSumClickCount()) {
                        clickCount = clickCount + Double.parseDouble(ddto.getSumClickCount());
                    }
                }
            }
        }
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        if (clickCount > 0 && showCount > 0) {
            ctr = 1.0 * clickCount / showCount ;
            ctr = Double.valueOf(df.format(ctr));
        } else {
            ctr = 0;
        }
        homeDisplayTotalArr[0] = showCount;
        homeDisplayTotalArr[1] = clickCount;
        homeDisplayTotalArr[2] = totalMoney;
        homeDisplayTotalArr[3] = cosumeMoney;
        homeDisplayTotalArr[4] = ctr;
        // 将首页上方的所有信息计算完毕

        // 首页下方信息列表
        // 分页信息
        List<DisplayCountResultDTO> adDisplayCountList = Lists.newArrayList();
        Page<DisplayCountResultDTO> orderListPage = new Page<>(pageNum, 3);
        orderListPage.setOrderByField("id");
        orderListPage.setAsc(false);
        orderListPage = adDisplayCountService.getAdDisplayCountByAd(
                orderListPage, adOrder);
        adDisplayCountList = orderListPage.getRecords();
        // 页面传值
        PageInfo<DisplayCountResultDTO> page = new PageInfo<>(orderListPage);
        // 设置其日期时间格式
        for (DisplayCountResultDTO adto : adDisplayCountList) {
            adto.setBeginTime(adto.getBeginTime().substring(0, 10));
            adto.setEndTime(adto.getEndTime().substring(0, 10));
        }
        boolean judge = true;
        for(DisplayCountResultDTO rdto:adDisplayCountList) {
            judge = true;
            for (DisplayCountDTO ddto : cosumeList) {
                if(rdto.getOrderId().equals(ddto.getOrderId())){
                    if(null != ddto.getOrderTotalPrice()) {
                        rdto.setCosumeMoney(ddto.getOrderTotalPrice());
                    }
                        judge = false;
                        break;
                }
            }
            if(judge == true){
                rdto.setCosumeMoney(0D);
            }
        }
        Double totalMoneyTemp = 0D;
        Double cosumeMoneyTemp = 0D;
        for(DisplayCountResultDTO rdto:adDisplayCountList){
            for(DisplayCountResultDTO rdtoTemp:homeList){
                if(rdto.getOrderId().equals(rdtoTemp.getOrderId())){
                    rdto.setTotalMoney((rdtoTemp.getTotalMoney()-rdto.getCosumeMoney()));
                    if(null != rdtoTemp.getTotalMoney()){
                        totalMoneyTemp = rdtoTemp.getTotalMoney();
                    }else{
                        totalMoneyTemp = 0D;
                    }
                    if(null != rdtoTemp.getCosumeMoney()){
                        cosumeMoneyTemp = rdtoTemp.getCosumeMoney();
                    }else{
                        cosumeMoneyTemp = 0D;
                    }
                    rdto.setTotalMoney((totalMoneyTemp-cosumeMoneyTemp));

                }
            }
        }
        Double showCountTemp = 0D;
        Double clickCountTemp = 0D;
        for(DisplayCountResultDTO rdto:adDisplayCountList){
            judge = true;
            for(DisplayCountDTO ddto:showAndClickList){
                if(ddto.getOrderId().equals(rdto.getOrderId())){
                    if(null != ddto.getSumShowCount()) {
                        rdto.setShowCounts(Double.parseDouble(ddto.getSumShowCount()));
                        showCountTemp = Double.parseDouble(ddto.getSumShowCount() );
                    }else{
                        rdto.setShowCounts(0D);
                    }
                    if(null != ddto.getSumClickCount()){
                        rdto.setClickCounts(Double.parseDouble(ddto.getSumClickCount()));
                        clickCountTemp = Double.parseDouble(ddto.getSumClickCount());
                    }else{
                        rdto.setClickCounts(0D);
                    }
                    if(showCountTemp > 0){
                        ctr = Double.valueOf(df.format(clickCountTemp/showCount));
                        rdto.setCtr(ctr);
                        rdto.setShowCounts(Double.parseDouble(df.format((Double.parseDouble(ddto.getSumShowCount())/10000))));
                    }else{
                        rdto.setCtr(0D);
                    }
                    judge = false;
                    break;
                }
            }
            if(judge == true){
                rdto.setShowCounts(0D);
                rdto.setClickCounts(0D);
                rdto.setCtr(0D);
            }
        }
        // 4. 获取订单名称列表
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectOrderNameList(companyId,0);

        model.addAttribute("page", page);
        model.addAttribute("homeDisplayTotalArr", homeDisplayTotalArr);
        model.addAttribute("adDisplayCountList", adDisplayCountList);
        model.addAttribute("orderNameList", orderNameList);
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
        model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
        model.addAttribute("errorMessage", "*用户|密码输入错误");
        return "login";
    }

}
