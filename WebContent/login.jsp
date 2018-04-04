<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生管理系统</title>
	
		<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/login.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/demo/demo.css"/>
		<script type="text/javascript" src="<%=basePath%>/js/md5.js"></script>
		
		<style type="text/css">
			.submit a{border:none; font-weight:bold;color:#FFF;display:inline-block;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;-webkit-box-shadow: #CCC 0px 0px 5px;-moz-box-shadow: #CCC 0px 0px 5px;box-shadow: #CCC 0px 0px 5px;background: #31b6e7; cursor: pointer;}
	 		.submit a:hover{ background:#ff9229;}
 			.submit a{ padding:5px 30px;}
			.name{padding:10px 5px; font-size:14px;}
 			.pwd{padding:10px 5px; font-size:14px;}
		</style>
		
		<script type="text/javascript">
		    var basePath="<%=basePath%>";
		</script>
		
	</head>
	<body>
		<div class="easyui-window" title="登录" style="width:350px;height:188px;padding:5px;"
			 minimizable="false" maximizable="false" resizable="false" collapsible="false" closable="false" modal="true" draggable="false">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="text-align:center;margin:0px auto;padding:5px;background:#fff;">
					<form method="post" id="loginForm">
						<div class="name" style="padding:5px 0;">
							<label for="login">用户名</label>
							<input type="text" class="text" id="username" placeholder="用户名" name="login" tabindex="1">
						</div>
						<div class="pwd" style="padding:5px 0;">
							<label for="password">密　码</label>
							<input type="password" class="text" id="password" placeholder="密码" name="password" tabindex="2">
						</div>
					</form>
				</div>
				<div region="south" border="false" style="text-align:center;padding:15px 5px;" class="submit">
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="login()">登录</a>
				</div>
			</div>
		</div>
	</body>
</html>