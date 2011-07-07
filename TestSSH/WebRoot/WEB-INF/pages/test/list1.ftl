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
    <table id="dg" title="My Users" class="easyui-datagrid" style="width:550px;height:250px"  
            url="getListToJson.do"  
            toolbar="#toolbar"  
            rownumbers="true" fitColumns="true" singleSelect="true">  
        <thead>  
            <tr>  
                <th field="id" width="50">First Name</th>  
                <th field="str" width="50">Last Name</th>  
                <th field="param" width="50">Phone</th>  
                <th field="id" width="50">Email</th>  
            </tr>  
        </thead>  
    </table>  
    <div id="toolbar">  
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>  
    </div>  
    
    <div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"  
            closed="true" buttons="#dlg-buttons">  
        <div class="ftitle">User Information</div>  
        <form id="fm" method="post">  
            <div class="fitem">  
                <label>First Name:</label>  
                <input name="id" class="easyui-validatebox" required="true">  
            </div>  
            <div class="fitem">  
                <label>Last Name:</label>  
                <input name="str" class="easyui-validatebox" required="true">  
            </div>  
            <div class="fitem">  
                <label>Phone:</label>  
                <input name="param">  
            </div>  
            
        </form>  
    </div>  
    <div id="dlg-buttons">  
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>  
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>  
    </div>  
    
    <script>
    function newUser(){  
        $('#dlg').dialog('open').dialog('setTitle','New User');  
        $('#fm').form('clear');  
        url = 'add.do';  
    }  
    
    function editUser(){
    	var row = $('#dg').datagrid('getSelected');  
    	
	    if (row){  
	        $('#dlg').dialog('open').dialog('setTitle','Edit User');  
	        $('#fm').form('load',row);  
	        url = 'helloWorld.do?id='+row.id;  
   		}  
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
    </script>
</body>
</html>