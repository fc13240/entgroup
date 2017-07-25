$(function() {
    function goBack() {
        window.history.go(0)
    }
    var selectAdId;
    $("#addOrder").on("click", function () {
    	$.getJSON(ADMS.ctx + "/order/selectAdList", function (resp) {
    		if (resp['success']) {
				var adList = resp['data']['adList'];
                $("#saveAdOrder").removeAttr("value");
				var trs = "<table class="+'"'+'no-style'+'"'+">";
				$.each(adList, function (i, ad) {
                    trs += "<tr><td style='width:50px;'><div class='piaochecked on_check' value="
                        + ad.id
                        + "></div></td>";
                    /*trs += "<input name='need_inv' type='checkbox' style='height:20px;width:20px;' class='radioclass input' value="
                        + ad.id
                        + "></td>";*/
                    trs += "</div>";
                    trs += "<td style='width:100px;'>"
                        + ad.name +"</td><td style='width:100px;'>"
                        + ad.adStyle.name +"</td><td style='width:100px;'>"
                        + ad.createdString +"</td>"
                        + "</tr>";
                })
                trs+="</table>";
                $("#ad-id").html(trs);
				$("#saveOrderModal").modal("show");

                $(".piaochecked").on("click", function () {
                    var flag = $(this).hasClass("on_check");
                	$(".piaochecked").removeClass("on_check");
                    $(".piaochecked").addClass("on_check");
                	if (flag) {
                        selectAdId = $(this).attr("value");
                        $(this).removeClass("on_check");
					} else {
                        $(this).addClass("on_check");
					}
                })
			} else {
				showpromptModal(resp['msg'], false, 1500, true);
			}
        })
    })


    /*var piaocheckeds = $(".piaochecked");
    $.each(piaocheckeds, function () {
		if (!$(this).hasClass("on_check")) {
			selectAdId = $(this).attr("value");
			console.info(selectAdId);
		}
    })*/

	var selectTotal=0

	function checkJust(){
		var adId="";
		var selectNum=0;
		$("input[name='need_inv'][checked]").each(function(){
			adId += $(this).val()+",";
 			selectNum +=1;
 		});
		return new Array(adId,selectNum);
	}

	// edited by mxy on 2017-04-15 11:47 begin
	$('#saveAdOrder').one('click', function() {

        // 金额校验
        if (!$("#totalMoney").val()) {
            $("#totalMoney").html("*请输入金额");
            selectTotal=0;
            return;
        } else if (!isDigit($("#totalMoney").val())) {
            $("#totalMoney").html("*请输入数字");
            selectTotal=0;
            return;
        }else if($("#totalMoney").val().length> 9){
            selectTotal=0;
            return;
        }else{
            var chooseNum = checkJust()[1];
            selectTotal=1;
            /*if(chooseNum>0){
                $("#saveAdOrder").removeAttr("disabled");
                $("#saveAdOrder").removeClass("unclick");
            }*/
        }

		var companyId = $('#comList').val();
		var remark = $('#remark').val();
		var adId = selectAdId;
		var totalMoney = $("#totalMoney").val();
		var totalName = $("#totalName").val();
		var beginTime = $("#beginTime").val();
		var endTime = $("#endTime").val();

		$.getJSON(ADMS.ctx + '/order/saveAdOrder', $.param({
			'adId' : adId,
			'remark' : encodeURIComponent(remark),
			'totalMoney' : totalMoney,
			'orderName' : totalName,
			'beginTime' : beginTime,
			'endTime' : endTime
		}, true), function(resp) {
			 hideModal('saveOrderModal');
			if (resp['success']) {
				showpromptModal(resp['msg'],true,3000,false,function(){
					window.location.href = ADMS.ctx + "/order/orderSelectProgram?adOrderId=" + resp['data']['adOrderId'];
				});
			} else {
				showpromptModal(resp['msg'],false,3000,true);
			}
		});
	});

	//edited by mxy on 2017-04-15 11:47 end
	$("#updateOrderSlot").click(function(){
		var companyId = $('#comList').val();
		$.ajax( {
			  type: "GET",
			  async:false,
			  contentType : "application/json",
			  url: ADMS.ctx + "/order/updateOrderSlot",
			  dataType: "json", 
			  scriptCharset: 'utf-8',
			  success: function(resp) {
				  showpromptModal(resp['msg'],true,3000,false,function(){
					  window.location.href = ADMS.ctx + "/order/adOrderList?companyId=" + companyId;
				  });
			  },
			  cache: false,
			  error:function(result){ 
		      alert("修改广告位失败");
             } 
	        });
	});

	$(".ad_order_check").on("click", function () {
		var adOrderId = $(this).attr("id").split("_")[1];
		$.getJSON( ADMS.ctx + "/order/adOrderCheck", $.param({"adOrderId":adOrderId}), function (resp) {
			if (resp['success']) {
				showpromptModal(resp['msg'], true, 1500, true);
			} else {
				showpromptModal(resp['msg'], false, 1500, true);
			}
        })
    })
});