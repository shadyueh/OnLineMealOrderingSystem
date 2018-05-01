<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script type="text/javascript">
 function dele(id){
 
 var flag=window.confirm("确认删除吗？");
 if(flag){
 location.href="${pageContext.request.contextPath}/delete?id="+id;
  }
 }
</script>
<body>

	<c:if test="${empty pd}">
无产品信息
</c:if>
	<c:if test="${not empty pd}">
		<table border="1" align="center" width="65%">
			<tr>
				<th><input type="checkbox"></th>
				<th>菜品编号</th>
				<th>菜品名称</th>
				<th>菜品价格</th>
				<th>菜品余量</th>
				<th>菜品描述</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${pd.dishs}" var="product">
              <tr> 
                <td><input type="checkbox"></td>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.pnum}</td>
				<td>${product.description}</td>
				<td>
				<a href="${pageContext.request.contextPath}/findbyid?id=${product.id}">编辑</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="dele('${product.id}')">删除</a>
				</td> 
				</tr>
            </c:forEach>
                   <tr>
				<td colspan="9" align="center"><a
					href="${pageContext.request.contextPath}/pageDishList?page=1&rows=${pd.rows}">首页</a>&nbsp;&nbsp;&nbsp;

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
						</c:if> <a
					href="${pageContext.request.contextPath}/pageDishList?page=${pd.rows}">尾页</a>&nbsp;&nbsp;&nbsp;

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
						end="${pb.totalPage}" var="n" step="1">
						<c:if test="${n==pb.pageNum}">
							<a
								href="/day20_1/findAllByPage?pageNum=${n}&currentPage=${pb.currentPage}"><font
								color='red'>第${n}页</font> </a>&nbsp;&nbsp;
							</c:if>

						<c:if test="${n!=pb.pageNum}">
							<a
								href="/day20_1/findAllByPage?pageNum=${n}&currentPage=${pb.currentPage}">第${n}页</a>&nbsp;&nbsp;
					
								</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="9" align="center"><my:page pb="${pb}" />
				</td>
			</tr>
		</table>

		</table>
       &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/add.jsp">添加商品</a> 
	</c:if>




</body>

</html>