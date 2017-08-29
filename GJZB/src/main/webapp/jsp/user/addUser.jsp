<%@page import="com.lnsf.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/js/common_jsAndcss.jsp"%>
<title>添加用户</title>
<script type="text/javascript">
  function sysusersave(){
	  //准备使用jquery 提供的ajax Form提交方式
	  //将form的id传入，方法自动将form中的数据组成成key/value数据，通过ajax提交，提交方法类型为form中定义的method，
	  //使用ajax form提交时，不用指定url，url就是form中定义的action
	  //此种方式和原始的post方式差不多，只不过使用了ajax方式
	  
	  //第一个参数：form的id
	  //第二个参数：sysusersave_callback是回调函数，sysusersave_callback当成一个方法的指针
	  //第三个参数：传入的参数， 可以为空
	  //第四个参数：dataType预期服务器返回的数据类型,这里action返回json
	  //根据form的id找到该form的action地址
	  jquerySubByFId('userform',sysusersave_callback,null,"json");
	  
  }
  //ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
 function sysusersave_callback(data){
	  
	  message_alert(data);
	 // alert(data.message);
	  /* if(data.type=='0'){
		  $.messager.alert('提示信息',data.message,'success');
	  }else{
		  $.messager.alert('提示信息',data.message,'error');
	  } */
	 
	  //action返回的是json数据
	  //如果是成功显示一个对号
	  
	  //如果是错误信息
	  
	//提交结果类型
	//data中存放的是action返回Resultinfo，有一个type
		/* var type=data.resultInfo.type;
		//结果提示信息
		var message=data.resultInfo.message;
		//alert(message);
		if(type==0){
			//如果type等于0表示失败，调用 jquery easyui的信息提示组件
			$.messager.alert('提示信息',message,'error');
		}else if(type==1){
			$.messager.alert('提示信息',message,'success');
		}else if(type==2){
			$.messager.alert('提示信息',message,'warning');
		}else if(type==3){
			$.messager.alert('提示信息',message,'info');
		}
	   */

  }
 
</script>
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
	<form id="userform" action="${baseurl}user/addsysusersubmit.action"
		method="post">
		<table border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>
			<tr>
				<td>用户名:</td>
				<td><input type="text" class="textbox" name="userName"
					id="userName" /></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="text" class="textbox" name="userPw"
					id="userPw" /></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input class="easyui-validatebox textbox"
					data-options="required:true,novalidate:true" name="reuserPw"
					id="reuserPw" /></td>
			</tr>
			<tr>
				<td>邮箱:</td>
				<td><input type="text" class="textbox" name="email" id="email" /></td>
				<td><label class="labelError" id="emailError"
					style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label></td>

			</tr>
			<tr>
				<td>电话号码:</td>
				<td><input type="text" class="textbox" name="phone" id="phone" /></td>
				<td><label class="labelError" id="phoneError"
					style="line-height: 22px; font-size: 10pt; color: #f40000; display: none;"></label></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					class="easyui-linkbutton" id="submit" value="注册" /> <input
					type="reset" class="easyui-linkbutton" name="reset" value="取消" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>