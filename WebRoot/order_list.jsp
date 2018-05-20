<%@page import="net.sf.json.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="订单列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/listOrder',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'username',width:150 ,align:'center'">下单用户</th>
            <th data-options="field:'ordertime',width:120 ,align:'center'">下单时间</th>
            <th data-options="field:'paystate',width:60,align:'center',formatter:TAOTAO.formatItemStatus">订单状态</th>
            <th data-options="field:'money',width:170,align:'center',formatter:TAOTAO.formatDateTime">订单金额</th>
             <th data-options="field:'receiverinfo',width:170,align:'center',formatter:TAOTAO.formatDateTime">收获地址</th>

        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'${pageContext.request.contextPath}/order_info.jsp'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].orderid);
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
        text:'查看详情',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	alert(ids);
        	if(ids.length == 0){
        		$.messager.alert('提示','必须选择一个订单才能编辑!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('提示','只能选择一个订单!');
        		return ;
        	}
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        	
        			//回显数据
						$.ajax({
						type:"POST",
						async:false,
						headers:{"Content-Type":"application/x-www-form-urlencoded"},
						processData:false,
						cache:false,
						//dataType:'json',
						url:'${pageContext.request.contextPath}/findOrderById',
						data:"id="+ids,
						success:function(result){
						alert(result.dishinfo_map.dish.id);
						var data =JSON.stringify(result);

					alert(data);
						},
						error:function(){
						alert("异常，请检查！");
						window.close();
						
						
						}
						});


        		
 

        		}
        	}).window("open");
        	
        	$("#itemEditWindow").panel('refresh');
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