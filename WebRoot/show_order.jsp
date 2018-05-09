<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商品详细信息</title>

<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript">
 function dele(idp){
 
 var flag=window.confirm("确认移除吗？");
 if(flag){
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
//dataType:'json',
url:'${pageContext.request.contextPath}/deleteDishFromCart',
data:"id="+idp,
success:function(result){
alert("移除成功!");
location.href="${pageContext.request.contextPath}/showcart.jsp";
},
error:function(){
alert("异常，请检查！");
window.close();
}});}};	
	
 function deleteall(){
 
 var flag=window.confirm("确认清空购物车吗？");
 if(flag){
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
data:'',
//dataType:'json',
url:'${pageContext.request.contextPath}/deleteAllFromCart',
success:function(result){
alert("移除成功!");
location.href="${pageContext.request.contextPath}/showcart.jsp";
},
error:function(){
alert("异常，请检查！");
window.close();
}});}};		
	
	
$(function(){ 
$(".add").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())+1) 
var id="0"+$(this).parent().find('span[class*=id]').text(); 
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
data:"op=add&id="+id,
//dataType:'json',
url:'${pageContext.request.contextPath}/changeDishNumToCart',
success:function(result){
alert("修改数量成功!");
location.href="${pageContext.request.contextPath}/showcart.jsp";
},
error:function(){
alert("异常，请检查！");
window.close();
}});

setTotal(); 
}) 
$(".min").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())-1) 
if(parseInt(t.val())<0){ 
t.val(0); 
} 

var id="0"+$(this).parent().find('span[class*=id]').text(); 
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
data:"op=min&id="+id,
//dataType:'json',
url:'${pageContext.request.contextPath}/changeDishNumToCart',
success:function(result){
alert("修改数量成功!");
location.href="${pageContext.request.contextPath}/showcart.jsp";
},
error:function(){
alert("异常，请检查！");
window.close();
}});

setTotal(); 
}) 
function setTotal(){ 
var s=0; 
$(".num").each(function(){ 
s+=parseInt($(this).find('input[class*=text_box]').val())*parseFloat($(this).find('span[class*=price]').text()); 
}); 
$("#total").html(s.toFixed(2)); 
} 


}) 

function goon(){


location.href=document.referrer;
}
</script>
</head>

<body>
<c:if test="${cart.total==0.0}">

 <div  align="center" style="margin:0 auto;top:100px;bottom:100px">
 				<font>你的购物车还是空的，赶快去预定吧！</font>	</div>	
 				 <div  align="center">															
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}">回到首页</a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goon()">继续选购</a>
	       </div></c:if>

<c:if test="${cart.total!=0.0}">
	<table border="1" align="center" >
		<tr>
				<th align="left"><input type="checkbox"></th>
				<th style="display:none">菜品编号</th>
				<th >菜品名称</th>
				<th >所属分类</th>
				<th >菜品描述</th>
				<th >菜品价格</th>
				<th >选订数量</th>
				<th >操作</th>
		</tr>
		<c:forEach items="${cart.map}" var="entry1">
              <tr> 
             	<td width="50px"><img width="50px" height="50px" src="${pageContext.request.contextPath}${entry1.value.dish.imgurl}"></img></td>
				<td style="display:none">${entry1.value.dish.id}</td>
				<td width="16%" >${entry1.value.dish.name}</td>
				<td width="20%">${entry1.value.dish.category}</td>
				<td width="20%">${entry1.value.dish.description}</td>
				<td width="10%">${entry1.value.dish.price}</td>
				<td class="num" > 
				    <span style="display:none">id:</span><span class="id" style="display:none">${entry.value.dish.id}</span> 
					<span style="display:none">单价:</span><span class="price" style="display:none">${entry.value.dish.price}</span> 
					<input class="min" name="" type="button" style="text-align:center" value="-" /> 
					<input class="text_box" id="num" name="" style="text-align:center" type="text" value="${entry1.value.count}" /> 
					<input class="add" name="" type="button" style="text-align:center" value="+" /> 
				</td> 
				<td><a href="javascript:void(0)" onclick="dele('${entry1.value.dish.id}')">移除</a></td>
				</tr>
           </c:forEach>
             <tr>
	       <td colspan="7" align="center">
			 <p style="text-align:center">总价：<label id="total" >${cart.total}</label></p> 
	       </td>
	       </tr>
	       <tr>
	       <td colspan="7" align="center">
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}">回到首页</a>
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:void(0)" onclick="deleteall( )">清空购物车</a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goon()">继续选购</a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/showOrder">结算</a>
	       
	       </td>
	       
	       </tr>
		
	</table>
	
	
	
	<table border="1" align="center" width="50%" >
		<tr>
				<th align="left"><input type="checkbox"></th>
				<th style="display:none">菜品编号</th>
				<th >餐桌编号</th>
				<th >餐桌位置</th>
				<th >餐桌容量</th>
				<th >操作</th>
		</tr>
		<c:forEach items="${cart.tmap}" var="entry">
              <tr> 
             	<td width="50px"><img width="50px" height="50px" src="${entry.value.imgurl}"></img></td>
				<td style="display:none">${entry.value.id}</td>
				<td width="16%" >${entry.value.name}</td>
				<td width="16%" >${entry.value.location}</td>
				<td width="16%" >${entry.value.capacity}</td>
				<td><a href="javascript:void(0)" onclick="dele('${entry1.value.id}')">移除</a></td>
				</tr>
           </c:forEach>


		
	</table>
	<a>支付</a> 
	<a>稍後支付</a> 
	</c:if>
			
			</body>
</html>

