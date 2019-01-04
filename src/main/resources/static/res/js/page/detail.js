$(function () {
    layui.use('layer', function(){
        var layer = layui.layer;
        $("#reply-btn").click(function () {
            $.ajax({
                url: '/comment/reply',
                type: 'POST',
                dataType: 'json',
                data: $('#reply-form').serialize(),
                success: function (res) {
                    if(res.status==10){
                        layer.msg(res.msg);
                    }else{
                        layer.confirm(res.msg,{
                            btn : ['知道了']
                        }, function(){
                            location.reload();
                        });
                    }
                },
                error:function (res) {

                }
            });
        });
    });
});