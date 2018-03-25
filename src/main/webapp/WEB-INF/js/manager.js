$(function(){
	var uIndex;
	//获取工单列表数据
    var data = {};
    var pageSize = 10;
    var pageNum = 1;
    var url = '/worker/isCheckedOrder';
    var pageDivId="page";
    var templateId="serviceMsg";
    
    //初始化列表页和分页
    var arrays = {'pageNum':pageNum,'pageSize':pageSize};
    // pagements(url,pageNum,pageSize,pageDivId,arrays,templateId);


	// 菜单栏切换
	$('.tabBar span').click(function(){
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		var section = $(this).attr('tag');
		if(section==0){
			$('#addUser').hide();
			console.log('进入工单管理');
		}else{
			$('#addUser').show();
			console.log('进入员工管理');
		}
	})

	// 工单审核处理
	$('#content').delegate('.verify', 'click', function() {
		layer.open({
			title:'工单审核',
			type: 1,
            shift: 2,
            shadeClose: false,
            content: $("#verifyModel"),
            btn: ['确定','取消'],
            btnAlign: 'c',
            area:['350px','310px'],
            yes: function(index, layero) {
              
            },
            btn2: function(index, layero) {
                layer.close(index);
            }
        })
	});

	// 员工信息提交验证
    $("#userForm").validate({
        rules: {
            name:{
                required: true,
                maxlength: 20
            },
            type:{
                required: true
            },
            mobile:{
                required: true,
                isMobile:true
            }
        },
        onkeyup: false,
        focusCleanup: true,
        success: "valid",
        submitHandler: function(form) {
            var data = $('#userForm').serialize();
            console.log('员工信息----',data)
            $.ajax({
                url: '',
                type: "post",
                data: data,
                error: function() {
                    layer.msg('系统繁忙，请稍后再试！');
                },
                success: function(res) {
                    res = JSON.parse(res);
                    if (res.code == 0) {
                        layer.msg('添加成功！');
                        location.reload();
                    } else {
                        layer.msg('添加失败！' + res.message);
                    }
                }
            })
        }
    });

    // 添加员工
	$('#addUser button').click(function() {
		uIndex = layer.open({
			title:'添加员工',
			type: 1,
            shadeClose: false,
            content: $("#userModel"),
            area:['500px','300px']
        })
	});

	// 编辑员工
	$('#content').delegate('.edit', 'click', function() {
		var id = $(this).parents('.list-id').attr('id');
		console.log(id);
		uIndex = layer.open({
			title:'编辑员工',
			type: 1,
            shadeClose: false,
            content: $("#userModel"),
            area:['500px','300px']
        })
	});
})