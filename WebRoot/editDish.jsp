<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改菜品</title>
<meta http-equiv="Cache-Control" content="no-cache"/>
<link rel="stylesheet" href="style.css" type="text/css" media="screen" /> 
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
</head>

<body>

<div>
	<form id="form" onsubmit="return false" action="##" method="post">
		<table border="1" align="center">
			<tr>
			<td rowspan="10"  align="right"><img id="image"
			width="280" height="400" alt="缓存失效！"
				src="${pageContext.request.contextPath}${p.imgurl}"/>
			</td>
			</tr>
			<tr>
			<td style="display:none"><input type="text" id="imgurl" name="imgurl" value="${p.imgurl}"/></td>
			<td>菜肴饮品名称</td>
				<td><input type="text" name="name" value="${p.name}"></td>
				<td style="display:none"><input type="text" name="id" value="${p.id}"/></td>
		</tr>
			<tr>
				<td>菜肴饮品价格</td>
				<td><input type="text" name="price" value="${p.price}"></td>
			</tr>
			<tr>
				<td>选择类别类别</td>
				<td><select name="category" id="category" value="${p.category}">
						<option value="未知">--请选择类别-</option>
						<option value="酒水" ${p.category=="酒水"?'selected':' '}>酒水</option>
						<option value="饮料" ${p.category=="饮料"?'selected':' '}>饮料</option>
						<option value="荤菜" ${p.category=="荤菜"?'selected':' '}>荤菜</option>
						<option value="素菜" ${p.category=="素菜"?'selected':' '}>素菜</option>
						<option value="海鲜" ${p.category=="海鲜"?'selected':' '}>海鲜</option>
						<option value="野味" ${p.category=="野味"?'selected':' '}>野味</option>
						<option value="其它" ${p.category=="其它"?'selected':' '}>其它</option>
				</select></td>
			</tr>
			<tr>
				<td>菜肴饮品数量</td>
				<td><input type="text" name="pnum" value="${p.pnum}"></td>
			</tr>
	
			<tr>
				<td>菜肴饮品图片</td>
				<td><input type="file" name="f" onchange="showPic(this)"></td>
			</tr>
			<tr>
				<td>菜肴饮品描述</td>
				<td><textarea rows="10" cols="35" name="description" >${p.description}</textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" value="修改" onclick="add();">&nbsp;&nbsp;
					<input type="reset" value="取消">
				</td>

			</tr>
		</table>
	</form>
</div>

</body>
<script type="text/javascript">




function add(){
alert("开始发请求");
var formData=new FormData($("#form")[0]);
$.ajax({
type:"POST",
async:false,
contentType:false,
processData:false,
cache:false,
dataType:'text',
url:'${pageContext.request.contextPath}/editDish',
data:formData,
success:function(result){
$('#form')[0].reset();
alert("修改成功!");
location.href=document.referrer;
},
error:function(){
alert("异常，请检查！");
window.close();
}
});
}


function showPic(source){
var file=source.files[0];
if(window.FileReader){
var fr=new FileReader();
console.log(fr);
var image = document.getElementById('image');
var imageurl = document.getElementById('imgurl');
imageurl.value="null"
          fr.onloadend = function(e) {
            image.src = e.target.result;
          };
          fr.readAsDataURL(file);
          image.style.display='block';
          alert("改图片成功！")
}
}

window.onload=function(){
var image = document.getElementById('image');

 image.src = "${pageContext.request.contextPath}${p.imgurl}";
  //image.style.display='block';
}


</script>

</html>
