/**
 * Created by sth on 2017/6/5.
 */

var baseOnAdChart = echarts.init(document.getElementById('baseOnAdChart'));
/**
 * Created by sth on 2017/6/1.
 */


// 指定图表的配置项和数据
var baseOnAdOption = {
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
baseOnAdChart.setOption(baseOnAdOption);


$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "/adDisplayCount/adCountList",
    data : {orderId:"all",dayPeriod:7,identification:"adCountList"},
    dataType : "json",        //返回数据形式为json
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        console.log(result);
        //alert("初始化进入");
        //alert(result.showCountArr.length);
        if (result) {
            baseOnAdChart.setOption({        //加载数据图表
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
    /*error : function(errorMsg) {
     //请求失败时执行该函数
     alert("图表请求数据失败!");
     homeChart.hideLoading();
     }*/
});

var btns = $('.base-date-btn>.normal-btn');
btns.on('click',function () {
    $(this).siblings('button').removeClass('blue-btn').addClass('unselected-btn');
    $(this).removeClass('unselected-btn').addClass('blue-btn');
    var dates = $(this).attr('value');
    adChart(dates);
});

var adBtn = $('#adConfirm');
adBtn.on('click',function () {
    btns.removeClass('blue-btn').addClass('unselected-btn');
    btns.eq(2).removeClass('unselected-btn').addClass('blue-btn');
    adChart(7);
});

// 异步加载数据
function adChart(dayPeriod){
  //alert("这是baseOnAd");
    var province =$("#province").find("option:selected").val();
   // alert(province);
    $.ajax({
        type : "post",
        async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url : "/adDisplayCount/adChartCountList",
        data : {orderId:province,dayPeriod:dayPeriod},
        dataType : "json",        //返回数据形式为json
        success : function(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            //console.log(result);
           // alert(result.adDisplayTotalArr[0]);
            //获取总计曝光、点击量实时获取
            window.document .getElementById ("showCount").innerHTML = result.adDisplayTotalArr[0];
            window.document .getElementById ("clickCount").innerHTML = result.adDisplayTotalArr[1];
            if (result) {
                baseOnAdChart.setOption({        //加载数据图表
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


