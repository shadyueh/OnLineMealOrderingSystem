<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>

<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/taotao.css" />
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body >
<table class="easyui-datagrid" id="itemList" title="餐桌列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath}/listTableToUser',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
   			
            <th data-options="field:'name',width:120,align:'center'">餐桌编号</th>
            <th data-options="field:'location',width:150 ,align:'center'">餐桌位置</th>
            <th data-options="field:'capacity',width:120 ,align:'center'">餐桌容量</th>
              <th data-options="field:'description',width:180 ,align:'center'">餐桌描述</th>
        </tr>
    </thead>

</table>
<div>
    <a >起始時間:</a> 
<input id="dt" class="easyui-datetimebox" name="birthday1"     
        data-options="required:true,showSeconds:true" value="3/4/2010 2/3" style="width:150px">   
<a>結束時間:</a> 

<input id="db" class="easyui-datetimebox" name="birthday2"     
        data-options="required:true,showSeconds:true" value="3/4/2010 2/3" style="width:150px">  
    <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"></a> 
</div>
</body>
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
    
    
    
      $(function(){    
    $('#btn').bind('click', function(){    
       var starttime =$('#dt').datetimebox('getValue'); 
        var endtime =$('#db').datetimebox('getValue'); 
        alert(starttime);
         alert(endtime);
         
         	var ids = getSelectionsIds();
         	if(starttime == "2018-05-09 02:03"&&endtime=="2018-05-09 02:03"){
        		$.messager.alert('提示','未选定時間!');
        		return ;
        	}
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	
        	
        	$.messager.confirm('确认','确定选定餐桌吗？',function(r){
        
        	    if (r){
        	    	var params = {"ids":ids,"stime":starttime,"etime":endtime};
        	    	
        	    		$.post("${pageContext.request.contextPath}/sureTable",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','选定成功!',undefined,function(){
            				alert("ok")
            					location.href="${pageContext.request.contextPath}/showOrder";
            				});
            			}
            		});
        	    }
      
    });    
});
});
    
   
   
   
   
   
   
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增餐桌')").parent().click();
        }
    },{
        text:'选定',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定选定餐桌吗？',function(r){
        
        	    if (r){
        	    $("#itemEditWindow").window('open');

        	    
        	    	var params = {"ids":ids,"stime":dt};
    
                	$.post("${pageContext.request.contextPath}/sureTable",params, function(data){
            			if(data == "ok"){
            				$.messager.alert('提示','选定成功!',undefined,function(){
            				alert("ok")
            					//location.href=${pageContext.request.contextPath}/showOrder;
            				});
            			}
            		});
        	    }
        	});
        }
    },'-'
    ];
</script>


</html>