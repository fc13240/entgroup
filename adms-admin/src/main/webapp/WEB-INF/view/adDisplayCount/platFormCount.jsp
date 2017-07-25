<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%--V1.0 By  guofei--%>

<style type="text/css">
.canvas-de {
	overflow: hidden;
	margin: 30px auto 0;
	font-size: 16px;
	color: #666;
	bottom: -60px;
	padding-left: 30%;
}

.canvas-de li {
	margin-right: 30px;
	overflow: hidden;
	float: left;
}

.canvas-de .canvas-circle {
	margin-top: 2px;
	width: 18px;
	height: 18px;
	border-radius: 10px;
	border: 4px solid #00bcd4;
	display: inline-block;
	margin-right: 10px;
}
</style>
<input type="hidden" id="columnNo" column="platFormCount" />
<input type="hidden" id="titleSource" value="基于视频平台" />
<form action="${ctx}/dataStatistics/platFormCount" method="GET"
	id="queryForm" class="form demo_form">
	<div class="col-lg-12 main-box">
		<shiro:hasPermission name="authorityType:admin">
			<div class="form col-lg-2">
				<label class="form_label">客戶</label>
				<div class="fl select2-box select2-box-top">
					<select class="js-example-basic-single" id="company"
						name="companyId" tabindex="1">
						<option value="">全部</option>
						<c:forEach var="com" items="${companyList}" varStatus="status">
							<option value="${com.id}" ${(companyId eq
								com.id)?'selected="selected"':''}>${com.companyName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</shiro:hasPermission>
		<shiro:lacksPermission name="authorityType:admin">
			<input type="hidden" id="companyId" name="companyId"
				value="${companyId}">
		</shiro:lacksPermission>
		<div class="form col-lg-2">
			<label class="form_label">样式</label>
			<div class="fl select2-box select2-box-top">
				<shiro:hasPermission name="authorityType:admin">
					<select class="js-example-basic-single" id="adStyle"
						name="adStyleId" tabindex="1">
						<c:if test="${empty adStyleId}">
						<option value="">全部</option>
						</c:if>
						<c:forEach var="style" items="${adStyleList}" varStatus="status">
							<option value="${style.adStyleId}" ${(adStyleId eq
								style.adStyleId)?'selected="selected"':''}>${style.adStyleName}</option>
						</c:forEach>
					</select>
				</shiro:hasPermission>
				<shiro:lacksPermission name="authorityType:admin">
					<select class="js-example-basic-single" id="adStyle"
						name="adStyleId" tabindex="1">
						<option value="">全部</option>
						<c:forEach var="style" items="${adStyleList}" varStatus="status">
							<option value="${style.adStyleId}" ${(adStyleId eq
								style.adStyleId)?'selected="selected"':''}>${style.adStyleName}</option>
						</c:forEach>
					</select>
				</shiro:lacksPermission>
			</div>
		</div>
		<div class="form col-lg-2">
			<label class="form_label">广告</label>
			<div class="fl select2-box select2-box-top">
				<select class="js-example-basic-single" id="ad" name="adId"
					tabindex="2">
					<c:if test="${empty adId}">
					<option value="">全部</option>
					</c:if>
					<c:forEach var="ad" items="${adList}" varStatus="status">
							<option value="${ad.adId}" ${(adId eq
								ad.adId)?'selected="selected"':''}>${ad.adName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-lg-1 video-padding-b form" style="min-width:120px;">
			<button class="normal-btn form-box-btn" onclick="days.value='7'">确定</button>
		</div>
		<div class="col-lg-5 video-top-box form" style="min-width:582px;">
			<input type="hidden" id="days" name="days" value="${days}">
			<button ${(days
				eq 7)?'class="normal-btn form-box-btn form-top-txt blue-btn"
				':'class="normal-btn form-box-btn form-top-txt blue-btn light-grey-btn"
				'} onclick="days.value=7 ">近7日</button>
			<button ${(days
				eq 30)?'class="normal-btn form-box-btn form-top-txt blue-btn"
				':'class="normal-btn form-box-btn form-top-txt blue-btn light-grey-btn"
				'} onclick="days.value=30 ">近30日</button>
			<button ${(days
				eq 90)?'class="normal-btn form-box-btn form-top-txt blue-btn"
				':'class="normal-btn form-box-btn form-top-txt blue-btn light-grey-btn"
				'} onclick="days.value=90 ">近3个月</button>
			<input type="button"
				onclick="window.location.href=('${ctx}/dataStatistics/platformCountExcel?companyId=${companyId}&adId=${adId}&adStyleId=${adStyleId}&days=${days}')"
				value="导出数据"
				class="normal-btn form-box-btn form-top-txt fr btn-default">
		</div>
	</div>
</form>
<div class="col-lg-12 main-box">
	<p class="col-lg-6 canvas-title">曝光量/万</p>
	<p class="col-lg-6 canvas-title">曝光人数/万</p>
	<div class="box-body col-lg-6 border-right " id="showChart">
		<canvas id="pieChart1" style="height:200px"></canvas>
		<ul class="canvas-ul">
			<c:forEach items="${AdPlatCountList}" var="showcount">
				<li>${showcount.companyName}: <fmt:formatNumber type="number"
						value="${showcount.totalShowCount/10000}" maxFractionDigits="2" />
				</li>
			</c:forEach>
		</ul>
		<c:forEach items="${AdPlatCountList}" var="showcount">
			<ul class="canvas-de" id="collorShow"></ul>
		</c:forEach>
	</div>
	<div class="box-body col-lg-6" id="userChart">
		<canvas id="pieChart2" style="height:200px"></canvas>
		<ul class="canvas-ul">
			<c:forEach items="${AdPlatCountList}" var="usercount">
				<li>${usercount.companyName}:<fmt:formatNumber type="number"
						value="${usercount.totalUserCount/10000} " maxFractionDigits="2" />
				</li>
			</c:forEach>
		</ul>
		<c:forEach items="${AdPlatCountList}" var="usercount">
			<ul class="canvas-de" id="collorUser">
			</ul>
		</c:forEach>
	</div>
	<c:if test="${empty type}">
		<div class="table_wrapper">
			<table>
				<thead>
					<tr>
						<th>视频平台</th>
						<th>曝光量/万</th>
						<th>曝光人数/万</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty AdPlatCountList}">
							<tr>
								<td colspan="16">暂无数据</td>
							</tr>
						</c:when>

						<c:otherwise>
							<c:forEach items="${AdPlatCountList}" var="count">
								<tr>
									<td>${count.companyName}</td>
									<td><fmt:formatNumber type="number"
											value="${count.totalShowCount/10000}" maxFractionDigits="2" />
									</td>
									<td><fmt:formatNumber type="number"
											value="${count.totalUserCount/10000} " maxFractionDigits="2" />
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</c:if>
</div>
<c:if test="${empty type}">
	<jsp:include page="../../layouts/panel-pagination.jsp" />
</c:if>
<jsp:include page="../../layouts/panel-model.jsp" />
<script type="text/javascript"
	src="${ctx}/resources/view/dataStatistics/js/platFormCount.js"></script>
