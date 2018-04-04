<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js" ></script>

<script type="text/javascript" src="js/students.js"></script>
<script type="text/javascript" src="js/stuScore.js"></script>
<script type="text/javascript">
var basePath='<%=request.getContextPath()%>';
var classsList='${requestScope.classsList}';
var courseList='${requestScope.courseList}';
var roleId=${sessionScope.my_user.getMyRole()};
</script>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/student.css" />
<title>学生管理系统</title>
</head>

<body class="easyui-layout">
	
	<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:75px;background:#666;">
		<div class="logo"> 学生管理系统</div>
		<div class="logout">您好，${sessionScope.my_user.pname } &nbsp;|&nbsp; <a href="${pageContext.request.contextPath}">退出</a></div>
	</div>
	
	<div data-options="region:'west',title:'导航',split:true" style="width:180px;">
		<div id="rightAccordion" class="easyui-accordion"></div>
		<ul id="navigation"></ul>  
	</div>
	
	<div data-options="region:'center'" style="overflow:hidden;">
		<div id="tabs">
			<div title="起始页" style="padding:0 10px;display:block;">
				欢迎来到后台管理系统！
			</div>
		</div>
	</div>
</body>
</html>	