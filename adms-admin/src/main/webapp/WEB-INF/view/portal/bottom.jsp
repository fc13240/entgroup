<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!--bottom-->
<div class="bottom clear">
    <ul class="fl">
        <li class="bottom_img">
            <img src="${ctx}/resources/2.0/portal/img/footer01.png" alt="">
        </li>
        <li class="bold"><a href="${ctx}/">艺恩</a></li>
        <li><a href="${ctx}/contact">联系我们</a></li>
        <li><a href="${ctx}/about">关于我们</a></li>
        <li><a href="${ctx}/news">新闻动态</a></li>
    </ul>
    <ul class="fl">
        <li class="bottom_img">
            <img src="${ctx}/resources/2.0/portal/img/footer02.png" alt="">
        </li>
        <li class="bold"><a href="#">客户服务</a></li>
        <li><a href="#">使用条款</a></li>
        <li><a href="#">隐私政策</a></li>
        <li><a href="#">帮助中心</a></li>
    </ul>
    <ul class="fl">
        <li class="bottom_img">
            <img src="${ctx}/resources/2.0/portal/img/footer03.png" alt="">
        </li>
        <li class="bold"><a href="#">商务合作</a></li>
        <li><a href="#">渠道合作</a></li>
        <li><a href="#">媒体合作</a></li>
    </ul>
    <ul class="fl">
        <li class="bottom_img">
            <img src="${ctx}/resources/2.0/portal/img/footer04.png" alt="">
        </li>
        <li class="bold"><a href="#">关注我们</a></li>
        <li><a href="#">新浪微博</a></li>
        <li><a href="#">官方微信</a></li>
        <li><a href="#">官方贴吧</a></li>
    </ul>
</div>
<div class="copyright">Copyright 2015 艺恩 EntGroup Inc. 北京 All rights reserved. 京ICP备10031103号-3.</div>
<!--bottom end-->