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
	// ===========================================================================================饼图
	var adId = $("#ad").val();
	var companyId = $("#company").val();
	var adStyleId = $("#adStyle").val();
	var days = $("#days").val();
	var show = new Array;
	var user = new Array;
	$.ajax({
		type : "GET",
		async : false,
		contentType : "application/json",
		url : ADMS.ctx + "/dataStatistics/platformCountChart",
		dataType : "json",
		scriptCharset : 'utf-8',
		data : {
			"adId" : adId,
			"companyId" : companyId,
			"adStyleId" : adStyleId,
			"days" : days
		},
		success : function(result) {
			var trs = "";
			var sumShowCount="";
			var sumUserCount="";
			$.each(result.AdPlatCountList,
					function(n, val) {
				sumShowCount+="<li>"+val.companyName+":"+val.dShowCount+"</li>";
				sumUserCount+="<li>"+val.companyName+":"+val.dUserCount+"</li>";
						trs += "<li><span class='canvas-circle fl' ";
						trs += "style=" + '"' + 'border-color:' + val.color
								+ '"' + ">";
						trs += "</span><span class='fl'>" + val.companyName
								+ "</span></li>";
						show.push({
							'value' : val.dShowCount,
							'label' : val.companyName,
							'color' : val.color
						});
						user.push({
							'value' : val.dUserCount,
							'label' : val.companyName,
							'color' : val.color
						});
					});
			$("#sumShowCount").html(sumShowCount);
			$("#sumUserCount").html(sumUserCount);
			$("#collorShow").html(trs);
			$("#collorUser").html(trs);
		},
		cache : false,
		error : function(result) {
			alert("获取广告位失败");
		}
	});

	// -------------
	// - PIE CHART -
	// -------------
	// Get context with jQuery - using jQuery's .get() method.
	var pieChartCanvas1 = $("#pieChart1").get(0).getContext("2d");
	var pieChart1 = new Chart(pieChartCanvas1);
	var PieData1 = show;

	var pieOptions1 = {
		// Boolean - Whether we should show a stroke on each segment
		segmentShowStroke : true,
		// String - The colour of each segment stroke
		segmentStrokeColor : "#fff",
		// Number - The width of each segment stroke
		segmentStrokeWidth : 2,
		// Number - The percentage of the chart that we cut out of the
		// middle
		percentageInnerCutout : 50, // This is 0 for Pie charts
		// Number - Amount of animation steps
		animationSteps : 100,
		// String - Animation easing effect
		animationEasing : "easeOutBounce",
		// Boolean - Whether we animate the rotation of the Doughnut
		animateRotate : true,
		// Boolean - Whether we animate scaling the Doughnut from the
		// centre
		animateScale : false,
		// Boolean - whether to make the chart responsive to window
		// resizing
		responsive : true,
		// Boolean - whether to maintain the starting aspect ratio or
		// not when responsive, if set to false, will take up entire
		// container
		maintainAspectRatio : true,
		// String - A legend template
		legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
	};
	// Create pie or douhnut chart
	// You can switch between pie and douhnut using the method below.
	pieChart1.Doughnut(PieData1, pieOptions1);

	// pieChart2
	var pieChartCanvas2 = $("#pieChart2").get(0).getContext("2d");
	var pieChart2 = new Chart(pieChartCanvas2);
	var PieData2 = user;
	var pieOptions2 = {
		// Boolean - Whether we should show a stroke on each
		// segment
		segmentShowStroke : true,
		// String - The colour of each segment stroke
		segmentStrokeColor : "#fff",
		// Number - The width of each segment stroke
		segmentStrokeWidth : 2,
		// Number - The percentage of the chart that we cut out
		// of the middle
		percentageInnerCutout : 50, // This is 0 for Pie charts
		// Number - Amount of animation steps
		animationSteps : 100,
		// String - Animation easing effect
		animationEasing : "easeOutBounce",
		// Boolean - Whether we animate the rotation of the
		// Doughnut
		animateRotate : true,
		// Boolean - Whether we animate scaling the Doughnut
		// from the centre
		animateScale : false,
		// Boolean - whether to make the chart responsive to
		// window resizing
		responsive : true,
		// Boolean - whether to maintain the starting aspect
		// ratio or not when responsive, if set to false, will
		// take up entire container
		maintainAspectRatio : true,
		// String - A legend template
		legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
	};
	// Create pie or douhnut chart
	// You can switch between pie and douhnut using the method
	// below.
	pieChart2.Doughnut(PieData2, pieOptions2);

});