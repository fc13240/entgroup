package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.VideoMapper;
import com.entgroup.adms.model.system.Video;
import com.entgroup.adms.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

	/**
	 * @param page
	 * @param name
	 * @param statusSelect
	 * @param companyId
	 * @param typeId
	 *
	 * @return Page<Video>
	 *
	 * @throws ???
	 * @title getMatchVideoList
	 * @description TODO 获取视频识别信息
	 * @author mxy
	 * @date 2017-03-20 20:21
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Video> getMatch4VideoList(Page<Video> page, String name, Integer statusSelect, Long companyId,
			Long typeId) {
		page.setRecords(baseMapper.getMatch4VideoList(page, name, statusSelect, companyId, typeId));
		return page;
	}

	/**
	 * @param page
	 * @param name
	 * @param statusSelect
	 * @param companyId
	 * @param typeId
	 *
	 * @return Page<Video>
	 *
	 * @throws ???
	 * @title getAds4VideoList
	 * @description TODO 获取视频（列表）广告位信息
	 * @author mxy
	 * @date 2017-03-21 15:06
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public Page<Video> getAds4VideoList(
			Page<Video> page, String name, Integer statusSelect, Long companyId, Long typeId
	) {
		page.setRecords(baseMapper.getAds4VideoList(page, name, statusSelect, companyId, typeId));
		return page;
	}

	/**
	 * @param videoIds
	 *         用于IN语句，如：1,2,3,4
	 *
	 * @return List<Video>
	 *
	 * @throws
	 * @title getMatchByVideoIds
	 * @description TODO 根据VideoIds获取视频（列表）识别信息
	 * @author mxy
	 * @date 2017-04-24 10:36
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	@Override
	public List<Video> getMatchByVideoIds(String videoIds) {
		return baseMapper.getMatchByVideoIds(videoIds);
	}
}
