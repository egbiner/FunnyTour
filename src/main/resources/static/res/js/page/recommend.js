// 绘制图表。
var hotCities = echarts.init(document.getElementById('hotCities'));

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
                name: '2018年',
                type: 'bar',
                data: res.data.data
            }
        ]
    });
});

