$(function () {
    $('.img-box').find('li').mouseover(function () {
        //console.log($(this));
        var tip = $(this).find('.tip-container');
        //显示提示框
        tip.addClass('show');
        //移动提示框至正确水平位置
        var x = -tip.outerWidth();//获取提示框宽度
        tip.css("right", (x-9) + "px");
        //如果元素在最右侧则向左移动移动并改变三角标样式
        if(($(this).parent().children().index($(this))+1)%5==0) {
            tip.css("left",(x-9) + "px");
            tip.css("right",($('.full-img').outerWidth()+9) + "px");
            tip.find('.tip-pointer').css({
                "right":"-14%",
                "border-right-color":"transparent",
                "border-left-color":"#333"
            });
        }
        // console.log(($(this).parent().children().index($(this))+1)%5);
        //移动提示框至正确垂直位置
        var index = tip.find('p').index(tip.find('.blue-font'));//获取加亮信息位置
        var num = tip.find('p').length;//获取信息个数
        tip.css("top",(-index*30+$('.full-img').outerHeight()/2-18.5) + "px");
        //移动三角标至正确垂直位置
        tip.find('.tip-pointer').css("top",(index*30+10) + "px");
    });
    $('.img-box').find('li').mouseout(function () {
        //console.log($(this));
        $(this).find('.tip-container').removeClass('show');
    });

    //高价，中价，低价按钮点击
    $(".slot-price").click(function () {
        $("#programLevelId").val($(this).val());
        $("#queryForm").submit();
    });

    //预投放按钮点击
    $("#selection").click(function () {
        var result = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            result += $(this).val() + ",";
        });
        var adOrderId = $('#adOrderId').val();
        $.getJSON(ADMS.ctx + '/order/saveSelectionResult2', $.param({
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

    //返回按钮
    $("#goBack").click(function () {
        var adOrderId = $("#adOrderId").val();
        window.location.href = ADMS.ctx + "/order/orderSelectedProgram?adOrderId=" + adOrderId;
    });
    //orderSelect页面下一步
    $('#nextStep').on('click', function () {
        var adOrderId = $('#adOrderId').val();
        showSmallConfirmModal('确认完成？',function(){
            window.location.href = ADMS.ctx + "/order/willPutAd?adOrderId=" + adOrderId;
        });
    });

});