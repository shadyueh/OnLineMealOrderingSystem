<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商品详细信息</title>

<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript">

 
 function pay(idp){
 
 var flag=window.confirm("确认支付吗？");
 if(flag){
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
//dataType:'json',
url:'${pageContext.request.contextPath}/payOrder',
data:"orderid="+idp,
success:function(result){
alert("支付成功!");
location.href="${pageContext.request.contextPath}/showNoPayOrder";
},
error:function(){
alert("异常，请检查！");
window.close();
}});}};	
	
 


</script>
</head>

<body>
<c:if test="${fn:length(nopayresult.orderInfos) == 0}">

 <div  align="center" style="margin:0 auto;top:100px;bottom:100px">
 				<font>你还没有未支付的订单，赶快去预定吧！</font>	</div>	
 				 <div  align="center">															
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}">回到首页</a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goon()">继续选购</a>
	       </div></c:if>

<c:if test="${fn:length(nopayresult.orderInfos) > 0}">
<c:forEach var="orderinfo" items="${ nopayresult.orderInfos }">

	<table  border="1" align="center" width="60%" >
		<tr>
				<td  colspan="8" style="border:0">商品信息：</td>

		</tr>

		<c:forEach items="${orderinfo.dishinfo_map}" var="dishinfo_map">
              <tr> 
             	<td rowspan="2" colspan="1" style="border:0"><img width="50px" height="50px" src="${pageContext.request.contextPath}${dishinfo_map.value.dish.imgurl}"></img></td>
				<td style="display:none">${dishinfo_map.value.dish.id}</td>
				<td colspan="2" style="border:0">商品名称：${dishinfo_map.value.dish.name}</td>
				<td colspan="2" style="border:0">所属分类：${dishinfo_map.value.dish.category}</td>
				<td colspan="2" style="border:0">商品单价：${dishinfo_map.value.dish.price}</td>
			</tr >
			<tr>
				<td colspan="4" style="border:0">商品描述：${dishinfo_map.value.dish.description}</td>
				<td colspan="2" style="border:0">预订数量：${dishinfo_map.value.count}</td>
			</tr>
           </c:forEach>
	
		<tr>
				<td align="left"  style="border:0">餐桌信息：</td>
			
		</tr>
		
		<c:forEach items="${orderinfo.tab_map}" var="tab_map">
              <tr> 
             	<td colspan="1" style="border:0"><img width="50px" height="25px" src="${tab_map.value.imgurl}"></img></td>
				<td style="display:none" style="border:0">${tab_map.value.id}</td>
				<td colspan="2" style="border:0">餐桌编号：${tab_map.value.name}</td>
				<td colspan="2" style="border:0">餐桌位置：${tab_map.value.location}</td>
				<td  style="border:0">餐桌容量：${tab_map.value.capacity}</td>

				</tr>
				 <tr> 
				 <td align="center" rowspan="1" colspan="8"  style="border:0">用餐时间：【${tab_map.value.begintime}】-【${tab_map.value.endtime}】</td>
				 </tr>
           </c:forEach>
           	<tr align="center">
				<td  colspan="2" style="border:0"><a>下单时间：${orderinfo.ordertime}</a></td>
				<c:if test="${orderinfo.paystate == 0}">
								<td  colspan="2" style="border:0"><a>支付状态：未支付</a></td>
				</c:if>
				<c:if test="${orderinfo.paystate == 1}">
								<td  colspan="2" style="border:0"><a>支付状态：已支付</a></td>
				</c:if>

				<td  colspan="2" style="border:0"><a>金额：${orderinfo.total}</a></td>
		</tr>
	</table>
	<div align="center">
	<a href="javascript:void(0)" onclick="pay('${orderinfo.id}')">去支付</a>
	<a href="${pageContext.request.contextPath}">稍后支付</a>
	<a href="${pageContext.request.contextPath}">取消订单</a>
	</div>
	</c:forEach>
	<div  align="center">
						<c:if test="${nopayresult.page==1}">
							首页&nbsp;&nbsp;&nbsp;
						</c:if>
						 <c:if test="${nopayresult.page!=1}">
							<a href="${pageContext.request.contextPath}/showNoPayOrder?page=1&rows=${nopayresult.rows}">首页</a>&nbsp;&nbsp;&nbsp;
						</c:if>
				
						
						<c:if test="${nopayresult.page=='1'}">
							上一页&nbsp;&nbsp;&nbsp;
						</c:if> 
						<c:if test="${nopayresult.page!=1 }">
							<a href="${pageContext.request.contextPath}/showNoPayOrder?page=${nopayresult.page-1 }&rows=${nopayresult.rows}">上一页</a>&nbsp;&nbsp;&nbsp;
						</c:if> 
				
						
						<c:if test="${nopayresult.page==nopayresult.totalPage}">
							下一页&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${nopayresult.page!=nopayresult.totalPage}">
							<a href="${pageContext.request.contextPath}/showNoPayOrder?page=${nopayresult.page+1 }&rows=${nopayresult.rows}">下一页</a>&nbsp;&nbsp;&nbsp;
						</c:if> 
				
                       
                       <c:if test="${nopayresult.page==nopayresult.totalPage}">
							尾页&nbsp;&nbsp;&nbsp;
						</c:if> 
						<c:if test="${nopayresult.page!=nopayresult.totalPage }">
							<a href="${pageContext.request.contextPath}/showNoPayOrder?page=${nopayresult.totalPage}&rows=${nopayresult.rows}">尾页</a>&nbsp;&nbsp;&nbsp;
						</c:if> 
						
						</div>
						
	</c:if>
			
			</body>
</html>

