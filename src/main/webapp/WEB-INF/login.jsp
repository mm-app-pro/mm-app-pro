<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

    <!-- 公共样式 -->
    <link href="./lib/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="./lib/h-ui/css/H-ui.reset.css" rel="stylesheet" type="text/css" />
    <link href="./lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <!-- 业务样式 -->
    <link href="./css/login.css" rel="stylesheet">
    <link href="./css/media-phone.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div  class="loginBox">
		<form class="form form-horizontal" id="loginForm">
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-3"><i class="Hui-iconfont">&#xe60d;</i></label>
				<div class="formControls col-xs-8 col-sm-8">
					<input id="" name="num" type="text" placeholder="账户" class="input-text size-L radius">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
				<div class="formControls col-xs-8">
					<input id="" name="password" type="password" placeholder="密码" class="input-text size-L radius">
				</div>
			</div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe62b;</i></label>
                <div class="formControls col-xs-8 col-sm-8"> <span class="select-box">
                    <select class="select" size="1" name="roleId">
                        <option value="" selected>请选择角色</option>
                        <option value="0">管理员</option>
                        <option value="1">维修人员</option>
                        <option value="2">学生</option>
                    </select>
                </span> </div>
            </div>
			<div class="row cl">
				<div class="formControls col-xs-12 col-sm-8 col-sm-offset-3  mt-20">
					<input name="" id="commit" type="submit" class="btn btn-success radius size-L mr-50" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
					<input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
				</div>
			</div>
		</form>
	</div>
</body>
</html>

<!-- 业务脚本 -->
<script src="./lib/jquery.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script src="./js/login.js"></script>