package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.dto.DisplayCountDTO;
import com.entgroup.adms.dto.DisplayCountResultDTO;
import com.entgroup.adms.model.system.AdDisplayCount;
import com.entgroup.adms.model.system.AdOrder;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 日曝光统计 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdDisplayCountMapper extends BaseMapper<AdDisplayCount> {

	/**
	 *
	 * @Title: selectHomeDisplayCountPage
	 * @Description: 统计前一天的订单列表信息 分页
	 * @param beforeDay
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月8日
	 */
	List<DisplayCountDTO> selectHomeDisplayCountPage(
			@Param("beforeDay") String beforeDay,
			@Param("companyId") long companyId);

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
	 * @Description: 获取首页折线图数据
	 * @param orderId
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月12日
	 */
	List<DisplayCountDTO> selectGraphCount(
			@Param("orderId") String orderId,
			@Param("companyId") long companyId);

	/**
	 *
	 * @Title: selectOrderNameList
	 * @Description: 获取首页订单名称下拉列表
	 * @param companyId
	 * @param userId
	 * @return
	 * @author qmh
	 * @date 2017年6月12日
	 */
	List<DisplayCountDTO> selectOrderNameList(
			@Param("companyId") long companyId,
			@Param("userId") Integer userId);

	/**
	 *
	 * @Title: selectGraphCount
	 * @Description: 获取首页中其它信息(不包括折线图数据)
	 * @param beforeDay
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月11日
	 */
	List<DisplayCountDTO> selectHomeOtherList(
			@Param("beforeDay") String beforeDay,
			@Param("companyId") long companyId);

	/**
	 *
	 * @Title: selectAdDisplayCountPage
	 * @Description: 获取基于广告中订单列表 分页
	 * @param page
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月14日
	 */
	List<DisplayCountDTO> selectAdDisplayCountPage(
			Pagination page,
			@Param("companyId") long companyId);



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
	 * @date 2017年6月11日
	 */
	List<DisplayCountDTO> selectAdOtherList(
			@Param("dayPeriod") Integer dayPeriod,
			@Param("orderId") String orderId,
			@Param("companyId") long companyId,
			@Param("userId") Integer userId
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
			@Param("dayPeriod") Integer dayPeriod,
			@Param("orderId") String orderId,
			@Param("companyId") long companyId,
			@Param("userId") Integer userId
	);

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
			@Param("userId") Integer userId
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
	List<DisplayCountDTO> selectAdminDisplayCountListPage(
			Pagination page,
			@Param("userId") Integer userId
	);

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
	List<DisplayCountDTO> selectVideoForPlatCountPage(
			Pagination page,
			AdDisplayCount adDisplayCount);

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
	List<DisplayCountDTO> selectProgramTypeCountPage(
			Pagination page,
			AdDisplayCount adDisplayCount);

	/**
	 *
	 * @Title: selectVideoAndProgramOrderNameList
	 * @Description: 基于节目类型/视频平台 订单名称列表
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月18日
	 */
	List<DisplayCountDTO> selectVideoAndProgramOrderNameList(
			@Param("companyId") long companyId);

	/**
	 *
	 * @Title: selectVideoForPlatAdCount
	 * @Description: 基于视频平台广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	List<DisplayCountResultDTO> selectVideoForPlatAdCount(
			@Param("companyId") long companyId);

	/**
	 *
	 * @Title: selectProgramTypeAdCount
	 * @Description: 基于节目类型广告位总数
	 * @param companyId
	 * @return
	 * @author qmh
	 * @date 2017年6月29日
	 */
	List<DisplayCountResultDTO> selectProgramTypeAdCount(
			@Param("companyId") long companyId);

/**
 * 
 * @Title: staOrderCosumeList  
 * @Description:  获取前一天的消费金额列表  
 * @param beforeDay
 * @return
 * @author guofei 
 * @date 2017年5月5日
 */
	List<DisplayCountDTO> staOrderCosumeList(String beforeDay);

	/**
	 * 
	 * @Title: selectAdAndCount  
	 * @Description: 获取广告对应的曝光量 不分页
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectAdAndCount(
			@Param("companyId") String companyId,
			@Param("adOrderId") String adOrderId,
			@Param("adStyleId") String adStyleId, @Param("adId") String adId,
			@Param("beforeDay") String beforeDay);

	/**
	 * 
	 * @Title: selectAdAndCountPage  
	 * @Description: 获取广告对应的曝光量 分页 
	 * @param pagination
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectAdAndCountPage(Pagination pagination,
			@Param("companyId") String companyId,
			@Param("adOrderId") String adOrderId,
			@Param("adStyleId") String adStyleId, @Param("adId") String adId,
			@Param("beforeDay") String beforeDay);

	/**
	 * 
	 * @Title: selectCountPlatPage  
	 * @Description: 查询公司在平台的统计数量 分页
	 * @param pagination
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectCountPlatPage(Pagination pagination,
			@Param("companyId") String companyId,
			@Param("adOrderId") String adOrderId,
			@Param("adStyleId") String adStyleId, @Param("adId") String adId,
			@Param("beforeDay") String beforeDay);

/**
 * 
 * @Title: selectCountForPlat  
 * @Description: 查询公司在平台的统计数量 导出excel
 * @param companyId
 * @param adOrderId
 * @param adStyleId
 * @param adId
 * @param beforeDay
 * @return
 * @author guofei 
 * @date 2017年5月5日
 */
	List<DisplayCountDTO> selectCountForPlat(@Param("companyId") String companyId,@Param("adOrderId")String adOrderId,
			@Param("adStyleId") String adStyleId, @Param("adId") String adId,
			@Param("beforeDay") String beforeDay);

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
	List<AdDisplayCount> selectDisplayCount(
			@Param("beforeDay") String beforeDay, @Param("nowDay") String nowDay);

	/**
	 *
	 * @Title: selectDisplayClickCount
	 * @Description: 统计前一天的点击量
	 * @param beforeDay
	 * @param nowDay
	 * @return
	 * @author quminghui
	 * @date 2017年6月26日
	 */
	List<AdDisplayCount> selectDisplayClickCount(
			@Param("beforeDay") String beforeDay, @Param("nowDay") String nowDay);

	/**
	 * 
	 * @Title: selectCountForLine  
	 * @Description:生成统计折线图 
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @param adId
	 * @param beforeDay
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<DisplayCountDTO> selectCountForLine(
			@Param("companyId") String companyId,
			@Param("adOrderId") String adOrderId,
			@Param("adStyleId") String adStyleId, @Param("adId") String adId,
			@Param("beforeDay") String beforeDay);

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
	List<AdDisplayCount> getAdCountList4Chart(@Param("companyId") Long companyId, @Param("adId") Long adId, @Param("adStyleId") Long
			adStyleId, @Param("tortTimeStart") Date tortTimeStart, @Param("tortTimeEnd") Date tortTimeEnd);
	/**
	 * 
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
	List<DisplayCountDTO> selectCountForPlatChart(@Param("companyId")String companyId, @Param("adOrderId")String adOrderId,@Param("adStyleId") String adStyleId, @Param("adId")String adId,
			@Param("beforeDay")String beforeDay);


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
	List<DisplayCountResultDTO> getAdDisplayCountByAd(Pagination page,AdOrder adOrder);




//	List<DisplayCountResultDTO> getAdDisplayCountByProgramType(AdOrder adOrder);
	// edited by xiaokun on 2017-06-29 22:38 end

	// edited by xiaokun on 2017-07-02 20:07 begin
	/**
	 * 1、查询所有slot_ids
	 */
	DisplayCountResultDTO getAllSlotIds(AdOrder adOrder);
	/**
	 * 2、查询对应广告位数量
	 */
	List<DisplayCountResultDTO> getSlotIdsGroup(DisplayCountResultDTO displayCountResultDTO);
	/**
	 * 3、点击量、曝光量、消费
	 */
	List<DisplayCountResultDTO> getDisplayCountGroup(DisplayCountResultDTO displayCountResultDTO);
	// edited by xiaokun on 2017-07-02 20:07 end
}
