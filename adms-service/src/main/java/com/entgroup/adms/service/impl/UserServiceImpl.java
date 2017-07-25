package com.entgroup.adms.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.service.UserService;
import com.entgroup.adms.util.DigestUtils;
import com.entgroup.adms.util.EncodeUtils;
import org.springframework.stereotype.Service;
import com.entgroup.adms.mapper.UserMapper;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hpb
 * @since 2017-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    /**
     * @title findUserInfoByLoginName
     * @description TODO
     * @param loginName
     * @return User
     * @exception
     * @author mxy
     * @date 2017-59-16 11:59
     * @modifier
     * @remark
     * @version V1.0
     */
    public User findUserInfoByLoginName(String loginName) {
        return baseMapper.findUserInfoByLoginName(loginName);
    }

    /**
     * 密码加密
     *
     * @param salt     加密的盐
     * @param password 未加密的密码
     * @return 加密密码
     * @author hpb
     */
    private String genEntryptPassword(String salt, String password) {
        byte[] hashPassword = DigestUtils.sha1(password.getBytes(), EncodeUtils.decodeHex(salt), UserService.HASH_INTERATIONS);
        return EncodeUtils.encodeHex(hashPassword);
    }

    /**
     * 生成salt
     *
     * @return 加密的盐
     */
    private String genSalt() {
        return EncodeUtils.encodeHex(DigestUtils.generateSalt(UserService.SALT_SIZE));
    }

    /**
     * @title setEntryPassword
     * @description TODO 获取加密盐与密码——新增用户
     * @author xiaokun
     * @date 2017-04-09 13:48:41
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param user
     * @return User
     * @throws
     */
    public User setEntryPassword (User user) {
        String salt;
        String password;
        if (null != user.getSalt()) {
            salt = user.getSalt();
        } else {
            salt = genSalt();
            user.setSalt(salt);
        }
        password = genEntryptPassword(salt, user.getPassword());
        user.setPassword(password);
        return user;
    }

    /**
     * @title getAllUsers
     * @description TODO 分页查询用户信息
     * @author xiaokun
     * @date 2017-03-23 14:46
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param page
     * @param user
     * @return Page<User>
     * @throws
     */
    public Page<User> getAllUsers(Page<User> page, User user) {
        page.setRecords(baseMapper.getAllUsers(page, user));
        return page;
    }

    /**
     * @title getPassword
     * @description TODO 密码校验
     * @author xiaokun
     * @date 2017-03-29 14:32
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param password
     * @return String
     * @throws
     */
    public boolean checkPassword(User user, String password) {
        password = genEntryptPassword(user.getSalt(), password);
        return password.equals(user.getPassword());
    }
}

