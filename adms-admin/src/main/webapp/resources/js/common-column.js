// JavaScript Document
/**
 * Created by mxy on 2017-03-29 11:29.
 * Description 边栏通用js
 */
$(function() {
	/**
	 * 判断用户当前使用的浏览器
	 */
    var Sys = {};
    var ua = navigator.userAgent.toLowerCase();
    var s;

    (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
    (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
    (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
    (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
    (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;

    if (Sys.ie) console.log('IE: ' + Sys.ie);
    if (Sys.firefox) console.log('Firefox: ' + Sys.firefox);
    if (Sys.chrome) console.log('Chrome: ' + Sys.chrome);
    if (Sys.opera) console.log('Opera: ' + Sys.opera);
    if (Sys.safari) console.log('Safari: ' + Sys.safari);
    
	// 退出
    $('.exit').bind("click", function(){
        window.location.href = ADMS.ctx + "/logout";
    });
	
    $.ajaxSetup({
	    complete:function(XMLHttpRequest,textStatus){
	          if(textStatus=="parsererror"){
	               layer.alert("登陆超时！请重新登陆！",function(){
	                   window.top.location.reload();
	               });
	          } 
	    }
	});

    /* 左侧menu选择效果 */
    var columnNo = $("#columnNo").attr("column");
	$(".nav_link_active").removeClass("nav_link_active");
	$("#" + columnNo).addClass("nav_link_active");

    /* 导航栏显示标题 */
    var title = $("#titleSource").val();
    $("#title").text('');
    $("#title").text(title);

    $("#body").scrollTop($('#pageContent')[0].scrollHeight);//偏移
    $("#body").scrollTop(0);//偏移

    /* 左侧menu偏移效果 */
    if($("#" + columnNo).length > 0 ) {
        //偏移量 = 获取标签位置 - 获取屏幕高/2 + 标签宽度
        var excursion = $("#" + columnNo).offset().top-$(window).height()/2+$("#" + columnNo).outerHeight(true);
        if(excursion > 0) {
            $("#leftMenu").scrollTop(excursion);//偏移
        }
    }
});
