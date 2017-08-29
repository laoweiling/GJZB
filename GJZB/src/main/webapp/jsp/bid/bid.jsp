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
	<div id="bid_tool" style="padding: 5px;">
		<div style="padding: 0 0 0 7px; color: #333;">
			<a id="go"  class="easyui-linkbutton">可投标项目信息</a>
		</div>
	</div> 

	<!-- 根据用户已投标的项目id和用户id(controller层获取)删除投标 -->
	<form id="deleteBidform"
		action="${pageContext.request.contextPath}/relation/deleteBidById"
		method="post">
		<input type="hidden" id="delete_projectId" name="projectId" />
	</form>

	<!--文件的上传下载和在线查看-->
	<form action="${pageContext.request.contextPath}/ReadWord/readWord" id="BidOnlineForm" method="post" >
		<input type="hidden" id="BidOnline_method" name="filePath">
	</form>

	<!--招标模块管理模块的DataGrid 数据表 -->
	<table id="bidProjects"></table>

	<script type="text/javascript">
		$(function() {
			//跳转到info页面
			$('#go').click(function(){
				var current = $('#tabs').tabs('getSelected');
				current.panel('refresh','/GJZB/jsp/bid/info.jsp');
			});
			
			
			
			$('#bidProjects').datagrid({
				url : '/GJZB/relation/getAllFkProjects',//获取已投标的所有项目信息
				fitColumns : true,
				striped : true,
				rownumbers : true,
				border : false,
				pagination : true,
				pageSize : 2,
				pageList : [ 2],
				pageNumber : 1,
				sortName : 'date',
				sortOrder : 'desc',
				columns : [ [
						{
							field : 'projectId',
							title : '项目id',
							width : 100,
							checkbox : true,
							formatter : function(value, row, index) {
								 return row.project.projectId;
							}
						},	
						  {
							field : 'projectName',
							title : '项目名称',
							width : 100,
							formatter : function(value, row, index) {
								 return row.project.projectName;
							}
						}, 
						{ //下载文件
							field : 'projectcontent',
							title : '项目内容',
							width : 100,
							formatter : function(value, row, index) {
								return "<a href='/GJZB/ReadWord/Resourcesdownload.do?path="+
										row.project.projectcontent+"'>"+row.project.projectName+"</a>";
							}
						},  
						{//在线阅读
							field : 'online_content',
							title : '在线阅读项目',
							width : 100,
							formatter : function(value, row, index) {
								return "<a href=javascript:BpostProjectName('"+ row.project.projectcontent + "')>"
										+row.project.projectName+"</a>";
							}
						},
						 {
							field : 'releaseTime',
							title : '发布时间',
							width : 100,
							formatter : function(value, row, index) {
								 return new Date(row.project.releaseTime).Format("yyyy-MM-dd");    
							}
						}, 
						 {
							field : 'lastTime',
							title : '截止时间',
							width : 100,
							formatter : function(value, row, index) {
								return new Date(row.project.lastTime).Format("yyyy-MM-dd"); 
							}
						}, 
						{
							field : 'price',
							title : '价格',
							width : 100,
							formatter : function(value, row, index) {
								 return row.project.price;
							}
						},  
						{
							field : 'winFlag',
							title : '中标情况',
							width : 100,
							formatter : function(value, row, index) {
								 if(value == 1) return "是";
								 else return "未中标";
							}
						}, 
						  {
							field : 'del',
							title : '删除',
							formatter : function(value, row, index) {
								 /* alert("row.win " + row.win);  */
								return "<a href=javascript:deleteBid('"
								+ row.project.projectId + "')>取消投标</a>";
							}
						}  ] ],
						
			});
			
		});
		
		//删除投标信息
		function deleteBid(projectId){
			$.messager.confirm('确认对话框', '您确定要删除该投标信息吗？', function(flag){
				if(flag){
					$('#delete_projectId').val(projectId);
					//sysuserdeleteform：form的id，userdel_callback：删除回调函数，
					//第三个参数是url的参数
					//第四个参数是datatype，表示服务器返回的类型
					jquerySubByFId('deleteBidform',reBid,null,"json");
				}
				
			});
		}
		function reBid(data){
			var row = $('#bidProjects').datagrid('getSelected');
			if (row) {
			         var rowIndex = $('#bidProjects').datagrid('getRowIndex', row);
			         $('#bidProjects').datagrid('deleteRow', rowIndex);  
			         $('#bidProjects').datagrid('reload');//删除后重新加载下
			 } 
		}
		
		function BpostProjectName(projectName){
			$('#BidOnline_method').val(projectName);
			jquerySubByFId('BidOnlineForm',BidSuccessFun,null,"json");
		}
		
		function BidSuccessFun(data){
			alert(data.text);
		}
	</script>

</body>
</html>