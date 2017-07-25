package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.CompanyMapper;
import com.entgroup.adms.model.system.Company;
import com.entgroup.adms.service.CompanyService;
import com.entgroup.adms.util.DigestUtils;
import com.entgroup.adms.util.EncodeUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    /**
     * 根据short name生成秘钥
     * @param simpleName
     * @return
     */
    private String genSecretKey(String simpleName) {
        byte[] salt = DigestUtils.generateSalt(8);
        byte[] hashSecretKey = DigestUtils.sha1(simpleName.getBytes(), salt, 1024);
        return EncodeUtils.encodeHex(hashSecretKey);
    }
    @Override
    public Company selectByAppkeyAndPackageName(String appKey, String appPackageName) {
        return baseMapper.selectByAppkeyAndPackageName(appKey,appPackageName);
    }

    /**
     * @title getAllCompanies
     * @description TODO 分页查询客户信息
     * @author xiaokun
     * @date 2017-03-23 16:27
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param company
     * @return Page<Company>
     * @throws 2017-03-27 14:45
     */
    public Page<Company> getAllCompanies(Page<Company> page, Company company) {
        page.setRecords(baseMapper.getAllCompanies(page, company));
        return page;
    }

    /**
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
    @Override
    public List<Company> getContentPlatformList() {
        return baseMapper.selectList(new EntityWrapper<Company>().eq("deleted", "0").eq("status", "3").in
                ("company_type","0,1"));
    }

}

