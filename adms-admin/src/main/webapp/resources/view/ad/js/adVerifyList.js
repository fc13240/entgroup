$(function(){
	//待审核列表审核 弹出框 edited by liuxiaolong on 2017-03-31
	$("#auditAds").find("button").on("click",function(){
		var auditButtonId = $(this).attr("id");
		var adId = $("#"+auditButtonId).attr("value");
		$('#myModal').on('show.bs.modal',function() {
			$.ajax( {
				  type: "GET",
				  async:false,
				  contentType : "application/json",
				  url: ADMS.ctx + "/ad/getSingleAd",
				  data:{"adId":adId},
				  dataType: "json", 
				  success: function(result) {
					    //清空原内用
					    $("#ad-name").html("");
					    $("#ad-link").html("");
					    $("#aduit-ad-path").removeAttr("src");
					    $("#audit-unpass").removeAttr("value");
					    $("#audit-pass").removeAttr("value");
					    //添加新内容
					    var links = "<a href='"+result.ad.link+"' target='_blank'>"+result.ad.link+"</a>";
					    $("#ad-name").html(result.ad.name);
					    $("#ad-link").html(links);
					    $("#aduit-ad-path").attr("src",result.ad.imagePath);
					    $("#audit-unpass").attr("value",result.adId);
					    $("#audit-pass").attr("value",result.adId);
					     $("#myModal").delegate('.piaochecked', 'click', function(){
					         var chooseNum = checkJust()[1];
					         if(chooseNum > 0){
					 			 $("#audit-unpass").removeAttr("disabled");
					 			 $("#audit-unpass").removeClass("unclick");
					 		}else{
					 			$("#audit-unpass").attr("disabled",true);
					 			 $("#audit-unpass").addClass("unclick");
					 		}
					     });
						 },
						 cache: false,
						 error:function(result){ 
							 showpromptModal("请求失败");
                         } 
		        });
		});
	});
	
	function checkJust(){
		var chooseid="";
		var selectNum=0;
		$("input[name='reasonId'][checked]").each(function(){
 			chooseid += $(this).val()+",";
 			selectNum +=1;
 		});
		return new Array(chooseid,selectNum);
	}
	
	//广告审核弹出框 不合格按钮操作  edited by liuxiaolong on 2017-03-31
	$("#audit-unpass").click(function(){
		var adId = $(this).attr("value");
		var otherReason = $("#otherReason").val();
		var reasonId = checkJust()[0];
		
		$.ajax( {
			  type: "GET",
			  async:false,
			  contentType : "application/json",
			  url: ADMS.ctx + "/ad/saveAdVerifyResult",
			  data:{"adId":adId,"adStatus":3,"reasonIds":reasonId,"otherReason":otherReason},
			  dataType: "json", 
			  success: function(result) {
				     $('#myModal').modal('hide');
                     $("#queryForm").submit();
			  },
			  cache: false,
			  error:function(result){ 
				  showpromptModal("请求失败");
               } 
	        });
		
	});
	
	//广告审核通告按钮事件 edited by liuxiaolong on 2017-03-31
	$("#audit-pass").click(function(){
		var adId = $(this).attr("value");
		$.ajax( {
			  type: "GET",
			  async:false,
			  contentType : "application/json",
			  url: ADMS.ctx + "/ad/saveAdVerifyResult",
			  data:{"adId":adId,"adStatus":2},
			  dataType: "json", 
			  success: function(result) {
				     $('#myModal').modal('hide');
                     $("#queryForm").submit();
			  },
			  cache: false,
			  error:function(result){ 
				  showpromptModal("请求失败");
               } 
	        });
		
	});
	
	
	
});