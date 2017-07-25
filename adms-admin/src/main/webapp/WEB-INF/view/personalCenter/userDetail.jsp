<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="userDetail"/>
<input type="hidden" id="titleSource" value="基本信息"/>
<div class="col-lg-12 main-box main-box-b">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="video_table">
                <thead>
                <tr>
                    <th>账号</th>
                    <th>企业名称</th>
                    <th>角色</th>
                    <th>注册时间</th>
                    <th>备注</th>
                    <th>手机号绑定</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${shiroUser.loginName}</td>
                    <td>${shiroUser.company.companyName}</td>
                    <td>${shiroUser.role.name}</td>
                    <td><fmt:formatDate value="${shiroUser.created}" pattern="yyyy-MM-dd"/></td>
                    <td>${shiroUser.remark}</td>
                    <td>${shiroUser.mobile}</td>
                    <td class="btn-td">
                        <button class="normal-btn blue-btn changPassword">修改密码</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="changePasswordModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="form line-form line-form-b">
                    <label for="oldPassword" class="form_label fl">请输入旧密码：</label>
                    <div class="fl-select fl">
                        <input type="password" name="oldPassword" id="oldPassword" class="normal-txt">
                        <span class="pwd_img show_pwd_img"></span>
                        <p id="oldPasswordInfo"></p>
                    </div>
                </div>
                <div class="form line-form line-form-b">
                    <label for="newPassword" class="form_label fl">请输入新密码：</label>
                    <div class="fl-select fl">
                        <input type="password" name="newPassword" id="newPassword" class="normal-txt">
                        <span class="pwd_img show_pwd_img"></span>
                        <p id="newPasswordInfo"></p>
                    </div>
                </div>
                <div class="form line-form line-form-b">
                    <label for="newPasswordAgain" class="form_label fl">再次输入新密码：</label>
                    <div class="fl-select fl">
                        <input type="password" name="newPasswordAgain" id="newPasswordAgain" class="normal-txt">
                        <span class="pwd_img show_pwd_img"></span>
                        <p id="newPasswordAgainInfo"></p>
                    </div>
                </div>
                <p class="password-p">*密码由数字，英文和符号组成，长度8-20位，不能包含邮箱账号；<br>*两次密码必须输入一致</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="confirm">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/personalCenter/js/userDetail.js?ver=${nowDate}"/>