package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.dto.AdvertisersDTO;
import com.entgroup.adms.dto.ScreenAdSlotDTO;
import com.entgroup.adms.model.system.Advertiser;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.model.system.ProgramType;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author mxy
 * @ClassName: AdvertiserController
 * @Description: TODO 广告主管理
 * @date 2017-05-05 14:45
 */
@Controller
@RequestMapping("/advertiser")
public class AdvertiserController extends BaseController  {

    /**
     * 系统模块名
     */
    static final String SYSTEM_MODULE = "广告主管理";

    @RequestMapping(value = "/advertisersManagement")
    @RequiresPermissions(value = {AuthorityConstants.AdSlot.VIEW_ADSLOT_SCREENADSLOT}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告主管理列表页")
    public String advertisersManagementList(
            Model model, @ModelAttribute AdvertisersDTO advertisersDTO,
            @RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize
    ) {
        log.info("advertisersManagement......");

        // 视频内容平台列表0
        List<Company> companyList = companyService.getContentPlatformList();
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);

        // 获取视频（列表）识别信息
        Page<Advertiser> advertiserPage = new Page(pageNum, pageSize);
        advertiserPage.setOrderByField("id");

        advertiserPage.setAsc(true);
        advertiserPage = advertiserService.getAdvertiserList(advertiserPage, null, string2Null(advertisersDTO.getName()),
                                                             string2List(advertisersDTO.getStatuses()),
                                                             string2List(advertisersDTO.getIds()),
                                                             long2Null(advertisersDTO.getCompanyVocationId()),
                                                             long2Null(advertisersDTO.getCompanyId()),
                                                             long2Null(advertisersDTO.getUserId()));
        // 封装DTO
        List<ScreenAdSlotDTO> dtoList = Lists.newArrayList();
        return "home";
    }
}
