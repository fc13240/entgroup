package com.entgroup.adms.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
public interface UserService extends IService<User> {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * @title findUserInfoByLoginName
     * @description TODO
     * @param loginName
     * @return User
     * @exception
     * @author mxy
     * @date 2017-01-16 12:01
     * @modifier
     * @remark
     * @version V1.0
     */
    User findUserInfoByLoginName(String loginName);

    /**
     * @title getAllUsers
     * @description TODO 分页获取用户列表信息
     * @author xiaokun
     * @date 2017-03-20 14:42
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param user
     * @return Page<User>
     * @throws
     */
    public Page<User> getAllUsers(Page<User> page, User user);

    /**
     * @title setEntryPassword
     * @description TODO 获取加密盐与密码——新增用户
     * @author xiaokun
     * @date 2017-04-02 09:37
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param user
     * @return User
     * @throws
     */
    public User setEntryPassword (User user);

    /**
     * @title checkPassword
     * @description TODO 密码校验
     * @author xiaokun
     * @date 2017-03-29 15:31
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param user
     * @param password
     * @return boolean
     * @throws
     */
    public boolean checkPassword(User user, String password);
}
