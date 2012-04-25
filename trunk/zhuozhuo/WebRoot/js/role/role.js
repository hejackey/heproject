function preAddRole(){
	document.form1.action="preAddRole.do";
	document.form1.submit();
}

function editRole(){
	if($("#roleName").val().isEmpty()||$("#roleName").val().getByteLength()>60){
		alert("角色名称不能为空，且长度不能超过60");
		return;
	}
	
	if(window.confirm("确认修改角色信息")){
		document.form1.action="editRole.do";
		document.form1.submit();
	}
}

function saveRole(){
	if($("#roleName").val().isEmpty()||$("#roleName").val().getByteLength()>60){
		alert("角色名称不能为空，且长度不能超过60");
		return;
	}
	
	document.form1.submit();
}
function preEditRole(id){
	
	document.form1.action="preEditRole.do?id="+id;
	document.form1.submit();
}

function queryTadmRole(){
	$("#pageNext").val(1);
	document.form1.submit();
}

//全选复选框
function selectRolePril(flag,id,len)
{
	
	for(var i = 0; i <len;i++)
	{
		if(!$("#"+id+"_"+i).attr("disabled"))
		{
			$("#"+id+"_"+i).attr("checked",flag);
		}
	}	
}

//全选复选框
function selectRoleAll(flag,id,title)
{
	var cks = document.getElementsByName(id);
	
	if(cks == null)
	{
		return;
	}
	
	for(var i = 0; i < cks.length;i++)
	{
		if(!cks[i].disabled && cks[i].title==title)
		{
			cks[i].checked = flag;
		}
	}	
}