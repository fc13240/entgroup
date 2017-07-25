/**
 * Created by xiaokun on 2017/4/8 14:56
 * Description: 个人中心——基本信息
 */

$(function () {
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

    //失去焦点时触发的事件
    $('#newPassword').blur(function (){
        if($('#newPassword').val()==$('#oldPassword').val()) {
            $('#newPasswordInfo').removeClass("succeed").addClass("error");
            $('#newPasswordInfo').text('*不可与旧密码相同');
            return;
        }
        if (isPasswd($('#newPassword').val())) {
            var i = checkPasswordStrong($('#newPassword').val());
            $('#newPasswordInfo').removeClass("error").addClass("succeed");
            if(i==0) {
                $('#newPasswordInfo').removeClass("succeed").addClass("error");
                $('#newPasswordInfo').text('*密码长度6-20位，不可有中文字符');
            }else if(i==1) {
                $('#newPasswordInfo').text('密码强度：弱');
            }else if(i==2) {
                $('#newPasswordInfo').text('密码强度：中');
            }else if(i==3) {
                $('#newPasswordInfo').text('密码强度：强');
            }else if(i==4) {
                $('#newPasswordInfo').text('密码强度：很强');
            }
        } else {
            $('#newPasswordInfo').removeClass("succeed").addClass("error");
            $('#newPasswordInfo').text('*密码长度6-20位，不可有中文字符');
        }
    });
    //失去焦点时触发的事件
    $('#newPasswordAgain').blur(function (){
        if($('#newPassword').val()==$('#newPasswordAgain').val()) {
            $('#newPasswordAgainInfo').removeClass("error").addClass("succeed");
            $('#newPasswordAgainInfo').text('两次输入密码一致');
            $("#confirm").removeAttr("disabled");
            return;
        } else {
            $('#newPasswordAgainInfo').removeClass("succeed").addClass("error");
            $('#newPasswordAgainInfo').text('*两次输入密码不一致');
            $("#confirm").attr("disabled", "disabled");
            return;
        }
    });

    // 弹窗显示
    $(".changPassword").on("click", function () {
        $("#changePasswordModal").modal("show");
    });

    // 密码校验
    $("#confirm").on("click", function () {
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var checkPath = ADMS.ctx + '/user/checkPassword';
        var changePath = ADMS.ctx + '/user/changePassword';
        $.getJSON(checkPath, $.param({"oldPassword":oldPassword}), function (resp) {
            if (resp['success']) {
                $("#oldPasswordInfo").html(resp['msg']);
                $("#oldPasswordInfo").attr("class", "succeed");
                $("#oldPasswordInfo").removeAttr("style");
                $.getJSON(changePath, $.param({"newPassword": newPassword}), function (resp) {
                    if (resp['success']) {
                        hideModal('changePasswordModal');
                        // 修改成功退出登录
                        showpromptModal(resp['msg'], true, 0, false, function () {
                            window.location.href = ADMS.ctx + '/login';
                        });
                    } else {
                        showpromptModal(resp['msg']);
                    }
                });
            } else {
                $("#oldPasswordInfo").html(resp['msg']);
                $("#oldPasswordInfo").attr("class", "error");
                $("#oldPasswordInfo").removeAttr("style");
            }
        });
    });
})