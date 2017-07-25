<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adList"/>
<input type="hidden" id="titleSource" value="制作广告"/>
<input type="hidden" name="adId" value="${ad.id}"/>
       <div class="col-lg-12 main-box">
            <div class="col-lg-8">
                <div class="grey-header">编辑广告</div>
                <div class="grey-border-box">
                    <div class="ad-form">

                        <%--updata by sth on 2017/6/1--%>
                        <div class="col-lg-12 ad-form-line">
                            <label for="demo_label" class="form_label fl" tabindex="-1">广告名称</label>
                            <div class="fl ad-input">
                                <input type="text" name="adName" placeholder="请填写广告名称" value="${ad.name}" class="normal-txt">
                            </div>
                        </div>
                        <%--移除--%>
                        <%--<div class="col-lg-12 ad-form-line">--%>
                            <%--<label for="" class="form_label fl">广告品类</label>--%>
                            <%--<div class="fl ad-input">--%>
                                <%--<select class="js-example-basic-hide-search" name="adTyle">--%>
                                    <%--<c:forEach var="adt" items="${adTypeList}" varStatus="status">--%>
				                        <%--<option value="${adt.id}"--%>
				                            <%--${(ad.typeId eq adt.id)?'selected="selected"':''}>${adt.name}--%>
				                        <%--</option>--%>
				                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="col-lg-12 ad-form-line">
                            <label for="" class="form_label fl">广告样式</label>
                            <div class="fl ad-input">
                                <select class="js-example-basic-hide-search" name="adStyle">
                                    <c:forEach var="ads" items="${adStyleList}" varStatus="status">
				                        <option value="${ads.id}"
				                            ${(ad.styleId eq ads.id)?'selected="selected"':''}>${ads.name}
				                        </option>
				                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <%--落地页链接提至上传图片之前--%>
                        <div class="col-lg-12 ad-form-line">
                            <label for="demo_label" class="form_label fl" tabindex="-1">落地页链接</label>
                            <div class="fl ad-input">
                                <input type="text" name="adLink" placeholder="请填写落地页链接地址" value="${ad.link}" class="normal-txt">
                            </div>
                        </div>

                        <%--新增监测代码--%>
                        <div class="col-lg-12 ad-form-line">
                            <label for="demo_label" class="form_label fl" tabindex="-1">监测代码</label>
                            <div class="fl ad-input">
                                <input type="text" name="trackingCode" placeholder="请填写监测代码" value="${ad.trackingCode}" class="normal-txt">
                            </div>
                        </div>

                        <div class="col-lg-12 ad-form-line">
                            <label for="" class="form_label fl"></label>
                            <div class="fl ad-input">
                                <div class="up-top">
                                    <img src="${ctx}/resources/images/radio.png" class="fl">
                                    <span class="fl">上传图片<span class="blue-font">（请上传500*130px   png格式图片）</span></span>
                                </div>
                                <div id="imgdiv" class="imgdiv">
                                    
                                    <c:if test="${empty ad.imagePath}">
				                      <img id="imgShow" class="imgShow"/>
				                    </c:if>
				                    <c:if test="${not empty ad.imagePath}">
				                      <img id="imgShow" class="imgShow" src="${ad.imagePath}"/>
				                    </c:if>
                                    <input type="file" id="up_img" name ="up_img" />
                                    <input type="hidden" id="imagePath" name ="imagePath" value="${lastImagePath}" />
                                 </div>
                            </div>
                        </div>
                        <%--<div class="col-lg-12 ad-form-line">--%>
                            <%--<label for="demo_label" class="form_label fl" tabindex="-1">落地页链接</label>--%>
                            <%--<div class="fl ad-input">--%>
                                <%--<input type="text" name="adLink" placeholder="请填写落地页链接" value="${ad.link}" class="normal-txt">--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--顺序位置修改--%>
                        <div class="col-lg-12 ad-form-line" style="text-align: left;text-indent: 100px;">
                            <button type="button" class="btn btn-primary" id="makeAdSubmit" style="margin-right: 30px">确定</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:history.go(-1)">返回</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="grey-header">预览广告</div>
                <div class="grey-border-box">
                    <img src="${ctx}/resources/images/example.png" class="img-responsive" style="border-radius: 6px;">
                    <c:if test="${empty ad.imagePath}">
                      <img id="img-responsive" src=" " class="ad-img-eg-s" style="position:absolute;width:120px;top:calc(100% - 140px);left:calc(50% - 60px);display:none">
                    </c:if>
                    <c:if test="${not empty ad.imagePath}">
                      <img id="img-responsive" src="${ad.imagePath}" onerror="this.src='${ctx}/resources/images/eg.jpg'" class="ad-img-eg-s" style="position:absolute;width:120px;top:calc(100% - 140px);left:calc(50% - 60px);" >
                    </c:if>
                    
                    <div class="ad-detail">
                        <p>广告名称：<span id="showAdName">${ad.name}</span></p>
                        <p>落地页： <span class="blue-font" id="showAdLink" >${ad.link}</span></p>
                    </div>
                </div>
            </div>
      </div>

<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/ad/js/adCreate.js"></script>