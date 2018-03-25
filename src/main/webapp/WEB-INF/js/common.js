   // 显示用户信息
   var obj = sendRequest('/apply/loginUser', null);
   obj = JSON.parse(obj);
   var userText = obj.num + ' &middot;' + obj.name;
   $('#userMsg').html(userText);
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
   function sendRequest(url, data) {
       var obj = {};
       $.ajax({
           url: url,
           type: 'post',
           data: data,
           async: false,
           error: function() {
               layer.msg('系统繁忙，请稍后再试！');
           },
           success: function(res) {
               obj = res;
           }
       })
       return obj
   }
   /**
    * 分页
    * Url 请求地址
    * pageNum  当前页数
    * pageSize  每页条数
    * pageDivId 容器ID
    * array 参数
    * templateId 模板ID
    */
   function pagements(url, curr, pageSize, pageDivId, array, templateId) {
       array.pageNum = curr || 1;
       $.getJSON(url, array, function(res) {
           //没有数据提示
           if (res != null && res.data != null && res.data.length == 0) {
               document.getElementById('content').innerHTML = '暂时没有数据哦！';
               return;
           }
           var pages = res.pages;
           // 数据渲染
           var html = template(templateId, res);
           document.getElementById('content').innerHTML = html;
           //显示分页
           laypage({
               cont: pageDivId, //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
               pages: pages, //通过后台拿到的总页数
               curr: curr || 1, //当前页
               skip: true,
               groups: 3,
               jump: function(obj, first) { //触发分页后的回调
                   if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                       pagements(url, obj.curr, pageSize, pageDivId, array, templateId);
                   }
               }
           })
       })
   }