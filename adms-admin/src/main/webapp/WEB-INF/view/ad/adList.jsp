<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<input type="hidden" id="columnNo" column="adList"/>
<input type="hidden" id="titleSource" value="广告列表"/>
<form id="queryForm" action="${ctx}/ad/adList" method="GET" class="form demo_form">
    <div class="col-lg-12 main-box">
        <%--<div class="form col-lg-2">
            <label for="adStatus" class="form_label">状态</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="adStatus" name="adStatus" tabindex="1">
                    <option value="">全部</option>
                    <option value="1" ${(adStatus eq 1)?'selected="selected"':''}>待审核</option>
                    <option value="2" ${(adStatus eq 2)?'selected="selected"':''}>待上架</option>
                    <option value="3" ${(adStatus eq 3)?'selected="selected"':''}>已上架</option>
                    <option value="4" ${(adStatus eq 4)?'selected="selected"':''}>未通过</option>
                </select>
            </div>
        </div>--%>
        <%--<div class="form col-lg-2">
            <label for="adStatus" class="form_label">广告样式</label>
            <div class="fl select2-box select2-box-top">
                <select class="js-example-basic-single" id="adStyle" name="adStyle" tabindex="1">
                    <option value="">全部</option>
                    <c:forEach var="com" items="${styleList}" varStatus="status">
                        <option value="${com.id}"
                            ${(adStyle eq com.id)?'selected="selected"':''}>${com.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>--%>
        <div class="col-lg-4 form video-padding-b">
            <input type="text" name="adName" id="adName" placeholder="输入广告名称" value="${ad.name}"
                   class="fl normal-txt form-box-btn">
            <button class="normal-btn fl txt-btn form-box-btn">搜索</button>
        </div>
        <div class="col-lg-4 fr form video-padding-b">
            <button type="button" class="normal-btn fr blue-btn form-box-btn" id="toMakeNewAd">制作广告</button>
        </div>
    </div>

</form>

<div class="col-lg-12 main-box">
    <div class="col-lg-12">
        <div class="table_wrapper">
            <table id="myAdAllsList">
                <thead>
                <tr>
                    <th>提交日期</th>
                    <th>广告名称</th>
                    <th>广告样式</th>
                    <%--<th>产品类型</th>--%>
                    <%--<th>状态</th>--%>
                    <th>所属订单</th>
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
                        <c:forEach items="${adList}" var="ad">
                            <tr>
                                <td>
                                    <fmt:formatDate value="${ad.created}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>${ad.name}</td>
                                <td>${ad.adStyle.name}</td>
                                <%--<td>${dto.typeName}</td>--%>

                                <%--<td>
                                    <c:if test="${dto.adStatus==1}">待审核</c:if>
                                    <c:if test="${dto.adStatus==2}">待上架</c:if>
                                    <c:if test="${dto.adStatus==3}">已上架</c:if>
                                    <c:if test="${dto.adStatus==4}">
                                        <span type="button" class="tooltip-btn" data-toggle="tooltip"
                                              data-placement="right" title="${dto.adReasonTemplates}">未通过</span>
                                    </c:if>

                                </td>--%>
                                <td>${ad.adOrderName != null ? ad.adOrderName : "无"}</td>
                                <td class="btn-td">
                                    <%--<c:if test="${ad.adOrderId != null}">
                                        <button class="normal-btn blue-btn" id="chooseAds${ad.id}"
                                                value="${ad.id}" style="margin-left: 0" data-toggle="modal"
                                                data-target="#myModal" }>查看
                                        </button>
                                    </c:if>
                                    <c:if test="${ad.adOrderId == null}">
                                        <a class="normal-btn blue-btn red-btn"
                                           href="${ctx}/ad/adCreate?adId=${ad.id}" style="margin-left: 0">修改</a>
                                    </c:if>--%>
                                    <button class="normal-btn blue-btn ${ad.adOrderId==null?'disabled':''}" id="chooseAds${ad.id}"
                                            value="${ad.id}" style="margin-left: 0" data-toggle="modal"
                                            data-target="#myModal" } ${ad.adOrderId==null?'disabled="disabled"':''}>查看</button>
                                    <button id="editAd_${ad.id}" class="ad-edit normal-btn blue-btn white-btn" style="margin-left: 0">修改</button>
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
                <img id="al-ad-path" src="" class="ad-img-eg" onerror="this.src='${ctx}/resources/images/eg.jpg'">
            </div>
            <div class="modal-body">
                <div class="modal-border-bottom">
                    <p>广告名称：<span id="ad-name"></span></p>
                    <p>订单名称：<span id="order_name"></span></p>
                    <p>落地页： <span class="blue-font" id="ad-link"></span></p>
                </div>
                <div class="table_wrapper grey_table_wrapper">
                    <table class="modal-th">
                        <thead>
                        <tr>
                            <th>视频平台</th>
                            <th>视频名称</th>
                            <th>位置</th>
                        </tr>
                        </thead>
                    </table>
                    <div class="modal-table">
                        <table>
                            <tbody id="ad-reason">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../../layouts/panel-pagination.jsp"/>
<jsp:include page="../../layouts/panel-model.jsp"/>
<script type="text/javascript" src="${ctx}/resources/view/ad/js/adList.js"></script>