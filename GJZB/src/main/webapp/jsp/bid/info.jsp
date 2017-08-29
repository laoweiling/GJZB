<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>投标模块管理---信息</title>
<style type="text/css">
	.icon-back .{
	background: url('icons/back.png') no-repeat;
</style>
</head>
<body>
	<%
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("index.jsp");
		}
	%>
	<!-- 根据用户的Id和要投标项目的id ，添加到投标表gjzb_relation中-->
	<form id="addBidForm"
		action="${pageContext.request.contextPath}/relation/addBid"
		method="post">
		<input type="hidden" id="add_projectId" name="project.projectId" /> <input
			type="hidden" id="userId" name="user.userId" value="${user.userId}" />
	</form>

	<div style="width: 22px; height: 22px margin:auto;">
		<span class="icon-back"
			style="width: 20px; height: 20px; display: block"></span>
	</div>
	<!--提交到controller getAllBidInfos中 -->
	<div  id="showInfoForm">
		<input type="hidden" name="projectId" id="projectId" value="">
		&nbsp;&nbsp;&nbsp;<input type="submit" id="AllBidsSubmit"
			class="easyui-linkbutton" value="全部显示">
		&nbsp;&nbsp;&nbsp;项目类型:<input id="typeInfo" class="easyui-combobox" 
			name="typeId" style="width: 100px; height: 22px;" />&nbsp;&nbsp;&nbsp;
		价格:<input type="text" class="easyui-numberbox"
			data-options="min:0,precision:2" id="minPrice" name="minPrice"
			style="width: 80px; height: 22px;" />&nbsp;- <input type="text"
			class="easyui-numberbox" data-options="min:0,precision:2"
			id="maxPrice" name="maxPrice" style="width: 80px; height: 22px;">&nbsp;&nbsp;&nbsp;
		项目名称:<input type="text" class="easyui-textbox"   
		        style="width: 100px; height: 22px;"  id="projectName"   name="projectName" >
			 <input type="submit" id="BidSubmit" class="easyui-linkbutton" value="查询">
			  <input type="button" id="BidReset" class="easyui-linkbutton" value="清空">
	</div>

	<!-- 在线阅读form -->
	<form action="${pageContext.request.contextPath}/ReadWord/readWord"
		id="onlineForm" method="post">
		<input type="hidden" id="online_method" name="filePath">
	</form>

	<!--显示根据多个条件查询的结果  -->
	<table id="showBidInfosByCondition"></table>


	<script type="text/javascript">
		$(function() {
			//跳回到bid.jsp页面
			$('.icon-back').click(function() {
				var currPage = $('#tabs').tabs('getSelected');
				currPage.panel('refresh', '/GJZB/jsp/bid/bid.jsp');
			});
			//获取项目类型的下拉列表
			$('#typeInfo').combobox({
				url : '/GJZB/type/getAllTypes',
				valueField : 'typeId',
				textField : 'projectType',
				panelHeight : 80,
			});
			//ajax提交form表单
			$('#BidSubmit').click(function() {
				$('#showBidInfosByCondition').datagrid({
					url : "/GJZB/Project/getAllBidInfos?projectId="+$('#projectId').val()+"&typeId="+$('#typeInfo').combo('getValue')
							+"&minPrice="+$('#minPrice').val()
							+"&maxPrice="+$('#maxPrice').val()+"&projectName="+$('#projectName').val()
				});
				var $pInfo = $('#showBidInfosByCondition').datagrid("getPager");
				$pInfo.pagination({
					showPageList : false,
					layout : [ 'first', 'prev', 'links', 'next',
							'last', 'manual' ],
				});
				return false;
			});

			//点击全部
			$('#AllBidsSubmit').click(function() {
				$('#showBidInfosByCondition').datagrid({
					url : "/GJZB/Project/getAllBidInfos?projectId="+$('#projectId').val()+"&typeId="+$('#typeInfo').val()
							+"&minPrice="+$('#minPrice').val()
							+"&maxPrice="+$('#maxPrice').val()+"&projectName"+$('#projectName').val()
				});
				var $pInfo = $('#showBidInfosByCondition').datagrid("getPager");
				$pInfo.pagination({
					showPageList : false,
					layout : [ 'first', 'prev', 'links', 'next',
							'last', 'manual' ],
				});
				return false;
				
			});

			//自动去加载
			$('#showBidInfosByCondition')
					.datagrid(
							{
								/* 	获取未投标的所有项目信息 */
								url : "/GJZB/Project/getAllBidInfos",
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
										},
										{
											field : 'projectName',
											title : '项目名称',
											width : 100,
										},
										{ //下载文件
											field : 'projectcontent',
											title : '下载项目内容',
											width : 100,
											formatter : function(value, row,
													index) {
												return "<a href='/GJZB/ReadWord/Resourcesdownload.do?path="
														+ row.projectcontent
														+ "'>"
														+ row.projectName
														+ "</a>";
											}
										},
										{//在线阅读
											field : 'online_content',
											title : '在线阅读项目',
											width : 100,
											formatter : function(value, row,
													index) {
												return "<a href=javascript:ApostProjectName('"
														+ row.projectcontent
														+ "')>"
														+ row.projectName
														+ "</a>";
											}
										},
										{
											field : 'price',
											title : '价格',
											width : 100,
										},
										{
											field : 'submit_bid',
											title : '投标',
											width : 100,
											formatter : function(value, row,
													index) {
												/* alert("row.win " + row.win);  */
												return "<a href=javascript:addBidFun('"
														+ row.projectId
														+ "')>投标</a>";
											}
										}, ] ]
							});
			
			
			//点击清空
			$('#BidReset').click(function(){
				    $('#typeInfo').combobox('clear');
					$('#minPrice').numberbox({'value': ''});
					$('#maxPrice').numberbox({'value': ''});
					$('#projectName').textbox("setValue", "");
					return false;
			});

		});

		function addBidFun(projectId) {
			$('#add_projectId').val(projectId);
			jquerySubByFId('addBidForm', addBid, null, "json");
		}

		function addBid(data) {
			/* alert("data"+data+"投标成功！"); */
			if (data > 0) {
				var InfoRow = $('#showBidInfosByCondition').datagrid(
						'getSelected');
				if (InfoRow) {
					var InfoRowIndex = $('#showBidInfosByCondition').datagrid(
							'getRowIndex', InfoRow);
					$('#showBidInfosByCondition').datagrid('deleteRow',
							InfoRowIndex);
					$('#showBidInfosByCondition').datagrid('reload');
				}
			} else {
				alert("投标失败!");
			}
		}

		//在线阅读、、post提交
		function ApostProjectName(projectName) {
			$('#online_method').val(projectName);
			jquerySubByFId('onlineForm', successFun, null, "json");
		}
		//成功返回数据（文件的内容）
		function successFun(data) {
			alert(data.text);
		}
	</script>
</body>
</html>