<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- 提示框Modal -->
<div class="modal fade" id="promptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-backdrop="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="model-main">
                    <img id="promptModalImg" hidden="hidden" src="${ctx}/resources/images/close.png">
                    <span class="blue-right" id="promptModalBody">失败</span>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="myModalTitle">标题</h4>
            </div>
            <div class="modal-body" id="myModalBody">
                主体
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 询问框Modal -->
<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="confirmModalTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="model-main">
                    <img id="confirmModalImg" hidden="hidden" src="${ctx}/resources/images/blue-question.png">
                    <span class="blue-right" id="confirmModalBody"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 小询问框Modal -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" id="smallConfirmModal" role="dialog"
     aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="smallConfirmModalTitle"></h4>
            </div>
            <div class="modal-body">
                <div class="model-main">
                    <img id="smallConfirmModalImg" hidden="hidden" src="${ctx}/resources/images/blue-question.png">
                    <span class="blue-right" id="smallConfirmModalBody"></span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal"
                        style="margin-right: 0px">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- zTreeModal -->
<div class="modal fade" id="zTreeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true"><img src="${ctx}/resources/images/close.png"></span></button>
                <h4 class="modal-title" id="zTreeModalTitle">选择标签</h4>
            </div>
            <div class="modal-body" id="zTreeModalBody">
                <form id="editAdSlotLabelForm">
                    <div class="form line-form line-form-blank">
                        <label for="zTreeSourceId" class="form_label fl"></label>
                        <div class="fl-select fl">
                            <input name="zTreeSourceId" id="zTreeSourceId" class="normal-txt"/>
                        </div>
                    </div>
                    <div class="form line-form line-form-blank">
                        <label for="zTreeSourceName" class="form_label fl"></label>
                        <div class="fl-select fl">
                            <input name="zTreeSourceName" id="zTreeSourceName" class="normal-txt"/>
                        </div>
                    </div>
                    <div class="form line-form line-form-blank">
                        <label for="zTreeSourceName2" class="form_label fl"></label>
                        <div class="fl-select fl">
                            <input name="zTreeSourceName2" id="zTreeSourceName2" class="normal-txt"/>
                        </div>
                    </div>
                    <div class="form line-form">
                        <input type="hidden" name="zTreeResults" id="zTreeResults"/>
                        <label for="zTreeBody" class="form_label fl"></label>
                        <div class="fl-select fl">
                            <ul id="zTreeBody" class="zTree white-box"></ul>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirmButton">确定</button>
                <button type="button" class="btn btn-default cancelButton" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>



