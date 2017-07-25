$(function () {
    //order-bar样式切换
    var dds = $('.order-bar').children('dd');
    dds.on('click', function () {
        if ($(this).hasClass('order-checked')) {
            $(this).removeClass('order-checked');
            $(this).children('input').removeAttr('checked');
        } else {
            $(this).children('input').attr('checked', 'true');
            $(this).addClass('order-checked');
        }

        //company选择并赋值
        var companyIds = '';
        $(".company").each(function () {
            if ($(this).is(':checked')) {
                companyIds+=($(this).val())+',';
            }
        });
        $("#companyIds").val(companyIds);

        //programType选择并赋值
        var programTypeIds = '';
        $(".programType").each(function () {
            if ($(this).is(':checked')) {
                programTypeIds+=($(this).val())+',';
            }
        });
        $("#programTypeIds").val(programTypeIds);
        $("#queryForm").submit();
    })

    //day选择并赋值
    $('.day').on('click', function () {
        var day = $(this).val();
        $("#day").val(day);
        $("#queryForm").submit();
    })

    $("#comList").click(function () {
        var companyId = $(this).val();
        $.ajax({
            type: "GET",
            async: false,
            contentType: "application/json",
            url: ADMS.ctx + "/order/selectAdStyle",
            data: {
                "companyId": companyId
            },
            dataType: "json",
            scriptCharset: 'utf-8',
            success: function (result) {
                $("#saveAdOrder").removeAttr("value");
                var trs = "";
                trs += "<table  class=" + '"' + 'no-style' + '"' + ">";
                if (result.adListByCompany.length == 0) {
                    var trs1 = "<li style=" + '"' + 'line-height: 162px;width: 100%;text-align: center;color:red;font-size: 16px;' + '"' + ">请先联系客户制作广告，广告审核通过后才可建立订单</li>";
                }
                $.each(result.adListByCompany, function (n, value) {
                    trs += "<tr><td style='width:50px;'><div class='piaochecked on_check '>";
                    trs += "<input name='need_inv' type='checkbox' style='height:20px;width:20px;' class='radioclass input' value="
                        + value.adId
                        + "></td>";
                    trs += "</div>";
                    trs += "<td style='width:100px;'>"
                        + value.adName + "</td><td style='width:100px;'>"
                        + value.styleName + "</td><td style='width:100px;'>"
                        + value.date + "</td>"
                        + "</tr>";
                });
                trs += "</table>";
                $("#ad-id").html(trs);
                $("#ad-id").html(trs1);
                $("#saveOrderModal").delegate('.piaochecked', 'click', function () {
                    $(this).toggleClass("on_check");
                    if ($(this).children("input:first").attr("checked")) {
                        $(this).children("input:first").removeAttr("checked");
                    } else {
                        $(this).children("input:first").attr("checked", 'true');
                    }
                    var chooseNum = checkJust()[1];
                    if (chooseNum > 0 && selectTotal > 0) {
                        $("#saveAdOrder").removeAttr("disabled");
                        $("#saveAdOrder").removeClass("unclick");
                    } else {
                        $("#saveAdOrder").attr("disabled", true);
                        $("#saveAdOrder").addClass("unclick");
                    }
                });
            },
            cache: false,
            error: function (result) {
                alert("获取广告失败");
            }
        });
    });
    var selectTotal = 0

    function checkJust() {
        var adId = "";
        var selectNum = 0;
        $("input[name='need_inv'][checked]").each(function () {
            adId += $(this).val() + ",";
            selectNum += 1;
        });
        return new Array(adId, selectNum);
    }

    // 金额校验
    $("#totalMoney").blur(function () {
        if (!$("#totalMoney").val()) {
            $("#totalMoney").html("*请输入金额");
            $("#saveAdOrder").attr("disabled", "disabled");
            selectTotal = 0;
        } else if (!isDigit($("#totalMoney").val())) {
            $("#totalMoney").html("*请输入数字");
            $("#saveAdOrder").attr("disabled", "disabled");
            selectTotal = 0;
        } else if ($("#totalMoney").val().length > 9) {
            $("#saveAdOrder").attr("disabled", "disabled");
            selectTotal = 0;
        } else {
            var chooseNum = checkJust()[1];
            selectTotal = 1;
            if (chooseNum > 0) {
                $("#saveAdOrder").removeAttr("disabled");
                $("#saveAdOrder").removeClass("unclick");
            }
        }
    });
    // edited by mxy on 2017-04-15 11:47 begin
    $('#selection').one('click', function () {
        //统计选择的视频program
        var result = '';
        //格式："oppId-programId-checked,..."如0-123-0就代表123号视频没有被订单关联且没有被选择，12-123-1就代表123号视频被订单关联且被选择
        var num = 0;//计数
        $(".checkbox-select").each(function () {
            var oppId = $(this).attr('oppId');
            if ($(this).is(':checked')) {
                result += (oppId + '-' + ($(this).val()) + '-1,');
                num++;
            }else {
                result += (oppId + '-' + ($(this).val()) + '-0,');
            }
        });
/*        if(num == 0) {
            showpromptModal('请至少选择一个视频', false, 3000, false);
            return;
        }*/
        var adOrderId = $('#adOrderId').val();
        $.getJSON(ADMS.ctx + '/order/saveSelectionResult', $.param({
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

    //orderSelect页面下一步
    $('#nextStep').on('click', function () {
        var adOrderId = $('#adOrderId').val();
        showSmallConfirmModal('确认选择都已提交？',function(){
            window.location.href = ADMS.ctx + "/order/orderSelectedProgram?adOrderId=" + adOrderId;
        });
    });

    //orderSelected页面下一步
    $('#nextStep2').on('click', function () {
        var adOrderId = $('#adOrderId').val();
        showSmallConfirmModal('确认选择都已提交？',function(){
            window.location.href = ADMS.ctx + "/order/orderSelectedAdSlot?adOrderId=" + adOrderId;
        });
    });


    //orderSelected页面返回
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