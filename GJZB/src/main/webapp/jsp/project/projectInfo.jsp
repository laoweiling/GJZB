<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目信息</title>
<%@include file="/js/common_jsAndcss.jsp"%>
</head>
<body>
<script type="text/javascript">
	$(function(){
		$('#proImg').tooltip({
			content : '供应商',
			});

	});
	function proProjectName(){
		var projectName = $("#pro_a").html();
		$('#BidOnline_method').val(projectName);
		jquerySubByFId('proForm',proFun,null,"json");
	}
	
	function proFun(data){
		alert(data.text);
	}
</script>

	<!--文件的上传下载和在线查看-->
	<form action="${pageContext.request.contextPath}/ReadWord/readWord" id="proForm" method="post" >
		<input type="hidden" id="BidOnline_method" name="filePath">
	</form>
	
	<div class="projectInfo" >
	   <div class="content" style="width:800px;height:850px;">
           <form action="" method="post" class="login-form" >
		<img alt="供应商" id="proImg" class="pro_img"  src="<%=htph %>/image/image/${project.user.profilePic}" />
               <div class="username">
                   <input type="text" name="username" autocomplete="on" readonly="readonly" value="${project.projectName }"/>
                   <span class="user-icon icon">标题</span>
               </div>
			   <div class="username">
                   <input type="text" name="username" autocomplete="on" readonly="readonly" value="${project.type.projectType }"/>
                   <span class="user-icon icon">类型</span>
               </div>
			   <div class="username">
                   <input type="text" name="username" autocomplete="on" readonly="readonly" value="${project.releaseTime } 到   ${project.lastTime } "/>
                   <span class="user-icon icon">时间</span>
               </div>
			   <div class="username">
                   <input type="text" name="username" autocomplete="on" readonly="readonly" value="${project.price } "/>
                   <span class="user-icon icon">价钱</span>
               </div>
			   <div class="username" style="height:60px;">
			   		<a id="pro_a" style="font-size:20px;text-align:center;height:60px; padding-left:60px;"
			   		href="javascript:proProjectName()">${project.projectcontent }</a>
                   <span class="user-icon icon" style="width:80px" >在线查看文件</span>
               </div>
			   <div class="username" style="height:60px;">
			   		<a id="pro_download" style="font-size:20px;text-align:center;height:60px; padding-left:60px;"
			   		href="/GJZB/ReadWord/Resourcesdownload.do?path=${project.projectcontent}">${project.projectcontent }</a>
                   <span class="user-icon icon" style="width:80px" >下载文件</span>
               </div>
            </form> 
	   </div>
	   </div>
</body>
</html>