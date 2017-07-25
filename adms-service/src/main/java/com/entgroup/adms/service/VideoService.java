package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Video;

import java.util.List;

/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface VideoService extends IService<Video> {

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
	 * @description TODO 获取视频（列表）识别信息
	 * @author mxy
	 * @date 2017-03-20 20:21
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<Video> getMatch4VideoList(Page<Video> page, String name, Integer statusSelect, Long companyId, Long typeId);
	/**
	 *
	 * @param page
	 * @param name
	 * @param statusSelect
	 * @param companyId
	 * @param typeId
	 * @return Page<Video>
	 *
	 * @title getAds4VideoList
	 * @description TODO 获取视频（列表）广告位信息
	 * @throws ???
	 * @author mxy
	 * @date 2017-03-21 15:06
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	Page<Video> getAds4VideoList(Page<Video> page, String name, Integer statusSelect, Long companyId, Long typeId);

	/**
	 * @param videoIds 用于IN语句，如：1,2,3,4
	 * @return List<Video>
	 * @throws
	 *
	 * @title getMatchByVideoIds
	 * @description TODO 根据VideoIds获取视频（列表）识别信息
	 * @author mxy
	 * @date 2017-04-24 10:36
	 * @modifier
	 * @remark
	 * @version V1.0
	 */
	List<Video> getMatchByVideoIds(String videoIds);

}
