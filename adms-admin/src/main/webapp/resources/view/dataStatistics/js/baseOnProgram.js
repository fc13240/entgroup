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
        data:['电视剧','电影','综艺','动漫','少儿']
    },
    series: [
        {
            name:'节目类型',
            type:'pie',
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
            color:['#00BCD4','#FF635C','#0088D3','#EFC30A','#6cca08'],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'电视剧'},
                {value:310, name:'电影'},
                {value:234, name:'综艺'},
                {value:575, name:'动漫'},
                {value:135, name:'少儿'}
            ]
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
        data:['电视剧','电影','综艺','动漫','少儿']
    },
    series: [
        {
            name:'节目类型',
            type:'pie',
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
            color:['#00BCD4','#FF635C','#0088D3','#EFC30A','#6cca08'],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'电视剧'},
                {value:310, name:'电影'},
                {value:234, name:'综艺'},
                {value:575, name:'动漫'},
                {value:135, name:'少儿'}
            ]
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
        data:['电视剧','电影','综艺','动漫','少儿']
    },
    series: [
        {
            name:'节目类型',
            type:'pie',
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
            color:['#00BCD4','#FF635C','#0088D3','#EFC30A','#6cca08'],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'电视剧'},
                {value:310, name:'电影'},
                {value:234, name:'综艺'},
                {value:575, name:'动漫'},
                {value:135, name:'少儿'}
            ]
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
        data:['电视剧','电影','综艺','动漫','少儿']
    },
    series: [
        {
            name:'节目类型',
            type:'pie',
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
            color:['#00BCD4','#FF635C','#0088D3','#EFC30A','#6cca08'],
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:335, name:'电视剧'},
                {value:310, name:'电影'},
                {value:234, name:'综艺'},
                {value:575, name:'动漫'},
                {value:135, name:'少儿'}
            ]
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
baseOnEdChart.setOption(baseOnEdOption);

