<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style type="text/css">

.forbidden{
	margin-top: 100px;
}

#sys_error * {
	color: #666;
	text-align: center;
	font-family: Helvetica, 'microsoft yahei', Arial, sans-serif;
	margin: 0;
	width: 800px;
	margin: auto;
	font-size: 14px;
}

#sys_error h1 {
	font-size: 56px;
	line-height: 100px;
	font-weight: normal;
	color: #456;
}

#sys_error h2 {
	font-size: 24px;
	color: #666;
	line-height: 1.5em;
}

#sys_error h3 {
	color: #456;
	font-size: 20px;
	font-weight: normal;
	line-height: 28px;
}

#sys_error hr {
	margin: 18px 116px;
	border: 0;
	border-top: 1px solid #EEE;
	border-bottom: 1px solid white;
}

#sys_error a {
	color: #17bc9b;
	text-decoration: none;
}
</style>
<head>
<title>403 - 用户权限不足</title>
</head>
<input type="hidden" id="columnNo" column="menuAdminIndex"/>
<div class="right_cont forbidden">
	<div id="sys_error">
		<h1>系统繁忙</h1>
		<h3>${errormessage}</h3>
		<hr />
		<p>
		系统异常,请联系管理员. <a href="<c:url value="/"/>">返回首页</a>
		</p>
	</div>
</div>