$(function(){
	//广告落地页动态显示 edited by liuxiaolong on 2017/4/12
	$("input[name=adLink]").bind("blur change keyup mouseleave",function(){
		$("#showAdLink").html("");
		$("#showAdLink").html($(this).val());
		
	});
	//广告名字动态显示  edited by liuxiaolong on 2017/4/13
	$("input[name=adName]").bind("blur change keyup mouseleave",function(){
		$("#showAdName").html("");
		$("#showAdName").html($(this).val());
		
	});
	
	//动态显示上传图片  edited by liuxiaolong on 2017/4/13
	$("input[name=up_img]").click(function(){	
		
        new uploadPreview({UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow",callback: function() { 
        	var imgFile=document.getElementById("up_img").value;
        	if(!/\.(PNG|png)$/.test(imgFile)){
        		$("#imgShow").removeAttr("src");
        		$("#img-responsive").src = ADMS.ctx+"/resources/images/ad-eg.jpg";
        		showpromptModal("请上传500*130px png格式图片", false, 1500, false);
              return false;
            }
        	
        } });
        
	        var $input = document.querySelector("#up_img");
			var $img = document.querySelector("#img-responsive");
			$input.addEventListener("change",function(){
				
				var imgFile=document.getElementById("up_img").value;
				
				if(/\.(PNG|png)$/.test(imgFile)){
					var reader = new FileReader(),
					file = this.files[0];
					reader.onload = function(e) {
							var image = new Image();
							image.src = e.target.result;
							image.onload=function(){
									if(image.width != 500 || image.height != 130){
										fill = false;
										$("#imgShow").removeAttr("src");
										$img.src = ADMS.ctx+"/resources/images/ad-eg.jpg";
										showpromptModal("请上传500*130px png格式图片", false, 1500, false);
									}else{
										var img_url = window.URL.createObjectURL($input.files.item(0)); 
										$img.src = img_url;
										$("input[name=imagePath]").val(img_url);
										
									} 
							 
							}
				};
		        reader.readAsDataURL(file);
		        }
			},false);
	});

	//点击提交提交表单
    var lastPath="";
	$("#makeAdSubmit").click(function(){
		var adId = $("input[name=adId]").val();
		var adName = $("input[name=adName]").val();
		var adLink = $("input[name=adLink]").val();
		var adStyle = $("select[name=adStyle]").val();
		// var adTyle = $("select[name=adTyle]").val();
		var imgPath = $("input[name=up_img]").val();
		var trackingCode = $("input[name=trackingCode]").val();
		var imageHiddent = $("input[name=imagePath]").val();
		if(adName?false:true){
			showpromptModal("请输入广告名！", false, 1500, false);
		}else if(imageHiddent?false:true){
			if(imgPath?false:true){
		        showpromptModal("请上传图片！", false, 1500, false);
			}
		}else if(adLink?false:true){
			showpromptModal("请输入落地页链接！", false, 1500, false);
		}else{
			//检测广告是否已存在
			$.ajax( {
				type: "GET",
				async:false,
				contentType : "application/json",
				url: ADMS.ctx + "/ad/checkAdName",
				// data:{"adId":adId,"adName":adName,"adTyle":adTyle},
                data:{"adId":adId,"adName":adName},
				dataType: "json",
				success: function(result) {
					if(result.data.check==1){
						showpromptModal(result['msg'], false, 1500, false);
					}else{
						$.ajaxFileUpload({
							type: "POST",
							async:false,
							contentType : "application/json",
							url: ADMS.ctx + "/ad/saveAdCreateResult",
							data:{"id":adId,"name":adName,"link":adLink,"styleId":adStyle,"trackingCode":trackingCode,"imagePath":imageHiddent},
							dataType: "json",
							fileElementId : 'up_img',
							success:function(resp){
								showpromptModal(resp['msg'], true, 1500, false,function(){
									window.location.href= ADMS.ctx+"/ad/adList";
								});
								},
							error:function(resp){
								showpromptModal(resp['msg']);
							}
						});
					}
					},
				cache: false,
				error:function(result){
					showpromptModal("请求失败");
				}
			});
			}
	});
});