package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.model.system.AdOrder;
import com.entgroup.adms.model.system.OrderAd;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 日曝光统计 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface AdDisplayCountService extends IService<AdDisplayCount> {

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
	List<DisplayCountDTO> selectHomeDisplayCountPage(
			String beforeDay,
			long companyId);

	// edited by qmh on 2017-07-02 10:25 begin
	/**
	 *
	 * @Title: selectHomeDisplayCountList
	 * @Description: 公司下订单信息列表
	 * @param adOrder
	 * @return
	 * @author qmh
	 * @date 2017年7月2日
	 */
	List<DisplayCountResultDTO> selectHomeDisplayCountList(AdOrder adOrder);
	// edited by qmh on 2017-07-02 10:25 end

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
	List<DisplayCountDTO> selectHomeOtherList(
			String beforeDay,
			long companyId);

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
	List<DisplayCountDTO> selectGraphCount(
			String orderId,
			long companyId);

	/**
	 *
	 * @Title: selectOrderNameList
	 * @Description: 获取首页订单名称列表
	 * @param companyId
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月12日
	 */
	List<DisplayCountDTO> selectOrderNameList(
			long companyId,
			Integer userId);

	/**
	 *
	 * @Title: selectAdDisplayCountPage
	 * @Description: 获取基于广告订单列表 分页
	 * @param page,
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	Page<DisplayCountDTO> selectAdDisplayCountPage(
			Page<DisplayCountDTO> page,
			long companyId);

	/**
	 *
	 * @Title: selectAdOtherList
	 * @Description: 获取基于广告中其它信息(不包括折线图数据)
	 * @param dayPeriod
	 * @param orderId
	 * @param companyId
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	List<DisplayCountDTO> selectAdOtherList(
			Integer dayPeriod,
			String orderId,
			long companyId,
			Integer userId
	);
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
	List<DisplayCountDTO> selectAdGraphCount(
			Integer dayPeriod,
			String orderId,
			long companyId,
			Integer userId);

	/**
	 *
	 * @Title: selectCompanyNameList
	 * @Description: 获取管理员端资源统计中公司名称列表
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	List<DisplayCountDTO> selectCompanyNameList(
			Integer userId
	);

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
	Page<DisplayCountDTO> selectAdminDisplayCountListPage(
			Page<DisplayCountDTO> page,
			Integer userId
	);

	/**
	 *
	 * @Title: selectVideoForPlatCountPage
	 * @Description: 获取视频平台列表   分页
	 * @param page
	 * @param adDisplayCount
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	Page<DisplayCountDTO> selectVideoForPlatCountPage(
			Page<DisplayCountDTO> page,
			AdDisplayCount adDisplayCount
	);

	/**
	 *
	 * @Title: selectProgramTypeCountPage
	 * @Description: 获取基于节目类型  分页
	 * @param page
	 * @param adDisplayCount
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	Page<DisplayCountDTO> selectProgramTypeCountPage(
			Page<DisplayCountDTO> page,
			AdDisplayCount adDisplayCount
	);

	/**
	 *
	 * @Title: selectVideoAndProgramOrderNameList
	 * @Description: 获取基于节目类型/视频平台 订单名称列表
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月18日List
	 */
	List<DisplayCountDTO> selectVideoAndProgramOrderNameList(
			long companyId
	);

	/**
	 *
	 * @Title: selectVideoForPlatAdCount
	 * @Description: 获取基于视频平台广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	List<DisplayCountResultDTO> selectVideoForPlatAdCount(
			long companyId
	);

	/**
	 *
	 * @Title: selectProgramTypeAdCount
	 * @Description: 获取基于节目类型广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	List<DisplayCountResultDTO> selectProgramTypeAdCount(
			long companyId
	);























	/**
	 * 
	 * @Title: staOrderCosumeList  
	 * @Description: 获取订单前一天的消费金额 
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> staOrderCosumeList(String beforeDay);

	/**
	 * 
	 * @Title: selectAdAndCountPage  
	 * @Description: 根据公司id查询 广告的曝光量 
	 * @param pageNum
	 * @param pageSize
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	Page<DisplayCountDTO> selectAdAndCountPage(int pageNum, int pageSize,
			String companyId,String adOrderId, String adStyleId, String adId, String beforeDay);

	/**
	 * 
	 * @Title: getUserWorkBook  
	 * @Description: 获取广告页面excel表格
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param days
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	HSSFWorkbook getUserWorkBook(String companyId,String adOrderId, String adStyleId,
			String adId, String days, String beforeDay);

/**
 * 
 * @Title: selectCountPlatPage  
 * @Description:分页获取平台统计数据 
 * @param pageNum
 * @param pageSize
 * @param companyId
 * @param adOrderId
 * @param adStyleId
 * @param adId
 * @param beforeDay
 * @return
 * @author guofei 
 * @date 2017年5月5日
 */
	Page<DisplayCountDTO> selectCountPlatPage(int pageNum, int pageSize,
			String companyId,String adOrderId, String adStyleId, String adId, String beforeDay);
/**
 * 
 * @Title: getPlatWorkBook  
 * @Description:获取平台统计数据导出excel表格 
 * @param companyId
 * @param adOrderId
 * @param adStyleId
 * @param adId
 * @param days
 * @param beforeDay
 * @return
 * @author guofei 
 * @date 2017年5月5日
 */
	HSSFWorkbook getPlatWorkBook(String companyId,String adOrderId, String adStyleId,
			String adId, String days, String beforeDay);

	/**
	 * 
	 * @Title: selectDisplayCount  
	 * @Description: 统计前一天的曝光量和用户量  
	 * @param beforeDay
	 * @param nowDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdDisplayCount> selectDisplayCount(String beforeDay, String nowDay);

	/**
	 *
	 * @Title: selectDisplayClickCount
	 * @Description: 统计前一天的点击次数
	 * @param beforeDay
	 * @param nowDay
	 * @return
	 * @author quminghui
	 * @date 2017年6月26日
	 */
	List<AdDisplayCount> selectDisplayClickCount(String beforeDay, String nowDay);

	/**
	 * 
	 * @Title: selectCountForLine  
	 * @Description: 生成统计折线图 
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectCountForLine(String companyId,String adOrderId,
			String adStyleId, String adId, String beforeDay);

	/**
	 * 
	 * @Title: selectCountForPlat  
	 * @Description: 饼图获取数据 
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectCountForPlat(String companyId,String adOrderId,
			String adStyleId, String adId, String beforeDay);

	/**
	 * @param companyId
	 * @param adId
	 * @param adStyleId
	 * @param tortTimeStart
	 * @param tortTimeEnd
	 * @return List<AdDisplayCount>
	 * @throws
	 * 
	 * @title getAdCountList4Chart
	 * @description TODO 获取广告相关曝光信息（按日期统计）
	 * @author mxy
	 * @date 2017-04-19 16:43
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	List<AdDisplayCount> getAdCountList4Chart(Long companyId, Long adId,
			Long adStyleId, Date tortTimeStart, Date tortTimeEnd);
	/**
	 * 
	 * @Title: selectCountForPlatChart  
	 * @Description: 获取平台统计图  
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectCountForPlatChart(String companyId, String adOrderId, String adStyleId, String adId,
			String beforeDay);
	//FIXME 以下为测试统计订单金额  之后需要删除======================================================================
	
	
	/**
	 * 
	 * @Title: selectOneAdOrder  
	 * @Description:根据订单id获取订单     （统计订单金额  之后删除） 
	 * @param orderId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	AdOrder selectOneAdOrder(String orderId);
	/**
	 * 
	 * @Title: updateAdByOrderId  
	 * @Description:  删除广告位和广告关联表  
	 * @param orderIdList
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean updateAdByOrderId(List<String> orderIdList);
	/**
	 * 
	 * @Title: deleteOrderAd  
	 * @Description: 删除订单广告关联表
	 * @param orderIdList
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean deleteOrderAd(List<String> orderIdList);
	/**
	 * 
	 * @Title: updateBatchAdOrderById  
	 * @Description: 完成订单 将订单 delete设置为1 
	 * @param adOrderList
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean updateBatchAdOrderById(List<AdOrder> adOrderList);
	/**
	 * 
	 * @Title: addAdOrderNotice  
	 * @Description: 添加订单完成通知  
	 * @param selectOneOrder
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	void addAdOrderNotice(AdOrder selectOneOrder);
	/**
	 * 
	 * @Title: selectOrderAdList  
	 * @Description:获取订单广告关联表 
	 * @param orderId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<OrderAd> selectOrderAdList(String orderId);
	/**
	 * 
	 * @Title: updateBatchAd  
	 * @Description:批量重启广告  
	 * @param adIdList
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean updateBatchAd(List<String> adIdList);
	//以上为测试统计订单金额  之后需要删除======================================================================


	// edited by xiaokun on 2017-06-29 22:38 begin
	/**
	 * 基于视频平台获取统计数据
	 */
	List<DisplayCountResultDTO> getAdDisplayCountByPlatform(AdOrder adOrder);

	/**
	 * 基于节目类型获取统计数据
	 */
	List<DisplayCountResultDTO> getAdDisplayCountByProgramType(AdOrder adOrder);
	// edited by xiaokun on 2017-06-29 22:38 end

	// edited by qmh on 2017-06-30 10:25 begin
	/**
	 * 基于广告统计数据
	 */
	Page<DisplayCountResultDTO> getAdDisplayCountByAd(Page<DisplayCountResultDTO> page,AdOrder adOrder);


	// edited by qmh on 2017-06-30 10:25 end
}
