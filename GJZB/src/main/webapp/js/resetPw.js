	//发送验证码倒计时
	var countdown=60; 
function settime(obj) { 
		if (countdown == 0) {
			obj.attr('disabled', false);
			// obj.removeattr("disabled");
			obj.val("免费获取验证码");
			countdown = 60;
			return;
		} else {
			obj.attr('disabled', true);
			obj.val("重新发送(" + countdown + ")");
			countdown--;
		}
		setTimeout(function() {
			settime(obj)
		}, 1000)
	}



$(function() {
	$('#newUserPw').validatebox({
		required : true,
		validType : 'length[5,30]',
		missingMessage : '请输入密码',
		invalidMessage : '密码在5-30 位',
	});
	
	
	/**
	 * 验证邮箱是否正确填写和是否存在
	 */
	$('#email').validatebox({
		required : true,
		validType : 'email',
	});
	$('#email').blur(function() {
		if (!$('#email').validatebox('isValid')) {
			$("#chkmsg").html("请填写正确的邮箱！");
			$('#email').focus(function() {
				$("#chkmsg").html("");
			});
		} else {
			// 将email保存到session里
			$.ajax({
				type : "post",
				data : {
					"email" : $('#email').val()
				},
				url : "/GJZB/users/getEmail",
				async : true,
				success : function(data) {
					if (data > 0) {
						$("#chkmsg").html("邮箱验证成功！请继续操作...");
					} else {
						$("#chkmsg").html("该邮箱尚未注册！");
					}
				},
				error : function(data) {

				},
			});
		}
		return false;
	});

	/**
	 * 点击获取验证码
	 */
	$('#getCheck_code').click(function() {
		if ($('#email').val() == '') {
			$("#chkmsg").html("该邮箱尚未填写！");
		} else {
			settime($('#getCheck_code'));
			$.ajax({
				type : "post",
				data : {
					"email" : $('#email').val()
				},
				url : "/GJZB/users/getIdentifideCodeByEmail",
				async : true,
				success : function(data) {
					$('#saveData').val(data);
				},
				error : function(data) {

				},
			});
		}
		return false;
	});

	$('#next_btn').click(function() {
		if(!$('#email').val()){
			$("#email").focus();
			return false;
		}
		if ($('#check_code').val() == $('#saveData').val()) {
			location.href = "newUserPwd.jsp";
		} else {
			$.messager.alert('验证失败', '验证码错误！', 'warning', function() {
				$('#check_code').select();
			});
		}
		return false;
	});
	
	//点击重置密码确定按钮，发送密码和email
		//判断两次密码是否一致
		$('#sure').click(function(){
			var pw = $("#newUserPw").val() == $("#check_userPw").val();
			var userPw = $("#newUserPw").val();
			if (!pw ||  userPw.trim().length ==0) {
				$("#newUserPw").focus();
				return false;
			}
			else{
				$.ajax({
					type : "post",
					data : {
						userPw:$("#newUserPw").val(),
						email :$("#email").val(),
					},
					url : "/GJZB/users/saveNewUserPw",
					async : true,
					success : function(data) {
						if(data>0){
							location.href = "/GJZB/login.jsp";
						}
						else{
							alert('保存密码出错了！');
						}
					},
					error : function(data) {
					},
				});
			}
			return false;
		});

});

