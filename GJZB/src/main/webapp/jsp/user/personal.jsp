<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
<%@include file="/js/common_jsAndcss.jsp"%>

</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<script type="text/javascript">
		var openFile = function(event) {
			jquerySubByFId("picForm", picSave, null, "text");
		};
		function picSave(data) {
			if (data != null) {
				// src:http:/localhost:8080/image/image/图片名
				//path:http:/localhost:8080/image/image/
				var img_src = $("#img").attr('src');
				var path = img_src.substr(0, img_src.lastIndexOf("/"));
				$("#img").attr('src', path + "/" + data);
				var head_img = $("#headImg").attr('src');
				$("#headImg").attr('src', path + "/" + data);
			}
		}
		function updateUser() {
			jquerySubByFId('updateForm', updateUser_callback, null, "json");

		}
		//ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
		function updateUser_callback(data) {
			//如果成功，自动关闭
			if (data) {
				alert("修改成功！");
			}
		}
	</script>
	<div id="container"
		style="height: 600px; margin-top: 50px; margin-left: 50px;">
		<div id="c_left" style="width: 200px; height: 600px; float: left;">
			<form
				action="${pageContext.request.contextPath }/users/addOneUserPicture"
				method="post" enctype="multipart/form-data" id="picForm">
				<input type="hidden" name="userId" id="userId"
					value="${user.userId }" /> <img
					src="<%=htph %>/image/image/${user.profilePic}" width="190px"
					id="img" /><br>
				<div style="margin-top: 10px;">
					<a href="javascript:void(0);"
						style="padding: 4px 10px; height: 50px; line-height: 50px; position: relative; border: 1px solid #999; text-decoration: none; color: while;">修改头像
						<input
						style="position: absolute; overflow: hidden; right: 0; top: 0; opacity: 0;"
						type="file" name="file" accept="image/*" onchange="openFile(this)" />
					</a>
				</div>
			</form>
		</div>
		<div id="c_right" style="width: 600px; height: 600px; float: left;">

			<form action="${pageContext.request.contextPath }/users/updateUser"
				method="post" id="updateForm">
				<input type="hidden" name="userId" id="userId"
					value="${user.userId }" />
				<TABLE border=1 cellSpacing=1 cellPadding=4 align=center>
					<TBODY>
						<TR>
							<TD height=30 width="15%" align=right>用户名：</TD>
							<TD width="35%"><input type="text" name="userName"
								value="${user.userName }" /></TD>
						</TR>
						<TR>
							<TD height=30 width="15%" align=right>自我介绍：</TD>
							<TD class=category width="35%"><textarea id="introduction"
									name="introduction" value="${user.introduction }" cols="50"
									rows="10" maxlength="250" placeholder="长度不能超过250字"></textarea>
							</TD>
						</TR>
						<tr>
							<td colspan=2 align=center class=category><a id="submitbtn"
								class="easyui-linkbutton" iconCls="icon-ok" href="#"
								onclick="updateUser()">提交</a><a id="closebtn"
								class="easyui-linkbutton" iconCls="icon-cancel" href="#"
								onclick="parent.closemodalwindow()">关闭</a></td>
						</tr>

					</TBODY>
				</TABLE>

			</form>
		</div>

	</div>
</body>
</html>