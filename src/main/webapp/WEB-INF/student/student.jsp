<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>

    <!-- 公共样式 -->
    <link href="/lib/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="/lib/h-ui/css/H-ui.reset.css" rel="stylesheet" type="text/css" />
    <link href="/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <!-- 业务样式 -->
    <link href="/css/pc.css" rel="stylesheet">
    <link href="/css/media-phone.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <header class="navbar-wrapper">
        <div class="navbar navbar-black">
            <div class="container cl">
                <a class="logo navbar-logo f-l mr-10" href="javascript:;">欢迎您</a>
                <span class="logo navbar-slogan f-r mr-10">201406114353 &middot; 李同学</span>
            </div>
        </div>
    </header>
    <div class="container stuCon mt-50">
        <div id="stuTab" class="manageTab">
            <div class="tabBar clearfix">
                <span>历史工单</span>
                <span>申报工单</span>
            </div>
            <!-- 历史工单 -->
            <div class="tabCon">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-border table-border table-hover">
                            <thead class="text-c hidden-xs">
                                <tr>
                                    <th>时间</th>
                                    <th>工单详情</th>
                                    <th>工单分类</th>
                                    <th>工单状态</th>
                                </tr>
                            </thead>
                            <tbody class="text-c">
                                <tr>
                                    <td>2017-12-06 13:14</td>
                                    <td>宿舍冷水第二个水龙头漏水</td>
                                    <td class="td-klass">水工</td>
                                    <td class="c-warning td-state">处理中</td>
                                </tr>
                                <tr>
                                    <td>2017-12-06 13:14</td>
                                    <td>宿舍冷水第二个水龙头漏水</td>
                                    <td class="td-klass">水工</td>
                                    <td class="c-warning td-state">处理完成</td>
                                </tr>
                                <tr>
                                    <td>2017-12-06 13:14</td>
                                    <td>宿舍冷水第二个水龙头漏水</td>
                                    <td class="td-klass">水工</td>
                                    <td class="c-warning td-state">处理完成</td>
                                </tr>
                                <tr>
                                    <td>2017-12-06 13:14</td>
                                    <td>宿舍冷水第二个水龙头漏水</td>
                                    <td class="td-klass">水工</td>
                                    <td class="c-warning td-state">处理完成</td>
                                </tr>
                                <tr>
                                    <td>2017-12-06 13:14</td>
                                    <td>宿舍冷水第二个水龙头漏水</td>
                                    <td class="td-klass">水工</td>
                                    <td class="c-warning td-state">处理完成</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <!-- 申报工单 -->
            <div class="tabCon">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <form class="form form-horizontal" id="orderForm">
                            <div class="row cl">
                                <label class="form-label col-xs-3 col-sm-2 text-r">学号ID：</label>
                                <div class="formControls col-xs-9 col-sm-8">
                                    <input id="" name="identityNum" type="text" placeholder="请输入.." class="input-text size-L radius">
                                </div>
                            </div>
                            <div class="row cl">
                                <label class="form-label col-xs-3 col-sm-2 text-r">工单类别：</label>
                                <div class="formControls col-xs-9 col-sm-8">
                                    <span class="select-box size-L radius">
                                        <select class="select" size="1" name="type">
                                            <option value="" selected>请选择..</option>
                                            <option value="water">水工</option>
                                            <option value="electricity">电工</option>
                                            <option value="apartment">公寓管理</option>
                                        </select>
                                    </span>
                                </div>
                            </div>
                            <div class="row cl">
                                <label class="form-label col-xs-3 col-sm-2 text-r">时间段：</label>
                                <div class="formControls col-xs-9 col-sm-8">
                                    <span class="select-box  size-L radius">
                                        <select class="select" size="1" name="time" id="timeSelect">
                                            <option value="" selected>请选择..</option>
                                            <option value="08：30-10：00">08：30-10：00</option>
                                            <option value="10：00-12：00">10：00-12：00</option>
                                            <option value="12：00-14：00">12：00-14：00</option>
                                            <option value="14：30-17：00">14：30-17：00</option>
                                        </select>
                                    </span>
                                </div>
                                <input type="hidden" name="reserveDateStart" id="reserveDateStart" value="">
                                <input type="hidden" name="reserveDateEnd" id="reserveDateEnd" value="">
                            </div>
                            <div class="row cl">
                                <label class="form-label col-xs-3 col-sm-2 text-r">地点：</label>
                                <div class="formControls col-xs-9 col-sm-8">
                                    <input id="" name="address" type="text" placeholder="请输入.." class="input-text size-L radius">
                                </div>
                            </div>
                            <div class="row cl">
                                <label class="form-label col-xs-3 col-sm-2 text-r">具体事项：</label>
                                <div class="formControls col-xs-9 col-sm-8">
                                    <textarea name="detail" class="textarea" placeholder="请输入.."></textarea>
                                </div>
                            </div>
                            <div class="row cl">
                				<div class="formControls mt-20 text-c">
                					<input name="" id="commit" type="submit" class="btn btn-primary radius size-L mr-50" value="&nbsp;申&nbsp;&nbsp;&nbsp;&nbsp;报&nbsp;">
                				</div>
                			</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>

<!-- 业务脚本 -->
<script src="/lib/jquery.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="/js/common.js"></script>
<script src="/js/student.js"></script>