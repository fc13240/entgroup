package com.entgroup.adms.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.mapper.RoleAuthorityMapper;
import com.entgroup.adms.model.system.Role;
import com.entgroup.adms.mapper.RoleMapper;
import com.entgroup.adms.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * @title getAllRoles
     * @description TODO 获取角色列表信息
     * @author xiaokun
     * @date 2017-03-27 14:45
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param role
     * @return Page<Role>
     * @throws
     */
    public Page<Role> getAllRoles(Page<Role> page, Role role) {
        page.setRecords(baseMapper.getAllRoles(page, role));
        return page;
    }
}

