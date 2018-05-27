<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemeEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
	        <tr>
	            <td>餐桌类目:</td>
		       <td> <select id="cc" class="easyui-combobox" name="category" style="width:200px;">   
    		<option value="aa">餐桌等级</option>   
   			 <option>包间</option>   
    		<option>大堂</option>   
    		<option>豪华套件</option>   
   			</td>
	        </tr>
	        	        <tr>
	            <td>餐桌编号:</td>
	              <td><input class="easyui-numberbox" type="text" name="name" data-options="min:1,max:99999999,precision:0,required:true,missingMessage:'该输入项为必填选项，请输入整数'" />

	        </tr>
	        <tr>
	            <td>餐桌容量:</td>
	            <td><input class="easyui-numberbox" type="text" name="capacity" data-options="min:1,max:30,precision:0,required:true,missingMessage:'该输入项为必填选项，请输入整数'" /></td>
	        </tr>
	        <tr>
	            <td>餐桌位置:</td>
	         
	            	 <td><input class="easyui-textbox" type="text" name="location" data-options="required:true,missingMessage:'该输入项为必填选项，如2楼3号包间，大厅4号桌'" style="width: 280px;"></input></td>
	            </td>
	        </tr>
	         <tr>
	            <td>餐桌实拍:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	                 <input type="hidden" name="imgurl"/>
	            </td>
	        </tr>
	        <tr>
	            <td>餐桌描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="description"></textarea>
	            </td>
	        </tr>
	       
	        <tr class="params hide">
	        	<td>商品规格:</td>
	        	<td>
	        		
	        	</td>
	        </tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	</div>

</div>
<script>
	var itemEditEditor ;
	$(function(){
		//实例化编辑器
		itemEditEditor = TAOTAO.createEditor("#itemeEditForm [name=description]");
	});
	
	function submitForm(){
		if(!$('#itemeEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		itemEditEditor.sync();
		
		var paramJson = [];
		$("#itemeEditForm .params li").each(function(i,e){
});

		

		
		$.post("${pageContext.request.contextPath}/editTable",$("#itemeEditForm").serialize(), function(data){
			if(data == '{"status":200}'){
				$.messager.alert('提示','修改餐桌信息成功!','info',function(){
					$("#itemEditWindow").window('close');
					$("#itemList").datagrid("reload");
				});
			}
		});
	}
	
</script>
