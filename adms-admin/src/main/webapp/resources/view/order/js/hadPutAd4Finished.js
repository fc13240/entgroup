$(function () {

    //导出数据
    $('.exportExcel').click(function() {
        var adOrderId = $('#adOrderId').val();
        window.location.href = ADMS.ctx+'/order/exportPutAdInfo?adOrderId='+adOrderId+'&method=2';
    });

    //上一步
    $('#goBack').one('click', function () {
        window.location.href = ADMS.ctx + "/order/adOrderList";
    });
});