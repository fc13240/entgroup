package com.entgroup.adms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.entgroup.adms.aop.SystemControllerLog;
import com.entgroup.adms.conf.AuthorityConstants;
import com.entgroup.adms.conf.CompanyAddConfig;
import com.entgroup.adms.model.JsonResult;
import com.entgroup.adms.model.system.*;
import com.entgroup.adms.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecgframework.poi.cache.manager.IFileLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author xiaokun
 * @ClassName: CompanyController
 * @Description: 客户管理
 * @date 2017-03-19 14:39
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {
    /**
     * 系统模块名
     */
    private static final String SYSTEM_MODULE = "客户管理";

    /**
     * @title companyList
     * @description TODO 查询客户列表
     * @author xiaokun
     * @date 2017-03-24 14:39
     * @modifier
     * @remark
     * @version V1.0
     *
     * @param pageNum
     * @param pageSize
     * @param company
     * @param model
     * @return String
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.VIEW_COMPANYANDUSER_COMPANYLIST}, logical = Logical.OR)
    @RequestMapping("/companyList")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "查询客户列表")
    public String companyList(@RequestParam(required = false, defaultValue = PAGE_NUM) int pageNum,
                                 @RequestParam(required = false, defaultValue = PAGE_SIZE) int pageSize,
                                 @ModelAttribute("company") Company company, Model model) {

        this.log.info("companyList......");

        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();

        // 赋值创建者id用于查询列表
        company.setUserId(userId);

        // 筛选框参数-企业分类
        List<CompanyVocation> companyVocationList = companyVocationService.selectList(null);
        List<Company> companyList;

        // 分页信息
        Page<Company> companyPage = new Page<>(pageNum, pageSize);
        companyPage.setOrderByField("id");
        companyPage.setAsc(false);
        companyPage = companyService.getAllCompanies(companyPage, company);
        companyPage.setOrderByField("id");
        companyPage.setAsc(false);
        companyList = companyPage.getRecords();

        for (Company companyTemp : companyList) {
            // 赋值用户总数
            if (null == company.getUserIds()) {
                companyTemp.setOrderCount(0);
            } else {
                companyTemp.setUserCount(StringUtils.countMatches(companyTemp.getUserIds(), ",")+1);
            }
            // 赋值订单总数
            if (null == companyTemp.getOrderIds()) {
                companyTemp.setOrderCount(0);
            } else {
                companyTemp.setOrderCount(StringUtils.countMatches(companyTemp.getOrderIds(), ",")+1);
            }
            // 赋值处理中订单数
            companyTemp.setOrderOnDeal(StringUtils.countMatches(companyTemp.getOrderIds(), "-1"));
        }
        // 页面传值
        PageInfo<Company> page = new PageInfo<>(companyPage);
        model.addAttribute("companyList", companyList);
        model.addAttribute("page", page);
        model.addAttribute("companyVocationList", companyVocationList);

        // 日志记录
        if (log.isDebugEnabled()) {
            log.info("companyList......");
            log.debug("companyList...pageNum:{}", pageNum);
            log.debug("companyList...pageSize:{}", pageSize);
            log.debug("companyList...param company toString:{}", company);
            log.debug("companyList...result companyList size:{}", companyPage.getRecords().size());
            log.debug("companyList...result page total:{}", page.getTotal());
        }
        return "company/companyList";
    }

    /**
     * @title saveCompany
     * @description TODO 保存客户信息
     * @author xiaokun
     * @date 2017-06-20 08:57
     * @modifier
     * @remark
     * @version V2.0
     *
     * @param company
     * @param files
     * @param request
     * @return JsonResult
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.PERMISSION_COMPANYLIST_SAVECOMPANY}, logical = Logical.OR)
    @RequestMapping(value = "/saveCompany", method = RequestMethod. POST,produces = "text/plain;charset=UTF-8")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "保存客户信息")
    @ResponseBody
    public JsonResult saveCompany(@ModelAttribute("company") Company company,
                                  @RequestParam("files") MultipartFile[] files,
                                  HttpServletRequest request) {

        log.info("saveCompany......");

        User shiroUser = getShiroUser();
        Long userId = shiroUser.getId();

        // 操作标识符
        boolean insertOrUpdate = true; // 默认为新增
        if (null != company.getId()) {
            insertOrUpdate = false;
        }

        boolean file0 = files[0].isEmpty();
        boolean file1 = files[1].isEmpty();

        // 证件图片修改标识符
        boolean updateOpenAccountPermit = true;
        boolean updateOrganizationCode = true;

        // 对象抽取
        // company;
        Company companyOld = new Company();
        User operator = new User();
        User operatorOld = new User();

        UserRole operatorRole = new UserRole(); // 仅用于新增

        // company对象校验
        String msg = companyIsNull(company);
        if (null != msg) {
            jr = renderError(msg);
            return jr;
        }

        // 证件图片元素
        MultipartFile openAccountPermit = null;
        MultipartFile organizationCode = null;

        String[] subOpenAccountPermitPath = new String[]{};
        String[] subOrganizationCodePath = new String[]{};

        // 证件图片数量检验
        if (insertOrUpdate) {
            if (files.length == 2) {
                openAccountPermit = files[0];
                organizationCode = files[1];
            } else {
                if (null == company.getOpenAccountPermitPath() || company.getOpenAccountPermitPath().equals("")) {
                    jr = renderError("请上传开户许可证图片");
                } else if (null == company.getOrganizationCodePath() || company.getOrganizationCodePath().equals("")) {
                    jr = renderError("请上传组织机构代码图片");
                }
                return jr;
            }
        } else {
            companyOld = companyService.selectById(company.getId());
            subOpenAccountPermitPath = company.getOpenAccountPermitPath().split("/");
            subOrganizationCodePath = company.getOrganizationCodePath().split("/");

            updateOpenAccountPermit = !subOpenAccountPermitPath[subOpenAccountPermitPath.length - 1].equals(companyOld.getOpenAccountPermitPath());
            updateOrganizationCode = !subOrganizationCodePath[subOrganizationCodePath.length - 1].equals(companyOld.getOrganizationCodePath());

            if (updateOpenAccountPermit && updateOrganizationCode) {
                openAccountPermit = files[0];
                organizationCode = files[1];
            } else if (updateOpenAccountPermit) {
                openAccountPermit = files[0];
            } else if (updateOrganizationCode) {
                organizationCode = files[1];
            }
        }

        // company对象属性完善
        /** 字段属性：id, company_name, address, post_code, phone_number, status, secret_key, deleted, created, short_name, company_type, contact, email, rank, company_vocation_id, user_id, internet_address, open_account_permit_path, organization_code_path */
        /** 需要属性：    company_name, address,            phone_number,         secret_key, deleted, created, short_name, company_type, contact, email,       company_vocation_id, user_id, internet_address, open_account_permit_path, organization_code_path */
        /** 必要属性：    company_name,                                                       deleted, created, short_name, company_type, contact, email,       company_vocation_id, user_id,                   open_account_permit_path, organization_code_path */
        company.setUserId(userId);
        company.setDeleted(0);
        company.setStatus(3);
        company.setCreated(TimeStamp.getCurrentTime().getDate());
        // 信息查重
        if (insertOrUpdate) {
            if (null != companyService.selectOne(new EntityWrapper<Company>().eq("company_name", company.getCompanyName()))
                    || null != companyService.selectOne(new EntityWrapper<Company>().eq("short_name", company.getShortName()))) {
                jr = renderError("该客户名或简称已被注册");
                return jr;
            }
        } else {
            if (!company.getCompanyName().equals(companyOld.getCompanyName()) && (null != companyService.selectOne(new EntityWrapper<Company>().eq("company_name", company.getCompanyName())))) {
                jr = renderError("该客户全称已被注册");
                return jr;
            } else if (!company.getShortName().equals(companyOld.getShortName()) && (null != companyService.selectOne(new EntityWrapper<Company>().eq("short_name", company.getShortName())))) {
                jr = renderError("该客户简称已被注册");
                return jr;
            }
        }

        // operator对象属性完善
        /** operator属性：login_name, password, real_name, phone_number, mobile, email */
        /** company 对应：                      contact,   phone_number,         email */
        operator.setLoginName(company.getOperatorLoginName());
        operator.setPassword(company.getOperatorPassword());
        operator.setRealName(company.getContact());
        operator.setPhoneNumber(company.getPhoneNumber());
        operator.setMobile(company.getOperatorMobile());
        operator.setEmail(company.getEmail());

        if (insertOrUpdate) {
            operator.setEnabled(DELETED_VALUE_OF_DELETE_OR_UPDATE);
            operator.setDeleted(DELETED_VALUE_OF_SELECT_OR_SAVE);
            operator.setRemark(company.getCompanyName() + " 运营");
            operator.setManagerId(userId);
            operator.setCreatorId(userId);
            operator.setCreated(TimeStamp.getCurrentTime().getDate());
            operator.setAdmin(1);
            // 获取入库密码&盐信息
            operator = userService.setEntryPassword(operator);
        } else {
            operatorOld = userService.selectOne(new EntityWrapper<User>().eq("company_id", company.getId()).eq("admin", 1));
            if (null == operatorOld) {
                jr = renderError("该客户对应系统信息尚未升级 请联系管理员");
                return jr;
            } else {
                // 保证当前页面不能修改/清空密码
                operator.setPassword(operatorOld.getPassword());
            }
            operator.setModifierId(userId);
            operator.setUpdated(TimeStamp.getCurrentTime().getDate());
        }
        // 信息查重
        if (insertOrUpdate) {
            if (null != userService.selectOne(new EntityWrapper<User>().eq("login_name", operator.getLoginName()))) {
                jr = renderError("该登录账号名称已存在");
                return jr;
            }
        } else {
            if (!operator.getLoginName().equals(operatorOld.getLoginName()) && (null != userService.selectOne(new EntityWrapper<User>().eq("login_name", operator.getLoginName())))) {
                jr = renderError("该登录账号名称已存在");
                return jr;
            }
        }

        // 证件图片上传
        // 路径创建
        boolean online = CompanyAddConfig.config.online();
        String path = "";
        if (online) {
            path = CompanyAddConfig.config.uploadFolder();
        } else {
            path = CompanyAddConfig.config.uploadAddress();
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String originalName = "";
        String suffixType = "";
        String imageFileName = "";
        File image = null;
        if (null != openAccountPermit && !openAccountPermit.isEmpty() && updateOpenAccountPermit) {
            try {
                originalName = openAccountPermit.getOriginalFilename();
                suffixType = originalName.substring(originalName.lastIndexOf("."));
            } catch (StringIndexOutOfBoundsException stre) {
                // 异常时使用默认后缀名 .png
                stre.printStackTrace();
                suffixType = ".png";
            }
            imageFileName = "openAccountPermit_" + new Date().getTime() + suffixType;
            image = new File(path, imageFileName);
            try {
                openAccountPermit.transferTo(image);
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("开户许可证件上传出错");
                return jr;
            }
            company.setOpenAccountPermitPath(imageFileName);
        } else {
            company.setOpenAccountPermitPath(companyOld.getOpenAccountPermitPath());
        }
        if (null != organizationCode && !organizationCode.isEmpty() && updateOrganizationCode) {
            try {
                originalName = organizationCode.getOriginalFilename();
                log.info("TESTPRINT:" + originalName);
                suffixType = originalName.substring(originalName.lastIndexOf("."));
            } catch (StringIndexOutOfBoundsException stre) {
                // 异常时使用默认后缀名 .png
                stre.printStackTrace();
                suffixType = ".png";
            }
            imageFileName = "organizationCode_" + new Date().getTime() + suffixType;
            image = new File(path, imageFileName);
            try {
                organizationCode.transferTo(image);
            } catch (Exception e) {
                e.printStackTrace();
                jr = renderError("组织机构代码证上传出错");
                return jr;
            }
            company.setOrganizationCodePath(imageFileName);
        } else {
            company.setOrganizationCodePath(companyOld.getOrganizationCodePath());
        }
        // 清除过时证件图片
        if (!insertOrUpdate) {
            if (null != openAccountPermit && !openAccountPermit.isEmpty() && updateOpenAccountPermit) {
                File lastOpenAccountPermit = new File(path + companyOld.getOpenAccountPermitPath());
                if (lastOpenAccountPermit.isFile() && lastOpenAccountPermit.exists()) {
                    lastOpenAccountPermit.delete();
                }
            }
            if (null != organizationCode && !organizationCode.isEmpty() && updateOrganizationCode) {
                File lastOrganizationCode = new File(path + companyOld.getOrganizationCodePath());
                if (lastOrganizationCode.isFile() && lastOrganizationCode.exists()) {
                    lastOrganizationCode.delete();
                }
            }
        }

        // 对象保存
        String trackingCompanyName = company.getCompanyName();
        String trackingLoginName = operator.getLoginName();

        if (insertOrUpdate) {
            try {
                companyService.insert(company);
                company = companyService.selectOne(new EntityWrapper<Company>().eq("company_name", trackingCompanyName));

                operator.setCompanyId(company.getId());
                userService.insert(operator);
                operator = userService.selectOne(new EntityWrapper<User>().eq("login_name", trackingLoginName));

                operatorRole.setRoleId(roleService.selectOne(new EntityWrapper<Role>().eq("role_identity", "operator")).getId());
                operatorRole.setUserId(operator.getId());
                userRoleService.insert(operatorRole);
            } catch (Exception e) {
                if (null == companyService.selectOne(new EntityWrapper<Company>().eq("company_name", trackingCompanyName))) {
                    jr = renderError("客户信息保存异常");
                } else if (null == userService.selectOne(new EntityWrapper<User>().eq("login_name", trackingLoginName))) {
                    jr = renderError("登录账号信息保存异常");
                    companyService.delete(new EntityWrapper<Company>().eq("company_name", trackingCompanyName));
                } else if (null == userRoleService.selectOne(
                        new EntityWrapper<UserRole>()
                                .eq("user_id", userService.selectOne(new EntityWrapper<User>().eq("login_name", trackingLoginName)).getId())
                                .eq("role_id", roleService.selectOne(new EntityWrapper<Role>().eq("role_identity", "operator")).getId())
                )) {
                    jr = renderError("登录账号角色权限信息初始化异常");
                    userService.delete(new EntityWrapper<User>().eq("login_name", trackingLoginName));
                    companyService.delete(new EntityWrapper<Company>().eq("company_name", trackingCompanyName));
                }
                return jr;
            }
        } else {
            try {
                companyService.updateById(company);
                operator.setId(operatorOld.getId());
                userService.updateById(operator);
            } catch (Exception e) {
                jr = renderError("客户信息更改异常");
                companyService.updateById(companyOld);
                userService.updateById(operatorOld);
                return jr;
            }
        }

        if (log.isDebugEnabled()) {
            log.info("saveCompany......");
            log.debug("saveCompany...param company:{}", company);
            log.debug("saveCompany...result jr:{}", jr);
        }

        jr = renderSuccess("保存成功");
        return jr;
    }

    /**
     * @title companyAddOrUpdate
     * @description TODO 客户新增/修改
     * @author xiaokun
     * @date 2017-06-14 20:14
     * @modifier
     * @remark
     * @version V2.0
     *
     * @param companyId
     * @return String
     * @throws
     */
    @RequiresPermissions(value = {AuthorityConstants.CompanyAndUser.PERMISSION_COMPANYLIST_SAVECOMPANY}, logical = Logical.OR)
    @RequestMapping("/companyAddOrUpdate")
    @SystemControllerLog(moduleName = {SYSTEM_MODULE}, description = "客户新增/修改")
    public String companyAddOrUpdate(Model model, @RequestParam(required = false, value = "companyId") Long companyId) {

        log.info("companyAddOrUpdate......");

        Company company;
        User operator;

        String lastOpenAccountPermitPath = "";
        String lastOrganizationCodePath = "";

        if (null == companyId) {
            company = new Company();
            operator = new User();
        } else {
            company = companyService.selectById(companyId);
            operator = userService.selectOne(new EntityWrapper<User>().eq("company_id", company.getId()));

            // 图片路径生成
            lastOpenAccountPermitPath = company.getOpenAccountPermitPath();
            lastOrganizationCodePath = company.getOrganizationCodePath();

            // 完整路径获取
            if (CompanyAddConfig.config.online()) {
                // 线上
                company.setOpenAccountPermitPath(CompanyAddConfig.config.ipPort()+CompanyAddConfig.config.uploadFolder()+company.getOpenAccountPermitPath());
                company.setOrganizationCodePath(CompanyAddConfig.config.ipPort()+CompanyAddConfig.config.uploadFolder()+company.getOrganizationCodePath());
            } else {
                // 线下
                company.setOpenAccountPermitPath(CompanyAddConfig.config.uploadAddress()+company.getOpenAccountPermitPath());
                company.setOrganizationCodePath(CompanyAddConfig.config.uploadAddress()+company.getOrganizationCodePath());
            }
        }

        List<CompanyVocation> companyVocationList = companyVocationService.selectList(null);

        model.addAttribute("company", company);
        model.addAttribute("operator", operator);
        model.addAttribute("companyVocationList", companyVocationList);

        if (log.isDebugEnabled()) {
            log.info("companyAddOrUpdate......");
            log.debug("companyAddOrUpdate...param companyId:{}", companyId);
            log.debug("companyAddOrUpdate...result company toString:{}", company.toString());
        }
        return "company/companyAdd";
    }

    public String companyIsNull(Company company) {
        String msg = null;
        if (null == company.getCompanyType()) {
            msg = "请选择客户类型";
        }else if (null == company.getCompanyName() || company.getCompanyName().equals("")) {
            msg = "请填写客户全称";
        } else if (null == company.getShortName() || company.getShortName().equals("")) {
            msg = "请填写客户简称";
        } else if (null == company.getCompanyVocationId()) {
            msg = "请选择所属行业";
        } else if (null == company.getContact() || company.getContact().equals("")) {
            msg = "请填写联系人";
        } else if (null == company.getPhoneNumber() || company.getPhoneNumber().equals("")) {
            msg = "请填写公司电话";
        } else if (null == company.getEmail() || company.getEmail().equals("")) {
            msg = "请填写联系邮箱";
        }
        else if (null == company.getOperatorMobile() || company.getOperatorMobile().equals("")) {
            msg = "请填写联系电话";
        } else if (null == company.getOperatorLoginName() || company.getOperatorLoginName().equals("")) {
            msg = "请填写登录账号";
        } else if (null == company.getOperatorPassword() || company.getOperatorPassword().equals("")) {
            msg = "请填写登录密码";
        }
        return msg;
    }
}
