<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- LOGO展示 -->
<div class="nav_header">
    <a class="header_logo"><img src="${ctx}/resources/images/logo.png"></a>
    <div class="header_meta">v1.0<span>&nbsp;&middot;&nbsp;<a >艺恩</a></span>
    </div>
</div>
<%--艺恩V2.0菜单 begin--%>
<%--SuperAdministrator--%>
<shiro:hasRole name="super_administrator">
    <div class="nav_set js-nav_set_2">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-03.png" class="nav-icon">用户管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:companyAndUser:userList">
                <a id="userList" class="nav_link" href="${ctx}/user/userList">用户列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_5">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-06.png" class="nav-icon">角色与权限管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:roleAuthority:roleList">
                <a id="roleList" class="nav_link" href="${ctx}/role/roleList">角色列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_6">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:personalCenter:userDetail">
                <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:feedbackList">
                <a id="feedbackList" class="nav_link" href="${ctx}/feedback/feedbackList">用户反馈</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:helpList">
                <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助管理</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:noticeList">
                <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">通知</a>
            </shiro:hasPermission>
        </div>
    </div>
</shiro:hasRole>
<%--Administrator--%>
<shiro:hasRole name="administrator">
    <div class="nav_set js-nav_set_2">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-03.png" class="nav-icon">用户管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:companyAndUser:userList">
                <a id="userList" class="nav_link" href="${ctx}/user/userList">用户列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-01.png" class="nav-icon">广告位管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:adSlot:adjustPrice">
                <a id="adjustPrice" class="nav_link" href="${ctx}/adSlot/adjustPrice">价格管理</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_6">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:personalCenter:userDetail">
                <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:feedbackList">
                <a id="feedbackList" class="nav_link" href="${ctx}/feedback/feedbackList">用户反馈</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:helpList">
                <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助管理</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:noticeList">
                <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">通知</a>
            </shiro:hasPermission>
        </div>
    </div>
</shiro:hasRole>
<%--MainOperatorADSlot--%>
<shiro:hasRole name="main_operator_adSlot">
    <div class="nav_set">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-01.png" class="nav-icon">广告位管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:adSlot:screenAdSlot">
                <a id="screenAdSlot" class="nav_link" href="${ctx}/adSlot/screenAdSlot">筛选</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:adSlot:editLabel">
                <a id="editLabel" class="nav_link" href="${ctx}/adSlot/editLabel">加标签</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_6">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:personalCenter:userDetail">
                <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:feedbackList">
                <a id="feedbackList" class="nav_link" href="${ctx}/feedback/feedbackList">用户反馈</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:helpList">
                <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助管理</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:noticeList">
                <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">通知</a>
            </shiro:hasPermission>
        </div>
    </div>
</shiro:hasRole>
<%--MainOperatorCompany--%>
<shiro:hasRole name="main_operator_company">
    <div class="nav_set js-nav_set_2">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-03.png" class="nav-icon">客户管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:companyAndUser:companyList">
                <a id="companyList" class="nav_link" href="${ctx}/company/companyList">客户列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_3">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-04.png" class="nav-icon">订单管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:adOrder:adOrderList">
                <a id="adOrderList" class="nav_link" href="${ctx}/order/adOrderList">订单审核</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_4">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-05.png" class="nav-icon">数据中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:dataStatistics:adCount">
                <a id="adAdminCountList" class="nav_link" href="${ctx}/adAdminDisplayCount/adAdminCountList">数据统计</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_6">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:personalCenter:userDetail">
                <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:feedbackList">
                <a id="feedbackList" class="nav_link" href="${ctx}/feedback/feedbackList">用户反馈</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:helpList">
                <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助管理</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:noticeList">
                <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">通知</a>
            </shiro:hasPermission>
        </div>
    </div>
</shiro:hasRole>
<%--Operator--%>
<shiro:hasRole name="operator">
    <div class="nav_set">
        <div class="nav_children">
            <a id="home" class="nav_link" href="${ctx}/home"><b>首页</b></a>
        </div>
    </div>
    <div class="nav_set js-nav_set_3">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-04.png" class="nav-icon">订单管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:adOrder:adOrderList">
                <a id="adOrderList" class="nav_link" href="${ctx}/order/adOrderList">订单列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_1">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-02.png" class="nav-icon">广告管理</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:ad:adList">
                <a id="adList" class="nav_link" href="${ctx}/ad/adList">广告列表</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_4">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-05.png" class="nav-icon">数据中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:dataStatistics:adCount">
                <a id="adCountList" class="nav_link" href="${ctx}/adDisplayCount/adCountList">数据统计</a>
            </shiro:hasPermission>
        </div>
    </div>
    <div class="nav_set js-nav_set_6">
        <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
        <div class="nav_children">
            <shiro:hasPermission name="view:personalCenter:userDetail">
                <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:noticeList">
                <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">系统通知</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="view:personalCenter:helpList">
                <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助与反馈</a>
            </shiro:hasPermission>
        </div>
    </div>
</shiro:hasRole>
<%--艺恩V2.0菜单  end --%>

<%--
&lt;%&ndash;管理菜单&ndash;%&gt;
<shiro:hasPermission name="authorityType:admin">
    <!--广告位管理-->
    <shiro:hasPermission name="module:adSlot:view">
        <div class="nav_set">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-01.png" class="nav-icon">广告位管理</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:adSlot:screenAdSlot">
                    <a id="screenAdSlot" class="nav_link" href="${ctx}/adSlot/screenAdSlot">筛选</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:adSlot:adjustPrice">
                    <a id="adjustPrice" class="nav_link" href="${ctx}/adSlot/adjustPrice">调价</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:adSlot:editLabel">
                    <a id="editLabel" class="nav_link" href="${ctx}/adSlot/editLabel">加标签</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--广告管理-->
    <shiro:hasPermission name="module:ad:view">
        <div class="nav_set js-nav_set_1">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-02.png" class="nav-icon">广告管理</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:ad:adVerify">
                    <a id="adVerify" class="nav_link" href="${ctx}/ad/adVerify">审核</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:ad:adShelfList">
                    <a id="adShelf" class="nav_link" href="${ctx}/ad/adShelf">投放与下架</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--客户用户管理-->
    <shiro:hasPermission name="module:companyAndUser:view">
        <div class="nav_set js-nav_set_2">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-03.png" class="nav-icon">客户用户管理</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:companyAndUser:companyList">
                    <a id="companyList" class="nav_link" href="${ctx}/company/companyList">客户管理</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:companyAndUser:userList">
                    <a id="userList" class="nav_link" href="${ctx}/user/userList">用户管理</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--订单管理-->
    <shiro:hasPermission name="module:adOrder:view">
        <div class="nav_set js-nav_set_3">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-04.png" class="nav-icon">订单管理</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:adOrder:adOrderList">
                    <a id="adOrderList" class="nav_link" href="${ctx}/order/adOrderList">订单列表</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--数据统计-->
    <shiro:hasPermission name="module:dataStatistics:view">
        <div class="nav_set js-nav_set_4">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-05.png" class="nav-icon">数据统计</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:dataStatistics:adCount">
                    <a id="adAdminCountList" class="nav_link" href="${ctx}/adAdminDisplayCount/adAdminCountList">数据统计</a>
                </shiro:hasPermission>
                &lt;%&ndash;<shiro:hasPermission name="view:dataStatistics:platformCount">&ndash;%&gt;
                    &lt;%&ndash;<a id="platFormCount" class="nav_link" href="${ctx}/dataStatistics/platFormCount">基于视频平台</a>&ndash;%&gt;
                &lt;%&ndash;</shiro:hasPermission>&ndash;%&gt;
            </div>
        </div>
    </shiro:hasPermission>

    <!--角色与权限管理-->
    <shiro:hasPermission name="module:roleAuthority:view">
        <div class="nav_set js-nav_set_5">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-06.png" class="nav-icon">角色与权限管理</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:roleAuthority:roleList">
                    <a id="roleList" class="nav_link" href="${ctx}/role/roleList">角色列表</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--个人中心-->
    <shiro:hasPermission name="module:personalCenter:view">
        <div class="nav_set js-nav_set_6">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:personalCenter:userDetail">
                    <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:personalCenter:feedbackList">
                    <a id="feedbackList" class="nav_link" href="${ctx}/feedback/feedbackList">用户反馈</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:personalCenter:helpList">
                    <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助管理</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:personalCenter:noticeList">
                    <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">通知</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>
</shiro:hasPermission>

&lt;%&ndash;========================================================================  管理/用户 菜单分界线  ========================================================================&ndash;%&gt;

&lt;%&ndash;用户菜单&ndash;%&gt;
<shiro:lacksPermission name="authorityType:admin">
    <!--我的订单-->
    <shiro:hasPermission name="module:adOrder:view">
        <div class="nav_set js-nav_set_3">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-04.png" class="nav-icon">我的订单</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:adOrder:adOrderList">
                    <a  id="adOrderList" class="nav_link" href="${ctx}/order/adOrderList">订单列表</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--我的广告-->
    <shiro:hasPermission name="module:ad:view">
        <div class="nav_set js-nav_set_1">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-02.png" class="nav-icon">我的广告</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:ad:adList">
                    <a id="adList" class="nav_link" href="${ctx}/ad/adList">广告列表</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--数据中心-->
    <shiro:hasPermission name="module:dataStatistics:view">
        <div class="nav_set js-nav_set_4">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-05.png" class="nav-icon">数据中心</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:dataStatistics:adCount">
                    <a id="adCountList" class="nav_link" href="${ctx}/adDisplayCount/adCountList">数据统计</a>
                </shiro:hasPermission>
                &lt;%&ndash;<shiro:hasPermission name="view:dataStatistics:platformCount">&ndash;%&gt;
                    &lt;%&ndash;<a id="platFormCount" class="nav_link" href="${ctx}/dataStatistics/platFormCount">基于视频平台</a>&ndash;%&gt;
                &lt;%&ndash;</shiro:hasPermission>&ndash;%&gt;
            </div>
        </div>
    </shiro:hasPermission>

    <!--我的用户-->
    <shiro:hasPermission name="module:companyAndUser:view">
        <div class="nav_set js-nav_set_2">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-03.png" class="nav-icon">我的用户</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:companyAndUser:userList">
                    <a id="userList" class="nav_link" href="${ctx}/user/userList">用户列表</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>

    <!--个人中心-->
    <shiro:hasPermission name="module:personalCenter:view">
        <div class="nav_set js-nav_set_6">
            <h4 class="nav_heading"><img src="${ctx}/resources/images/icon-07.png" class="nav-icon">个人中心</h4>
            <div class="nav_children">
                <shiro:hasPermission name="view:personalCenter:userDetail">
                    <a id="userDetail" class="nav_link" href="${ctx}/user/userDetail">基本信息</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:personalCenter:noticeList">
                    <a id="noticeList" class="nav_link" href="${ctx}/notice/noticeList">系统通知</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="view:personalCenter:helpList">
                    <a id="helpList" class="nav_link" href="${ctx}/help/helpList">帮助与反馈</a>
                </shiro:hasPermission>
            </div>
        </div>
    </shiro:hasPermission>
</shiro:lacksPermission>
--%>
