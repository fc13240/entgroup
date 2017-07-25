<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="companyList"/>
<input type="hidden" id="titleSource" value="客户管理"/>
<form id="queryForm" action="${ctx}/company/companyList" method="POST" class="form demo_form">
    <div class="col-lg-12 main-box">
        <div class="col-lg-4 form video-padding-b">
            <input type="" name="companyName" value="${company.companyName}" placeholder="输入企业名称"
                   class="fl normal-txt form-box-btn"/>
            <button class="normal-btn fl txt-btn form-box-btn" type="submit">搜索</button>
        </div>
        <div class="col-lg-6 form video-padding-b">
        </div>
        <div class="col-lg-2 form video-padding-b">
            <button id="companyInsert" class="normal-btn fr blue-btn companyEdit form-box-btn" onclick="window.location='${ctx}/company/companyAddOrUpdate'"
                    type="button">新增客户
            </button>
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="company_table">
                <thead>
                <tr>
                    <th width="10%">注册日期</th>
                    <th width="15%">客户简称</th>
                    <th width="28%">客户全称</th>
                    <th width="12%">所属行业</th>
                    <th width="10%" style="padding: 0 0 0 0;">订单数<br/>(进行中/总数)</th>
                    <th>管理员账号</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty companyList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${companyList}" var="company">
                            <tr>
                                <td><fmt:formatDate value="${company.created}" pattern="yyyy-MM-dd"/></td>
                                <td>${company.shortName}</td>
                                <td>${company.companyName}</td>
                                <td>${company.companyVocationName}</td>
                                <td>${company.orderOnDeal}/${company.orderCount}</td>
                                <td>${company.admin.loginName}</td>
                                <td class="btn-td">
                                    <a id="companyUpdate_${company.id}" style="margin-left: 0"
                                       class="normal-btn blue-btn companyEdit" href="${ctx}/company/companyAddOrUpdate?companyId=${company.id}">修改</a>
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
<!-- edited by xiaokun on 2017-04-01 11:45 客户管理模块—模态框 begin -->
<div class="modal fade" id="companyModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span>
                </button>
                <b><h4 class="modal-title" id="companyLabel">客户管理</h4></b>
            </div>
            <div class="modal-body">
                <div class="form line-form">
                    <label class="form_label fl">客户全称：</label>
                    <div class="fl-select fl">
                        <input id="editCompanyName" type="text" name="companyName" placeholder="请填写客户全称"
                               class="normal-txt">
                        <p class="error hide" id="editCompanyNameInfo">*请填写客户全称</p>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">客户简称：</label>
                    <div class="fl-select fl">
                        <input id="editShortName" type="text" name="shortName" placeholder="请填写客户简称" class="normal-txt">
                        <p class="error hide" id="editShortNameInfo">*请填写客户简称</p>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">所属行业：</label>
                    <div class="fl-select fl">
                        <select class="js-example-basic-single select" id="companyVocationId">
                            <c:forEach items="${companyVocationList}" var="companyVocation">
                                <option id="editCompanyVocation"
                                        value="${companyVocation.id}">${companyVocation.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">联系人：</label>
                    <div class="fl-select fl">
                        <input id="editContact" type="text" name="contact" placeholder="请填写联系人" class="normal-txt">
                        <p class="error hide" id="editContactInfo">*请填写联系人</p>
                    </div>
                </div>
                <div class="form line-form">
                    <label class="form_label fl">联系电话：</label>
                    <div class="fl-select fl">
                        <input id="editPhoneNumber" type="text" name="phoneNumber" placeholder="请填写联系电话"
                               class="normal-txt">
                        <p class="error hide" id="editPhoneNumberInfo">*请填写联系电话</p>
                    </div>
                </div>
<div class="form line-form">
    <label class="form_label fl">联系人邮箱：</label>
    <div class="fl-select fl">
        <input id="editEmail" type="text" name="email" placeholder="请填写联系人邮箱" class="normal-txt">
        <p class="error hide" id="editEmailInfo">*请填写联系人邮箱</p>
        <input id="companyId" type="hidden"/>
    </div>
</div>
</div>
<div class="modal-footer">
    <button id="saveCompany" type="button" class="btn btn-primary confirmButton">确定</button>
    <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
<!-- edited by xiaokun on 2017-04-01 13:30 end -->
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/company/js/companyList.js?ver=${nowDate}"></script>

