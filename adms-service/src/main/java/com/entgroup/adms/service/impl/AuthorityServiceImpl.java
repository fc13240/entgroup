package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entgroup.adms.model.system.Authority;
import com.entgroup.adms.mapper.AuthorityMapper;
import com.entgroup.adms.service.AuthorityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {


    @Cacheable(value = "AllAuthoritiesCache")
    public List<Authority> selectAll() {
        return selectList(new EntityWrapper<>(new Authority()));
    }
}
