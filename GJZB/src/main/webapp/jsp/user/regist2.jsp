<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>注册用户</title>

</head>
<body>
		<form id="regitForm"
			action="${pageContext.request.contextPath}/users/regit" method="post">
			<table cellpadding="5">
				<tr>
					<td>用户名:</td>
					<td><input type="text" class="textbox tdInput" name="userName"
						id="userName" /></td>
					<td><label class="labelError" id="userNameError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label></td>

				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="password" class="textbox tdInput"
						name="userPw" id="userPw" /></td>
				</tr>
				<tr>
					<td>确认密码:</td>
					<td><input class="textbox" name="password tdInput"
						id="reuserPw" /></td>
				</tr>
				<tr>
					<td>邮箱:</td>
					<td><input type="text" class="textbox tdInput" name="email"
						id="email" /></td>
					<td><label class="labelError" id="emailError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label></td>

				</tr>
				<tr>
					<td>电话号码:</td>
					<td><input type="text" class="textbox tdInput" name="phone"
						id="phone" /></td>
					<td><label class="labelError" id="phoneError"
						style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						class="easyui-linkbutton" onclick="return regitSubmit();"
						id="submit" value="注册" /> <input type="button"
						class="easyui-linkbutton" name="reset"  onclick="parent.closemodalwindow()" value="取消" /></td>
				</tr>
			</table>

		</form>
</body>
</html>