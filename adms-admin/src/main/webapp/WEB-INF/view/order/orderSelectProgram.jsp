<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="orderSelectProgram"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="titleSource" value="新建订单-选择节目"/>
<%--<div class="form col-lg-2">--%>
<%--<label for="companyId" class="form_label">客户</label>--%>
<%--<div class="fl select2-box select2-box-top">--%>
<%--<select class="js-example-basic-single" id="companyId"--%>
<%--name="companyId" tabindex="1">--%>
<%--<option value="">全部</option>--%>
<%--<c:forEach var="com" items="${companyList}" varStatus="status">--%>
<%--<option value="${com.id}" ${(companyId eq--%>
<%--com.id)?'selected="selected"':''}>${com.companyName}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<%--</div>--%>
<%--</div>--%>
<div class="col-lg-12 main-box ">
    <button id="nextStep" type="button" class="normal-btn white-btn fr order-adjust-mr">下一步</button>
    <button id="selection" type="button" class="normal-btn white-btn fr">确认选择</button>
    <button type="button" class="normal-btn white-btn fr" onclick="goBack()">上一步</button>
</div>
<form id="queryForm" action="${ctx}/order/orderSelectProgram" method="GET"
      class="form demo_form">
    <input type="hidden" id="adOrderId" name="adOrderId" value="${adOrderId}">
    <div class="main-box col-lg-12">
        <nav class="order-bar-box">
            <div class="order-bar-width">
                <dl class="order-bar">
                    <dt>视频平台</dt>
                    <input type="hidden" id="companyIds" name="companyIds" value="${companyIds}">
                    <c:forEach var="company" items="${companyList}" varStatus="status">
                        <dd class="${(company.selected)?'order-checked':''}">
                            <input type="checkbox" class="order-inp-vid company" value="${company.id}"
                                ${(company.selected)?'checked="checked"':''}>
                            <i class="order-bar-check">${company.shortName}</i>
                        </dd>
                    </c:forEach>
                </dl>
            </div>
            <div class="">
                <dl class="order-bar">
                    <dt>节目类型</dt>
                    <input type="hidden" id="programTypeIds" name="programTypeIds" value="${programTypeIds}">
                    <c:forEach var="type" items="${programTypeList}" varStatus="status">
                        <dd class="${(type.selected)?'order-checked':''}">
                            <input type="checkbox" class="order-inp-pro programType" value="${type.id}"
                            ${(type.selected)?'checked="checked"':''}>
                            <i class="order-bar-check">${type.name}</i>
                        </dd>
                    </c:forEach>
                </dl>
            </div>
        </nav>
    </div>
    <div class="main-box col-lg-12">
        <div class="col-lg-4 order-bar-my">
            <input type="text" placeholder="输入节目名称" class="fl normal-txt" id="programName"
                   name="programName" value="${programName}">
            <button class="normal-btn fl txt-btn">确定</button>
        </div>
        <div class="col-lg-8 ">
            <input type="hidden" id="day" name="day" value="${day}">
            <button class="normal-btn form-box-btn form-top-txt fr day
            ${(day eq 30)?'selected-btn':'unselected-btn'}" value="30" type="button">近1个月</button>
            <button class="normal-btn form-box-btn form-top-txt fr day
            ${(day eq 15)?'selected-btn':'unselected-btn'}" value="15" type="button">近15日</button>
            <button class="normal-btn form-box-btn form-top-txt fr day
             ${(day eq 7)?'selected-btn':'unselected-btn'}" value="7" type="button">近7日 </button>
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>节目名称</th>
                    <th>平台</th>
                    <th>类型</th>
                    <th>主演</th>
                    <th>剧集数</th>
                    <th>上映时间</th>
                    <th>观看次数</th>
                    <th>广告位数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty dtoList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${dtoList}" var="dto">
                            <tr>
                                <td>${dto.programName}</td>
                                <td>${dto.programPlatform}</td>
                                <td>${dto.programType}</td>
                                <td>${dto.actors}</td>
                                <td>${dto.videoNum}</td>
                                <td>${dto.showTime}</td>
                                <td>${dto.playNum}</td>
                                <td>${dto.adSlotNum}</td>
                                <td class="btn-td"><button value="${dto.programId}"
                                                           class="normal-btn select
                                                           ${(dto.selected)?' unselected-btn-ml':' blue-btn'}"
                                                      style="margin-left: 0">${(dto.selected)?'取消选择':'选择'}</button>
                                    <input type="checkbox" class="checkbox-select" value="${dto.programId}" oppId="${dto.oppId}"
                                        ${(dto.selected)?'checked="checked"':''} hidden="hidden">
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
<script type="text/javascript"
        src="${ctx}/resources/view/order/js/orderSelectProgram.js?ver=${nowDate}"></script>

