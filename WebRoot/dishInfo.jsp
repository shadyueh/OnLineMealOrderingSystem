<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>商品详细信息</title>

<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript">
	function addDishToCart( ){
	var num	=$("#total").html();
var flag=window.confirm("确认添加到购物车吗？");
 if(flag){
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
url:'${pageContext.request.contextPath}/addDishToCart',
data:"id=${p.id}&num="+num,
success:function(result){
alert("添加成功!");
//location.href="${pageContext.request.contextPath}/pageDishList?page=${pd.page}&rows=${pd.rows}"
},
error:function(){
alert("异常，请检查！");
window.close();
}
});
 
  }
		
	}
	
	
$(function(){ 
$(".add").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())+1) 
setTotal(); 
}) 
$(".min").click(function(){ 
var t=$(this).parent().find('input[class*=text_box]'); 
t.val(parseInt(t.val())-1) 
if(parseInt(t.val())<0){ 
t.val(0); 
} 
setTotal(); 
}) 
function setTotal(){ 
var s=0; 
$("#num").each(function(){ 
s+=parseInt($(this).find('input[class*=text_box]').val())*parseFloat($(this).find('span[class*=price]').text()); 
}); 
$("#total").html(s.toFixed(2)); 
} 
setTotal(); 

}) 

function goon(){
location.href=document.referrer;
}
</script>
</head>

<body>

	<table border="1" align="center" >
		<tr>
			<td rowspan="5"><img
			width="150px" height="150px"
				src="${pageContext.request.contextPath}${p.imgurl}"></img>
			</td>
			<td>商品名称:${p.name }</td>
		</tr>
		<tr>
			<td>商品余量:${p.pnum }</td>
		</tr>
		<tr>
			<td>商品类别:${p.category }</td>
		</tr>
		<tr>
			<td>商品价格:${p.price }</td>
		</tr>
		<tr>
			<td>商品描述:${p.description }</td>
		</tr>
		<tr>
		
				<td id="num"> 
		<span style="display:none">单价:</span><span class="price" style="display:none">${p.price}</span> 
		
		<p >总价：<label id="total"></label></p> 
	
		<input class="min" name="" type="button" value="-" /> 
		<input class="text_box" id="num" name="" type="text" value="1" /> 
		<input class="add" name="" type="button" value="+" /> 
		</td> 
	
			<td colspan="2" align="right">
			<img src="${pageContext.request.contextPath}/images/buy.bmp" onclick="addDishToCart()">
			</td>
		</tr>
		<tr>
		<td colspan="2">
				<c:if test="${!empty sessionScope.cart}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goon()">继续选订</a></c:if>
				<c:if test="${!empty sessionScope.cart}">
				&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)">查看购物车</a></c:if>
<c:if test="${!empty sessionScope.cart}">
				&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="updateState('${product.id}',1)">去结算</a></c:if>
			</td>
			
			</tr></table></body>
</html>
