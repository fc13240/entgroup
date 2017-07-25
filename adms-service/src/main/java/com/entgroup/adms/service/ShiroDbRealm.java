package com.entgroup.adms.service;

import com.entgroup.adms.model.system.Authority;
import com.entgroup.adms.model.system.Role;
import com.entgroup.adms.model.system.User;
import com.entgroup.adms.util.EncodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Description shiro登陆域Service.
 * @Author mengqch.
 * @Create 2014年5月22日.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private final Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);
    protected UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        log.info("doGetAuthorizationInfo");
        // FIXME 待解决
        User shiroUser = (User) principals.getPrimaryPrincipal();

        User user = userService.findUserInfoByLoginName(shiroUser
                .getLoginName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            String roleIdentity = role.getRoleIdentity();
            if (StringUtils.isNotBlank(roleIdentity)) {
                info.addRole(roleIdentity);
            }
        }

        // 此处的authority是通过管理sys_role联合查出来的.
        for (Authority authority : user.getAuthorities()) {
            String authorityIdentify = authority.getAuthorityIdentity();
            if (StringUtils.isNotBlank(authorityIdentify)) {
                info.addStringPermission(authorityIdentify);
            }
        }

        return info;
    }

    /**
     * 退出登录时清除用户缓存
     * @param principals
     */
    public void clearCacheAuthentication(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }



    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        String username = StringUtils.trim(token.getUsername());

        User user = userService.findUserInfoByLoginName(username);

        if (null != user) {
            user.setLastLoginDate(new Date());
            // 更新用户最后登陆时间
//            userService.updateLastLoginDate(user);
            byte[] salt = EncodeUtils.decodeHex(user.getSalt());
            User shiroUser = new User();
            BeanUtils.copyProperties(user, shiroUser);
            return new SimpleAuthenticationInfo(shiroUser, user.getPassword(),
                    ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }

    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
                UserService.HASH_ALGORITHM);

        matcher.setHashIterations(UserService.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    private static final String OR_OPERATOR = " or ";
    private static final String AND_OPERATOR = " and ";
    private static final String NOT_OPERATOR = "not ";

    /**
     * 支持or and not 关键词 不支持and or混用
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains(OR_OPERATOR)) {
            String[] permissions = permission.split(OR_OPERATOR);
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains(AND_OPERATOR)) {
            String[] permissions = permission.split(AND_OPERATOR);
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals,
                                               String permission) {
        if (permission.startsWith(NOT_OPERATOR)) {
            return !super.isPermitted(principals,
                    permission.substring(NOT_OPERATOR.length()));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

}
