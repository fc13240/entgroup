package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *
     * @param page
     * @param role
     * @return List<Role>
     *
     * @title getAllRoles
     * @description TODO 分页查询角色信息
     * @author xiaokun
     * @date 2017-03-27 14:49
     * @modifier
     * @remark
     * @version V1.0
     */
    List<Role> getAllRoles(Pagination page, Role role);
}
