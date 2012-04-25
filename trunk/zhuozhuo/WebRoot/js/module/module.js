function preAddModule(type){
	var parentid = $("#q_parentid").val();
	var id = $("#q_id").val();
	
	if(type==='1'){
		if(parentid.isEmpty() && id.isEmpty()){
			alert("根节点只允许添加下级部门");
			return;
		}
	}
	
	if(type==='2'){
		if(id.isEmpty())
			parentid="";		
		else
			parentid=id;
	}
	
	document.form1.action="preAddModule.do?parentid="+parentid;
	document.form1.submit();
}

function editModule(){
	if($("#moduleName").val().isEmpty()||$("#moduleName").val().getByteLength()>30){
		alert("模块名称不能为空，且长度不能超过30");
		return;
	}
	if($("#moduleCode").val().isEmpty()||$("#moduleCode").val().getByteLength()>30){
		alert("模块编号不能为空，且长度不能超过50");
		return;
	}
	
	if(window.confirm("确认修改部门信息")){
		document.form1.action="editModule.do";
		document.form1.submit();
	}
}

function saveModule(){
	if($("#moduleName").val().isEmpty()||$("#moduleName").val().getByteLength()>30){
		alert("模块名称不能为空，且长度不能超过30");
		return;
	}
	if($("#moduleCode").val().isEmpty()||$("#moduleCode").val().getByteLength()>30){
		alert("模块编号不能为空，且长度不能超过50");
		return;
	}
	
	document.form1.submit();
}
function preEditModule(id){
	
	document.form1.action="preEditModule.do?id="+id;
	document.form1.submit();
}

function queryTadmModule(){
	$("#pageNext").val(1);
	document.form1.submit();
}