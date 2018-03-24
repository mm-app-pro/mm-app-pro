// 选项卡功能
jQuery.manageTab = function(tabBar, tabCon, class_name, tabEvent, i) {
    var $tab_menu = $(tabBar);
    $tab_menu.removeClass(class_name);
    $(tabBar).eq(i).addClass(class_name);
    $(tabCon).hide();
    $(tabCon).eq(i).show();
    $tab_menu.bind(tabEvent, function() {
        $tab_menu.removeClass(class_name);
        $(this).addClass(class_name);
        var index = $tab_menu.index(this);
        $(tabCon).hide();
        $(tabCon).eq(index).show()
    })
}


// 请求
function sendRequest(url,data){
    var obj = {};
    $.ajax({
        url:url,
        async:false,
        type:'post',
        data:data,
        error:function(res){
            console.log('系统繁忙');
        },
        succcess:function(res){
        	   console.log("common:",res);
            obj = res
        }
    })
    return obj
}