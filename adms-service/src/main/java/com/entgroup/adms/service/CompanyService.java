package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Company;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 企业表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface CompanyService extends IService<Company> {

    /**
     * @Title: selectByAppkeyAndPackageName
     * @Description: 根据"应用密钥"和"包名"查询对应的公司
     * @author sunzhen
     * @date 2017/3/15
     * @param @param appKey
     * @param @param appPackageName
     * @return Company
     * @throws
     */
    Company selectByAppkeyAndPackageName(String appKey, String appPackageName);

    /**
     * @title getAllCompanies
     * @description TODO 查看企业列表
     * @author xiaokun
     * @date 2017-03-20 14:44
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param company
     * @return Page<Company>
     * @throws
     */
    public Page<Company> getAllCompanies(Page<Company> page, Company company);

    /**
     *
     * @return List<Company>
     *
     * @title getContentPlatformList
     * @description TODO 获取所有内容平台
     * @author mxy
     * @date 2017-03-23 15:42
     * @modifier
     * @remark
     * @version V1.0
     */
    List<Company> getContentPlatformList();

}
