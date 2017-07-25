package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.entgroup.adms.model.system.Advertiser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MaxNull
 * @since 2017-05-05
 */
public interface AdvertiserService extends IService<Advertiser> {
    /**
     * @param page
     * @param id
     * @param name
     * @param statuses
     * @param ids
     * @param companyVocationId
     * @param companyId
     * @param userId
     * @return Page<Advertiser>
     * @throws
     *
     * @title getAdvertiserList
     * @description TODO 根据获取广告主列表
     * @author mxy
     * @date 2017-05-05 11:59
     * @modifier
     * @remark
     * @version V1.0
     */
    Page<Advertiser> getAdvertiserList(Page<Advertiser> page, Long id, String name,
                                        List<String> statuses, List<String> ids,
                                        Long companyVocationId, Long companyId,
                                        Long userId);
}
