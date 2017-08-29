<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<style type="text/css">
	.logo {
		width: 180px;
		height: 50px;
		line-height: 50px;
		text-align: center;
		font-size: 20px;
		font-weight: bold;
		float: left;
		color: #fff;
	}
	
	.logout {
		float: right;
		padding: 30px 15px 0 0;
		color: #fff;
	}
	
</style>
</head>
<body>
	<div
		data-options="region:'north',title:'header',split:true,noheader:true"
		style="height: 60px; background: #666;">
		<div class="logo">国际招标</div>
		<div class="logout">
			 <a href="javascript:void(0)" id="login" >登录</a> | <a href="javascript:void(0)" id="regit">
				注册</a>
		</div>
	</div>
	
	<!--注册窗口-->
	<div id="wregit" class="easyui-dialog" title="注册"
		data-options="closed:true,iconCls:'icon-save'" style="padding: 20px;">
		<form id="regitForm" action="" method="post">
			<table cellpadding="5">
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
		
	</div>	
		<!--登录表单-->
	<div id="login_div" class="easyui-dialog" data-options="modal:true,closed:true">
		<form action="" id="login_form">
			<p>
				<label for="userName" class="label-top">姓名:</label>
				<input id="login_userName" name="userName" class="easyui-validatebox tb"><br>
			</p>
			<p>
				<label for="userPw" class="label-top">密码:</label>
				<input id="login_userPw" type="password" name="userPw" class="easyui-validatebox tb"
				data-options="required:true">
			</p>
			<p>
				<label for="check_code" class="label-top">验证码:</label>
					<input  id="check_code" name="check_code" class="easyui-validatebox tb" data-options="required:true">
					<span class="verifyCodeImg">
          				<img id="vCode"  src="" />
          		    </span>
				<a href="javascript: _change()" id="change">换一张</a>
				<span class="tdError">
          			<label class="labelError" id="verifyCodeError"></label>
        		</span>
			</p>
			<span id="forgetPw"><a href="#">忘记密码</a></span><br>
			<input type="submit" id="login_submit" value="登录" />
			<input type="reset" id="login_reset" value="重置" />
		</form>
	</div>
	
</body>
