package com.entgroup.adms.service.impl;

import com.entgroup.adms.dto.AdAndStyleByCompanyDTO;
import com.entgroup.adms.dto.AdDTO;
import com.entgroup.adms.model.system.Ad;
import com.entgroup.adms.mapper.AdMapper;
import com.entgroup.adms.mapper.AdReasonTemplateMapper;
import com.entgroup.adms.service.AdService;
import com.entgroup.adms.util.DateUtils;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
@Transactional
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements
		AdService {

	@Resource
	AdReasonTemplateMapper adReasonTemplateMapper;

	@Override
	public List<Ad> selectAdList(Long videoId) {
		return baseMapper.selectAdList(videoId);
	}

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
//	 * @exception @modifier
//	 * @remark
//	 * @version V1.0
//	 */
//	@Override
//	public List<AdDTO> selectAudiAdList(Long companyId, String adName,
//			Integer delivStatus, Long userId, Integer userType,Long adStyle) {
//		return baseMapper.selectAudiAdList(companyId, adName, 1, delivStatus,
//				userId, userType,adStyle);
//	}


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
	 * @param adStyle 广告样式                   
	 * @return 待审核的广告列表
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<AdDTO> selectAudiAdPage(Integer pageNo, Integer pageSize,
			Long companyId, String adName, Integer adStatus,
			Integer delivStatus, Long userId, Integer userType,Long adStyle) {
		Page<AdDTO> page = new Page<AdDTO>(pageNo, pageSize);
		page.setRecords(baseMapper.selectAudiAdPage(page, companyId, adName,
				adStatus, delivStatus, userId, userType,adStyle));
		return page;
	}

	/**
	 * @Title: selectAdById
	 * @Description: 按广告id查询广告信息
	 * @author liuxiaolong
	 * @date 2017/3/21
	 * @param adId
	 *            广告ID
	 * @return 广告
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Ad selectAdById(Long adId) {
		Ad ad = baseMapper.selectById(adId);
		return ad;
	}

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
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public boolean updateAdStatus(Integer adStatus, Long adId) {
		String nowTime = DateUtils.getDateTime();
		return baseMapper.updateAdStatus(adStatus, adId, nowTime);
	}

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
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<AdDTO> selectDeliveAdPage(Integer pageNo, Integer pageSize,
			Long companyId, String adName, Long styleId) {
		Page<AdDTO> page = new Page<AdDTO>(pageNo, pageSize);
		page.setRecords(baseMapper.selectDeliveAdPage(page, companyId, adName,
				styleId, 2));
		return page;
	}

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
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public boolean updateAdStartTime(String startTime, Long adId) {
		return baseMapper.updateAdStartTime(startTime, adId);
	}

	/**
	 * 
	 * @method: selectAdListForComIdAndSytle
	 * @Description: 根据公司id获取ad下拉框
	 * @param companyId
	 * @param adOrderId
	 * @param adStyleId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<AdAndStyleByCompanyDTO> selectAdListForComIdAndSytle(
			String companyId,String adOrderId, String adStyleId) {
		return baseMapper.selectAdListByCount(companyId,adOrderId, adStyleId);
	}

	/**
	 * \
	 * 
	 * @method: selectAdListByCompany
	 * @Description: 根据公司id获取对应的广告以及广告的样式名
	 * @param companyId
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public List<AdDTO> selectAdListByCompany(String companyId) {

		return baseMapper.selectAdListByCompany(companyId);
	}

	/**
	 * @param page
	 * @param companyId
	 * @param adId
	 * @param adStyleId
	 * @param tortTimeStart
	 * @param tortTimeEnd
	 * 
	 * @return Page<Ad>
	 * 
	 * @throws @title getAdCountList
	 * @description TODO 获取广告相关曝光信息（按广告统计）
	 * @author mxy
	 * @date 2017-04-19 15:14
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Ad> getAdCountList(Page<Ad> page, Long companyId, Long adId,
			Long adStyleId, Date tortTimeStart, Date tortTimeEnd) {
		page.setRecords(baseMapper.getAdCountList(page, companyId, adId,
				adStyleId, tortTimeStart, tortTimeEnd));
		return page;
	}
	/**
	 * 
	 * @method: updateBatchAd  
	 * @Description: 订单完成后批量恢复广告（新订单可以关联广告） 
	 * @param adIdList
	 * @return
	 * @author guofei 
	 * @date 2017年4月26日
	 */
	@Override
	public boolean updateBatchAd(List<String> adIdList) {
		int row=baseMapper.updateBatchAd(adIdList);
		if(row!=0){
			return true;
		}
		return false;
	}

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
	public Page<Ad> adList(Page<Ad> page, Ad ad) {
		List<Ad> adList = baseMapper.adList(page, ad);
		page.setRecords(adList);
		return page;
	}
}
