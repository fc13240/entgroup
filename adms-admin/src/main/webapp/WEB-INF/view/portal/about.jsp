<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="decorator" content="about" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0">
<title>小白世纪－关于我们</title>
<link type="image/x-icon" href="${ctx}/resources/img/icon-20160128.ico" rel="shortcut icon">
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="/css/lib/fontello-ie7.min.css?1426762594" /><![endif]-->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/2.0/portal/style/about.css" />
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/2.0/portal/script/public.js"></script>
<style>
	body{height:auto;}
	.line_bottom{border-top:1px solid #eeeeee;}
	@media screen and (min-device-width: 1024px) {
	.absolute_bottom{position:absolute;bottom:0;left:0;right:0;}
	}
</style>
</head>
<body>
<input type="hidden" id="columnNo" column="top_1" />
<jsp:include page="top.jsp" />
<div class='about_content'>
	<div class='about_title'>
		<span style="font-size:16px" id="font_3">关于我们 <span style="color:#FECE00">/</span><span style="color:#2e3192">About us</span>
	</div>
	
	<div class='about_intro'>
		<ul class='clear'>
			<li class='l1'><img src="${ctx}/resources/np/image/logo-xb.jpg" width="150" height="150"></li>
			<li class='l2'>
				北京小白世纪网络科技有限公司于2014年7月成立，是一家致力于视频内容搜索的CBI（基于内容的互动）科技型创新公司；
								公司合伙人均来自清华大学、哈工大、华中科大、武汉大学、北航、多伦多大学等国内外著名高校；
								核心员工来自于阿里巴巴、华为、中国电信、搜狗等国内著名企业。公司依托清华大学技术基础，研发视频搜索平台，
								并利用大数据技术实现海量视频的分析与处理功能，从而实现直播与点播视频的内容、广告和营销的互动。
								凭借独创的视频内容搜索专利技术北京小白世纪成功加入T2O（中国）实验室；
								并荣获国际电视联盟（ITVU）“2015年度最佳视频搜索技术创新奖”。
								小白世纪将与百视通、华数传媒、国广星空、新疆广电等战略合作伙伴共同努力，
								继续以全面的技术创新，将视频内容搜索技术应用于全球更多的平台。
			</li>
		</ul>
	</div>
	
</div>
<div class='absolute_bottom line_bottom'>
	<jsp:include page="bottom.jsp" />
</div>
</body>
</html>
