$(function(){
	//广告列表查看 弹出框 edited by liuxiaolong
	$("#myAdAllsList").find("button").on("click",function(){
		var buttonId = $(this).attr("id");
		var adId = $("#"+buttonId).attr("value");
			$('#myModal').on('show.bs.modal',function() {
				
				$.ajax( {
					  type: "GET",
					  async:false,
					  contentType : "application/json",
					  url: ADMS.ctx + "/ad/adDetail",
					  data:{"adId":adId},
					  dataType: "json",
					  success: function(result) {
						//清空原内用
						   $("#ad-name").html("");
						    $("#ad-link").html("");
						    $("#ad-reason").html("");
						    $("#al-ad-path").attr("src","");
						    $("#audit-unpass").removeAttr("value");
						    $("#audit-pass").removeAttr("value");
						  //添加新内容
						    $("#ad-name").html(result.ad.name);
						    $("#order_name").html(result.orderName);
						    $("#ad-link").html(result.ad.link);
						    $("#al-ad-path").attr("src",result.ad.imagePath);
						    $("#audit-unpass").attr("value",result.adId);
						    $("#audit-pass").attr("value",result.adId);
						    var trs = "";
						    var videoPlatform ="";
						    var videoName ="";
						    var videoPosition ="";
						    $.each(result.adSlotOfAd, function (n, value) {  
						    	  videoPlatform =value.videoPlatform;
						    	  if (videoPlatform == null || videoPlatform == undefined || videoPlatform == '') {
						    		  videoPlatform="无";
						    	   } 
							      videoName =value.videoName;
							      if (videoName == null || videoName == undefined || videoName == '') {
							    	  videoName="无";
						    	   } 
							      videoPosition =value.videoPosition;
							      if (videoPosition == null || videoPosition == undefined || videoPosition == '') {
							    	  videoPosition="无";
						    	   } 
					              trs +="<tr><td>"+ videoPlatform + "</td><td>"+videoName+"</td><td>"+videoPosition+"</td></tr>";
					          });  
					  
						     $("#ad-reason").html(trs);
						},
						 cache: false,
						 error:function(result){ 
							 showpromptModal("请求失败");
                         } 
			        });
			});
		
	});
	
	//审核未通告显示为通告原因
	$('[data-toggle="tooltip"]').tooltip();
	
	//新增广告连接到新增页面
	$("#toMakeNewAd").click(function(){
		window.location.href= ADMS.ctx+"/ad/adCreate";
	});
	
	$(".ad-edit").on("click", function () {
		var adId = $(this).attr("id").split("_")[1];
		window.location.href = ADMS.ctx + "/ad/adCreate?adId=" + adId;
    })
});