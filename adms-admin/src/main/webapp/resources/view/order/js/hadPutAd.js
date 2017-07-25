$(function () {

    //导出数据
    $('.exportExcel').click(function() {
        var adOrderId = $('#adOrderId').val();
        window.location.href = ADMS.ctx+'/order/exportPutAdInfo?adOrderId='+adOrderId+'&method=1';
    });

    //投放按钮点击
    $("#nextStep").one('click',function () {
        var result = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            result += $(this).val() + ",";
        });
        if(result=="") {
            showpromptModal("请至少选择一个广告位", false, 3000, false);
        }
        var adOrderId = $('#adOrderId').val();
        $.getJSON(ADMS.ctx + '/order/savePutInResult', $.param({
            'method': 1,
            'adOrderId': adOrderId,
            'result': result
        }, true), function (resp) {
            if (resp['success']) {
                showpromptModal(resp['msg'], true, 3000, true);
            } else {
                showpromptModal(resp['msg'], false, 3000, true);
            }
        });
    });

    //下架按钮点击
    $(".soldOut").on('click',function () {
        var result = $(this).val();
        var adOrderId = $('#adOrderId').val();
        showSmallConfirmModal('确定下架该广告位的广告吗？',function(){
            $.getJSON(ADMS.ctx + '/order/savePutInResult', $.param({
                'method': 0,
                'adOrderId': adOrderId,
                'result': result
            }, true), function (resp) {
                if (resp['success']) {
                    showpromptModal(resp['msg'], true, 3000, true);
                } else {
                    showpromptModal(resp['msg'], false, 3000, true);
                }
            });
        });
    });

    //上一步
    $('#goBack').one('click', function () {
        window.location.href = ADMS.ctx + "/order/adOrderList";
    });
});