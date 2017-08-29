<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>修改用户</title>
<script type="text/javascript">
	function sysusersave() {
		jquerySubByFId('userform', sysusersave_callback, null, "json");

	}
	//ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
	function sysusersave_callback(data) {
		//如果成功，自动关闭
		if (data) {
			//延迟1秒执行关闭方法
			parent.closemodalwindow();
			var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
			currTab.panel('refresh', '/GJZB/jsp/user/user.jsp');

		}
	}
</script>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/GJZB/index.jsp");
		}
		else if (user.getUserlevel().equals("0")) {
			response.sendRedirect("/GJZB/index.jsp");
		} 
	%>

	<form id="userform"
		action="${pageContext.request.contextPath}/users/editUser"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="userId" value="${editUser.userId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>
			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TR>
								<TD>&nbsp;修改用户信息</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TABLE>
					</TD>
				</TR>

				<TR>
					<TD>
						<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
							align=center>
							<TBODY>

								<TR>
									<TD height=30 width="15%" align=right>用户账号：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="userId" disabled="disabled"
												name="userId" value="${editUser.userId}" />
										</div> <!-- useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="useridTip"></div>
									</TD>
									<TD height=30 width="15%" align=right>用户名：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="userName" name="userName"
												value="${editUser.userName}" />
										</div>
										<div id="userNameTip"></div>
									</TD>
								</TR>
								<TR>
									
									<TD height=30 width="15%" align=right>权限：</TD>
									<TD class=category width="35%">
										<div>
											<select name="userlevel" id="userlevel">

												<option value="">请选择</option>
												<option value="1"
													<c:if test="${editUser.userlevel=='1'}">selected</c:if>>管理员</option>
												<option value="0"
													<c:if test="${editUser.userlevel=='0'}">selected</c:if>>普通用户</option>

											</select>
										</div>
										<div id="userlevel"></div>
									</TD>


								</TR>
								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="sysusersave()">提交</a> <a id="closebtn"
										class="easyui-linkbutton" iconCls="icon-cancel" href="#"
										onclick="parent.closemodalwindow()">关闭</a></td>
								</tr>

							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>
</body>
</html>