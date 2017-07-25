<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="editLabel"/>
<input type="hidden" id="titleSource" value="广告位加标签"/>
<form id="queryForm" action="${ctx}/adSlot/editLabel" method="GET" class="form demo_form">
    <div class="col-lg-12 main-box">
        <div class="form col-lg-2">
            <label for="companyId" class="form_label">视频平台</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(editLabelDTO.companyId eq com.id)?'selected="selected"':''}>${com.shortName}</option>
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
                            ${(editLabelDTO.typeId eq type.id)?'selected="selected"':''}>${type.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="videoName" placeholder="输入节目名称" class="fl normal-txt form-box-btn"
                   value="${editLabelDTO.videoName}" tabindex="4">
            <button class="normal-btn fl txt-btn form-box-btn" type="submit">搜索</button>
        </div>
        <div class="col-lg-4 form video-padding-b">
        </div>
    </div>
</form>
<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table>
                <thead>
                <tr>
                    <th>入库日期</th>
                    <th>视频名称</th>
                    <th>视频类型</th>
                    <th>视频平台</th>
                    <th>标签数（附加/原始）</th>
                    <th>操作</th>
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
                        <c:forEach items="${dtoList}" var="dto" >
                            <tr>
                                <td>${dto.created}</td>
                                <td>${dto.videoName}</td>
                                <td>${dto.videoType}</td>
                                <td>${dto.videoPlatform}</td>
                                <td>${dto.labelNum}</td>
                                <td class="btn-td">
                                    <a class="normal-btn blue-btn" style="margin-left: 0px;"
                                       href="${ctx}/adSlot/editAdSlotLabel?videoId=${dto.videoId}">加标签</a>
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
<jsp:include page="../../layouts/panel-pagination.jsp"/>