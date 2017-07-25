package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.mapper.CompanyVocationMapper;
import com.entgroup.adms.model.system.CompanyVocation;
import com.entgroup.adms.service.CompanyVocationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业表 服务实现类
 * </p>
 *
 * @author cuixiaokun
 * @since 2017-03-23
 */
@Service
public class CompanyVocationServiceImpl extends ServiceImpl<CompanyVocationMapper, CompanyVocation> implements CompanyVocationService {
}