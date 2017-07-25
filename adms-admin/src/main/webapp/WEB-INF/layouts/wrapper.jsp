<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <%--edited by mxy on 2017-03-28 10:38 begin--%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="mobile-web-app-capable" content="yes">
    <title>艺恩</title>
    <link type="image/x-icon" href="${ctx}/resources/images/logo.png" rel="shortcut icon">
    <meta name="description" content="Formstone &middot; A Collection of Modular Front End Components.">
    <meta name="msapplication-TileColor" content="#da532c">
    <meta name="theme-color" content="#455a64">

    <link type="text/css" rel="stylesheet" href="${ctx}/resources/include/bootstrap-3.3.7/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/zTreeStyle.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/include/select2/css/select2.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/global.css"/>


    <!--[if lte IE 8]>
    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            overflow: scroll
        }

        .section-btn {
            display: none;
        }
    </style>
    <![endif]-->
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="${ctx}/resources/js/ie8.js"></script><![endif]-->
    <!--[if lt IE 9]>
    <script src="${ctx}/resources/js/shiv.js"></script>
    <script src="${ctx}/resources/js/respond.js"></script>
    <![endif]-->
    <script type="text/javascript">
        var ADMS = {
            ctx: '${pageContext.request.contextPath}',
            userId: '${loginUser.id}',
            userRealName: '${loginUser.realName}'
        };
    </script>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.validation/jquery.validate.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.validation/localization/messages_zh.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.validation/additional-methods.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.validation/jquery.idcard.validate.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.blockUI.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/jquery.form.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/common-util.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/js/layer-v2.0/layer/layer.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/xiaobai/js/xb.lib.min.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/xiaobai/js/xb.houtai.common.min.js"></script>--%>
    <%--<script type="text/javascript" src="${ctx}/resources/xiaobai/js/xb.houtai.developer.min.js"></script>--%>
    <sitemesh:head/>
</head>
<body role="document" id="body">
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-niceScroll.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-jSelect.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/select2/js/select2.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/select2/js/select2_locale_zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/flot/jquery.flot.time.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/chartjs/chart.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/uploadPreview.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-column.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-checkbox.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-model.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-zTree.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-checkout.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>

<input type="hidden" value="30" id="sessionTimeCount"/>
<!-- 左侧菜单  navigation -->
<div class="navigation js-mobile_navigation" id="leftMenu">
    <jsp:include page="panel-sidebar.jsp"/>
</div>
<!-- 主页面 right-slider -->
<div class="right-slider" id="rightSlider">
    <!-- 页头 panel-header -->
    <nav class="page_header navbar navbar-default navbar-fixed-top">
        <jsp:include page="panel-header.jsp"/>
    </nav>
    <!-- 主页面 page_content -->
    <div class="page_content" id="pageContent">
        <sitemesh:body/>
    </div>
    <!-- 页脚 panel-footer -->
    <%--<div class="footer panel-footer">
                <jsp:include page="panel-footer.jsp"/>
            </div>--%>
</div>
</body>
<script type="text/javascript">

    //    function keepAlive() {
    //        $.post(ADMS.ctx + '/refresh');
    //        console.log("refresh session");
    //        setTimeout(keepAlive, 10*60 * 1000);
    //    }
    ////    keepAlive();
</script>
</html>
