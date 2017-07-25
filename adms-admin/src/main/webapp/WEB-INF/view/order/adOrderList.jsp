<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adOrderList"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="titleSource" value="订单列表"/>
<%--<shiro:hasRole name="operator">--%>
    <form id="queryForm" action="${ctx}/order/adOrderList" method="GET"
          class="form demo_form">
        <div class="col-lg-12 main-box">
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
            <div class="form col-lg-2">
                <label for="orderStatus" class="form_label">状态</label>
                <div class="fl select2-box select2-box-top">
                    <select class="js-example-basic-single" id="orderStatus" name="status" value="${adOrder.status}" tabindex="1">
                        <option value="">全部</option>
                        <option value="0" ${(adOrder.status eq 1)?'selected="selected"':''}>未开始</option>
                        <option value="1" ${(adOrder.status eq 2)?'selected="selected"':''}>进行中</option>
                        <option value="2" ${(adOrder.status eq 3)?'selected="selected"':''}>交易完成</option>
                    </select>
                </div>
            </div>

            <%-- 新增 by sth on 2017/6/1--%>
            <div class="form col-lg-4">
                <input type="text" placeholder="输入订单名称" class="fl normal-txt form-box-btn" id="orderName" name="orderName"
                       value="${adOrder.orderName}">
                <button class="normal-btn fl txt-btn form-box-btn">确定</button>
            </div>
            <shiro:hasRole name="operator">
                <div class="col-lg-6 video-padding-b form">
                    <%--<button class="normal-btn form-box-btn">确定</button>--%>
                    <button type="button"
                            class="normal-btn fr blue-btn model-show form-box-btn"
                            id="addOrder" data-toggle="modal">新建订单
                    </button>
                        <%--刷新广告位 之后删除--%>
                    <%--<button type="button"--%>
                            <%--class="normal-btn fr blue-btn model-show form-box-btn"--%>
                            <%--id="updateOrderSlot">刷新广告位--%>
                    <%--</button>--%>
                </div>
            </shiro:hasRole>
        </div>
    </form>
<%--</shiro:hasRole>
<shiro:hasRole name="main_operator_company">
    <form id="queryForm" action="${ctx}/order/adOrderList" method="GET"
          class="form demo_form">
        <div class="col-lg-12 main-box">
            <input type="hidden" id="companyId" name="companyId"
                   value="${companyId}">
            <div class="form col-lg-2">
                <label for="orderStatus" class="form_label">状态</label>
                <div class="fl select2-box select2-box-top">
                    <select class="js-example-basic-single" id="orderStatus" name="status" value="${adOrder.status}" tabindex="1">
                        <option value="">全部</option>
                        <option value="1" ${(orderStatus eq 1)?'selected="selected"':''}>未开始</option>
                        <option value="2" ${(orderStatus eq 2)?'selected="selected"':''}>进行中</option>
                        <option value="3" ${(orderStatus eq 3)?'selected="selected"':''}>交易完成</option>
                    </select>
                </div>
            </div>
                &lt;%&ndash;刷新广告位 之后删除&ndash;%&gt;
            &lt;%&ndash;<div class="col-lg-10 video-padding-b form">&ndash;%&gt;
                &lt;%&ndash;<button class="normal-btn form-box-btn">确定</button>&ndash;%&gt;
                &lt;%&ndash;<button type="button"&ndash;%&gt;
                        &lt;%&ndash;class="normal-btn fr blue-btn model-show form-box-btn"&ndash;%&gt;
                        &lt;%&ndash;id="updateOrderSlot">刷新广告位&ndash;%&gt;
                &lt;%&ndash;</button>&ndash;%&gt;
            &lt;%&ndash;</div>&ndash;%&gt;
        </div>
    </form>
</shiro:hasRole>--%>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>投放周期</th>
                    <th>订单名称</th>
                    <th>预投放广告位数量</th>
                    <th>已投放广告位数量</th>
                    <th>剩余时间/天</th>
                    <th>昨日消费</th>
                    <th>余额&总金额/万</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty adOrderList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${adOrderList}" var="order">
                            <tr>
                                <td><fmt:formatDate value="${order.beginTime}" type="date" pattern="yyyy/MM/dd"/> ~
                                    <fmt:formatDate value="${order.endTime}" type="date" pattern="yyyy/MM/dd"/></td>
                                <td>${order.orderName}</td>
                                <td class="${(order.selected eq 1)?'td-style-green':''}">${order.slotCountPreview != null ? order.slotCountPreview : 0}</td>
                                <td>
                                    <c:if test="${order.status != 2}">${(order.slotCountAll != null ? order.slotCountAll : 0) - (order.slotCountPreview != null ? order.slotCountPreview : 0)}</c:if>
                                    <c:if test="${order.status == 2}">${order.slotCount}</c:if>
                                </td>
                                <td>${order.lastDate < 0 ? 0 : order.lastDate}</td>
                                <td><fmt:formatNumber type="number" value="${order.orderTotalprice != null ? order.orderTotalprice : 0}" pattern="0.00" maxFractionDigits="2" /></td>
                                <td>${(order.totalMoney - order.cosumeMoney)}/${order.totalMoney}</td>
                                <%--<td><fmt:formatNumber type="number"
                                                      value="${(order.totalMoney - order.cosumeMoney)/10000}"
                                                      maxFractionDigits="2"/>/<fmt:formatNumber
                                        type="number" value="${order.totalMoney/10000} "
                                        maxFractionDigits="2"/>
                                </td>--%>
                                <c:if test="${order.status==0}">
                                    <td>未开始</td>
                                </c:if>
                                <c:if test="${order.status==1}">
                                    <td>进行中</td>
                                </c:if>
                                <c:if test="${order.status==2}">
                                    <td>交易完成</td>
                                </c:if>
                                <td class="btn-td">
                                    <shiro:hasRole name="operator">
                                        <a class="normal-btn blue-btn" href="${ctx}/order/hadPutAd${(order.status
                                        eq 2)?'4Finished':''}?adOrderId=${order.id}" style="margin-left: 0">详情</a>
                                    </shiro:hasRole>
                                    <shiro:hasRole name="main_operator_company">
                                        <button id="adOrderCheck_${order.id}" class="ad_order_check normal-btn blue-btn ${order.status == 0?'':'disabled'}" style="margin-left: 0" ${order.status == 0?'':'disabled="disabled"'}>审核通过</button>
                                        <%--<c:if test="${adOrder.status!=0}">
                                            <button class="normal-btn blue-btn" href="${ctx}/order/adOrderDetail?orderId=${adOrder.id}&companyId=${companyId}&status=${adOrder.status}" style="margin-left:0" disabled>审核通过</button>
                                        </c:if>--%>
                                    </shiro:hasRole>
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

<div class="modal fade" id="saveOrderModal" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
					<span aria-hidden="true"><img
                            src="${ctx}/resources/images/close.png"> </span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增订单</h4>
            </div>
            <form action="" id="fbean" method="post">
                <div class="modal-body">
                    <%--<div class="form line-form">--%>
                        <%--<label for="companyId" class="form_label fl" tabindex="-1">所属客户：</label>--%>
                        <%--<div class="fl-select fl">--%>
                            <%--<select class="js-example-basic-single select" id="comList"--%>
                                    <%--name="companyId" tabindex="1">--%>
                                <%--<option value="">选择所属客户</option>--%>
                                <%--<c:forEach var="com" items="${companyList}" varStatus="status">--%>
                                    <%--<option value="${com.id}">${com.companyName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%-- 新增 by sth on 2017/6/1--%>
                    <div class="form line-form">
                        <label for="totalName" class="form_label fl">订单名称：</label>
                        <div class="fl-select fl">
                            <input type="text" id="totalName" placeholder="请输入订单名称"
                                   class="normal-txt">
                        </div>
                    </div>

                    <div class="form line-form">
                        <label for="totalMoney" class="form_label fl">订单金额：</label>
                        <div class="fl-select fl">
                            <input type="text" id="totalMoney" placeholder="请输入订单金额/元(最大为9位整数)"
                                   class="normal-txt">
                        </div>
                    </div>
                        <%-- 新增 by sth on 2017/6/1--%>
                        <%--del by sth on 2017/6/6--%>
                    <%--<div class="form line-form">--%>
                        <%--<label for="monitorCode" class="form_label fl">监测代码：</label>--%>
                        <%--<div class="fl-select fl">--%>
                            <%--<input type="text" id="monitorCode" placeholder="请输入监测代码"--%>
                                   <%--class="normal-txt">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                        <%--新增投放时间 by sth on 2017/6/2--%>

                        <%--日期控件依赖组件--%>
                        <%--<link rel="stylesheet" type="text/css" media="all" href="../../../resources/include/datepicker/css/daterangepicker-bs3.css" />--%>
                        <%--<script type="text/javascript" src="../../../resources/include/datepicker/js/moment.js"></script>--%>
                        <%--<script type="text/javascript" src="../../../resources/include/datepicker/js/daterangepicker.js"></script>--%>


                    <div class="form line-form">
                        <label for="beginTime" class="form_label fl">投放时间：</label>
                        <div class="input-prepend input-group fl">
                            开始：<input type="date" required min="${minDate}" name="beginTime" id="beginTime" class="short-txt"/>
                        </div>
                        <div class="input-prepend input-group fl" style="margin-left: 28px;">
                            结束：<input type="date" name="endTime" id="endTime" class="short-txt"/>
                        </div>
                    </div>

                    <div class="form line-form">
                        <label for="ad-id" class="form_label fl">关联广告：</label>
                        <div class="fl-select fl">
                            <ul class="modal-grey white-box modal-table" id="ad-id"
                                style="border-top: 1px solid #546E7A;"></ul>
                        </div>
                    </div>
                    <%--<div class="form line-form">--%>
                        <%--<label for="remark" class="form_label fl">备注：</label>--%>
                        <%--<div class="fl-select fl">--%>
                            <%--<textarea placeholder="请填写备注" class="normal-txt" id="remark"></textarea>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default cancelButton"
                            style="margin-right: 0px;" data-dismiss="modal">取消
                    </button>
                    <button type="button" class="btn btn-primary confirmButton"
                            id="saveAdOrder">下一步
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript"
        src="${ctx}/resources/view/order/js/adOrder.js?ver=${nowDate}"></script>

