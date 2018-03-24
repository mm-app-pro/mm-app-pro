$(function() {
    var index;
    //显示工单详细内容
    $.manageTab("#serviceTab .tabBar span", "#serviceTab .tabCon", "current", "click", "0");

    //获取工单列表数据
    var data = {};
    var pageSize = 10;
    var pageNum = 1;
    var url = '/worker/isCheckedOrder'
    var pageDivId="page";
    var templateId="serviceMsg";
    
    //初始化列表页和分页
    var arrays = {'pageNum':pageNum,'pageSize':pageSize};
    pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);

    // 领取工单
    $('#content').delegate('.getOrder','click',function(){
        var id = $(this).parents('.list-id').attr('id');
        var res = sendRequest('/worker/getOrder',{"id":id});
        console.log(res);
        if(res.code==0){
            layer.msg('领取成功！',{time:1000});
            setTimeout(function(){
                location.reload();
            })
        }else{
            layer.msg('领取失败！',{time:1000});
            setTimeout(function(){
                location.reload();
            })
        }
    })


    // 显示留言内容
    $('body').delegate('.content span', 'mouseover', function() {
        if ($(this).text().length <= 0) {
            return;
        } else {
            layer.tips($(this).text(), $(this), {
                tips: [1, '#5a98de']
            });
        }
    })


    //上门维修后反馈
    $('.content').delegate('.feedbackWait', 'click', function() {
        var obj = $(this).parents('.li-data');
        var address = obj.find('.li-address').text();
        var contact = obj.find('.li-name').text() + '-' + obj.find('.li-phone').text();
        var detail = obj.find('.li-detail').text();
        $('#fbContack').text(contact);
        $('#fbAddress').text(address);
        $('#fbDetail').text(detail);

        index = layer.open({
            type: 1,
            title: '维修反馈',
            shift: 2,
            scrollbar: false,
            shadeClose: false,
            content: $("#feedbackModel"),
            btn: ['确定','取消'],
            btnAlign: 'c',
            yes: function(index, layero) {
                //按钮【按钮一】的回调
            },
            btn2: function(index, layero) {
                //按钮【按钮二】的回调

                //return false 开启该代码可禁止点击该按钮关闭
            }
        })
    })
})
