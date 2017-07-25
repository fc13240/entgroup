// JavaScript Document
/**
 * Created by mxy on 2017-03-31 21:05.
 * Description
 */
$(function () {
    //打开调价模块
    $('#editAdjustPrice').on('click', function () {
        var programIds = "";
        $("input.adjustPrice[checked='checked']").each(function () {
            programIds+=this.id && parseInt(this.id, 10)+","
        })
        if(programIds=="") {
            showpromptModal("请至少选择一个节目",null,3000,false);
            return;
        }
        $('#programIds').val(programIds);
        $('#adjustPriceModal').modal('show');
    });
    //保存设置后的价格
    $("#adjustPriceModal").find(".confirmButton").one("click", function () {
        var programIds = $("#programIds").val();
        var newLevelId = $("#newLevelId").val();
        var newLevelName = $("#newLevelId option:selected").text();
        $.getJSON(ADMS.ctx + '/adSlot/saveAdjustPrice', $.param({'programIds': programIds, 'newLevelId': newLevelId}, true), function (resp) {
            hideModal('adjustPriceModal');
            if (resp['success']) {
                showSuccessModal('设置'+newLevelName+'成功');
            } else {
                showpromptModal('设置'+newLevelName+'失败');
            }
        });
    });
})
