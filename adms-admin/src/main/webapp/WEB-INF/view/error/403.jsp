<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">

.forbidden{
	margin-top: 100px;
}

#forbidden_403 * {
	color: #666;
	text-align: center;
	font-family: Helvetica, 'microsoft yahei', Arial, sans-serif;
	margin: 0;
	width: 800px;
	margin: auto;
	font-size: 14px;
}

#forbidden_403 h1 {
	font-size: 56px;
	line-height: 100px;
	font-weight: normal;
	color: #456;
}

#forbidden_403 h2 {
	font-size: 24px;
	color: #666;
	line-height: 1.5em;
}

#forbidden_403 h3 {
	color: #456;
	font-size: 20px;
	font-weight: normal;
	line-height: 28px;
}

#forbidden_403 hr {
	margin: 18px 0;
	border: 0;
	border-top: 1px solid #EEE;
	border-bottom: 1px solid white;
}

#forbidden_403 a {
	color: #17bc9b;
	text-decoration: none;
}
</style>
<head>
<title>403 - 用户权限不足</title>
</head>
<input type="hidden" id="columnNo" column="menuAdminIndex"/>
<div class="right_cont forbidden">
	<div id="forbidden_403">
		<h1>403</h1>
		<h3>用户权限不足</h3>
		<hr />
		<p>
		你可能没有权限访问该资源,请联系管理员开通. <a href="<c:url value="/"/>">返回首页</a>
		</p>
	</div>
</div>