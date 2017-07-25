$(function(){
	//CheckBox样式
	$(".piaochecked").on("click",function(){
	    $(this).toggleClass( "on_check" );
	})
    //hover改变button内容
    $('.top-btn').hover(function(){
    	$(this).children('span').removeClass('hide');
    	$(this).children('img').addClass('hide');
    },function(){
    	$(this).children('img').removeClass('hide');
    	$(this).children('span').addClass('hide');
    });
    //筛选图片
    $('.img-box').children('li').click(function(){
    	$(this).children('input').click();
    });
   $('.img-box').children('li').children('input').click(function(){
    	$(this).siblings('.choose-img').toggleClass('hide');
    	$(this).siblings('.black-bg').toggleClass('hide');
    });
    $('.img-box').children('li').hover(function(){
    	$(this).css('box-shadow','0 0 5px #000');
    },function(){
    	$(this).css('box-shadow','0 0 0 #333');
    })
    $('.tag-img-box').children('li').hover(function(){
    	$(this).css('box-shadow','0 0 5px #000');
    	$(this).children('.black-bg').removeClass('hide');
    	$(this).children('.top-small-box').removeClass('height-hide');
    },function(){
    	$(this).css('box-shadow','0 0 0 #333');
    	$(this).children('.black-bg').addClass('hide');
    	$(this).children('.top-small-box').addClass('height-hide');
    });
    //select样式
    $("#video").jSelect();
	$("#video-type").jSelect();
	$("#status").jSelect();
	$("#price").jSelect();
	// //ztree
	// var setting = {
 //   			view:{
 //   				showIcon: false,
 //   				showLine:false
 //   			},
	// 		check: {
	// 			enable: true,
	// 			chkboxType:{"Y":"ps","N":"ps"}
	// 		},
	// 		data: {
	// 			simpleData: {
	// 				enable: true
	// 			}
	// 		}
	// 	};

	// 	var zNodes =[
	// 		{ id:1, pId:0, name:"随意勾选 1", open:true},
	// 		{ id:11, pId:1, name:"随意勾选 1-1", open:true},
	// 		{ id:12, pId:1, name:"随意勾选 1-2", open:true},
	// 		{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
	// 		{ id:21, pId:2, name:"随意勾选 2-1"},
	// 		{ id:22, pId:2, name:"随意勾选 2-2", open:true},
	// 		{ id:23, pId:2, name:"随意勾选 2-3"}
	// 	];
	// 	$(document).ready(function(){
	// 		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	// 	});
})