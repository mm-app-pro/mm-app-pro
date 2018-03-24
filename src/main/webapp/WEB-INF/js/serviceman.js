$(function() {
    var index;
    //显示工单详细内容
    // $.manageTab("#serviceTab .tabBar span", "#serviceTab .tabCon", "current", "click", "0");

    //获取工单列表数据
    var data = {};
    var pageSize = 10;
    var pageNum = 1;
    var url = '/worker/isCheckedOrder';
    var pageDivId="page";
    var templateId="serviceMsg";
    
    //初始化列表页和分页
    var arrays = {'pageNum':pageNum,'pageSize':pageSize};
    pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);

    // 根据不同选项显示列表
    $('.tabBar span').click(function(){
        var tag = $(this).attr('tag');
        if(tag==0){
            url = '/worker/isCheckedOrder';
            pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);
        }else if(tag==1){
            url = '/worker/getValidOrder';
            pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);
        }else if(tag==2){
            url = '/worker/getFinishOrder';
            pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);
        }
    })


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
    $('body').delegate('.feedbackWait', 'click', function() {
        var id = $(this).parents('.list-id').attr('id');
        var res = sendRequest('/worker/getDetailById',{"id":id});
        $('#fbContack').text(res.mobile);
        $('#fbAddress').text(res.area+''+res.address);
        $('#fbDetail').text(res.detail);

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
               var remark = $('#setfeedbak').val();
               var res = sendRequest('/worker/getDetailById',{"id":id,"remark":remark});
               if(!remark && res.code==0){
                 layer.msg('反馈完毕！');
                 layer.close(index);
               }
            },
            btn2: function(index, layero) {
                //按钮【按钮二】的回调
                layer.msg('反馈失败，请稍后再试！');
                layer.close(index);
            }
        })
    })
})
