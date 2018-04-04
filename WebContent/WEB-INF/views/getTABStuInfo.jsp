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

<body class="easyui-layout" style="width:900px;">
	
	 <div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:500px;height:350px">
			<div class="ftitle2stuInfo">学生信息</div>
			<form id="fm" method="post" >
				<table style="width:500px;height:350px">
					<tr>
						<td>学号： <input name="id" class="easyui-textbox" data-options="required:true"  readonly="readonly"/></td>
						<td>姓名： <input name="s_name" class="easyui-textbox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>电话： <input name="sPhone" class="easyui-textbox" /></td>
						<td>邮箱： <input name="sMail" class="easyui-textbox" /></td>
					</tr>
					<tr>
						<td>出生日期：<input name="birth" class="easyui-datebox" style="width:127px"></td>
						<td>班级： <input type="text" id="classbox" name="class_id" class="easyui-combobox" ></td>
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
						<td>省份： <input type="text" id="provincebox" name="provinceId" class="easyui-combobox" ></td>
						<td>城市： <input type="text" id="citybox" name="cityId" class="easyui-combobox" ></td>
					</tr>
					<tr>
						<td colspan="2">通讯地址：<input type="text" name="address" class="easyui-textbox" style="width:373px"/></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveStuInfo()" iconcls="icon-save">保存</a> 
			</div>
		</div> 
	 </div>
	 <div data-options="region:'east',title:'学生成绩',split:true" style="width:250px;">
	 	<div style="padding:1 1px;display:block;">
			<table id="stuScore"></table>
		</div>
	 </div>
	 
</body>
</html>	 