<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	response.setStatus(200);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>你所访问的页面不存在 (404)</title>
<style type="text/css">
body {
	color: #666;
	text-align: center;
	font-family: Helvetica, 'microsoft yahei', Arial, sans-serif;
	margin: 0;
	width: 800px;
	margin: auto;
	font-size: 14px;
}

h1 {
	font-size: 56px;
	line-height: 100px;
	font-weight: normal;
	color: #456;
}

h2 {
	font-size: 24px;
	color: #666;
	line-height: 1.5em;
}

h3 {
	color: #456;
	font-size: 20px;
	font-weight: normal;
	line-height: 28px;
}

hr {
	margin: 18px 0;
	border: 0;
	border-top: 1px solid #EEE;
	border-bottom: 1px solid white;
}

a {
	color: #17bc9b;
	text-decoration: none;
} 
</style>
</head>
<body>
<input type="hidden" id="columnNo" column="menuAdminIndex"/>
	<h1>404</h1>
	<h3>你所访问的页面不存在.</h3>
	<hr />
	<p>
		你可能输入了不存在的URL地址，或者该资源已经被删除， <a href="<c:url value="/"/>">返回首页</a>
</body>
</html>
