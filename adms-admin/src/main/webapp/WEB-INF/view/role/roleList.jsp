<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="roleList"/>
<input type="hidden" id="titleSource" value="角色管理"/>
<form id="queryForm" action="${ctx}/role/roleList" method="POST" class="form demo_form">
    <%--<div class="col-lg-12 main-box">
        <div style="margin-bottom: 25px;float: right">
            <button id="roleInsert" style="margin: 65px 0 0 0" class="normal-btn fr blue-btn roleEdit" type="button">
                新增角色
            </button>
        </div>
    </div>--%>
</form>
<div class="col-lg-12 main-box main-box-b">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="video_table">
                <thead>
                <tr>
                    <th>角色名称</th>
                    <th>角色描述</th>
                    <th>权限</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty roleList}">
                        <tr>
                            <td colspan="8">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${roleList}" var="role">
                            <tr>
                                <td>${role.name}</td>
                                <td>${role.description}</td>
                                <td>
                                    <c:if test="${role.roleLevel <= 2}">${role.authorityNames}</c:if>
                                    <c:if test="${role.roleLevel > 2}">${role.authorityRemarks}</c:if>
                                </td>
                                <td class="btn-td">
                                    <a id="roleEdit_${role.id}" style="margin-left: 0"
                                       class="normal-btn blue-btn roleEdit">修改</a>
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
<%--Modal--%>
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="roleModalTitle">选择标签</h4>
            </div>
            <div class="modal-body" id="roleModalBody">
                <form id="editAdSlotLabelForm">
                    <div class="form line-form">
                        <label class="form_label fl">角色名称：</label>
                        <div class="fl-select fl">
                            <input id="roleName" type="text" name="name" placeholder="*请填写角色名称" class="normal-txt">
                            <p class="error hide" id="roleNameInfo">*请填写角色名称</p>
                        </div>
                    </div>
                    <div class="form line-form">
                        <label class="form_label fl">角色描述：</label>
                        <div class="fl-select fl">
                            <input id="roleDescription" type="text" name="name" placeholder="*请填写角色描述"
                                   class="normal-txt">
                            <p class="error hide" id="roleDescriptionInfo">*请填写角色描述</p>
                            <input type="hidden" id="editRoleId"/>
                        </div>
                    </div>
                    <div class="form line-form">
                        <label class="form_label fl">角色类型：</label>
                        <div class="fl-select fl">
                            <select class="js-example-basic-single select" id="roleType">
                                <option value="1">管理角色</option>
                                <option value="2">总运营角色</option>
                                <option value="3">运营角色</option>
                            </select>
                        </div>
                    </div>
                    <div class="form line-form">
                        <input type="hidden" name="zTreeResults" id="zTreeResults"/>
                        <label for="zTreeBody" class="form_label fl">权限详情：</label>
                        <div class="fl-select fl">
                            <ul id="zTreeBody" class="zTree white-box"></ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="saveRole" type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/role/js/roleList.js?ver=${nowDate}"/>
<script type="text/javascript" src="${ctx}/resources/js/common-zTree.js?ver=${nowDate}"/>
