$(function () {
    //hover改变button内容
/*    $('.top-btn').hover(function () {
        $(this).children('span').removeClass('hide');
        $(this).children('img').addClass('hide');
    }, function () {
        $(this).children('img').removeClass('hide');
        $(this).children('span').addClass('hide');
    });*/
    //edited by mxy on 2017-03-29 17:35 begin



    //筛选图片
    $('.img-box').children('li').click(function () {
        if ($(this).attr("status") != 2) {
            $(this).children('input').click();
        }
    });
    //触发（选择）图片
    //用于input缓存元素
    var inps = $('.img-box').children('li').children('input');

    inps.click(function () {

        if ($(this).siblings('.choose-img').hasClass('hide')) {
            $(this).attr("checked", 'true');


        } else {
            $(this).removeAttr("checked");
        }
        $(this).siblings('.choose-img').toggleClass('hide');
        $(this).siblings('.black-bg').toggleClass('hide');

        // added by sth on 06/12/2017 B
        //投放统计
        var amounts = 0;

        $('.shopping-amount').text(function() {
            for(var i =0; i < inps.length; i++){
                if(inps[i].checked){
                    amounts ++;
                }
            }
            return amounts;
        });
        //modal层统计
        $('.text-or-alert').text('您一共选择了' + amounts + '个视频');
        // added by sth on 06/12/2017 E
    });
    //edited by mxy on 2017-03-29 19:14 end
    $('.img-box').children('li').hover(function () {
        $(this).css('box-shadow', '0 0 5px #000');
    }, function () {
        $(this).css('box-shadow', '0 0 0 #333');
    })
    $('.tag-img-box').children('li').hover(function () {
        $(this).css('box-shadow', '0 0 5px #000');
        $(this).children('.black-bg').removeClass('hide');
        $(this).children('.top-small-box').removeClass('height-hide');
    }, function () {
        $(this).css('box-shadow', '0 0 0 #333');
        $(this).children('.black-bg').addClass('hide');
        $(this).children('.top-small-box').addClass('height-hide');
    });
    //edited by mxy on 2017-03-28 19:12 begin
    //加载select样式
    if ( $(".js-example-basic-single").length > 0 ) {
        $(".js-example-basic-single").select2();
    }
    if ( $(".js-example-basic-hide-search").length > 0 ) {
        $(".js-example-basic-hide-search").select2({
            minimumResultsForSearch: Infinity
            //隐藏搜索框
        });
    }
    //edited by mxy on 2017-03-28 19:13 end
});
//回退
function goBack() {
    window.history.go(0)
}