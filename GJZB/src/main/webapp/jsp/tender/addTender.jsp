<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8"> 
		<title>添加项目</title>
		<%@include file="/js/common_jsAndcss.jsp"%>
	<style type="text/css">
			#pTable tr td{
				font-size: 24px;
			}
		
		</style>
</head>
<body>
	
	<div style="padding: 3px 400px; border-bottom: 1px solid #ccc;font-size: 30px;"><b>发布项目</b></div>
	<!-- basePath  -->
	<form action="/GJZB/ReadWord/addProjectResource" method="post"
		id="addUserForm" enctype="multipart/form-data" style="margin-left:300px;margin-top:30px;">
		<input type="hidden" name="user.userId" id="user.userId"
			value="${user.userId }" />
		<table id="pTable">
			<%-- 项目id  设置默认--%>
			<tr>
				<td><input type="hidden" name="projectId" id="projectId" style="width: 220px;height: 36px;
				border-radius: 5%;margin-bottom: 8px;"/></td>
			</tr>
			<tr>
				<td style="font-size: 22px;">项目名称:</td>
				<td><input name="projectName" id="projectName" style="width: 220px;height: 30px;
				border-radius: 5%;margin-bottom: 8px;"  class="easyui-validatebox" data-options="required:true"  type="text"  ></input></td>
			</tr>
			<tr>
				<td style="font-size: 22px;">价格：</td>
				<td><input name="price" id="price" type="text" class="easyui-validatebox"
				 style="width: 220px;height: 30px;
				border-radius: 5%;margin-bottom: 8px;"></input></td>
			</tr>

			<tr>
				<td style="font-size: 22px;">项目类型：</td>
				<td>
					<div name="type.typeId" class="type"  style="width: 220px;height: 34px;
				margin-bottom: 8px;"></div>
				</td>
			</tr>

			<tr>
				<td style="font-size: 22px;">发布时间:</td>
				<td><input name="releaseTime" id="releaseTime" type="date"  style="width: 220px;height: 30px;
				border-radius: 5%;margin-bottom: 8px;"
					 class="easyui-validatebox" data-options="required:true"
					 ></input></td>
			</tr>
			<tr>
				<td style="font-size: 22px;">截止时间：</td>
				<td><input name="lastTime" id="lastTime" type="date"
					class="easyui-validatebox" data-options="required:true"
					style="width: 220px;height: 30px;
				border-radius: 5%;margin-bottom: 8px;"></input></td>
			</tr>

			<tr>
				<td style="font-size: 22px;">上传文档：</td>
				<td><input type="file" name="file" id='projectFile'
					 class="easyui-validatebox" data-options="required:true"
					 style="width: 220px;height: 30px;
				border-radius: 5%;margin-bottom: 8px;"></td>
			</tr>
			<tr>
				<td><input type="button" id="add_submit" value="确认发布" style="margin-left:100px;margin-top:10px;width: 75px; height: 30px;"></input></td>
				<td><input type="reset" id="pReset" value="重置" style="margin-left:50px;margin-top:10px;width: 75px; height: 30px;"/></td>
			</tr>
		</table>
	</form>

	<script type="text/javascript">
		function addTender(data){
			if(data){
				var currTab =  $('#tabs').tabs('getSelected'); //获得当前tab
				currTab.panel('refresh', '/GJZB/jsp/tender/nottendered.jsp');
			}else{
				alert("添加项目失败！");
			}
		}
		$(function(){
			$("#add_submit").click(function(){
				var pFlag=true;
				if($('#projectName').val()==""){
					$('#projectName').focus();
					pFlag=false;
					return false;
				}
				if($('#price').val()==""){
					$('#price').focus();
					pFlag=false;
					return false;
				}
				/*if($('.type').val()==""){
					$('.type').combo('textbox').focus();
					pFlag=false;
					return false;
				}*/
				if($('#releaseTime').val()==""){
					$('#releaseTime').focus();
					pFlag=false;
					return false;
				}
				if($('#lastTime').val()==""){
					$('#lastTime').focus();
					pFlag=false;
					return false;
				}
				if($('#lastTime').val()!=""){
					var endtime = $('#lastTime').val();
		            var starttime = $('#releaseTime').val();
		            var start = new Date(starttime.replace("-", "/").replace("-", "/"));
		            var end = new Date(endtime.replace("-", "/").replace("-", "/"));
		            if (end < start) {
		                alert('结束日期不能小于开始日期！');
		                pFlag=false;
		                return false;
           			} 
				}
				if($('#projectFile').val()==""){
					alert('文件不能为空!');
					pFlag=false;
					return false;
				}
				
				if(pFlag)
				{
					jquerySubByFId('addUserForm',addTender,null,"json");
				}
					
			});
			
			$('#projectName').validatebox({
				required : true,
				validType : 'length[1,100]',
				missingMessage : '该输入项为必输入项',
			});
			$('#price').validatebox({
				required : true,
				validType : 'length[1,100]',
				missingMessage : '该输入项为必输入项',
			});
			$('.type').validatebox({
				required : true,
				validType : 'length[1,100]',
				missingMessage : '该输入项为必输入项',
			});
		});
		$('.type').combobox({
			valueField : 'typeId',
			textField : 'projectType',
			url : '/GJZB/type/test/getTypes',
			required : true,
		});
		
		
		function ckdate() {
            var endtime = $('#lastTime').val();
            var starttime = $('#releaseTime').val();
            var start = new Date(starttime.replace("-", "/").replace("-", "/"));
            var end = new Date(endtime.replace("-", "/").replace("-", "/"));
            if (end < start) {
                alert('结束日期不能小于开始日期！');
                return false;
            }
            else {
                return true;
            }

        }
	</script>
</body>
</html>
