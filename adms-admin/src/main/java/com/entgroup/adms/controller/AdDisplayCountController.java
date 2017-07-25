package com.entgroup.adms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.ExportExcelUtil;
import com.entgroup.adms.util.PageInfo;
import com.entgroup.adms.vo.DataStatisticsVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author mxy
 * @ClassName: AdDisplayCountController
 * @Description: TODO 统计数据展示
 * @date 2017-04-19 17:19
 */
@Controller
@RequestMapping("/adDisplayCount")
public class AdDisplayCountController extends BaseController {

    /**
     * 系统模块名
     */
    static final String SYSTEM_MODULE = "统计数据展示";

    /**
     * @param model
     * @param dataStatisticsVO
     * @param pageNum
     * @param pageSize
     * @return String
     * @throws
     * @title adCount
     * @description TODO 统计数据-广告
     * @author mxy
     * @date 2017-04-19 17:23
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adCount")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "统计数据-广告")
    public String adCount(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize
    ) {
        log.info("adCount......");

        // 获取公司下拉框
        EntityWrapper<Company> entityWrapper = new EntityWrapper<>(
                new Company());
        entityWrapper.eq("status", 3).eq("deleted", 0).eq("company_type", 2);
        List<Company> allCompanyList = companyService.selectList(entityWrapper);

        Long companyId = long2Null(dataStatisticsVO.getCompanyId());
        Long adStyleId = long2Null(dataStatisticsVO.getAdStyleId());
        Long adId = long2Null(dataStatisticsVO.getAdId());
        Integer days = integer2Null(dataStatisticsVO.getDays());
        User shiroUser = getShiroUser();
        // 用于判断是否为管理员,若不为管理员（Admin==0），companyId=当前用户CompanyId
        if (shiroUser.getAdmin() == 0) {
            companyId = getShiroUser().getCompanyId();
        }
        if (days == null) {
            days = 7;
        }
        Date adCountTimeStart = getDateBefore(new Date(), days);
        Date adCountTimeEnd = new Date();


        // 获取广告相关曝光信息（按日期统计）
        List<AdDisplayCount> adDisplayCounts = adDisplayCountService.getAdCountList4Chart(companyId, adId, adStyleId,
                adCountTimeStart, adCountTimeEnd);
        // 根据获取广告相关曝光信息（按广告统计）
        Page<Ad> adPage = new Page(pageNum, pageSize);
        adPage.setOrderByField("ad_id");
        adPage.setAsc(false);
        adPage = adService.getAdCountList(adPage, companyId, adId, adStyleId, adCountTimeStart, adCountTimeEnd);

        int sumShowCount = 0;
        int sumUserCount = 0;
        for (AdDisplayCount adDisplayCount : adDisplayCounts) {
            Integer showCount = adDisplayCount.getShowCount();
            sumShowCount += showCount;
            Integer userCount = adDisplayCount.getUserCount();
            sumUserCount += userCount;
        }
        // 封装折线图DTO
        List<DisplayCountDTO> chartDTOList = Lists.newArrayList();
        for (AdDisplayCount adDisplayCount : adDisplayCounts) {
            DisplayCountDTO dto = new DisplayCountDTO();
            dto.setDayTime(adDisplayCount.getDayTime());
            dto.setSumShowCount(adDisplayCount.getShowCount() + "");
            dto.setSumUserCount(adDisplayCount.getUserCount() + "");
        }

        // 封装DTO
        List<DisplayCountDTO> dtoList = Lists.newArrayList();
        for (Ad ad : adPage.getRecords()) {
            DisplayCountDTO dto = new DisplayCountDTO();
            dto.setAdId(ad.getId());
            dto.setAdName(ad.getName());
            dto.setAdStyleId(ad.getAdStyle().getId());
            dto.setAdStyleName(ad.getAdStyle().getName());
            dto.setShowCount(ad.getShowCount());
            dto.setUserCount(ad.getUserCount());
            dtoList.add(dto);
        }

        PageInfo<Ad> page = new PageInfo(adPage);
        model.addAttribute("page", page);
        model.addAttribute("sumShowCount", sumShowCount);
        model.addAttribute("sumUserCount", sumUserCount);
        model.addAttribute("companyList", allCompanyList);
        model.addAttribute("chartDTOList", chartDTOList);
        model.addAttribute("dtoList", dtoList);
        //model.addAttribute("type", type);

        return "adDisplayCount/adCount";
    }

    /**
     * @return String
     * @throws
     * @title home
     * @description TODO 统计数据-首页
     * @author qmh
     * @date 2017-04-19 17:23
     * @modifier
     * @remark
     * @version V1.0
     */
//    @RequestMapping(value = "/homeOrderCount")
    //@ResponseBody()
    /*public String homeOrderCount(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @ModelAttribute("user") User user,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "orderId") String orderId) {

        log.info("orderHomeCount......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        //前一天日期--格式：yyyy-mm-dd
        Date beforeDateTemp = getDateBefore(new Date(), 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beforeDateStr = dateFormat.format(beforeDateTemp);

        System.out.println("1. companyId:" + companyId);
        System.out.println("2. orderId:" + orderId);
        System.out.println("3. pageNum:" + pageNum);
        System.out.println("4. pageSize:" + pageSize);
        System.out.println("5. beforeDay:" + beforeDateStr);
        //companyId = 1l;

        // 3. 获取公司合计(曝光总量、消费金额、账户余额、ctr)
        List<DisplayCountDTO> homeDisplayTotal = adDisplayCountService.selectHomeOtherList(beforeDateStr, companyId);
        //System.out.println("homeDisplayTotal:" + homeDisplayTotal);
        // 3.1 计算出合计值
        double homeDisplayTotalArr[] = {1, 2, 3, 4, 5};
        int showCount = 0;
        int clickCount = 0;
        int totalMoney = 0;
        int cosumeMoney = 0;
        double ctr = 0;
        for (DisplayCountDTO dis : homeDisplayTotal) {
            if (null != dis.getSumShowCount())
                showCount = showCount + Integer.parseInt(dis.getSumShowCount());
            else {
                showCount = showCount + 0;
            }
            if (null != dis.getSumClickCount())
                clickCount = clickCount + Integer.parseInt(dis.getSumClickCount());
            else {
                clickCount = clickCount + 0;
            }
            if (null != dis.getSumTotalMoney())
                totalMoney = totalMoney + Integer.parseInt(dis.getSumTotalMoney());
            else {
                totalMoney = totalMoney + 0;
            }
            if (null != dis.getSumCosumeMoney())
                cosumeMoney = cosumeMoney + Integer.parseInt(dis.getSumCosumeMoney());
            else {
            }
            cosumeMoney = cosumeMoney + 0;
        }
        // System.out.println("这什么:" + 1.0 * clickCount / showCount * 100);
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        if (clickCount > 0 && showCount > 0) {
            ctr = 1.0 * clickCount / showCount * 100;
            ctr = Double.valueOf(df.format(ctr));
        } else {
            ctr = 0;
        }


        homeDisplayTotalArr[0] = showCount;
        homeDisplayTotalArr[1] = clickCount;
        homeDisplayTotalArr[2] = totalMoney;
        homeDisplayTotalArr[3] = cosumeMoney;
        homeDisplayTotalArr[4] = ctr;
        // 4. 获取订单名称列表
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectOrderNameList(companyId);

        // 5. 获取折线统计图数据
        //System.out.println("orderOd" + orderId);
        String orderIdTemp = null;
        List<DisplayCountDTO> graphCountList = null;
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            //System.out.println("没有值传过来");
            graphCountList = adDisplayCountService.selectGraphCount(null, companyId);
        } else {
            //System.out.println("paramOrderId:" + orderId);
            graphCountList = adDisplayCountService.selectGraphCount(orderId, companyId);
        }

        Date graphDate = null;
        String dateArr[] = {"1", "2", "3", "4", "5", "6", "7"};
        String showCountArr[] = {"100", "2", "3", "4", "5", "6", "7"};
        String clickCountArr[] = {"1", "2", "3", "4", "5", "6", "7"};
        String ctrArr[] = {"1", "2", "3", "4", "5", "6", "7"};
        DisplayCountDTO dto = null;
        boolean judge = true;
        int j = 0;
        for (int i = 7; i >= 1; i--) {
            graphDate = getDateBefore(new Date(), i);
            String dateTemp = dateFormat.format(graphDate);
            //System.out.println("是相等的" + dateTemp);

            for (DisplayCountDTO displayCount : graphCountList) {
                dto = new DisplayCountDTO();
                // System.out.println("dto+：" + displayCount.getDayTime());
                String str = dateFormat.format(displayCount.getDayTime());
                //System.out.println("str" + str);

                if (dateTemp.equals(str)) {
                    // System.out.println("来过" + i + "次" + graphDate);

                    //System.out.println("----------------------------" + displayCount.getSumShowCount());
                    showCountArr[j] = displayCount.getSumShowCount();
                    clickCountArr[j] = displayCount.getSumClickCount();
                    ctrArr[j] = displayCount.getCtr();
                    dateArr[j] = dateTemp;
                    judge = false;
                    break;
                } else {
                    judge = true;
                }
            }
            if (judge) {

                showCountArr[j] = "0";
                clickCountArr[j] = "0";
                ctrArr[j] = "0";
                dateArr[j] = dateTemp;
            }
            j++;

            //showCountArr[j] = dateFormat.format(dto.getDayTime());
        }


        pageSize = 3;
        List<DisplayCountDTO> homeList = Lists.newArrayList();
        // 分页信息
        Page<DisplayCountDTO> homeListPage = new Page<>(pageNum, pageSize);
        homeListPage.setOrderByField("id");
        homeListPage.setAsc(false);
        homeListPage = adDisplayCountService.selectHomeDisplayCountPage(
                homeListPage, beforeDateStr, companyId);
        homeList = homeListPage.getRecords();
        // 页面传值
        PageInfo<DisplayCountDTO> page = new PageInfo<>(homeListPage);
        //page.setPages(100);
        System.out.println("homeList:" + homeList);
        System.out.println("homeList.size():" + homeList.size());
        System.out.println("--->page:" + page.getPages());
        System.out.println("maxPage:" + maxPage);
        maxPage = page.getPages();
        identification = "home";
        System.out.println("maxPage:" + maxPage);
        page.setPages(page.getPages());
        model.addAttribute("page", page);
        model.addAttribute("homeDisplayTotalArr", homeDisplayTotalArr);
        model.addAttribute("homeList", homeList);
        model.addAttribute("orderNameList", orderNameList);
        model.addAttribute("showCountArr", showCountArr);
        model.addAttribute("clickCountArr", clickCountArr);
        model.addAttribute("ctrArr", ctrArr);
        model.addAttribute("dateArr", dateArr);
        return "home";
    }*/

    /**
     * @return String
     * @throws
     * @title adCountList
     * @description TODO 统计数据-广告
     * @author qmh
     * @date 2017-06-14 17:12
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adCountList")
    public String adCountList(
            Model model, @ModelAttribute AdOrder adOrders,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false, value = "dayPeriod") Integer dayPeriod
    ) {
        log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long companyId = shiroUser.getCompanyId();
        pageSize = 3;
        if (null == orderId || "all".equals(orderId)) {
            orderId = null;
        }
        DecimalFormat df = new java.text.DecimalFormat("#.00");
        // 3. 获取合计(总曝光量、总点击量)
        List<DisplayCountDTO> adOtherList = adDisplayCountService.selectAdOtherList(7, null, companyId, 0);
        // 加入新增的数据
        AdOrder adOrder = new AdOrder();
        adOrder.setCompanyId(companyId);
        //List<DisplayCountResultDTO> addData = adDisplayCountService.getAdDisplayCountByAd(adOrder);

        DisplayCountDTO adOtherListTemp = null;


        // 3.1 计算出合计值
        String adDisplayTotalArr[] = {"1", "2"};
        Double showCount = 0D;
        Double clickCount = 0D;
        if (null != adOtherList.get(0)) {
            for (DisplayCountDTO dis : adOtherList) {
                if (null != dis.getSumShowCount())
                    showCount = showCount + Double.parseDouble(dis.getSumShowCount());
                else {
                    showCount = showCount + 0;
                }
                if (null != dis.getSumClickCount())
                    clickCount = clickCount + Double.parseDouble(dis.getSumClickCount());
                else {
                    clickCount = clickCount + 0;
                }
            }
        }
        //ctr = Math.round(clickCount / showCount * 100);
        Format fm = new DecimalFormat("#,###");
        adDisplayTotalArr[0] = fm.format(showCount);
        adDisplayTotalArr[1] = fm.format(clickCount);
        // 4. 获取订单名称集合
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectOrderNameList(companyId, 0);
        // 5. 获取折线统计图数据
        String orderIdTemp = null;
        List<DisplayCountDTO> adgraphCountList = null;
        if ("null" != orderId || "all" != orderId) {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(7, null, companyId, 0);

        } else {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(7, orderId, companyId, 0);
        }
        String dateArr[] = null;
        String showCountArr[] = null;
        String clickCountArr[] = null;
        String ctrArr[] = null;
        if (null == dayPeriod) {
            dayPeriod = 7;
        }
        dateArr = new String[dayPeriod];
        showCountArr = new String[dayPeriod];
        clickCountArr = new String[dayPeriod];
        ctrArr = new String[dayPeriod];
        Date graphDate = null;
        DisplayCountDTO dto = null;
        boolean judge = true;
        int j = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = dayPeriod; i >= 1; i--) {
            graphDate = getDateBefore(new Date(), i);
            String dateTemp = dateFormat.format(graphDate);
            for (DisplayCountDTO displayCount : adgraphCountList) {
                dto = new DisplayCountDTO();
                String str = dateFormat.format(displayCount.getDayTime());
                if (dateTemp.equals(str)) {
                    showCountArr[j] = displayCount.getSumShowCount();
                    clickCountArr[j] = displayCount.getSumClickCount();
                    ctrArr[j] = displayCount.getCtr();
                    dateArr[j] = dateTemp;
                    judge = false;
                    break;
                } else {
                    judge = true;
                }
            }
            if (judge) {
                showCountArr[j] = "0";
                clickCountArr[j] = "0";
                ctrArr[j] = "0";
                dateArr[j] = dateTemp;
            }
            j++;
        }
        // 分页信息
        List<DisplayCountResultDTO> adDisplayCountList = Lists.newArrayList();
        Page<DisplayCountResultDTO> orderListPage = new Page<>(pageNum, pageSize);
        orderListPage.setOrderByField("id");
        orderListPage.setAsc(false);
        orderListPage = adDisplayCountService.getAdDisplayCountByAd(
                orderListPage, adOrder);
        adDisplayCountList = orderListPage.getRecords();
        // 列表中新增数据
        for (DisplayCountResultDTO rdto : adDisplayCountList) {
            rdto.setTotalMoney(rdto.getTotalMoney()-rdto.getCosumeMoney());
        }
        //将其中Null值替换为0
        Double showCountTemp = 0D;
        for(DisplayCountResultDTO rdto: adDisplayCountList){
            if(null == rdto.getShowCounts()){
                rdto.setShowCounts(0D);
            }else{
                showCountTemp = rdto.getShowCounts() /10000;
                rdto.setShowCounts(Double.parseDouble(df.format(showCountTemp)));
            }
            if(null == rdto.getClickCounts()){
                rdto.setClickCounts(0D);
            }
            if(null == rdto.getCtr()){
                rdto.setCtr(0D);
            }
            if(null == rdto.getCosumeMoney()){
                rdto.setCosumeMoney(0D);
            }
            if(null == rdto.getTotalMoney()){
                rdto.setTotalMoney(0D);
            }
        }
        // 页面传值
        PageInfo<DisplayCountResultDTO> page = new PageInfo<>(orderListPage);
        for (DisplayCountResultDTO adto : adDisplayCountList) {
            adto.setBeginTime(adto.getBeginTime().substring(0, 10));
            adto.setEndTime(adto.getEndTime().substring(0, 10));
        }
        maxPage = page.getPages();
        identification = "adCountList";
        model.addAttribute("page", page);
        model.addAttribute("adDisplayCountList", adDisplayCountList);
        model.addAttribute("adDisplayTotalArr", adDisplayTotalArr);
        model.addAttribute("orderNameList", orderNameList);
        model.addAttribute("showCountArr", showCountArr);
        model.addAttribute("clickCountArr", clickCountArr);
        model.addAttribute("ctrArr", ctrArr);
        model.addAttribute("dateArr", dateArr);
        return "adDisplayCount/adCountList";
    }

    /**
     * @return String
     * @throws
     * @title home/基于广告
     * @description TODO 折线图数据
     * @author qmh
     * @date 2017-06-15 10:35
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adChartCountList")
    public String chartUtil(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false, value = "dayPeriod") Integer dayPeriod
    ) {
        log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        String orderIdTemp = null;
        List<DisplayCountDTO> adgraphCountList = null;
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(dayPeriod, null, companyId, 0);
        } else {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(dayPeriod, orderId, companyId, 0);
        }
        String dateArr[] = null;
        String showCountArr[] = null;
        String clickCountArr[] = null;
        String ctrArr[] = null;
        if (null == dayPeriod) {
            dayPeriod = 7;
        }
        dateArr = new String[dayPeriod];
        showCountArr = new String[dayPeriod];
        clickCountArr = new String[dayPeriod];
        ctrArr = new String[dayPeriod];
        Date graphDate = null;
        DisplayCountDTO dto = null;
        boolean judge = true;
        int j = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = dayPeriod; i >= 1; i--) {
            graphDate = getDateBefore(new Date(), i);
            String dateTemp = dateFormat.format(graphDate);
            for (DisplayCountDTO displayCount : adgraphCountList) {
                dto = new DisplayCountDTO();
                String str = dateFormat.format(displayCount.getDayTime());
                if (dateTemp.equals(str)) {
                    showCountArr[j] = displayCount.getSumShowCount();
                    clickCountArr[j] = displayCount.getSumClickCount();
                    ctrArr[j] = displayCount.getCtr();
                    dateArr[j] = dateTemp;
                    judge = false;
                    break;
                } else {
                    judge = true;
                }
            }
            if (judge) {
                showCountArr[j] = "0";
                clickCountArr[j] = "0";
                ctrArr[j] = "0";
                dateArr[j] = dateTemp;
            }
            j++;
        }
        // 曝光量与点击量
        List<DisplayCountDTO> adOtherList = null;
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            adOtherList = adDisplayCountService.selectAdOtherList(dayPeriod, null, companyId, 0);
        } else {
            adOtherList = adDisplayCountService.selectAdOtherList(dayPeriod, orderId, companyId, 0);
        }
        // 3.1 计算出合计值
        if ("adCountList".equalsIgnoreCase(identification)) {
            String adDisplayTotalArr[] = {"1", "2"};
            int showCount = 0;
            int clickCount = 0;
            if (null != adOtherList.get(0)) {
                for (DisplayCountDTO dis : adOtherList) {
                    if (null != dis.getSumShowCount())
                        showCount = showCount + Integer.parseInt(dis.getSumShowCount());
                    else {
                        showCount = showCount + 0;
                    }
                    if (null != dis.getSumClickCount())
                        clickCount = clickCount + Integer.parseInt(dis.getSumClickCount());
                    else {
                        clickCount = clickCount + 0;
                    }
                }
            }
            Format fm = new DecimalFormat("#,###");
            adDisplayTotalArr[0] = fm.format(showCount);
            adDisplayTotalArr[1] = fm.format(clickCount);
            model.addAttribute("adDisplayTotalArr", adDisplayTotalArr);
        }
        model.addAttribute("showCountArr", showCountArr);
        model.addAttribute("clickCountArr", clickCountArr);
        model.addAttribute("ctrArr", ctrArr);
        model.addAttribute("dateArr", dateArr);
        if ("home".equalsIgnoreCase(identification)) {
            return "adDisplayCount/home";
        } else {
            return "c/adCountList";
        }
    }

    /**
     * @return String
     * @throws
     * @title home
     * @description TODO 统计数据-首页列表(分页局部刷新)
     * @author qmh
     * @date 2017-06-16 14:23
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adOrderCount")
    public String adOrderCount(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "pageNum") Integer pageNum
    ) {
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
                        showCountTemp = Double.parseDouble(ddto.getSumShowCount());
                    }
                    if(null != ddto.getSumClickCount()){
                        rdto.setClickCounts(Double.parseDouble(ddto.getSumClickCount()));
                        clickCountTemp = Double.parseDouble(ddto.getSumClickCount());
                    }
                    if(showCountTemp > 0 && rdto.getClickCounts() >0){
                        ctr = Double.valueOf(df.format(clickCountTemp/showCount));
                        rdto.setCtr(ctr);
                        rdto.setShowCounts(Double.parseDouble(df.format((Double.parseDouble(ddto.getSumShowCount())/10000))));
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

    /**
     * @return String
     * @throws
     * @title 基于视频平台
     * @description TODO 视频平台的统计数据
     * @author qmh
     * @date 2017-06-19 17:23
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/videoForPlatCount")
    public String videoForPlatCount(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "pageNum") Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false, value = "dayPeriod") Integer dayPeriod
    ) {
        log.info("videoForPlatCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        //2. 需要对值进行判断的，进行处理
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            orderId = null;
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        //3. 订单下拉列表数据
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectVideoAndProgramOrderNameList(companyId);
        //4. 分页信息与列表信息
        List<DisplayCountDTO> videoForPlatCount = Lists.newArrayList();
        Page<DisplayCountDTO> videoForPlatCountPage = new Page<>(pageNum, 3);
        videoForPlatCountPage.setOrderByField("id");
        videoForPlatCountPage.setAsc(false);
        // 匹配信息，将广告位总数加入原有信息列表中
        String videoTemp = "";
        AdOrder adOrder = new AdOrder();
        adOrder.setCompanyId(companyId);
        AdDisplayCount adDisplayCount = new AdDisplayCount();
        adDisplayCount.setDayPeriod(dayPeriod);
        adDisplayCount.setOrderId(orderId);
        adDisplayCount.setCompanyId(companyId);
        Page<DisplayCountDTO> videoForPlatCountAdCountPage = adDisplayCountService.selectVideoForPlatCountPage(videoForPlatCountPage,adDisplayCount);
        //videoForPlatCount = videoForPlatCountAdCount.getRecords();
        List<DisplayCountDTO> videoForPlatCountAdCount = videoForPlatCountAdCountPage.getRecords();
        // edited by xiaokun on 2017-06-30 10:34 begin
        if (companyId != companyService.selectOne(new EntityWrapper<Company>().eq("company_name", "艺恩")).getId()) {
            adOrder.setCompanyId(companyId);
        }
        if (null != orderId) {
            adOrder.setId(orderId);
        }
        List<DisplayCountResultDTO> displayCountResultDTOList = adDisplayCountService.getAdDisplayCountByPlatform(adOrder);
        model.addAttribute("displayCountResultDTOList", displayCountResultDTOList);
        // edited by xiaokun on 2017-06-30 10:34 end
        // 已完成与未完成数据匹配
        Integer slotCount = 0;
        String slotOnline = "0";
        String slotOffline = "0";
        Double showCount = 0D;
        Double clickCount = 0D;
        Double cash = 0D;
        Double ctr = 0D;
        //PageInfo<DisplayCountDTO> page = new PageInfo<>(videoForPlatCountAdCount);
        //5. 饼图中数据需要对第4条获取到的数据，进行相应处理(4个数组---4个饼图)
        int i = 0;
        List showCountlist = new ArrayList();
        List clickCountList = new ArrayList();
        List ctrList = new ArrayList();
        List cosumeMoneyList = new ArrayList();
        Integer slotOnLineNum = 0;
        Integer slotOfflineNum = 0;
        if (videoForPlatCountAdCount.size() != 0 && null != videoForPlatCountAdCount.get(0)) {
            String videoPlatName[] = new String[videoForPlatCountAdCount.size()];
            for (DisplayCountDTO dto : videoForPlatCountAdCount) {
                Map<String, String> showCountMap = new HashMap<>();
                Map<String, String> clickCountMap = new HashMap<>();
                Map<String, String> ctrMap = new HashMap<>();
                Map<String, String> cosumeMoneyMap = new HashMap<>();
                videoPlatName[i] = dto.getCompanyName();
                if(null != dto.getSumShowCount()){
                    Double showCounts = 0D;
                    showCounts = Double.parseDouble(dto.getSumShowCount()) * 10000;

                    dto.setSumShowCount(showCounts.toString());
                }

                if("华数tv".equals(dto.getCompanyName())){
                    showCountMap.put("name", "华数tv");
                    showCountMap.put("value", "0");
                    clickCountMap.put("name", "华数tv");
                    clickCountMap.put("value", "0");
                    cosumeMoneyMap.put("name","华数tv");
                    cosumeMoneyMap.put("value", "0");
                    ctrMap.put("name", "华数tv");
                    ctrMap.put("value","0");
                }else {
                    showCountMap.put("name", (null == dto.getCompanyName() ? "0" : dto.getCompanyName()));
                    showCountMap.put("value", (null == dto.getSumShowCount() ? "0" : dto.getSumShowCount().toString()));
                    clickCountMap.put("name", (null == dto.getCompanyName() ? "0" : dto.getCompanyName()));
                    clickCountMap.put("value", (null == dto.getSumClickCount() ? "0" : dto.getSumClickCount().toString()));
                    cosumeMoneyMap.put("name", (null == dto.getCompanyName() ? "0" : dto.getCompanyName()));
                    cosumeMoneyMap.put("value", (null == dto.getSumCosumeMoney() ? "0" : dto.getSumCosumeMoney().toString()));
                    ctrMap.put("name", (null == dto.getCompanyName() ? "0" : dto.getCompanyName()));
                    ctrMap.put("value", (null == dto.getCtr() ? "0" : dto.getCtr().toString()));
                }i++;

                showCountlist.add(showCountMap);
                clickCountList.add(clickCountMap);
                ctrList.add(ctrMap);
                cosumeMoneyList.add(cosumeMoneyMap);
            }
            model.addAttribute("videoPlatName", videoPlatName);
            model.addAttribute("showCountlist", showCountlist);
            model.addAttribute("clickCountList", clickCountList);
            model.addAttribute("ctrList", ctrList);
            model.addAttribute("cosumeMoneyList", cosumeMoneyList);
        } else {
            String videoPlatName[] = {"暂无数据"};
            model.addAttribute("videoPlatName", videoPlatName);
        }
        //6. 将对象传送回页面
        //model.addAttribute("page", page);
        //model.addAttribute("videoForPlatCount", videoForPlatCount);
        model.addAttribute("orderNameList", orderNameList);
        return "adDisplayCount/videoForPlatCount";
    }

    /**
     * @return String
     * @throws
     * @title 基于节目类型
     * @description TODO 基于节目类型的统计数据
     * @author qmh
     * @date 2017-06-19 13:23
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/programTypeCount")
    public String programTypeCount(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "pageNum") Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false, value = "dayPeriod") Integer dayPeriod
    ) {
        log.info("programTypeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();


        //2. 需要对值进行判断的，进行处理
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            orderId = null;
        }
        if (null == pageNum) {
            pageNum = 1;
        }

        //3. 订单下拉列表数据
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectVideoAndProgramOrderNameList(companyId);
        //4. 分页信息与列表信息
        List<DisplayCountDTO> programTypeCount = Lists.newArrayList();
        Page<DisplayCountDTO> videoForPlatCountPage = new Page<>(pageNum, 3);
        videoForPlatCountPage.setOrderByField("id");
        videoForPlatCountPage.setAsc(false);

        // 匹配信息，将广告位总数加入原有信息列表中
        String videoTemp = "";
        AdOrder adOrder = new AdOrder();
        adOrder.setCompanyId(companyId);
        AdDisplayCount adDisplayCount = new AdDisplayCount();
        adDisplayCount.setDayPeriod(dayPeriod);
        adDisplayCount.setOrderId(orderId);
        adDisplayCount.setCompanyId(companyId);
        Page<DisplayCountDTO> videoForPlatCountAdCount = adDisplayCountService.selectProgramTypeCountPage(videoForPlatCountPage,adDisplayCount);
        programTypeCount = videoForPlatCountAdCount.getRecords();
        //PageInfo<DisplayCountResultDTO> page = new PageInfo<>(programTypeCount);
        // edited by xiaokun on 2017-06-30 10:34 begin
        if (companyId != companyService.selectOne(new EntityWrapper<Company>().eq("company_name", "艺恩")).getId()) {
            adOrder.setCompanyId(companyId);
        }
        if (null != orderId) {
            adOrder.setId(orderId);
        }
        List<DisplayCountResultDTO> displayCountResultDTOList = adDisplayCountService.getAdDisplayCountByProgramType(adOrder);
        model.addAttribute("displayCountResultDTOList", displayCountResultDTOList);
        // edited by xiaokun on 2017-06-30 10:34 end

        //5. 饼图中数据需要对第4条获取到的数据，进行相应处理(4个数组---4个饼图)
        int i = 0;
        List showCountlist = new ArrayList();
        List clickCountList = new ArrayList();
        List ctrList = new ArrayList();
        List cosumeMoneyList = new ArrayList();
        Integer slotOnLineNum = 0;
        Integer slotOfflineNum = 0;
        boolean judge ;
        if (programTypeCount.size() != 0 && null != programTypeCount.get(0)) {
            String videoPlatName[] = new String[programTypeCount.size()];
            for (DisplayCountDTO dto : programTypeCount) {
                judge = true;
                Map<String, String> showCountMap = new HashMap<>();
                Map<String, String> clickCountMap = new HashMap<>();
                Map<String, String> ctrMap = new HashMap<>();
                Map<String, String> cosumeMoneyMap = new HashMap<>();
                videoPlatName[i] = dto.getProgramName();
                if(null != dto.getSumShowCount()){
                    Double showCounts = 0D;
                    showCounts = Double.parseDouble(dto.getSumShowCount()) * 10000;
                    dto.setSumShowCount(showCounts.toString());
                }
                if("综艺".equals(dto.getProgramName())){
                    showCountMap.put("name", "综艺");
                    showCountMap.put("value", "0");
                    clickCountMap.put("name", "综艺");
                    clickCountMap.put("value", "0");
                    cosumeMoneyMap.put("name","综艺");
                    cosumeMoneyMap.put("value", "0");
                    ctrMap.put("name", "综艺");
                    ctrMap.put("value","0");
                }else if("电影".equals(dto.getProgramName())){
                    showCountMap.put("name", "电影");
                    showCountMap.put("value", "0");
                    clickCountMap.put("name", "电影");
                    clickCountMap.put("value", "0");
                    cosumeMoneyMap.put("name","电影");
                    cosumeMoneyMap.put("value", "0");
                    ctrMap.put("name", "电影");
                    ctrMap.put("value","0");
                }else {
                    // 将相关信息加入列表
                    showCountMap.put("name", (null == dto.getProgramName() ? "0" : dto.getProgramName()));
                    showCountMap.put("value", (null == dto.getSumShowCount() ? "0" : dto.getSumShowCount().toString()));
                    clickCountMap.put("name", (null == dto.getProgramName() ? "0" : dto.getProgramName()));
                    clickCountMap.put("value", (null == dto.getSumClickCount() ? "0" : dto.getSumClickCount().toString()));
                    cosumeMoneyMap.put("name", (null == dto.getProgramName() ? "0" : dto.getProgramName()));
                    cosumeMoneyMap.put("value", (null == dto.getSumCosumeMoney() ? "0" : dto.getSumCosumeMoney().toString()));
                    ctrMap.put("name", (null == dto.getProgramName() ? "0" : dto.getProgramName()));
                    ctrMap.put("value", (null == dto.getCtr() ? "0" : dto.getCtr().toString()));
                }
                i++;
                showCountlist.add(showCountMap);
                clickCountList.add(clickCountMap);
                ctrList.add(ctrMap);
                cosumeMoneyList.add(cosumeMoneyMap);
            }
            model.addAttribute("videoPlatName", videoPlatName);
            model.addAttribute("showCountlist", showCountlist);
            model.addAttribute("clickCountList", clickCountList);
            model.addAttribute("ctrList", ctrList);
            model.addAttribute("cosumeMoneyList", cosumeMoneyList);
        } else {
            String videoPlatName[] = {"暂无数据"};
            model.addAttribute("videoPlatName", videoPlatName);
        }
        //6. 将对象传送回页面
        //model.addAttribute("page", page);
        //model.addAttribute("programTypeCount", programTypeCount);
        model.addAttribute("orderNameList", orderNameList);
        return "adDisplayCount/programTypeCount";
    }


    @RequestMapping("/exportDisplayCount")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "导出统计数据列表")
    public String exportDisplayCount(@RequestParam("exportItem") String exportItem,
                                     HttpServletResponse response,
                                     Model model) throws Exception {
        log.info("exportDisplayCount......");

        if (null == exportItem) {
            return null;
        }

        User shiroUser = getShiroUser();
        Long companyId = shiroUser.getCompanyId();

        AdOrder adOrder = new AdOrder();

        if (companyId != companyService.selectOne(new EntityWrapper<Company>().like("company_name", "艺恩")).getId()) {
            adOrder.setCompanyId(companyId);
        }

        List<DisplayCountResultDTO> dtoList = null;
        Page<DisplayCountResultDTO> displayCountResultDTOPage = new Page<>(1, 10000);

        // 封装数据
        Map<String, Object> map;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        List<Map<String, Object>> list = Lists.newArrayList();
        String title = "";
        String[][] headers = null;

        DecimalFormat df = new DecimalFormat("#.00");

        if (exportItem.equals("exportAd")) {
            // 基于广告
            dtoList = adDisplayCountService.getAdDisplayCountByAd(displayCountResultDTOPage, adOrder).getRecords();
            title = "基于广告";
            headers = new String[][] {
                    { "1", "订单名称" },
                    { "2", "投放日期" },
                    { "3", "曝光量/万" },
                    { "4", "点击量" },
                    { "5", "CTR" },
                    { "6", "花费" },
                    { "7", "余额" }
            };

            for (DisplayCountResultDTO dto : dtoList) {
                map = new HashMap<String, Object>();

                map.put("1", dto.getOrderName());
                map.put("2", dto.getBeginTime().split(" ")[0].replace("-", "/") + " ~ " + dto.getEndTime().split(" ")[0].replace("-", "/"));
                map.put("3", df.format(null == dto.getShowCounts() ? 0 : dto.getShowCounts()/10000).equals(".00") ? 0 : df.format(null == dto.getShowCounts() ? 0 : dto.getShowCounts()/10000));
                map.put("4", (int)(null == dto.getClickCounts() ? 0 : dto.getClickCounts()));
                map.put("5", (null == dto.getCtr() ? 0 : df.format(dto.getCtr()).equals(".00") ? 0 : df.format(dto.getCtr())) + "%");
                map.put("6", df.format(dto.getCosumeMoney()).equals(".00") ? 0 : df.format(dto.getCosumeMoney()));
                map.put("7", df.format(dto.getTotalMoney()-dto.getCosumeMoney()).equals(".00") ? "0.00" : df.format(dto.getTotalMoney()-dto.getCosumeMoney()));

                list.add(map);
            }
        } else if (exportItem.equals("exportPlatform")) {
            // 基于视频平台
            dtoList = adDisplayCountService.getAdDisplayCountByPlatform(adOrder);
            title = "基于视频平台";
            headers = new String[][] {
                    { "1", "平台名称" },
                    { "2", "广告位数量" },
                    { "3", "曝光量/万" },
                    { "4", "点击量" },
                    { "5", "CTR" },
                    { "6", "花费" }
            };

            for (DisplayCountResultDTO dto : dtoList) {
                map = new HashMap<String, Object>();

                map.put("1", dto.getPlatformName());
                map.put("2", dto.getSlotCount());
                map.put("3", df.format(null == dto.getShowCounts() ? 0 : (dto.getShowCounts()/10000)).equals(".00") ? 0 : df.format(null == dto.getShowCounts() ? 0 : (dto.getShowCounts()/10000)));
                map.put("4", (int)(null == dto.getClickCounts() ? 0 : dto.getClickCounts()));
                map.put("5", (null == dto.getCtr() ? 0 : df.format(dto.getCtr()).equals(".00") ? 0 : df.format(dto.getCtr())) + "%");
                map.put("6", df.format(null == dto.getCtr() ? 0 : dto.getCash()).equals(".00") ? "0.00" : df.format(null == dto.getCtr() ? 0 : dto.getCash()));

                list.add(map);
            }
        } else if (exportItem.equals("exportProgramType")) {
            // 基于节目类型
            dtoList = adDisplayCountService.getAdDisplayCountByProgramType(adOrder);
            title = "基于节目类型";
            headers = new String[][] {
                    { "1", "节目类型" },
                    { "2", "广告位数量" },
                    { "3", "曝光量/万" },
                    { "4", "点击量" },
                    { "5", "CTR" },
                    { "6", "花费" }
            };

            for (DisplayCountResultDTO dto : dtoList) {
                map = new HashMap<String, Object>();

                map.put("1", dto.getProgramTypeName());
                map.put("2", dto.getSlotCount());
                map.put("3", df.format(null == dto.getShowCounts() ? 0 : (dto.getShowCounts()/10000)).equals(".00") ? 0 : df.format(null == dto.getShowCounts() ? 0 : (dto.getShowCounts()/10000)));
                map.put("4", (int)(null == dto.getClickCounts() ? 0 : dto.getClickCounts()));
                map.put("5", (null == dto.getCtr() ? 0 : df.format(dto.getCtr()).equals(".00") ? 0 : df.format(dto.getCtr())) + "%");
                map.put("6", df.format(null == dto.getCtr() ? 0 : dto.getCash()).equals(".00") ? "0.00" : df.format(null == dto.getCtr() ? 0 : dto.getCash()));

                list.add(map);
            }
        } else {
            return null;
        }
        ExportExcelUtil e = new ExportExcelUtil();
        e.exportExcel(title, headers, list, response);
        return null;
    }
}