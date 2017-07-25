<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="decorator" content="contact" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0">
<title>小白世纪－联系我们</title>
<link type="image/x-icon" href="${ctx}/resources/img/icon-20160128.ico" rel="shortcut icon">
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="/css/lib/fontello-ie7.min.css?1426762594" /><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/contact.css" />
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/public.js"></script>
<style>
	.line_bottom{border-top:1px solid #eeeeee; margin-top:20px;}
</style>
</head>
<body>
<input type="hidden" id="columnNo" column="top_1" />
<jsp:include page="top.jsp" />
	<div align="center" style="width:100%; height:1070px; margin-left:auto; margin-right:auto; margin-top:100px">
  <div style="margin-bottom:30px"><span style="font-size:16px" id="font_3">联系我们 <span style="color:#FECE00">/</span><span style="color:#2e3192"> Contact us</span></div>
  <div class='contact_content'>
  		<ul class='clear'>
  			<li class='l1'><img src="${ctx}/resources/np/image/photo.jpg" width="300" height="193"></li>
  			<li class='l2'>
  				<p style="font-size:18px">北京小白世纪网络科技有限公司</p>
  				<p>电话：010-82711656</p>
  				<p>地址：北京市海淀区竹溪园小区37-1号</p>
  				<p>网址：www.xiaobaishiji.com</p>
  				<p>邮箱：duqiang@xiaobaishiji.com</p>
  			</li>
  		</ul>
  </div>
  
  <div class='title_map'>
  		<span style="font-size:16px" id="font_3">地图 <span style="color:#FECE00">/</span><span style="color:#2e3192"> Map</span>
  </div>
		   <!--引用百度地图API-->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zl5ylAdESUszN4d0mqqGBXZX"></script>
    <!--百度地图容器-->
    <div id="map"></div>

  <script type="text/javascript" src="${ctx}/resources/np/js/baidumap.js"></script>
  <!--百度地图结束-->
<div class="line_bottom">
	<jsp:include page="bottom.jsp" />
</div>
</body>

</html>
