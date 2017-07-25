package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.dto.AdSlotOfAdDTO;
import com.entgroup.adms.dto.AdSlotSearchDTO;
import com.entgroup.adms.dto.VideoAdSlotDTO;
import com.entgroup.adms.mapper.AdSlotMapper;
import com.entgroup.adms.model.system.AdSlot;
import com.entgroup.adms.service.AdSlotService;
import com.entgroup.adms.util.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告位 服务实现类
 * </p>
 * 
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AdSlotServiceImpl extends ServiceImpl<AdSlotMapper, AdSlot> implements AdSlotService {

	/**
	 * @param id
	 * @param videoId
	 * @param adId
	 * @param sceneId
	 * @param personId
	 * @param objectId
	 * @param slotLabelId
	 * 
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
	@Override
	public List<AdSlot> getLabel4AdSlot(Long id, Long videoId, Long adId, Long sceneId, Long personId, Long objectId,
			Long slotLabelId) {
		return baseMapper.getLabel4AdSlot(id, videoId, adId, sceneId, personId, objectId, slotLabelId);
	}

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
	 *
	 * @return Page<AdSlot>
	 *
	 * @throws @title
	 *             orderSelectedAdSlot
	 * @description TODO 订单投放广告位列表
	 * @author mxy
	 * @date 2017-06-28 16:12
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<AdSlotDTO> orderSelectedAdSlot(Page<AdSlotDTO> page, String programIds, Long id, Long adId,
			String orderId, Long programLevelId, Long programTypeId, Long programId, String programName,
			String videoName, Long companyId, Long sceneId, Long personId, Long objectId, Long slotLabelId) {
		page.setRecords(baseMapper.orderSelectedAdSlot(page, programIds, id, adId, orderId, programLevelId,
				programTypeId, programId, programName, videoName, companyId, sceneId, personId, objectId, slotLabelId));
		return page;
	}

	/**
	 * @param page
	 * @param adPrice
	 * @param orderId
	 * @param videoName
	 * @param entryTime
	 * @param used
	 *
	 * @return Page<AdSlotDTO>
	 *
	 * @throws
	 * @title selectAdSlotShowInfoList
	 * @description TODO 广告位播放信息列表
	 * @author mxy
	 * @date 2017-06-29 01:05
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<AdSlotDTO> selectAdSlotShowInfoList(
			Page<AdSlotDTO> page, Double adPrice, String orderId, String videoName, Date entryTime, Boolean used
	) {
		//获取前一天时间
		Calendar now = Calendar.getInstance();
		now.setTime(entryTime);
		now.set(5, now.get(5) - 1);

		page.setRecords(baseMapper.selectAdSlotShowInfoList(page,adPrice, orderId,videoName,now.getTime(),entryTime,used)
						);
		return page;
	}

	/**
	 * @param page
	 * @param adPrice
	 * @param orderId
	 * @param videoName
	 * @param entryTime
	 * @param used
	 *
	 * @return Page<AdSlotDTO>
	 *
	 * @throws
	 * @title selectAdSlotShowInfoList4Finished
	 * @description TODO 已完成订单广告位播放信息列表
	 * @author mxy
	 * @date 2017-06-29 01:05
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<AdSlotDTO> selectAdSlotShowInfoList4Finished(
			Page<AdSlotDTO> page, Double adPrice, String orderId, String videoName, Date entryTime, Boolean used
	) {
		//获取前一天时间
		Calendar now = Calendar.getInstance();
		now.setTime(entryTime);
		now.set(5, now.get(5) - 1);

		page.setRecords(baseMapper.selectAdSlotShowInfoList4Finished(page,adPrice, orderId,videoName,now.getTime(),entryTime,used)
		);
		return page;
	}

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
	// * @exception
	// * @modifier
	// * @remark
	// * @version V1.0
	// */
	// @Override
	// public List<AdSlotOfAdDTO> selectAdSlotAll(Long programLevelId,
	// Long sceneId, Long personId, Long videoCompanyId, Long videoTypeId,
	// String videoName, Long labelId) {
	// if (StringUtils.isNull(programLevelId + "")) {
	// programLevelId = 3L;
	// }
	//
	// List<AdSlotOfAdDTO> adSlot = baseMapper.selectAdSlotAll(programLevelId,
	// sceneId, personId, videoCompanyId, videoTypeId, videoName,
	// labelId);
	// return adSlot;
	// }

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
	@Override
	public Page<AdSlotOfAdDTO> selectAdSlotPage(Integer pageNo, Integer pageSize, Long programLevelId, Long sceneId,
			Long personId, Long videoCompanyId, Long videoTypeId, String videoName, Long labelId) {
		if (StringUtils.isNull(programLevelId + "")) {
			programLevelId = 3L;
		}

		Page<AdSlotOfAdDTO> page = new Page<AdSlotOfAdDTO>(pageNo, pageSize);
		List<AdSlotOfAdDTO> adSlot = baseMapper.selectAdSlotPage(page, programLevelId, sceneId, personId,
				videoCompanyId, videoTypeId, videoName, labelId);
		page.setRecords(adSlot);
		return page;
	}

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
	@Override
	public List<VideoAdSlotDTO> selectAdSlotSingle(Long videoId) {
		return baseMapper.selectAdSlotSingle(videoId);
	}

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
	@Override
	public List<AdSlotOfAdDTO> selectAdSlotByIds(List list) {
		return baseMapper.selectAdSlotByIds(list);
	}

	/**
	 * @Title: updateAdSlotAdid
	 * @Description: 修给广告位表的广告ID字段，已与广告关联
	 * @author liuxiaolong
	 * @date 2017/3/22
	 * @param adId
	 *            广告ID
	 * @param list
	 *            广告位IDS
	 * @return
	 * @exception @modifier
	 * @remark
	 * @version V1.0
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean updateAdSlotAdid(Long adId, List list) {
		return baseMapper.updateAdSlotAdid(adId, list);
	}

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
	@Override
	public List<AdSlotOfAdDTO> selectAdSlotByAdId(Long adId) {
		return baseMapper.selectAdSlotByAdId(adId);
	}

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
	@Override
	public Page<AdSlotOfAdDTO> selectOffShelfAdPage(Integer pageNo, Integer pageSize, String videoName,
			Long videoCompanyId, Long adId) {
		Page<AdSlotOfAdDTO> page = new Page<AdSlotOfAdDTO>(pageNo, pageSize);
		page.setRecords(baseMapper.selectOffShelfAdPage(page, videoName, videoCompanyId, adId));
		return page;
	}

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
	@Override
	public boolean updateAdSlotByChoosAd(List list) {
		return baseMapper.updateAdSlotByChoosAd(list);
	}

	// /**
	// * @Title: updateAdSlotAllAdid
	// * @Description: 下架所有广告
	// * @author liuxiaolong
	// * @date 2017/3/23
	// * @param adId
	// * 广告ID
	// * @return
	// * @exception
	// * @modifier
	// * @remark
	// * @version V1.0
	// */
	// @Override
	// public boolean updateAdSlotAllAdid(Long adId) {
	// return baseMapper.updateAdSlotAllAdid(adId);
	// }

	/**
	 * @Title: selectOffShelfAdPage
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
	public List<AdSlotOfAdDTO> selectOffShelfAdList(List list) {
		return baseMapper.selectOffShelfAdList(list);
	}

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
	@Override
	public HSSFWorkbook createExcel(List<AdSlotOfAdDTO> adSlotList) {
		// 创建一个webbook，对应一个excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应excel文件中的sheet
		HSSFSheet sheet = workbook.createSheet("广告对应的广告位表");
		// 设置列宽
		sheet.setColumnWidth(0, 25 * 100);
		sheet.setColumnWidth(1, 60 * 100);
		sheet.setColumnWidth(2, 100 * 100);
		sheet.setColumnWidth(3, 80 * 100);
		sheet.setColumnWidth(4, 40 * 100);
		// 在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置表头，设置表头居中
		HSSFCellStyle style = workbook.createCellStyle();
		// 创建一个居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 带边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		// 字体增粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 字体大小
		font.setFontHeightInPoints((short) 12);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 单独设置整列居中或居左
		HSSFCellStyle style1 = workbook.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFDataFormat format = workbook.createDataFormat();
		style2.setDataFormat(format.getFormat("yyyy/mm/dd"));

		HSSFCellStyle style3 = workbook.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style3.setDataFormat(format.getFormat("HH:mm:ss"));

		HSSFCell cell = null;
		// 表头
		String[] title = {"序号", "上架日期", "视频名称", "视频平台", "位置"};
		for (int x = 0; x < title.length; x++) {
			cell = row.createCell(x);
			cell.setCellValue(title[x]);
			cell.setCellStyle(style);
		}
		// 表体内容
		for (int i = 0; i < adSlotList.size(); i++) {
			// 创建行
			row = sheet.createRow(i + 1);
			AdSlotOfAdDTO adSlot = adSlotList.get(i);
			// 创建单元格，并设置值
			// 编号列居左
			HSSFCell c0 = row.createCell(0);
			c0.setCellStyle(style1);
			c0.setCellValue(i);

			HSSFCell c1 = row.createCell(1);
			c1.setCellStyle(style2);
			if (org.apache.commons.lang3.StringUtils.isNotBlank(adSlot.getStartTime() + "")) {
				Date date = adSlot.getStartTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String startTime = formatter.format(date);
				c1.setCellValue(startTime);// 开始日期
			} else {
				c1.setCellValue("");
			}

			HSSFCell c2 = row.createCell(2);
			c2.setCellStyle(style1);
			if (!StringUtils.isNull(adSlot.getVideoName())) {
				c2.setCellValue(adSlot.getVideoName()); // 视频名称
			} else {
				c2.setCellValue("");
			}

			HSSFCell c3 = row.createCell(3);
			c3.setCellStyle(style1);
			if (!StringUtils.isNull(adSlot.getVideoPlatform())) {
				c3.setCellValue(adSlot.getVideoPlatform()); // 视频平台
			} else {
				c3.setCellValue(""); // 视频平台
			}

			HSSFCell c4 = row.createCell(4);
			c4.setCellStyle(style3);
			if (!StringUtils.isNull(adSlot.getVideoPosition())) {
				c4.setCellValue(adSlot.getVideoPosition()); // 广告位置
			} else {
				c4.setCellValue(""); // 广告位置
			}

		}
		return workbook;
	}

	/**
	 * 
	 * @method: updateAdByOrderId
	 * @Description: 批量删除广告位表
	 * @param orderIdList
	 * @return
	 * @author guofei
	 * @date 2017-4-21
	 */
	@Override
	public boolean updateAdByOrderId(List<String> orderIdList) {
		int rows = baseMapper.updateAdByOrderId(orderIdList);
		if (rows > 0) {
			return true;
		}
		return false;
	}

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
	@Override
	public List<AdSlotSearchDTO> selectAdSlotSearch() {
		return baseMapper.selectAdSlotSearch();
	}

}
