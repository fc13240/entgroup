<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adShelf"/>
<input type="hidden" id="titleSource" value="下架"/>
<input type="hidden" id="companyId" name="companyId" value="${companyId}"/>
<input type="hidden" id="styleId" name="styleId" value="${styleId}"/>
<input type="hidden" id="adName" name="adName" value="${adName}"/>
<input type="hidden" id="parentPageNum" name="parentPageNum" value="${parentPageNum}"/>
<input type="hidden" id="parentPageSize" name="parentPageSize" value="${parentPageSize}"/>
<form action="${ctx}/adSlot/adShelfOffList" method="GET" id="queryForm" class="form demo_form">
    <input type="hidden" name="adId" value="${adId}" id="ad-id-export"/>
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="videoCompanyId" class="form_label">视频平台</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="videoCompanyId" name="videoCompanyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(videoCompanyId eq com.id)?'selected="selected"':''}>${com.companyName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="videoName" placeholder="输入视频名称" value="${videoName}"
                   class="fl normal-txt form-box-btn">
            <button class="normal-btn fl form-box-btn">搜索</button>
        </div>
        <div class="col-lg-6 form video-padding-b">
            <button type="button" id="backAdList" class="normal-btn fr form-box-btn" style="margin-left:20px;">返回
            </button>
            <button type="button" class="normal-btn fr blue-btn model-show form-box-btn" data-toggle="modal"
                    id="off-shelf-adslot"
                    data-target="#alert-dialog" style="margin-left:20px;">下架
            </button>
            <button type="button" id="exportExcel" class="normal-btn fr white-btn model-show form-box-btn"
                    style="margin-left:20px;"
                    data-toggle="modal" data-target="#alert-dialog">导出数据
            </button>
        </div>

    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th style="width: 80px;">
                        <div class="piaochecked" id="checkAllAdSlot">
                            <input name="checkAllAdSlot" type="checkbox" style="height:20px;width:20px;"
                                   class="radioclass input">
                        </div>
                    </th>
                    <th>上架日期</th>
                    <th>视频名称</th>
                    <th>视频平台</th>
                    <th>位置</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty offAdList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${offAdList}" var="dto">
                            <tr>
                                <td>
                                    <div class="piaochecked">
                                        <input name="adSlotIdCheck" type="checkbox" style="height:20px;width:20px;"
                                               class="radioclass input" value="${dto.adSlotId}">
                                    </div>
                                </td>
                                <td>
                                    <fmt:formatDate value="${dto.startTime}" type="date" dateStyle="medium"/>
                                </td>
                                <td>${dto.videoName}</td>
                                <td>${dto.videoPlatform}</td>
                                <td>${dto.videoPosition}</td>
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
<div class="modal fade bs-example-modal-sm" tabindex="-1" id="alert-dialog" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="myModalLabel">下架广告</h4>
            </div>
            <div class="modal-body">
                <p class="modal-p" id="offsheltalert"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="offsheltpass">确定</button>
                <button type="button" class="btn btn-default" style="margin-right: 0px;" data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/ad/js/adShelfOffList.js"></script>