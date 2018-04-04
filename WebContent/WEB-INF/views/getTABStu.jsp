<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath=request.getContextPath(); %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/easyui/locale/easyui-lang-zh_CN.js" ></script>

<script type="text/javascript" src="<%=basePath%>/js/students.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/stuScore.js"></script>
<script type="text/javascript">
var basePath="<%=basePath%>";
var classsList='${requestScope.classsList}';
var courseList='${requestScope.courseList}';
var roleId=${sessionScope.my_user.getMyRole()};
</script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/student.css" />
<title>学生管理系统</title>
</head>

<body class="easyui-layout">
	
	 <div data-options="region:'center'"> 
		<div id="toolbar" style="padding: 5px">
			<div style="padding:1 1px;display:block;">
				<div  style="margin-top:10px;padding-left:5px;padding-bottom:5px">
					<span>学号 :&nbsp;<input id="student_id" class="easyui-textbox" />&nbsp;&nbsp;</span> 
					<span>姓名 :&nbsp;<input id="s_name" class="easyui-textbox" />&nbsp;&nbsp;</span>
					<span>班级 :&nbsp; 
						<select name="stype" id="classbox1" style="width:100px"></select> &nbsp;&nbsp;&nbsp;&nbsp;
					</span>
					<span>省份 :&nbsp; 
						<select name="stype" id="provincebox1" style="width:100px"></select> &nbsp;&nbsp;&nbsp;&nbsp;
					</span>
					<span>城市 :&nbsp; 
						<select name="stype" id="citybox1" style="width:100px"></select> &nbsp;&nbsp;&nbsp;&nbsp;
					</span>
					<span><a class="easyui-linkbutton" iconcls="icon-search" onclick="searchStu()">查询</a></span>
				
					<div id="tb" style="padding: 5px">
						<a id="tb_add" href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-add" onclick="addStu()" plain="true">增加</a>
					 	<a id="tb_edit" href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-edit" onclick="editStu()" plain="true">修改</a>
						<a id="tb_delete" href="javascript:void(0)" class="easyui-linkbutton"
							iconcls="icon-remove" plain="true" onclick="destroyStu()">删除</a>
					</div>
				
				</div>
			</div>
		</div>
		<div style="padding:1 1px;height:522px;">
			<table id="tt"></table>
		</div>
	</div> 
	<div data-options="region:'south',title:'学生成绩',split:true" style="height:309px;">
		<div id="toolbar2stuScore" style="padding: 5px">
				<div id="tb2stuScore" style="padding: 5px">
					<a id="ss_add" href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-add" onclick="addStuScore()" plain="true">增加</a>
				 	<a id="ss_edit" href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-edit" onclick="editStuScore()" plain="true">修改</a>
					<a id="ss_delete" href="javascript:void(0)" class="easyui-linkbutton"
						iconcls="icon-remove" plain="true" onclick="destroyStuScore()">删除</a>
				</div>		
		</div>
		<div style="padding:1 1px;display:block;">
			<table id="stuScore"></table>
		</div>	
	</div>
		
	<div id="dlg2stuScore" style="width: 350px; height: 225px; padding: 10px 20px; left: 320px; top: 490px;"
		closed="true" buttons="#dlg-buttons2stuScore">
		<div class="ftitle">学生成绩</div>
		<form id="fm2stuScore" method="post">
			<div class="fitem">
				<label> 科目： </label> <select id="coursebox" name="courseId" >
				</select>
			</div>
			<div class="fitem">
				<label> 分数： </label>
				<input name="scores" class="easyui-textbox" required="true" />
			</div>
		</form>
	</div>
	<div id="dlg-buttons2stuScore" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveStuScore()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg2stuScore').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
		
	<div id="dlg" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#dlg-buttons'" style="left: 300px; top: 150px;">
		<div class="ftitle">学生信息</div>
		<form id="fm" method="post">
			<table style="width:430px">
				<tr>
					<td>学号： <input name="id" class="easyui-textbox" data-options="required:true" /></td>
					<td>姓名： <input name="s_name" class="easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>电话： <input name="sPhone" class="easyui-textbox" /></td>
					<td>邮箱： <input name="sMail" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td>出生日期：<input name="birth" class="easyui-datebox" style="width:127px"></td>
					<td>班级： <input type="text" id="classbox" name="class_id" class="easyui-combobox"></td>
				</tr>
				<tr>
					<td>父亲： <input name="father" class="easyui-textbox" /></td>
					<td>联系电话：<input name="fPhone" class="easyui-textbox" style="width:127px"/></td>
				</tr>
				<tr>
					<td>母亲： <input name="mother" class="easyui-textbox" /></td>
					<td>联系电话：<input name="mPhone" class="easyui-textbox" style="width:127px"/></td>
				</tr>
				<tr>
					<td>省份： <input type="text" id="provincebox" name="provinceId" class="easyui-combobox"></td>
					<td>城市： <input type="text" id="citybox" name="cityId" class="easyui-combobox"></td>
				</tr>
				<tr>
					<td colspan="2">通讯地址：<input type="text" name="address" class="easyui-textbox" style="width:333px"/></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons" style="text-align: center;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveStu()" iconcls="icon-save">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
	</div>
	
</body>
</html>