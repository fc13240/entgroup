package com.entgroup.adms.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.PageInfo;
import com.entgroup.adms.vo.DataStatisticsVO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by guop-i7-4770 on 2017/6/15.
 */
@Controller
@RequestMapping("/adAdminDisplayCount")
public class AdAdminDisplayCountController extends BaseController{

    /**
     * 系统模块名
     */
    static final String SYSTEM_MODULE = "统计数据展示";


    /**
     * @return String
     * @throws
     * @title adAdminCountList
     * @description TODO 统计数据-管理员
     * @author qmh
     * @date 2017-06-15 17:12
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adAdminCountList")
    public String adAdminCountList(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false,value = "dayPeriod") Integer dayPeriod,
            @RequestParam(required = false,value = "companyId") Long companyId
    ) {
         log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        // 3. 获取合计(总曝光量、总点击量)
        List<DisplayCountDTO> adOtherList = adDisplayCountService.selectAdOtherList(7,null,0,userId.intValue());
        // 3.1 计算出合计值
        String adDisplayTotalArr[] = {"1","2"};
        int showCount = 0;
        int clickCount = 0;
        if(null != adOtherList.get(0)) {
            for (DisplayCountDTO dis : adOtherList) {
                if (null != dis.getSumShowCount()) {
                    showCount = showCount + Integer.parseInt(dis.getSumShowCount());
                } else {
                    showCount = showCount + 0;
                }
                if (null != dis.getSumClickCount()) {
                    clickCount = clickCount + Integer.parseInt(dis.getSumClickCount());
                } else {
                    clickCount = clickCount + 0;
                }
            }
        }
        Format fm = new DecimalFormat("#,###");
        adDisplayTotalArr[0] = fm.format(showCount);
        adDisplayTotalArr[1] = fm.format(clickCount);
        // 4 获取公司名称
        List<DisplayCountDTO> companyNameList = adDisplayCountService.selectCompanyNameList(userId.intValue());
        // 4.1 获取订单名称列表
            List<DisplayCountDTO> orderNameList = adDisplayCountService.selectOrderNameList(0, userId.intValue());

        // 5. 获取折线统计图数据
        //System.out.println("orderOd" + orderId);
       String orderIdTemp = null;
        List<DisplayCountDTO> adgraphCountList = null;
        adgraphCountList = adDisplayCountService.selectAdGraphCount(7,null,0,userId.intValue());
        String dateArr[] = null;
        String showCountArr[] = null;
        String clickCountArr[] = null;
        String ctrArr[] = null;
        if(null == dayPeriod){
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
        pageSize = 3;
        // 分页信息
        List<DisplayCountDTO> adminList = Lists.newArrayList();
        Page<DisplayCountDTO> orderListPage = new Page<>(pageNum,pageSize);
        orderListPage.setOrderByField("id");
        orderListPage.setAsc(false);
        orderListPage = adDisplayCountService.selectAdminDisplayCountListPage(
                orderListPage,userId.intValue());
        adminList = orderListPage.getRecords();
        //将其中的Null值全部设为0
        for(DisplayCountDTO ddto:adminList){
             if(null == ddto.getSumShowCount()){
                 ddto.setSumShowCount("0");
             }
            if(null == ddto.getSumClickCount()){
                ddto.setSumClickCount("0");
            }
            if(null == ddto.getCtr()){
                ddto.setCtr("0");
            }
            if(null == ddto.getSumCosumeMoney()){
                ddto.setSumCosumeMoney("0");
            }
            if(null == ddto.getSumTotalMoney()){
                ddto.setSumTotalMoney("0");
            }
        }
        // 页面传值
        PageInfo<DisplayCountDTO> page = new PageInfo<>(orderListPage);
        maxPage = page.getPages();
        model.addAttribute("page", page);
        model.addAttribute("adminList", adminList);
        model.addAttribute("adDisplayTotalArr", adDisplayTotalArr);
        model.addAttribute("orderNameList", orderNameList);
        model.addAttribute("companyNameList", companyNameList);
        model.addAttribute("showCountArr", showCountArr);
        model.addAttribute("clickCountArr", clickCountArr);
        model.addAttribute("ctrArr", ctrArr);
        model.addAttribute("dateArr", dateArr);
        return "adAdminDisplayCount/adAdminCountList";
    }


    // 折线图数据
    @RequestMapping(value = "/adAdminChartCountList")
    public String chartUtil(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "orderId") String orderId,
            @RequestParam(required = false,value = "dayPeriod") Integer dayPeriod,
            @RequestParam(required = false,value = "companyId") long companyId
    ) {
        log.info("adAdminchartCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        String orderIdTemp = null;
        List<DisplayCountDTO> companyNameList = null;
        List<DisplayCountDTO> adgraphCountList = null;
        if ("null".equalsIgnoreCase(orderId) || "all".equalsIgnoreCase(orderId)) {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(dayPeriod, null, companyId,userId.intValue());
        } else {
            adgraphCountList = adDisplayCountService.selectAdGraphCount(dayPeriod, orderId, companyId,userId.intValue());
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
            adOtherList = adDisplayCountService.selectAdOtherList(dayPeriod,null,companyId,userId.intValue());
        } else {
            adOtherList = adDisplayCountService.selectAdOtherList(dayPeriod,orderId,companyId,userId.intValue());
        }
            String adDisplayTotalArr[] = {"1", "2"};
            int showCount = 0;
            int clickCount = 0;
            if(null != adOtherList.get(0)){
                for (DisplayCountDTO dis : adOtherList) {
                    if (null !=dis.getSumShowCount()) {
                        showCount = showCount + Integer.parseInt(dis.getSumShowCount());
                    }
                    else {
                        showCount = showCount + 0;
                    }
                    if (null != dis.getSumClickCount()) {
                        clickCount = clickCount + Integer.parseInt(dis.getSumClickCount());
                    }
                    else {
                        clickCount = clickCount + 0;
                    }
                }
            }
            Format fm = new DecimalFormat("#,###");
            adDisplayTotalArr[0] = fm.format(showCount);
            adDisplayTotalArr[1] = fm.format(clickCount);
        model.addAttribute("adDisplayTotalArr",adDisplayTotalArr);
        model.addAttribute("showCountArr", showCountArr);
        model.addAttribute("clickCountArr", clickCountArr);
        model.addAttribute("ctrArr", ctrArr);
        model.addAttribute("dateArr", dateArr);
        return "adAdminDisplayCount/adAdminCountList";
    }

    /**
     * @return String
     * @throws
     * @title 管理员-数据统计
     * @description TODO 统计数据-管理员列表(分页局部刷新)
     * @author qmh
     * @date 2017-06-17 15:26
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adminOrderCountList")
    //@ResponseBody()
    public String adminOrderCountList(
            Model model, @ModelAttribute DataStatisticsVO dataStatisticsVO,
            @RequestParam(required = false, value = "pageNum") Integer pageNum
    ) {
        log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        //Long companyId = shiroUser.getCompanyId();
        //  companyId = 1l;
        Integer pageSize = 3;
        if(pageNum < 1){
            pageNum = 1;
        }
        if(maxPage < pageNum){
            pageNum = maxPage;
        }
        // 分页信息
        List<DisplayCountDTO> orderList = Lists.newArrayList();
        Page<DisplayCountDTO> orderListPage = new Page<>(pageNum,pageSize);
        orderListPage.setOrderByField("id");
        orderListPage.setAsc(false);
        // 判断其请求数据为home页，还是adCountList页
        orderListPage = adDisplayCountService.selectAdminDisplayCountListPage(
                    orderListPage,userId.intValue());
        orderList = orderListPage.getRecords();
        // 页面传值
        PageInfo<DisplayCountDTO> page = new PageInfo<>(orderListPage);
        model.addAttribute("page", page);
        model.addAttribute("orderList", orderList);
            return "adAdminDisplayCount/adAdminCountList";
        }

    /**
     * @return String
     * @throws
     * @title 管理员-客户与订单及联
     * @description TODO 统计数据-查询订单
     * @author qmh
     * @date 2017-06-18 09：56
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping(value = "/adminOrderNameList")
    public String adminOrderNameList(
            Model model,
            @RequestParam(required = false, value = "companyId") Integer companyId
    ) {
        log.info("orderHomeCount......");
        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        List<DisplayCountDTO> orderNameList = adDisplayCountService.selectOrderNameList(companyId,userId.intValue());
        model.addAttribute("orderNameList", orderNameList);
        return "adAdminDisplayCount/adAdminCountList";
    }
}