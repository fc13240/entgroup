<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="adCountList"/>
<input type="hidden" id="titleSource" value="数据统计"/>


    <div class="page_content ad-data-statistics">
        <div class="col-lg-12 main-box ad-tab-box">
            <a href="${ctx}/adDisplayCount/adCountList" class="ad-tab-btn ad-tab-select">基于广告</a>
            <a href="${ctx}/adDisplayCount/videoForPlatCount" class="ad-tab-btn">基于视频平台</a>
            <a href="${ctx}/adDisplayCount/programTypeCount" class="ad-tab-btn">基于节目类型</a>
        </div>
        <div class="form demo_form">
            <div class="col-lg-12 main-box grey-border-bottom">
                <div class="form col-lg-2">
                    <label class="form_label">订单名称</label>
                    <div class="fl select2-box select2-box-top">
                        <select class="js-example-basic-hide-search" id="province">
                            <option value="all">全部</option>
                            <c:forEach items="${orderNameList}" var="orderNameList">
                                <option value="${orderNameList.id}">${orderNameList.orderName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="col-lg-4 video-padding-b">
                    <button class="normal-btn form-box-btn" id="adConfirm">确定</button>
                </div>
                <div class="col-lg-6 video-top-box base-date-btn">
                    <button class="normal-btn form-box-btn form-top-txt unselected-btn fr" value="30">近30日</button>
                    <button class="normal-btn form-box-btn form-top-txt unselected-btn fr" value="15">近15日</button>
                    <button class="normal-btn form-box-btn form-top-txt blue-btn fr" value="7">近7日</button>
                </div>
            </div>
        </div>
        <div class="ad-canvas-p"><span class="red-font">总曝光量：</span><span
                id="showCount">${adDisplayTotalArr[0]}</span>　　<span
                class="blue-font">总点击量：</span><span id="clickCount">${adDisplayTotalArr[1]}</span>
        </div>
        <div class="ad-charts-box">
            <div class="chart-content" id="baseOnAdChart"></div>
        </div>
        <form id="queryForm" action="${ctx}/adDisplayCount/adCountList" method="POST" class="form demo_form">
            <div class="col-lg-12 main-box">
                <button type="button" id="exportAd" class="export-btn normal-btn fr white-btn"
                        style="margin: 15px"
                        data-toggle="modal" data-target="#alert-dialog">导出数据
                </button>
                <div class="table_wrapper">
                    <table>
                        <thead>
                        <tr>
                            <th>订单名称</th>
                            <th>投放日期</th>
                            <th>曝光量/万</th>
                            <th>点击量</th>
                            <th>CTR</th>
                            <th>花费</th>
                            <th>余额</th>
                        </tr>
                        </thead>
                        <tbody id="toLoad">
                        <c:choose>
                            <c:when test="${empty adDisplayCountList}">
                                <tr>
                                    <td colspan="16">暂无数据</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${adDisplayCountList}" var="adList">
                                    <tr>
                                        <td>${adList.orderName}</td>
                                        <td>${adList.beginTime}
                                            ~ ${adList.endTime}</td>
                                        <td>${adList.showCounts}</td>
                                        <td>${adList.clickCounts}</td>
                                        <td>${adList.ctr}%</td>
                                        <td>${adList.cosumeMoney}</td>
                                        <td>${adList.totalMoney}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
    <%--<div id="curPage" value="1"></div>--%>
    <%--<div id="maxPage" value></div>--%>

    <jsp:include page="../../layouts/panel-pagination.jsp"/>
    <script src="${ctx}/resources/include/ECharts3/echarts.js"></script>
    <script src="${ctx}/resources/view/dataStatistics/js/adCountList.js"></script>
    <script src="${ctx}/resources/view/dataStatistics/js/exportCount.js"></script>
    <%--<script type="text/javascript">--%>
    <%--var url = "/adDisplayCount/adOrderCount";--%>
    <%--var datas = "homeDatas";--%>
    <%--</script>--%>
    <%--<script src="${ctx}/resources/include/pageTurn/pageTurn.js"></script>--%>

