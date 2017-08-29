<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>已中标模块管理</title>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<!-- 根据用户已中标的项目id和用户id(controller层获取)删除中标 -->
	<form id="deleteTenderform"
		action="${pageContext.request.contextPath}/Win/deleteWinById" method="post">
		<input type="hidden" id="delete_projectId" name="winId" />
	</form>

	<!--招标模块管理模块的DataGrid 数据表 -->
	<table id="istendered"></table>
	
 	<script type="text/javascript">
		$(function() {
			
			
			 $('#istendered').datagrid({
				url : '/GJZB/Win/getAllProjectInWinByUser',//获取已中标的所有项目信息
				fitColumns : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 15, 20 ],
				pageNumber : 1,
				sortName : 'date',
				sortOrder : 'desc',
				//toolbar : toolbar_v,
				  columns : [ [
						{
							field : 'project.projectId',
							title : '项目id',
							width : 100,
							checkbox : true,
							formatter : function(value, row,index) {
								return row.project.projectId;
							}
						},
						{
							field : 'project.projectName',
							title : '项目名称',
							width : 100,
							formatter : function(value, row,index) {
								return row.project.projectName;
							}
						},
						 {
							field : 'project.projectcontent',
							title : '详细内容',//窗口弹出具体信息
							width : 100,
							formatter : function(value, row,index) {
								return row.project.projectcontent;
							}
						},
						 {
							field : 'project.releaseTime',
							title : '发布时间',
							width : 100,
							formatter : function(value, row, index) {
								return new Date(row.project.releaseTime).Format("yyyy-MM-dd");
								
							}
						
						},
						{
							field : 'project.lastTime',
							title : '截止时间',
							width : 100,
							formatter : function(value, row, index) {
								 return new Date(row.project.lastTime).Format("yyyy-MM-dd");
								
							}
						},  
						{
							field : 'project.price',
							title : '价格',
							width : 100,
							formatter : function(value, row,index) {
								return row.project.price;
							}
							
						},  
						{
							field : 'user.userName',
							title : '投标用户',
							width : 100,
							formatter : function(value, row,index) {
								
								 return row.user.userName;
								
							}
							
						} 
						  
						] ]
						
			});  
			  
			  
		});
		
		
	</script>
	
</body>
</html>