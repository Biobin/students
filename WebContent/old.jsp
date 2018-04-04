<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js" ></script>

<script type="text/javascript" src="js/students.js"></script>
<script type="text/javascript">
var basePath='<%=request.getContextPath()%>';
var classsList='${requestScope.classsList}';
</script>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/student.css" />
<title>学生管理系统</title>
</head>

<body class="easyui-layout">
	
	<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:70px;background:#666;">
		<div class="logo"> 学生管理系统</div>
	</div>
	
	<div data-options="region:'center'" style="overflow:hidden;">
		<div id="search" >
			<div class="easyui-accordion" style="padding:1 1px;display:block;">
				<div title="使用说明" style="padding-left:10px">
					<p>欢迎来到学生管理系统！点击查询功能可根据学生相关信息进行查找，点击管理功能可对学生信息进行相应操作</p>
				</div>
				<div title="查询功能" style="margin-top:20px;padding-left:10px;padding-bottom:10px">
					<span>学号 :&nbsp;<input id="student_id" class="easyui-textbox" />&nbsp;&nbsp;</span> 
					<span>姓名 :&nbsp;<input id="s_name" class="easyui-textbox" />&nbsp;&nbsp;</span>
					<span>班级 :&nbsp; 
						<select name="stype" id="classbox1" style="width:100px"></select> &nbsp;&nbsp;&nbsp;&nbsp;
					</span>
					<span><a class="easyui-linkbutton" iconcls="icon-search" onclick="searchStu()">查询</a></span>
				</div>
				<div title="管理功能">
					<div id="tb" style="padding: 5px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-add" onclick="addStu()" plain="true">增加</a>
					 	<a href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-edit" onclick="editStu()" plain="true">修改</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-remove" plain="true" onclick="destroyStu()">删除</a>
					</div>
				</div>
			</div>
		</div>
		<div id="result" border="true"  split="true" >
			<div style="padding:1 1px;display:block;">
				<table id="tt"></table>
			</div>
		</div>
	</div>
		
	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px; left: 250px; top: 100px;"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">学生信息</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label> 学号： </label>
				<input name="id" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label> 姓名： </label>
				<input name="s_name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label> 出生日期： </label>
				<input name="birth" class="easyui-datebox">
			</div>
			<div class="fitem">
				<label> 班级： </label> <select id="classbox" name="class_id">
				</select>
				<!-- <input type="hidden" name="teacher_id"> -->
			</div>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveStu()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	
	<div id="menu" class="easyui-menu" style="width:120px;display:none;">
		<div onclick="addStu()" iconCls="icon-add">增加</div>
		<div onclick="destroyStu()" iconCls="icon-remove">删除</div>
		<div onclick="editStu()" iconCls="icon-edit">修改</div>
	</div>
	
</body>
</html>