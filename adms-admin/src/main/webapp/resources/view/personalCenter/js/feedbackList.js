/**
 * Created by xiaokun on 2017/4/9 15:19
 * Description: 用户反馈
 */

$(function () {
    // 点击行查看详情
    $(".feedbackDetail").on("click", function () {
        $("#creator").html("");
        $("#publishDate").html("");
        $("#content").text("");
        $("#insertBtns").attr("style", "display: none");

        var clickId = this.id;
        var id = clickId.split("_")[1];
        var path = ADMS.ctx + '/feedback/feedbackDetail';
        $.getJSON(path, $.param({"id": id}), function (resp) {
            var feedback = resp['data']['feedback'];
            $("#creator").html(feedback.user.loginName+"&nbsp;"+feedback.company.companyName);
            $("#publishDate").html(feedback.formatPublishDate);
            $("#content").text(feedback.content);
        });
        $("#content").attr("disabled", "disabled");
        $("#feedbackModal").modal("show");
    });

    // 点击新增反馈(仅用户)
    /*$("#insertFeedback").on("click", function () {
        $("#creator").html("");
        $("#publishDate").html("");
        $("#content").text("");
        $("#content").removeAttr("readonly");
        $("#content").attr("placeholder", "填写反馈内容");
        $("#insertBtns").removeAttr("style");
        $("#content").removeAttr("disabled");
        $("#feedbackModal").modal("show");
    });*/

    // 点击确定(保存新增反馈)
    /*$("#saveFeedback").on("click", function () {
        var feedback = {};
        feedback.content = $("#content").val();
        var path = ADMS.ctx + '/feedback/addFeedback';
        $.getJSON(path, $.param(feedback), function (resp) {
            hideModal('feedbackModal');
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        })
    });*/
})