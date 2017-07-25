/**
 * Created by xiaokun on 2017/4/9 15:52
 * Description: 通知
 */

$(function () {
    // 显示标题栏按钮
    if ($("#userType").length > 0 && $("#userType").val() == "admin") {
        $("#titleButton").html("新增通知");
        $("#titleButtonDiv").removeAttr("hidden");
    }

    // 点击行查看详情
    $(".noticeDetail").on("click", function () {
        $("#myModalLabel").html("通知详情");
        $("#publishDate").html("");
        $("#content").text("");
        $("#insertBtns").attr("style", "display: none");

        var clickId = this.id;
        var id = clickId.split("_")[1];
        var path = ADMS.ctx + '/notice/noticeDetail';
        $.getJSON(path, $.param({"id": id}), function (resp) {
            var notice = resp['data']['notice'];
            $("#noticeTitle").html(notice.title);
            $("#publishDate").html(notice.formatPublishDate);
            $("#content").text(notice.content);
        });
        $("#content").attr("disabled", "disabled");
        $("#noticeModal").modal("show");
    });

    // 新增系统通知
    $("#titleButton").on("click", function () {
        $("#myModalLabel").html("新增通知");
        $("#noticeTitle").html("系统通知");
        $("#publishDate").html("");
        $("#content").text("");
        $("#content").removeAttr("readonly");
        $("#content").attr("placeholder", "填写通知内容");
        $("#insertBtns").removeAttr("style");
        $("#content").removeAttr("disabled");
        $("#noticeModal").modal("show");
    });

    //  此处需要添加$("#content").blur失去焦点触发验证content非空方法
    $("#saveNotice").blur(function () {
        $("#saveNotice").removeAttr("disabled");
    });

    // 点击确定保存
    $("#saveNotice").on("click", function () {
        var path = ADMS.ctx + '/notice/addNotice';
        var content = $("#content").val();
        if(content.trim() == "" || content == null){
            $("#saveNotice").attr("disabled", "disabled");
            return;
        }
        hideModal('noticeModal');
        $.getJSON(path, $.param({"content": content}), function (resp) {
            if (resp['success']) {
                showSuccessModal('保存成功');
            } else {
                showpromptModal('保存失败');
            }
        })
    });
})