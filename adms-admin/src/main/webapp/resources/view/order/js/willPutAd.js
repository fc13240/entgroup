$(function () {

    var showNums = 0;
    var clickNum = 0;
    var expenses = 0;
    //CheckBox选择效果checkbox
    $(".piaochecked").on("click",function(){
        clickNum = $("#clickNum").val();
        $(this).toggleClass( "on_adSlot" );
        if(!$(this).hasClass("on_adSlot")){
            showNums -= ($(this).children("input:first").attr('showNum')) * 1;
            expenses -= ($(this).children("input:first").attr('expense')) * 1;
        }else {
            showNums += ($(this).children("input:first").attr('showNum')) * 1;
            expenses += ($(this).children("input:first").attr('expense')) * 1;
        }
        $('#showNums').html('预计曝光量：'+showNums);
        $('#clickNums').html('预计曝光量：'+Math.ceil(showNums*clickNum));
        $('#expenses').html('预计消费：'+expenses);
    })

    /* 全选反选按钮 */
    $(".checkAll").on("click",function(){
        showNums = 0;
        expenses = 0;
        if($(this).hasClass('on_adSlot')) {
            $('.piaochecked[for="' + this.id + '"]').addClass('on_adSlot');
            $(".piaochecked").each(function () {
                showNums += (($(this).children("input:first").attr('showNum')) * 1);
                expenses += (($(this).children("input:first").attr('expense')) * 1);
            });
        }else {
            $('.piaochecked[for="' + this.id + '"]').removeClass('on_adSlot')
        }
        $('#showNums').html('预计曝光量：'+showNums);
        $('#clickNums').html('预计曝光量：'+Math.ceil(showNums*clickNum));
        $('#expenses').html('预计消费：'+expenses);
    });

    /* 子选择框联动全选 */
    var $subBox = $(".piaochecked[for]");
    $subBox.click(function () {
        var id = $(this).attr('for');
        if($subBox.length == $('.piaochecked.on_adSlot[for="' + id + '"]').length) {
            $('#'+id).addClass('on_adSlot');
        }else {
            $('#'+id).removeClass('on_adSlot');
        }
    });

    //设置订单是否完成广告位选择
    $('#tag-completed').on('click',function () {
        var selected = 0;
        if(!$(this).hasClass('export-bar-on')){
            selected = 1;
        }
        var adOrderId = $('#adOrderId').val();
        $.getJSON(ADMS.ctx + '/order/saveOrderSelected', $.param({
            'adOrderId': adOrderId,
            'selected': selected
        }, true), function (resp) {
            if (resp['success']) {
                if($('#tag-completed').hasClass('export-bar-on')){
                    $('#tag-completed').removeClass('export-bar-on');
                }else {
                    $('#tag-completed').addClass('export-bar-on');
                }
            } else {
                showpromptModal(resp['msg'], false, 3000, false);
            }
        });
    });

    //导出数据
    $('.exportExcel').click(function() {
        var adOrderId = $('#adOrderId').val();
        window.location.href = ADMS.ctx+'/order/exportPutAdInfo?adOrderId='+adOrderId+'&method=0';
    });

    //删除按钮点击
    $("#selection").click(function () {
        var result = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            result += $(this).val() + ",";
        });
        $.getJSON(ADMS.ctx + '/order/saveSelectionResult2', $.param({
            'adOrderId': 0,
            'result': result
        }, true), function (resp) {
            if (resp['success']) {
                showpromptModal(resp['msg'], true, 3000, true);
            } else {
                showpromptModal(resp['msg'], false, 3000, true);
            }
        });
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
    $(".soldOut").one('click',function () {
        var result = $(this).val();
        var adOrderId = $('#adOrderId').val();
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

    //上一步
    $('#goBack').one('click', function () {
        var adOrderId = $('#adOrderId').val();
        window.location.href = ADMS.ctx + "/order/orderSelectedAdSlot?adOrderId=" + adOrderId;
    });

    //继续添加
    $('#goBack2').one('click', function () {
        var adOrderId = $('#adOrderId').val();
        window.location.href = ADMS.ctx + "/order/orderSelectProgram?adOrderId=" + adOrderId;
    });
    //edited by mxy on 2017-04-15 11:47 end

    $(".select").click(function () {
        if($(this).hasClass('unselected-btn-ml')) {
            $(this).addClass('blue-btn');
            $(this).removeClass('unselected-btn-ml')
            $(this).next().removeAttr("checked");
            $(this).text("选择");
        }else {
            $(this).removeClass('blue-btn');
            $(this).addClass('unselected-btn-ml')
            $(this).next().attr("checked",'true');
            $(this).text("取消选择");
        }
    });
});