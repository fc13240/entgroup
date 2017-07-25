<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adVerify"/>
<input type="hidden" id="titleSource" value="广告审核"/>
<form id="queryForm" action="${ctx}/ad/adVerify" method="GET" class="form demo_form">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="companyId" class="form_label">客户</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(companyId eq com.id)?'selected="selected"':''}>${com.companyName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="adName" id="adName" placeholder="输入广告名称" value="${adName}"
                   class="fl normal-txt form-box-btn">
            <button class="normal-btn fl txt-btn form-box-btn">确定</button>
        </div>
        <div class="form col-lg-6">
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="auditAds">
                <thead>
                <tr>
                    <th>制作时间</th>
                    <th>客户</th>
                    <th>广告名称</th>
                    <th>广告样式</th>
                    <th>产品品类</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty adList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${adList}" var="dto">
                            <tr>
                                <td>
                                    <fmt:formatDate value="${dto.created}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${dto.companyName}</td>
                                <td>${dto.adName}</td>
                                <td>${dto.styleName}</td>
                                <td>${dto.typeName}</td>
                                <td class="btn-td">
                                    <button class="normal-btn blue-btn" id="chooseAdIds${dto.adId}" value="${dto.adId}"
                                       data-toggle="modal"
                                       data-target="#myModal" style="margin-left: 0">审核
                                    </button>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog small-modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <img src="${ctx}/resources/images/example.png">
                <img id="aduit-ad-path" src="" class="ad-img-eg" onerror="this.src='${ctx}/resources/images/eg.jpg'">
            </div>
            <div class="modal-body">
                <div class="modal-border-bottom">
                    <p>广告名称：<span id="ad-name"></span></p>
                    <p>落地页： <span class="blue-font" id="ad-link"></span></p>
                </div>
                <ul class="modal-grey" id="ad-reason">
                    <c:forEach items="${adReasonTemplateList}" var="ret">
                        <li>
                            <div class="piaochecked">
                                <input name="reasonId" type="checkbox" style="height:20px;width:20px;"
                                       class="radioclass input" value="${ret.id}">
                            </div>
                            <p>${ret.content}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default unclick" disabled id="audit-unpass">不合格</button>
                <button type="button" class="btn btn-primary" id="audit-pass">合格</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/ad/js/adVerifyList.js"></script>
