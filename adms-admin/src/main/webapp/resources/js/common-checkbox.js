// JavaScript Document
/**
 * Created by mxy on 2017-03-28 19:41.
 * Description 选择框通用js
 */
$(document).ready(function () {

    //CheckBox选择效果checkbox
    $(".piaochecked").on("click",function(){
        $(this).toggleClass( "on_check" );
        if($(this).children("input:first").attr("checked")){
            $(this).children("input:first").removeAttr("checked");
        }else {
            $(this).children("input:first").attr("checked",'true');
        }
    })

    /* 全选反选按钮 */
    $(".checkAll").on("click",function(){
        if($(this).hasClass('on_check')) {
            $('.piaochecked[for="' + this.id + '"]').each(function () {
                $(this).addClass('on_check');
                $(this).children("input:first").attr("checked",'true');
            });
        }else {
            $('.piaochecked[for="' + this.id + '"]').each(function () {
                $(this).removeClass('on_check');
                $(this).children("input:first").removeAttr("checked");
            });
        }
    });

    /* 子选择框联动全选 */
    var $subBox = $(".piaochecked[for]");
    $subBox.click(function () {
        var id = $(this).attr('for');
        if($subBox.length == $('.piaochecked.on_check[for="' + id + '"]').length) {
            $('#'+id).addClass('on_check');
            $('#'+id).children("input:first").attr("checked",'true');
        }else {
            $('#'+id).removeClass('on_check');
            $('#'+id).children("input:first").removeAttr("checked");
        }
    });

/*    $("input[type='checkbox']").change(function() {
    });

    /!* 鍒犻櫎鍒楄〃 *!/
    $("input[name='subBox']").click(function () {
        $(this).attr("checked", this.checked);
    });

    /!* 鍙戦�鐭俊鍐呭 *!/

    var $subradio = $("input[type='radio']");
    $subradio.each(function () {
        $(this).click(function () {
            $("#neirong").val($(this).next("label").text());
        })
    })*/


});

















