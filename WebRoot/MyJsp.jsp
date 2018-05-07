<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd

"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<title>jQuery实现购物车多物品数量的加减+总价计算</title> 
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script> 

</script> 
</head> 
<body> 




<div class="art-content-layout overview-table">
	<div class="art-content-layout-row">
		<c:forEach items="${ps}" var="p" varStatus="vs">
			<div class="art-layout-cell">
					<div class="overview-table-inner">
						<h4>${p.name }</h4>
						<img src="${pageContext.request.contextPath}${p.imgurl}" width="55px" height="55px" alt="an image" class="image" onclick="findDishById('${p.id}&dist=list')"/>
						<p>价格: ￥${p.price }</p>
						<p><a href="${pageContext.request.contextPath}/findDishById?id=${p.id}&dist=list">速速抢购</a></p>
					</div>
			</div>										
			<c:if test="${vs.count%5==0}">
		</div> <!-- 判断当前已经有5个商品了，这 一行结束，在重新开启一行 -->
				<div class="art-content-layout-row">
			</c:if>		
		</c:forEach>
													<!-- end cell -->
	</div>
												
												
</div> 
</body> 
</html>
