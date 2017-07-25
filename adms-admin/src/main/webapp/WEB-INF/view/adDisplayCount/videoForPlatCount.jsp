<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<!--[if lte IE 8]>
<style type="text/css">
html, body {
width: 100%;
height: 100%;
overflow: scroll
}

.section-btn {
display: none;
}
</style>
<![endif]-->
<div class="page_content ad-data-statistics">
    <div class="col-lg-12 main-box ad-tab-box">
        <a href="${ctx}/adDisplayCount/adCountList" class="ad-tab-btn">基于广告</a>
        <a href="${ctx}/adDisplayCount/videoForPlatCount" class="ad-tab-btn ad-tab-select">基于视频平台</a>
        <a href="${ctx}/adDisplayCount/programTypeCount" class="ad-tab-btn">基于节目类型</a>
    </div>
    <div class="col-lg-12 main-box grey-border-bottom">
        <div class="form col-lg-2">
            <label class="form_label">订单</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-hide-search" id="province">
                    <option value="all">全部</option>
                    <c:forEach items="${orderNameList}" var="orderNameList">
                        <option value=${orderNameList.orderId}>${orderNameList.orderName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 video-padding-b">
            <button class="normal-btn form-box-btn" id="adConfirm">确定</button>
        </div>
        <div class="col-lg-6 public-tab-box base-date-btn">

            <button class="normal-btn form-box-btn form-top-txt unselected-btn fr btn-sm-mr" value="30">
                近30日
            </button>
            <button class="normal-btn form-box-btn form-top-txt unselected-btn fr btn-sm-mr" value="15">
                近15日
            </button>
            <button class="normal-btn form-box-btn form-top-txt blue-btn fr btn-sm-mr" value="7">近7日
            </button>
        </div>
    </div>
    <div class="col-lg-12 main-box chart-legend">
        <ul id="toUl">
            <c:forEach items="${videoForPlatCount}" var="videoForPlatCount">
                <li><i></i>${videoForPlatCount.platformName}</li>
            </c:forEach>
        </ul>
    </div>
    <div class="col-lg-12 main-box">
        <div class="platform-charts-box">
            <span>曝光量</span>
            <div style="width: 100%;height: 100%;" id="exposureChart"></div>
        </div>
        <div class="platform-charts-box">
            <span>点击量</span>
            <div style="width: 100%;height: 100%;" id="clickChart"></div>
        </div>
        <div class="platform-charts-box">
            <span>CTR</span>
            <div style="width: 100%;height: 100%;" id="CTRChart"></div>
        </div>
        <div class="platform-charts-box">
            <span>消费额</span>
            <div style="width: 100%;height: 100%;" id="expenditureChart"></div>
        </div>
    </div>
    <div class="col-lg-12 main-box">
        <button type="button" id="exportPlatform" class="export-btn normal-btn fr white-btn"
                style="margin: 15px"
                data-toggle="modal" data-target="#alert-dialog">导出数据
        </button>
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>平台名称</th>
                    <th>广告位数量</th>
                    <th>曝光量/万</th>
                    <th>点击量</th>
                    <th>CTR</th>
                    <th>花费</th>
                </tr>
                </thead>
                <tbody id="toLoad">
                <c:choose>
                    <c:when test="${empty displayCountResultDTOList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${displayCountResultDTOList}" var="videoCount">
                            <tr>
                                <td>${videoCount.platformName}</td>>
                                <td>${videoCount.slotCount == null ? 0 : videoCount.slotCount}</td>
                                <td><fmt:formatNumber type="number" value="${videoCount.showCounts/10000}" maxFractionDigits="2"/></td>
                                <td><fmt:formatNumber type="number" value="${null == videoCount.clickCounts ? 0 : videoCount.clickCounts}" maxFractionDigits="0" /></td>
                                <td><fmt:formatNumber type="number" value="${null == videoCount.ctr ? 0 : videoCount.ctr}" maxFractionDigits="2"/>%</td>
                                <td><fmt:formatNumber type="number" value="${videoCount.cash == null ? 0 : videoCount.cash}" maxFractionDigits="2"/></td>
                            </tr>
                </c:forEach>
                </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    <%--<div id="curPage" value="1"></div>--%>
    <%--<div id="maxPage" value></div>--%>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<script src="${ctx}/resources/include/ECharts3/echarts.js"></script>
<script src="${ctx}/resources/view/dataStatistics/js/videoForPlatCount.js"></script>
<script src="${ctx}/resources/view/dataStatistics/js/exportCount.js"></script>
<script type="text/javascript">
    var url = "${ctx}/adDisplayCount/videoForPlatCount";
    var datas = "videoDatas";
    $(function () {
        pieChart(7);
    });
</script>
<script src="${ctx}/resources/include/pageTurn/pageTurn.js?ver=${nowDate}"></script>

