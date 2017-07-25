<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="navbar-header">
    <b><a id="title" class="navbar-brand "
          style="color:#FFF;font-size:20px;padding-left: 280px;font-weight:normal;"></a></b>
    <div class="fr-cell" style="height: 26px;margin-top: 14px;line-height: 0px;text-align:left;padding-left: 20px;"
         hidden="hidden" id="titleButtonDiv" >
        <button type="button" id="titleButton" class="header-btn">新增通知</button>
    </div>
</div>
<div class="navbar-right">
    <div class="fr-cell" style="height: 26px;margin-top: 14px;line-height: 0px;text-align:left;padding-left: 20px;">
        <a href="${ctx}/logout">
            <button class="header-btn-white">退出</button>
        </a>
    </div>
    <shiro:user>
        <div class="fr-cell" style="border-left: 0;padding-right: 20px;text-align:right;width: 300px;">
            <img src="${ctx}/resources/images/icon-user.png" class="user-icon"><span
                class="user-detail"><shiro:principal property="loginName"/></span>
        </div>
    </shiro:user>
    <!-- 超级管理员专有标签 -->
    <shiro:hasRole name="administrator">
        <%--<div class="fr-cell" style="border-left: 0;text-align:right">
            <span class="user-detail"><shiro:principal property="realName"/></span>
        </div>--%>
    </shiro:hasRole>
</div>
