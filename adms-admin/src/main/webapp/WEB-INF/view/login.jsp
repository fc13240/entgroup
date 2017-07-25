<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="decorator" content="login"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
    <meta name="renderer" content="webkit"/>
    <title>艺恩</title>
    <link type="image/x-icon" href="${ctx}/resources/images/logo.png" rel="shortcut icon"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/include/bootstrap-3.3.7/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/include/video/css/video-js.css"/>
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
    <style>
        body {
            height: auto;
        }
        .media-wrapper .my-video-dimensions {
            width: 418px;
            height: 230px;
            position: absolute;
            left: 420px;
            top: 60px;
        }

        .media-wrapper {
            position: absolute;
            left: 60px;
            top: 181px;
            background: url(${ctx}/resources/images/video-bg.png) no-repeat center center;
            width: 1294px;
            height: 450px;
        }
    </style>
</head>
<body role="document">
<div class="container login-header">
    <a href="" class="fl"><img src="${ctx}/resources/images/l_logo.jpg" class="fl">
        <h1 class="fl">智能广告植入平台</h1></a>
    <p class="fr blue-font">欢迎登录</p>
</div>
<div class="login-bg container-fluid">
    <div class="media-wrapper">
        <video id="my-video" class="video-js" controls preload="auto" width="640" height="264"
               poster="${ctx}/resources/images/video.jpg" data-setup="{}" autoplay loop>
            <source src="${ctx}/resources/images/video.mp4" type="video/mp4">
        </video>
    </div>
    <div class="container" style="position: relative;">
        <div class="login-box">
            <div class="login-box-t">用户登录</div>
            <div>
                <form id="loginForm" name="loginForm" method="post">
                    <div class="fl-select">
                        <input name="username" id="username" type="text" placeholder="请填写用户名" class="login-txt"
                               tabindex="1">
                    </div>
                    <div class="fl-select">
                        <input name="password" id="password" type="password" placeholder="请填写密码" class="login-txt"
                               tabindex="2">
                        <span class="pwd_img show_pwd_img"></span>
                        <p class="error">${errorMessage}</p>
                    </div>
                </form>
                <div class="piaochecked login-rember-btn" id="rememberMeImg" tabindex="3">
                    <input id="rememberMe" name="rememberMe" type="checkbox" style="height:20px;width:20px;"
                           class="radioclass input">
                </div>
                <p class="login-rember">记住账号</p>
                <input class="login-btn" type="button" value="登录" tabindex="4">
            </div>
        </div>
    </div>
</div>
<div class="container">
    <ul class="login-bottom">
        <li><a href="">关于我们</a>　|　</li>
        <li><a href="">合作伙伴</a>　|　</li>
        <li><a href="">下载中心</a>　|　</li>
        <li><a href="">加入我们</a>　|　</li>
        <li><a href="">联系我们</a>　|　</li>
        <li><a href="">条款规定</a>　|　</li>
        <li><a href="">网站地图</a>　|　</li>
        <li><a href="">关注我们</a>　|　</li>
        <li><a href="">帮助</a></li>
    </ul>
    <p class="login-bottom-p">Copyright 2015 艺恩 EntGroup Inc. 北京 All rights reserved. 京ICP备10031103号-3.</p>
</div>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/ie.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-niceScroll.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/video/js/videojs-ie8.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/include/video/js/video.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common-checkbox.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/main.js"></script>
<script type="text/javascript">
    //启动播放视频
    var myPlayer = videojs('my-video');
    videojs("my-video").ready(function () {
        myPlayer = this;
        myPlayer.play();
    });

    $(function () {
        //密码是否可见切换
        $('.pwd_img').click(function () {
            $(this).prev().attr('type', 'text');
            if ($(this).hasClass('show_pwd_img')) {
                $(this).prev().attr('type', 'text');
                $(this).removeClass('show_pwd_img').addClass('hide_pwd_img');
            } else if ($(this).hasClass('hide_pwd_img')) {
                $(this).prev().attr('type', 'password');
                $(this).removeClass('hide_pwd_img').addClass('show_pwd_img');
            }
        });
        //记住我按钮监听，↑↓切换状态
        $(".login-rember-btn").bind("keydown",function(e){
            // 兼容FF和IE和Opera
            var theEvent = e || window.event;
            var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
            if (code == 38||code == 40) {
                //↑↓执行切换
                $(".login-rember-btn").click();
            }
        });
        //回车监听，触发登陆
        $(document).keyup(function (event) {
            if (event.which == "13") {
                //回车执行查询
                $('.login-btn').click();
            }
        });
        //登录按钮点击事件
        $(".login-btn").one("click", function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var rememberMe = $("#rememberMe").attr("checked");
            if (rememberMe == "checked") {
                $.cookie("rememberUser", "true", {expires: 30}); //存储一个带7天期限的cookie
                $.cookie("username", username, {expires: 30});
                $.cookie("password", password, {expires: 30});
            }
            else {
                $.cookie("rememberUser", "false", {expire: -1});
                $.cookie("username", "", {expires: -1});
                $.cookie("password", "", {expires: -1});
            }
            document.loginForm.action = '${ctx}/login';
            document.loginForm.submit();
        });

    });
    //页面加载完毕后查看cookie中是否有“记住我”信息
    $(document).ready(function () {
        //获取cookie的值
        var rememberMe = $.cookie('rememberUser');
        var username = $.cookie('username');
        var password = $.cookie('password');
        //将获取的值填充入输入框中
        $('#username').val(username);
        $('#password').val(password);
        if (rememberMe == 'true') {//选中保存秘密的复选框
            $("#rememberMeImg").addClass('on_check');
            $("#rememberMe").attr("checked", 'true');
        }
    });
</script>
</body>
</html>
