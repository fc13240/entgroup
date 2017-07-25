<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="adAdminCountList"/>
<input type="hidden" id="titleSource" value="数据统计"/>

<div class="ad-data-statistics">
    <div class="col-lg-12 main-box grey-border-bottom">
        <div class="form col-lg-2">
            <label class="form_label">客户名称</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-hide-search" id="companyId">
                    <option value="all">全部</option>
                    <c:forEach items="${companyNameList}" var="companyNameList">
                        <option value=${companyNameList.id}>${companyNameList.companyName}</option>
                    </c:forEach>
                </select>

            </div>
        </div>
        <div class="form col-lg-2">
            <label class="form_label">订单名称</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-hide-search" id="province">
                    <option value="all">全部</option>
                    <c:forEach items="${orderNameList}" var="orderNameList">
                        <option value=${orderNameList.id}>${orderNameList.orderName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="col-lg-3 video-padding-b">
            <button class="normal-btn form-box-btn" id="adConfirm">确定</button>
        </div>
        <div class="col-lg-5 public-tab-box base-date-btn">
            <button class="normal-btn form-box-btn form-top-txt unselected-btn btn-sm-mr fr" value="30">近30日</button>
            <button class="normal-btn form-box-btn form-top-txt unselected-btn btn-sm-mr fr" value="15">近15日</button>
            <button class="normal-btn form-box-btn form-top-txt blue-btn btn-sm-mr fr" value="7">近7日</button>
        </div>
    </div>
    <div class="ad-canvas-p"><span class="red-font">总曝光量：</span><span
            id="showCount">${adDisplayTotalArr[0]}</span>　　<span
            class="blue-font">总点击量：</span><span id="clickCount">${adDisplayTotalArr[1]}</span>
    </div>
    <div class="ad-charts-box">
        <div class="chart-content" id="adAdminChart"></div>
    </div>
    <form id="queryForm" action="${ctx}/adAdminDisplayCount/adAdminCountList" method="POST" class="form demo_form">
    <div class="col-lg-12 main-box">

        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>公司名称</th>
                    <th>曝光量/万</th>
                    <th>点击量</th>
                    <th>CTR</th>
                    <th>花费</th>
                    <th>余额</th>
                </tr>
                </thead>
                <tbody id="toLoad">
                <c:choose>

                    <c:when test="${empty adminList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${adminList}" var="adminList">
                            <tr>
                                <td>${adminList.companyName}</td>
                                <td>${adminList.sumShowCount}</td>
                                <td>${adminList.sumClickCount}</td>
                                <td>${adminList.ctr}%</td>
                                <td>${adminList.sumCosumeMoney}</td>
                                <td>${adminList.sumTotalMoney}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
    </form>
    <%--<div id="curPage" value="1"></div>--%>
    <%--<div id="maxPage" value></div>--%>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<script src="${ctx}/resources/include/ECharts3/echarts.js"></script>
<script src="${ctx}/resources/view/dataStatistics/js/adAdminCountList.js"></script>
<%--<script type="text/javascript">--%>
    <%--var url = "${ctx}/adAdminDisplayCount/adminOrderCountList";--%>
    <%--var datas = "adminDatas";--%>
<%--</script>--%>
<%--<script src="${ctx}/resources/include/pageTurn/pageTurn.js"></script>--%>
