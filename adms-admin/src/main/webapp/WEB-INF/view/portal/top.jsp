<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var ADMS = {
		ctx: '${pageContext.request.contextPath}',
		userId: '${loginUser.id}',
		userRealName: '${loginUser.realName}'
	};
</script>
<div class="top">
    <div class="wrap">
        <div class="top-logo" onclick='window.open("${ctx}/","_parent")'></div>
        <div class="top-menu">
            <ul id="top_menu_ul">
                <li id="top_menu_1"><a target="_parent" href="${ctx}/">首页</a></li>
                <li id="top_menu_2" active><a target="_parent" href="${ctx}/product">产品介绍</a></li>
                <li id="top_menu_5"><a target="_parent" href="${ctx}/news">新闻动态</a></li>
                <li id="top_menu_4" class="login log-reg">
                	<shiro:authenticated>
                	<a target="_parent" href="${ctx}/home">进入后台</a><br/>
                	</shiro:authenticated>
                	<shiro:notAuthenticated>
	                <a target="_parent" href="${ctx}/login">登录</a>
                	</shiro:notAuthenticated>
	            </li>
                <!-- 
                <li id="top_menu_0" class="register log-reg"><a href="javascript:;">注册</a></li>
                 -->
            </ul>
        </div>
        <div class="line">
            <span></span>
            <span></span>
            <span></span>
        </div>
        <ul class="nav-list">
            <li><a target="_parent" href="${ctx}/">首页</a></li>
            <li><a target="_parent" href="${ctx}/product">产品介绍</a></li>
            <li><a target="_parent" href="${ctx}/news">新闻动态</a></li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        /**
         * menu效果
         */
        var arr = $("#columnNo").attr("column") && $("#columnNo").attr("column").split('_');
        arr = arr || [];
        $('#top_menu_ul').children('li').removeAttr("active");
        $("#top_menu_" + arr[1]).attr('active', '');
    });
</script>