<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="/js/common_jsAndcss.jsp"%>
</head>
<body>
欢迎使用国际招标系统
	<form style="margin:auto;" >
		<div style="margin-bottom:40px">
				<label for="newUserPw" class="label-top">设置新密码:</label>
				<input id="newUserPw" type="password" name="newUserPw" class="easyui-validatebox tb" data-options="prompt:'Enter password.',required:true">
		</div>
		<div style="margin-bottom:20px">
				<label for="check_userPw"  class="label-top">密码确认:</label>
				<input id="check_userPw" type="password" class="easyui-validatebox tb" data-options="prompt:'Enter your URL.',required:true">
		</div>
				<input type="hidden" id="email" name="email" value="${email}">
		</div>	
			
			<input type="submit" id="sure" style="padding:auto;" value="确定"/>
	</form>
</body>
</html>