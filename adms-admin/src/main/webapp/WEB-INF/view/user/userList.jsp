<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="userList"/>
<input type="hidden" id="titleSource" value="用户管理"/>
<form id="queryForm" action="${ctx}/user/userList" method="POST" class="form demo_form">
    <shiro:hasPermission name="authorityType:admin">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="companyId" class="form_label">企业</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}" ${(user.companyId eq com.id)?'selected="selected"':''}>${com.shortName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-10 video-padding-b form">
            <button class="normal-btn form-box-btn" type="submit">确定</button>
            <button id="insertUser" class="normal-btn fr blue-btn form-box-btn" type="button">
                新增用户
            </button>
        </div>
        <%--<shiro:lacksPermission name="authorityType:admin">
            <div class="col-lg-12 video-padding-b form">
                <button id="insertUser" class="normal-btn fr blue-btn form-box-btn userEdit" type="button">
                    新增用户
                </button>
            </div>
        </shiro:lacksPermission>--%>
    </div>
    </shiro:hasPermission>
</form>
<div id="userTable" class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="video_table">
                <thead>
                <tr>
                    <th width="25%">企业</th>
                    <th width="15%">用户名称</th>
                    <th width="15%">绑定手机号</th>
                    <th width="15%">角色</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty userList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td>${user.company.companyName}</td>
                                <td>${user.loginName}</td>
                                <td>${user.mobile}</td>
                                <td>${user.role.name}</td>
                                <td>${user.remark}</td>
                                <td class="btn-td">
                                    <a style="margin-left: 0" class="normal-btn blue-btn userEdit"
                                       id="userUpdate_${user.id}">修改</a>
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
<!-- edited by xiaokun on 2017-04-03 10:04 用户管理模块—模态框 begin -->
<div class="modal fade" id="userModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <b><h4 class="modal-title" id="userLabel">用户管理</h4></b>
            </div>
            <div class="modal-body">
                <shiro:hasPermission name="authorityType:admin">
                    <div class="form line-form">
                        <label class="form_label fl">所属企业：</label>
                        <div class="fl-select fl">
                            <select class="js-example-basic-single select" id="userCompanyId">
                                <c:forEach items="${companyList}" var="company">
                                    <option id="${company.id}_editCompanyId"
                                            value="${company.id}">${company.companyName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </shiro:hasPermission>
                <div class="form line-form">
                    <label class="form_label fl">用户名：</label>
                    <div class="fl-select fl">
                        <input id="editName" type="text" name="name" placeholder="请填写用户名" class="normal-txt">
                        <p class="error hide" id="editNameInfo">*请输入用户名</p>
                    </div>
                </div>
                <div id="passwordLine" class="form line-form">
                    <label class="form_label fl">登录密码：</label>
                    <div class="fl-select fl">
                        <input id="editPassword" type="password" name="password" placeholder="请填写登录密码"
                               class="normal-txt">
                        <p class="error hide" id="editPasswordInfo">*请输入密码</p>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">手机号：</label>
                    <div class="fl-select fl">
                        <input id="editMobile" type="text" name="mobile" placeholder="请填写手机号" class="normal-txt">
                        <p class="error hide" id="editMobileInfo">*请输入手机号</p>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">邮箱：</label>
                    <div class="fl-select fl">
                        <input id="editUserEmail" type="email" name="email" placeholder="请填写联系人邮箱" class="normal-txt">
                        <p class="error hide" id="editUserEmailInfo">*请输入联系人邮箱</p>
                    </div>
                </div>
                <div id="roleEditDiv" class="form line-form">
                    <label class="form_label fl">角色：</label>
                    <div class="fl-select fl">
                        <select class="js-example-basic-single select" id="editRoleId"></select>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">备注：</label>
                    <div class="fl-select fl">
                        <input id="editRemark" type="text" name="remark" placeholder="请填写备注" class="normal-txt">
                        <input id="userId" type="hidden" value="insertUser"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="saveUser" type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- edited by xiaokun on 2017-04-03 10:07 end -->
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/user/js/userList.js?ver=${nowDate}"/>


