$(function() {
	$(".js-example-basic-hide-search").select2({
		minimumResultsForSearch : Infinity
	});

	 $('#company').on("change", function(){
				var companyId = $(this).val();
				$("#company").removeAttr("value");
				$("#adStyle").removeAttr("value");
				$("#adOrder").removeAttr("value");
				$("#ad").removeAttr("value");
				$('#adOrder').select2("destroy");
				$('#adOrder').empty();
				$('#adStyle').select2("destroy");
				$('#adStyle').empty();
				$('#ad').select2("destroy");
				$('#ad').empty();
				if(companyId==""){
					$("#adStyle").append("<option value=" + '""' + ">全部</option>");
					$('#adStyle').select2();
					$("#ad").append("<option value=" + '""' + ">全部</option>");
					$('#ad').select2();
					$("#adOrder").append("<option value=" + '""' + ">全部</option>");
					$('#adOrder').select2();
				}else{
				$.ajax({
					type : "GET",
					async : false,
					contentType : "application/json",
					url : ADMS.ctx + "/dataStatistics/selectOrder",
					data : {
						"companyId" : companyId,
					},
					dataType : "json",
					scriptCharset : 'utf-8',
					success : function(result) {
						var trs = "";
						var trs1 = "";
						var trs2="";
						if (result.adStyleList.length == 0) {
							trs += "<option value=" + '""' + ">全部</option>";
							trs1 += "<option value=" + '""' + ">全部</option>";
							trs2 += "<option value=" + '""' + ">全部</option>";
						}else{
							trs1 += "<option value=" + '""' + ">全部</option>";
							trs += "<option value=" + '""' + ">全部</option>";
							trs2 += "<option value=" + '""' + ">全部</option>";
						} 
						$.each(result.adStyleList, function(n, val) {
							trs += "<option value=" + '"' + val.adStyleId
									+ '">' + val.adStyleName + "</option>";
						});
						$.each(result.adList, function(n, val) {
							trs1 += "<option value=" + '"' + val.adId
									+ '">' + val.adName + "</option>";
						});
						$.each(result.adOrderList, function(n, val) {
							trs2 += "<option value=" + '"' + val.sAdOrderId
							+ '">' + val.sAdOrderId + "</option>";
						});
						
						$("#adStyle").append(trs);
						$('#adStyle').select2();
						$("#ad").append(trs1);
						$('#ad').select2();
						$("#adOrder").append(trs2);
						$('#adOrder').select2();
					},
					cache : false,
					error : function(result) {
						alert("获取失败");
					}
				});
				
				}
			});
	 $('#adOrder').on("change", function(){
			var companyId = $("#company").val();
			var adOrderId = $(this).val();
			$("#company").removeAttr("value");
			$("#adOrder").removeAttr("value");
			$("#adStyle").removeAttr("value");
			$("#ad").removeAttr("value");
			$('#ad').select2("destroy");
			$('#ad').empty();
			$('#adStyle').select2("destroy");
			$('#adStyle').empty();
			if(companyId==""){
				$("#adStyle").append("<option value=" + '""' + ">全部</option>");
				$('#adStyle').select2();
				$("#ad").append("<option value=" + '""' + ">全部</option>");
				$('#ad').select2();
				$("#adOrder").append("<option value=" + '""' + ">全部</option>");
				$('#adOrder').select2();
			}else{
			$.ajax({
				type : "GET",
				async : false,
				contentType : "application/json",
				url : ADMS.ctx + "/dataStatistics/selectStyle",
				data : {
					"companyId" : companyId,
					"adOrderId" : adOrderId
				},
				dataType : "json",
				scriptCharset : 'utf-8',
				success : function(result) {
					var trs = "";
					var trs1 = "";
					if(result.adList.length == 0){
						trs1 += "<option value=" + '""' + ">全部</option>";
						trs += "<option value=" + '""' + ">全部</option>";
					}else{
						trs1 += "<option value=" + '""' + ">全部</option>";
						trs += "<option value=" + '""' + ">全部</option>";
					}
					$.each(result.adList, function(n, val) {
						trs1 += "<option value=" + '"' + val.adId + '">'
								+ val.adName + "</option>";
					});
					$.each(result.adStyleList, function(n, val) {
						trs += "<option value=" + '"' + val.adStyleId + '">'
						+ val.adStyleName + "</option>";
					});
					$("#ad").append(trs1);
					$('#ad').select2();
					$("#adStyle").append(trs);
					$('#adStyle').select2();
				},
				cache : false,
				error : function(result) {
					alert("获取失败");
				}
			});
			}
		});
	 
	 
	 
	 $('#adStyle').on("change", function(){
				var companyId = $("#company").val();
				var adOrderId = $("#adOrder").val();
				var adStyleId = $(this).val();
				$("#company").removeAttr("value");
				$("#adStyle").removeAttr("value");
				$("#adOrder").removeAttr("value");
				$("#ad").removeAttr("value");
				$('#ad').select2("destroy");
				$('#ad').empty();
				if(companyId==""){
					$("#adStyle").append("<option value=" + '""' + ">全部</option>");
					$('#adStyle').select2();
					$("#ad").append("<option value=" + '""' + ">全部</option>");
					$('#ad').select2();
					$("#adOrder").append("<option value=" + '""' + ">全部</option>");
					$('#adOrder').select2();
				}else{
				$.ajax({
					type : "GET",
					async : false,
					contentType : "application/json",
					url : ADMS.ctx + "/dataStatistics/selectAd",
					data : {
						"companyId" : companyId,
						"adOrderId" : adOrderId,
						"adStyleId" : adStyleId
					},
					dataType : "json",
					scriptCharset : 'utf-8',
					success : function(result) {
						var trs = "";
						if(result.adList.length == 0){
							trs += "<option value=" + '""' + ">全部</option>";
						}else{
							trs += "<option value=" + '""' + ">全部</option>";
						}
						$.each(result.adList, function(n, val) {
							trs += "<option value=" + '"' + val.adId + '">'
									+ val.adName + "</option>";
						});
						$("#ad").append(trs);
						$('#ad').select2();
					},
					cache : false,
					error : function(result) {
						alert("获取广告失败");
					}
				});
				}
			});
	var myNum = new Number();
	/*
	 * LINE CHART ----------
	 */
	// LINE randomly generated data
	var adId = $("#ad").val();
	var companyId = $("#company").val();
	var adStyleId = $("#adStyle").val();
	var adOrderId = $("#adOrder").val();
	var days = $("#days").val();
	var show = new Array;
	var show1 = new Array;
	var user = new Array;
	var user1 = new Array;
	var date = new Array;
	var date2 = new Array;
	var date1 = new Array;
	var datepoint = new Array;
	var sumShowCount=0;
	var sumUserCount=0;
		$.ajax({
			type : "GET",
			async : false,
			contentType : "application/json",
			url : ADMS.ctx + "/dataStatistics/adCountChart",
			dataType : "json",
			scriptCharset : 'utf-8',
			data : {
				"adId" : adId,
				"companyId" : companyId,
				"adOrderId" : adOrderId,
				"adStyleId" : adStyleId,
				"days" : days
			},
			success : function(result) {
				sumShowCount=result.sumShow;
				sumUserCount=result.sumUser;
				var trs="";
				var trs1="";
				trs+=sumShowCount+"/万";
				trs1+=sumUserCount+"/万"
				$("#sumShowCount").html(trs);
				$("#sumUserCount").html(trs1);
				$.each(result.countDTOList, function(n, val) {
					
					if (n == 0) {
						myNum = val.dateTime;
					}
					show.push([ n+1, val.sumShowCount]);
					show1[n+1]=val.sumShowCount;
					user.push([ n+1, val.sumUserCount]);
					user1[n+1]=val.sumUserCount;
					var strdate3=null;
					datepoint[n+1] = timeStamp2String(val.dateTime);
					if(days<=30){
						 strdate3 = timeStamp2String(val.dateTime);
					}else{
						strdate3 = timeStamp2String2(val.dateTime);
					}
					if(days<=7){
						date.push([ n+1, strdate3 ]);
						date2.push([ n+1, strdate3 ]);
					}
				
					if(days>7){
						date.push([ n+1, strdate3 ]);
						if(n%5==0){
							date2.push([ n+1, strdate3 ]);
						}
					}
					
				});
			},
			cache : false,
			error : function(result) {
				alert("获取广告位失败");
			}
		});
		// alert(myNum);
		var line_data1 = {
			label : "用户量",
			data : user,
			color : "#00bcd4"
		};
		var line_data2 = {
			label : "曝光量",
			data : show,
			color : "#ff635c"
		};
		function timeStamp2String(time) {
			var datetime = new Date();
			datetime.setTime(time);
			var year = datetime.getFullYear();
			var month = datetime.getMonth() + 1;
			var date = datetime.getDate();
			var hour = datetime.getHours();
			var minute = datetime.getMinutes();
			var second = datetime.getSeconds();
			var mseconds = datetime.getMilliseconds();
			return year + "-" + month + "-" + date;
		};
		function timeStamp2String2(time) {
			var datetime = new Date();
			datetime.setTime(time);
			var year = datetime.getFullYear();
			var month = datetime.getMonth() + 1;
			var date = datetime.getDate();
			var hour = datetime.getHours();
			var minute = datetime.getMinutes();
			var second = datetime.getSeconds();
			var mseconds = datetime.getMilliseconds();
			return  month + "-" + date;
		};
		$.plot("#line-chart", [ line_data1, line_data2 ], {
			legend: {
				     show: false
				 },
			grid : {
				hoverable : true,
				borderColor : "#f3f3f3",
				borderWidth : 1,
				tickColor : "#f3f3f3",
			},
			series : {
				shadowSize : 0,
				lines : {
					show : true,
				},
				points : {
					show : true
				}

			},
			lines : {
				fill : false,
				color : [ "#3c8dbc", "#f56954" ]
			},
			yaxis : {
				show : true,
				min: 0,
			},
			xaxis : {
				ticks : date2,
				autoscaleMargin: 0.01
			}
		});
		// Initialize tooltip on hover
		$('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
			position : "absolute",
			display : "none",
			opacity : 0.8
		}).appendTo("body");
		$("#line-chart").bind(
				"plothover",
				function(event, pos, item) {
					if (item) {
						var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);
						var b = x.toString();
						var c = parseInt(b);
						var a = datepoint[c];
						var y1=show1[c];
						var y2= user1[c];
						$("#line-chart-tooltip").html(
								a+"<br/>" + "曝光量：" + y1+"万" +"<br/>"+ "用户量："+y2+"万").css({
							top : item.pageY + 5,
							left : item.pageX + 5
						}).fadeIn(200);
					} else {
						$("#line-chart-tooltip").hide();
					}

				});

	/* END LINE CHART */
});