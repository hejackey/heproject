<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/icon.css">
	<script type="text/javascript" src="${base}/web/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/jquery.easyui.min.js"></script>
</head>
<body>	    
    <table id="dg" title="My Users" class="easyui-datagrid" style="width:650px;height:450px"  
            url="getListToJson.do"  
            toolbar="#toolbar"  
            rownumbers="true" fitColumns="true" singleSelect="true">  
        <thead>  
            <tr>  
                <th field="id" width="50">id</th>  
                <th field="str" width="50">str</th>  
                <th field="param" width="50">param</th>  
                <th field="id" width="50">id</th>  
            </tr>  
        </thead>  
    </table>  
    <div id="toolbar">  
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove</a>  
    </div>  
    
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
    
    <script>
    function newUser(){  
        $('#dlg').dialog('open').dialog('setTitle','New User');  
        $('#fm').form('clear');  
        $("#update").css("display","none");
        $("#save").css("display","");
        //url = 'add.do';  
    }  
    
    function editUser(){
    	var row = $('#dg').datagrid('getSelected');  
    	
	    if (row){  
	        $('#dlg').dialog('open').dialog('setTitle','Edit User');  
	        $('#fm').form('load',row);  
	        
	        $("#update").css("display","");
        	$("#save").css("display","none");
	        //url = 'helloWorld.do?id='+row.id;  
   		}  
    }
    
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
                    $('#dg').datagrid('reload');    // reload the user data  
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
                    $('#dg').datagrid('reload');    // reload the user data  
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
        var row = $('#dg').datagrid('getSelected');  
        if (row){  
            $.messager.confirm('Confirm','确认删除本条记录?',function(r){  
                if (r){  
                    $.post('delHelloWorldAjax.do',{id:row.id},function(result){  
                    	
                        if (result.success){  
                            $('#dg').datagrid('reload');    // reload the user data  
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
</body>
</html>