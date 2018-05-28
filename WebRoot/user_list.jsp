<%@page import="net.sf.json.JSON"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="用户列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/listUser',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'username',width:150 ,align:'center'">用户名</th>
            <th data-options="field:'nickname',width:120 ,align:'center'">用户昵称</th>
            <th data-options="field:'email',width:162,align:'center'">用户邮箱</th>
            <th data-options="field:'role',width:175,align:'center',formatter:TAOTAO.formatUserStatus">用户权限</th>
             <th data-options="field:'state',width:175,align:'center',formatter:TAOTAO.formatAccountStatus">账号状态</th>
             <th data-options="field:'tel',width:175,align:'center'">手机号码</th>
             <th data-options="field:'registtime',width:175,align:'center',formatter:TAOTAO.formatDateTime">注册时间</th>

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
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    };
    
    var toolbar = [{
        text:'设为管理员',
        iconCls:'icon-add',
        handler:function(){
        	  	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定将此用户设为管理员吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"role":"admin"};
                	$.post("${pageContext.request.contextPath}/upUserGrant",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','设为管理员成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'撤销管理员',
        iconCls:'icon-edit',
        handler:function(){
        	        	  	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定撤回此用户权限吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"role":'user'};
                	$.post("${pageContext.request.contextPath}/upUserGrant",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','撤销权限成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'封禁',
        iconCls:'icon-cancel',
        handler:function(){
        	        	  	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定将此用户封禁吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"state":1};
                	$.post("${pageContext.request.contextPath}/banUser",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','封禁用户成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-',{
        text:'解封',
        iconCls:'icon-remove',
        handler:function(){
        	     	        	  	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定将此用户解封吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids,"state":0};
                	$.post("${pageContext.request.contextPath}/banUser",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','解封用户成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },'-'];
</script>