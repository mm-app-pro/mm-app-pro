$(function() {
    //初始化
    $.manageTab("#stuTab .tabBar span", "#stuTab .tabCon", "current", "click", "0");
    //获取工单列表数据
    var data = {};
    var pageSize = 10;
    var pageNum = 1;
    var url = '/apply/list'
    var pageDivId = "page";
    var templateId = "orderTp";
    //初始化列表页和分页
    var arrays = {
        'pageNum': pageNum,
        'pageSize': pageSize
    };
    pagements(url, pageNum, pageSize, pageDivId, arrays, templateId);
    // 工单提交验证
    $("#orderForm").validate({
        rules: {
            identityNum: {
                required: true,
                number: true,
                maxlength: 20
            },
            type: {
                required: true
            },
            time: {
                required: true
            },
            area: {
                required: true,
                maxlength: 10
            },
            address: {
                required: true,
                maxlength: 50
            },
            detail: {
                required: true,
                maxlength: 100
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function(form) {
            var time = $('#timeSelect').val().split('-');
            $('#reserveDateStart').val(time[0]);
            $('#reserveDateEnd').val(time[1]);
            var data = $('#orderForm').serialize();
            $.ajax({
                url: '/apply/submit',
                type: "post",
                data: data,
                error: function() {
                    layer.msg('系统繁忙，请稍后再试！');
                },
                success: function(res) {
                    res = JSON.parse(res);
                    if (res.code == 0) {
                        layer.msg('提交成功！');
                        location.reload();
                    } else {
                        layer.msg('提交失败！' + res.message);
                    }
                }
            })
        }
    });
})