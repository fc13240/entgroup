package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.model.system.Ad;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告表 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface AdService extends IService<Ad> {

	/**
	 * @Title: selectAdList
	 * @Description: 获取广告列表
	 * @author sunzhen
	 * @date 2017/3/15
	 * @param @param videoId
	 * @return List<Ad>
	 * @throws
	 */
	List<Ad> selectAdList(Long videoId);

//	/**
//	 * @Title: selectAudiAdList
//	 * @Description: 查询待审核的全部广告
//	 * @author liuxiaolong
//	 * @date 2017/3/21
//	 * @param companyId
//	 *            客户ID
//	 * @param adName
//	 *            广告名称
//	 * @param delivStatus
//	 *            投放状态
//	 * @param userId
//	 *            用户ID
//	 * @param userType
//	 *            用户类型（是否为管理员）
//	 * @param adStyle 广告样式           
//	 * @return 待审核的广告列表
//	 * @exception
//	 * @modifier
//	 * @remark
//	 * @version V1.0
//	 */
//	List<AdDTO> selectAudiAdList(Long companyId, String adName,
//			Integer delivStatus, Long userId, Integer userType,Long adStyle);


	/**
	 * @Title: selectAudiAdPage
	 * @Description: 分页查询待审核的广告
	 * @author liuxiaolong
	 * @date 2017/3/21
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @param companyId
	 *            客户ID
	 * @param adName
	 *            广告名称
	 * @param adStatus
	 *            广告状态
	 * @param delivStatus
	 *            投放状态
	 * @param userId
	 *            用户ID
	 * @param userType
	 *            用户类型（是否为管理员）
	 * @param adStyle 广告样式
	 * @return 待审核的广告列表
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdDTO> selectAudiAdPage(Integer pageNo, Integer pageSize,
			Long companyId, String adName, Integer adStatus,
			Integer delivStatus, Long userId, Integer userType,Long adStyle);

	/**
	 * @Title: selectAdById
	 * @Description: 按广告id查询广告信息
	 * @author liuxiaolong
	 * @date 2017/3/21
	 * @param adId
	 *            广告ID
	 * @return 广告
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Ad selectAdById(Long adId);

	/**
	 * @Title: updateAdStatus
	 * @Description: 修改广告状体
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param adStatus
	 *            广告状体
	 * @param adId
	 *            广告ID
	 * @return boolean值
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	boolean updateAdStatus(Integer adStatus, Long adId);

	/**
	 * @Title: selectDeliveAdPage
	 * @Description: 分页查询待投放广告列表
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @param companyId
	 *            客户ID
	 * @param adName
	 *            广告名称
	 * @param styleId
	 *            广告类型ID
	 * @return 待投放的广告
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdDTO> selectDeliveAdPage(Integer pageNo, Integer pageSize,
			Long companyId, String adName, Long styleId);

	/**
	 * @Title: updateAdStartTime
	 * @Description: 根据广告ID修改广告投放日期
	 * @author liuxiaolong
	 * @date 2017/3/23
	 * @param startTime
	 *            广告投放时间
	 * @param adId
	 *            广告ID
	 * @return
	 * @exception
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	boolean updateAdStartTime(String startTime, Long adId);

	/**
	 * 
	 * @Title: selectAdListForComIdAndSytle  
	 * @Description: 根据公司id和styleid获取ad下拉框 （只有id和name）  
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdAndStyleByCompanyDTO> selectAdListForComIdAndSytle(String companyId,String adOrderId,
			String adStyleId);

	/**
	 * 
	 * @Title: selectAdListByCompany  
	 * @Description: 根据公司id获取对应的广告以及广告的样式名
	 * @param companyId
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	List<AdDTO> selectAdListByCompany(String companyId);

	/**
	 * @param page
	 * @param companyId
	 * @param adId
	 * @param adStyleId
	 * @param tortTimeStart
	 * @param tortTimeEnd
	 * @return List<Ad>
	 * @throws
	 * 
	 * @title getAdCountList
	 * @description TODO 获取广告相关曝光信息（按广告统计）
	 * @author mxy
	 * @date 2017-04-19 15:14
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<Ad> getAdCountList(Page<Ad> page, Long companyId, Long adId,
			Long adStyleId, Date tortTimeStart, Date tortTimeEnd);
	/**
	 * 
	 * @Title: updateBatchAd  
	 * @Description: 订单完成后恢复广告（新订单可以关联广告） 
	 * @param adIdList
	 * @return
	 * @author guofei 
	 * @date 2017年5月5日
	 */
	boolean updateBatchAd(List<String> adIdList);

	/**
	 * @title adList
	 * @description TODO 获取广告列表
	 * @author xiaokun
	 * @date 2017-06-28 17:37
	 * @modifier
	 * @remark
	 * @version V1.0
	 *
	 * @param page
	 * @param ad
	 * @return Page<Ad>
	 * @throws
	 */
	public Page<Ad> adList(Page<Ad> page, Ad ad);
}
