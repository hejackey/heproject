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
				height:450,
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'getListToJson.do?str='+encodeURI($("#str").val())+"&param="+encodeURI($("#param").val()),
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'id',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'id',field:'id',width:80,sortable:true,
	                	formatter:function(value,rowData,rowIndex){
                            //function里面的三个参数代表当前字段值，当前行数据对象，行号（行号从0开始）
                            return "<a href='#' onclick='openWindow()'>"+value+"</a>";  
                       }
	                }
				]],
				columns:[[
			        {title:'Base Information',colspan:2},
					{title:'str1',field:'str',width:130,align:'center', rowspan:2
					},
					{title:'param1',field:'param',width:130,align:'center',rowspan:2}
				],[
					{title:'Name2',field:'str',width:130},
					{title:'Address2',field:'param',width:130,rowspan:2,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					}
				]
				],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						$('#dlg').dialog('open').dialog('setTitle','New User');  
				        $('#fm').form('clear');  
				        $("#update").css("display","none");
				        $("#save").css("display","");
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-cut',
					handler:function(){
						removeUser();
					}
				},
				{
					id:'btnUpdate',
					text:'修改',
					iconCls:'icon-cut',
					handler:function(){
						var selected = $('#test').datagrid('getSelected');  
    					
					    if (selected){  
					        $('#dlg').dialog('open').dialog('setTitle','Edit User');  
					        $('#fm').form('load',selected);  
					        
					        $("#update").css("display","");
				        	$("#save").css("display","none");
				   		} 
					}
				},
				'-',{
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
				    }
				});
			}
		});
		
		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			
			if (selected){
				alert(selected.id+":"+selected.str+":"+selected.param);
			}
		}
		
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
			}
			
		}
	</script>
</head>
<body>
	<h1>DataGrid</h1>
	<div style="margin-bottom:10px;">
		<a href="#" onclick="getSelected()">getSelected</a>
		<a href="#" onclick="getSelections()">getSelections</a>
	</div>
	<form name="form1" method="post" method="action" action="list.do">
	<div>
	str:<input type="text" name="str" id="str" value="${model.str?if_exists}">
	param:<input type="text" name="param" id="param" value="${model.param?if_exists}">
	<input type="submit" value="查询">
	</div>
	
	<table id="test"></table>
	</form>
	
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"  
            closed="true" buttons="#dlg-buttons">  
        <div class="ftitle">User Information</div>  
        <form id="fm" method="post">  
            <div class="fitem">  
                <label>id:</label>  
                <input name="id" class="easyui-validatebox" readonly>  
            </div>  
            <div class="fitem">  
                <label>str:</label>  
                <input name="str" class="easyui-validatebox" required="true">  
            </div>  
            <div class="fitem">  
                <label>param:</label>  
                <input name="param">  
            </div>  
            
        </form>  
    </div>  
    <div id="dlg-buttons">  
        <span id="save"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">保存</a></span>  
        <span id="update"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="updateUser()">修改</a></span>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">关闭</a>  
    </div> 
</body>
</html>
<script>
	function updateUser(){  
        $('#fm').form('submit',{  
            url: "updateHelloWorldAjax.do",  
            onSubmit: function(){  
                return $(this).form('validate');  
            },  
            success: function(result){  
                var result = eval('('+result+')');  
                if (result.success){  
                    $('#dlg').dialog('close');      // close the dialog  
                    $('#test').datagrid('reload');    // reload the user data  
                } else {  
                    $.messager.show({  
                        title: 'Error',  
                        msg: result.msg  
                    });  
                }  
            }  
        });  
    }  
    
    function saveUser(){
    	$('#fm').form('submit',{  
            url: "saveHelloWorldAjax.do",  
            onSubmit: function(){  
                return $(this).form('validate');  
            },  
            success: function(result){  
                var result = eval('('+result+')');  
                if (result.success){  
                    $('#dlg').dialog('close');      // close the dialog  
                    $('#test').datagrid('reload');    // reload the user data  
                } else {  
                    $.messager.show({  
                        title: 'Error',  
                        msg: result.msg  
                    });  
                }  
            }  
        }); 
    }
    
    function removeUser(){  
        //var row = $('#test').datagrid('getSelected');
        var ids=[];  
        var row = $('#test').datagrid('getSelections');
			
		for(var i=0;i<row.length;i++){
			ids.push(row[i].id);
		}
		
        if (row){  
            $.messager.confirm('Confirm','确认删除本条记录?',function(r){  
                if (r){  
                    $.post('delHelloWorldAjax.do',{id:ids.join(",")},function(result){  
                    	
                        if (result.success){  
                            $('#test').datagrid('reload');    // reload the user data  
                        } else {  
                            $.messager.show({   // show error message  
                                title: 'Error',  
                                msg: result.msg  
                            });  
                        }  
                    },'json');  
                }  
            });  
        }  
    }  
</script>
