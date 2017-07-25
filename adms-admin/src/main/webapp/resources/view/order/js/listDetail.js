$(function(){
	var orderId=$("#orderId").val();
	var companyId=$("#companyId").val();
	$("#adSlots").click(function(){
		var status=$("#status").val();
		var adId = $("#getSlot").val();
		$('#addOrderModal').on('show.bs.modal',function() {
		$.ajax( {
			  type: "GET",
			  async:false,
			  contentType : "application/json",
			  url: ADMS.ctx + "/order/adOrderAdDetail",
			  dataType: "json", 
			  scriptCharset: 'utf-8',
			  data:{"adId":adId,"status":status},
			  success: function(result) {
				  var trs="";
				  trs +="<table><tbody>";
				  if(result.allSlotList!=null){
				  $.each(result.allSlotList, function(key, val) {
						  trs +="<tr><td>"+val.companyName+"</td>";
	                      trs +="<td>"+val.videoName+"</td>";
	                      trs +="<td>"+val.slotTime+"</td></tr>"; 
                  });
				  }else{
					  trs +="<tr><td>该订单已完成暂无广告位</td></tr>";
				  }
				  trs+="</tbody></table>";
			     $("#slotList").html(trs);
			  },
			  cache: false,
			  error:function(result){ 
		      alert("获取广告位失败");
               } 
	        });
		});
	});
	 $('#addOrderModal').on('hide.bs.modal', function () {
		 $("#slotList").empty();
	 });
		});