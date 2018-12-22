$(function () {
    $("#reply-btn").click(function () {
        $.ajax({
            url: '/comment/reply',
            type: 'POST',
            dataType: 'json',
            data: $('#reply-form').serialize(),
            success: function (res) {
                alert(res.msg);
                location.reload();
            },
            error:function (res) {

            }
        });
    });

});