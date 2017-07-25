<%@ page contentType="text/html;charset=UTF-8" session="false" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException" %>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>500 - 系统内部错误</title>
    <style type="text/css">
        .syserror {
            margin-top: 100px;
        }

        #syserror_500 * {
            color: #666;
            text-align: center;
            font-family: Helvetica, 'microsoft yahei', Arial, sans-serif;
            margin: 0;
            width: 800px;
            margin: auto;
            font-size: 14px;
        }

        #syserror_500 h1 {
            font-size: 56px;
            line-height: 100px;
            font-weight: normal;
            color: #456;
        }

        #syserror_500 h2 {
            font-size: 24px;
            color: #666;
            line-height: 1.5em;
        }

        #syserror_500 h3 {
            color: #456;
            font-size: 20px;
            font-weight: normal;
            line-height: 28px;
        }

        #syserror_500 hr {
            margin: 18px 0;
            border: 0;
            border-top: 1px solid #EEE;
            border-bottom: 1px solid white;
        }

        #syserror_500 a {
            color: #17bc9b;
            text-decoration: none;
        }
    </style>
</head>
<input type="hidden" id="columnNo" column="menuAdminIndex"/>
<div class="right_cont syserror">
    <div id="syserror_500">
        <h1>500</h1>
        <h3>系统发生内部错误</h3>
        <hr/>
        <p>
            系统发生内部错误,请联系管理员. <a href="<c:url value="/"/>">返回首页</a> 或 <a href="javascript:void(0)"
                                                                        onclick="location.reload()">刷新</a>
        </p>
    </div>
</div>
