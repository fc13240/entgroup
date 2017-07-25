/**
 * Created by sth on 2017/6/5.
 */

var baseOnVdChart = echarts.init(document.getElementById('exposureChart'));
var baseOnCkChart = echarts.init(document.getElementById('clickChart'));
var baseOnCTRChart = echarts.init(document.getElementById('CTRChart'));
var baseOnEdChart = echarts.init(document.getElementById('expenditureChart'));

// 指定图表的配置项和数据1
var baseOnVdOption = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: []
    },
    series: [
        {
            name: '视频平台',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            color: [],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: []
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
baseOnVdChart.setOption(baseOnVdOption);

// 指定图表的配置项和数据2
var baseOnCkOption = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: []
    },
    series: [
        {
            name: '视频平台',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            color: [],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: []
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
baseOnCkChart.setOption(baseOnCkOption);

// 指定图表的配置项和数据3
var baseOnCTROption = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: []
    },
    series: [
        {
            name: '视频平台',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            color: [],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: []
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
baseOnCTRChart.setOption(baseOnCTROption);

// 指定图表的配置项和数据4
var baseOnEdOption = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data: []
    },
    series: [
        {
            name: '视频平台',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            color: [],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: []
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
baseOnEdChart.setOption(baseOnEdOption);

var btns = $('.public-tab-box>.normal-btn');
btns.on('click',function () {
    $(this).siblings('button').removeClass('blue-btn').addClass('unselected-btn');
    $(this).removeClass('unselected-btn').addClass('blue-btn');
    var dates = $(this).attr('value');
    pieChart(dates);
});

var adBtn = $('#adConfirm');
adBtn.on('click',function () {
    btns.removeClass('blue-btn').addClass('unselected-btn');
    btns.eq(2).removeClass('unselected-btn').addClass('blue-btn');
    pieChart(7);
});


//刷新饼图中相关数据
function pieChart(data) {
    var province = $("#province").find("option:selected").val();
    var toLoad = $('#toUl');
    $.ajax({
        type: "post",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: url,
        data: {dayPeriod: data, orderId: province},
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                //拼接名称
                //console.log(result.videoPlatName);
                var tul = '';
                    for (var i = 0; i < result.videoPlatName.length; i++) {
                        tul += '<li><i></i>' + result.videoPlatName[i] + '</li>';
                    }
                toLoad.html(tul);
                // 图表1 曝光量
                        baseOnVdChart.setOption({        //加载数据图表
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: result.videoPlatName  //需要动态数据
                    },
                    series: [{
                        name: '视频平台',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        color: ['#00BCD4', '#FF635C', '#0088D3', '#EFC30A'],
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: result.showCountlist                           //需要动态数据（数组里面是对象）

                    }]
                });
                // 图表2 点击量
                baseOnCkChart.setOption({        //加载数据图表
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: result.videoPlatName  //需要动态数据
                    },
                    series: [{
                        name: '视频平台',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        color: ['#00BCD4', '#FF635C', '#0088D3', '#EFC30A'],
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: result.clickCountList                          //需要动态数据（数组里面是对象）

                    }]
                });
                // 图表3 CTR
                baseOnCTRChart.setOption({        //加载数据图表
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: result.videoPlatName //需要动态数据
                    },
                    series: [{
                        name: '视频平台',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        color: ['#00BCD4', '#FF635C', '#0088D3', '#EFC30A'],
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: result.ctrList                            //需要动态数据（数组里面是对象）


                    }]
                });

                // 图表4 消费额
                baseOnEdChart.setOption({        //加载数据图表
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'left',
                        data: result.videoPlatName  //需要动态数据
                    },
                    series: [{
                        name: '视频平台',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        color: ['#00BCD4', '#FF635C', '#0088D3', '#EFC30A'],
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: result.cosumeMoneyList                             //需要动态数据（数组里面是对象）
                    }]
                });
            }
        }
    });
}



