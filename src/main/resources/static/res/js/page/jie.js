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
                    if(res.status==10){
                        layer.msg(res.msg);
                    }else{
                        layer.confirm(res.msg,{
                            btn : ['知道了']
                         }, function(){
                             location.href="/";
                         });
                    }
                },
                error:function(res) {

                }
            });
        });
   });
});