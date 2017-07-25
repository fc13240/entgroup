package com.entgroup.adms.service;

import com.entgroup.adms.model.system.CompanyApp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 接入APP表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface CompanyAppService extends IService<CompanyApp> {

    /**
     * 通过包名获取CompanyApp信息
     *
     * @param packageName
     * @return
     */
    public CompanyApp selectByAppPackage(String packageName);

}
