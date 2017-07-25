// JavaScript Document
/**
 * Created by mxy on 2017-03-30 15:03.
 */
$(function () {
    //提交广告位交筛选结果
    $('#saveScreenAdSlotResults').one('click', function () {
        var videoId = $('#videoId').val();
        var results = "";
        $("input.img-checkbox[checked='checked']").each(function () {
            results+=this.id+","
        })
        var unchooseResults = "";
        $("input.img-checkbox:not([checked='checked'])").each(function () {
            unchooseResults+=this.id+","
        })
        /*$.getJSON(ADMS.ctx + '/adSlot/saveScreenAdSlotResults', $.param({'videoId': videoId, 'results': results, 'unchooseResults': unchooseResults}, true), function (resp) {
            if (resp['success']) {
                showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });*/

        var data = {
            'videoId': videoId,
            'results': results,
            'unchooseResults': unchooseResults
        }

        $.ajax({
            type: "POST",
            async:false,
            url: ADMS.ctx + "/adSlot/saveScreenAdSlotResults",
            data:{
                'videoId': videoId,
                'results': results,
                'unchooseResults': unchooseResults
            },
            dataType: "json",
            success: function(resp) {
                if (resp['success']) {
                    showSuccessModal(resp['msg']);
                } else {
                    showpromptModal(resp['msg'], false, 1500, true);
                }
            },
            error:function(resp){
                showpromptModal("提交广告位筛选结果失败");
            }
        });
    });
    //提交广告位交筛选状态
    $('#saveScreenAdSlotStatus').one('click', function () {
        var videoId = $('#videoId').val();
        $.getJSON(ADMS.ctx + '/adSlot/saveScreenAdSlotStatus', $.param({'videoId': videoId}, true), function (resp) {
            if (resp['success']) {
                $('#saveScreenAdSlotResults').trigger('click');
                //showSuccessModal(resp['msg']);
            } else {
                showpromptModal(resp['msg']);
            }
        });

        /*$.ajax({
            type: "POST",
            async:false,
            contentType : "application/json",
            url: ADMS.ctx + "/adSlot/saveScreenAdSlotStatus",
            data:{'videoId': videoId},
            dataType: "json",
            success: function(resp) {
                $('#saveScreenAdSlotResults').trigger('click');
            },
            cache: false,
            error:function(resp){
                showpromptModal(resp['msg'], false, 1500, true);
            }
        });*/
    });
})