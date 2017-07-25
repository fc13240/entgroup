package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.dto.AdSlotDTO;
import com.entgroup.adms.dto.AdSlotOfAdDTO;
import com.entgroup.adms.dto.AdSlotSearchDTO;
import com.entgroup.adms.dto.VideoAdSlotDTO;
import com.entgroup.adms.model.system.AdSlot;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 广告位 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface AdSlotMapper extends BaseMapper<AdSlot> {

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
    List<AdSlot> getLabel4AdSlot(
            @Param("id") Long id, @Param("videoId") Long videoId, @Param("adId") Long adId,
            @Param("sceneId") Long sceneId, @Param("personId") Long personId, @Param("objectId") Long objectId,
            @Param("slotLabelId") Long slotLabelId
    );

    /**
     * @param page
     * @param programIds
     * @param id
     * @param adId
     *         输入0搜索空闲广告位
     * @param orderId
     *         输入0搜索空闲广告位
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
     * @return List<AdSlotDTO>
     *
     * @throws
     * @title orderSelectedAdSlot
     * @description TODO    订单投放广告位列表
     * @author mxy
     * @date 2017-06-28 16:12
     * @modifier
     * @remark
     * @version V1.0
     */
    List<AdSlotDTO> orderSelectedAdSlot(
            Pagination page, @Param("programIds") String programIds, @Param("id") Long id, @Param("adId") Long adId,
            @Param("orderId") String orderId, @Param("programLevelId") Long programLevelId,
            @Param("programTypeId") Long programTypeId, @Param("programId") Long programId,
            @Param("programName") String programName, @Param("videoName") String videoName,
            @Param("companyId") Long companyId, @Param("sceneId") Long sceneId, @Param("personId") Long personId,
            @Param("objectId") Long objectId, @Param("slotLabelId") Long slotLabelId
    );

    /**
     * @param page
     * @param orderId
     * @param videoName
     * @param entryTimeStart
     * @param entryTimeEnd
     * @param used
     * @return List<AdSlotDTO>
     * @throws
     *
     * @title selectAdSlotShowInfoList
     * @description TODO 广告位播放信息列表
     * @author mxy
     * @date 2017-06-29 01:03
     * @modifier
     * @remark
     * @version V1.0
     */
    List<AdSlotDTO> selectAdSlotShowInfoList(
            Pagination page,@Param("adPrice") Double adPrice,
            @Param("orderId") String orderId, @Param("videoName") String videoName,
            @Param("entryTimeStart") Date entryTimeStart, @Param("entryTimeEnd") Date entryTimeEnd,
            @Param("used") Boolean used
    );

    /**
     * @param page
     * @param orderId
     * @param videoName
     * @param entryTimeStart
     * @param entryTimeEnd
     * @param used
     * @return List<AdSlotDTO>
     * @throws
     *
     * @title selectAdSlotShowInfoList4Finished
     * @description TODO 已完成订单广告位播放信息列表
     * @author mxy
     * @date 2017-06-29 01:03
     * @modifier
     * @remark
     * @version V1.0
     */
    List<AdSlotDTO> selectAdSlotShowInfoList4Finished(
            Pagination page,@Param("adPrice") Double adPrice,
            @Param("orderId") String orderId, @Param("videoName") String videoName,
            @Param("entryTimeStart") Date entryTimeStart, @Param("entryTimeEnd") Date entryTimeEnd,
            @Param("used") Boolean used
    );

    //	/**
    //	 * @Title: selectAdSlotAll
    //	 * @Description: 查询所有广告位
    //	 * @author liuxiaolong
    //	 * @param programLevelId 节目等级ID
    //     * @param sceneId 场景ID
    //     * @param personId 明星ID
    //     * @param videoCompanyId 平台公司ID
    //     * @param videoTypeId 视频类型ID
    //     * @param videoName 视频名称
    //     * @param labelId 标签id
    //	 * @return 广告位集合
    //	 * @exception @modifier
    //	 * @remark
    //	 * @version V1.0
    //	 */
    //	List<AdSlotOfAdDTO> selectAdSlotAll(@Param("programLevelId") Long programLevelId,
    //										@Param("sceneId") Long sceneId,
    //										@Param("personId") Long personId,
    //										@Param("videoCompanyId") Long videoCompanyId,
    //										@Param("videoTypeId") Long videoTypeId,
    //										@Param("videoName") String videoName,
    //										@Param("labelId") Long labelId);

    /**
     * @param page
     *         分页参数
     * @param programLevelId
     *         节目等级ID
     * @param sceneId
     *         场景ID
     * @param personId
     *         明星ID
     * @param videoCompanyId
     *         平台公司ID
     * @param videoTypeId
     *         视频类型ID
     * @param videoName
     *         视频名称
     * @param labelId
     *         标签id
     *
     * @return 广告位集合
     *
     * @throws @modifier
     * @Title: selectAdSlotPage
     * @Description: 分页查询所有广告位
     * @author liuxiaolong
     * @remark
     * @version V1.0
     */
    List<AdSlotOfAdDTO> selectAdSlotPage(
            Pagination page, @Param("programLevelId") Long programLevelId, @Param("sceneId") Long sceneId,
            @Param("personId") Long personId, @Param("videoCompanyId") Long videoCompanyId,
            @Param("videoTypeId") Long videoTypeId, @Param("videoName") String videoName, @Param("labelId") Long labelId
    );

    /**
     * @param videoId
     *         视频ID
     *
     * @return 广告位集合
     *
     * @throws @modifier
     * @Title: selectAdSlotSingle
     * @Description: 查询单个视频的所有广告位
     * @author liuxiaolong
     * @remark
     * @version V1.0
     */
    List<VideoAdSlotDTO> selectAdSlotSingle(@Param("videoId") Long videoId);

    /**
     * @param list
     *         广告位ID
     *
     * @return 广告位集合
     *
     * @throws @modifier
     * @Title: selectAdSlotByIds
     * @Description: 通过广告位列表查询广告位
     * @author liuxiaolong
     * @remark
     * @version V1.0
     */
    @SuppressWarnings("rawtypes")
    List<AdSlotOfAdDTO> selectAdSlotByIds(List list);

    /**
     * @param adId
     *         广告ID
     * @param list
     *         广告位ID
     *
     * @return
     *
     * @throws @modifier
     * @Title: updateAdSlotAdid
     * @Description: 更新广告位的广告ID
     * @author liuxiaolong
     * @remark
     * @version V1.0
     */
    @SuppressWarnings("rawtypes")
    boolean updateAdSlotAdid(@Param("adId") Long adId, @Param("list") List list);

    /**
     * @param adId
     *         广告id
     *
     * @return 广告的广告位
     *
     * @throws @modifier
     * @Title: selectAdSlotByAdId
     * @Description: 根据广告ID查询广告位
     * @author liuxiaolong
     * @date 2017/4/10
     * @remark
     * @version V1.0
     */
    List<AdSlotOfAdDTO> selectAdSlotByAdId(Long adId);

    /**
     * @param videoName
     *         视频名称
     * @param videoCompanyId
     *         视频公司ID
     * @param adId
     *         广告id
     *
     * @return 广告位集合
     *
     * @throws @modifier
     * @Title: selectOffShelfAdPage
     * @Description: 分页查询待下架的广告位
     * @author liuxiaolong
     * @date 2017/3/23
     * @remark
     * @version V1.0
     */
    List<AdSlotOfAdDTO> selectOffShelfAdPage(
            Pagination page, @Param("videoName") String videoName, @Param("videoCompanyId") Long videoCompanyId,
            @Param("adId") Long adId
    );

    /**
     * @param list
     *         广告位ID
     *
     * @return
     *
     * @throws @modifier
     * @Title: updateAdSlotByChoosAd
     * @Description: 下架选中广告
     * @author liuxiaolong
     * @date 2017/3/23
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
    // boolean updateAdSlotAllAdid(@Param("adId") Long adId);

    /**
     * @param list
     *         选中的广告位ids
     *
     * @return 广告位集合
     *
     * @throws @modifier
     * @Title: selectOffShelfAdList
     * @Description: 通过广告位ID列 查询要导出的 下架广告位信息
     * @author liuxiaolong
     * @date 2017/3/24
     * @remark
     * @version V1.0
     */
    @SuppressWarnings("rawtypes")
    List<AdSlotOfAdDTO> selectOffShelfAdList(List list);

    /**
     * @param orderIdList
     *
     * @return
     *
     * @Title: updateAdByOrderId
     * @Description: 批量删除广告位和广告的关联
     * @author guofei
     * @date 2017年5月5日
     */
    int updateAdByOrderId(@Param("orderIdList") List<String> orderIdList);

    /**
     * @return
     *
     * @throws @modifier
     * @Title: selectAdSlotSearch
     * @Description: 广告投放广告位的查询下拉框
     * @author liuxiaolong
     * @date 2017/4/20
     * @remark
     * @version V1.0
     */
    List<AdSlotSearchDTO> selectAdSlotSearch();

}
