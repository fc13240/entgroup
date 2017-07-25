package com.entgroup.adms.mapper;

import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.model.system.Ad;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
  * 广告表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdMapper extends BaseMapper<Ad> {


    List<Ad> selectAdList(@Param("videoId") Long videoId);
    
//    /**
//     * @Title: selectAudiAdList
//     * @Description: 查询待审核的广告
//     * @param companyId 客户ID
//     * @param adName 广告名称
//     * @param adStatus 广告状体
//     * @param delivStatus 广告是否投放状态
//     * @param userId 用户ID
//     * @param userType 用户类型（是否为管理员）
//     * @param adStyle 广告样式           
//     * @author liuxiaolong
//     * @date 2017/3/21
//     * @return 广告集合
//     * @exception
//     * @modifier
//     * @remark
//     * @version V1.0
//     */
//    List<AdDTO> selectAudiAdList(@Param("companyId") Long companyId,@Param("adName") String adName,@Param("adStatus") Integer adStatus,@Param("delivStatus") Integer delivStatus,@Param("userId") Long userId,@Param("userType") Integer userType,@Param("adStyle") Long adStyle);
//    
    /**
     * @Title: selectAudiAdPage
     * @Description: 分页查询待审核广告
     * @param page 页条件
     * @param companyId 客户ID
     * @param adName 广告名称
     * @param adStatus 广告状体
     * @param delivStatus 广告是否投放状态
     * @param userId 用户ID
     * @param userType 用户类型（是否为管理员）
     * @param adStyle 广告样式           
     * @author liuxiaolong
     * @date 2017/3/21
     * @return 广告集合
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    List<AdDTO> selectAudiAdPage(Pagination page,@Param("companyId") Long companyId,@Param("adName") String adName,@Param("adStatus") Integer adStatus,@Param("delivStatus") Integer delivStatus,@Param("userId") Long userId,@Param("userType") Integer userType,@Param("adStyle") Long adStyle);
    
    /**
     * @Title: updateAdStatus
     * @Description: 更新广告审核状体
     * @param adStatus 广告状体
     * @param adId 广告ID
     * @param nowTime 跟新时间
     * @author liuxiaolong
     * @date 2017/3/21
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    boolean updateAdStatus(@Param("adStatus") int adStatus,@Param("adId") Long adId,@Param("nowTime") String nowTime);
    
    /**
     * @Title: selectDeliveAdPage
     * @Description: 查询待投放的广告
     * @param page 页条件
     * @param companyId 客户ID
     * @param adName 广告名称
     * @param styleId 广告类型ID
     * @param adStatus 广告状态
     * @author liuxiaolong
     * @date 2017/3/22
     * @return 广告集合
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    List<AdDTO> selectDeliveAdPage(Pagination page,@Param("companyId") Long companyId,@Param("adName") String adName,@Param("styleId") Long styleId,@Param("adStatus") Integer adStatus);
    
    /**
     * @Title: updateAdStartTime
     * @Description: 广告投放时更新投放时间
     * @param startTime 投放时间
     * @param adId 广告ID
     * @author liuxiaolong
     * @date 2017/3/22
     * @return
     * @exception
     * @modifier
     * @remark
     * @version V1.0
     */
    boolean updateAdStartTime(@Param("startTime") String startTime,@Param("adId") Long adId);
	/**
	 * 
	 * @method: selectAdListByCompany  
	 * @description:根据公司id获取对应的广告以及广告的样式名  
	 * @param companyId
	 * @return
	 * @author guofei 
	 * @date 2017-4-18
	 */
	List<AdDTO> selectAdListByCompany(String companyId);
	/**
	 * 
	 * @method: selectAdListByCount  
	 * @description: 统计页面获取广告下拉框  
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @return
	 * @author guofei 
	 * @date 2017-4-18
	 */
	List<AdAndStyleByCompanyDTO> selectAdListByCount(@Param("companyId")String companyId,@Param("adOrderId")String adOrderId, @Param("adStyleId")String adStyleId);

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
	List<Ad> getAdCountList(Pagination page,@Param("companyId") Long companyId, @Param("adId") Long adId, @Param("adStyleId") Long
			adStyleId, @Param("tortTimeStart") Date tortTimeStart, @Param("tortTimeEnd") Date tortTimeEnd);
	/**
	 * 
	 * @method: updateBatchAd  
	 * @Description: 订单完成后批量恢复广告（新订单可以关联广告） 
	 * @param adIdList
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	int updateBatchAd(@Param("adIdList")List<String> adIdList);

	/**
	 * @title adList
	 * @description TODO 获取广告列表
	 * @author xiaokun
	 * @date 2017-06-28 22:58
	 * @modifier
	 * @remark
	 * @version V1.0
	 *
	 * @param page
	 * @param ad
	 * @return List<Ad>
	 * @throws
	 */
	List<Ad> adList(Pagination page, Ad ad);
}
