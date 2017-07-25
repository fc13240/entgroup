$(function(){
	//下架复选框全选	edited by liuxiaolong on 2017-03-29
	$("#checkAllAdSlot").click(function(){
		if($("input[name='checkAllAdSlot']").attr("checked")){
			$("input[name='adSlotIdCheck']").removeAttr("checked");
			$(".piaochecked").removeClass("on_check");
			$("input[name='adSlotIdCheck']").attr("checked","true");
			$(".piaochecked").addClass("on_check");
		}else{
			$("input[name='adSlotIdCheck']").removeAttr("checked");
			$(".piaochecked").removeClass("on_check");
		}
		
	});
	//广告位下架导出操作 edited by liuxiaolong on 2017-03-31
	$("#exportExcel").click(function(){
		var adId = $("#ad-id-export").val();
		var exportAdSlotIds = "";
		var exportChooseNum = 0;
		$("input[name='adSlotIdCheck'][checked]").each(function(){
			exportChooseNum += 1;
			exportAdSlotIds += $(this).val()+",";
		});
		$('#alert-dialog').on('show.bs.modal',function() {
			if(exportChooseNum>0){
				$("#myModalLabel").html("");
	        	$("#offsheltalert").html("");
	        	$("#myModalLabel").html("导出数据");
				$("#offsheltalert").html("确定要导出这"+exportChooseNum+"条数据吗？");
				$("#offsheltpass").click(function(){
					$('#alert-dialog').modal('hide');
					$("#queryForm").append(
		        	   "<form action='"+ADMS.ctx+"/adSlot/adShelfOffExport' method='POST' id='export-offshout-adslot'>"
		        	   +"<input type='hidden' name='adSlotIds' value='"+exportAdSlotIds+"'>"
		        	   +"<input type='hidden' name='adId' value='"+adId+"'> </form>"		
		        	);
		        	$("#export-offshout-adslot").submit();
				});
	        	
	        }else{
	        	$("#myModalLabel").html("");
	        	$("#offsheltalert").html("");
	        	$("#myModalLabel").html("导出数据");
	        	$("#offsheltalert").html("请选择广告位！");
				$("#offsheltpass").click(function(){
					$('#alert-dialog').modal('hide');
				});
	        }
		});
        
		 
	});
	
	//广告位下架弹出框  edited by liuxiaolong on 2017-03-31
	$("#off-shelf-adslot").click(function(){
		$('#alert-dialog').on('show.bs.modal',function() {
			var chooseNum = 0;
			var reasonsid = "";
			$("input[name='adSlotIdCheck'][checked]").each(function(){
				chooseNum += 1;
				reasonsid += $(this).val()+",";
			});
			if(chooseNum>0){
				$("#myModalLabel").html("");
	        	$("#offsheltalert").html("");
	        	$("#myModalLabel").html("下架广告");
				$("#offsheltalert").html("确定下架这"+chooseNum+"条广告吗？");
				$("#offsheltpass").click(function(){
					$.ajax( {
						  type: "GET",
						  async:false,
						  contentType : "application/json",
						  url: ADMS.ctx + "/adSlot/adShelfOff",
						  data:{"ids":reasonsid},
						  dataType: "json", 
						  success: function(result) {
							     $('#alert-dialog').modal('hide');
							     $("#queryForm").submit();
						  },
						  cache: false,
						  error:function(result){ 
							  showpromptModal("请求失败");
			               } 
				        });
				});
			}else{
				$("#myModalLabel").html("");
	        	$("#offsheltalert").html("");
	        	$("#myModalLabel").html("下架广告");
				$("#offsheltalert").html("请选择要下架的广告位！");
				$("#offsheltpass").click(function(){
					$('#alert-dialog').modal('hide');
				});
			}
		});
	});
	
	//返回按钮
	$("#backAdList").click(function(){
		var companyId = $("#companyId").val();
		var styleId = $("#styleId").val();
		var adName = $("#adName").val();
		var parentPageNum = $("#parentPageNum").val();
		var parentPageSize = $("#parentPageSize").val();
		window.location.href= ADMS.ctx+"/ad/adShelf?pageNum="+parentPageNum+"&pageSize="+parentPageSize+"&companyId="+companyId+"&styleId="+styleId+"&adName="+adName;
	});
});