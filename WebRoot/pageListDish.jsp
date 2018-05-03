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
alert("删除成功!");
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
	var arr=[];
	arr[0] = new Array("state","已上架","已下架");
	arr[1] = new Array("name");
	arr[2] = new Array("price","0~5","5~10","10~15","15~25","25~40","40~60","60~85","85~115","115以上");
	arr[3] = new Array("pnum","0~5","5~10","10~25","25~40","40以上");
	arr[4] = new Array("category","酒水","饮料","荤菜","素菜","海鲜","野味","其它");
 function changeCondtion(value){
 //alert(value);
 	var select2 = document.getElementById("select2");
			for(var i=0;i<arr.length;i++){
				var inarr = arr[i];
				var str = inarr[0];
				
				
				if(value == str&&value !="name"){
				//alert("1");
					var select2 = document.getElementById("condtion");
					var name = document.getElementById("name");
					if(name.style.display=="inline"){
					//alert("input----not name---"+name.style.display);
					name.style.display="none";
					}
				
					
					
					
					if(select2.style.display=="none"){
					
					//alert("select2----not name---"+select2.style.display);
					select2.style.display="inline";
					}				
						var ops = select2.getElementsByTagName("option");
						for(var x=0;x<ops.length;x++){
						var op = ops[x];
						select2.removeChild(op);
						x--;}
	
					//alert("数组长度---"+inarr.length);
						for(var j=1;j<inarr.length;j++){
						var instr = inarr[j];
						var option = document.createElement("option");
						var text = document.createTextNode(instr);
						option.appendChild(text);
						select2.appendChild(option);
					
	
					}

					return;
				}else if(value =="name"){
				//alert("2");
					var select2 = document.getElementById("condtion");
					var name = document.getElementById("name");
					if(select2.style.display=="inline"){
					
						//alert("select2----in name---"+select2.style.display);
						select2.style.display="none";
					}
					
					if(name.style.display=="none"){
					//alert("input----in name---"+name.style.display);
							name.style.display="inline";
						}
				return;
				}else if(value =="--所有--"){
				var select2 = document.getElementById("condtion");
					var name = document.getElementById("name");
				if(select2.style.display=="inline"){
					
						select2.style.display="none";
					}
					if(name.style.display=="inline"){
					
					name.style.display="none";
					}
					return;
				}
			}
 }



function search(){
alert("查询")
}

</script>
<body >

	<c:if test="${empty pd}">
无产品信息
</c:if>
	<c:if test="${not empty pd}">
		<table border="1"  align="center" width="80%">
			<tr>
				<th align="left"><input type="checkbox"></th>
				<th style="display:none">菜品编号</th>
				<th >菜品名称</th>
				<th >菜品价格</th>
				<th >菜品余量</th>
				<th >菜品描述</th>
				<th >添加时间</th>
				<th >最近修改时间</th>
				<th >操作</th>
			</tr>
			<c:forEach items="${pd.dishs}" var="product">
              <tr> 
                <td width="2%" height="22"><input type="checkbox"></td>
				<td style="display:none">${product.id}</td>
				<td width="14%" >${product.name}</td>
				<td width="8%">${product.price}</td>
				<td width="8%">${product.pnum}</td>
				<td width="20%">${product.description}</td>
				<td width="17%">${product.createtime}</td>
				<td width="17%">${product.updatetime}</td>
				<td width="20%">
				<c:if test="${product.id ne null}">
				&nbsp;<a href="${pageContext.request.contextPath}/findDishById?id=${product.id}&dist=edit">编辑</a>&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="dele('${product.id}')">删除</a>&nbsp;&nbsp;&nbsp;
				<c:if test="${product.state==0}">
				<a href="javascript:void(0)"   onclick="updateState('${product.id}',1)">上架</a></c:if>
				<c:if test="${product.state==1}">
				<a href="javascript:void(0)"  onclick="updateState('${product.id}',0)">下架</a></c:if>
				</c:if>
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
					<c:if test="${pd.page==''}">
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
				<td colspan="9" align="right" >
				
					<select name="condtion" id="condtion" width="50" height="22" style="display:none">
				<option>--所有--</option>
				</select>
				<input type="text" name="name" id=name style="display:none">
				<button type="button" name="bt" id="bt" onclick="search()" >查询</button>
				<select name="searchbycond" height="22"  onchange="changeCondtion(this.value)">
						<option  width="60">--所有--</option>
						<option value="state">按状态查询</option>
						<option value="name">按名称查询</option>
						<option value="price">按价格查询</option>
						<option value="pnum">按余量查询</option>
						<option value="category">按分类查询</option>
				</select>
				
			
				</td>
			</tr>
			<tr>
				<td colspan="9" align="center"><my:page pd="${pd}" />
				</td>
			</tr>
		</table>

       &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/add.jsp">添加商品</a> 
        &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/backmanger.jsp">返回管理界面</a>
        &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}">返回主界面</a>  
	</c:if>




</body>

</html>