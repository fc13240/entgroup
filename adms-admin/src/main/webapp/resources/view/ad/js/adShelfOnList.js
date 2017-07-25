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
    $("#slot-hight-price").click(function () {
        $("#programLevelId").val("3");
        $("#slot-hight-price").removeClass('light-grey-btn');
        $("#slot-middle-price").addClass('light-grey-btn');
        $("#slot-low-price").addClass('light-grey-btn');
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        $("#chooseIds").val(chooseid);

        var currentPageIds = "";
        $("input[name='adSlotIds']").each(function () {
            currentPageIds += $(this).val() + ",";
        });
        $("#currentPageIds").val(currentPageIds);
        $("#queryForm").submit();
    });
    $("#slot-middle-price").click(function () {
        $("#programLevelId").val("2");
        $("#slot-middle-price").removeClass('light-grey-btn');
        $("#slot-hight-price").addClass('light-grey-btn');
        $("#slot-low-price").addClass('light-grey-btn');
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        $("#chooseIds").val(chooseid);

        var currentPageIds = "";
        $("input[name='adSlotIds']").each(function () {
            currentPageIds += $(this).val() + ",";
        });
        $("#currentPageIds").val(currentPageIds);
        $("#queryForm").submit();

    });
    $("#slot-low-price").click(function () {
        $("#programLevelId").val("1");
        $("#slot-low-price").removeClass('light-grey-btn');
        $("#slot-hight-price").addClass('light-grey-btn');
        $("#slot-middle-price").addClass('light-grey-btn');
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        $("#chooseIds").val(chooseid);

        var currentPageIds = "";
        $("input[name='adSlotIds']").each(function () {
            currentPageIds += $(this).val() + ",";
        });
        $("#currentPageIds").val(currentPageIds);
        $("#queryForm").submit();

    });


    //获取选中的广告位ID
    function checkJust() {
        var chooseid = "";
        var selectNum = 0;
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
            selectNum += 1;

        });
        return new Array(chooseid, selectNum);
    }

    //投放按钮点击
    $("#delivAdSlots").click(function () {
        var adId = $("#slotAdId").val();
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        var selectNum = $("#lastChooseIds").val() + chooseid;
        if (selectNum.length > 0) {
            $('#myModalDeliv').on('show.bs.modal', function () {
                $.ajax({
                    type: "GET",
                    async: false,
                    contentType: "application/json",
                    url: ADMS.ctx + "/ad/getSingleAd",
                    data: {"adId": adId},
                    dataType: "json",
                    success: function (result) {
                        //清空原内用
                        $("#ad-name").html("");
                        $("#ad-link").html("");
                        $("#delic-ad-path").removeAttr("src");
                        //添加新内容
                        $("#ad-name").html(result.ad.name);
                        $("#ad-link").html(result.ad.link);
                        $("#delic-ad-path").attr("src", result.ad.imagePath);
                        if (selectNum.length > 0) {
                            $("#adSlotDelivPass").removeAttr("disabled");
                        } else {
                            $("#adSlotDelivPass").attr("disabled", true);
                        }
                    },
                    cache: false,
                    error: function (result) {
                        showpromptModal("请求失败");
                    }
                });
            });
        } else {
            $(this).removeAttr("data-target");
            $(this).removeAttr("data-toggle");
            showpromptModal("请选择广告位！", true, 1500, false, function () {
                $("#delivAdSlots").attr("data-target", "#myModalDeliv");
                $("#delivAdSlots").attr("data-toggle", "modal");
            });

        }


    });

    //投放按钮
    $("#adSlotDelivPass").click(function () {
        var adId = $("#slotAdId").val();
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        var ids = $("#lastChooseIds").val() + chooseid;
        $.ajax({
            type: "GET",
            async: false,
            contentType: "application/json",
            url: ADMS.ctx + "/adSlot/adShelfOn",
            data: {"adId": adId, "ids": ids},
            dataType: "json",
            success: function (resp) {
                showSuccessModal(resp['msg']);
                hideModal('myModalDeliv');
                $("#queryForm").submit();
            },
            cache: false,
            error: function (result) {
                showpromptModal(resp['msg']);
            }
        });
    });

    //点击下一页时给隐藏域赋值已达到保留以前选中的状态
    $(".pagination").click(function () {
        var chooseid = "";
        $("input[name='adSlotIds'][checked]").each(function () {
            chooseid += $(this).val() + ",";
        });
        $("#chooseIds").val(chooseid);

        var currentPageIds = "";
        $("input[name='adSlotIds']").each(function () {
            currentPageIds += $(this).val() + ",";
        });
        $("#currentPageIds").val(currentPageIds);

    });

    //点击搜索时清空隐藏域，已达到重新选中的目的
    $("#sercherAdSlot").click(function () {
        $("#chooseIds").val("");
        $("#lastChooseIds").val("");
        $("#queryForm").submit();
    });

    //返回按钮
    $("#backAdList").click(function () {
        var companyId = $("#companyId").val();
        var styleId = $("#styleId").val();
        var adName = $("#adName").val();
        var parentPageNum = $("#parentPageNum").val();
        var parentPageSize = $("#parentPageSize").val();
        window.location.href = ADMS.ctx + "/ad/adShelf?pageNum=" + parentPageNum + "&pageSize=" + parentPageSize + "&companyId=" + companyId + "&styleId=" + styleId + "&adName=" + adName;
    });

});