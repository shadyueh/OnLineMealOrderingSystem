<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<title>添加商品</title>

<link rel="stylesheet" href="style.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>   
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
url:'${pageContext.request.contextPath}/addDish',
data:formData,
success:function(result){
$('#form')[0].reset();
alert("添加成功!");

},
error:function(){
alert("异常，请检查！");
window.close();
}
});
}
</script>



<div>
	<form id="form" onsubmit="return false" action="##" method="post">
		<table border="1" align="center">
			<tr>
				<td>菜肴饮品名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>菜肴饮品价格</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>选择类别类别</td>
				<td><select name="category">
						<option>--请选择类别-</option>
						<option value="菜肴">菜肴</option>
						<option value="饮品">饮品</option>
				</select></td>
			</tr>
			<tr>
				<td>菜肴饮品数量</td>
				<td><input type="text" name="pnum"></td>
			</tr>
			<tr>
				<td>菜肴饮品图片</td>
				<td><input type="file" name="f"></td>
			</tr>
			<tr>
				<td>菜肴饮品描述</td>
				<td><textarea rows="10" cols="20" name="description"></textarea>
				</td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" value="添加" onclick="add();">&nbsp;&nbsp;
					<input type="reset" value="取消">
				</td>

			</tr>
		</table>
	</form>
</div>