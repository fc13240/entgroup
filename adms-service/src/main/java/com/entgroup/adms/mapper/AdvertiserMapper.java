package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Advertiser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MaxNull
 * @since 2017-05-05
 */
public interface AdvertiserMapper extends BaseMapper<Advertiser> {

    /**
     * @param page
     * @param id
     * @param name
     * @param statuses
     * @param ids
     * @param companyVocationId
     * @param companyId
     * @param userId
     * @return List<Advertiser>
     * @throws
     *
     * @title getAdvertiserList
     * @description TODO 根据获取广告主列表
     * @author mxy
     * @date 2017-05-05 11:48
     * @modifier
     * @remark
     * @version V1.0
     */
    List<Advertiser> getAdvertiserList(Pagination page, @Param("id") Long id, @Param("name") String name,
                                       @Param("statuses") List<String> statuses, @Param("ids") List<String> ids,
                                       @Param("companyVocationId") Long companyVocationId, @Param("companyId") Long companyId,
                                       @Param("userId") Long userId);
}