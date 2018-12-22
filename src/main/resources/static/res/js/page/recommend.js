// 绘制图表。
var hotCities = echarts.init(document.getElementById('hotCities'));
var hotComments = echarts.init(document.getElementById('hotComments'));
var hotVotes = echarts.init(document.getElementById('hotVotes'));
var hotViews = echarts.init(document.getElementById('hotViews'));

$.get('/hotCities').done(function(res){
    hotCities.setOption({
        title: {
            text: '热门城市排行',
            subtext: '数据'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['HotCity']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: res.data.category
            // data: ['巴西','印尼','美国','印度','中国','桂林']
        },
        series: [
            {
                name: '热度',
                type: 'bar',
                data: res.data.data
            }
        ]
    });
});

$.get('/hotComments').done(function(res){
    hotComments.setOption({
        title: {
            text: '热评文章排行',
            subtext: '数据'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['HotCity']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: res.data.category
        },
        series: [
            {
                name: '评论数',
                type: 'bar',
                data: res.data.data,
                itemStyle:{
                    normal:{
                        color:'#CD919E'
                    }
                }
            }
        ]
    });
});


$.get('/hotVotes').done(function(res){
    hotVotes.setOption({
        title: {
            text: '点赞排行',
            subtext: '数据'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['HotCity']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: res.data.category
        },
        series: [
            {
                name: '点赞数',
                type: 'bar',
                data: res.data.data,
                itemStyle:{
                    normal:{
                        color:'#4F94CD'
                    }
                }
            }
        ]
    });
});

$.get('/hotViews').done(function(res){
    hotViews.setOption({
        title: {
            text: '浏览排行',
            subtext: '浏览量'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['HotCity']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            data: res.data.category
        },
        series: [
            {
                name: '浏览数',
                type: 'bar',
                data: res.data.data,
                itemStyle:{
                    normal:{
                        color:'#589f80'
                    }
                }
            }
        ]
    });
});

// $.get('/hotViews').done(function(res) {
//     hotViews.setOption({
//         title : {
//             text: '某地区蒸发量和降水量',
//             subtext: '纯属虚构'
//         },
//         tooltip : {
//             trigger: 'axis'
//         },
//         legend: {
//             data:['蒸发量','降水量']
//         },
//         toolbox: {
//             show : true,
//             feature : {
//                 dataView : {show: true, readOnly: false},
//                 magicType : {show: true, type: ['line', 'bar']},
//                 restore : {show: true},
//                 saveAsImage : {show: true}
//             }
//         },
//         calculable : true,
//         xAxis : [
//             {
//                 type : 'category',
//                 // data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
//                 data: res.data.category
//             }
//         ],
//         yAxis : [
//             {
//                 type : 'value'
//             }
//         ],
//         series : [
//             {
//                 name:'蒸发量',
//                 type:'bar',
//                 // data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
//                 data:res.data.data,
//                 markPoint : {
//                     data : [
//                         {type : 'max', name: '最大值'},
//                         {type : 'min', name: '最小值'}
//                     ]
//                 },
//                 markLine : {
//                     data : [
//                         {type : 'average', name: '平均值'}
//                     ]
//                 }
//             }
//         ]
//     });
// });
//





