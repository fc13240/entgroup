<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="willPutAd"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="titleSource" value="预投放广告位"/>
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
<div class="col-lg-12 main-box">
    <a href="${ctx}/order/willPutAd?adOrderId=${adOrderId}" class="ad-tab-btn adjust-btn-mt ad-tab-select">预投放广告位</a>
    <a href="${ctx}/order/hadPutAd?adOrderId=${adOrderId}" class="ad-tab-btn adjust-btn-mt">已投放广告位</a>
</div>
<div class="main-box col-lg-12">
    <div class="common-bar common-bar-width fl">
        <dl>
            <dt>总计</dt>
            <dd>预计曝光量：${showNumCount}</dd>
            <dd>预计点击量：<fmt:formatNumber type="number" value="${showNumCount*clickNum}" maxFractionDigits="0"/></dd>
            <input type="hidden" id="clickNum" name="clickNum" value="${clickNum}">
            <dd>预计消费：${expenseCount}</dd>
        </dl>
    </div>
    <div class="common-bar common-bar-width fl">
        <dl>
            <dt>已选择</dt>
            <dd id="showNums">预计曝光量：0</dd>
            <dd id="clickNums">预计点击量：0</dd>
            <dd id="expenses">预计消费：0</dd>
        </dl>
    </div>

    <div class="fr">
        <div class="export-bar fr exportExcel">
            <img src="${ctx}/resources/images/export.png" alt="导出">
            <span>导出排期</span>
        </div>
        <div class="export-bar fr" id="goBack2">
            <img src="${ctx}/resources/images/add.png" alt="增加">
            <span>继续增加</span>
        </div>
        <div class="export-bar fr ${selected?'export-bar-on':''}" id="tag-completed">
            <img src="${ctx}/resources/images/label.png" alt="标记">
            <span style="left: 13px;">标记已完成</span>
        </div>
    </div>
</div>
<form id="queryForm" action="${ctx}/order/willPutAd" method="post"
      class="form demo_form">
    <input type="hidden" id="adOrderId" name="adOrderId" value="${adOrderId}">
    <div class="main-box col-lg-12">
        <div class="form col-lg-4">
            <input type="text" placeholder="输入视频名称" class="fl normal-txt form-box-btn" id="videoName"
                   name="videoName" value="${videoName}">
            <button class="normal-btn fl txt-btn form-box-btn">确定</button>
        </div>
        <div class="col-lg-8">
            <c:if test="${check}">
                <button id="nextStep" type="button" class="normal-btn form-box-btn blue-btn fr order-adjust-mr">投放</button>
            </c:if>
            <button id="selection" type="button" class="normal-btn form-box-btn white-btn fr">删除</button>
            <button id="goBack" type="button" class="normal-btn form-box-btn white-btn fr">上一步</button>
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th style="width: 65px;">
                        <div class="piaochecked checkAll"  id="checkbox1">
                            <input name="need_inv" type="checkbox"
                                   class="radioclass" showNum="0" expense="0">
                        </div>
                    </th>
                    <th>广告位画面</th>
                    <th>视频平台</th>
                    <th>视频名称</th>
                    <th>类型</th>
                    <th>主演</th>
                    <th>上映日期</th>
                    <th>时间点</th>
                    <th>预计曝光量</th>
                    <th>昨日消费/元</th>
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
                                <td>
                                    <div class="piaochecked" for="checkbox1">
                                        <input name="adSlotIds" type="checkbox"
                                               class="radioclass" value="${dto.adSlotId}"
                                               showNum="${dto.showNum}" expense="${dto.expense}">
                                    </div>
                                </td>
                                <td>
                                    <img src="${dto.pictureAddress}" onerror="this.src='${ctx}/resources/images/img.jpg'"
                                         alt="广告位画面">
                                </td>
                                <td>${dto.companyName}</td>
                                <td>${dto.videoName}</td>
                                <td>${dto.programTypeName}</td>
                                <td>${dto.actors}</td>
                                <td>${dto.showTime}</td>
                                <td>${dto.videoPositionTime}</td>
                                <td>${dto.showNum}</td>
                                <td>${dto.expense}</td>
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
        src="${ctx}/resources/view/order/js/willPutAd.js?ver=${nowDate}"></script>

