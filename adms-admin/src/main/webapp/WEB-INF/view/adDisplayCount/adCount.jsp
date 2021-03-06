<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<input type="hidden" id="columnNo" column="adCount" />
<input type="hidden" id="titleSource" value="基于广告"
	style="display: none;" />
<form action="${ctx}/dataStatistics/adCount" method="GET" id="queryForm"
	class="form demo_form">
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
		<div class="col-lg-5 video-top-box form" style="min-width:480px;">
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
			<button class="normal-btn form-box-btn form-top-txt fr btn-default">
				<a
					href="${ctx}/dataStatistics/adCountExcel?companyId=${companyId}&adId=${adId}&adStyleId=${adStyleId}&days=${days}">导出数据</a>
			</button>
		</div>
	</div>
</form>
<div class="grey-border-bottom ad-canvas-p">
	<span  class="red-font">总曝光量：</span><span><fmt:formatNumber
			type="number" value="${sumShowCount/10000} " maxFractionDigits="2" />/万</span>
	<span  class="blue-font">总曝光人数：</span><span><fmt:formatNumber
			type="number" value="${sumUserCount/10000} " maxFractionDigits="2" />/万</span>
			<input type="hidden" id="sumShowCount" name="sumShowCount" value="${sumShowCount}">
			<input type="hidden" id="sumUserCount" name="sumUserCount" value="${sumUserCount}">
</div>

<div class="col-lg-12 main-box">
	<div class="box-body">
		<div class="box-body">
			<div id="line-chart" style="height: 400px;"></div>
		</div>
	</div>
	<c:if test="${empty type}">
		<div class="table_wrapper">
			<table>
				<thead>
					<tr>
						<th>广告名称</th>
						<th>广告样式</th>
						<th>曝光量/万</th>
						<th>曝光人数/万</th>
					</tr>
				</thead>
				<tbody>

					<c:choose>

						<c:when test="${empty adDisplayCountList}">
							<tr>
								<td colspan="16">暂无数据</td>
							</tr>
						</c:when>

						<c:otherwise>
							<c:forEach items="${adDisplayCountList}" var="adcount">
								<tr>
									<td>${adcount.adName}</td>
									<td>${adcount.adStyleName}</td>
									<td><fmt:formatNumber type="number"
											value="${adcount.showCount/10000} " maxFractionDigits="2" />
									</td>
									<td><fmt:formatNumber type="number"
											value="${adcount.userCount/10000} " maxFractionDigits="2" />
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
	src="${ctx}/resources/view/dataStatistics/js/adCount.js"></script>