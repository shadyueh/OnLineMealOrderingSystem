<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
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
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	var itemAddEditor ;
	//页面初始化完毕后执行此方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = TAOTAO.createEditor("#itemAddForm [name=description]");
		//初始化类目选择和图片上传器
		TAOTAO.init({fun:function(node){
			//根据商品的分类id取商品 的规格模板，生成规格信息。第四天内容。
			//TAOTAO.changeItemParam(node, "itemAddForm");
		}});
	});
	//提交表单
	function submitForm(){
		//有效性验证
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//取商品价格，单位为“分”
		//$("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
		//同步文本框中的商品描述
		itemAddEditor.sync();
		//取商品的规格
		/*
		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//把json对象转换成字符串
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson);
		*/
		//ajax的post方式提交表单
		//$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
		alert($("#itemAddForm").serialize());
		$.post("${pageContext.request.contextPath}/addTable",$("#itemAddForm").serialize(), function(data){
		
			if(data == '{"status":200}'){
				$.messager.alert('提示','新增商品成功!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
