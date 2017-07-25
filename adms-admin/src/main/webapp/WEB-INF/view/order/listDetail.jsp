<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adOrderList"/>
<input type="hidden" id="titleSource" value="订单详情"/>
<form action="${ctx}/order/adOrderDetail" method="GET" id="queryForm"
      class="form demo_form">
    <input type="hidden" id="orderId" name="orderId" value="${orderId}">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="styleId" class="form_label">广告样式</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="styleId" name="styleId"
                        tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="style" items="${adStyleList}" varStatus="status">
                        <option value="${style.id}" ${(styleId eq
                                style.id)?'selected="selected"':''}>${style.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-10 video-padding-b form" style="min-width:600px;">
            <button class="normal-btn form-box-btn" type="submit">确定</button>
            <a href="${ctx}/order/adOrderList?companyId=${companyId}">
                <button
                        class="normal-btn txt-btn top-btn fr" style="margin-top: 53px;"
                        type="button">
                    返 回
                </button>
            </a>
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <input type="hidden" id="getSlot" name="getSlot" value="${orderAd.adId}">
        <input type="hidden" id="companyId" name="companyId" value="${companyId}">
        <input type="hidden" id="orderId" name="orderId" value="${orderId}">
  		<input type="hidden" id="status" name="status" value="${status}">
        <div class="table_wrapper">
            <table id="adSlots">
                <thead>
                <tr>
                    <th>广告名称</th>
                    <th>广告样式</th>
                    <th>广告位</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty orderAdList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${orderAdList}" var="orderAd">
                            <tr onclick="getSlot.value=${orderAd.adId}" id="selectSlot${orderAd.adId}"
                                data-toggle="modal" data-target="#addOrderModal">
                                <td>${orderAd.adName}</td>
                                <td>${orderAd.adStyleName}</td>
                                <td>
                                        ${orderAd.adSlotCount}
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
<!-- Modal -->
<div class="modal fade" id="addOrderModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
					<span aria-hidden="true"><img
                            src="${ctx}/resources/images/close.png"> </span>
                </button>
                <h4 class="modal-title" id="myModalLabel">投放广告位</h4>
            </div>
            <div class="modal-body">
                <div class="table_wrapper">
                    <table class="modal-th">
                        <thead>
                        <tr>
                            <th>视频平台</th>
                            <th>视频名称</th>
                            <th>位置</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="modal-table">
                        <ul id="slotList">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript"
        src="${ctx}/resources/view/order/js/listDetail.js?ver=${nowDate}"></script>