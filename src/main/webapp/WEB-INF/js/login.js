$(function(){
    $("#loginForm").validate({
		rules:{
            num:{
                required:true,
                number:true,
                maxlength:20
            },
			password:{
				required:true,
                maxlength:20
			},
            roleId:{
                required:true
            }
		},
        debug:false,
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
            var data = $('#loginForm').serialize();
            // 登录请求
            var res = sendRequest('/login/user',data);
            if(res.code==0){
                layer.msg('登录成功！');
            }else{
                layer.msg('登录失败！');
            }
        }
	});
})
