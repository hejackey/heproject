function preAddProductType(type){
	var parentId = $("#parentId").val();	
	var recordId = $("#recordId").val();	
	goodsTypeName= $("#goodsTypeName").val();	
	if(type==='1'){//增加同级商品类别		
		if(parentId.isEmpty()){
			alert("根节点只允许添加下级部门");
			return;
		}
	}	
	if(type==='2'){//增加下级商品类别	
		if(recordId.isEmpty())
			parentId="0";		
		else
			parentId=recordId;
	}
	document.forms[0].action="addorEditProductType.do?parentId="+parentId;	
	document.forms[0].submit();
}


/**
function editDept(){
	if(window.confirm("确认修改部门信息")){
		document.form.action="editDept.do";
		document.form.submit();
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
	
	document.form.submit();
}
function preEditDept(id){
	
	document.form.action="preEditDept.do?id="+id;
	document.form.submit();
}

function queryTadmDept(){
	$("#pageNext").val(1);
	document.form.submit();
}
**/