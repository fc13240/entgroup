<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>

<input type="hidden" id="columnNo" column="companyList"/>
<input type="hidden" id="titleSource" value="客户管理"/>

<%--<div class="page_content">--%>
<form id="queryForm" action="${ctx}/company/saveCompany" method="POST" class="form demo_form">
    <div class="main-body" id="companyModal" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <input id="companyId" type="hidden" value="${company.id}">
            <div class="form  line-form">
                <label class="form_label fl must-fill-in" tabindex="-1">客户类型：</label>
                <div class="fl-select fl add-line-width">
                    <input type="radio" class="company-radio" name="companyType" ${(company.companyType ==null)?'checked="checked"':''} ${(company.companyType eq 2)?'checked="checked"':''} value=2 id="check-1" hidden><label class="fl" for="check-1">广告主</label>
                    <input type="radio" class="company-radio" name="companyType" ${(company.companyType eq 3)?'checked="checked"':''} value=3 id="check-2" hidden><label class="fl" for="check-2">代理商</label>
                </div>
            </div>

            <div class="form line-form">
                <label class="form_label fl must-fill-in">客户全称：</label>
                <div class="fl-select fl">
                    <input id="editCompanyName" type="text" name="companyName" placeholder="请填写客户全称"
                          value="${company.companyName}" class="normal-txt">
                    <p class="error hide" id="editCompanyNameInfo">*请填写客户全称</p>
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">客户简称：</label>
                <div class="fl-select fl">
                    <input id="editShortName" type="text" name="shortName" placeholder="请填写客户简称" value="${company.shortName}" class="normal-txt">
                    <p class="error hide" id="editShortNameInfo">*请填写客户简称</p>
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">所属行业：</label>
                <div class="fl-select fl">
                    <select class="js-example-basic-single select" name="companyVocationId" id="companyVocationId">
                        <c:forEach items="${companyVocationList}" var="companyVocation">
                            <option value="${companyVocation.id}" ${(company.companyVocationId eq companyVocation.id)?'selected="selected"':''}>${companyVocation.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form line-form">
                <label class="form_label fl">公司地址：</label>
                <div class="fl-select fl">
                    <input id="editCompanyAddress" type="text" name="address" placeholder="请填写公司地址" value="${company.address}" class="normal-txt">
                </div>
            </div>

            <div class="form line-form">
                <label class="form_label fl">公司网站：</label>
                <div class="fl-select fl">
                    <input id="editCompanyInternetAddress" type="text" name="internetAddress" placeholder="请填写公司网站" value="${company.internetAddress}" class="normal-txt">
                </div>
            </div>

            <div class="form line-form">
                <label class="form_label fl must-fill-in">公司电话：</label>
                <div class="fl-select fl">
                    <input id="editCompanyPhoneNumber" type="text" name="phoneNumber" placeholder="请填写公司电话" value="${company.phoneNumber}" class="normal-txt">
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">联系人&emsp;：</label>
                <div class="fl-select fl">
                    <input id="editContact" type="text" name="contact" placeholder="请填写联系人" value="${company.contact}" class="normal-txt">
                    <p class="error hide" id="editContactInfo">*请填写联系人</p>
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">联系电话：</label>
                <div class="fl-select fl">
                    <input id="editPhoneNumber" type="text" name="mobile" placeholder="请填写联系电话"
                          value="${operator.mobile}" class="normal-txt">
                    <p class="error hide" id="editPhoneNumberInfo">*请填写联系电话</p>
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">联系邮箱：</label>
                <div class="fl-select fl">
                    <input id="editEmail" type="text" name="email" placeholder="请填写联系人邮箱" value="${company.email}" class="normal-txt">
                    <p class="error hide" id="editEmailInfo">*请填写联系人邮箱</p>
                </div>
            </div>
            <div class="form line-form">
                <label class="form_label fl must-fill-in">登陆账号：</label>
                <div class="fl-select fl">
                    <input id="editUserName" type="text" name="loginName" placeholder="请填写登录账号" value="${operator.loginName}" class="normal-txt">
                    <p class="error hide" id="editUserNameInfo">*请填写登陆账号</p>
                </div>
            </div>
            <c:if test="${empty operator.password}">
                <div class="form line-form">
                    <label class="form_label fl must-fill-in">登陆密码：</label>
                    <div class="fl-select fl">
                        <input id="editPassword" type="text" name="password" placeholder="请填写登录密码" value="${operator.password}" class="normal-txt">
                        <p class="error hide" id="editPasswordInfo">*请填写登陆密码</p>
                    </div>
                </div>
            </c:if>
            <div class="upimg-box">
                <div class="imgdiv fl" id="openAccountPermit">
                    <input type="file" id="up_openAccountPermit" name="files" class="up-img" >
                    <input type="hidden" id="openAccountPermitPath" value="${company.openAccountPermitPath}">
                    <img id="imgShowOpenAccountPermit" class="imgsShow" style="${company.openAccountPermitPath == null ? 'display:"none"':''}" src="${company.openAccountPermitPath}">
                    <span>上传开户许可证</span>
                </div>
                <div class="imgdiv fr" id="organizationCode">
                    <input type="file" id="up_organizationCode" name="files" class="up-img" >
                    <input type="hidden" id="organizationCodePath" value="${company.organizationCodePath}">
                    <img id="imgShowOrganzationCode" class="imgsShow" style="${company.organizationCodePath == null ? 'display:"none"':''}" src="${company.organizationCodePath}">
                    <span>上传组织机构代码</span>
                </div>

            </div>
            <div class="upimg-box">
                <button id="saveCompany" type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="reset" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</form>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/company/js/companyAdd.js?ver=${nowDate}"></script>
