package com.entgroup.adms.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.dto.*;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.ExportExcelUtil;
import com.entgroup.adms.util.PageInfo;
import com.entgroup.adms.util.StringUtils;
import com.google.common.collect.Lists;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author guofei 2017-3-23
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	/**
	 * 系统模块名
	 */
	private static final String SYSTEM_MODULE = "订单管理";

    //设置点击量与播放量系数
    private final Double clickNum = 0.1D;

	/**
	 * 
	 * @Title: updateOrderSlot  
	 * @Description: 刷新广告位数量
	 * @param model
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	// FIXME 修改广告位数量 之后需要删除
	@RequestMapping(value = "/updateOrderSlot", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public JsonResult updateOrderSlot(Model model) {
		log.info("updateOrderSlot...........");
		// 获取所有的订单
		Wrapper<AdOrder> adOrderwrapper = new EntityWrapper<AdOrder>();
		adOrderwrapper.eq("status", 1);
		List<AdOrder> selectObjs = adOrderService.selectList(adOrderwrapper);
		for (AdOrder adOrder : selectObjs) {
			// 根据订单获取对应的订单id
			String OrderId = adOrder.getId();
			// 根据订单id获取广告位数量
//			int countForOrderSlot = orderAdService.selectCountForSlot(OrderId);
//			adOrder.setSlotCount(countForOrderSlot);
		}
		boolean updateBatchById = adOrderService.updateBatchById(selectObjs);
		if (updateBatchById) {
			jr = renderSuccess("更新广告位成功");
		} else {
			jr = renderError("更新广告位失败");
		}

		if (log.isDebugEnabled()) {
			log.info("updateOrderSlot......");
			log.debug("updateOrderSlot...selectObjs:{}", selectObjs);
			log.debug("updateOrderSlot...updateBatchById:{}", updateBatchById);
		}
		return jr;
	}

	/**
	 * @title selectAdList
	 * @description TODO 获取广告列表用于订单挂载
	 * @author xiaokun
	 * @date 2017-06-28 11:16
	 * @modifier
	 * @remark
	 * @version V1.0
	 *
	 * @param
	 * @return JsonResult
	 * @throws
	 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取广告列表用于订单挂载")
	@RequestMapping(value = "/selectAdList")
	@ResponseBody
	public JsonResult selectAdList() {
		log.info("selectAdList");

		User shiroUser = getShiroUser();
		Long companyId = shiroUser.getCompanyId();

		// 根据公司id获取对应的广告以及广告的样式名
		List<Ad> adList = adService.selectList(new EntityWrapper<Ad>().eq("company_id", companyId).eq("order_online", 0));
		List<AdStyle> adStyleList = adStyleService.selectList(null);
		if (adList.size() == 0) {
			jr = renderError("当前没有未上线广告资源");
			return jr;
		}
		SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
		for (Ad ad : adList) {
			Long adStyleId = ad.getStyleId();
			ad.setCreatedString(smt.format(ad.getCreated()));
			for (AdStyle adStyle : adStyleList) {
				if (adStyle.getId() == adStyleId) {
					ad.setAdStyle(adStyle);
				}
			}
		}
		if (log.isDebugEnabled()) {
			log.info("selectAdList......");
			log.debug("selectAdList...companyId:{}", companyId);
			log.debug("selectAdList...result adListByCompany size:{}", adList.size());
		}
		jr = renderSuccess();
		jr.setData("adList", adList);
		return jr;
	}

    /**
     * @title saveAdOrder
     * @description TODO 订单信息存储
     * @author xiaokun
     * @date 2017-06-28 16:56
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param adOrder
     * @param model
     * @return JsonResult
     * @throws
     */
	@ResponseBody
	@RequestMapping("/saveAdOrder")
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "订单信息存储")
	public JsonResult saveAdOrder(@ModelAttribute("adOrder") AdOrder adOrder, Model model) {
		log.info("saveAdOrder...........");
		User shiroUser = getShiroUser();
		Long userId = shiroUser.getId();
		Long companyId = shiroUser.getCompanyId();

		// 生成订单号
		adOrder.setId(companyId + userId + DateUtils.getDateTimeStr());

		if (null == adOrder.getAdId()) {
			jr = renderError("请选择关联广告");
			return jr;
		} else {
			try {
				adOrder.setTrackingCode(adService.selectById(adOrder.getAdId()).getTrackingCode());
			} catch (Exception e) {
				e.printStackTrace();
				jr = renderError("广告数据获取错误");
				return jr;
			}
		}
		OrderAd orderAd = new OrderAd();
		orderAd.setOrderId(adOrder.getId());
		orderAd.setAdId(adOrder.getAdId());
		orderAd.setDeleted(0);

		adOrder.setCompanyId(companyId);
		adOrder.setAdCount(1);
		adOrder.setCreated(TimeStamp.getCurrentTime().getDate());
		adOrder.setSlotCount(0);
		adOrder.setStatus(0);
		adOrder.setCosumeMoney(0);

		Ad ad = new Ad();
		ad.setId(adOrder.getAdId());

		try {
			if (!adOrderService.insertAdOrderTemp(adOrder)) {
				jr = renderError("订单信息保存异常");
				return jr;
			}
			orderAdService.insert(orderAd);
			ad.setOrderOnline(1);
			adService.updateById(ad);
		} catch (Exception e) {
			e.printStackTrace();
			ad.setOrderOnline(0);
			if (null == adOrderService.selectById(adOrder.getId())) {
				jr = renderError("订单信息保存异常");
			} else if (null == orderAdService.selectOne(new EntityWrapper<OrderAd>().eq("order_id", adOrder.getId()))) {
				adOrderService.deleteById(adOrder.getId());
				jr = renderError("订单广告信息保存异常");
			}
			return jr;
		}
		noticeService.addAdOrderNotice(adOrder);
		jr = renderSuccess("添加订单成功");
		jr.setData("adOrderId",adOrder.getId());

		if (log.isDebugEnabled()) {
			log.info("saveAdOrder......");
			log.debug("saveAdOrder...AdOrder:{}", adOrder.toString());
		}
		return jr;
	}


    /**
     * @param adId
     * @param status
     * @param model
     *
     * @return
     *
     * @throws Exception
     * @Title: adOrderAdDetail
     * @Description: 订单广告详情
     * @author guofei
     * @date 2017年5月5日
     */
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "广告位详情")
    @RequestMapping(value = "/adOrderAdDetail", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String adOrderAdDetail(
            @RequestParam(required = false) Integer adId, @RequestParam(required = false) String status, Model model
    ) throws Exception {
        log.info("adOrderAdDetail....");
        boolean statusHas = StringUtils.isNotNull(status);
        List<OrderAdListDTO> allSlotList = null;
        if (statusHas) {
            if (status.equals("1")) {
                allSlotList = orderAdService.selectAllSlotListByAdId(adId);
            }
            if (status.equals("2")) {
                allSlotList = null;
            }
        }

        if (allSlotList != null) {
            for (OrderAdListDTO orderAdListDTO : allSlotList) {
                Integer videoPosition = orderAdListDTO.getVideoPosition();
                String second2Time = second2Time(videoPosition);
                orderAdListDTO.setSlotTime(second2Time);
            }
        }
        if (log.isDebugEnabled()) {
            log.info("adOrderAdDetail......");
            log.debug("adOrderAdDetail...adId:{}", adId);
            if (allSlotList != null) {
                log.debug("adOrderAdDetail...result allSlotList size:{}", allSlotList.size());
            }
        }
        model.addAttribute("allSlotList", allSlotList);
        return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
    }

    /**
     * @title adOrderList
     * @description TODO 订单页列表
     * @author xiaokun
     * @date 2017-06-28 21:22
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param pageNum
     * @param pageSize
     * @param adOrder
     * @param model
     * @return String
     * @throws
     */
    @RequestMapping("/adOrderList")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单页列表")
    public String adOrderList(
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @ModelAttribute("adOrder") AdOrder adOrder,
            Model model) throws Exception {
        log.info("adOrderList.......");
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();

        if (companyId != companyService.selectOne(new EntityWrapper<Company>().like("company_name", "艺恩")).getId()) {
            adOrder.setCompanyId(companyId);
        } else {
            String manageCompanyIds = "";
            List<Company> companyList = companyService.selectList(new EntityWrapper<Company>().eq("user_id", userId));
            for (int i=0;i<companyList.size();i++) {
                manageCompanyIds += companyList.get(i).getId();
                if (i<companyList.size()-1) {
                    manageCompanyIds += ",";
                }
            }
            adOrder.setManageCompanyIds(manageCompanyIds);
        }

        SimpleDateFormat smt = new SimpleDateFormat("yyyyMMdd");

        List<AdOrder> adOrderList = Lists.newArrayList();
        List<DisplayCountDTO> displayCountDTOList = adDisplayCountService.staOrderCosumeList((Integer.parseInt(smt.format(new Date()))-1) + "");

        Page<AdOrder> adOrderListPage = new Page<>(pageNum, pageSize);
        adOrderListPage = adOrderService.adOrderList(adOrderListPage, adOrder);

        adOrderList = adOrderListPage.getRecords();
        for (AdOrder order : adOrderList) {
            for (DisplayCountDTO displayCountDTO : displayCountDTOList) {
                if (displayCountDTO.getAdOrderId().equals(order.getId())) {
                    order.setOrderTotalprice(displayCountDTO.getOrderTotalPrice());
                }
            }
        }

        for (AdOrder order : adOrderList) {
            Integer adSlotCount = 0;
            if (order.getStatus() < 2) {
                adSlotCount = adSlotService.selectList(new EntityWrapper<AdSlot>().eq("order_id", order.getId())).size();
            } else {
                OrderFinishedInfo orderFinishedInfo = null;
                orderFinishedInfo = orderFinishedInfoService.selectOne(new EntityWrapper<OrderFinishedInfo>().eq("order_id", order.getId()));
                if (null != orderFinishedInfo) {
                    adSlotCount = orderFinishedInfo.getAdSlotIds().split(",").length;
                }
            }
            order.setSlotCount(adSlotCount);
        }

        adOrderListPage.setRecords(adOrderList);

        PageInfo<AdOrder> page = new PageInfo<>(adOrderListPage);

        SimpleDateFormat smt1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat smt2 = new SimpleDateFormat("yyyy-MM-dd");
        String minDate = smt2.format(smt1.parse(Integer.parseInt(smt1.format(new Date())) + 1 + ""));

        model.addAttribute("minDate", minDate);
        model.addAttribute("adOrderList", adOrderList);
        model.addAttribute("page", page);
        model.addAttribute("adOrder", adOrder);

        return "order/adOrderList";
    }


    /**
     * @title adOrderCheck
     * @description TODO 订单审核
     * @author xiaokun
     * @date 2017-06-28 22:27
     * @modifier
     * @remark
     * @version V1.0
     *
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/adOrderCheck")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单审核")
    @ResponseBody
    public JsonResult adOrderCheck(@RequestParam String adOrderId) {

        log.info("adOrderCheck......");

        if (null == adOrderId || adOrderId.equals("")) {
            jr = renderError("获取订单信息失败");
            return jr;
        }
        AdOrder adOrder = new AdOrder();
        adOrder.setId(adOrderId);
        adOrder.setStatus(1);
        try {
            adOrderService.updateById(adOrder);
            jr = renderSuccess("订单审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("审核订单异常");
            return jr;
        }
        return jr;
    }

    /**
     * @param adOrderId
     * @param pageNum
     * @param pageSize
     * @param companyIds
     * @param programTypeIds
     * @param programName
     * @param day
     * @param model
     * @return String
     * @throws
     *
     * @title orderSelect
     * @description TODO 订单选择视频列表
     * @author mxy
     * @date 2017-06-28 10:54
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/orderSelectProgram")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单选择视频列表")
    public String orderSelectProgramList(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String companyIds, @RequestParam(required = false) String programTypeIds,
            @RequestParam(required = false) String programName,
            @RequestParam(required = false, defaultValue = "30") Integer day, Model model
    ) throws Exception {
        log.info("orderSelectProgram.......");
        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();
        if (string2Null(companyIds) != null) {
            String[] companyIdX = companyIds.split(",");
            for (String s : companyIdX) {
                for (Company company : companyList) {
                    if (s.equals(company.getId() + "")) {
                        company.setSelected(true);
                        break;
                    }
                }
            }
        }
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);
        if (string2Null(programTypeIds) != null) {
            String[] programTypeIdX = programTypeIds.split(",");
            for (String s : programTypeIdX) {
                for (ProgramType programType : programTypeList) {
                    if (s.equals(programType.getId() + "")) {
                        programType.setSelected(true);
                        break;
                    }
                }
            }
        }
        //处理查询起始日期
        Date entryTimeStart = getDateBefore(new Date(), day);

        // 获取节目播放信息Top列表
        Page<Program> programPage = new Page(pageNum, pageSize);
        programPage.setOrderByField("COUNT(DISTINCT ads.`id`)");//按广告位数排序COUNT(DISTINCT ads.`id`)，按观看次数排序SUM(vprn.`play_record_count`)
        programPage.setAsc(false);
        companyIds = org.apache.commons.lang3.StringUtils.removeEnd(string2Null(companyIds), ",");
        programTypeIds = org.apache.commons.lang3.StringUtils.removeEnd(string2Null(programTypeIds), ",");
        programPage = programService.getTopProgramList(programPage, programName, companyIds, programTypeIds,adOrderId,
                                                       entryTimeStart, null);
        // 封装DTO
        List<OrderSelectDTO> dtoList = Lists.newArrayList();
        for (Program program : programPage.getRecords()) {
            OrderSelectDTO dto = new OrderSelectDTO();
            dto.setProgramId(program.getId());
            dto.setShowTime(program.getShowTime());
            dto.setProgramPlatform(program.getCompany() == null ? null : program.getCompany().getShortName());
            dto.setProgramName(program.getName());
            dto.setActors(program.getActors());
            dto.setProgramType(program.getProgramType().getName());
            dto.setVideoNum(program.getVideoNum());
            dto.setPlayNum(program.getPlayNum());
            dto.setAdSlotNum(program.getAdSlotNum());
            if (adOrderId.equals(program.getOrderId())) {
                dto.setSelected(true);
                dto.setOppId(program.getOppId());
                if (1==program.getOppDeleted()) {
                    dto.setSelected(false);
                }
            } else {
                dto.setSelected(false);
                dto.setOppId(0L);//没有被当前订单关联则用0占位
            }
            dtoList.add(dto);
        }


        PageInfo<Video> page = new PageInfo(programPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        model.addAttribute("adOrderId", adOrderId);
        model.addAttribute("companyIds", companyIds);
        model.addAttribute("programTypeIds", programTypeIds);
        model.addAttribute("programName", programName);
        model.addAttribute("day", day);
        return "order/orderSelectProgram";
    }


    /**
     * @param adOrderId
     * @param result 格式："oppId-programId-checked,..."如0-123-0就代表123号视频没有被订单关联且没有被选择，12-123-1就代表123号视频被订单关联且被选择
     * @param model
     * @return JsonResult
     * @throws
     *
     * @title saveSelectionResult
     * @description TODO 设置订单关联视频
     * @author mxy
     * @date 2017-06-28 10:30
     * @modifier
     * @remark
     * @version V1.0
     */
    @ResponseBody
    @RequestMapping("/saveSelectionResult")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "设置订单关联视频")
    public JsonResult saveSelectionResult(
            @RequestParam("adOrderId") String adOrderId, @RequestParam("result") String result, Model model
    ) {
        log.info("saveSelectionResult...........");
        User shiroUser = getShiroUser();
        Long userCompanyId = shiroUser.getCompanyId();
        AdOrder adOrder = new AdOrder();

        try {
            // 查询订单所属公司
            adOrder = adOrderService.selectById(adOrderId);
            Long adOrderCompanyId = adOrder.getCompanyId();
            if (!adOrderCompanyId.equals(userCompanyId)) {
                jr = renderError("订单不属于当前用户");
                return jr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("订单不存在");
            return jr;
        }

        try {
            ArrayList<OrderProgramPreview> orderProgramPreviews = Lists.newArrayList();
            String[] resultX = result.split(",");
            for (String s : resultX) {
                OrderProgramPreview orderProgramPreview = new OrderProgramPreview();
                String[] temp = s.split("-");
                if (!"0".equals(temp[0])) {
                    orderProgramPreview.setId(Long.parseLong(temp[0]));
                }else if("0".equals(temp[2])){
                    continue;
                }
                orderProgramPreview.setOrderId(adOrderId);
                orderProgramPreview.setProgramId(Long.parseLong(temp[1]));
                orderProgramPreview.setDeleted("0".equals(temp[2]) ? 1 : 0);
                orderProgramPreviews.add(orderProgramPreview);
            }
            orderProgramPreviewService.insertOrUpdateBatch(orderProgramPreviews);
            jr = renderSuccess("设置视频成功");
            jr.setData("adOrderId", adOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("设置视频失败");
        }

        if (log.isDebugEnabled()) {
            log.info("saveSelectionResult......");
            log.debug("adOrderId:{}", adOrderId);
            log.debug("result:{}", result.toString());
        }

        return jr;
    }

    /**
     * @param adOrderId
     * @param pageNum
     * @param pageSize
     * @param companyIds
     * @param programTypeIds
     * @param programName
     * @param model
     * @return String
     * @throws
     *
     * @title orderSelect
     * @description TODO 订单选择视频列表
     * @author mxy
     * @date 2017-06-28 10:54
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/orderSelectedProgram")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单已选择视频列表")
    public String orderSelectedProgramList(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String companyIds, @RequestParam(required = false) String programTypeIds,
            @RequestParam(required = false) String programName, Model model
    ) throws Exception {
        log.info("orderSelectedProgram.......");
        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();
        if (string2Null(companyIds) != null) {
            String[] companyIdX = companyIds.split(",");
            for (String s : companyIdX) {
                for (Company company : companyList) {
                    if (s.equals(company.getId() + "")) {
                        company.setSelected(true);
                        break;
                    }
                }
            }
        }
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);
        if (string2Null(programTypeIds) != null) {
            String[] programTypeIdX = programTypeIds.split(",");
            for (String s : programTypeIdX) {
                for (ProgramType programType : programTypeList) {
                    if (s.equals(programType.getId() + "")) {
                        programType.setSelected(true);
                        break;
                    }
                }
            }
        }

        // 获取节目播放信息Top列表
        Page<Program> programPage = new Page(pageNum, pageSize);
        programPage.setOrderByField("id");
        programPage.setAsc(false);
        companyIds = org.apache.commons.lang3.StringUtils.removeEnd(string2Null(companyIds), ",");
        programTypeIds = org.apache.commons.lang3.StringUtils.removeEnd(string2Null(programTypeIds), ",");
        programPage = programService.getOrderProgramList(programPage, programName, companyIds, programTypeIds,adOrderId,
                                                        true, null, null);
        // 封装DTO
        List<OrderSelectDTO> dtoList = Lists.newArrayList();
        for (Program program : programPage.getRecords()) {
            OrderSelectDTO dto = new OrderSelectDTO();
            dto.setProgramId(program.getId());
            dto.setShowTime(program.getShowTime());
            dto.setProgramPlatform(program.getCompany() == null ? null : program.getCompany().getShortName());
            dto.setProgramName(program.getName());
            dto.setActors(program.getActors());
            dto.setProgramType(program.getProgramType().getName());
            dto.setVideoNum(program.getVideoNum());
            dto.setPlayNum(program.getPlayNum());
            if (adOrderId.equals(program.getOrderId())) {
                dto.setSelected(true);
                dto.setOppId(program.getOppId());
                if (1==program.getOppDeleted()) {
                    dto.setSelected(false);
                }
            } else {
                dto.setSelected(false);
                dto.setOppId(0L);//没有被当前订单关联则用0占位
            }
            dtoList.add(dto);
        }


        PageInfo<Video> page = new PageInfo(programPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        model.addAttribute("adOrderId", adOrderId);
        model.addAttribute("companyIds", companyIds);
        model.addAttribute("programTypeIds", programTypeIds);
        model.addAttribute("programName", programName);
        return "order/orderSelectedProgram";
    }


    /**
     * @param adOrderId
     * @param pageNum
     * @param pageSize
     * @param adSlotDTO
     * @param sceneId
     * @param personId
     * @param objectId
     * @param labelId
     * @param model
     * @return String
     * @throws
     *
     * @title orderSelectedAdSlot
     * @description TODO 订单选择广告位列表
     * @author mxy
     * @date 2017-06-28 21:58
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/orderSelectedAdSlot")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单选择广告位列表")
    public String orderSelectedAdSlot(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @ModelAttribute AdSlotDTO adSlotDTO,
            @RequestParam(required = false) Long sceneId,
            @RequestParam(required = false) Long personId,
            @RequestParam(required = false) Long objectId,
            @RequestParam(required = false) Long labelId,
            Model model
    ) throws Exception {
        log.info("orderSelectedAdSlot.......");
        if (long2Null(adSlotDTO.getProgramLevelId())==null) {
            adSlotDTO.setProgramLevelId(3L);
        }
        // 视频内容平台列表
        List<Company> companyList = companyService.getContentPlatformList();
        // 视频分类列表
        List<ProgramType> programTypeList = programTypeService.selectList(null);
        // 视频列表
        Wrapper<OrderProgramPreview> orderProgramPreviewWrapper = new EntityWrapper<>();
        orderProgramPreviewWrapper.eq("order_id",adOrderId).eq("deleted",0);
        List<OrderProgramPreview> orderProgramPreviewList = orderProgramPreviewService.selectList(orderProgramPreviewWrapper);
        String programIds = "";
        for (OrderProgramPreview orderProgramPreview : orderProgramPreviewList) {
            programIds += orderProgramPreview.getProgramId() + ",";
        }
        programIds = org.apache.commons.lang3.StringUtils.removeEnd(string2Null(programIds), ",");

        Wrapper<Program> programWrapper = new EntityWrapper<>();
        programWrapper.in("id",programIds);
        List<Program> programList = programService.selectList(programWrapper);

        // 获取可用广告位列表
        Page<AdSlotDTO> adSlotDTOPage = new Page(pageNum, pageSize);
        adSlotDTOPage.setOrderByField("id,video_position");
        adSlotDTOPage.setAsc(false);
        adSlotDTOPage = adSlotService.orderSelectedAdSlot(adSlotDTOPage,programIds,null,0L,"0",
                                                          long2Null(adSlotDTO.getProgramLevelId()),
                                                          long2Null(adSlotDTO.getProgramTypeId()),
                                                          long2Null(adSlotDTO.getProgramId()),
                                                          string2Null(adSlotDTO.getProgramName()),
                                                          string2Null(adSlotDTO.getVideoName()),
                                                          long2Null(adSlotDTO.getCompanyId()),
                                                          long2Null(sceneId),
                                                          long2Null(personId),
                                                          long2Null(objectId),
                                                          long2Null(labelId)
                                                          );
        // 封装广告位图片地址及标签及其他广告位
        List<AdSlotDTO> dtoList = adSlotDTOPage.getRecords();
        for (AdSlotDTO dto : dtoList) {
            String videoPosition = dto.getVideoPosition();
            String imageServer = dto.getImageServer();
            String imgPathPrefix;
            if (null != imageServer) {
                imageServer = imageServer.substring(
                        imageServer.lastIndexOf(".") + 1, imageServer.length());
                imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                        + imageServer + "/" + dto.getVideoId() + "/";
            } else {
                imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + dto.getVideoId()
                        + "/";
            }
            Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                    .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
            dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");
            dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
            String[] labelNames = dto.getLabels().split(",");
            dto.setLabelNames(labelNames);
            List<VideoAdSlotDTO> videoAdSlotList = adSlotService.selectAdSlotSingle(dto.getVideoId());
            for(VideoAdSlotDTO videoAdSlotDTO : videoAdSlotList){
                videoAdSlotDTO.setVideoPosition(second2Time(Integer.parseInt(videoAdSlotDTO.getVideoPosition())));
            }
            dto.setVideoAdSlot(videoAdSlotList);
        }

        //场景
        List<Scene> sceneList =  sceneService.getScenes(null, null);
        //明星
        List<Person> personList =  personService.selectList(null);
        //物品
        List<RecognitionObject> recognitionObjectList =  objectService.selectList(null);
        //标签
        List<SlotLabel> slotLabelList =  slotLabelService.selectList(null);
        //获取预投放数
        Wrapper<AdSlot> adSlotWrapper = new EntityWrapper<>();
        adSlotWrapper.eq("order_id",adOrderId).eq("ad_id",0L);
        Integer previewNum = adSlotService.selectCount(adSlotWrapper);
/*        //获取广告
        Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
        orderAdWrapper.eq("order_id",adOrderId);
        Long slotAdId = orderAdService.selectOne(orderAdWrapper).getAdId();*/

        PageInfo<AdSlotDTO> page = new PageInfo(adSlotDTOPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("companyList", companyList);
        model.addAttribute("programTypeList", programTypeList);
        model.addAttribute("programList", programList);
        model.addAttribute("sceneList", sceneList);
        model.addAttribute("personList", personList);
        model.addAttribute("objectList", recognitionObjectList);
        model.addAttribute("slotLabelList", slotLabelList);
        model.addAttribute("adOrderId", adOrderId);
//        model.addAttribute("slotAdId", slotAdId);
        model.addAttribute("previewNum", previewNum);
        model.addAttribute("sceneId", sceneId);
        model.addAttribute("personId", personId);
        model.addAttribute("objectId", objectId);
        model.addAttribute("labelId", labelId);
        return "order/orderSelectedAdSlot";
    }

    /**
     * @param adOrderId
     * @param result
     * @param model
     * @return JsonResult
     * @throws
     *
     * @title saveSelectionResult2
     * @description TODO 设置订单关联广告位
     * @author mxy
     * @date 2017-06-29 04:16
     * @modifier
     * @remark
     * @version V1.0
     */
    @ResponseBody
    @RequestMapping("/saveSelectionResult2")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "设置订单关联广告位")
    public JsonResult saveSelectionResult2(
            @RequestParam("adOrderId") String adOrderId, @RequestParam("result") String result, Model model
    ) {
        log.info("saveSelectionResult2...........");
        if (!"0".equals(adOrderId)) {
            User shiroUser = getShiroUser();
            Long userCompanyId = shiroUser.getCompanyId();
            AdOrder adOrder = new AdOrder();
            try {
                // 查询订单所属公司
                adOrder = adOrderService.selectById(adOrderId);
                Long adOrderCompanyId = adOrder.getCompanyId();
                if (!adOrderCompanyId.equals(userCompanyId)) {
                    jr = renderError("订单不属于当前用户");
                    return jr;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("订单不存在");
                return jr;
            }
        }

        try {
            ArrayList<AdSlot> adSlots = Lists.newArrayList();
            String[] resultX = result.split(",");
            for (String s : resultX) {
                AdSlot adSlot = adSlotService.selectById(Long.parseLong(s));
                adSlot.setOrderId(adOrderId);
                adSlots.add(adSlot);
            }
            adSlotService.updateBatchById(adSlots);
            jr = renderSuccess("设置广告位成功");
            jr.setData("adOrderId", adOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("设置广告位失败");
        }

        if (log.isDebugEnabled()) {
            log.info("saveSelectionResult2......");
            log.debug("adOrderId:{}", adOrderId);
            log.debug("result:{}", result.toString());
        }

        return jr;
    }

    /**
     * @param adOrderId
     * @param pageNum
     * @param pageSize
     * @param videoName
     * @param model
     * @return String
     * @throws
     *
     * @title willPutAdList
     * @description TODO 订单预投放广告位列表
     * @author mxy
     * @date 2017-06-29 04:16
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/willPutAd")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单预投放广告位信息列表")
    public String willPutAdList(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String videoName,
            Model model
    ) throws Exception {
        log.info("willPutAd.......");

        //获取广告
        Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
        orderAdWrapper.eq("order_id",adOrderId);
        Long adId = orderAdService.selectOne(orderAdWrapper).getAdId();
        Ad ad = adService.selectAdById(adId);
        //获取广告价格系数
        AdStyle adStyle = adStyleService.selectById(ad.getStyleId());
        Double adPrice = adStyle.getPrice().doubleValue()/adStyle.getPriceUnit();
        //获取订单信息
        AdOrder adOrder = adOrderService.selectById(adOrderId);
        Boolean check = adOrder.getStatus()==1?true:false;
        Boolean selected = adOrder.getSelected()==1?true:false;

        // 获取昨日广告位播放信息列表
        Page<AdSlotDTO> adSlotDTOPage = new Page(pageNum, pageSize);
        adSlotDTOPage.setOrderByField("id,video_position");
        adSlotDTOPage.setAsc(false);
        adSlotDTOPage = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage,adPrice,adOrderId,string2Null(videoName),
                                                               getDateBefore(new Date(),0),false);
        // 封装广告位图片地址及标签
        List<AdSlotDTO> dtoList = adSlotDTOPage.getRecords();
        for (AdSlotDTO dto : dtoList) {
            String videoPosition = dto.getVideoPosition();
            String imageServer = dto.getImageServer();
            String imgPathPrefix;
            if (null != imageServer) {
                imageServer = imageServer.substring(
                        imageServer.lastIndexOf(".") + 1, imageServer.length());
                imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                        + imageServer + "/" + dto.getVideoId() + "/";
            } else {
                imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + dto.getVideoId()
                        + "/";
            }
            Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                    .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
            dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");
            dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
            dto.setShowNum(null==dto.getShowNum()?"0":dto.getShowNum());
            dto.setExpense(Integer.parseInt(null==dto.getExpense()?"0":dto.getExpense())+"");
        }

        //获取预曝光量及预消费
        Integer showNumCount=0;
        Integer expenseCount=0;
        Page<AdSlotDTO> adSlotDTOPage2 = new Page(1,10000);
        adSlotDTOPage2 = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage2,adPrice,adOrderId,string2Null
                                                                        (videoName),
                                                               getDateBefore(new Date(),0),false);
        for (AdSlotDTO adSlotDTO : adSlotDTOPage2.getRecords()) {
            showNumCount += Integer.parseInt(null==adSlotDTO.getShowNum()?"0":adSlotDTO.getShowNum());
            expenseCount += Integer.parseInt(null==adSlotDTO.getExpense()?"0":adSlotDTO.getExpense());
        }

        PageInfo<AdSlotDTO> page = new PageInfo(adSlotDTOPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("videoName", videoName);
        model.addAttribute("adOrderId", adOrderId);
        //        model.addAttribute("slotAdId", slotAdId);
        model.addAttribute("showNumCount", showNumCount);
        model.addAttribute("expenseCount", expenseCount);
        model.addAttribute("clickNum", clickNum);
        model.addAttribute("check", check);
        model.addAttribute("selected", selected);
        return "order/willPutAd";
    }

    /**
     * @param adOrderId
     * @param method 0-预投放，1-已投放，2-已完成订单-已投放
     * @param response
     * @param model
     * @return String
     * @throws
     *
     * @title exportWillPutAd
     * @description TODO 导出订单预/已投放广告位列表
     * @author mxy
     * @date 2017-06-29 09:16
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/exportPutAdInfo")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "导出订单预/已投放广告位列表")
    public String exportPutAdInfo(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam("method") Integer method,
            HttpServletResponse response,
            Model model
    ) throws Exception {
        log.info("exportPutAdInfo.......");

        //获取广告
        Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
        orderAdWrapper.eq("order_id",adOrderId);
        Long adId = orderAdService.selectOne(orderAdWrapper).getAdId();
        Ad ad = adService.selectAdById(adId);
        //获取广告价格系数
        AdStyle adStyle = adStyleService.selectById(ad.getStyleId());
        Double adPrice = adStyle.getPrice().doubleValue()/adStyle.getPriceUnit();

        // 获取昨日广告位播放信息列表
        Page<AdSlotDTO> adSlotDTOPage = new Page(1, 10000);
        adSlotDTOPage.setOrderByField("id,video_position");
        adSlotDTOPage.setAsc(false);
        if (method==2) {
            adSlotDTOPage = adSlotService.selectAdSlotShowInfoList4Finished(adSlotDTOPage,adPrice,adOrderId,null,
                                                                   getDateBefore(new Date(),0),null);
        }else {
            adSlotDTOPage = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage,adPrice,adOrderId,null,
                                                                   getDateBefore(new Date(),0),0==method?false:true);
        }
        // 封装数据
        Map<String, Object> map;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Map<String, Object>> list = Lists.newArrayList();
        List<AdSlotDTO> dtoList = adSlotDTOPage.getRecords();
        for (AdSlotDTO dto : dtoList) {
            map = new HashMap<String, Object>();
            map.put("1", dto.getCompanyName());
            map.put("2", dto.getVideoName());
            map.put("3", dto.getProgramTypeName());
            map.put("4", dto.getActors());
            map.put("5", dto.getShowTime());
            map.put("6", second2Time(Integer.parseInt(dto.getVideoPosition())));
            map.put("7", Integer.parseInt(null==dto.getShowNum()?"0":dto.getShowNum()));
            map.put("8", Integer.parseInt(null==dto.getExpense()?"0":dto.getExpense()));
            list.add(map);
        }
        ExportExcelUtil e = new ExportExcelUtil();
        String headers[][] = { { "1", "视频平台" },
                { "2", "视频名称" },
                { "3", "类型" },
                { "4", "主演" },
                { "5", "上映日期" },
                { "6", "时间点" },
                { "7", "预计曝光量" },
                { "8", "昨日消费/元" }};
        if (0==method) {
            e.exportExcel("预投放广告位列表", headers, list, response);
        }else {
            e.exportExcel("已投放广告位列表", headers, list, response);
        }
        return null;
    }

    /**
     * @param adOrderId
     * @param pageNum
     * @param pageSize
     * @param videoName
     * @param model
     * @return String
     * @throws
     *
     * @title hadPutAdList
     * @description TODO 订单已投放广告位列表
     * @author mxy
     * @date 2017-06-29 09:01
     * @modifier
     * @remark
     * @version V1.0
     */
    @RequestMapping("/hadPutAd")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单已投放广告位信息列表")
    public String hadPutAdList(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String videoName,
            Model model
    ) throws Exception {
        log.info("hadPutAd.......");

        //获取广告
        Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
        orderAdWrapper.eq("order_id",adOrderId);
        Long adId = orderAdService.selectOne(orderAdWrapper).getAdId();
        Ad ad = adService.selectAdById(adId);
        //获取广告价格系数
        AdStyle adStyle = adStyleService.selectById(ad.getStyleId());
        Double adPrice = adStyle.getPrice().doubleValue()/adStyle.getPriceUnit();
        //获取订单信息
/*        AdOrder adOrder = adOrderService.selectById(adOrderId);
        Date endDay = adOrder.getEndTime();
        Date now = new Date();
        Long ltime = endDay.getTime()-now.getTime()<0 ? now.getTime()-endDay.getTime():endDay.getTime()-now.getTime();
        Long day = ltime/86400000;*/
        AdOrder adOrder = new AdOrder();
        adOrder.setId(adOrderId);
        Page<AdOrder> adOrderPage = new Page<>();
        adOrder = adOrderService.adOrderList(adOrderPage, adOrder).getRecords().get(0);
        adOrder.setLastDate(adOrder.getLastDate()<0?0:adOrder.getLastDate());
        Integer day = adOrder.getLastDate();
        Integer balance = adOrder.getTotalMoney()-adOrder.getCosumeMoney();

        // 获取昨日广告位播放信息列表
        Page<AdSlotDTO> adSlotDTOPage = new Page(pageNum, pageSize);
        adSlotDTOPage.setOrderByField("id,video_position");
        adSlotDTOPage.setAsc(false);
        adSlotDTOPage = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage,adPrice,adOrderId,string2Null(videoName),
                                                               getDateBefore(new Date(),0),true);
        // 封装广告位图片地址及标签及其他广告位
        List<AdSlotDTO> dtoList = adSlotDTOPage.getRecords();
        for (AdSlotDTO dto : dtoList) {
            String videoPosition = dto.getVideoPosition();
            String imageServer = dto.getImageServer();
            String imgPathPrefix;
            if (null != imageServer) {
                imageServer = imageServer.substring(
                        imageServer.lastIndexOf(".") + 1, imageServer.length());
                imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                        + imageServer + "/" + dto.getVideoId() + "/";
            } else {
                imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + dto.getVideoId()
                        + "/";
            }
            Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                    .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
            dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");
            dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
            dto.setShowNum(null==dto.getShowNum()?"0":dto.getShowNum());
            dto.setExpense(Integer.parseInt(null==dto.getExpense()?"0":dto.getExpense())+"");
        }

        //获取预曝光量及预消费
        Integer showNumCount=0;
        Integer expenseCount=0;
        Page<AdSlotDTO> adSlotDTOPage2 = new Page(1,10000);
        adSlotDTOPage2 = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage2,adPrice,adOrderId,string2Null
                                                                        (videoName),
                                                                getDateBefore(new Date(),0),false);
        for (AdSlotDTO adSlotDTO : adSlotDTOPage2.getRecords()) {
            showNumCount += Integer.parseInt(null==adSlotDTO.getShowNum()?"0":adSlotDTO.getShowNum());
            expenseCount += Integer.parseInt(null==adSlotDTO.getExpense()?"0":adSlotDTO.getExpense());
        }

        PageInfo<AdSlotDTO> page = new PageInfo(adSlotDTOPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("videoName", videoName);
        model.addAttribute("adOrderId", adOrderId);
        //        model.addAttribute("slotAdId", slotAdId);
        model.addAttribute("showNumCount", showNumCount);
        model.addAttribute("expenseCount", expenseCount);
        model.addAttribute("clickNum", clickNum);
        model.addAttribute("remainingDays", day);
        model.addAttribute("balance", balance);
        return "order/hadPutAd";
    }
    @RequestMapping("/hadPutAd4Finished")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "已完成订单已投放广告位信息列表")
    public String hadPutAd4FinishedList(
            @RequestParam("adOrderId") String adOrderId,
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String videoName,
            Model model
    ) throws Exception {
        log.info("hadPutAd4Finished.......");

        //获取广告
        Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
        orderAdWrapper.eq("order_id",adOrderId);
        Long adId = orderAdService.selectOne(orderAdWrapper).getAdId();
        Ad ad = adService.selectAdById(adId);
        //获取广告价格系数
        AdStyle adStyle = adStyleService.selectById(ad.getStyleId());
        Double adPrice = adStyle.getPrice().doubleValue()/adStyle.getPriceUnit();
        //获取订单信息
/*        AdOrder adOrder = adOrderService.selectById(adOrderId);
        Date endDay = adOrder.getEndTime();
        Date now = new Date();
        Long ltime = endDay.getTime()-now.getTime()<0 ? now.getTime()-endDay.getTime():endDay.getTime()-now.getTime();
        Long day = ltime/86400000;*/
        AdOrder adOrder = new AdOrder();
        adOrder.setId(adOrderId);
        Page<AdOrder> adOrderPage = new Page<>();
        adOrder = adOrderService.adOrderList(adOrderPage, adOrder).getRecords().get(0);
        adOrder.setLastDate(adOrder.getLastDate()<0?0:adOrder.getLastDate());
        Integer day = adOrder.getLastDate();
        Integer balance = adOrder.getTotalMoney()-adOrder.getCosumeMoney();

        // 获取昨日广告位播放信息列表
        Page<AdSlotDTO> adSlotDTOPage = new Page(pageNum, pageSize);
        adSlotDTOPage.setOrderByField("id,video_position");
        adSlotDTOPage.setAsc(false);
        adSlotDTOPage = adSlotService.selectAdSlotShowInfoList4Finished(adSlotDTOPage,adPrice,adOrderId,string2Null(videoName),
                                                               getDateBefore(new Date(),0),null);
        // 封装广告位图片地址及标签及其他广告位
        List<AdSlotDTO> dtoList = adSlotDTOPage.getRecords();
        for (AdSlotDTO dto : dtoList) {
            String videoPosition = dto.getVideoPosition();
            String imageServer = dto.getImageServer();
            String imgPathPrefix;
            if (null != imageServer) {
                imageServer = imageServer.substring(
                        imageServer.lastIndexOf(".") + 1, imageServer.length());
                imgPathPrefix = "http://api.xiaobaishiji.com:81/image/"
                        + imageServer + "/" + dto.getVideoId() + "/";
            } else {
                imgPathPrefix = "http://101.200.207.204:8080/thumbnail/" + dto.getVideoId()
                        + "/";
            }
            Integer timeIn = (Integer.parseInt(videoPosition) - 0) >= 0 ? (Integer
                    .parseInt(videoPosition) - 0) : 1;//由于抽帧全部减0秒
            dto.setPictureAddress(imgPathPrefix + timeIn + ".jpg");
            dto.setVideoPositionTime(second2Time(Integer.parseInt(videoPosition)));
            dto.setShowNum(null==dto.getShowNum()?"0":dto.getShowNum());
            dto.setExpense(Integer.parseInt(null==dto.getExpense()?"0":dto.getExpense())+"");
        }

        //获取预曝光量及预消费
        Integer showNumCount=0;
        Integer expenseCount=0;
        Page<AdSlotDTO> adSlotDTOPage2 = new Page(1,10000);
        adSlotDTOPage2 = adSlotService.selectAdSlotShowInfoList(adSlotDTOPage2,adPrice,adOrderId,string2Null
                                                                        (videoName),
                                                                getDateBefore(new Date(),0),false);
        for (AdSlotDTO adSlotDTO : adSlotDTOPage2.getRecords()) {
            showNumCount += Integer.parseInt(null==adSlotDTO.getShowNum()?"0":adSlotDTO.getShowNum());
            expenseCount += Integer.parseInt(null==adSlotDTO.getExpense()?"0":adSlotDTO.getExpense());
        }

        PageInfo<AdSlotDTO> page = new PageInfo(adSlotDTOPage);
        model.addAttribute("page", page);
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("videoName", videoName);
        model.addAttribute("adOrderId", adOrderId);
        //        model.addAttribute("slotAdId", slotAdId);
        model.addAttribute("showNumCount", showNumCount);
        model.addAttribute("expenseCount", expenseCount);
        model.addAttribute("clickNum", clickNum);
        model.addAttribute("remainingDays", day);
        model.addAttribute("balance", balance);
        return "order/hadPutAd4Finished";
    }

    /**
     * @param adOrderId
     * @param selected
     * @param model
     * @return JsonResult
     * @throws
     *
     * @title saveOrderSelected
     * @description TODO 设置订单是否完成广告位选择
     * @author mxy
     * @date 2017-06-29 05:31
     * @modifier
     * @remark
     * @version V1.0
     */
    @ResponseBody
    @RequestMapping("/saveOrderSelected")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "设置订单是否完成广告位选择")
    public JsonResult saveOrderSelected(
            @RequestParam("adOrderId") String adOrderId, @RequestParam("selected") Integer selected, Model model
    ) {
        log.info("saveOrderSelected...........");
        User shiroUser = getShiroUser();
        Long userCompanyId = shiroUser.getCompanyId();
        AdOrder adOrder = new AdOrder();
        try {
            // 查询订单所属公司
            adOrder = adOrderService.selectById(adOrderId);
            Long adOrderCompanyId = adOrder.getCompanyId();
            if (!adOrderCompanyId.equals(userCompanyId)) {
                jr = renderError("订单不属于当前用户");
//                return jr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("订单不存在");
            return jr;
        }
        try {
            adOrder.setSelected(selected);
            adOrderService.updateById(adOrder);
            jr = renderSuccess("设置广告位选择成功");
            jr.setData("adOrderId", adOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("设置广告位选择失败");
        }

        if (log.isDebugEnabled()) {
            log.info("saveOrderSelected......");
            log.debug("adOrderId:{}", adOrderId);
            log.debug("selected:{}", selected.toString());
        }

        return jr;
    }

    /**
     * @param method 0-下架，1-投放
     * @param adOrderId
     * @param result
     * @param model
     * @return JsonResult
     * @throws
     *
     * @title savePutInResult
     * @description TODO 
     * @author mxy
     * @date 2017-06-29 05:44
     * @modifier
     * @remark
     * @version V1.0
     */
    @ResponseBody
    @RequestMapping("/savePutInResult")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "设置广告位投放广告")
    public JsonResult savePutInResult(@RequestParam("method") Integer method,
            @RequestParam("adOrderId") String adOrderId, @RequestParam("result") String result, Model model
    ) {
        log.info("savePutInResult...........");

        User shiroUser = getShiroUser();
        Long userCompanyId = shiroUser.getCompanyId();
        AdOrder adOrder = new AdOrder();
        try {
            // 查询订单所属公司
            adOrder = adOrderService.selectById(adOrderId);
            Long adOrderCompanyId = adOrder.getCompanyId();
            if (!adOrderCompanyId.equals(userCompanyId)) {
                jr = renderError("订单不属于当前用户");
                return jr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("订单不存在");
            return jr;
        }

        try {
            //获取广告
            Wrapper<OrderAd> orderAdWrapper = new EntityWrapper<>();
            orderAdWrapper.eq("order_id",adOrderId);
            Long adId = orderAdService.selectOne(orderAdWrapper).getAdId();
            if (method==0) {
                adId = 0L;
            }
            ArrayList<AdSlot> adSlots = Lists.newArrayList();
            String[] resultX = result.split(",");
            for (String s : resultX) {
                AdSlot adSlot = adSlotService.selectById(Long.parseLong(s));
                adSlot.setAdId(adId);
                adSlots.add(adSlot);
            }
            adSlotService.updateBatchById(adSlots);
            if (method==0) {
                jr = renderSuccess("下架广告成功");
            }else {
                jr = renderSuccess("投放广告成功");
            }
            jr.setData("adOrderId", adOrderId);
        } catch (Exception e) {
            e.printStackTrace();
            if ("0".equals(method)) {
                jr = renderSuccess("下架广告失败");
            }else {
                jr = renderSuccess("投放广告失败");
            }
        }

        if (log.isDebugEnabled()) {
            log.info("savePutInResult......");
            log.debug("adOrderId:{}", adOrderId);
            log.debug("result:{}", result.toString());
        }

        return jr;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param orderId
     * @param companyId
     * @param styleId
     * @param status
     * @param model
     *
     * @return
     *
     * @throws Exception
     * @Title: adOrderDetail
     * @Description: 订单详情
     * @author guofei
     * @date 2017年5月5日
     */
    @RequestMapping("/adOrderDetail")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "订单对应广告样式列表")
    public String adOrderDetail(
            @RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
            @RequestParam(required = false) String orderId, @RequestParam(required = false) String companyId,
            @RequestParam(required = false) String styleId, @RequestParam(required = false) String status, Model model
    ) throws Exception {
        log.info("adOrderDetail......");
        boolean styleIdHas = StringUtils.isNull(styleId);
        boolean companyHas = StringUtils.isNull(companyId);
        User shiroUser = getShiroUser();
        Long companyId1 = shiroUser.getCompanyId();
        String companyId2 = companyId1.toString();

        // 根据公司id查询Order或者查询所有Order
        if (companyHas == true) {
            companyId = null;
        }
        if (companyId1 != 1) {
            companyId = companyId2;
        }
        if (styleIdHas == true) {
            styleId = null;
        }
        // 根据订单id获取对应的广告样式下拉框
        List<AdStyle> adStyleList = adStyleService.selectAdStyle(orderId);
        Page<OrderAdListDTO> allAdByOrderPage = null;
        if (status.equals("1")) {
            // 获取订单详情列表
            allAdByOrderPage = orderAdService.selectAllAdByOrderPage(pageNum, pageSize, orderId, styleId);
        }
        if (status.equals("2")) {
            //获取完成订单的订单详情
            allAdByOrderPage = orderAdService.selectAllAdPage(pageNum, pageSize, orderId, styleId);
        }

        List<OrderAdListDTO> orderAdList = allAdByOrderPage.getRecords();
        /*for (OrderAdListDTO orderAdDTO : orderAdList) {
            Integer adSlotCount = 0;
            if (adOrderService.selectById(orderAdDTO.getOrderId()).getStatus() < 2) {
                adSlotCount = adSlotService.selectList(new EntityWrapper<AdSlot>().eq("order_id", orderAdDTO.getOrderId())).size();
            } else {
                adSlotCount = orderFinishedInfoService.selectOne(new EntityWrapper<OrderFinishedInfo>().eq("order_id", orderAdDTO.getOrderId())).getAdSlotIds().split(",").length;
            }
            orderAdDTO.setAdSlotCount(adSlotCount);
        }*/
        PageInfo<OrderAdListDTO> pageInfo = new PageInfo<OrderAdListDTO>(allAdByOrderPage);
        if (log.isDebugEnabled()) {
            log.info("adOrderDetail......");
            log.debug("adOrderDetail...PAGE_NUM:{}", pageNum);
            log.debug("adOrderDetail...PAGE_SIZE:{}", pageSize);
            log.debug("adOrderDetail...orderId:{}", orderId);
            log.debug("adOrderDetail...adStyleId:{}", styleId);
            log.debug("adOrderDetail...result allAdByOrderPage size:{}", allAdByOrderPage.getRecords().size());
            log.debug("adOrderDetail...result orderAdList size:{}", orderAdList.size());
        }
        model.addAttribute("page", pageInfo);
        model.addAttribute("orderAdList", orderAdList);
        model.addAttribute("adStyleList", adStyleList);
        model.addAttribute("orderId", orderId);
        model.addAttribute("styleId", styleId);
        model.addAttribute("companyId", companyId);
        model.addAttribute("status", status);
        return "order/listDetail";
    }

}
