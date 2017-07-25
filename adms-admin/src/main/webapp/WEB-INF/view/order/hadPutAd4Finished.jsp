<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="hadPutAd4Finished"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="titleSource" value="已完成订单-投放广告位"/>
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
<form id="queryForm" action="${ctx}/order/hadPutAd4Finished" method="post"
      class="form demo_form">
    <input type="hidden" id="adOrderId" name="adOrderId" value="${adOrderId}">
    <div class="form col-lg-12">
        <div class="common-bar common-bar-width fl">
            <dl>
                <dt>总计</dt>
                <dd>昨日消费：${expenseCount}</dd>
                <dd>剩余天数：${remainingDays}</dd>
                <dd>余额：${balance}</dd>
            </dl>
        </div>
        <div class="fr">
            <div class="export-bar order-export exportExcel fr">
                <img src="${ctx}/resources/images/export.png" alt="导出">
                <span>导出数据</span>
            </div>
        </div>
    </div>
    <div class="main-box col-lg-12">
        <div class="col-lg-4">
            <input type="text" placeholder="输入视频名称" class="fl normal-txt order-box-btn" id="videoName"
                   name="videoName" value="${videoName}">
            <button class="normal-btn fl txt-btn order-box-btn">确定</button>
        </div>

        <div class="col-lg-8">
            <button id="goBack" type="button" class="normal-btn .order-export order-box-btn white-btn fr">返回订单页</button>
        </div>

    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>广告位画面</th>
                    <th>视频平台</th>
                    <th>视频名称</th>
                    <th>类型</th>
                    <th>主演</th>
                    <th>上映日期</th>
                    <th>时间点</th>
                    <th>昨日曝光量</th>
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
        src="${ctx}/resources/view/order/js/hadPutAd4Finished.js?ver=${nowDate}"></script>

