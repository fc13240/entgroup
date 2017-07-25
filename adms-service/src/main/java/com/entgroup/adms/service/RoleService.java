package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.system.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface RoleService extends IService<Role> {

    /**
     * @title getAllRoles
     * @description TODO 获取角色列表信息
     * @author xiaokun
     * @date 2017-03-28 14:43
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param role
     * @return Page<Role>
     * @throws 2017-03-23 15:42
     */
    public Page<Role> getAllRoles(Page<Role> page, Role role);
}
