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
            $.ajax({
            		url:"/login/user",
            		data:data,
            		type:"post",
            		cache:false,
            		error:function(){
            			console.log('登录失败，请稍后再试')
            		},
            		success:function(res){
            			if(res.code==0){
                            if(res.roleId==1){
                                location.href = 'user/html';
                            }else if(res.roleId==2){
                                location.href = 'worker/html';
                            }else{
                                location.href = 'apply/html';
                            }
                        }else{                            
                            layer.msg('登录失败！！');                        
                        }
            		}
            })
        }
	});
})