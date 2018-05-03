<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-cache"/>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<title>Insert title here</title>

</head>

<script type="text/javascript">
 function dele(idp){
 
 var flag=window.confirm("确认删除吗？");
 if(flag){
 var parm={id:idp};
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
//dataType:'json',
url:'${pageContext.request.contextPath}/deleteDishById',
data:"id="+idp,
success:function(result){
alert("修改成功!");
location.href="${pageContext.request.contextPath}/pageDishList?page=${pd.page}&rows=${pd.rows}"
},
error:function(){
alert("异常，请检查！");
window.close();
}
});
 
  }
  
  
  
  
 };
 
 
 
  function updateState(idp,state){
   var content;
  if (state==1){
  content="上架"
  }else {
   content="下架"
  }

 var flag=window.confirm("确认"+content+"吗？");
 if(flag){
 var parm={id:idp};
$.ajax({
type:"POST",
async:false,
headers:{"Content-Type":"application/x-www-form-urlencoded"},
processData:false,
cache:false,
url:'${pageContext.request.contextPath}/updateDishState',
data:"id="+idp+"&state="+state,
success:function(result){
alert(content+"成功!");
location.href="${pageContext.request.contextPath}/pageDishList?page=${pd.page}&rows=${pd.rows}"
},
error:function(){
alert("异常，请检查！");
window.close();
}
});
 
  }
  
  
  
  
 };
 function changeCurrentPage(value) {

		location.href = "${pageContext.request.contextPath}/pageDishList?rows="+ value;
	};

</script>
<body >

	<c:if test="${empty pd}">
无产品信息
</c:if>
	<c:if test="${not empty pd}">
		<table border="1"  align="center" width="65%">
			<tr>
				<th align="left"><input type="checkbox"></th>
				<th style="display:none">菜品编号</th>
				<th >菜品名称</th>
				<th >菜品价格</th>
				<th >菜品余量</th>
				<th >菜品描述</th>
				<th >操作</th>
			</tr>
			<c:forEach items="${pd.dishs}" var="product">
              <tr> 
                <td width="5%"><input type="checkbox"></td>
				<td style="display:none">${product.id}</td>
				<td width="15%">${product.name}</td>
				<td width="10%">${product.price}</td>
				<td width="10%">${product.pnum}</td>
				<td width="42%">${product.description}</td>
				<td width="18%">
				&nbsp;<a href="${pageContext.request.contextPath}/findDishById?id=${product.id}&dist=edit">编辑</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="dele('${product.id}')">删除</a>&nbsp;&nbsp;&nbsp;
				<c:if test="${product.state==0}">
				<a href="javascript:void(0)" id="content"  onclick="updateState('${product.id}',1)">上架</a></c:if>
				<c:if test="${product.state==1}">
				<a href="javascript:void(0)" id="content" onclick="updateState('${product.id}',0)">下架</a></c:if>
				
				</td> 
				</tr>
            </c:forEach>
                   <tr>
				<td colspan="9" align="center">
						<c:if test="${pd.page==1}">
							首页&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${pd.page!=1}">
						<a
					href="${pageContext.request.contextPath}/pageDishList?page=1&rows=${pd.rows}">首页</a>&nbsp;&nbsp;&nbsp;
						</c:if>
					<c:if test="${pd.page==1}">
							上一页&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${pd.page!=1}">
						<a
							href="${pageContext.request.contextPath}/pageDishList?page=${pd.page-1 }&rows=${pd.rows}">上一页</a>&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${pd.page==pd.totalPage}">
							下一页&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${pd.page!=pd.totalPage}">
						<a
							href="${pageContext.request.contextPath}/pageDishList?page=${pd.page+1 }&rows=${pd.rows}">下一页</a>&nbsp;&nbsp;&nbsp;
						</c:if> 
 <c:if test="${pd.page==pd.totalPage}">
							尾页&nbsp;&nbsp;&nbsp;
						</c:if> <c:if test="${pd.page!=pd.totalPage}">
						<a
					href="${pageContext.request.contextPath}/pageDishList?page=${pd.totalPage}&rows=${pd.rows}">尾页</a>&nbsp;&nbsp;&nbsp;
						</c:if> 
					<select name="currentPage"
					onchange="changeCurrentPage(this.value);">
						<option>--请选择每页条数--</option>
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="20">20</option>
				</select>
				</td>

			</tr>
			<tr>
				<td colspan="9" align="center"><c:forEach begin="1"
						end="${pd.totalPage}" var="n" step="1">
						<c:if test="${n==pd.page}">
						<a
							href="${pageContext.request.contextPath}/pageDishList?page=${n}&rows=${pd.rows}"><font
								color='red'>第${n}页</font> </a>&nbsp;&nbsp;
							
							</c:if>

						<c:if test="${n!=pd.page}">
							<a
								href="${pageContext.request.contextPath}/pageDishList?page=${n}&rows=${pd.rows}">第${n}页</a>&nbsp;&nbsp;
					
								</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="9" align="center"><my:page pd="${pd}" />
				</td>
			</tr>
		</table>

       &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/add.jsp">添加商品</a> 
	</c:if>




</body>

</html>