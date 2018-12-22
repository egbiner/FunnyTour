// 绘制图表。
var hotCities = echarts.init(document.getElementById('hotCities'));
var hotComments = echarts.init(document.getElementById('hotComments'));
var hotVotes = echarts.init(document.getElementById('hotVotes'));

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
                data: res.data.data
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
                data: res.data.data
            }
        ]
    });
});






