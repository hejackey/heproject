function preAddDept(type){
	var parentid = $("#q_parentid").val();
	var id = $("#q_id").val();
	
	if(type==='1'){
		if(parentid.isEmpty()){
			alert("根节点只允许添加下级部门");
			return;
		}
	}
	
	if(type==='2'){
		if(id.isEmpty())
			parentid="10000";		
		else
			parentid=id;
	}
	
	//window.open("preAddDept.do?parentid="+parentid);
	//$("#parentid").val(parentid);
	document.form1.action="preAddDept.do?parentid="+parentid;
	document.form1.submit();
}

function editDept(){
	if($("#departmentname").val().isEmpty()||$("#departmentname").val().getByteLength()>50){
		alert("部门名称不能为空，且长度不能超过50");
		return;
	}
	if($("#departmentcode").val().isEmpty()||$("#departmentcode").val().getByteLength()>50){
		alert("部门编号不能为空，且长度不能超过50");
		return;
	}
	if($("#levels").val().isEmpty()){
		alert("层级不能为空");
		return;
	}
	
	if(window.confirm("确认修改部门信息")){
		document.form1.action="editDept.do";
		document.form1.submit();
	}
}

function saveDept(){
	if($("#departmentname").val().isEmpty()||$("#departmentname").val().getByteLength()>50){
		alert("部门名称不能为空，且长度不能超过50");
		return;
	}
	if($("#departmentcode").val().isEmpty()||$("#departmentcode").val().getByteLength()>50){
		alert("部门编号不能为空，且长度不能超过50");
		return;
	}
	if($("#levels").val().isEmpty()){
		alert("层级不能为空");
		return;
	}
	
	document.form1.submit();
}
function preEditDept(id){
	
	document.form1.action="preEditDept.do?id="+id;
	document.form1.submit();
}

function queryTadmDept(){
	$("#pageNext").val(1);
	document.form1.submit();
}