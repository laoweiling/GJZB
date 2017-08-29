<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>投标模块管理</title>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<form id="manage_add"
		style="margin: 0; padding: 5px 0 0 25px; color: #333;">
		<p>
			管理帐号：<input type="text" name="manager" class="textbox"
				style="width: 200px;">
		</p>
		<p>
			管理密码：<input type="password" name="password" class="textbox"
				style="width: 200px;">
		</p>
	</form>



	<script type="text/javascript">
		$(function() {
			$('#manage_add').dialog({
				width : 350,
				title : '新增管理',
				iconCls : 'icon-user-add',
				modal : true,
				closed : true,
				buttons : [
					{
					text : '提交',
					iconCls : 'icon-add-new',
					handler : function () {
						if ($('#manage_add').form('validate')) {
							$.ajax({
								url : 'addManager.php',
								type : 'POST',
								data : {
									manager : $.trim($('input[name="manager"]').val()),
									auth : $('#auth').combotree('getText'),
									password : $.trim($('input[name="password"]').val()),
								},
								beforeSend : function () {
									$.messager.progress({
										text : '正在尝试提交...',
									});
								},
								success : function(data, response, status){
									$.messager.progress('close');
									if (data > 0) {
										$.messager.show({
										title : '提示',
										msg : '新增管理成功！',
									});
									$('#manage_add').dialog('close').form('reset');
									$('#manage').datagrid('reload');
									} else {
										$.messager.alert('警告操作', '未知操作，请重新提交！', 'warning');
									}

								}
							});//ajax
						}//form
					},//handler
					},{
					text : '取消',
					iconCls : 'icon-redo',
					handler : function () {
						$('#manage_add').dialog('close').form('reset');
						},
				}],
			});

			//管理帐号
			$('input[name="manager"]').validatebox({
				required : true,
				validType : 'length[2,20]',
				missingMessage : '请输入管理名称',
				invalidMessage : '管理名称在2-20 位',
			});

			//管理密码
			$('input[name="password"]').validatebox({
				required : true,
				validType : 'length[6,30]',
				missingMessage : '请输入管理密码',
				invalidMessage : '管理密码在6-30 位',
			});

			//分配权限
			$('#auth').combotree({
				url : 'nav.php',
				required : true,
				lines : true,
				multiple : true,
				checkbox : true,
				onlyLeafCheck : true,
				onLoadSuccess : function (node, data) {
					var _this = this;
					if (data) {
						$(data).each(function (index, value) {
							if (this.state == 'closed') {
								$(_this).tree('expandAll');
							}
						});
					}
				},
			});


		})
	</script>

</body>
</html>