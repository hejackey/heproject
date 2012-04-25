function preAddUser(){
	document.form1.action="preAddUser.do";
	document.form1.submit();
}

function editUser(){
	if($("#loginName").val().isEmpty()||$("#loginName").val().getByteLength()>30){
		alert("登录名称不能为空，且长度不能超过30");
		return;
	}
	if($("#name").val().isEmpty()||$("#name").val().getByteLength()>60){
		alert("用户名不能为空，且长度不能超过60");
		return;
	}
	if($("#password").val().isEmpty()||$("#password").val().getByteLength()>32){
		alert("密码不能为空，且长度不能超过32");
		return;
	}
	
	if($("#roleid").val()==0){
		alert("请选择用户所属角色");
		return;
	}

	if($("#deptid").val()==0){
		alert("请选择用户所属部门");
		return;
	}
	var handtel=$("#handtel").val();
	if(!handtel.isEmpty() && (handtel.getByteLength() != 11 || !/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(handtel))){
		alert("请填写正确的手机号");
		return;
	}
	
	var email = $("#email").val();
	if(!email.isEmpty() && !/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)){
		alert("请填写正确的email格式");
		return;
	}
	
	if(window.confirm("确认修改部门信息")){
		document.form1.action="editUser.do";
		document.form1.submit();
	}
}

function saveUser(){
	if($("#loginName").val().isEmpty()||$("#loginName").val().getByteLength()>30){
		alert("登录名称不能为空，且长度不能超过30");
		return;
	}
	if($("#name").val().isEmpty()||$("#name").val().getByteLength()>60){
		alert("用户名不能为空，且长度不能超过60");
		return;
	}
	if($("#password").val().isEmpty()||$("#password").val().getByteLength()>32){
		alert("密码不能为空，且长度不能超过32");
		return;
	}
	
	if($("#roleid").val()==0){
		alert("请选择用户所属角色");
		return;
	}

	if($("#deptid").val()==0){
		alert("请选择用户所属部门");
		return;
	}
	var handtel=$("#handtel").val();
	if(!handtel.isEmpty() && (handtel.getByteLength() != 11 || !/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(handtel))){
		alert("请填写正确的手机号");
		return;
	}
	
	var email = $("#email").val();
	if(!email.isEmpty() && !/^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/.test(email)){
		alert("请填写正确的email格式");
		return;
	}
	
	document.form1.submit();
}
function preEditUser(id){
	
	document.form1.action="preEditUser.do?id="+id;
	document.form1.submit();
}

function queryTadmUser(){
	$("#pageNext").val(1);
	document.form1.submit();
}