<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@include file="/js/common_jsAndcss.jsp"%>
<title>国际招标系统</title>
<style type="text/css">
.logo {
	margin-top: 10px;
	height: 100px;
	width:320px;
	background:url(img/topleft.jpg) no-repeat;
	float: left;
}

.logout {
	width: 100px;
	float: right;
	margin-right:10px;
}

.logout img{
	width: 100px;
    height: 80px;
    border-radius: 14px 100px 100px 100px;
    -moz-border-radius: 100px 100px 100px 100px;
    -webkit-border-radius: 100px 100px 100px 100px;
    border: 1px solid #000000;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
}
</style>
</head>
<body class="easyui-layout">
	<div region="north" split="false" border="true" style="height: 90px;overflow:hidden;background:url(img/topbg.gif) repeat-x;" >
		<div
			data-options="region:'north',title:'header',split:true,noheader:true"
			style="height: 90px; ">
			<div class="logo">
				<img src="img/logo1.png" title="系统首页" />
			</div>
			
			<c:if test="${ empty user }">
				<div class="logout">
					<a href="login.jsp" id="login"  target="_parent">登录</a> | <a
						href="jsp/user/regist.jsp" target="_parent" id="regit"> 注册</a>
				</div>
			</c:if>
			
			<c:if test="${ !empty user }">
				<div class="logout">
					<a href="#" class="easyui-menubutton" menu="#topbar-menu" >
						<img alt="${user.userName }" id="headImg" src="<%=htph %>/image/image/${user.profilePic}">
					</a>
					<div id="topbar-menu" style="width:150px;">
					    <div data-options="iconCls:'icon-edit'" onclick="showPersonal()">修改个人信息</div>
					    <div data-options="iconCls:'icon-lock'" onclick="changePw()">修改密码</div>
					    <div data-options="iconCls:'icon-cancel'"><a href="users/exit">退出</a></div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div
		data-options="region:'south',title:'footer',split:true,noheader:true"
		style="height: 35px; line-height: 30px; text-align: center;"></div>
	
	<c:if test="${ !empty user }">
	<div
		data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'"
		style="width: 180px; padding: 10px;">
		<ul id="nav"></ul>
	</div>
	</c:if>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="tabs">
			<div title=" 起始页" iconCls="icon-house" href="default.jsp"
				style="padding: 0 10px; display: block;">
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function showPersonal(){
			if ($('#tabs').tabs('exists', '修改个人信息')) {
				$('#tabs').tabs('select', '修改个人信息');
			} else{
				$('#tabs').tabs('add', {
					title : '修改个人信息',
					closable : true,
					href : 'jsp/user/personal.jsp',
				});
			}
		}
		function changePw(){
			if ($('#tabs').tabs('exists', '修改密码')) {
				$('#tabs').tabs('select', '修改密码');
			} else{
				$('#tabs').tabs('add', {
					title : '修改密码',
					closable : true,
					href : 'jsp/user/resetPwd.jsp',
				});
			}
		}
		$('#tabs').tabs({
			fit : true,
			border : false,
		});
		//导航条
		$('#nav').tree({
			url : "${pageContext.request.contextPath}/nav?userlevel=${user.userlevel}",
			lines : true,
			animate : true,
			onLoadSuccess : function(node, data) {
				var _this = this;
				if (data) {
					$(data).each(function(index, value) {
						if (this.state == 'closed') {
							$(_this).tree('expandAll');
						}
					});
				}
			},
			onClick : function(node) {
				if (node.url) { 
					if ($('#tabs').tabs('exists', node.text)) {
						$('#tabs').tabs('select', node.text);
					} else {
						$('#tabs').tabs('add', {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							href : 'jsp/' + node.url + '.jsp',
						});
					}
				}
			}

		});
	</script>

</body>
</html>