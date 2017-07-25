/**
 * Created by sth on 2017/6/1.
 */

var homeChart = echarts.init(document.getElementById('chartMain'));

// 指定图表的配置项和数据
var homeOption = {
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
        data:[0,0,0,0,0,0,0]
    },
        {
            name:'曝光量',
            type:'line',
            stack: '曝光量',
            smooth: true,
            data:[0,0,0,0,0,0,0]
        },
        {
            name:'点击量',
            type:'line',
            stack: '点击量',
            smooth: true,
            data:[0,0,0,0,0,0,0]
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
homeChart.setOption(homeOption);

// 异步加载数据
$("#province").change(function() {
    var provinceCode = $(this).val();
   // alert(provinceCode);
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/adDisplayCount/adChartCountList",
        data : {orderId:provinceCode,dayPeriod:7},
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            console.log(result);
            //alert(result.showCountArr.length);
            if (result) {
                homeChart.hideLoading();
                homeChart.setOption({        //加载数据图表
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

        },
        /*error : function(errorMsg) {
         //请求失败时执行该函数
         alert("图表请求数据失败!");
         homeChart.hideLoading();
         }*/
    });
});


$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "/adDisplayCount/adChartCountList",
    data : {orderId:"all",dayPeriod:7,identification:"home"},
    dataType : "json",        //返回数据形式为json
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        console.log(result);
        //alert("result:初次加载--》" + result.dateArr);
        //alert(result.showCountArr.length);
        if (result) {
            homeChart.hideLoading();
            homeChart.setOption({        //加载数据图表
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

    },
    /*error : function(errorMsg) {
        //请求失败时执行该函数
        alert("图表请求数据失败!");
        homeChart.hideLoading();
    }*/
});
