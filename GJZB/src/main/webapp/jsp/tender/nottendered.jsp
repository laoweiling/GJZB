<%@page
	import="com.lnsf.entity.User,  com.lnsf.util.DateUtil,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>未中标模块管理</title>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<!--未中标模块管理模块的DataGrid 数据表 -->
	<table id="nottenderProjects">
		<a href='javascript:Modify()' class='easyui-linkbutton'
			iconCls='icon-edit' plain='true'>修改</a>
	</table>
	<div id="dlg-buttons">
		<a href="javascript:save()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<div id="dlg" class="easyui-dialog"
		style="width: 500px; height: 280px; padding: 10px 10px; top: 0px; left: 0;"
		closed="true" buttons="#dlg-buttons">
		<form method="post" id="fm">
			<table cellspacing="8px;">
				<tr>

					<td><input type="hidden" id="projectId" name="projectId"
						class="easyui-validatebox" readonly="readonly" />&nbsp;</td>
					<td>名称：</td>
					<td><input type="text" id="projectName" name="projectName"
						class="easyui-validatebox" />&nbsp;</td>
					<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
					<td>价格：</td>
					<td><input type="text" id="price" name="price"
						class="easyui-validatebox" />&nbsp;</td>
				</tr>
				<%--  <tr>    
		                        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td> 
		                        <br /><br />
		                        <td>截止时间：</td>
		                        <c:set var="ltime" value="${lastTime}" scope="request"></c:set>
		                         <td><input type="text" id="lastTime" name="lastTime" 
		                              class="easyui-validatebox"  value='<%=DateUtil.getStringFromDate((Date)request.getAttribute("ltime"))%>'/>&nbsp;
		                           <!--  class="easyui-validatebox"  value="new Date().Format('yyyy-MM-dd')"/>&nbsp; -->
		                        </td> 
		                       
		                    </tr>    --%>










			</table>
		</form>
	</div>

	<script type="text/javascript" defer="defer">
		$(function() {
			//获取未中标的所有项目信息getAllProjectNotInWinByUser
			$('#nottenderProjects')
					.datagrid(
							{
								url : '/GJZB/Win/getAllProjectNotInWinByUser',
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
								columns : [ [
										{
											field : 'project.projectId',
											title : '项目id',
											width : 100,
											checkbox : true,
											formatter : function(value, row,
													index) {
												
												return row.project.projectId;
											}

										},
										{
											field : 'project.projectName',
											title : '项目名称',
											width : 100,
											
											formatter : function(value, row,
													index) {
												return row.project.projectName;
											}

										},
										{
											field : 'project.projectcontent',
											title : '详细内容',//窗口弹出具体信息
											width : 100,
											formatter : function(value, row,
													index) {
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
											formatter : function(value, row,
													index) {
												return row.project.price;
											}

										},
										{
											field : 'userName',
											title : '投标者',
											width : 100,
											formatter : function(value, row,
													index) {
												var updateProjectIdInWin=row.project.projectId;
												var userIds = "";
												for (var i = 0; i < row.listUser.length; i++) {
													/* alert("updateProjectIdInWin "+updateProjectIdInWin); */
													userIds += "<a href=javascript:getUserInfo1('"
															+ row.listUser[i].userId+","+updateProjectIdInWin
															+ "')> "
															+ row.listUser[i].userName
															+ "  </a>"
															+ "     ";
												}
												return userIds;

											}

										},
										
										  

								] ]

							});

		});

		//根据页面name，后台userid在表中去查看用户信息
		function getUserInfo1(userId1) {
					var userId=userId1.split(",")[0]; 
				 	var projectId=userId1.split(",")[1];
					var currTab = $('#tabs').tabs('getSelected');
					currTab.panel('refresh', "/GJZB/users/getNotTenderUser?userId="
							+ userId+"&projectId="+projectId);
		}
	
		//修改指定项目信息，只是修改项目的某个字段
		function Modify() {
			var selectedRows = $("#nottenderProjects").datagrid("getSelections");
		        if (selectedRows.length != 1) {
		            $.messager.alert("系统提示", "请选择一条要编辑的数据！");
		            return;
		        }
		        var row = selectedRows[0];
		        alert(row.project.projectName);
		        $("#dlg").dialog("open").dialog("setTitle", "编辑项目信息");
		        $("#fm").form("load", row.project);
		        url = "/GJZB/Project/updateProjectBySome?project" + row.project;
		    }

		//修改数据后保存
		function save(){
	        $("#fm").form("submit", {
	            url : url,         
	            success : function(result) { 
	            	alert(url);
	            	alert(result); //返回project信息
	                if (result) {
	                    $.messager.alert("系统提示", "保存成功！");
	                    
	                    $("#dlg").dialog("close");
	                    $("#nottenderProjects").datagrid("reload");
	                } else {
	                    $.messager.alert("系统提示", "保存失败！");
	                    return;
	                }
	            }
	        });
	    }
	    
		
		//关闭对话框
		function closeDialog() {
	        $("#dlg").dialog("close");
	        //resetValue();
	    }
		//重加载
		function reload(){
			$("#nottenderProjects").datagrid("reload");
		}
		
	</script>

</body>
</html>