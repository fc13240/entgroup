package com.entgroup.adms.conf;

/**
 * <p>
 * 权限常量类 按模块划分内部静态类(参考数据库sys_authority二级目录)，静态列表权限(参考数据库sys_authority权限值)方便调用
 * </p>
 *
 * @author xiaokun
 * @since 2017-03-15
 */
public class AuthorityConstants {
    /**
     * 广告位管理
     */
    public class AdSlot {
        public static final String VIEW_ADSLOT_SCREENADSLOT = "view:adSlot:screenAdSlot";
        //public static final String VIEW_ADSLOT_SCREENADSLOTIMG = "view:adSlot:screenAdSlotImg";
        public static final String PERMISSION_SCREENADSLOTIMG_SAVESCREENADSLOTRESULTS = "sys:screenAdSlotImg:saveScreenAdSlotResults";
        public static final String VIEW_ADSLOT_ADJUSTPRICE = "view:adSlot:adjustPrice";
        public static final String PERMISSION_ADJUSTPRICE_SAVEADJUSTPRICE = "sys:adjustPrice:saveAdjustPrice";
        public static final String VIEW_ADSLOT_EDITLABEL = "view:adSlot:editLabel";
        //public static final String VIEW_ADSLOT_EDITADSLOTLABEL = "view:adSlot:editAdSlotLabel";
        public static final String PERMISSION_EDITADSLOTLABEL_SAVEEDITADSLOTLABEL = "sys:editAdSlotLabel:saveEditAdSlotLabel";
    }

    /**
     * 客户用户管理
     */
    public class CompanyAndUser {
        // 客户管理
        public static final String VIEW_COMPANYANDUSER_COMPANYLIST = "view:companyAndUser:companyList";
        public static final String PERMISSION_COMPANYLIST_SAVECOMPANY = "sys:companyList:saveCompany";
        public static final String PERMISSION_COMPANYLIST_COMPANYDETAIL = "sys:companyList:getCompanyDetail";
        // public static final String PERMISSION_COMPANYLIST_DELETECOMPANY = "sys:companyList:deleteCompany";
        // 用户管理
        public static final String VIEW_COMPANYANDUSER_USERLIST = "view:companyAndUser:userList";
        public static final String PERMISSION_USERLIST_SAVEUSER = "sys:userList:saveUser";
        public static final String PERMISSION_USERLIST_USERDETAIL = "sys:userList:getUserDetail";
        // public static final String PERMISSION_USERLIST_DELETEUSER = "sys:userList:deleteUser";
    }

    /**
     * 角色权限管理
     */
    public class RoleAuthority {
        public static final String VIEW_ROLEAUTHORITY_ROLELIST = "view:roleAuthority:roleList";
        public static final String PERMISSION_ROLELIST_SAVEROLE = "sys:roleList:saveRole";
        public static final String PERMISSION_ROLELIST_ROLEDETAIL = "sys:roleList:getRoleDetail";
        // public static final String PERMISSION_ROLELIST_DELETEROLE = "sys:roleList:deleteRole";
    }

    /**
     * 个人中心
     */
    public class PersonalCenter {
        // 基本信息
        public static final String VIEW_PERSONALCENTER_USERDETAIL = "view:personalCenter:userDetail";
        public static final String PERMISSION_USERDETAIL_CHANGEPASSWORD = "sys:userDetail:changePassword";
        // 用户反馈
        public static final String VIEW_PERSONALCENTER_FEEDBACKLIST = "view:personalCenter:feedbackList";
        public static final String PERMISSION_FEEDBACKLIST_FEEDBACKDETAIL = "sys:feedbackList:getFeedbackDetail";
        public static final String PERMISSION_FEEDBACKLIST_ADDFEEDBACK = "sys:helpList:addFeedback";
        // public static final String PERMISSION_FEEDBACKLIST_DELETEFEEDBACK = "sys:feedbackList:deleteFeedback";
        // 帮助管理
        public static final String VIEW_PERSONALCENTER_HELPLIST = "view:personalCenter:helpList";
        public static final String PERMISSION_HELPLIST_SAVEHELP = "sys:helpList:saveHelp";
        public static final String PERMISSION_HELPLIST_HELPDETAIL = "sys:helpList:helpDetail";
        // public static final String PERMISSION_HELPLIST_DELETEHELP = "sys:helpList:deleteHelp";
        // 通知
        public static final String VIEW_PERSONALCENTER_NOTICELIST = "view:personalCenter:noticeList";
        public static final String PERMISSION_NOTICELIST_ADDNOTICE = "sys:noticeList:addNotice";
        public static final String PERMISSION_NOTICELIST_NOTICEDETAIL = "sys:noticeList:noticeDetail";
        // public static final String PERMISSION_NOTICELIST_DELETENOTICE = "sys:noticeList:deleteNotice";
    }

    /**
     * 订单管理
     */
    public class AdOrder {
        public static final String VIEW_ADORDER_ADORDERLIST = "view:adOrder:adOrderList";
        public static final String PERMISSION_ADORDERLIST_SAVEADORDER = "sys:adOrderList:saveAdOrder";
        //public static final String VIEW_ADORDER_ADORDERDETAIL = "view:adOrder:adOrderDetail";
        public static final String PERMISSION_ADORDERDETAIL_ADORDERADDETAIL = "sys:adOrderDetail:adOrderAdDetail";
        // edited by xiaokun on 2017-06-14 09:44 begin
        /**
         * 订单审核
          */
        public static final String VIEW_ADORDER_ADORDERVERIFY = "view:adOrder:adOrderVerify";
        // edited by xiaokun on 2017-06-14 09:44 end
    }

    /**
     * 数据统计
     */
    public class DataStatistics {
        public static final String VIEW_DATASTATISTICS_ADCOUNT = "view:dataStatistics:adCount";
        public static final String PERMISSION_ADCOUNT_EXCEL = "sys:adCount:adCountExcel";
        public static final String VIEW_DATASTATISTICS_PLATFORMCOUNT = "view:dataStatistics:platFormCount";
        public static final String PERMISSION_PLATFORMCOUNT_EXCEL = "sys:platFormCount:platformCountExcel";
    }

    /**
     * 广告管理
     */
    public class Ad {
        public static final String VIEW_AD_ADVERIFY = "view:ad:adVerify";
        public static final String PERMISSION_ADVERIFY_SAVEADVERIFYRESULT = "sys:adVerify:saveAdVerifyResult";
        public static final String VIEW_AD_ADLIST = "view:ad:adList";
        public static final String PERMISSION_ADLIST_ADDETAIL = "sys:adList:adDetail";
        public static final String VIEW_AD_ADSHELFLIST = "view:ad:adShelfList";
        //public static final String VIEW_ADSHELFLIST_ADSHELFONLIST = "view:ad:adShelfOnList";
        public static final String PERMISSION_ADSHELFLIST_ADSHELFON = "sys:adShelfOnList:adShelfOn";
        //public static final String VIEW_ADSHELFLIST_ADSHELFOFFLIST = "view:ad:adShelfOffList";
        public static final String PERMISSION_ADSHELFLIST_ADSHELFOFF = "sys:adShelfOffList:adShelfOff";
        public static final String VIEW_AD_ADCREATE = "view:ad:adCreate";
        public static final String PERMISSION_ADCREATE_SAVEADCREATERESULT = "sys:adCreate:saveAdCreateResult";
    }

    /* ———————————————————————— 以下为旧版本权限内容 ———————————————————————— */
    /**
     * 应用中心
     * SDK相关权限
     * Administrator
     *//*
    public class SDK {
        *//**
         * SDK下载
         *//*
        // 浏览当前发布版本
        public static final String PERMISSION_SDK_NOW       = "sys:sdk:now";
        // 查看历史版本
        public static final String PERMISSION_SDK_HISTORY   = "sys:sdk:history";
        // 上传SDK
        public static final String PERMISSION_SDK_UPLOAD    = "sys:sdk:upload";
        // 发布/取消发布SDK
        public static final String PERMISSION_SDK_PUBLISH   = "sys:sdk:publish";
        // 下载SDK
        public static final String PERMISSION_SDK_DOWNLOAD  = "sys:sdk:download";
    }

    *//**
     * 管理中心
     * 系统日志相关权限
     * Administrator
     *//*
    public class SysLog {
        *//**
         * 系统日志
         *//*
        // 系统日志列表
        public static final String PERMISSION_LIST = "sys:syslog:list";
    }

    *//**
     * 广告位管理
     * 广告位操作相关权限
     * Administrator
     *//*
    public class AdSlot {
        *//**
         * 筛选
         *//*
        // 广告位筛选
        public static final String PERMISSION_ADSLOTFLITE_FLITE     = "sys:adslotflite:flite";

        *//**
         * 调价
         *//*
        // 广告位调价
        public static final String PERMISSION_ADSLOTPRICE_CHANGE    = "sys:adslotprice:change";

        *//**
         * 加标签
         *//*
        // 广告位加标签
        public static final String PERMISSION_ADSLOTLABEL_ADD       = "sys:adslotlabel:add";
        // 广告位改标签
        public static final String PERMISSION_ADSLOTLABEL_UPDATE    = "sys:adslotlabel:update";
        // 广告位删标签
        public static final String PERMISSION_ADSLOTLABEL_DELETE    = "sys:adslotlabel:delete";
    }

    *//**②
     * 客户用户管理——客户管理
     * 客户(企业)管理相关权限
     * Administrator
     *//*
    public class Company {
        *//**
         * 客户管理
         *//*
        // 客户列表
        public static final String PERMISSION_COMPANY_LIST      = "sys:company:list";
        // 客户新增
        public static final String PERMISSION_COMPANY_ADD       = "sys:company:add";
        // 客户编辑
        public static final String PERMISSION_COMPANY_UPDATE    = "sys:company:update";
        // 客户删除
        public static final String PERMISSION_COMPANY_DELETE    = "sys:company:delete";
        // 客户启用/停用
        public static final String PERMISSION_COMPANY_ENABLED   = "sys:company:enabled";
    }

    *//**②
     * 客户用户管理——用户管理/我的用户
     * 用户管理相关权限
     * Administrator &/ User
     *//*
    public class User {
        *//**
         * 用户管理/用户列表——Administrator &/ User
         *//*
        *//* ——管理权限—— *//*
        // 用户启用/停用
        public static final String PERMISSION_USER_ENABLED  = "sys:user:enabled";

        *//* ——共有权限—— *//*
        // 查看列表/查看列表
        public static final String PERMISSION_USER_LIST     = "sys:user:list";
        // 用户新增/用户新增
        public static final String PERMISSION_USER_ADD      = "sys:user:add";
        // 用户编辑/用户编辑
        public static final String PERMISSION_USER_UPDATE   = "sys:user:update";
        // 用户删除/用户删除
        public static final String PERMISSION_USER_DELETE   = "sys:user:delete";
    }

    *//**
     * 广告管理/我的广告
     * 广告管理相关权限
     * Administrator &/ User
     *//*
    public class Ad {
        *//**
         * 广告审核/广告列表——Administrator &/ User
         *//*
        *//* ——管理权限—— *//*
        // 广告审核
        public static final String PERMISSION_ADLIST_CHECK  = "sys:adlist:check";

        *//* ——共有权限—— *//*
        // 查看列表/查看
        public static final String PERMISSION_ADLIST_LIST   = "sys:adlist:list";

        *//* ——用户权限—— *//*
        // 制作广告
        public static final String PERMISSION_ADLIST_CREATE = "sys:adlist:create";
        // 修改
        public static final String PERMISSION_ADLIST_UPDATE = "sys:adlist:update";
        // 删除
        public static final String PERMISSION_ADLIST_DELETE = "sys:adlist:delete";

        *//**
         * 投放与下架——Administrator
         *//*
        *//* ——管理权限—— *//*
        // 投放
        public static final String PERMISSION_ADSHELF_PUBLISH = "sys:adshelf:publish";
        // 下架
        public static final String PERMISSION_ADSHELF_SUPPRESS = "sys:adshelf:suppress";
    }

    *//**
     * 订单管理/我的订单
     * 订单管理相关权限
     * Administrator &/ User
     *//*
    public class Order {
        *//**
         * 订单列表/订单列表——Administrator &/ User
         *//*
        *//* ——管理权限—— *//*
        // 订单新增
        public static final String PERMISSION_ORDERLIST_ADD     = "sys:orderlist:add";
        // 订单编辑
        public static final String PERMISSION_ORDERLIST_UPDATE  = "sys:orderlist:update";
        // 订单删除
        public static final String PERMISSION_ORDERLIST_DELETE  = "sys:orderlist:delete";

        *//* ——共有权限—— *//*
        // 查看列表/查看列表
        public static final String PERMISSION_ORDERLIST_LIST    = "sys:orderlist:list";
        // 订单详情/订单详情
        public static final String PERMISSION_ORDERLIST_DETAIL  = "sys:orderlist:detail";
    }

    *//**
     * 数据统计/数据中心
     * 数据统计相关权限
     * Administrator & User
     *//*
    public class DataCenter {
        *//**
         * 基于广告/基于广告——Administrator & User
         *//*
        *//* ——共有权限—— *//*
        // 广告统计列表/广告统计列表
        public static final String PERMISSION_COUNTBYAD_LIST        = "sys:countbyad:list";
        // 广告统计筛选/广告统计筛选
        public static final String PERMISSION_COUNTBYAD_FLITE       = "sys:countbyad:flite";

        *//**
         * 基于视频平台/基于视频平台——Administrator & User
         *//*
        *//* ——共有权限—— *//*
        // 平台统计列表/平台统计列表
        public static final String PERMISSION_COUNTBYPLATFORM_LIST  = "sys:countbyplatform:list";
        // 平台统计筛选/平台统计筛选
        public static final String PERMISSION_COUNTBYPLATFORM_FLITE = "sys:countbyplatform:flite";
    }

    *//**
     * 个人中心/个人中心
     * 个人中心相关权限
     * Administrator &/ User
     *//*
    public class Personal {
        *//**
         * 基本信息/基本信息——Administrator & User
         *//*
        *//* ——共有权限—— *//*
        // 查看详情/查看详情
        public static final String PERMISSION_INFORMATION_DETAIL         = "sys:information:detail";
        // 修改密码/修改密码
        public static final String PERMISSION_INFORMATION_CHANGEPASSWORD = "sys:information:changepassword";

        *//**
         * 用户反馈——Administrator
         *//*
        *//* ——管理权限—— *//*
        // 反馈列表
        public static final String PERMISSION_USERFEEDBACK_LIST     = "sys:userfeedback:list";
        // 反馈删除
        public static final String PERMISSION_USERFEEDBACK_DELETE   = "sys:userfeedback:delete";
        // 反馈详情
        public static final String PERMISSION_USERFEEDBACK_DETAIL   = "sys:userfeedback:detail";

        *//**
         * 通知/系统通知——Administrator &/ User
         *//*
        *//* ——管理权限—— *//*
        // 新增通知
        public static final String PERMISSION_SYSNOTICE_ADD     = "sys:sysnotice:add";
        // 修改通知
        public static final String PERMISSION_SYSNOTICE_UPDATE  = "sys:sysnotice:update";
        // 删除通知
        public static final String PERMISSION_SYSNOTICE_DELETE  = "sys:sysnotice:delete";

        *//* ——共有权限—— *//*
        // 历史通知/历史通知
        public static final String PERMISSION_SYSNOTICE_HISTORY = "sys:sysnotice:history";
        // 查看详情/阅读通知
        public static final String PERMISSION_SYSNOTICE_DETAIL  = "sys:sysnotice:detail";

        *//* ——用户权限—— *//*
        // 清空通知(不显示 非删除)
        public static final String PERMISSION_SYSNOTICE_CLEAR   = "sys:sysnotice:clear";

        *//**
         * 帮助与反馈——Administrator &/ User
         *//*
        *//* ——用户权限—— *//*
        // 帮助
        public static final String PERMISSION_HELPANDFEEDBACK_HELPDETAIL    = "sys:helpandfeedback_helpdetail";
        // 反馈列表
        public static final String PERMISSION_HELPANDFEEDBACK_LISTFEEDBACK  = "sys:helpandfeedback_listfeedback";
        // 新增反馈
        public static final String PERMISSION_HELPANDFEEDBACK_ADDFEEDBACK   = "sys:helpandfeedback:addfeedback";
    }

    *//**
     * 角色与权限管理
     * 角色管理 赋权限
     * Administrator
     *//*
    public class RoleAndAuthority {
        *//**
         * 角色列表
         *//*
        *//* ——管理权限—— *//*
        // 查看列表
        public static final String PERMISSION_ROLELIST_LIST = "sys:rolelist:list";
        // 角色新增
        public static final String PERMISSION_ROLELIST_ADD = "sys:rolelist:add";
        // 角色编辑
        public static final String PERMISSION_ROLELIST_UPDATE = "sys:rolelist:update";
        // 角色删除
        public static final String PERMISSION_ROLELIST_DELETE = "sys:rolelist:delete";
        // 角色详情
        public static final String PERMISSION_ROLELIST_DETAIL = "sys:rolelist:detail";
        // 角色赋权限
        public static final String PERMISSION_ROLELIST_SETAUTHORITY = "sys:rolelist:setauthority";
    }*/
}