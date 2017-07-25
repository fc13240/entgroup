<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adShelf"/>
<input type="hidden" id="titleSource" value="投放"/>
<input type="hidden" id="companyId" name="companyId" value="${companyId}"/>
<input type="hidden" id="styleId" name="styleId" value="${styleId}"/>
<input type="hidden" id="adName" name="adName" value="${adName}"/>
<input type="hidden" id="parentPageNum" name="parentPageNum" value="${parentPageNum}"/>
<input type="hidden" id="parentPageSize" name="parentPageSize" value="${parentPageSize}"/>
<form id="queryForm" action="${ctx}/adSlot/adShelfOnList" method="POST" class="form demo_form">
    <input type="hidden" id="slotAdId" name="adOrderId" value="${adOrderId}"/>
    <input type="hidden" id="programLevelId" name="programLevelId" value="${programLevelId}"/>
    <input type="hidden" id="currentPageIds" name="currentPageIds" value=""/>
    <input type="hidden" id="chooseIds" name="chooseIds" value="${chooseIds}"/>
    <input type="hidden" id="lastChooseIds" name="lastChooseIds" value="${lastChooseIds}"/>
    <div class="col-lg-12 col-sm-12 main-box" style="margin-bottom: 0;">
        <div class="col-lg-2">
            <label for="demo_label" class="form_label fl">视频平台：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="videoCompanyId" name="videoCompanyId" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${searchList}" varStatus="status">
                        <c:if test="${com.searchType eq 1 }">
                            <option value="${com.id}"
                                ${(videoCompanyId eq com.id)?'selected="selected"':''}>${com.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="demo_label" class="form_label fl">视频类型：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="videoTypeId" name="videoTypeId" tabindex="2">
                    <option value="">全部</option>
                    <c:forEach var="vid" items="${searchList}" varStatus="status">
                        <c:if test="${vid.searchType eq 2 }">
                            <option value="${vid.id}"
                                ${(videoCompanyId eq vid.id)?'selected="selected"':''}>${vid.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="demo_label" class="form_label fl">视频名称：</label>
            <div class="fl select2-box select2-box-line">
                <input type="text" name="videoName" id="videoName" placeholder="输入视频名称" value="${videoName}"
                       class="normal-txt normal-br-txt">
            </div>
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
            <label for="demo_label" class="form_label fl">明星：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="personId" name="personId" tabindex="5">
                    <option value="">全部</option>
                    <c:forEach var="per" items="${searchList}" varStatus="status">
                        <c:if test="${per.searchType eq 5 }">
                            <option value="${per.id}"
                                ${(personId eq per.id)?'selected="selected"':''}>${per.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-2">
            <label for="demo_label" class="form_label fl">其他：</label>
            <div class="fl select2-box select2-box-line">
                <select class="js-example-basic-single" id="labelId" name="labelId" tabindex="6">
                    <option value="">全部</option>
                    <c:forEach var="slt" items="${searchList}" varStatus="status">
                        <c:if test="${slt.searchType eq 3 }">
                            <option value="${slt.id}"
                                ${(labelId eq slt.id)?'selected="selected"':''}>${slt.name}
                            </option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-lg-6 form video-padding-b fr">
            <button type="button" id="sercherAdSlot" class="normal-btn fl txt-btn form-box-btn"
                    style="margin-top: 32px;">确定
            </button>
            <button type="button" id="backAdList" class="normal-btn fl txt-btn form-box-btn" style="margin-top:
                32px;margin-left: 28px">返回
            </button>
        </div>
    </div>
</form>
<div class="main-box col-lg-12 on-top-box">
    <button ${(programLevelId eq 3)?'class="normal-btn form-box-btn form-top-txt"':'class="normal-btn form-box-btn form-top-txt light-grey-btn"'}
            id="slot-hight-price">高价
    </button>
    <button ${(programLevelId eq 2)?'class="normal-btn form-box-btn form-top-txt"':'class="normal-btn form-box-btn form-top-txt light-grey-btn"'}
            id="slot-middle-price">中价
    </button>
    <button ${(programLevelId eq 1)?'class="normal-btn form-box-btn form-top-txt"':'class="normal-btn form-box-btn form-top-txt light-grey-btn"'}
            id="slot-low-price">低价
    </button>
    <button class="normal-btn form-box-btn form-top-txt fr blue-btn" data-toggle="modal" id="delivAdSlots"
            data-target="#myModalDeliv">投放
    </button>
</div>

<div class="col-lg-12 col-xs-12 main-box">
    <div class="col-lg-12">
        <ul class="img-box col-lg-12 no-padding mbthirty" style="overflow: initial;">
            <c:choose>
                <c:when test="${empty adSlotList}">
                    <h5>暂无数据</h5>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${adSlotList}" var="dto">
                        <li class="img-detail-box img-detail-box-b">
                            <img src="${dto.pictureAdress}" onerror="this.src='${ctx}/resources/images/img.jpg'"
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
                            <p class="time-p">${dto.videoPosition}</p>
                            <div class="top-small-box">
                                <div class="top-small-black-bg"></div>
                                <ul class="tag-ul">
                                    <c:choose>
                                        <c:when test="${empty dto.videoAdSlot && empty dto.videoAdSlot.slotLabels && empty dto.videoAdSlot.scenes && empty dto.videoAdSlot.persons}">
                                            <li>无</li>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${dto.videoAdSlot}" var="vas">
                                                <c:if test="${vas.adSlotId eq dto.adSlotId}">
                                                    <c:forEach items="${vas.slotLabels}" var="dtol">
                                                        <li>${dtol.name}</li>
                                                    </c:forEach>
                                                    <c:forEach items="${vas.scenes}" var="scen">
                                                        <li>${scen.name}</li>
                                                    </c:forEach>
                                                    <c:forEach items="${vas.persons}" var="perl">
                                                        <li>${perl.name}</li>
                                                    </c:forEach>
                                                </c:if>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                            <p class="img-detail"><span>${dto.videoName}</span></p>
                            <p class="img-detail img-detail-b"><span>${dto.programType},${dto.videoPlatform}</span></p>

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
<!-- Modal -->
<div class="modal fade" id="myModalDeliv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog small-modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <img src="${ctx}/resources/images/example.png">
                <img id="delic-ad-path" src="" class="ad-img-eg" onerror="this.src='${ctx}/resources/images/eg.jpg'">
            </div>
            <div class="modal-body">
                <div class="modal-border-bottom">
                    <p>广告名称：<span id="ad-name"></span></p>
                    <p>落地页： <span class="blue-font" id="ad-link"></span></p>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消投放</button>
                <button type="button" class="btn btn-primary" id="adSlotDelivPass">投放</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/ad/js/adShelfOnList.js"></script>
 