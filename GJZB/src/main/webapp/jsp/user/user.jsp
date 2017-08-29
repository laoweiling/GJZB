<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>招标模块管理</title>
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
	<!-- 这是为了在jquery中能够获取到图片的路径： http://localhost:8080/image/image/ -->
	<img src="<%=htph%>/image/image/" style="display: none;" id="user_img" />
	<div id="user_tool" style="padding: 5px;">
		<div style="padding: 0 0 0 7px; color: #333;">
			查询用户：<input type="text" name="search_userr" id="search_user"
				style="width: 110px" onpropertychange="searchUser(this);"
				oninput="searchUser(this);"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-search"
				onclick="user_tool.search();">查询</a>
		</div>
	</div>

	<!-- html的静态布局 -->
	<form id="sysuserqueryForm">
		<table id="user"></table>
	</form>
	<!-- 删除用户 -->
	<form id="sysuserdeleteform"
		action="${pageContext.request.contextPath}/users/deleteUserById"
		method="post">
		<input type="hidden" id="delete_ids" name="userIds" />
	</form>


	<script type="text/javascript">
		var size = 5;
		var number = 1;
		$(function() {
			//定义 datagird工具
			var toolbar_v = [
					{//工具栏
						id : 'btnadd',
						text : '添加',
						iconCls : 'icon-add',
						handler : function() {
							//打开一个窗口，用户添加页面
							//参数：窗口的title、宽、高、url地址
							createmodalwindow("添加用户信息", 800, 250,
									'jsp/user/addUser.jsp');
						}
					}, {//工具栏
						id : 'btnadd',
						text : '批量删除',
						iconCls : 'icon-cancel',
						handler : function() {
							batchdelete();
						}
					} ];

			$('#user')
					.datagrid(
							{
								url : 'users/getAllUsers',
								striped : true,
								rownumbers : true,
								border : false,
								pagination : true,
								pageSize : 5,
								pageList : [ 5 ],
								pageNumber : 1,
								toolbar : toolbar_v,
								columns : [ [
										{
											field : 'userId',
											title : '编号',
											width : 100,
											checkbox : true,
										},
										{
											field : 'profilePic',
											title : '头像',
											width : 100,
											height : 100,
											formatter : function(value) {
												return "<img style='width:100px; height:100px' src='"
														+ $("#user_img").attr(
																'src')
														+ value
														+ "'>";
											}
										},
										{
											field : 'userName',
											title : '用户名',
											width : 100,
										},
										{
											field : 'userlevel',
											title : '权限',
											width : 100,
											formatter : function(value, row,
													index) {
												if (value == '1')
													return "管理员";
												else
													return "普通用户"
											},
											styler : function(value, row, index) {
												if (value == '1') {
													return 'background-color:#ffee00;color:red;';
												}
											}
										},
										{
											field : 'opt1',
											title : '删除',
											formatter : function(value, row,
													index) {
												return "<a href=javascript:deleteuser('"
														+ row.userId
														+ "')>删除</a>";
											}
										},
										{
											field : 'opt2',
											title : '修改',
											formatter : function(value, row,
													index) {
												return "<a href=javascript:edituser('"
														+ row.userId
														+ "')>修改</a>";
											}
										} ] ],
								
							});

			var $p = $('#user').datagrid("getPager");
			$p
					.pagination({
						showPageList : false,
						layout : [ 'first', 'prev', 'links', 'next', 'last',
								'manual' ],
					});

		});
		//查询方法
		function queryuser() {
			//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入controller
			//将form表单数据提取出来，组成一个json
			var formdata = $("#sysuserqueryForm").serializeJson();
			$('#sysuserlist').datagrid('load', formdata);
		}

		//删除用户方法
		function deleteuser(userIds) {
			//第一个参数是提示信息，第二个参数，取消执行的函数指针，第三个参是，确定执行的函数指针
			$.messager.confirm('确认对话框', '您确定要删除该用户吗？', function(flag) {
				if (flag) {
					// 操作;
					//将要删除的id赋值给deleteid，deleteid在sysuserdeleteform中
					$("#delete_ids").val(userIds);
					//使用ajax的from提交执行删除
					//sysuserdeleteform：form的id，userdel_callback：删除回调函数，
					//第三个参数是url的参数
					//第四个参数是datatype，表示服务器返回的类型
					jquerySubByFId('sysuserdeleteform', userdel_callback, null,
							"json");
				}
			});
		}

		//删除用户的回调
		function userdel_callback(data) {
			//刷新 页面
			//在提交成功后重新加载 datagrid
			//取出提交结果
			if (data) {
				//成功结果
				//重新加载 datagrid
				$('#user').datagrid('loaded');
				$('#user').datagrid('reload');
				$('#user').datagrid('unselectAll');

			}
		}

		//模糊查询
		function searchUser(even) {
			var path = "/GJZB/users/searchUser?userName="
					+ $("#search_user").val();
			$('#user').datagrid({
				url : path
			});
			var $p = $('#user').datagrid("getPager");
			$p
					.pagination({
						showPageList : false,
						layout : [ 'first', 'prev', 'links', 'next', 'last',
								'manual' ],
						onSelectPage : function(pageNumber, pageSize) {
							alert(pageNumber + "  " + pageSize);

						}
					});
		}
		//修改用户
		function edituser(userId) {
			//打开修改窗口
			createmodalwindow("修改用户信息", 800, 250,
					'${pageContext.request.contextPath}/users/toEditUser?userId='
							+ userId);
		}

		//批量删除
		function batchdelete() {
			var ss = "";
			var rows = $('#user').datagrid('getSelections');
			if (rows.length == 0) {
				alert('没有选择用户！');
				return;
			}
			ss = rows[0].userId;
			for (var i = 1; i < rows.length; i++) {
				ss += "," + rows[i].userId;
			}
			//调用删除方法
			deleteuser(ss);
		}
	</script>

</body>
</html>