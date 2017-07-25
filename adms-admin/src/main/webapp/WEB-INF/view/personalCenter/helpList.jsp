<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="helpList"/>
<%--标题显示  管理--%>
<shiro:hasPermission name="authorityType:admin">
    <input type="hidden" id="titleSource" value="帮助管理"/>
</shiro:hasPermission>
<%--标题显示  用户--%>
<shiro:lacksPermission name="authorityType:admin">
    <input type="hidden" id="titleSource" value="帮助与反馈"/>
</shiro:lacksPermission>
<form id="queryForm" action="${ctx}/help/helpList" class="form demo_form">
    <%--<div class="col-lg-12 main-box">
        <div class="form col-lg-12">--%>
    <shiro:hasPermission name="authorityType:admin">
        <%--<button id="insertHelp" class="normal-btn form-box-btn fr blue-btn helpEdit" type="button">新增帮助</button>--%>
        <input type="hidden" id="userType" value="admin"/>
    </shiro:hasPermission>
    <%--<shiro:lacksPermission name="authorityType:admin">
        <button id="insertFeedback" class="normal-btn form-box-btn fr blue-btn" type="button">我要反馈</button>
    </shiro:lacksPermission>--%>
        <%--</div>
    </div>--%>
</form>
<div class="col-lg-12 main-box main-box-b">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="video_table">
                <thead>
                <tr>
                    <th><label class="fl" style="font-size: 15px;margin-left: 40px">帮助信息</label></th>
                </tr>
                </thead>
                <tbody style="margin-left: 30px">
                <c:choose>
                    <c:when test="${empty helpList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${helpList}" var="help" varStatus="status">
                            <shiro:hasPermission name="authorityType:admin">
                                <tr style="width: 100%" class="fl">
                                        <%--<td id="helpDetail_${help.id}" width="100%"><a style="margin-left: 40px;float: left">${status.index+1}. ${help.title}</a></td>--%>
                                    <td id="helpDetail_${help.id}" width="100%">
                                        <text style="margin-left: 40px;float: left">${status.index+1}. ${help.title}</text>
                                    </td>
                                    <td><a class="normal-btn blue-btn helpEdit" id="helpEdit_${help.id}">修改</a></td>
                                </tr>
                            </shiro:hasPermission>
                            <shiro:lacksPermission name="authorityType:admin">
                                <tr style="width: 100%" class="fl">
                                    <td class="fl" width="100%"><a class="help" id="helpDetail_${help.id}_${status.index+1}" style="text-align:left;margin-left: 40px;width: 100%;display: inline-block">${status.index+1}. ${help.title}</a>
                                    </td>
                                </tr>
                            </shiro:lacksPermission>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%--feedbackModal--%>
<div class="modal fade" id="feedbackModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <h4 class="modal-title" id="feedbackModalLabel">反馈详情</h4>
            </div>
            <div class="modal-body">
                <p class="modal-grey-font">
                    <span id="creator" class="fl" style="margin-left: 30px;"></span>
                    <span id="publishDate" class="fr" style="margin-right: 30px;"></span>
                </p>
                <div class="fl-select fl">
                    <div class="form line-form">
                        <textarea id="feedback" placeholder="*请填写反馈详情" readonly="readonly" class="normal-txt"
                                  style="width: 508px;margin-left: 30px;height: 10em;border-radius: 6px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div id="feedbackBtns" style="display: none" class="modal-footer">
                    <button id="saveFeedback" type="button" class="btn btn-primary confirmButton">确定</button>
                    <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%--helpModal--%>
<div class="modal fade" id="helpModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <b><h4 class="modal-title" id="companyLabel">帮助详情</h4></b>
            </div>
            <div class="modal-body" style="height: 260px">
                <shiro:hasPermission name="authorityType:admin">
                    <div class="form line-form">
                        <label class="form_label fl modal-grey-font">帮助标题：</label>
                        <div class="fl-select fl">
                            <input id="helpTitle" type="text" name="companyName" placeholder="*请填写帮助标题"
                                   class="normal-txt">
                        </div>
                    </div>
                    <div class="form line-form">
                        <label class="form_label fl modal-grey-font">帮助内容：</label>
                        <div class="fl-select fl">
                            <div class="form line-form">
                                <textarea id="helpContent" readonly="readonly" class="normal-txt"
                                          style="width: 400px;height: 10em;border-radius: 6px"
                                          placeholder="*请填写帮助内容"></textarea>
                                <input id="helpId" type="hidden"/>
                            </div>
                        </div>
                    </div>
                </shiro:hasPermission>
                <shiro:lacksPermission name="authorityType:admin">
                    <p class="modal-grey-font">
                        <span id="helpTitle" class="fl" style="margin-left: 30px;"></span>
                    </p>
                    <div class="fl-select fl">
                        <div class="form line-form">
                    <textarea id="helpContent" readonly="readonly" class="normal-txt"
                              style="width: 508px;margin-left: 30px;height: 10em;border-radius: 6px;"></textarea>
                        </div>
                    </div>
                </shiro:lacksPermission>
            </div>
            <div class="modal-footer">
                <div id="helpBtns" style="display: none" class="modal-footer">
                    <button id="saveHelp" type="button" class="btn btn-primary confirmButton">确定</button>
                    <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-model.jsp"/>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/personalCenter/js/helpList.js?ver=${nowDate}"/>