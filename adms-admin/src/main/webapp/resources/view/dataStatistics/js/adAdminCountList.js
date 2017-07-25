/**
 * Created by guop-i7-4770 on 2017/6/15.
 */
/**
 * Created by sth on 2017/6/5.
 */

var adAdminChart = echarts.init(document.getElementById('adAdminChart'));
/**
 * Created by sth on 2017/6/1.
 */


// 指定图表的配置项和数据
var adAdminOption = {
    title: {
        text: ''
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['曝光量','点击量','CTR(%)']
    },
    grid: {
        left: '5%',
        right: '5%',
        bottom: '20%',
        top:'10%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: []
    },
    yAxis: [{
        type: 'value',
        name:'曝光量/点击量'
    },{
        type:'value',
        name:'CTR(%)',
        max:100,
        axisLabel: {
            formatter: '{value} %'
        }
    }],
    series: [{
        name:'CTR(%)',
        type:'line',
        yAxisIndex: 1,
        smooth: true,
        data:[]
    },
        {
            name:'曝光量',
            type:'line',
            stack: '曝光量',
            smooth: true,
            data:[]
        },
        {
            name:'点击量',
            type:'line',
            stack: '点击量',
            smooth: true,
            data:[]
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
adAdminChart.setOption(adAdminOption);


$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "/adAdminDisplayCount/adAdminCountList",
    data : {orderId:"all",dayPeriod:7,companyId:0},
    dataType : "json",        //返回数据形式为json
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        console.log(result);
        if (result) {
            adAdminChart.setOption({        //加载数据图表
                xAxis: {
                    data: result.dateArr  //x轴坐标（数组格式）
                },
                series: [{
                    // 根据名字对应到y轴系列
                    name: '曝光量',
                    data: result.showCountArr //数组格式
                },{

                    name: '点击量',
                    data: result.clickCountArr
                },{
                    name: 'CTR(%)',
                    data: result.ctrArr
                }]
            });

        }

    }
});

var btns = $('.public-tab-box>.normal-btn');
btns.on('click',function () {
    $(this).siblings('button').removeClass('blue-btn').addClass('unselected-btn');
    $(this).removeClass('unselected-btn').addClass('blue-btn');
    var dates = $(this).attr('value');
    adAdminCharts(dates);
});

var adBtn = $('#adConfirm');
adBtn.on('click',function () {
    btns.removeClass('blue-btn').addClass('unselected-btn');
    btns.eq(2).removeClass('unselected-btn').addClass('blue-btn');
    adAdminCharts(7);
});

// 异步加载数据
function adAdminCharts(dayPeriod){
    var province =$("#province").find("option:selected").val();
    var companyId = $("#companyId").find("option:selected").val();
    if(companyId == "all" || companyId == -1){
        companyId = 0;
    }
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/adAdminDisplayCount/adAdminChartCountList",
        data : {orderId:province,dayPeriod:dayPeriod,companyId:companyId},
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            //获取总计曝光、点击量实时获取
            window.document .getElementById ("showCount").innerHTML = result.adDisplayTotalArr[0];
            window.document .getElementById ("clickCount").innerHTML = result.adDisplayTotalArr[1];
            if (result) {
                adAdminChart.setOption({        //加载数据图表
                    xAxis: {
                        data: result.dateArr  //x轴坐标（数组格式）
                    },
                    series: [{
                        // 根据名字对应到y轴系列
                        name: '曝光量',
                        data: result.showCountArr //数组格式
                    },{

                        name: '点击量',
                        data: result.clickCountArr
                    },{
                        name: 'CTR(%)',
                        data: result.ctrArr
                    }]
                });

            }

        }
    });
};

// 异步加载数据 根据公司id--获取对应订单名称列表
$("#companyId").change(function() {
    var companyId = $(this).val();
    var orderSelect = $('#province');
    var opts = '';
    if(companyId == "all" || companyId == -1){
        companyId = 0;
    }
    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/adAdminDisplayCount/adminOrderNameList",
        data: {companyId:companyId},
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            console.log(result);
            opts = '<option value="all">全部</option>';
            for (var i = 0; i < result.orderNameList.length; i++) {
                opts += '<option value=' + result.orderNameList[i].id + '>' + result.orderNameList[i].orderName + '</option>';
            }
            orderSelect.html(opts);
            $('#select2-chosen-2').text('全部');
        },
        error:function () {
            alert("error");
        }
    });
});



