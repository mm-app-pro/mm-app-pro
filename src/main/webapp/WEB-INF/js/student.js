$(function(){
    //初始化
    $.manageTab("#stuTab .tabBar span","#stuTab .tabCon","current","click","0");

    // 工单提交验证
    $("#orderForm").validate({
		rules:{
            identityNum:{
                required:true,
                number:true,
                maxlength:20
            },
			type:{
				required:true
			},
            time:{
                required:true
            },
            address:{
                required:true,
                maxlength:50
            },
            detail:{
                required:true,
                maxlength:100
            }
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
        debug:true,
		submitHandler:function(form){
            var time = $('#timeSelect').val().split('-');
            $('#reserveDateStart').val(time[0]);
            $('#reserveDateEnd').val(time[1]);
            var data = $('#orderForm').serialize();
            console.log(data);
        }
	});
})
