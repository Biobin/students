var url;
var type;

//添加学生成绩
function addStuScore() {
	var row = $("#tt").datagrid("getSelected");
	if (row) {
		var id = row.id;
		$("#dlg2stuScore").dialog("open").dialog('setTitle', '增加');
		$("#fm2stuScore").form("clear");
//		console.log(row);
//		console.log(id);
		url = basePath + "/stu/addStuScore/"+id;
	}else {
		$.messager.alert("警告操作","请选择一条记录","warning")
	}	
}

//修改学生成绩
function editStuScore() {
	var row = $("#stuScore").datagrid("getSelected");
	if (row) {
		var scoreId = row.scoreId;
		$("#dlg2stuScore").dialog("open").dialog('setTitle', '修改');
//		console.log(row);
//		console.log(row.scoreId);
		$("#fm2stuScore").form("load", row);
//		$("#fm2stuScore").form("load", basePath+"/stu/showScore/"+scoreId);
		url = basePath + "/stu/updateStuScore/" + scoreId;
	} else {
		$.messager.alert("警告操作","请选择一条记录","warning")
	}
}

//保存操作
function saveStuScore() {
	$("#fm2stuScore").form("submit", {
		url : url,
		onsubmit : function(param) {
//			var id=$('#id').val();
			return $(this).form('enableValidation').form('validate');
		},
		success : function(result) {
			if (result == "1") {
				$.messager.alert("提示信息", "操作成功", "info");
				$("#dlg2stuScore").dialog("close");
				$("#stuScore").datagrid("reload");
			} else {
				$.messager.alert("提示信息", "操作失败", "error");
			}
		}
	});
}

//删除按钮
function destroyStuScore() {
	var row = $('#stuScore').datagrid('getSelected');
	if (row!=null) {
		$.messager.confirm('警告', '确定要删除该记录信息吗?', function(flag) {
			if (flag) {
				var scoreId = row.scoreId;
//				console.log(row);
//				console.log(scoreId);
//				console.log(row.scoreId);
				$.ajax({
					type : 'POST',
					url :basePath+'/stu/deleteStuScore',
					data : {
						scoreId : scoreId,
						_method:'DELETE'
					},
					success : function (data) {
						if (data=="delete") {
							$('#stuScore').datagrid('reload');
							$.messager.alert('提示信息', '删除成功！','info');
						} else {
							$.messager.alert('提示信息', '删除失败！','error');
						}
					}
				});
			}
		});
	}else{
		$.messager.alert("警告操作","请选择一条记录","warning");
	}
}  

$(function () {
	
    $("#dlg2stuScore").dialog({
        modal:true,
        shadow:true,
        draggable:true
    });
    
	$('#stuScore').datagrid({
		url:basePath+'/stu/getStuScoreView',
		rownumbers : true,
		border : false,
		singleSelect: true,
		pageNumber : 1,
		sortName : 'courseName',
		sortOrder : 'desc',
		remoteSort : false,
		toolbar : '#toolbar2stuScore',
		columns : [[
			{
				field : 'scoreId',
				title : '成绩id',
				width : 100,
				hidden : true
			},
			{
				field :'courseName',
				title : '科目',
				width : 100,
				sortable:true
			},
			{
				field : 'scores',
				title : '分数',
				width : 100
			}
		]]
	});
    
    $('#coursebox').combobox({
		valueField : 'courseId',
		textField : 'courseName',
		limitToList : true,
		url : basePath+'/stu/courseList'
	});
    
    //学生用户不能操作
	if(roleId==0) {
		$('#ss_add').linkbutton('disable');
		$('#ss_edit').linkbutton('disable');
		$('#ss_delete').linkbutton('disable');
	};
    
});