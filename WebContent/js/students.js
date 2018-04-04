/*工具栏按钮事件*/
var url;
var type;

//添加按钮
function addStu() {
	$("#dlg").dialog("open").dialog('setTitle', '增加');
	$("#fm").form("clear");
	url = basePath + "/stu/addStu";
}

//修改按钮
function editStu() {
	var row = $("#tt").datagrid("getSelected");
	if (row) {
		var id = row.id;
		$("#dlg").dialog("open").dialog('setTitle', '修改');
		$("#fm").form("load", row);
		url = basePath + "/stu/updateStu/" + id;
	} else {
		$.messager.alert("警告操作","请选择一条记录","warning")
	}
}

//保存操作
function saveStu() {
	$("#fm").form("submit", {
		url : url,
		onsubmit : function(param) {
			var id=$('#id').val();
			return $(this).form('enableValidation').form('validate');
		},
		success : function(result) {
			if (result == "1") {
				$.messager.alert("提示信息", "操作成功", "info");
				$("#dlg").dialog("close");
				$("#tt").datagrid("load");
				$('#stuScore').datagrid('load',{});
			} else {
				$.messager.alert("提示信息", "操作失败", "error");
			}
		}
	});
}

function saveStuInfo() {
	var id = $('#id').val();
	$("#fm").form("submit", {
		url : basePath + "/stu/updateStu/" + id,
		onsubmit : function(param) {
			var id=$('#id').val();
			return $(this).form('enableValidation').form('validate');
		},
		success : function(result) {
			if (result == "1") {
				$.messager.alert("提示信息", "操作成功", "info");
			} else {
				$.messager.alert("提示信息", "操作失败", "error");
			}
		}
	});
}

//删除按钮
function destroyStu() {
	var row = $('#tt').datagrid('getSelected');
	if (row!=null) {
		$.messager.confirm('警告', '确定要删除该记录信息吗?', function(flag) {
			if (flag) {
				var id = row.id;
				$.ajax({
					type : 'POST',
					url :basePath+'/stu/delete/'+id,
					data : {
						id : id,
						_method:'DELETE'
					},
					success : function (data) {
						if (data=="delete") {
							$('#tt').datagrid('reload');
							$.messager.alert('提示信息', '删除成功！','info');
							$('#stuScore').datagrid('load',{});
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

//查询按钮
function searchStu() {
	$('#tt').datagrid('load',{
		id:$.trim($('#student_id').val()),
		sName:$.trim($('#s_name').val()),
		classId:$('#classbox1').combobox('getValue'),
		provinceId:$('#provincebox1').combobox('getValue'),
		cityId:$('#citybox1').combobox('getValue')
	});
	//每次查询都清空成绩
	var selectedExpertRows=$('#stuScore').datagrid('getRows');
	if(selectedExpertRows.length>0){
		$('#stuScore').datagrid('load',{});
	};
}

$(function () {
	
	$('#navigation').tree({   
		url : basePath + '/getMenus',
		lines : true,
		onBeforeExpand:function(node){
			$(this).tree('options').url = basePath+'/getMenus?menuId=' + node.id;
		},
		onClick : function(node){
			if(node.url!=null && node.url!=''){
				var tabTitle = node.text;
				var url = basePath+node.url;
				var icon = node.iconCls;
				addTab(tabTitle, url, icon);
			}
		}
	});
	//初始化选项卡
	$('#tabs').tabs({
		fit:true,
		border:false,
	});
	//打开选项卡
	function addTab(title, url, icon){
		if ($('#tabs').tabs('exists', title)){
			$('#tabs').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
			$('#tabs').tabs('add',{
				title:title,
				content:content,
				iconCls:icon,
				closable:true
			});
		}
	}	
	
//    $("#dlg").dialog({
//        modal:true,
//        shadow:true,
//        draggable:true
//    });
	
//	$('#search').panel({
//		title : '学生信息管理',
//		iconCls : 'icon-search',
//		collapsible : true,
//		maximizable : true,
//		closable : true
//	});
	
//	$('#result').panel({
//		title : '学生列表信息',
//		iconCls : 'icon-tip',
//		tools : [{
//			iconCls : 'icon-add',
//			handler : function(){
//				addStu();
//			}
//		},{
//			iconCls : 'icon-edit',
//			handler : function(){
//				editStu();
//			}
//		},{
//			iconCls : 'icon-remove',
//			handler : function(){
//				destroyStu();
//			}
//		}]
//	});
	
//	var selectedExpertRows=$('#stuScore').datagrid('getRows');
//	if(selectedExpertRows.length>0){
//		$('#stuScore').datagrid('load',{});
//	};
	
	$('#tt').datagrid({
		url : basePath+'/stu/getStudentVOList',
		fit : true,
		pagination :true,
		pageSize : 15,
		pageList : [5,10,15,20],
		striped : true,
		fitColumns : true,
		singleSelect : true,
		toolbar : '#toolbar',
		columns : [[
			{
				width : 100,
				field : 'ck',
				checkbox : true
			},
			{
				width : 50,
				field : 'id',
				title : '学号',
				sortable : true
			},
			{
				width : 100,
				field : 's_name',
				title : '姓名'
			},
			{
				width : 100,
				field : 'classroom',
				title : '门牌号'
			},
			{
				width : 100,
				field : 'class_name',
				title : '班级',
				sortable : true
			},
			{
				width : 100,
				field : 't_name',
				title : '班主任'
			},
			{
				width : 100,
				field : 'birth',
				title : '出生日期'
			},
			{
				width : 100,
				field : 'provinceName',
				title : '省份'
			},
			{
				width : 100,
				field : 'cityName',
				title : '城市'
			},
		]],
		onRowContextMenu : function(e,rowIndex,rowData) {
			e.preventDefault();
			$('#menu').menu('show',{
				left : e.pageX,
				top : e.pageY,
			});
		},
		// 点击其中一条学生记录，关联学生成绩
		onClickRow : function(index, row){ 
			var id=row.id;
			if(id!=null){
				$('#stuScore').datagrid('load',{
					id:id
				});
			}
		}
	
	});
	
	$('#classbox').combobox({
		valueField : 'classId',
		textField : 'className',
		limitToList : true,
		url : basePath+'/stu/classsList',
//		onBeforeLoad: function (param) {
//			if(roleId==1) {
//            	$('#classbox').combobox('reload',basePath+'/stu/getClassId');
//			}
//            if(roleId==1){
//            	//教师
//            	$('#classbox').combobox('reload',basePath+'/stu/getClassId');
//              $('#classbox').combobox('setValue',classId);
//            }
//        },
//        onChange:function (value){
//        	if(roleId==1){
//            	$('#classbox').combobox('setValue',classId);
//            }
//        }
	});

	if(roleId==1){
		$('#classbox').combobox('reload',basePath+'/stu/getClassId');
	}
	
	$('#classbox1').combobox({
		valueField : 'classId',
		textField : 'className',
		limitToList : true,
		url : basePath+'/stu/classsList',
		onLoadSuccess: function (data) {
            if (data) {
                if(roleId==1||roleId==0){
                	//教师
                	$('#classbox1').combobox('readonly',true);
//                	$('#classbox1').combobox('setValue',classId);
                }
            }
        },
//        onChange:function (value){
//        	if(roleId==1){
//            	$('#classbox1').combobox('setValue',classId);
//            }
//        }
	});

	$('#provincebox').combobox({
		valueField : 'provinceId',
		textField : 'provinceName',
		limitToList : true,
		url : basePath+'/stu/provinceList',
		onChange:function(newValue,oldValue) {
			$('#citybox').combobox({
				url:basePath+"/stu/getCityListByProvinceId",
				queryParams: {
					"provinceId" :newValue
				},
				valueField : 'cityId',
				textField :  'cityName',
				limitToList:true,
			});
		}
	});
	$('#citybox').combobox({
		valueField : 'cityId',
		textField : 'cityName',
		limitToList : true,
	});
	
	$('#provincebox1').combobox({
		valueField : 'provinceId',
		textField : 'provinceName',
		limitToList : true,
		url : basePath+'/stu/provinceList',
		onChange:function(newValue,oldValue) {
			$('#citybox1').combobox({
				url:basePath+"/stu/getCityListByProvinceId",
				queryParams: {
					"provinceId" :newValue
				},
				valueField : 'cityId',
				textField :  'cityName',
				limitToList:true,
			});
		}
	});
	$('#citybox1').combobox({
		valueField : 'cityId',
		textField : 'cityName',
		limitToList : true,
	});
	
//	$('#navigation').tree({    
//		lines : true,
//		data: [{
//				text: '教师管理'
//			},{
//				text: '学生管理'
//			},{
//				text: '系统维护'
//			}]
////	    url:'tree_data.json',
////	    loadFilter: function(data){    
////	        if (data.d){    
////	            return data.d;    
////	        } else {    
////	            return data;    
////	        }    
////	    }    
//	});  
	
//	//学生用户不能操作
//	if(roleId==0) {
//		$('#tb_add').linkbutton('disable');
//		$('#tb_edit').linkbutton('disable');
//		$('#tb_delete').linkbutton('disable');
//	};
	
	if(roleId==0) {
		if(url==null){
			$("#fm").form('load', basePath+ '/stu/show');
		}
	}
	
});
