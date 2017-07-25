package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaokun
 * @ClassName: UserController
 * @Description: 用户管理
 * @date 2017-03-16 14:34
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "用户管理";

    /**
     * @title userList
     * @description TODO 查询用户列表
     * @author xiaokun
     * @date 2017-03-22 14:36
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param pageNum
     * @param pageSize
     * @param user
     * @param model
     * @return String
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.VIEW_COMPANYANDUSER_USERLIST}, logical = Logical.OR)
    @RequestMapping("/userList")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "查询用户列表")
    public String userList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer pageNum,
                              @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer pageSize,
                              @ModelAttribute("user") User user, Model model) {

        this.log.info("userList......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Integer admin = shiroUser.getAdmin();
        Long companyId = shiroUser.getCompanyId();

        UserRole shiroUserRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
        Role shiroRole = roleService.selectById(shiroUserRole.getRoleId());

        // 初始化用户集合
        List<User> userList = Lists.newArrayList();

        // 筛选框参数-企业列表
        List<Company> companyList = Lists.newArrayList();

        // 获取企业列表
        /*if (companyId != 1L) {
            companyList.add(companyService.selectById(companyId));
            user.setCompanyId(companyId);
            if (admin != 1) {
                user.setAdmin(0);
            }
        } else {
            companyList = companyService.selectList(null);
        }*/

        EntityWrapper<Company> companyEntityWrapper;

        // 默认只给定艺恩内部 (超级管理员 & 管理员)
        companyEntityWrapper = new EntityWrapper<>();
        companyEntityWrapper.like("company_name", "艺恩");
        companyList = companyService.selectList(companyEntityWrapper);
        user.setCompanyId(companyService.selectOne(companyEntityWrapper).getId());

        if (shiroRole.getRoleLevel() < 3) {
            user.setRoleLevel(shiroRole.getRoleLevel() + 1);
        }

        // 分页信息
        Page<User> userListPage = new Page<>(pageNum,pageSize);
        userListPage.setOrderByField("id");
        userListPage.setAsc(false);
        userListPage = userService.getAllUsers(userListPage, user);
        userList = userListPage.getRecords();

        // 总运营(客户总运营) 可见所有客户用户信息
        if (shiroRole.getRoleLevel() == 2) {
            companyEntityWrapper = new EntityWrapper<>();
            companyList = companyService.selectList(companyEntityWrapper.eq("user_id", userId));

            userList = Lists.newArrayList();
            for (Company company : companyList) {
                userList.addAll(userService.selectList(new EntityWrapper<User>().eq("company_id", company.getId())));
            }
            userListPage.setRecords(userList);
        }

        // 页面传值
        PageInfo<User> page = new PageInfo<>(userListPage);
        model.addAttribute("userList", userList);
        model.addAttribute("page", page);
        model.addAttribute("companyList", companyList);

        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("userList......");
            log.debug("userList...pageNum:{}", pageNum);
            log.debug("userList...pageSize:{}", pageSize);
            log.debug("userList...param user toString:{}", user.toString());
            log.debug("userList...result userList size:{}", userListPage.getRecords().size());
            log.debug("userList...result page total:{}", page.getTotal());
        }
        return "user/userList";
    }

    /**
     * @title saveUser
     * @description TODO 保存用户信息(新增/修改)
     * @author xiaokun
     * @date 2017-04-12 14:09
     * @modifier
     * @remark
     * @version V1.1
     *
     * @param user
     * @return JsonResult
     * @throws
     */
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.PERMISSION_USERLIST_SAVEUSER}, logical = Logical.OR)
    @RequestMapping("/saveUser")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存用户信息")
    public JsonResult saveUser(@ModelAttribute("user") User user) {

        log.info("saveUser......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();

        // 非艺恩创建用户默认企业为当前企业/角色为运营人员 (判断id为空避免覆盖广告主角色)
        if (companyId != 1L) {
            user.setCompanyId(companyId);
            if (null == user.getId()) {
                user.setRoleId(roleService.selectOne(new EntityWrapper<Role>().eq("role_identity", "operator")).getId());
            } else {
                user.setRoleId(userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", user.getId())).getRoleId());
            }
        }
        // 非空校验
        String msg = userIsNull(user);
        if (null != msg) {
            jr = renderError(msg);
            return jr;
        }

        // 获取标识值作为查找依据
        String userName = user.getLoginName();
        String userMobile = user.getMobile();
        String userEmail = user.getEmail();

        Integer userRoleId = user.getRoleId();
        UserRole oldUserRole = null;
        User oldUser = null;

        // 判断区分新增/修改
        if (null != user.getId()) {
            // 判断改名是否重复
            String newLoginName = user.getLoginName();
            User conflictUser = null;
            try {
                conflictUser = userService.selectOne(new EntityWrapper<User>().where("login_name='"+newLoginName+"'").and("id!="+user.getId()));
                if (null != conflictUser) {
                    jr = renderError("该用户已存在");
                    return jr;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("用户修改查重异常");
                return jr;
            }

            // 修改
            oldUser = userService.selectById(user.getId()); // 获取备份信息用于回滚
            user.setModifierId(userId); // 赋值修改人id
            user.setUpdated(TimeStamp.getCurrentTime().getDate()); // 赋值修改时间
            try {
                userService.updateById(user);
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("更新用户信息异常");
                return jr;
            }
            // 清空原有角色信息
            try {
                oldUserRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
                userRoleService.delete(new EntityWrapper<UserRole>().eq("user_id", user.getId()));
            } catch (Exception e) {
                e.printStackTrace();
                // 清空失败回滚update信息
                try {
                    userService.updateById(oldUser);
                    jr = renderError("清空原有角色信息异常");
                } catch (Exception e1) {
                    jr = renderError("清除失败信息异常 请手动还原用户信息");
                }
                return jr;
            }
        } else {
            // 校验数据库中不存在此用户
            String newLoginName = user.getLoginName();
            User conflictUser = null;
            try {
                conflictUser = userService.selectOne(new EntityWrapper<User>().where("login_name='"+newLoginName+"'"));
                if (null != conflictUser) {
                    jr = renderError("该用户已存在");
                    return jr;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("用户添加查重异常");
                return jr;
            }

            // 新增
            user = userService.setEntryPassword(user); // 密码加密
            user.setCreatorId(userId); // 赋值创建人id
            user.setCreated(TimeStamp.getCurrentTime().getDate()); // 赋值创建时间
            user.setManagerId(userId); // 赋值管理者id
            user.setEnabled(USER_ENABLED_TRUE);
            user.setDeleted(DELETED_VALUE_OF_SELECT_OR_SAVE);
            // 赋值admin (当企业目前没有人员且其角色为运营人员时-admin为1)
            if (user.getCompanyId() != 1L && user.getRoleId() == 2 && userService.selectList(new EntityWrapper<User>().eq("company_id", user.getCompanyId())).size() == 0) {
                user.setAdmin(1);
            } else {
                user.setAdmin(0);
            }
            // 保存用户信息到sys_user表
            try {
                userService.insert(user);
                user = userService.selectOne(new EntityWrapper<User>().where("login_name='"+userName+"'").and("mobile='"+userMobile+"'").and("email='"+userEmail+"'"));
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("保存用户信息异常");
                return jr;
            }
        }
        // 根据查找依据找到改动的user并赋值角色
        UserRole userRole = new UserRole();
        try {
            userRole.setUserId(user.getId());
            userRole.setRoleId(userRoleId);
            userRoleService.insert(userRole);
            jr = renderSuccess("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            // 角色赋值失败——回滚删除
            try {
                if (null == oldUser) {
                    userService.deleteById(user.getId());
                } else {
                    userService.updateById(oldUser);
                    userRoleService.insert(oldUserRole);
                }
                jr = renderError("用户角色赋值异常");
            } catch (Exception e1) {
                e1.printStackTrace();
                jr = renderError("清除失败信息异常 请手动还原用户信息");
            }
            return jr;
        }
        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("saveUser......");
            log.debug("saveUser...param user toString:{}", user.toString());
            log.debug("saveUser...result jr toString:{}", jr.toString());
        }
        return jr;
    }


    /**
     * @title getRoleListByRoleLevel
     * @description TODO 根据roleLevel获取角色列表
     * @author xiaokun
     * @date 2017-06-12 16:41
     * @modifier
     * @remark
     * @version V2.0
     *
     * @param
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/getRoleListByRoleLevel")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.VIEW_COMPANYANDUSER_USERLIST}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "根据roleLevel获取角色列表")
    public JsonResult getRoleListByRoleLevel() {

        log.info("getRoleListByRoleLevel......");

        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();
        Integer admin = shiroUser.getAdmin();

        UserRole shiroUserRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
        Role shiroRole = roleService.selectById(shiroUserRole.getRoleId());

        List<Role> roleList = Lists.newArrayList();
        EntityWrapper<Role> roleEntityWrapper = new EntityWrapper<>();
        if (shiroRole.getRoleLevel() < 3) {
            roleEntityWrapper.eq("role_level", shiroRole.getRoleLevel() + 1);
            roleList = roleService.selectList(roleEntityWrapper);
            if (roleList.size() == 0) {
                jr = renderError("获取角色列表失败");
                return jr;
            }
        }

        jr = renderSuccess();
        jr.setData("roleList", roleList);

        if (log.isDebugEnabled()) {
            log.info("getRoleListByRoleLevel......");
            log.debug("getRoleListByRoleLevel...result roleList size:{}", roleList.size());
            log.debug("getRoleListByRoleLevel...result jr toString:{}", jr.toString());
        }

        return jr;
    }

    /**
     * @title userDetail
     * @description TODO 用户详情(个人中心)
     * @author xiaokun
     * @date 2017-03-29 09:33
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return String
     * @throws
     */
    @RequestMapping("/userDetail")
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.VIEW_PERSONALCENTER_USERDETAIL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "用户详情")
    public String userDetail(Model model) {

        log.info("userDetail......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();

        // 获取角色权限信息
        UserRole userRole;
        Role role;
        try {
            userRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
            role = roleService.selectById(userRole.getRoleId());
            shiroUser.setRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("shiroUser", shiroUser);

        if (log.isDebugEnabled()) {
            log.info("userDetail......");
            log.debug("userDetail...result user toString:{}", shiroUser.toString());
        }
        return "personalCenter/userDetail";
    }

    /**
     * @title getUserDetail
     * @description TODO 查询用户详情(用户管理)
     * @author xiaokun
     * @date 2017-04-10 11:56:46
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/getUserDetail")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.PERMISSION_USERLIST_USERDETAIL}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "查询用户详情")
    public JsonResult getUserDetail(@RequestParam("id") Long id) {

        log.info("getUserDetail......");

        // 获取角色权限信息
        User user;
        UserRole userRole;
        Role role = new Role();
        try {
            user = userService.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("获取用户详情失败");
            return jr;
        }
        try {
            userRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", user.getId()));
            if (null != userRole) {
                role = roleService.selectById(userRole.getRoleId());
                user.setRole(role);
            }
            jr = renderSuccess("获取用户详情成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("获取用户角色失败");
        }
        jr.setData("user", user);

        if (log.isDebugEnabled()) {
            log.info("getUserDetail......");
            log.debug("getUserDetail...result user toString:{}", user.toString());
        }
        return jr;
    }

    /**
     * @title checkPassword
     * @description TODO 密码验证
     * @author xiaokun
     * @date 2017-04-10 15:10
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param oldPassword
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/checkPassword")
    @ResponseBody
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "密码验证")
    public JsonResult checkPassword(@RequestParam("oldPassword") String oldPassword) {

        log.info("checkPassword......");

        User shiroUser = getShiroUser();
        // 密码校验
        boolean checkResult = userService.checkPassword(shiroUser, oldPassword);
        if (!checkResult) {
            jr = renderError("密码输入错误");
            return jr;
        }
        jr = renderSuccess("密码输入正确");

        if (log.isDebugEnabled()) {
            log.info("checkPassword......");
            log.debug("checkPassword...param oldPassword:{}", oldPassword);
            log.debug("checkPassword...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title changePassword
     * @description TODO 密码修改
     * @author xiaokun
     * @date 2017-03-29 15:38
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param newPassword
     * @return JsonResult
     * @throws
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    @RequiresPermissions(value = {AuthorityConstants.PersonalCenter.PERMISSION_USERDETAIL_CHANGEPASSWORD}, logical = Logical.OR)
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "密码修改")
    public JsonResult changePassword(@RequestParam("newPassword") String newPassword) {

        log.info("changePassword......");

        // 获取当前用户信息
        User shiroUser = getShiroUser();

        // 新密码校验
        shiroUser.setPassword(newPassword);
        shiroUser = userService.setEntryPassword(shiroUser);
        try {
            userService.updateById(shiroUser);
            jr = renderSuccess("修改密码成功 下次请使用新密码登陆");
        } catch (Exception e) {
            jr = renderError("密码修改失败");
            return jr;
        }

        if (log.isDebugEnabled()) {
            log.info("changePassword......");
            log.debug("changePassword...param newPassword:{}", newPassword);
            log.debug("changePassword...result shiroUser toString:{}", shiroUser.toString());
            log.debug("changePassword...result jr toString:{}", jr.toString());
        }
        return jr;
    }


    public String userIsNull(User user) {

        String msg = null;

        if (null == user.getCompanyId()) {
            msg = "请选择所属企业";
        } else if (null == user.getLoginName() || user.getLoginName() == ""){
            msg = "请输入用户名";
        /*} else if (null == user.getMobile() || user.getMobile() == "") {
            msg = "请输入手机号";
        } else if (null == user.getEmail() || user.getEmail() == "") {
            msg = "请输入邮箱";*/
        } else if (null == user.getRoleId()) {
            msg = "请选择角色";
        } else if (null == user.getPassword() || user.getPassword() == "") {
            if (null == user.getId()) {
                msg = "请填写密码";
            }
        }
        return msg;
    }
}
