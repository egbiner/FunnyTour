$(function () {
    layui.use('layer', function(){
        var layer = layui.layer;

        $("#essay-add-btn").click(function () {
            $.ajax({
                url: '/essay/add',
                type: 'POST',
                dataType: 'json',
                data: $('#essay-add-form').serialize(),
                success: function (res) {
                    layer.msg(res.msg);
                    // layer.confirm(res.msg, {
                    //     btn: ['知道了'] //按钮
                    // }, function(){
                    //     location.href="/index";
                    // });
                },
                error:function (res) {

                }
            });
        });
   });
});