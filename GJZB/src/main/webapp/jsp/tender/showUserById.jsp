<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>竞标用户</title>


</head>
<body>
	<script type="text/javascript">
		$(function() {
			$('.icon-back').click(function() {
				var currPage = $('#tabs').tabs('getSelected');
				currPage.panel('refresh', '/GJZB/jsp/tender/nottendered.jsp');
			});
		})
	</script>
	<!-- 返回之前nottendered.jsp页面的图标 -->

	<div style="width: 400px; height: 22px margin:auto;">
		<span class="icon-back"
			style="width: 20px; height: 20px; display: block"></span>
		<div style="width: 100px; height: 22px margin:auto;">
			<form action="/GJZB/Win/insertWin" method="post" id="tenderForm">
				<input type="hidden" id="userId" name="user.userId" value="${tenderUser.userId}"> 
				<input type="hidden" id="projectId" name="project.projectId" value="${tenderProject.projectId}"> 
				<input type="button" id="tenderSubmit" value="选定用户为中标">
			</form>
		</div>


	</div>



	<table class="easyui-datagrid" id="tableTender">
		<thead>
			<tr>
				<th data-options="field:'userId',width:50">用户ID</th>
				<th data-options="field:'name',width:150">用户名称</th>
				<th data-options="field:'phone',width:150">用户电话</th>
				<th data-options="field:'email',width:150">用户邮件</th>
				<th data-options="field:'introduction',width:200">用户简介</th>
				<th data-options="field:'profilePic',width:200">用户头像</th>
				<th data-options="field:'winCount',width:200">用户中标次数</th>
				<th data-options="field:'money',width:120">用户所赚钱数</th>
				<!-- <th data-options="field:'projectName',width:120">项目</th> -->


			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${tenderUser.userId}</td>
				<td>${tenderUser.userName}</td>
				<td>${tenderUser.phone}</td>
				<td>${tenderUser.email}</td>
				<td>${tenderUser.introduction}</td>
				<td>${tenderUser.profilePic}</td>
				<td>${tenderUser.winCount}</td>
				<td>${tenderUser.money}</td>
				<%-- <td>${tenderProject.projectName}</td> --%>


			</tr>

		</tbody>
	</table>

	<script type="text/javascript">
		$(function(){
			$('#tenderSubmit').click(function(){
				$.messager.confirm('确认对话框', '您确定要选中该用户为中标用户？', function(flag){
					if(flag){
						jquerySubByFId('tenderForm',reTennder,null,"json");
					}
				});
			
			});
		});
		
		function reTennder(data){
			$("#tableTender").datagrid("reload");
			var currTab = $('#tabs').tabs('getSelected'); //获得当前tab
			currTab.panel('refresh', '/GJZB/jsp/tender/nottendered.jsp');
		}
	</script>

</body>
</html>