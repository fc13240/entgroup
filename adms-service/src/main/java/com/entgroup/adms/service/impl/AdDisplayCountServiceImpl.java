package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.mapper.AdDisplayCountMapper;
import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.model.system.OrderAd;
import com.entgroup.adms.service.*;
import com.entgroup.adms.util.DateUtils;
import com.entgroup.adms.util.StringUtils;
import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日曝光统计 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AdDisplayCountServiceImpl extends ServiceImpl<AdDisplayCountMapper, AdDisplayCount>
		implements AdDisplayCountService {

	@Resource
	private CompanyService companyService;

    //环形图颜色配置
	private String[] colors = new String[] { "#f56954", "#00c0ef", "#00bcd5", " #ff635c", " #00d59b", " #efb607" };

	/**
	 *
	 * @Title: selectHomeDisplayCountPage
	 * @Description: 统计前一天的订单列表信息,分页
	 * @param beforeDay
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月8日
	 */
	@Override
	public List<DisplayCountDTO> selectHomeDisplayCountPage(String beforeDay, long companyId){
		List<DisplayCountDTO> list = baseMapper.selectHomeDisplayCountPage(beforeDay,companyId);
		return list;
	}

// edited by qmh on 2017-07-02 10:30 begin
	/**
	 *
	 * @Title: selectHomeDisplayCountList
	 * @Description: 公司下订单信息列表
	 * @param adOrder
	 * @return
	 * @author qmh
	 * @date 2017年7月2日
	 */
	@Override
	public List<DisplayCountResultDTO> selectHomeDisplayCountList(AdOrder adOrder) {
		List<DisplayCountResultDTO> list = baseMapper.selectHomeDisplayCountList(adOrder);
		return list;
	}
	// edited by qmh on 2017-07-02 10:30 end

	/**
	 *
	 * @Title: selectHomeOtherList
	 * @Description: 获取首页中其它信息(不包括折线图数据)
	 * @param beforeDay
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月11日
	 */
	@Override
	public List<DisplayCountDTO> selectHomeOtherList(String beforeDay, long companyId){
		List<DisplayCountDTO> list = baseMapper.selectHomeOtherList(beforeDay,companyId);
		return list;
	}

	/**
	 *
	 * @Title: selectGraphCount
	 * @Description: 获取首页折线图数据
	 * @param orderId
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月12日
	 */
	@Override
	public List<DisplayCountDTO> selectGraphCount(String orderId,long companyId){
		List<DisplayCountDTO> list = baseMapper.selectGraphCount(orderId,companyId);
		return list;
	}

	/**
	 *
	 * @Title: selectGraphCount
	 * @Description: 获取首页订单名称列表
	 * @param companyId
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月12日
	 */
	@Override
	public List<DisplayCountDTO> selectOrderNameList(long companyId,Integer userId){
		List<DisplayCountDTO> list = baseMapper.selectOrderNameList(companyId,userId);
		return list;
	}

	/**
	 *
	 * @Title: selectAdDisplayCountPage
	 * @Description: 获取基于广告订单列表 分页
	 * @param page
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Override
	public Page<DisplayCountDTO> selectAdDisplayCountPage(Page<DisplayCountDTO> page,long companyId){
		/*page.setRecords(baseMapper.selectAdDisplayCountPage(page,companyId));*/
		// edited by xiaokun on 2017-06-29 16:49 begin
		List<DisplayCountDTO> displayCountDTOList = baseMapper.selectAdDisplayCountPage(page, companyId);
		for (DisplayCountDTO displayCountDTO : displayCountDTOList) {
			displayCountDTO.setBeginTime(displayCountDTO.getBeginTime().replace("-", "/"));
			displayCountDTO.setEndTime(displayCountDTO.getEndTime().replace("-", "/"));
		}
		page.setRecords(displayCountDTOList);
		// edited by xiaokun on 2017-06-29 16:49 end
		return page;
	}

	/**
	 *
	 * @Title: selectAdOtherList
	 * @Description: 获取基于广告中其它信息(不包括折线图数据)
	 * @param dayPeriod
	 * @param orderId
	 * @param companyId
	 * @param  userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Override
	public List<DisplayCountDTO> selectAdOtherList(Integer dayPeriod,String orderId, long companyId,Integer userId){
		List<DisplayCountDTO> list = baseMapper.selectAdOtherList(dayPeriod,orderId,companyId,userId);
		return list;
	}

	/**
	 *
	 * @Title: selectAdGraphCount
	 * @Description: 获取基于广告折线图数据
	 * @param dayPeriod
	 * @param orderId
	 * @param companyId
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Override
	public List<DisplayCountDTO> selectAdGraphCount(Integer dayPeriod,String orderId,long companyId,Integer userId){
		List<DisplayCountDTO> list = baseMapper.selectAdGraphCount(dayPeriod,orderId,companyId,userId);
		return list;
	}


	/**
	 *
	 * @Title: selectCompanyNameList
	 * @Description: 获取管理员端资源统计中公司名称列表
	 * @param  userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Override
	public List<DisplayCountDTO> selectCompanyNameList(Integer userId){
		List<DisplayCountDTO> list = baseMapper.selectCompanyNameList(userId);
		return list;
	}

	/**
	 *
	 * @Title: selectAdminDisplayCountListPage
	 * @Description: 获取管理员端资源统计中公司相关信息列表  分页
	 * @param page
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	@Override
	public Page<DisplayCountDTO> selectAdminDisplayCountListPage(Page<DisplayCountDTO> page,Integer userId){
		 page.setRecords(baseMapper.selectAdminDisplayCountListPage(page,userId));
		return page;
	}

	/**
	 *
	 * @Title: selectVideoForPlatCountPage
	 * @Description: 基于视频平台列表 分页
	 * @param page
	 * @param adDisplayCount
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	@Override
	public Page<DisplayCountDTO> selectVideoForPlatCountPage(Page<DisplayCountDTO> page,AdDisplayCount adDisplayCount){
		page.setRecords(baseMapper.selectVideoForPlatCountPage(page,adDisplayCount));
		return page;
	}

	/**
	 *
	 * @Title: selectProgramTypeCountPage
	 * @Description: 基于节目类型列表 分页
	 * @param page
	 * @param adDisplayCount
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	@Override
	public Page<DisplayCountDTO> selectProgramTypeCountPage(Page<DisplayCountDTO> page,AdDisplayCount adDisplayCount){
		page.setRecords(baseMapper.selectProgramTypeCountPage(page,adDisplayCount));
		return page;
	}

	/**
	 *
	 * @Title: selectVideoAndProgramOrderNameList
	 * @Description: 基于节目类型列表/视频平台 订单名称列表
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	@Override
	public List<DisplayCountDTO> selectVideoAndProgramOrderNameList(long companyId){
		List<DisplayCountDTO> list = baseMapper.selectVideoAndProgramOrderNameList(companyId);
		return list;
	}

	/**
	 *
	 * @Title: selectVideoForPlatAdCount
	 * @Description: 基于视频平台广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	@Override
	public List<DisplayCountResultDTO> selectVideoForPlatAdCount(long companyId){
		List<DisplayCountResultDTO> list = baseMapper.selectVideoForPlatAdCount(companyId);
		return list;
	}

	/**
	 *
	 * @Title: selectProgramTypeAdCount
	 * @Description: 基于节目类型广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	@Override
	public List<DisplayCountResultDTO> selectProgramTypeAdCount(long companyId){
		List<DisplayCountResultDTO> list = baseMapper.selectProgramTypeAdCount(companyId);
		return list;
	}
















	/**
	 * 
	 * @method: staOrderCosumeList
	 * @Description: 获取订单前一天的消费金额
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<DisplayCountDTO> staOrderCosumeList(String beforeDay) {
		return baseMapper.staOrderCosumeList(beforeDay);
	}

	/**
	 * 
	 * @method: selectAdAndCountPage
	 * @Description: 获取用户广告统计分页列表
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public Page<DisplayCountDTO> selectAdAndCountPage(int pageNum, int pageSize, String companyId,String adOrderId, String adStyleId,
			String adId, String beforeDay) {
		Pagination pagination = new Pagination(pageNum, pageSize);
		
		List<DisplayCountDTO> selectAdAndCount = baseMapper.selectAdAndCountPage(pagination, companyId,adOrderId, adStyleId, adId,
				beforeDay);
		Page<DisplayCountDTO> page = new Page<DisplayCountDTO>();
		page.setRecords(selectAdAndCount);
		page.setTotal(pagination.getTotal());
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		return page;
	}

	/**
	 * 
	 * @method: getUserWorkBook
	 * @Description: 导出用户广告excel表格
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public HSSFWorkbook getUserWorkBook(String companyId,String adOrderId, String adStyleId, String adId, String days,
			String beforeDay) {
		boolean daysHas = StringUtils.isNull(days);
		if (daysHas) {
			days = "7";
		}
		// 根据id获取公司名
		String companyName = "全部公司";
		Company company = companyService.selectById(companyId);
		if (company != null) {
			companyName = company.getCompanyName();
		}
		List<DisplayCountDTO> selectAdAndCount = baseMapper.selectAdAndCount(companyId,adOrderId, adStyleId, adId, beforeDay);
		// [1]创建HSSFWorkbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFDataFormat createDataFormat = workbook.createDataFormat();
		HSSFCellStyle createCellStyle = workbook.createCellStyle();
		createCellStyle.setDataFormat(createDataFormat.getFormat("#,#.00"));
		// [2]创建HSSFSheet，设置工作区名称
		String sheetName=null;
		if(adOrderId!=null){
			sheetName = companyName +adOrderId+ "订单近" + days + "天" + "曝光统计";
		}else{
			sheetName = companyName + "近" + days + "天" + "曝光统计";
		}
		HSSFSheet createSheet = workbook.createSheet(sheetName);
		//设置单元格宽度
		createSheet.setColumnWidth(0, 4766);
		createSheet.setColumnWidth(1, 4766);
		HSSFRow createRow = createSheet.createRow(0);
		String heads[] = {"订单号", "广告名称", "广告样式", "曝光量/万", "曝光人数/万" };
		for (int i = 0; i < heads.length; i++) {
			String head = heads[i];
			int cellIndex = i;
			HSSFCell createCell = createRow.createCell(cellIndex);
			createCell.setCellValue(head);
		}
		for (int i = 1; i < selectAdAndCount.size() + 1; i++) {
			int rowIndex = i;
			HSSFRow row = createSheet.createRow(rowIndex);

			List<String> list = new ArrayList<>();
			Date dayTime = selectAdAndCount.get(i - 1).getDayTime();
			String date = DateUtils.format(dayTime, "yyyy-MM-dd");
			String adName = selectAdAndCount.get(i - 1).getAdName();
			String adStyleName = selectAdAndCount.get(i - 1).getAdStyleName();
			String orderId = selectAdAndCount.get(i - 1).getOrderId();
			Integer showCount1 = selectAdAndCount.get(i - 1).getShowCount();
			double doubleValue1 = showCount1.doubleValue();
			Double showCount2 = doubleValue1 / 10000;
			DecimalFormat df = new DecimalFormat("######0.0000");
			String showCount3=((showCount1!=0)?df.format(showCount2):"0");
			String showCount = null;
			if (showCount3 != null) {
				showCount = showCount3.toString();
			}
			Integer userCount1 = selectAdAndCount.get(i - 1).getUserCount();
			Double doubleValue2 = userCount1.doubleValue();
			Double userCount2 = doubleValue2 / 10000;
			String userCount3=((userCount1!=0)?df.format(userCount2):"0");
			String userCount = null;
			if (userCount3 != null) {
				userCount = userCount3.toString();
			}
			list.add(orderId);
			list.add(adName);
			list.add(adStyleName);
			list.add(showCount);
			list.add(userCount);
			for (int j = 0; j < list.size(); j++) {
				int cellIndex = j;
				HSSFCell cell = row.createCell(cellIndex);
				cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(list.get(j));
			}
		}
		return workbook;
	}

	/**
	 * 
	 * @method: selectCountPlatPage
	 * @Description: 获取平台统计数据分页
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public Page<DisplayCountDTO> selectCountPlatPage(int pageNum, int pageSize, String companyId,String adOrderId, String adStyleId,
			String adId, String beforeDay) {
		Pagination pagination = new Pagination(pageNum, pageSize);
		List<DisplayCountDTO> selectPlatCountPage = baseMapper.selectCountPlatPage(pagination, companyId,adOrderId, adStyleId,
				adId, beforeDay);
		Page<DisplayCountDTO> page = new Page<DisplayCountDTO>();
		page.setRecords(selectPlatCountPage);
		page.setTotal(pagination.getTotal());
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		return page;
	}

	/**
	 * 
	 * @method: getPlatWorkBook
	 * @Description: 获取平台统计excel
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public HSSFWorkbook getPlatWorkBook(String companyId,String adOrderId, String adStyleId, String adId, String days,
			String beforeDay) {
		boolean daysHas = StringUtils.isNull(days);
		if (daysHas) {
			days = "7";
		}
		// 根据id获取公司名
		String companyName = "全部公司";
		Company company = companyService.selectById(companyId);
		if (company != null) {
			companyName = company.getCompanyName();
		}
		List<DisplayCountDTO> selectCountPlat = baseMapper.selectCountForPlat(companyId,adOrderId, adStyleId, adId, beforeDay);
		// [1]创建HSSFWorkbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFDataFormat createDataFormat = workbook.createDataFormat();
		HSSFCellStyle createCellStyle = workbook.createCellStyle();
		createCellStyle.setDataFormat(createDataFormat.getFormat("#,#.00"));
		// [2]创建HSSFSheet，设置工作区名称
		String sheetName =null;
		if(adOrderId!=null){
			sheetName = companyName+adOrderId+ "订单在平台" + "近" + days + "天" + "曝光统计";
		}
		sheetName = companyName + "平台" + "近" + days + "天" + "曝光统计";
		HSSFSheet createSheet = workbook.createSheet(sheetName);
		//设置单元格宽度
		createSheet.setColumnWidth(0, 4766);
		createSheet.setColumnWidth(1, 4766);
		createSheet.setColumnWidth(3, 3000);
		HSSFRow createRow = createSheet.createRow(0);
		String heads[] = { "订单号","视频平台", "曝光量/万", "曝光人数/万" };
		for (int i = 0; i < heads.length; i++) {
			String head = heads[i];
			int cellIndex = i;
			HSSFCell createCell = createRow.createCell(cellIndex);
			createCell.setCellValue(head);
		}
		for (int i = 1; i < selectCountPlat.size() + 1; i++) {
			int rowIndex = i;
			HSSFRow row = createSheet.createRow(rowIndex);
			List<String> list = new ArrayList<>();
			String orderId = selectCountPlat.get(i - 1).getOrderId();
			String platCompanyName = selectCountPlat.get(i - 1).getCompanyName();
			Integer totalShowCount1 = (selectCountPlat.get(i - 1).getTotalShowCount());
			double doubleValue1 = totalShowCount1.doubleValue();
			Double totalShowCount2 = doubleValue1 / 10000;
			DecimalFormat df = new DecimalFormat("######0.0000");
			String totalShowCount3=((totalShowCount1!=0)?df.format(totalShowCount2):"0");
			String totalShowCount = null;
			Integer totalUserCount1 = selectCountPlat.get(i - 1).getTotalUserCount();
			double doubleValue2 = totalUserCount1.doubleValue();
			Double totalUserCount2 = doubleValue2 / 10000;
			String totalUserCount3=((totalUserCount1!=0)?df.format(totalUserCount2):"0");
			String totalUserCount = null;
			if (totalShowCount3 != null) {
				totalShowCount = totalShowCount3.toString();
			}
			if (totalUserCount3 != null) {
				totalUserCount = totalUserCount3.toString();
			}
			list.add(orderId);
			list.add(platCompanyName);
			list.add(totalShowCount);
			list.add(totalUserCount);
			for (int j = 0; j < list.size(); j++) {
				int cellIndex = j;
				HSSFCell cell = row.createCell(cellIndex);
				cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(list.get(j));
			}
		}
		return workbook;
	}

	/**
	 * 
	 * @method: selectDisplayCount
	 * @Description:统计前一天的用户和曝光量
	 * @param beforeDay
	 * @param nowDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<AdDisplayCount> selectDisplayCount(String beforeDay, String nowDay) {
		return baseMapper.selectDisplayCount(beforeDay, nowDay);
	}

	/**
	 *
	 * @method: selectDisplayClickCount
	 * @Description:统计前一天的点击次数
	 * @param beforeDay
	 * @param nowDay
	 * @return
	 * @author quminghui
	 * @date 2017-6-26
	 */
	@Override
	public List<AdDisplayCount> selectDisplayClickCount(String beforeDay, String nowDay) {
		return baseMapper.selectDisplayClickCount(beforeDay, nowDay);
	}

	/**
	 * 
	 * @method: selectCountForLine
	 * @Description: 生成统计折线图
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<DisplayCountDTO> selectCountForLine(String companyId,String adOrderId, String adStyleId, String adId, String beforeDay) {
		return baseMapper.selectCountForLine(companyId,adOrderId, adStyleId, adId, beforeDay);
	}

	/**
	 * 
	 * @method: selectCountForPlat
	 * @Description: 获取饼图数据
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<DisplayCountDTO> selectCountForPlat(String companyId,String adOrderId, String adStyleId, String adId, String beforeDay) {
		List<DisplayCountDTO> selectCountForPlat = baseMapper.selectCountForPlat(companyId,adOrderId, adStyleId, adId, beforeDay);
		for (int i = 0; i < selectCountForPlat.size(); i++) {
			selectCountForPlat.get(i).setColor(colors[i % colors.length]);
		}
		return selectCountForPlat;
	}
	/**
	 * 
	 * @method: selectCountForPlatChart  
	 * @Description: 获取平台饼图
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月2日
	 */
	@Override
	public List<DisplayCountDTO> selectCountForPlatChart(String companyId, String adOrderId, String adStyleId,
			String adId, String beforeDay) {
		List<DisplayCountDTO> selectCountForPlatChart=baseMapper.selectCountForPlatChart(companyId,adOrderId, adStyleId, adId, beforeDay);
		for (int i = 0; i < selectCountForPlatChart.size(); i++) {
			selectCountForPlatChart.get(i).setColor(colors[i % colors.length]);
		}
		return selectCountForPlatChart;
	}
	/**
	 * @param companyId
	 * @param adId
	 * @param adStyleId
	 * @param tortTimeStart
	 * @param tortTimeEnd
	 * 
	 * @return List<AdDisplayCount>
	 * 
	 * @throws @title
	 *             getAdCountList4Chart
	 * @description TODO 获取广告相关曝光信息（按日期统计）
	 * @author mxy
	 * @date 2017-04-19 16:43
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public List<AdDisplayCount> getAdCountList4Chart(Long companyId, Long adId, Long adStyleId, Date tortTimeStart,
			Date tortTimeEnd) {
		return baseMapper.getAdCountList4Chart(companyId, adId, adStyleId, tortTimeStart, tortTimeEnd);
	}

	// FIXME 以下为测试统计订单金额 之后需要删除======================================================================

	@Autowired
	private OrderAdService orderAdService;
	@Autowired
	private AdSlotService adSlotService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AdOrderService adOrderService;
	@Autowired
	private AdService adServcie;

	/**
	 * 
	 * @method: selectOneAdOrder
	 * @Description: 根据订单id获取订单 （统计订单金额 之后删除）
	 * @param orderId
	 * @return
	 * @author guofei
	 * @date 2017年4月25日
	 */
	@Override
	public AdOrder selectOneAdOrder(String orderId) {

		return adOrderService.selectOne(new EntityWrapper<AdOrder>().eq("id", orderId)
				.eq("status", 1));
	}

	/**
	 * 
	 * @method: updateAdByOrderId
	 * @Description: 删除广告位和广告关联表
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017年4月25日
	 */
	@Override
	public boolean updateAdByOrderId(List<String> orderIdList) {
		return adSlotService.updateAdByOrderId(orderIdList);
	}

	/**
	 * 
	 * @method: deleteOrderAd
	 * @Description: 删除订单广告关联表
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017年4月25日
	 */
	@Override
	public boolean deleteOrderAd(List<String> orderIdList) {
		return orderAdService.deleteOrderAd(orderIdList);
	}

	/**
	 * 
	 * @method: updateBatchAdOrderById
	 * @Description: 完成订单 将订单 delete设置为1
	 * @param adOrderList
	 * @return
	 * @author guofei
	 * @date 2017年4月25日
	 */
	@Override
	public boolean updateBatchAdOrderById(List<AdOrder> adOrderList) {
		return adOrderService.updateBatchById(adOrderList);
	}

	/**
	 * 
	 * @method: addAdOrderNotice
	 * @Description: 添加订单完成通知
	 * @param selectOneOrder
	 * @author guofei
	 * @date 2017年4月25日
	 */
	@Override
	public void addAdOrderNotice(AdOrder selectOneOrder) {
		noticeService.addAdOrderNotice(selectOneOrder);
	}
	/**
	 * 
	 * @method: selectOrderAdList  
	 * @Description: 获取订单广告关联表
	 * @param orderId
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Override
	public List<OrderAd> selectOrderAdList(String orderId) {
		return orderAdService.selectList(new EntityWrapper<OrderAd>().eq("order_id", orderId)
				.eq("deleted", 0));
	}
	/**
	 * 
	 * @method: updateBatchAd  
	 * @Description: 批量重启广告 
	 * @param adIdList
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Override
	public boolean updateBatchAd(List<String> adIdList) {
		return adServcie.updateBatchAd(adIdList);
	}


	// 以上为测试统计订单金额
	// 之后需要删除======================================================================


	// edited by xiaokun on 2017-06-29 22:38 begin

	/**
	 * 基于节目类型获取统计数据
	 */
	public List<DisplayCountResultDTO> getAdDisplayCountByProgramType(AdOrder adOrder) {
		// 获取所有对应slotId
		DisplayCountResultDTO displayCountResult0DTO = baseMapper.getAllSlotIds(adOrder);
		displayCountResult0DTO.setCompanyId(adOrder.getCompanyId());
		List<DisplayCountResultDTO> displayCountResultDTO1List = Lists.newArrayList();
		List<DisplayCountResultDTO> displayCountResultDTO2List = Lists.newArrayList();
		if (null != displayCountResult0DTO.getSlotIds()) {
			// 获取分组展示slotIds
			displayCountResult0DTO.setGroupBy("programType.id");
			displayCountResultDTO1List = baseMapper.getSlotIdsGroup(displayCountResult0DTO);
			if (null != displayCountResultDTO1List && displayCountResultDTO1List.size() != 0) {
				// 获取其他展示数据
				displayCountResultDTO2List = baseMapper.getDisplayCountGroup(displayCountResult0DTO);
			}
			/*for (DisplayCountResultDTO adSlotIdsResult : displayCountResultDTO1List) {
				String adSlotIds = "";
				for (DisplayCountResultDTO displayCountResultDTO : displayCountResultDTO2List) {
					if (adSlotIdsResult.getProgramTypeId() == displayCountResultDTO.getProgramTypeId()) {
						adSlotIds = adSlotIdsResult.getSlotIds();
						Integer slotCount = adSlotIds.split(",").length;
						displayCountResultDTO.setSlotCount(slotCount);

						if (null == displayCountResultDTO.getSlotCount()) {
							displayCountResultDTO.setSlotCount(0);
						}
						if (null == displayCountResultDTO.getShowCounts()) {
							displayCountResultDTO.setShowCounts(0D);
						}
						if (null == displayCountResultDTO.getClickCounts()) {
							displayCountResultDTO.setClickCounts(0D);
						}
						displayCountResultDTO.setCtr((displayCountResultDTO.getClickCounts()/displayCountResultDTO.getShowCounts())*100);
						adSlotIds = "";
					}
				}
			}*/
			for (DisplayCountResultDTO displayCountResultDTO : displayCountResultDTO1List) {
				displayCountResultDTO.setSlotCount(displayCountResultDTO.getSlotIds().split(",").length);
				for (DisplayCountResultDTO countResultDTO : displayCountResultDTO2List) {
					if (displayCountResultDTO.getProgramTypeId() == countResultDTO.getProgramTypeId()) {
						displayCountResultDTO.setShowCounts(null == countResultDTO.getShowCounts() ? 0D : countResultDTO.getShowCounts());
						displayCountResultDTO.setClickCounts(null == countResultDTO.getClickCounts() ? 0D : countResultDTO.getClickCounts());
						displayCountResultDTO.setCash(null == countResultDTO.getCash() ? 0D : countResultDTO.getCash());
						displayCountResultDTO.setCtr(displayCountResultDTO.getShowCounts() == 0D ? 0D : (displayCountResultDTO.getClickCounts()/displayCountResultDTO.getShowCounts()*100));
					}
				}
			}
		} else {
			return null;
		}
		return displayCountResultDTO1List;
	}
	// edited by xiaokun on 2017-06-29 22:38 end

	// edited by qmh on 2017-06-30 10:30 begin
	/**
	 * 基于广告获取统计数据
	 */
	@Override
	public Page<DisplayCountResultDTO> getAdDisplayCountByAd(Page<DisplayCountResultDTO> page,AdOrder adOrder) {
		page.setRecords(baseMapper.getAdDisplayCountByAd(page,adOrder));

		return page;
	}

	/**
	 * 基于视频平台获取统计数据
	 */
	@Override
	public List<DisplayCountResultDTO> getAdDisplayCountByPlatform(AdOrder adOrder) {
		// 获取所有对应slotId
		DisplayCountResultDTO displayCountResult0DTO = baseMapper.getAllSlotIds(adOrder);
		displayCountResult0DTO.setCompanyId(adOrder.getCompanyId());
		List<DisplayCountResultDTO> displayCountResultDTO1List = Lists.newArrayList();
		List<DisplayCountResultDTO> displayCountResultDTO2List = Lists.newArrayList();
		if (null != displayCountResult0DTO.getSlotIds()) {
			// 获取分组展示slotIds
			displayCountResult0DTO.setGroupBy("platform.id");
			displayCountResultDTO1List = baseMapper.getSlotIdsGroup(displayCountResult0DTO);
			if (null != displayCountResultDTO1List && displayCountResultDTO1List.size() != 0) {
				// 获取其他展示数据
				displayCountResultDTO2List = baseMapper.getDisplayCountGroup(displayCountResult0DTO);
			}
			/*for (DisplayCountResultDTO adSlotIdsResult : displayCountResultDTO1List) {
				String adSlotIds = "";
				for (DisplayCountResultDTO displayCountResultDTO : displayCountResultDTO2List) {
					if (adSlotIdsResult.getPlatformId() == displayCountResultDTO.getPlatformId()) {
						adSlotIds = adSlotIdsResult.getSlotIds();
						Integer slotCount = adSlotIds.split(",").length;
						displayCountResultDTO.setSlotCount(slotCount);

						if (null == displayCountResultDTO.getSlotCount()) {
							displayCountResultDTO.setSlotCount(0);
						}
						if (null == displayCountResultDTO.getShowCounts()) {
							displayCountResultDTO.setShowCounts(0D);
						}
						if (null == displayCountResultDTO.getClickCounts()) {
							displayCountResultDTO.setClickCounts(0D);
						}
						displayCountResultDTO.setCtr((displayCountResultDTO.getClickCounts()/displayCountResultDTO.getShowCounts())*100);
						adSlotIds = "";
					}
				}
			}*/
			for (DisplayCountResultDTO displayCountResultDTO : displayCountResultDTO1List) {
				displayCountResultDTO.setSlotCount(displayCountResultDTO.getSlotIds().split(",").length);
				for (DisplayCountResultDTO countResultDTO : displayCountResultDTO2List) {
					if (displayCountResultDTO.getPlatformId() == countResultDTO.getPlatformId()) {
						displayCountResultDTO.setShowCounts(null == countResultDTO.getShowCounts() ? 0D : countResultDTO.getShowCounts());
						displayCountResultDTO.setClickCounts(null == countResultDTO.getClickCounts() ? 0D : countResultDTO.getClickCounts());
						displayCountResultDTO.setCash(null == countResultDTO.getCash() ? 0D : countResultDTO.getCash());
						displayCountResultDTO.setCtr(displayCountResultDTO.getShowCounts() == 0D ? 0D : (displayCountResultDTO.getClickCounts()/displayCountResultDTO.getShowCounts()*100));
					}
				}
			}
		} else {
			return null;
		}
		return displayCountResultDTO1List;
	}


	// edited by qmh on 2017-06-30 10:30 end
}
