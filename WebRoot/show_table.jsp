<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/listTableToUser',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
   			<th data-options="field:'imgurl',width:120,align:'center'">餐桌编号</th>
            <th data-options="field:'name',width:120,align:'center'">餐桌编号</th>
            <th data-options="field:'location',width:150 ,align:'center'">餐桌位置</th>
            <th data-options="field:'capacity',width:120 ,align:'center'">餐桌容量</th>
              <th data-options="field:'description',width:180 ,align:'center'">餐桌描述</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/table_edit.jsp'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增餐桌')").parent().click();
        }
    },{
        text:'编辑',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个商品才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个商品!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			alert(data)
        			//data.priceView = TAOTAO.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);
        			itemEditEditor.html(data.description);
        			$("#cc").combobox("setValue",data.category);

;
        		
        			
        			//加载商品规格
        			$.getJSON('',function(_data){
        	
        			});
        			
        			TAOTAO.init({
        				"pics" : data.imgurl,
        				"cid" : data.category,
        				fun:function(node){
        					TAOTAO.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("${pageContext.request.contextPath}/deleteTable",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','删除商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'下架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定下架商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"state":2};
                	$.post("${pageContext.request.contextPath}/changStateTable",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','下架商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'上架',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定上架商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"state":1};
                	$.post("${pageContext.request.contextPath}/changStateTable",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','上架商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>