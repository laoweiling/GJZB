<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>

<script language="javascript">
	function _change() {
		$('#vCode').attr(
				'src',
				"${pageContext.request.contextPath}/users/getVerifyCode?a="
						+ new Date().getTime());
		return flag;
	}
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>

</head>

<body style="background-color: #1c77ac; overflow: hidden;">
	<div class="logintop">
		<span>欢迎来到国际招标系统</span>
		<ul>
			<li><a href="index.jsp">回首页</a></li>
			<li><a href="#">注册</a></li>
		</ul>
	</div>
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud3" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="loginbody">
		<span class="systemlogo"></span>
		<div class="loginbox loginbox3">
			<form action="" id="login_form">
				<ul>
					<li><input id="login_userName" name="userName"
						class="easyui-validatebox loginuser" data-options="required:true"
						type="text" value="${user.userName}" /></li>
					<li><input id="login_userPw" type="password" name="userPw"
						class="easyui-validatebox loginpwd" data-options="required:true"
						value="${user.userPw}" /></li>
					<li class="yzm"><span><input id="check_code"
							name="check_code" data-options="required:true" type="text"
							placeholder="验证码" onclick="JavaScript:this.value=''" /></span><cite><img
							id="vCode"
							src="${pageContext.request.contextPath }/users/getVerifyCode" /></cite>
						<a id="yzm_a" href="javascript: _change()">换一张</a> <label
						class="labelError" id="verifyCodeError" style="color: red;"></label>
					</li>

					<li><input name="" type="submit" id="login_submit"
						class="loginbtn" value="登录" /> <label
						id="forgetPw"><a href="jsp/user/resetPwd.jsp">忘记密码</a></label></li>
				</ul>
			</form>

		</div>

	</div>


</body>

</html>
