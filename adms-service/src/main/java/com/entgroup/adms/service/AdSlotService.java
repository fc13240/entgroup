package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.dto.AdSlotOfAdDTO;
import com.entgroup.adms.dto.AdSlotSearchDTO;
import com.entgroup.adms.dto.VideoAdSlotDTO;
import com.entgroup.adms.model.system.AdSlot;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告位 服务类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
public interface AdSlotService extends IService<AdSlot> {

	/**
	 * 
	 * @param id
	 * @param videoId
	 * @param adId
	 * @param sceneId
	 * @param personId
	 * @param objectId
	 * @param slotLabelId
	 * @return List<AdSlot>
	 * 
	 * @title getLabel4AdSlot
	 * @description TODO 根据获取广告位相关标签
	 * @author mxy
	 * @date 2017-03-27 11:55
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	List<AdSlot> getLabel4AdSlot(Long id, Long videoId, Long adId, Long sceneId, Long personId, Long objectId,
			Long slotLabelId);

	/**
	 * @param page
	 * @param programIds
	 * @param id
	 * @param adId
	 *            输入0搜索空闲广告位
	 * @param orderId
	 *            输入0搜索空闲广告位
	 * @param programLevelId
	 * @param programTypeId
	 * @param programId
	 * @param programName
	 * @param videoName
	 * @param companyId
	 * @param sceneId
	 * @param personId
	 * @param objectId
	 * @param slotLabelId
	 * @return Page<AdSlot>
	 * @throws
	 *
	 * 			@title
	 *             orderSelectedAdSlot
	 * @description TODO 订单投放广告位列表
	 * @author mxy
	 * @date 2017-06-28 16:12
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdSlotDTO> orderSelectedAdSlot(Page<AdSlotDTO> page, String programIds, Long id, Long adId, String orderId,
			Long programLevelId, Long programTypeId, Long programId, String programName, String videoName,
			Long companyId, Long sceneId, Long personId, Long objectId, Long slotLabelId);

	/**
	 * @param page
	 * @param adPrice
	 * @param orderId
	 * @param videoName
	 * @param entryTime
	 * @param used
	 * @return Page<AdSlotDTO>
	 * @throws
	 *
	 * @title selectAdSlotShowInfoList
	 * @description TODO 广告位播放信息列表
	 * @author mxy
	 * @date 2017-06-29 01:05
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdSlotDTO> selectAdSlotShowInfoList(
			Page<AdSlotDTO> page,Double adPrice, String orderId, String videoName, Date entryTime, Boolean used
    );

	/**
	 * @param page
	 * @param adPrice
	 * @param orderId
	 * @param videoName
	 * @param entryTime
	 * @param used
	 * @return Page<AdSlotDTO>
	 * @throws
	 *
	 * @title selectAdSlotShowInfoList4Finished
	 * @description TODO 已完成订单广告位播放信息列表
	 * @author mxy
	 * @date 2017-06-29 01:05
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdSlotDTO> selectAdSlotShowInfoList4Finished(
			Page<AdSlotDTO> page,Double adPrice, String orderId, String videoName, Date entryTime, Boolean used
	);


	// /**
	// * @Title: selectAdSlotAll
	// * @Description: 查询待投放的全部广告位
	// * @author liuxiaolong
	// * @date 2017/3/22
	// * @param programLevelId
	// * 节目等级ID
	// * @param sceneId
	// * 场景ID
	// * @param personId
	// * 明星ID
	// * @param videoCompanyId
	// * 平台公司ID
	// * @param videoTypeId
	// * 视频类型ID
	// * @param videoName
	// * 视频名称
	// * @param labelId
	// * 标签id
	// * @return 广告位列表
	// * @exception @modifier
	// * @remark
	// * @version V1.0
	// */
	// List<AdSlotOfAdDTO> selectAdSlotAll(Long programLevelId, Long sceneId,
	// Long personId, Long videoCompanyId, Long videoTypeId,
	// String videoName, Long labelId);

	/**
	 * @Title: selectAdSlotPage
	 * @Description: 分页查询待投放的全部广告位
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @param programLevelId
	 *            节目等级ID
	 * @param sceneId
	 *            场景ID
	 * @param personId
	 *            明星ID
	 * @param videoCompanyId
	 *            平台公司ID
	 * @param videoTypeId
	 *            视频类型ID
	 * @param videoName
	 *            视频名称
	 * @param labelId
	 *            标签id
	 * @return 广告位列表
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdSlotOfAdDTO> selectAdSlotPage(Integer pageNo, Integer pageSize, Long programLevelId, Long sceneId,
			Long personId, Long videoCompanyId, Long videoTypeId, String videoName, Long labelId);

	/**
	 * @Title: selectAdSlotSingle
	 * @Description: 通过视频ID查找广告位
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param videoId
	 *            视频ID
	 * @return 视频对应广告位列表
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	List<VideoAdSlotDTO> selectAdSlotSingle(Long videoId);

	/**
	 * @Title: selectAdSlotByIds
	 * @Description: 查询选中的广告位集合
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param list
	 *            广告位IDs
	 * @return 广告位集合
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	List<AdSlotOfAdDTO> selectAdSlotByIds(List list);

	/**
	 * @Title: updateAdSlotAdid
	 * @Description: 修给广告位表的广告ID字段，已与广告关联
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param adId
	 *            广告ID
	 * @param list
	 *            广告位ID
	 * @return
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	boolean updateAdSlotAdid(Long adId, List list);

	/**
	 * @Title: selectAdSlotByAdId
	 * @Description: 根据广告ID查询广告位
	 * @author liuxiaolong
	 * @date 2017/4/10
	 * @param adId
	 *            广告id
	 * @return 广告的广告位
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	List<AdSlotOfAdDTO> selectAdSlotByAdId(Long adId);

	/**
	 * @Title: selectOffShelfAdPage
	 * @Description: 分页查询待下架的广告位列表
	 * @author liuxiaolong
	 * @date 2017/3/23
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @param videoName
	 *            视频名称
	 * @param videoCompanyId
	 *            视频提供方ID
	 * @param adId
	 *            广告id
	 * @return 待下架的广告
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<AdSlotOfAdDTO> selectOffShelfAdPage(Integer pageNo, Integer pageSize, String videoName, Long videoCompanyId,
			Long adId);

	/**
	 * @Title: updateAdSlotByChoosAd
	 * @Description: 下架选中的广告
	 * @author liuxiaolong
	 * @date 2017/3/23
	 * @param list
	 *            选中的广告位IDs
	 * @return
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	boolean updateAdSlotByChoosAd(List list);

	// /**
	// * @Title: updateAdSlotAllAdid
	// * @Description: 下架所有广告
	// * @author liuxiaolong
	// * @date 2017/3/23
	// * @param adId
	// * 广告ID
	// * @return
	// * @exception @modifier
	// * @remark
	// * @version V1.0
	// */
	// boolean updateAdSlotAllAdid(Long adId);

	/**
	 * @Title: selectOffShelfAdList
	 * @Description: 通过广告位ID列 查询要导出的 下架广告位信息
	 * @author liuxiaolong
	 * @date 2017/3/24
	 * @param list
	 *            选中的广告位ids
	 * @return 广告位集合
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	List<AdSlotOfAdDTO> selectOffShelfAdList(List list);

	/**
	 * @Title: createExcel
	 * @Description: 通过广告位ID列 查询要导出的 下架广告位信息时创建excel
	 * @author liuxiaolong
	 * @date 2017/3/26
	 * @param adSlotList
	 *            广告位集合
	 * @return
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	public HSSFWorkbook createExcel(List<AdSlotOfAdDTO> adSlotList);

	/**
	 * 
	 * @method: updateAdByOrderId
	 * @description: 根据订单id删除广告位和广告的关联
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	boolean updateAdByOrderId(List<String> orderIdList);

	/**
	 * @Title: selectAdSlotSearch
	 * @Description: 广告投放广告位的查询下拉框
	 * @author liuxiaolong
	 * @date 2017/4/20
	 * @return
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	List<AdSlotSearchDTO> selectAdSlotSearch();

}
