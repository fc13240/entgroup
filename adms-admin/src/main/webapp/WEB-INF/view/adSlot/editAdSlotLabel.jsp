<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="columnNo" column="editAdSlotLabel"/>
<input type="hidden" id="titleSource" value="广告位加标签"/>
<div class="col-lg-12 col-xs-12 grey-border-bottom main-box">
    <h2>${video.name}
        <button class="normal-btn txt-btn top-btn"
                onclick="window.location.href='javascript:history.go(-1);location.reload();'">
            <%--<img src="${ctx}/resources/images/icon-return.png">--%>
            <span class="">返 回</span></button>
    </h2>
</div>
<div class="col-lg-12 col-xs-12 main-box">
    <h3 class="grey-border-bottom adjust-title"><img src="${ctx}/resources/images/icon-add.png">场景</h3>
    <c:choose>
        <c:when test="${empty sceneList}">
            <h3 class="grey-border-bottom adjust-title">暂无数据</h3>
        </c:when>
        <c:otherwise>
            <c:forEach items="${sceneList}" var="scene">
                <h4 class="adjust-grey-title">${scene.name}</h4>
                <ul class="tag-img-box">
                    <c:forEach items="${scene.adSlotDTOList}" var="dto">
                        <li id="${dto.adSlotId}_adSlotId" class="zTreeModal">
                            <img src="${dto.pictureAddress}" onerror="this.src='${ctx}/resources/images/img.jpg'"
                                 class="full-img">
                            <div class="small-black-bg"></div>
                            <p class="time-p">${dto.videoPositionTime}</p>
                            <div class="top-small-box height-hide">
                                <div class="top-small-black-bg"></div>
                                <ul class="tag-ul">
                                    <c:choose>
                                        <c:when test="${empty dto.labelDTOList}">
                                            <span>暂无标签</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${dto.labelDTOList}" var="label">
                                                <li>${label.labelName}</li>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <h3 class="grey-border-bottom adjust-title"><img src="${ctx}/resources/images/icon-star.png">明星</h3>
    <c:choose>
        <c:when test="${empty personList}">
            <h3 class="grey-border-bottom adjust-title">暂无数据</h3>
        </c:when>
        <c:otherwise>
            <c:forEach items="${personList}" var="person">
                <h4 class="adjust-grey-title">${person.name}</h4>
                <ul class="tag-img-box">
                    <c:forEach items="${person.adSlotDTOList}" var="dto">
                        <li id="${dto.adSlotId}_adSlotId" class="zTreeModal">
                            <img src="${dto.pictureAddress}" onerror="this.src='${ctx}/resources/images/img.jpg'"
                                 class="full-img">
                            <div class="small-black-bg"></div>
                            <p class="time-p">${dto.videoPositionTime}</p>
                            <div class="top-small-box height-hide">
                                <div class="top-small-black-bg"></div>
                                <ul class="tag-ul">
                                    <c:choose>
                                        <c:when test="${empty dto.labelDTOList}">
                                            <span>暂无标签</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${dto.labelDTOList}" var="label">
                                                <li>${label.labelName}</li>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/adSlot/js/editAdSlotLabel.js?ver=${nowDate}"></script>

