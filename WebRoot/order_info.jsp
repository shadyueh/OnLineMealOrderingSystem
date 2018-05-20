<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<div style="padding:10px 10px 10px 10px">
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
		<c:forEach items="${orderInfo.dishinfo_map}" var="entry1">
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
			 <p style="text-align:center">总价：<label id="total" >${dishinfo_map.total}</label></p> 
	       </td>
	       </tr></table>
	       <table border="1" align="center" width="50%" >
		<tr>
				<th align="left"><input type="checkbox"></th>
				<th style="display:none">菜品编号</th>
				<th >餐桌编号</th>
				<th >餐桌位置</th>
				<th >餐桌容量</th>
				<th >操作</th>
		</tr>
		<c:forEach items="${orderInfo.tab_map}" var="entry">
              <tr> 
             	<td width="50px"><img width="50px" height="50px" src="${entry.value.imgurl}"></img></td>
				<td style="display:none">${entry.value.id}</td>
				<td width="16%" >${entry.value.name}</td>
				<td width="16%" >${entry.value.location}</td>
				<td width="16%" >${entry.value.capacity}</td>
				<td><a href="javascript:void(0)" onclick="dele('${entry1.value.id}')">移除</a></td>
				</tr>
           </c:forEach>
	       <tr>
	       <td colspan="7" align="center">
	      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}">回到首页</a>
	       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="goon()">继续选购</a>
	       
	       </td>
	        </tr></table>
	<div align="center">
	<a href="javascript:void(0)" onclick="pay('${data1.id}')">支付</a>
	<a href="${pageContext.request.contextPath}/payLatter">稍後支付</a> 
	</div>

<input style="display:none" id="data" ></input>



</div>







<script type="text/javascript">


</script>