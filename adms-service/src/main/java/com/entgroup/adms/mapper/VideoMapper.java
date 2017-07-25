package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * @param page
     * @param name
     * @param statusSelect
     * @param companyId
     * @param typeId
     *
     * @return List<Video>
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
    List<Video> getMatch4VideoList(
            Pagination page, @Param("name") String name,
            @Param("statusSelect") Integer statusSelect,
            @Param("companyId") Long companyId, @Param("typeId") Long typeId
    );

    /**
     *
     * @param page
     * @param name
     * @param statusSelect
     * @param companyId
     * @param typeId
     * @return List<Video>
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
    List<Video> getAds4VideoList(
            Pagination page, @Param("name") String name,
            @Param("statusSelect") Integer statusSelect,
            @Param("companyId") Long companyId, @Param("typeId") Long typeId
    );

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
    List<Video> getMatchByVideoIds(@Param("videoIds") String videoIds);
}
