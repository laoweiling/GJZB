<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>

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
		<div id="cloud3" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
		<span class="systemlogo"></span>
			<form id="regitForm"
				action="${pageContext.request.contextPath}/users/regit"
				method="post" id="regForm">
				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">用户名和密码</h2>
					<h3 class="fs-subtitle">第一步</h3>
					<div></div>
					<input type="text" class="textbox regIn" name="userName"
						id="userName" placeholder="用户名" />
					<label class="labelError" id="userNameError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label>
					</div>
					<input type="password" class="textbox regIn" name="userPw"
						id="userPw" placeholder="密码" /> <input type="password"
						class="textbox regIn" name="password" id="reuserPw"
						placeholder="确认密码" /> <input type="button" name="next"
						class="next action-button" value="下一步" />
				</fieldset>

				<fieldset>
					<h2 class="fs-title">邮箱验证</h2>
					<h3 class="fs-subtitle">最后一步</h3>
					<input type="text" class="textbox retInput" name="email" id="email"
						placeholder="Email" />
					<label class="labelError" id="emailError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label>
					<div>
					<input type="text" class="retInput" 
					style="width:300px;height:48px;line-height:48px; padding-left:44px; font-size:14px; font-weight:bold;"
					 name="reg_code" id="reg_inp" data-options="required:true"  placeholder="请输入验证码"/>
					<input type="button" class="submit action-button" id="reg_code"  value="点击获取验证码">
					</div>
					<input  type="hidden" id="saveData"/>
					<div>
					<input type="text" class="textbox retInput" name="phone" id="phone" placeholder="请输入手机号" />
					<label class="labelError" id="phoneError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label>
					</div>
					<input type="button" name="previous"
						class="previous action-button" value="后退" /> <input
						type="submit" name="submit" class="submit action-button"
						onclick="return regitSubmit();" id="submit" value="注册" />
				</fieldset>
			</form>


</body>

</html>
