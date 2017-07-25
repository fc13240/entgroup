package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.TreeNode;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xiaokun
 * @ClassName: RoleController
 * @Description: 角色管理
 * @date 2017-03-27 17:05
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "角色管理";

    /**
     * @title roleList
     * @description TODO 查询角色列表
     * @author xiaokun
     * @date 2017-03-27 14:41
     * @modifier
     * @remark
     * @version V2.0
     *
     * @param pageNum
     * @param pageSize
     * @param model
     * @return String
     * @throws 2017-03-24 14:40
     */
    @RequiresPermissions(value = {AuthorityConstants.RoleAuthority.VIEW_ROLEAUTHORITY_ROLELIST}, logical = Logical.OR)
    @RequestMapping("/roleList")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "查询角色列表")
    public String roleList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
                           @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,Model model) {
        log.info("roleList......");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();
        Integer admin = shiroUser.getAdmin();
        Long companyId = shiroUser.getCompanyId();

        // 所有用户都只能看到自己企业内角色列表
        Role role = new Role();

        // 仅显示V1.0除外的当前用角色
        role.setRoleIdentity("V1.0");

        List<Role> roleList = Lists.newArrayList();

        // 分页查询
        Page<Role> rolePage = new Page<>(pageNum, pageSize);
        rolePage.setOrderByField("id");
        rolePage = roleService.getAllRoles(rolePage, role);
        roleList = rolePage.getRecords();

        // 赋值权限名字符串
        for (Role roleTemp : roleList) {
            roleTemp.setAdmin(
                    null == roleAuthorityService.selectOne(new EntityWrapper<RoleAuthority>().eq("role_id", roleTemp.getId()).eq("authority_id", 1000)) ? 0 : 1
            );
        }

        // 页面传值
        PageInfo<Role> page = new PageInfo<>(rolePage);
        model.addAttribute("roleList", roleList);
        model.addAttribute("page", page);

        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("roleList......");
            log.debug("roleList...param pageNum:{}", pageNum);
            log.debug("roleList...param pageSize:{}", pageSize);
            log.debug("roleList...param role toString:{}", role);
            log.debug("roleList...result roleList size:{}", roleList.size());
            log.debug("roleList...result page total:{}", page.getTotal());
        }
        return "role/roleList";
    }

    /**
     * @title getAuthorityTree
     * @description TODO 获取权限树
     * @author xiaokun
     * @date 2017-04-06 09:16
     * @modifier
     * @remark
     * @version V1.0
     *
     * @return JsonResult
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.RoleAuthority.PERMISSION_ROLELIST_ROLEDETAIL}, logical = Logical.OR)
    @RequestMapping("/getAuthorityTree")
    @ResponseBody
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "获取权限树")
    public JsonResult getAuthorityTree(@ModelAttribute("role") Role role) {

        log.info("getAuthorityTree......");

        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();
        Integer admin = shiroUser.getAdmin();
        Long companyId = shiroUser.getCompanyId();

        UserRole shiroUserRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
        Role shiroRole = roleService.selectById(shiroUserRole.getRoleId());
        Integer shiroLevel = shiroRole.getRoleLevel();

        // 初始化
        List<RoleAuthority> roleAuthorityList = Lists.newArrayList();
        List<TreeNode> treeNodes = Lists.newArrayList();

        // 新建角色默认类型为用户角色
        if (null == role.getId() && null == role.getRoleLevel()) {
            role.setRoleLevel(1);
        }

        EntityWrapper<RoleAuthority> roleAuthorityEntityWrapper = new EntityWrapper<>();
        if (null != role.getId() && role.getId() != 0) {
            role = roleService.selectById(role.getId());
            roleAuthorityEntityWrapper.eq("role_id", role.getId());
            roleAuthorityList = roleAuthorityService.selectList(roleAuthorityEntityWrapper);
        }

        Integer type = 1;

        // 非艺恩管理员不能为角色赋管理权限
        if (null == role.getAdmin()) {
            if (null == roleAuthorityService.selectOne(new EntityWrapper<RoleAuthority>().eq("role_id", role.getId()).eq("authority_id", 1000))) {
                role.setAdmin(1);
            } else {
                role.setAdmin(2);
            }
        }
        type = role.getRoleLevel() < 3 ? 1:2;

        List<Authority> authorityList = authorityService.selectList(
                new EntityWrapper<Authority>().in("type", type+",3")
                        .and("id > 1000")
                        .and("id < 1000000")
        );

        boolean mark = Boolean.FALSE;

        if (null != authorityList) {
            if (type == 1) {
                for (Authority authority : authorityList) {
                    for (RoleAuthority roleAuthority : roleAuthorityList) {
                        if (authority.getId().equals(roleAuthority.getAuthorityId())) {
                            mark = Boolean.TRUE;
                        }
                    }
                    treeNodes.add(new TreeNode(authority.getId(), authority.getParentId(), authority.getName(), mark, mark));
                    mark = Boolean.FALSE;
                }
            } else if (type == 2){
                for (Authority authority : authorityList) {
                    for (RoleAuthority roleAuthority : roleAuthorityList) {
                        if (authority.getId().equals(roleAuthority.getAuthorityId())) {
                            mark = Boolean.TRUE;
                        }
                    }
                    treeNodes.add(new TreeNode(authority.getId(), authority.getParentId(), authority.getRemark(), mark, mark));
                    mark = Boolean.FALSE;
                }
            }/* else {

            }*/
            jr = renderSuccess();
            jr.setData("treeNodes", treeNodes);
            jr.setData("role", role);
        } else {
            jr = renderError();
        }

        if (log.isDebugEnabled()) {
            log.info("getAuthorityTree......");
            log.debug("getAuthorityTree...param type:{}", type);
            log.debug("getAuthorityTree...result treeNodes size:{}", treeNodes.size());
            log.debug("getAuthorityTree...result jr toString:{}", jr.toString());
        }
        return jr;
    }

    /**
     * @title getParentAuthorityIds
     * @description TODO 获取完整权限列表（不展示操作级权限的情况下）
     * @author xiaokun
     * @date 2017-04-07 10:58
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param viewAuthorityIdTemps
     * @return List<Integer>
     * @throws
     */
    public List<Integer> getParentAuthorityIds(List<String> viewAuthorityIdTemps) {

        log.info("getParentAuthorityIds......");

        // 初始化结果集
        List<Integer> authorityIds = Lists.newArrayList();// 全部权限级
        List<Integer> sysAuthorityIds = Lists.newArrayList();// 操作级权限
        List<Integer> viewAuthorityIds = Lists.newArrayList();// 视图级权限
        List<Integer> menuAuthorityIds = Lists.newArrayList();// 菜单级权限
        // 初始化遍历参数
        Integer sysAuthorityId;
        Integer viewAuthorityId;
        Integer menuAuthorityId;
        // 遍历生成各级权限集合
        for (String viewAuthorityIdTemp : viewAuthorityIdTemps) {
            // 获取视图级权限id
            viewAuthorityId = Integer.parseInt(viewAuthorityIdTemp.trim());
            viewAuthorityIds.add(viewAuthorityId);
            // 获取菜单级权限id
            menuAuthorityId = viewAuthorityId/100;
            if (!menuAuthorityIds.contains(menuAuthorityId)) {
                menuAuthorityIds.add(menuAuthorityId);
            }
            // 获取操作级权限id
            List<Authority> sysAuthorityList = authorityService.selectList(new EntityWrapper<Authority>().eq("parent_id", viewAuthorityId));
            for (Authority authority : sysAuthorityList) {
                sysAuthorityIds.add(authority.getId());
            }
        }
        // 添加所有权限
        authorityIds.addAll(sysAuthorityIds);
        authorityIds.addAll(viewAuthorityIds);
        authorityIds.addAll(menuAuthorityIds);

        if (log.isDebugEnabled()) {
            log.info("getParentAuthorityIds......");
            log.debug("getParentAuthorityIds...param sysAuthorityIdTemps size:{}", viewAuthorityIdTemps.size());
            log.debug("getParentAuthorityIds...result sysAuthorityIds size:{}", sysAuthorityIds.size());
            log.debug("getParentAuthorityIds...result sysAuthorityIds size:{}", sysAuthorityIds.size());
            log.debug("getParentAuthorityIds...result viewAuthorityIds size:{}", viewAuthorityIds.size());
            log.debug("getParentAuthorityIds...result menuAuthorityIds size:{}", menuAuthorityIds.size());
            log.debug("getParentAuthorityIds...result authorityIds size:{}", authorityIds.size());
        }

        return authorityIds;
    }

    /**
     * @title saveRole
     * @description TODO 保存角色信息(新增/修改)
     * @author xiaokun
     * @date 2017-04-07 15:52:49
     * @modifier
     * @remark
     * @version V1.1
     *
     * @param role
     * @return JsonResult
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.RoleAuthority.PERMISSION_ROLELIST_SAVEROLE}, logical = Logical.OR)
    @RequestMapping("/saveRole")
    @ResponseBody
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存角色信息(新增/修改)")
    public JsonResult saveRole(@ModelAttribute("role") Role role) {

        log.info("saveRole...");

        // 获取当前登录用户信息
        User shiroUser = getShiroUser();

        Long userId = shiroUser.getId();
        Long companyId = shiroUser.getCompanyId();

        /**
         * 角色等级标识位
         * 1-管理角色  2-总运营角色  3-运营角色
         */
        Integer adminRemark = role.getAdmin();
        role.setRoleLevel(adminRemark);

        UserRole shiroUserRole = userRoleService.selectOne(new EntityWrapper<UserRole>().eq("user_id", userId));
        Role shiroRole = roleService.selectById(shiroUserRole.getRoleId());
        Integer shiroLevel = shiroRole.getRoleLevel();

        if (null == shiroLevel) {
            jr = renderError("获取当前用户权限级失败");
            return jr;
        } else if (shiroLevel > 0) {
            jr = renderError("仅管理员能够创建角色");
        }

        // 超级管理员角色保护
        if (role.getRoleIdentity() == "super_administrator") {
            jr = renderError("系统管理员权限禁止修改");
            return jr;
        }

        // 防止误操作或恶意修改/删除角色信息 (直接点击确定：authorityIds)
        if (null == role.getName() || role.getName() == "" ||
                null == role.getDescription() || role.getDescription() == "") {
            jr = renderError("内容未填写完善或操作错误");
            return jr;
        }

        // 获取两个参数可以作为 新增 后查找role_id的标识位
        String roleName = role.getName();
        String roleDescription = role.getDescription();

        // 获取对应权限id集合
        List<String> authorityIdTemps;
        List<Integer> authorityIds = null;
        if (null != role.getAuthorityIds() && role.getAuthorityIds() != "") {
            authorityIdTemps = Lists.newArrayList(role.getAuthorityIds().split(","));
            authorityIds = getParentAuthorityIds(authorityIdTemps);
        }

        // 根据role.id判断区分 新增/修改
        if (null != role.getId() && role.getId() != 0) { // 有id为修改
            // role修改赋值
            role.setModifierId(userId);// 赋值modifier_id为当前用户id
            role.setUpdated(TimeStamp.getCurrentTime().getDate());// 赋值updated为当前时间
            // 角色保存
            try {
                roleService.update(role, new EntityWrapper<Role>().eq("id", role.getId()));
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("修改角色——保存故障");
                return jr;
            }
            // 清空原有RoleAuthority (针对修改)
            try {
                if (null == authorityIds) {
                    jr = renderSuccess("未更新角色权限-保存成功");
                    return jr;
                }
                roleAuthorityService.delete(new EntityWrapper<RoleAuthority>().eq("role_id", role.getId()));
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("修改角色——清空原有RoleAuthority故障");
                return jr;
            }
        } else { // 无id为新增
            // role新增赋值
            role.setDeleted(DELETED_VALUE_OF_SELECT_OR_SAVE);// 赋值deleted默认值
            role.setParentId(shiroRole.getId());// 赋值parent_id默认值为当前用户角色id
            role.setCreatorId(userId);// 赋值creator_id为当前用户id
            role.setCreated(TimeStamp.getCurrentTime().getDate());// 赋值created为当前时间
            role.setCompanyId(companyId);// 赋值company_id为当前用户企业id
            // 角色保存
            try {
                roleService.insert(role);
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("新增角色——保存故障");
                return jr;
            }
        }

        // 获取 修改/保存 的角色id生成权限(赋值 RoleAuthority)
        try {
            role = roleService.selectOne(new EntityWrapper<Role>().where("name='"+roleName+"'").and("description='"+roleDescription+"'"));
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("保存后重新获取role故障");
            return jr;
        }

        // 初始化RoleAuthority赋值参数
        RoleAuthority roleAuthority = new RoleAuthority();
        List<RoleAuthority> roleAuthorityList = Lists.newArrayList();

        // 入库参数集合roleAuthorityList赋值
        for (Integer authorityId : authorityIds) {
            // roleAuthority赋值
            roleAuthority.setRoleId(role.getId());
            roleAuthority.setAuthorityId(authorityId);
            // roleAuthority存入集合
            roleAuthorityList.add(roleAuthority);
            // roleAuthority重置
            roleAuthority = new RoleAuthority();
        }

        // 批量存入RoleAuthority
        try {
            if (null == authorityIds) {
                return jr;
            }
            if (null != adminRemark && adminRemark == 1) {
                RoleAuthority adminRemarkRoleAuthority = new RoleAuthority();
                adminRemarkRoleAuthority.setRoleId(role.getId());
                adminRemarkRoleAuthority.setAuthorityId(1000);
                roleAuthorityList.add(adminRemarkRoleAuthority);
            }
            roleAuthorityService.insertBatch(roleAuthorityList);
            jr = renderSuccess("角色保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr = renderError("批量存入RoleAuthority故障");
            return jr;
        }

        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("saveRole......");
            log.debug("saveRole...param role toString:{}", role.toString());
            log.debug("saveRole...result roleName:{}", roleName);
            log.debug("saveRole...result roleDescription:{}", roleDescription);
            log.debug("saveRole...result authorityIds size:{}", authorityIds.size());
            log.debug("saveRole...result roleAuthorityList size:{}", roleAuthorityList.size());
            log.debug("saveRole...result jr toString:{}", jr.toString());
        }
        return jr;
    }
}
