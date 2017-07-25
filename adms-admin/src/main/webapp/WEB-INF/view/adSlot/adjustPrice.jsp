<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="adjustPrice"/>
<input type="hidden" id="titleSource" value="广告位调价"/>
<form id="queryForm" action="${ctx}/adSlot/adjustPrice" method="GET" class="form demo_form">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="companyId" class="form_label">视频平台</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(adjustPriceDTO.companyId eq com.id)?'selected="selected"':''}>${com.shortName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form col-lg-2">
            <label for="typeId" class="form_label">视频类型</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-hide-search" id="typeId" name="typeId" tabindex="2">
                    <option value="">全部</option>
                    <c:forEach var="type" items="${programTypeList}" varStatus="status">
                        <option value="${type.id}"
                            ${(adjustPriceDTO.typeId eq type.id)?'selected="selected"':''}>${type.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form col-lg-2">
            <label for="levelId" class="form_label">价位</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-hide-search" id="levelId" name="levelId" tabindex="3">
                    <option value="">全部</option>
                    <option value="1" ${(adjustPriceDTO.levelId eq 1)?'selected="selected"':''}>低价</option>
                    <option value="2" ${(adjustPriceDTO.levelId eq 2)?'selected="selected"':''}>中价</option>
                    <option value="3" ${(adjustPriceDTO.levelId eq 3)?'selected="selected"':''}>高价</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="programName" placeholder="输入节目名称" class="fl normal-txt form-box-btn"
                   value="${adjustPriceDTO.programName}" tabindex="4">
            <button class="normal-btn fl txt-btn form-box-btn" type="submit">搜索</button>

        </div>
        <div class="col-lg-2 form video-padding-b">
            <button class="normal-btn fr blue-btn form-box-btn" type="button" id="editAdjustPrice">调价</button>
            <input type="hidden" name="programIds" id="programIds"/>
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
                        <div class="piaochecked checkAll" id="checkbox1">
                            <input name="need_inv" type="checkbox" style="height:20px;width:20px;"
                                   class="radioclass">
                        </div>
                    </th>
                    <th>节目名称</th>
                    <th>视频类型</th>
                    <th>视频平台</th>
                    <th>集数</th>
                    <th>广告位数量</th>
                    <th>价位</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${empty dtoList}">
                        <tr>
                            <td colspan="16">暂无数据</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${dtoList}" var="dto">
                            <tr>
                                <td>
                                    <div class="piaochecked checkAll" for="checkbox1">
                                        <input name="need_inv" type="checkbox" style="height:20px;width:20px;"
                                               class="radioclass adjustPrice" id="${dto.programId}">
                                    </div>
                                </td>
                                    <%--<td>${dto.created}</td>--%>
                                <td>${dto.programName}</td>
                                <td>${dto.programType}</td>
                                <td>${dto.programPlatform}</td>
                                <td>${dto.videoNum}</td>
                                <td>${dto.adSlotNum}</td>
                                <td>${dto.price}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- 选择价位Modal -->
<div class="modal fade bs-example-modal-sm" id="adjustPriceModal" tabindex="-1" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="adjustPriceModalLabel">改价</h4>
            </div>
            <div class="modal-body">
                <div class="center-block" style="width: 200px;">
                    <label for="newLevelId" class="form_label">价位选择</label>
                    <div class="select2-box select2-box-top">
                        <select class="js-example-basic-single" id="newLevelId" name="newLevelId">
                            <option value="1">低价</option>
                            <option value="2">中价</option>
                            <option value="3">高价</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" style="margin-right: 0px;"
                        data-dismiss="modal">取消
                </button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/adSlot/js/adjustPrice.js?ver=${nowDate}"></script>


