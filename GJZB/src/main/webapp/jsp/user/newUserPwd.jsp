<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<style type="text/css">
#sure:hover {
    text-shadow:0 0 2px rgb(255,255,255);
    box-shadow:inset 0 1px 0 rgb(86,174,251),0 0 10px 3px rgba(74,162,241,0.5);
}
#sure:active {
    background:-*-linear-gradient(top,rgb(52,119,182),rgb(74,162,241)) 1px 0 no-repeat,
               -*-linear-gradient(top,rgb(52,118,181),rgb(36,90,141)) left top no-repeat;
}
</style>

</head>

<body style="background-color: #1c77ac; overflow: hidden;">
<script language="javascript">
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
	<div class="logintop">
		<span>欢迎来到国际招标系统</span>
		<ul>
			<li><a href="/GJZB/index.jsp">回首页</a></li>
			<li><a href="/GJZB/login.jsp">登录</a></li>
		</ul>
	</div>
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="loginbody">
		<form style="margin: auto;">
			<input type="hidden" id="email" name="email" value="${email}"> 
			<span class="systemlogo"></span>
			<div class="loginbox"
				style="background-color: #FFFFF4; height: 400px; padding-left: 40px;">
				<img alt="找回密码" src="/GJZB/img/resetPwd.png">
				<p>
					<strong
						style="height: 46px; line-height: 46px; font-size: 18px; text-align: center;">设置新密码:</strong>
				</p>
				<input id="newUserPw" type="password" name="newUserPw"
					class="easyui-validatebox"
					style="height: 48px; line-height: 48px; padding-left: 44px; font-size: 14px; font-weight: bold;"
					data-options="prompt:'Enter password.',required:true">
				<p>
					<strong
						style="height: 46px; line-height: 46px; font-size: 18px; text-align: center;">密码确认:</strong>
				</p>
				<input id="check_userPw" type="password" class="easyui-validatebox"
					style="height: 48px; line-height: 48px; padding-left: 44px; font-size: 14px; font-weight: bold;"
					data-options="prompt:'Enter your URL.',required:true"> <br/>
					<input type="submit" id="sure" style="background-color:green;padding: auto;width:60px;height:40px;margin-left:160px;" value="确定" />
			</div>
		</form>

	</div>


</body>

</html>
