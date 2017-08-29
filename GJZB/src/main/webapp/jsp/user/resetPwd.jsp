<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>

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

</head>

<body style="background-color: #1c77ac; overflow: hidden;">
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
		<span class="systemlogo"></span>
		<div class="loginbox" style="background-color: #FFFFF4;height:400px;padding-left:40px;">
			<img alt="找回密码" src="/GJZB/img/resetPwd.png">
			<p><strong style="height:46px; line-height:46px; font-size:18px; text-align:center;">输入您注册的电子邮箱，找回密码：</strong></p>  
				<p>
					<input type="text" style="height:48px;line-height:48px; padding-left:44px; font-size:14px; font-weight:bold;"
					placeholder="输入邮箱" name="email" id="email" class="easyui-validatebox" 
					data-options="required:true,validType:'email'">
				  	<span id="chkmsg" style="color:red"></span>
				</p>  
				
				<p>
					<p><strong style="height:46px; line-height:46px; font-size:18px; text-align:center;">验证码：</strong></p> 
					<input type="text" style="height:48px;line-height:48px; padding-left:44px; font-size:14px; font-weight:bold;" name="check_code" id="check_code"  placeholder="请输入验证码"/>
					<input type="button" class="btn" id="getCheck_code" value="点击获取验证码">
					<input  type="hidden" id="saveData"/>
				</p> 
				
				<p><input type="button" class="btn" id="next_btn"  value="下一步"></p>
		</div>

		</div>

	</div>


</body>

</html>
