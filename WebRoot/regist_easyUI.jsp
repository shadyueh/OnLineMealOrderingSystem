<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'regist.jsp' starting page</title>

<link rel="stylesheet" href="style.css" type="text/css" media="screen" />

<script type="text/javascript">
	change=function() {
		document.getElementById("cimg").src = "${pageContext.request.contextPath}/checkImg?time="
				+ new Date().getTime();
	};
	function checkForm() {
		
		var f1=checkNull("username");
		
		var f2=checkNull("password");
		
		var f3=checkNull("repassword");
			
		var f4=checkNull("nickname");
		
		var f5=checkNull("email");

		var f6=checkNull("checkcode");
		
		var f7=document.getElementById("password").value==document.getElementById("repassword").value;
		
		if(!f7){
			document.getElementById("repassword_message").innerHTML="<font color='red'>两次密码不一致</font>";
		}
		
		return f1&&f2&&f3&&f4&&f5&&f6&&f7;
		
	};
	
	function checkNull(fieldName){
		var value = document.getElementById(fieldName).value;

		var reg = /^\s*$/; //代表0个或多个空字符。		

		if(reg.test(value)){
			document.getElementById(fieldName+"_message").innerHTML="<font color='red'>"+fieldName+"不能为空</font>";
			return false;
		}else{
			return true;
		}

	}
</script>
</head>

<body>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
 <table cellpadding="5" align="center">

	        <tr>
	            <td>用户名:</td>
	              <td><input class="easyui-textbox textbox" type="text" name="username" data-options="required:true,missingMessage:'必填项'" />

	        </tr>


	        <tr>
	            <td>密码:</td>
	            <td><input id="pwd" class="easyui-validatebox easyui-textbox" type="password" name="password" data-options="required:true,missingMessage:'必填项'" /></td>
	        </tr>
	        	        <tr>
	            <td>确认密码:</td>
	         
	            	 <td><input id="rpwd" class="easyui-validatebox easyui-textbox" type="password" name="repassword" data-options="required:true,delay:1000,missingMessage:'必填项', invalidMessage:'两次密码不一致！'" validType="equals['#pwd']"></input></td>
	           
	        </tr>
	        <tr>
	            <td>用户昵称:</td>
	              <td><input class="easyui-validatebox easyui-textbox"  name="nickname" data-options="required:true,validType:'length[3,10]', delay:1000,missingMessage:'必填项,3到10个字符之间', invalidMessage:'3到10个字符！'" />

	        </tr>
	        <tr>
	            <td>用户邮箱:</td>
	              <td><input  class="easyui-validatebox easyui-textbox" type="text" name="email" data-options="required:true, validType:'email', delay:1000, missingMessage:'必填项', invalidMessage:'邮箱格式不正确！'" />

	        </tr>
	         <tr>
	            <td>手机号码:</td>
	              <td><input class="easyui-validatebox easyui-textbox" type="text" name="tel" data-options="required:true,delay:1000,missingMessage:'必填项', validType:'phoneRex'" />
	        </tr>
			<tr>
				<td>验证码</td>
				<td><input type="text" name="checkcode" id="checkcode" class="easyui-textbox">
				</td>
				<td><img
					src="${pageContext.request.contextPath}/checkImg"
					onclick="change();" id="cimg"><span id="checkcode_message"></span></td>
			</tr>
			<tr>
				<td><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">注册</a></td>
	   <td> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>
