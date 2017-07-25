<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<input type="hidden" id="columnNo" column="home"/>

<shiro:hasRole name="operator">
    <input type="hidden" id="titleSource" value="首页"/>
    <!-- 以下为首页内容-->
    <style>
        .main-box {
            background-color: #eceff1;
        }

        .main-box .pagination-border {
            background-color: #fff;
        }
    </style>
    <div class="bgc-gray">
        <div class="page_content">
            <div class="home-main col-lg-12 ">
                <div class="col-lg-12 yesterday-statistics">
                    <div class="home-header"><span>昨日统计</span></div>
                    <div class="ys-body fl"><p>昨日总曝光量(次)</p><span><fmt:formatNumber type="number"
                                                                                    value="${homeDisplayTotalArr[0]} "
                                                                                    maxFractionDigits="0"/></span></div>
                    <div class="ys-body fl"><p>昨日CTR(百分比)</p><span>${homeDisplayTotalArr[4]}%</span></div>
                    <div class="ys-body fl"><p>昨日消费金额(元)</p><span>￥${homeDisplayTotalArr[3]}</span></div>
                    <div class="ys-body fl"><p>账户余额(元)</p><span>￥${homeDisplayTotalArr[2]}</span></div>
                </div>

            </div>
            <div class="col-lg-12 home-main">
                <div class="order-chart">
                    <div class="home-header">
                        <label>订单</label>
                        <select class="home-select js-example-basic-hide-search" id="province">
                            <option value="all">全部</option>
                            <c:forEach items="${orderNameList}" var="homeNameList">
                                <option value=${homeNameList.id}>${homeNameList.orderName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="home-charts-box">
                        <div class="chart-content" id="chartMain"></div>
                    </div>
                </div>
            </div>
            <form id="queryForm" action="${ctx}/adDisplayCount/adOrderCount" method="POST" class="form demo_form">
                <div class="home-main col-lg-12">
                    <div class="home-table fl">
                        <div class="table_wrapper">
                            <table>
                                <thead>
                                <tr>
                                    <th>订单名称</th>
                                    <th>周期</th>
                                    <th>昨日曝光量/万</th>
                                    <th>昨日点击量</th>
                                    <th>昨日CTR</th>
                                    <th>昨日消费金额</th>
                                    <th>账户余额</th>
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
                </div>
            </form>
                <div id="curPage" value="1"></div>
                <div id="maxPage" value></div>
        </div>
    </div>

    <jsp:include page="../layouts/panel-pagination.jsp"/>
</shiro:hasRole>
<shiro:lacksRole name="operator">
    <input type="hidden" id="titleSource" value="艺恩广告管理系统"/>
    <div id="backgroundDiv"
         style="width:100%; overflow: hidden; background-image: url('${ctx}/resources/images/home.png');background-repeat: no-repeat; background-size: contain"></div>
</shiro:lacksRole>

<script src="${ctx}/resources/include/ECharts3/echarts.js"></script>
<script src="${ctx}/resources/view/home/js/homeCharts.js"></script>
<%--<script type="text/javascript">--%>
<%--var url = "${ctx}/adDisplayCount/adOrderCount";--%>
<%--var datas = "homeDatas";--%>
<%--</script>--%>
<script src="${ctx}/resources/include/pageTurn/pageTurn.js"></script>



