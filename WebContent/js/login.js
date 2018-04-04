document.onkeydown = function (e) {
    var event = e || window.event;
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        login();
    }
}

$(function(){
	$('#username').focus();
	$('#username').textbox({
		required : true,
		missingMessage : '请输入用户名',
		invalidMessage : '用户名不得为空',
		iconCls:'icon-man'
	});
	$('#password').textbox({
		required : true,
		validType : 'length[3,30]',
		missingMessage : '请输入密码',
		invalidMessage : '密码长度必须至少3位，最多不超过30位',
		iconCls:'icon-lock'
	});
	
});

function login(){
	var username,password;
	if (!$('#username').textbox('isValid')) {
		$('#username').focus();
	} else if (!$('#password').textbox('isValid')) {
		$('#password').focus();
	} else {
		username=$('#username').val()
//		password=MD5($('#password').val());
		password=$('#password').val();
		$.ajax({
			url : basePath+'/loginValidate',
			type : 'post',
			data : {
				username : username,
				password : password,
			},
			success : function (data, response, status) {
				if (data == 1) {
					location.href = basePath+'/index';
				} else if(data == 2){
//					$.messager.alert('登录失败！', '请检查用户名或密码是否输入错误！', 'warning', function () {
//						$('#password').select();
//					});
					$.messager.alert('错误', '用户名不存在！', 'info', function () {
//						alert($('#username').val());
//						$('#username').focus(alert("aa"));
						$('#username').focus().val("");
					});
				}else if(data == 3){
					$.messager.alert('错误', '密码错误！', 'info', function () {
						$('#password').focus().val("");
					});
				}
			}
		});
	}
}