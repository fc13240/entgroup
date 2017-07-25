package com.entgroup.adms.mapper;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entgroup.adms.model.system.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface UserMapper extends BaseMapper<User> {
    User findUserInfoByLoginName(String loginName);

    /**
     *
     * @param page
     * @param user
     * @return List<User>
     *
     * @title getAllUsers
     * @description TODO 分页查询用户信息
     * @author xiaokun
     * @date 2017-03-20 14:50
     * @modifier
     * @remark
     * @version V1.0
     */
    List<User> getAllUsers(Pagination page, User user);
}
