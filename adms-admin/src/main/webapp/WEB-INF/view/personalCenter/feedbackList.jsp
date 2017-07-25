<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="feedbackList"/>
<input type="hidden" id="titleSource" value="用户反馈"/>
<form id="queryForm" action="${ctx}/feedback/feedbackList" class="form demo_form">
    <%--<div class="col-lg-12 main-box">
        <div class="form col-lg-12">
            &lt;%&ndash;<shiro:hasPermission name="sys:helpandfeedback:addfeedback">
                <button id="insertFeedback" class="normal-btn form-box-btn fr blue-btn" type="button">新增反馈</button>
            </shiro:hasPermission>
            <shiro:lacksPermission name="sys:helpandfeedback:addfeedback">&ndash;%&gt;
            <div style="margin-top: 100px"></div>
        </div>
    </div>--%>
</form>
<div class="col-lg-12 main-box main-box-b">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="video_table">
                <thead>
                <tr>
                    <th>时间</th>
                    <th>反馈用户</th>
                    <th>所属客户</th>
                    <th>反馈内容</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty feedbackList}">
                        <tr>
                            <td colspan="8">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${feedbackList}" var="feedback">
                            <tr class="feedbackDetail" id="feedbackDetail_${feedback.id}">
                                <td><fmt:formatDate value="${feedback.publishDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${feedback.user.loginName}</td>
                                <td>${feedback.company.companyName}</td>
                                <td>${feedback.content}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="feedbackModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <h4 class="modal-title" id="myModalLabel">反馈详情</h4>
            </div>
            <div class="modal-body">
                <p class="modal-grey-font">
                    <span id="creator" class="fl" style="margin-left: 30px;"></span>
                    <span id="publishDate" class="fr" style="margin-right: 30px;"></span>
                </p>
                <div class="fl-select fl">
                    <div class="form line-form">
                        <textarea id="content" readonly="readonly" class="normal-txt"
                                  style="width: 508px;margin-left: 30px;height: 10em;border-radius: 6px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div id="insertBtns" style="display: none" class="modal-footer">
                    <button id="saveFeedback" type="button" class="btn btn-primary confirmButton">确定</button>
                    <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-model.jsp"/>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/personalCenter/js/feedbackList.js?ver=${nowDate}"/>