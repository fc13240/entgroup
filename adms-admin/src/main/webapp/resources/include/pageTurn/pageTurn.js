/**
 * Created by guop-i7-4770 on 2017/6/17.
 */

// edited by xiaokun on 2017-06-22 14:40 begin
if (undefined != $("#backgroundDiv")) {
    $("#backgroundDiv").css("height", $(document).height() - document.body.clientHeight - 17 + "px");
}
// edited by xiaokun on 2017-06-22 14:40 end

// var lis = $('.pagination').children('li');
// var elLoad = $('#toLoad');
// var pages,indexPage;
// var curPage = $('#curPage');
// var maxPage = $('#maxPage');
// function setPage(url,pageNum) {
//     $.ajax({
//         type: "post",
//         async: false,
//         url:url,
//         data: {pageNum:pageNum},
//         dataType: 'json',
//         success: function (result) {
            // curPage.attr('value',result.page.pageNum);
            // maxPage.attr('value',result.page.pages);
            // var tbodys = '';
            // console.log(datas);
            // if(datas == "homeDatas") {
            //     for (var i = 0; i < result.orderList.length; i++) {
            //         tbodys += '<tr>';
            //         tbodys += '<td>' + result.orderList[i].order_name + '</td>';
            //         tbodys += '<td>' + result.orderList[i].beginTime + ' - ' + result.orderList[i].endTime +'</td>';
            //         tbodys += '<td>' + result.orderList[i].sumShowCount + '</td>';
            //         tbodys += '<td>' + result.orderList[i].sumClickCount + '</td>';
            //         tbodys += '<td>' + result.orderList[i].ctr + '%' +'</td>';
            //         tbodys += '<td>' + result.orderList[i].cosumeMoney + '</td>';
            //         tbodys += '<td>' + result.orderList[i].totalMoney + '</td>';
            //         tbodys += '</tr>';
            //     }
            // }else if(datas == "adminDatas"){
            //     for (var i = 0; i < result.orderList.length; i++) {
            //         tbodys += '<tr>';
            //         tbodys += '<td>' + result.orderList[i].companyName + '</td>';
            //         tbodys += '<td>' + result.orderList[i].sumShowCount + '</td>';
            //         tbodys += '<td>' + result.orderList[i].sumClickCount + '</td>';
            //         tbodys += '<td>' + result.orderList[i].ctr + '%' +'</td>';
            //         tbodys += '<td>' + result.orderList[i].sumCosumeMoney + '</td>';
            //         tbodys += '<td>' + result.orderList[i].sumTotalMoney + '</td>';
            //         tbodys += '</tr>';
            //     }
            // }else if(datas == "videoDatas"){
            //     for (var i = 0; i < result.videoForPlatCount.length; i++) {
            //         tbodys += '<tr>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].companyName + '</td>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].slotCount + '</td>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].sumShowCount + '</td>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].sumClickCount + '</td>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].ctr + '%' +'</td>';
            //         tbodys += '<td>' + result.videoForPlatCount[i].sumCosumeMoney + '</td>';
            //         tbodys += '</tr>';
            //     }
            // }
            //     elLoad.html(tbodys);
            // // 首尾页颜色判断
            // if(curPage.attr('value') > 1){
            //     $(lis[2]).prevAll('li').children('a').removeClass('unable-page');
            // } else{
            //     $(lis[2]).prevAll('li').children('a').addClass('unable-page');
            // }
            // if(curPage.attr('value') < maxPage.attr('value')){
            //     $(lis[lis.length - 3]).nextAll('li').children('a').removeClass('unable-page');
            // } else{
            //     $(lis[lis.length - 3]).nextAll('li').children('a').addClass('unable-page');
            // }
//         }
//     })
// }

// var maxNum = maxPage.attr('value');

// lis.on('click',function () {
//
//     // 分页样式
//     $(this).children('a').addClass('active');
//     $(this).siblings('li').children('a').removeClass('active');
//
//     indexPage = curPage.attr('value');
//     // 选择页码
//     pages = $(this).children('a').text();
//        console.log(url);
//     if(!isNaN(pages)){
//         //上一页
//     } else if($(this).children('a').hasClass('previous-page')) {
// //            页码
//         pages = (curPage.attr('value') * 1) - 1;
// //            页码索引
//         indexPage = (curPage.attr('value') * 1);
//         if(indexPage < 2){
//             indexPage = 2;
//         }
//         $(lis[indexPage]).children('a').addClass('active');
//         //下一页
//     } else if($(this).children('a').hasClass('next-page')) {
// //            页码
//         pages = (curPage.attr('value') * 1) + 1;
//         // 第一次的时候value值为汉字
//         if(!isNaN(pages)){
//             pages = 2;
//             console.log('页码'+pages)
//         }
// //            页码索引
//         indexPage = pages + 1;
//         if(indexPage > (maxNum * 1) && !((maxNum * 1) == 0)){
//             indexPage = (maxPage.attr('value') * 1) + 1;
//         }
//         $(lis[indexPage]).children('a').addClass('active');
//         //首页
//     } else if($(this).children('a').hasClass('first-page')) {
//         pages = 1;
//         $(lis[2]).children('a').addClass('active');
//         //尾页
//     } else if($(this).children('a').hasClass('last-page')) {
//         pages = maxPage.attr('value');
//         indexPage = (maxPage.attr('value') * 1) + 1;
//         $(lis[indexPage]).children('a').addClass('active');
//
//     }
//     setPage(url,pages);
// });