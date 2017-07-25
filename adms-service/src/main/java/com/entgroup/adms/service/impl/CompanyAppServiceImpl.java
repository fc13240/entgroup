package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.system.CompanyApp;
import com.entgroup.adms.mapper.CompanyAppMapper;
import com.entgroup.adms.service.CompanyAppService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接入APP表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class CompanyAppServiceImpl extends ServiceImpl<CompanyAppMapper, CompanyApp> implements CompanyAppService {

    @Cacheable(value = "appCache")
    public CompanyApp selectByAppPackage(String packageName) {
        return selectOne(new EntityWrapper<>(new CompanyApp()).where("app_package={0}",packageName));
    }

}

