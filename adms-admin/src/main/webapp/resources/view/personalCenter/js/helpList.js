/**
 * Created by xiaokun on 2017/4/18 17:45
 * Description: 帮助管理
 */

$(function () {
    // 标题栏按钮显示
    var title = "我要反馈";
    if ($("#userType").length > 0) {
        title = "新增帮助";
    }
    $("#titleButton").html(title);
    $("#titleButtonDiv").removeAttr("hidden");

    $("#titleButton").on("click", function (e) {
        if ($("#titleButton").html() == "新增帮助") {
            helpModal(e);
        // 我要反馈
        } else {
            $("#creator").html("");
            $("#publishDate").html("");
            $("#feedback").text("");
            $("#feedback").removeAttr("readonly");
            $("#feedback").attr("placeholder", "填写反馈内容");
            $("#feedbackBtns").removeAttr("style");
            $("#feedbackModal").modal("show");
        }
    })

    $(".helpEdit").on("click", function (e) {
        helpModal(e);
    });

    // 帮助新增/修改
    function helpModal(e) {
        $("#title").removeAttr("disabled");
        $("#helpContent").removeAttr("readonly");
        $("#helpBtns").removeAttr("style");

        $("#myModalLabel").html("帮助信息");
        $("#helpTitle").val("");
        $("#helpContent").text("");
        $("#helpId").val("");
        var btnId = e.target.id;
        // 新增
        if (btnId == "titleButton" && $("#titleButton").html() == "新增帮助") {
            $("#helpId").val("insertHelp");
            $("#helpModal").modal("show");
            // 修改
        } else {
            helpDetail(btnId);
        }
    }

    // 查看帮助详情
    $(".help").click(function (e) {
        e.stopPropagation();
        var btnId = e.target.id;
        var id = btnId.split("_")[1];
        var index = btnId.split("_")[2];
        var path = ADMS.ctx + "/help/helpDetail";
        $.getJSON(path, $.param({"id": id}), function (resp) {
            if (resp['success']) {
                var help = resp['data']['help'];
                $("#myModalLabel").html("帮助信息");
                $("#helpTitle").html(index+". "+help.title);
                $("#helpContent").text(help.content);
                $("#helpId").val(help.id);
                $("#helpModal").modal("show");
            } else {
                showpromptModal(resp['msg']);
            }
        });
        $("#title").attr("disabled", "disabled");
        $("#helpContent").attr("readonly", "readonly");
        $("#helpBtns").attr("style", "display: none");

    });

    // 点击新增帮助
    $("#saveHelp").on("click", function () {
        if ($("#helpTitle").val().trim() == "" || $("#helpTitle").val() == null || $("#helpContent").val().trim() == "" || $("#helpContent").val() == null) {
            $("#saveHelp").attr("disabled", "disabled");
            return;
        }
        var help = {};
        help.id = $("#helpId").val();
        help.title = $("#helpTitle").val();
        help.content = $("#helpContent").val();
        if ($("#helpId").val() == "insertHelp") {
            delete help.id;
        }
        // 发送请求
        var path = ADMS.ctx + "/help/saveHelp";
        $("#helpModal").modal("hide");
        $.getJSON(path, $.param(help), function (resp) {
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });
    })

    // 点击新增反馈(仅用户)
    /*$("#insertFeedback").on("click", function () {
        $("#creator").html("");
        $("#publishDate").html("");
        $("#feedback").text("");
        $("#feedback").removeAttr("readonly");
        $("#feedback").attr("placeholder", "填写反馈内容");
        $("#feedbackBtns").removeAttr("style");
        $("#feedbackModal").modal("show");
    });*/

    // 点击确定(保存新增反馈)
    $("#saveFeedback").on("click", function () {
        if ($("#feedback").val().trim() == "" || $("#feedback").val() == null) {
            $("#saveFeedback").attr("disabled", "disabled");
            return;
        }
        var feedback = {};
        feedback.content = $("#feedback").val();
        var path = ADMS.ctx + '/feedback/addFeedback';
        $.getJSON(path, $.param(feedback), function (resp) {
            hideModal('feedbackModal');
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        })
    });

    // 非空校验
    $("#feedback").blur(function () {
        $("#saveFeedback").removeAttr("disabled");
        if ($("#feedback").val().trim() == "" || $("#feedback").val() == null) {
            $("#saveFeedback").attr("disabled", "disabled");
            return;
        }
    });
    $("#helpTitle").blur(function () {
        $("#saveHelp").removeAttr("disabled");
        if ($("#helpTitle").val().trim() == "" || $("#helpTitle").val() == null) {
            $("#saveHelp").attr("disabled", "disabled");
            return;
        }
    });
    $("#helpContent").blur(function () {
        $("#saveHelp").removeAttr("disabled");
        if ($("#helpContent").val().trim() == "" || $("#helpContent").val() == null) {
            $("#saveHelp").attr("disabled", "disabled");
            return;
        }
    });
});

function helpDetail(id) {
    id = id.split("_")[1];
    var path = ADMS.ctx + "/help/helpDetail";
    $.getJSON(path, $.param({"id": id}), function (resp) {
        if (resp['success']) {
            var help = resp['data']['help'];
            $("#myModalLabel").html("帮助信息");
            $("#helpTitle").val(help.title);
            $("#helpContent").text(help.content);
            $("#helpId").val(help.id);
            $("#helpModal").modal("show");
        } else {
            showpromptModal(resp['msg']);
        }
    });
}