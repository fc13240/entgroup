<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adShelf"/>
<input type="hidden" id="titleSource" value="投放与下架"/>
<form action="${ctx}/ad/adShelf" method="GET" id="queryForm" class="form demo_form">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="companyId" class="form_label">客户</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(companyId eq com.id)?'selected="selected"':''}>${com.companyName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form col-lg-2">
            <label for="styleId" class="form_label">广告样式</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="styleId" name="styleId" tabindex="2">
                    <option value="">全部</option>
                    <c:forEach var="sty" items="${adStyleList}" varStatus="status">
                        <option value="${sty.id}"
                            ${(styleId eq sty.id)?'selected="selected"':''}>${sty.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="adName" id="adName" placeholder="输入广告名称" value="${adName}"
                   class="fl normal-txt form-box-btn">
            <button class="normal-btn fl txt-btn form-box-btn">确定</button>
        </div>
        <div class="form col-lg-4">

        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>制作日期</th>
                    <th>客户</th>
                    <th>广告名称</th>
                    <th>广告样式</th>
                    <th>产品品类</th>
                    <th>所属订单</th>
                    <th>已投放</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty delivAdList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${delivAdList}" var="dto">
                            <tr>
                                <td>
                                    <fmt:formatDate value="${dto.created}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${dto.companyName}</td>
                                <td>${dto.adName}</td>
                                <td>${dto.styleName}</td>
                                <td>${dto.typeName}</td>
                                <td>
                                    <c:if test="${empty dto.orderId}">
                                        无
                                    </c:if>
                                    <c:if test="${not empty dto.orderId}">
                                        ${dto.orderId}
                                    </c:if>
                                </td>
                                <td>${dto.adSlotNum}</td>
                                <td class="btn-td" style="padding: 5px 0;">
                                    <c:if test="${empty dto.orderId}">
                                        <a type="button" style="margin-left: 0;margin-bottom: 3px;"
                                           class="normal-btn blue-btn unclick">投放</a>
                                    </c:if>
                                    <c:if test="${not empty dto.orderId}">
                                        <a class="normal-btn blue-btn" style="margin-left: 0;margin-bottom: 3px;"
                                           href="${ctx}/adSlot/adShelfOnList?adId=${dto.adId}&programLevelId=3&companyId=${companyId}&styleId=${styleId}&adName=${adName}&parentPageNum=${page.pageNum}&parentPageSize=${page.pageSize}">投放</a>
                                    </c:if>

                                    <c:if test="${empty dto.orderId}">
                                        <a class="normal-btn blue-btn btn-default "
                                           style="margin-left: 0;margin-right: 0">下架</a>
                                    </c:if>
                                    <c:if test="${not empty dto.orderId}">
                                        <a class="normal-btn blue-btn btn-default"
                                           href="${ctx}/adSlot/adShelfOffList?adId=${dto.adId}&companyId=${companyId}&styleId=${styleId}&adName=${adName}&parentPageNum=${page.pageNum}&parentPageSize=${page.pageSize}"
                                           style="margin-left: 0;margin-right: 0">下架</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>