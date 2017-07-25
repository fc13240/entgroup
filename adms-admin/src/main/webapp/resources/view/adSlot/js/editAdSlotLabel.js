// JavaScript Document
/**
 * Created by mxy on 2017-03-30 10:29.
 */
$(function () {
    //设置标签-加载标签树
    $('.zTreeModal').on('click', function () {
        var adSlotId = this.id && parseInt(this.id, 10);//取roleId
        $.getJSON(ADMS.ctx + '/slotLabel/initSlotLabel', $.param({'adSlotId': adSlotId}, true), function (resp) {
            if (resp['success']) {
                zNodes = resp['data']['treeNodes'];
                $('#zTreeSourceId').val(adSlotId);
                openZTreeModalSelect("zTreeBody","标签列表：");
                $.fn.zTree.init($("#zTreeBody"), setting, zNodes);
                $('#zTreeModal').modal('show');
            } else {
                showpromptModal("查询失败");
            }
        });
    });
    //保存设置后的标签
    $("#zTreeModal").find(".confirmButton").one("click", function () {
        var videoId = $("#zTreeSourceId").val();
        var results = $("#zTreeResults").val();
        $.getJSON(ADMS.ctx + '/adSlot/saveEditAdSlotLabel', $.param({'videoId': videoId, 'results': results}, true), function (resp) {
            hideModal('zTreeModal');
            if (resp['success']) {
                showSuccessModal('保存成功');
            } else {
                showpromptModal("保存失败");
            }
        });
    });
})