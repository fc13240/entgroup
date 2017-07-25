package com.entgroup.adms.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.PageInfo;
import com.entgroup.adms.util.StringUtils;

@Controller
@RequestMapping("/dataStatistics")
public class DisplayCountController extends BaseController {
	/**
	 * 系统模块名
	 */
	private static final String SYSTEM_MODULE = "数据统计";
	private double parseDouble;

	/**
	 * 
	 * @Title: selectOrder  
	 * @Description: 选择公司后下拉菜单显示
	 * @param companyId
	 * @param model
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取订单下拉列表")
	@RequestMapping(value = "/selectOrder", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String selectOrder(@RequestParam(required = false) String companyId, Model model) {
		log.info("selectStyle.........");
		boolean companyHas = StringUtils.isNull(companyId);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas) {
			companyId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		// 根据公司id获取订单
		List<AdAndStyleByCompanyDTO> adOrderList = adOrderService.selectOrderList(companyId);
		for (AdAndStyleByCompanyDTO adAndStyleByCompanyDTO : adOrderList) {
			Long adOrderId = adAndStyleByCompanyDTO.getAdOrderId();
			adAndStyleByCompanyDTO.setsAdOrderId(adOrderId.toString());
		}
		// 根据公司id获取样式
		List<AdAndStyleByCompanyDTO> adStyleList = adStyleService.selectStyleByCompanyId(companyId, null);
		// 根据公司id获取广告
		List<AdAndStyleByCompanyDTO> adList = adService.selectAdListForComIdAndSytle(companyId, null, null);
		if (log.isDebugEnabled()) {
			log.info("selectStyle......");
			log.debug("selectStyle...companyId:{}", companyId);
			log.debug("selectStyle...result adOrderList size:{}", adOrderList.size());
		}
		model.addAttribute("adOrderList", adOrderList);
		model.addAttribute("adStyleList", adStyleList);
		model.addAttribute("adList", adList);
		model.addAttribute("companyId", companyId);
		return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 
	 * @Title: selectStyle  
	 * @Description: 获取样式下拉列表；和广告下拉框 
	 * @param companyId
	 * @param adOrderId
	 * @param model
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取样式下拉列表")
	@RequestMapping(value = "/selectStyle", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String selectStyle(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId, Model model) {
		log.info("selectStyle.........");
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas) {
			companyId = null;
		}
		if (adOrderIdHas) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}

		List<AdAndStyleByCompanyDTO> adStyleList = adStyleService.selectStyleByCompanyId(companyId, adOrderId);
		// 根据公司id获取广告
		List<AdAndStyleByCompanyDTO> adList = adService.selectAdListForComIdAndSytle(companyId, adOrderId, null);
		if (log.isDebugEnabled()) {
			log.info("selectStyle......");
			log.debug("selectStyle...companyId:{}", companyId);
			log.debug("selectStyle...adOrderId:{}", adOrderId);
			log.debug("selectStyle...result adStyleList size:{}", adStyleList.size());
		}
		model.addAttribute("adStyleList", adStyleList);
		model.addAttribute("adList", adList);
		model.addAttribute("companyId", companyId);
		model.addAttribute("adOrderId", adOrderId);
		return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
	}

/**
 * 	
 * @Title: selectAd  
 * @Description: 根据公司id和样式id获取广告列表
 * @param companyId
 * @param adOrderId
 * @param adStyleId
 * @param model
 * @return
 * @author guofei 
 * @date 2017年5月5日
 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取广告下拉列表")
	@RequestMapping(value = "/selectAd", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAd(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId, @RequestParam(required = false) String adStyleId,
			Model model) {
		log.info("selectAd。。。。。。。。。。。");

		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adStyleHas = StringUtils.isNull(adStyleId);

		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		if (adStyleHas) {
			adStyleId = null;
		}
		if (adOrderIdHas) {
			adOrderId = null;
		}
		// 根据公司id获取对应的广告以及广告的样式名
		List<AdAndStyleByCompanyDTO> adList = adService.selectAdListForComIdAndSytle(companyId, adOrderId, adStyleId);
		if (log.isDebugEnabled()) {
			log.info("selectAd......");
			log.debug("selectAd...companyId:{}", companyId);
			log.debug("selectAd...adStyleId:{}", adStyleId);
			log.debug("selectAd...adOrderId:{}", adOrderId);
			log.debug("selectAd...result adList size:{}", adList.size());
		}
		model.addAttribute("adList", adList);
		model.addAttribute("companyId", companyId);
		model.addAttribute("adOrderId", adOrderId);
		model.addAttribute("adStyleId", adStyleId);
		return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 
	 * @Title: adCountChart  
	 * @Description: 基于广告统计图 
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param model
	 * @param response
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@ResponseBody
	@RequestMapping(value = "adCountChart", produces = "text/plain;charset=UTF-8")
	public String adCountChart(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, Model model, HttpServletResponse response) {
		log.info("showChartStatistics..........");
		DecimalFormat df = new DecimalFormat("######0.0000");
		// 从ajax取出总量，并进行 轧差计算

		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (adOrderIdHas) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		List<DisplayCountDTO> AdCountList = adDisplayCountService.selectCountForLine(companyId,adOrderId, adStyleId, adId,
				beforeDay);
		Long sumShow1 = (long) 0;
		Long sumUser1 = (long) 0;
		for (int i = 0; i < AdCountList.size(); i++) {
				String sumShowCount = AdCountList.get(i).getSumShowCount();
				String sumUserCount = AdCountList.get(i).getSumUserCount();
				long sumShowCountLong = Long.parseLong(sumShowCount);
				sumShow1 += sumShowCountLong;
				long sumUserCountLong = Long.parseLong(sumUserCount);
				sumUser1 += sumUserCountLong;
		}
		
		Double sumShow2=(Double.parseDouble(sumShow1.toString())/10000);
		Double sumUser2=(Double.parseDouble(sumUser1.toString())/10000);
		String sumShow = df.format(sumShow2);
		String sumUser = df.format(sumUser2);
		List<DisplayCountDTO> countDTOList = new ArrayList<DisplayCountDTO>();

		// 获取坐标轴横轴的数据
		List<Date> dateList = null;
		try {
			dateList = new ArrayList<Date>();
			int intdays = 0;
			if (daysHas) {
				intdays = 7;
			} else {
				intdays = Integer.parseInt(days);
			}
			for (int i = intdays; i > 0; i--) {
				Date date = new Date();// 取时间
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(date);
				calendar.add(calendar.DATE, -(i));// 把日期往后增加一天.整数往后推,负数往前移动
				date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
				dateList.add(date);
			}
		} catch (NumberFormatException e) {
		}

		if (dateList != null) {
			int start = 0;
			for (Date date : dateList) {
				String sumShowCount = null;
				String sumUserCount = null;
				Date date1 = null;
				Date dayTime = null;
				int index = 0;
				String format = DateUtils.format(date, "yyyy-MM-dd");
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					date1 = sdf.parse(format);
				} catch (ParseException e) {
				}
				for (DisplayCountDTO displayCountDTO : AdCountList) {

					String totalShowCount = displayCountDTO.getSumShowCount();
					String totalUserCount = displayCountDTO.getSumUserCount();
					double parsesumShowCount1Double = Double.parseDouble(totalShowCount);
					double parsesumUserCount1Double = Double.parseDouble(totalUserCount);
					String totalShowCount3 = df.format(parsesumShowCount1Double / 10000);
					String totalUserCount3 = df.format(parsesumUserCount1Double / 10000);
					sumShowCount = totalShowCount3;
					sumUserCount = totalUserCount3;
					dayTime = displayCountDTO.getDayTime();
					String format2 = DateUtils.format(dayTime, "yyyy-MM-dd");
					if (format.equals(format2)) {
						index++;
						start = 1;
						break;
					}

				}
				if (index == 0) {
					DisplayCountDTO countDTO = new DisplayCountDTO();
					countDTO.setDateTime(date1);
					countDTO.setSumShowCount("0");
					countDTO.setSumUserCount("0");
					countDTOList.add(countDTO);
				} else {
					DisplayCountDTO countDTO = new DisplayCountDTO();
					countDTO.setDateTime(date1);
					countDTO.setSumShowCount(sumShowCount);
					countDTO.setSumUserCount(sumUserCount);
					countDTOList.add(countDTO);
				}
			}

		}
		if (log.isDebugEnabled()) {
			log.info("adCountChart......");
			log.debug("adCountChart...companyId:{}", companyId);
			log.debug("adCountChart...adStyleId:{}", adStyleId);
			log.debug("adCountChart...adId:{}", adId);
			log.debug("adCountChart...days:{}", days);
			log.debug("adCountChart...result AdCountList size :{}", AdCountList.size());
		}
		model.addAttribute("AdCountList", AdCountList);
		model.addAttribute("countDTOList", countDTOList);
		model.addAttribute("days", days);
		model.addAttribute("sumShow", sumShow);
		model.addAttribute("sumUser", sumUser);
		return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 
	 * @Title: platformCountChart  
	 * @Description:  基于视频平台统计图  
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param model
	 * @param response
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@ResponseBody
	@RequestMapping(value = "platformCountChart", produces = "text/plain;charset=UTF-8")
	public String platformCountChart(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, Model model, HttpServletResponse response) {
		log.info("platformCountChart..............");
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (adOrderIdHas) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}

		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		List<DisplayCountDTO> AdPlatCountList = adDisplayCountService.selectCountForPlatChart(companyId,adOrderId, adStyleId, adId,
				beforeDay);
		for (DisplayCountDTO displayCountDTO : AdPlatCountList) {
			Integer totalShowCount = displayCountDTO.getTotalShowCount();
			Integer totalUserCount = displayCountDTO.getTotalUserCount();
			double parsesumShowCount1Double = Double.parseDouble(totalShowCount.toString());
			double parsesumUserCount1Double = Double.parseDouble(totalUserCount.toString());
			DecimalFormat df = new DecimalFormat("######0.0000");
			String totalShowCount3 = df.format(parsesumShowCount1Double / 10000);
			String totalUserCount3 = df.format(parsesumUserCount1Double / 10000);
			double parseshowDouble2 = Double.parseDouble(totalShowCount3);
			double parseUserDouble2 = Double.parseDouble(totalUserCount3);
			if(totalShowCount==0){
				displayCountDTO.setdShowCount((double) 0);
			}else{
				displayCountDTO.setdShowCount(parseshowDouble2);
			}
			if(totalUserCount==0){
				displayCountDTO.setdUserCount((double) 0);
			}else{
				displayCountDTO.setdUserCount(parseUserDouble2);
			}
		}

		if (log.isDebugEnabled()) {
			log.info("platformCountChart......");
			log.debug("platformCountChart...companyId:{}", companyId);
			log.debug("platformCountChart...adStyleId:{}", adStyleId);
			log.debug("platformCountChart...adId:{}", adId);
			log.debug("platformCountChart...days:{}", days);
			log.debug("platformCountChart...result AdPlatCountList size :{}", AdPlatCountList.size());
		}
		model.addAttribute("AdPlatCountList", AdPlatCountList);
		return JSON.toJSONString(model, SerializerFeature.WriteMapNullValue);
	}

	/**
	 * 
	 * @Title: platformCountExcel  
	 * @Description: 基于视频平台数据导出
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param response
	 * @throws IOException
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "导出平台表格")
	@RequestMapping(value = "/platformCountExcel", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public void platformCountExcel(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, HttpServletResponse response) throws IOException {
		log.info("platformCountExcel..............");
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (adOrderIdHas == true) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		HSSFWorkbook workbook = adDisplayCountService.getPlatWorkBook(companyId,adOrderId, adStyleId, adId, days, beforeDay);
		// 根据id获取公司名
		String companyName = "全部公司";
		Company company = companyService.selectById(companyId);
		if (company != null) {
			companyName = company.getCompanyName();
		}
		// 生成文件名称
		String filename = companyName + "平台" + System.nanoTime() + ".xls";
		String codedFileName = java.net.URLEncoder.encode(filename, "UTF-8");

		// 设置响应内容类型
		response.setContentType("application/vnd.ms-excel");

		// 设置响应头
		// 表示客户端浏览器以另存为窗口打开附件
		response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName);
		// 将HSSFWorkbook写入到响应流中，将数据再响应给客户端浏览器(文件下载)
		ServletOutputStream sos = response.getOutputStream(); // 字节流
		if (log.isDebugEnabled()) {
			log.info("platformCountExcel......");
			log.debug("platformCountExcel...companyId:{}", companyId);
			log.debug("platformCountExcel...adStyleId:{}", adStyleId);
			log.debug("platformCountExcel...adId:{}", adId);
			log.debug("platformCountExcel...days:{}", days);
			log.debug("platformCountExcel...result filename :{}", codedFileName);
		}
		workbook.write(sos);
	}

	/**
	 * 
	 * @Title: showDisplayCountExcel  
	 * @Description: 基于广告数据导出 
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param response
	 * @throws IOException
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "导出用户表格")
	@RequestMapping(value = "/adCountExcel", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public void showDisplayCountExcel(@RequestParam(required = false) String companyId,
			@RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, HttpServletResponse response) throws IOException {
		log.info("adCountExcel...........");
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		boolean companyHas = StringUtils.isNull(companyId);
		User shiroUser = getShiroUser();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (adOrderIdHas == true) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		HSSFWorkbook workbook = adDisplayCountService.getUserWorkBook(companyId,adOrderId, adStyleId, adId, days, beforeDay);
		// 根据id获取公司名
		String companyName = "全部公司";
		Company company = companyService.selectById(companyId);
		if (company != null) {
			companyName = company.getCompanyName();
		}
		// 生成文件名称
		String filename = companyName + System.nanoTime() + ".xls";
		String codedFileName = java.net.URLEncoder.encode(filename, "UTF-8");
		// 设置响应内容类型
		response.setContentType("application/vnd.ms-excel");
		// 设置响应头
		// 表示客户端浏览器以另存为窗口打开附件
		response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName);
		// 将HSSFWorkbook写入到响应流中，将数据再响应给客户端浏览器(文件下载)
		ServletOutputStream sos = response.getOutputStream(); // 字节流
		if (log.isDebugEnabled()) {
			log.info("adCountExcel......");
			log.debug("adCountExcel...companyId:{}", companyId);
			log.debug("adCountExcel...adStyleId:{}", adStyleId);
			log.debug("adCountExcel...adId:{}", adId);
			log.debug("adCountExcel...days:{}", days);
			log.debug("adCountExcel...result filename :{}", codedFileName);
		}
		workbook.write(sos);
	}

	/**
	 * 
	 * @Title: adCount  
	 * @Description: 获取数据统计列表 用户 
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param model
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	// @RequiresPermissions(value = {
	// AuthorityConstants.DataCenter.PERMISSION_COUNTBYAD_LIST }, logical =
	// Logical.OR)
	@RequestMapping(value = "/adCount")
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取数据统计列表")
	public String adCount(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
			@RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(required = false) String companyId, @RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, Model model) {
		log.info("adCount...........");
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adOrderIdHas = StringUtils.isNull(companyId);
		boolean orderIdHas = StringUtils.isNull(adOrderId);
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		User shiroUser = getShiroUser();
		Integer admin = shiroUser.getAdmin();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (adOrderIdHas) {
			adOrderId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}
		if (orderIdHas) {
			adOrderId = null;
		}
		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
			days = "7";
		}
		List<Company> allCompanyList = null;
		if (companyId1 == 1) {
			// 获取公司下拉框
			EntityWrapper<Company> entityWrapper = new EntityWrapper<>(new Company());
			entityWrapper.eq("status", 3).eq("deleted", 0).eq("company_type", 2);
			allCompanyList = companyService.selectList(entityWrapper);
		}
		// 根据公司id获取订单
//		EntityWrapper<AdOrder> entityWrapper = new EntityWrapper<>(new AdOrder());
//		entityWrapper.eq("company_id", companyId);
//		List<AdOrder> adOrderList = adOrderService.selectList(entityWrapper);
		List<AdAndStyleByCompanyDTO> adOrderList = null;
		List<AdAndStyleByCompanyDTO> adStyleList = null;
		List<AdAndStyleByCompanyDTO> adList = null;
		if(companyId1 != 1||adOrderIdHas==false||companyHas==false){
			 adOrderList = adOrderService.selectOrderList(companyId);
		}
		if(adStyleIdHas==false||adOrderIdHas==false||companyHas==false){
			 adStyleList = adStyleService.selectStyleByCompanyId(companyId, adOrderId);
		}
		if(adIdHas==false||adStyleIdHas==false||companyHas==false||adOrderIdHas==false){
		adList = adService.selectAdListForComIdAndSytle(companyId, adOrderId, adStyleId);
		}

		Page<DisplayCountDTO> companyDisplayPage = adDisplayCountService.selectAdAndCountPage(pageNum, pageSize,
				companyId, adOrderId, adStyleId, adId, beforeDay);
		PageInfo<DisplayCountDTO> pageInfo = new PageInfo<>(companyDisplayPage);
		List<DisplayCountDTO> adDisplayCountList = companyDisplayPage.getRecords();

		if (log.isDebugEnabled()) {
			log.info("adCount......");
			log.debug("adCount...PAGE_NUM:{}", pageNum);
			log.debug("adCount...PAGE_SIZE:{}", pageSize);
			log.debug("adCount...adId:{}", adId);
			log.debug("adCount...days:{}", days);
			log.debug("adCount...companyId:{}", companyId);
			log.debug("adCount...adStyleId:{}", adStyleId);
			log.debug("adCount...result companyDisplayPage size:{}", companyDisplayPage.getRecords().size());
			if (allCompanyList != null) {
				log.debug("platFormCount...result allCompanyList size:{}", allCompanyList.size());
			}
		}
		model.addAttribute("page", pageInfo);
		model.addAttribute("adDisplayCountList", adDisplayCountList);
		model.addAttribute("companyList", allCompanyList);
		model.addAttribute("adStyleList", adStyleList);
		model.addAttribute("adList", adList);
		model.addAttribute("companyId", companyId);
		model.addAttribute("adStyleId", adStyleId);
		model.addAttribute("adOrderId", adOrderId);
		model.addAttribute("adId", adId);
		model.addAttribute("days", days);
		model.addAttribute("admin", admin);
		model.addAttribute("adOrderList", adOrderList);
		return "dataStatistics/adCount";
	}

	/**
	 * 
	 * @Title: platFormCount  
	 * @Description:获取平台数据统计 
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param model
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	// @RequiresPermissions(value = {
	// AuthorityConstants.DataCenter.PERMISSION_COUNTBYPLATFORM_LIST }, logical
	// = Logical.OR)
	@RequestMapping(value = "/platFormCount")
	@SystemControllerLog(moduleName = { SYSTEM_MODULE }, description = "获取平台数据统计列表")
	public String platFormCount(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
			@RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(required = false) String companyId, @RequestParam(required = false) String adOrderId,
			@RequestParam(required = false) String adStyleId, @RequestParam(required = false) String adId,
			@RequestParam(required = false) String days, Model model) {
		log.info("platFormCount.......");
		boolean companyHas = StringUtils.isNull(companyId);
		boolean adOrderIdHas = StringUtils.isNull(adOrderId);
		boolean adStyleIdHas = StringUtils.isNull(adStyleId);
		boolean adIdHas = StringUtils.isNull(adId);
		boolean daysHas = StringUtils.isNull(days);
		User shiroUser = getShiroUser();
		Integer admin = shiroUser.getAdmin();
		Long companyId1 = shiroUser.getCompanyId();
		String companyId2 = companyId1.toString();
		if (companyHas == true) {
			companyId = null;
		}
		if (companyId1 != 1) {
			companyId = companyId2;
		}

		if (adOrderIdHas) {
			adOrderId = null;
		}
		if (adStyleIdHas == true) {
			adStyleId = null;
		}
		if (adIdHas == true) {
			adId = null;
		}
		String beforeDay = null;
		if (daysHas == false) {
			Long valueOf = Long.valueOf(days);
			beforeDay = DateUtils.getBeforeDay(valueOf);
		}
		if (daysHas) {
			Long valueOf = Long.valueOf(7);
			beforeDay = DateUtils.getBeforeDay(valueOf);
			days = "7";
		}
		List<Company> allCompanyList = null;
		if (companyId1 == 1||companyHas==false) {
			// 获取公司下拉框
			EntityWrapper<Company> entityWrapper = new EntityWrapper<>(new Company());
			entityWrapper.eq("status", 3).eq("deleted", 0).eq("company_type", 2);
			allCompanyList = companyService.selectList(entityWrapper);
		}
		// 根据公司id获取订单
		// EntityWrapper<AdOrder> entityWrapper = new EntityWrapper<>(new
		// AdOrder());
		// entityWrapper.eq("company_id", companyId);
		// List<AdOrder> adOrderList = adOrderService.selectList(entityWrapper);
		List<AdAndStyleByCompanyDTO> adOrderList = null;
		List<AdAndStyleByCompanyDTO> adStyleList = null;
		List<AdAndStyleByCompanyDTO> adList = null;
		if(companyId1 != 1||adOrderIdHas==false||companyHas==false){
			 adOrderList = adOrderService.selectOrderList(companyId);
		}
		if(adStyleIdHas==false||adOrderIdHas==false||companyHas==false){
			 adStyleList = adStyleService.selectStyleByCompanyId(companyId, adOrderId);
		}
		if(adIdHas==false||adStyleIdHas==false||companyHas==false||adOrderIdHas==false){
		adList = adService.selectAdListForComIdAndSytle(companyId, adOrderId, adStyleId);
		}

		Page<DisplayCountDTO> platDisplayPage = adDisplayCountService.selectCountPlatPage(pageNum, pageSize, companyId,
				adOrderId, adStyleId, adId, beforeDay);
		PageInfo<DisplayCountDTO> pageInfo = new PageInfo<>(platDisplayPage);
		List<DisplayCountDTO> AdPlatCountList = platDisplayPage.getRecords();
		if (log.isDebugEnabled()) {
			log.info("platFormCount......");
			log.debug("platFormCount...PAGE_NUM:{}", pageNum);
			log.debug("platFormCount...PAGE_SIZE:{}", pageSize);
			log.debug("platFormCount...adId:{}", adId);
			log.debug("platFormCount...days:{}", days);
			log.debug("platFormCount...companyId:{}", companyId);
			log.debug("platFormCount...adStyleId:{}", adStyleId);
			log.debug("platFormCount...result platDisplayPage size:{}", platDisplayPage.getRecords().size());
			log.debug("platFormCount...result AdPlatCountList size:{}", AdPlatCountList.size());
			if (allCompanyList != null) {
				log.debug("platFormCount...result allCompanyList size:{}", allCompanyList.size());
			}
		}
		model.addAttribute("AdPlatCountList", AdPlatCountList);
		model.addAttribute("page", pageInfo);
		model.addAttribute("companyList", allCompanyList);
		model.addAttribute("adStyleList", adStyleList);
		model.addAttribute("adList", adList);
		model.addAttribute("companyId", companyId);
		model.addAttribute("adStyleId", adStyleId);
		model.addAttribute("adId", adId);
		model.addAttribute("days", days);
		model.addAttribute("admin", admin);
		model.addAttribute("adOrderId", adOrderId);
		model.addAttribute("adOrderList", adOrderList);
		return "dataStatistics/platFormCount";
	}
}