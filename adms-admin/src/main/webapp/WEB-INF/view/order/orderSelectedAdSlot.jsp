<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="orderSelectedAdSlot"/>
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<input type="hidden" id="titleSource" value="新建订单-选择广告位"/>
<form id="queryForm" action="${ctx}/order/orderSelectedAdSlot" method="POST" class="form demo_form">
    <input type="hidden" id="adOrderId" name="adOrderId" value="${adOrderId}"/>
    <%--<input type="hidden" id="slotAdId" name="slotAdId" value="${slotAdId}"/>--%>
    <div class="col-lg-12 col-sm-12 main-box" style="margin-bottom: 0;">
        <div class="col-lg-2">
            <label for="companyId" class="form_label fl">视频平台：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="companyId" name="companyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${companyList}" varStatus="status">
                        <option value="${com.id}"
                            ${(adSlotDTO.companyId eq com.id)?'selected="selected"':''}>${com.shortName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="programTypeId" class="form_label fl">视频类型：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="programTypeId" name="programTypeId" tabindex="2">
                    <option value="">全部</option>
                    <c:forEach var="type" items="${programTypeList}" varStatus="status">
                        <option value="${type.id}"
                            ${(adSlotDTO.programTypeId eq type.id)?'selected="selected"':''}>${type.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="programId" class="form_label fl">视频名称：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="programId" name="programId" tabindex="2">
                    <option value="">全部</option>
                    <c:forEach var="program" items="${programList}" varStatus="status">
                        <option value="${program.id}"
                            ${(adSlotDTO.programId eq program.id)?'selected="selected"':''}>${program.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-6 form video-padding-b fr">
            <input type="hidden" id="programLevelId" name="programLevelId" value="${adSlotDTO.programLevelId}"/>
            <button class="normal-btn form-box-btn form-top-txt2 slot-price
             ${(adSlotDTO.programLevelId eq 3)?'':'light-grey-btn'}"
                    id="slot-hight-price" type="button" value="3">高价
            </button>
            <button class="normal-btn form-box-btn form-top-txt2 slot-price
            ${(adSlotDTO.programLevelId eq 2)?'':'light-grey-btn'}"
                    id="slot-middle-price"type="button" value="2">中价
            </button>
            <button class="normal-btn form-box-btn form-top-txt2 slot-price
             ${(adSlotDTO.programLevelId eq 1)?'':'light-grey-btn'}"
                    id="slot-low-price" type="button" value="1">低价
            </button>
            <button id="nextStep" type="button"
                    class="normal-btn white-btn form-box-btn2 fr order-adjust-mr"
                    style="margin-left: 20px;">下一步</button>
            <button id="goBack" type="button" class="normal-btn white-btn form-box-btn2 fr"
                    style="margin-left: 20px;">上一步
            </button>
        </div>
    </div>
    <div class="col-lg-12 col-sm-12 main-box grey-border-bottom">
        <div class="col-lg-2">
            <label for="sceneId" class="form_label fl">场景：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="sceneId" name="sceneId" tabindex="4">
                    <option value="">全部</option>
                    <c:forEach var="scene" items="${sceneList}" varStatus="status">
                        <optgroup label="${scene.name}">
                            <c:forEach var="scene2" items="${scene.scenes}" varStatus="status">
                                <option value="${scene2.id}"
                                    ${(sceneId eq scene2.id)?'selected="selected"':''}>${scene2.name}
                                </option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="personId" class="form_label fl">明星：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="personId" name="personId" tabindex="5">
                    <option value="">全部</option>
                    <c:forEach var="person" items="${personList}" varStatus="status">
                        <option value="${person.id}"
                            ${(personId eq person.id)?'selected="selected"':''}>${person.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="objectId" class="form_label fl">物品：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="objectId" name="objectId" tabindex="6">
                    <option value="">全部</option>
                    <c:forEach var="object" items="${objectList}" varStatus="status">
                        <option value="${object.id}"
                            ${(object eq object.id)?'selected="selected"':''}>${object.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="labelId" class="form_label fl">其他：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="labelId" name="labelId" tabindex="7">
                    <option value="">全部</option>
                    <c:forEach var="slotLabe" items="${slotLabelList}" varStatus="status">
                        <option value="${slotLabe.id}"
                            ${(labelId eq slotLabe.id)?'selected="selected"':''}>${slotLabe.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-4 form video-padding-b fr">
            <button type="button" id="sercherAdSlot" class="normal-btn fl txt-btn form-box-btn"
                    style="margin-top: 32px;">确定
            </button>
            <button class="normal-btn form-box-btn form-top-txt fr blue-btn buy-car" id="selection"
                    type="button" style="margin-top: 32px;">加入预投放
                <i>${previewNum}</i>
            </button>
        </div>
    </div>
</form>

<div class="col-lg-12 col-xs-12 main-box">
    <div class="col-lg-12">
        <ul class="img-box col-lg-12 no-padding mbthirty" style="overflow: initial;">
            <c:choose>
                <c:when test="${empty dtoList}">
                    <h5>暂无数据</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${dtoList}" var="dto">
                        <li class="img-detail-box img-detail-box-b">
                            <img src="${dto.pictureAddress}" onerror="this.src='${ctx}/resources/images/img.jpg'"
                                 class="full-img">
                            <c:choose>
                                <c:when test="${empty lastChooseIds}">
                                    <input type="checkbox" name="adSlotIds" class="img-checkbox"
                                           value="${dto.adSlotId}">
                                    <div class="black-bg hide"></div>
                                    <img src="${ctx}/resources/images/choose.png" class="choose-img hide">
                                </c:when>
                                <c:otherwise>

                                    <c:if test="${fn:contains(lastChooseIds,dto.adSlotId)}">
                                        <input type="checkbox" name="adSlotIds" class="img-checkbox"
                                               value="${dto.adSlotId}" checked="checked">
                                        <div class="black-bg"></div>
                                        <img src="${ctx}/resources/images/choose.png" class="choose-img">
                                    </c:if>
                                    <c:if test="${!fn:contains(lastChooseIds,dto.adSlotId)}">
                                        <input type="checkbox" name="adSlotIds" class="img-checkbox"
                                               value="${dto.adSlotId}">
                                        <div class="black-bg hide"></div>
                                        <img src="${ctx}/resources/images/choose.png" class="choose-img hide">
                                    </c:if>

                                </c:otherwise>
                            </c:choose>

                            <div class="small-black-bg"></div>
                            <p class="time-p">${dto.videoPositionTime}</p>
                            <div class="top-small-box">
                                <div class="top-small-black-bg"></div>
                                <ul class="tag-ul">
                                    <c:choose>
                                        <c:when test="${empty dto.labelNames}">
                                            <li>无</li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${dto.labelNames}" var="labelName">
                                                <li>${labelName}</li>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                            <p class="img-detail"><span>${dto.videoName}</span></p>
                            <p class="img-detail img-detail-b"><span>${dto.programTypeName},${dto.companyName}</span></p>

                            <!-- 右侧浮动三角框 -->
                            <div class="tip-container" id="${dto.adSlotId}">
                                <span class="tip-content">
                                <c:choose>
                                    <c:when test="${empty dto.videoAdSlot}">
                                        <p><span></span><span>无</span></p>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${dto.videoAdSlot}" var="dtos" varStatus="status">
                                            <c:if test="${(dto.adSlotId ne dtos.adSlotId && !empty dtos.adName)||(dto.adSlotId eq dtos.adSlotId)}">
                                                <p  ${(dto.adSlotId eq dtos.adSlotId)?'class="blue-font"':''}>
                                                    <span>${dtos.videoPosition}</span>
                                                    <span>
                                                      <c:if test="${dto.adSlotId eq dtos.adSlotId }">当前广告位</c:if>
                                                      <c:if test="${dto.adSlotId ne dtos.adSlotId }">${dtos.adName}</c:if>
                                                    </span>
                                                </p>
                                            </c:if>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                </span>
                                <span class="tip-pointer">
                                </span>
                            </div>
                        </li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/order/js/orderSelectedAdSlot.js?ver=${nowDate}"></script>
 