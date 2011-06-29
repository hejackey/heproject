<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/icon.css">
	<script type="text/javascript" src="${base}/web/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/jquery.easyui.min.js"></script>
	<script>
		$(function(){
			$('#test').datagrid({
				title:'helloWorld测试easyui datagrid控件',
				iconCls:'icon-save',
				width:650,
				height:350,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'getListToJson.do',
				sortName: 'code',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'code',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'id',field:'id',width:80,sortable:true}
				]],
				columns:[[
			        {title:'Base Information',colspan:2},
			       // {title:'Another Information',colspan:2},
					{title:'str',field:'str',width:130,align:'center', rowspan:2
					},
					{title:'param',field:'param',width:130,align:'center',rowspan:2}
				],[
					{field:'str',title:'Name',width:130},
					{field:'param',title:'Address',width:130,rowspan:2,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					}//,
					//{field:'str',title:'Another Name',width:140},
					//{field:'param',title:'Another Address',width:140,rowspan:2,sortable:true,
					//	sorter:function(a,b){
					//		return (a>b?1:-1);
					//	}
					//}
				]
				],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'Add',
					iconCls:'icon-add',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						alert('add')
					}
				},{
					id:'btncut',
					text:'Cut',
					iconCls:'icon-cut',
					handler:function(){
						$('#btnsave').linkbutton('enable');
						alert('cut')
					}
				},'-',{
					id:'btnsave',
					text:'Save',
					disabled:true,
					iconCls:'icon-save',
					handler:function(){
						$('#btnsave').linkbutton('disable');
						alert('save')
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			if (p){
				$(p).pagination({
					onBeforeRefresh:function(){
						alert('before refresh');
					},
					onRefresh:function(pageNumber,pageSize){  
				           alert(pageNumber);  
				           alert(pageSize);  
				    }/*,  
				    onChangePageSize:function(){  
				           alert('pagesize changed');  
				    },  
				    onSelectPage:function(pageNumber,pageSize){  
				           alert(pageNumber);  
				           alert(pageSize);  
				    } 
				    */
				});
			}
		});
		function resize(){
			$('#test').datagrid('resize', {
				width:700,
				height:400
			});
		}
		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			if (selected){
				alert(selected.str+":"+selected.param);
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#test').datagrid('clearSelections');
		}
		function selectRow(){
			$('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#test').datagrid('unselectRow',2);
		}
		function mergeCells(){
			$('#test').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
	</script>
</head>
<body>
	<h1>DataGrid</h1>
	<div style="margin-bottom:10px;">
		<a href="#" onclick="resize()">resize</a>
		<a href="#" onclick="getSelected()">getSelected</a>
		<a href="#" onclick="getSelections()">getSelections</a>
		<a href="#" onclick="clearSelections()">clearSelections</a>
		<a href="#" onclick="selectRow()">selectRow</a>
		<a href="#" onclick="selectRecord()">selectRecord</a>
		<a href="#" onclick="unselectRow()">unselectRow</a>
		<a href="#" onclick="mergeCells()">mergeCells</a>
	</div>
	
	<table id="test"></table>
	
</body>
</html>