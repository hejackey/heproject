<%@ page contentType="text/html; charset=utf-8"%>
<html>
	<head>
		<title>更新某一产品的Document</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" href="css/main.css" type="text/css">
		<script src="js/util.js" type="text/javascript"></script>
		<script src="js/string.js" type="text/javascript"></script>
		<script src="js/validate.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/JavaScript">
	function check() {
        var Value = document.f1.id.value;
        var type = document.getElementById('type').value;
        if(type == null || type.length == 0){
            alert('类型不能为空!');
            return false;
        }
        if(!Value.isValidPositiveInteger()){
        	 alert('id 字段必需为数字');
             return false;
        }
        
		document.f1.b0.disabled = true;
		showdiv();
	}
	
	function showdiv() {
		document.all('_divLoading').style.display = '';
	}
</script>
	</head>
	<body bgcolor="#FFFFFF" text="#000000" leftmargin="8" topmargin="15"
		marginwidth="0" marginheight="0">
		<br>
		<div id="_divLoading" style="display: none">
			<table WIDTH="300" BORDER="0" ALIGN="center" CELLPADDING="0"
				CELLSPACING="0">
				<tr>
					<td>
						<img src="images/loading.gif" width="300" height="200">
					</td>
				<tr>
			</table>
		</div>
		<table width="100%" height="100%" border="1" cellpadding="0"
			cellspacing="0" bordercolor="#000000">
			<tr>
				<td align="center" bgcolor="#E8E8E8" valign="top">
					<br />
					<br />
					<form name="f1"
						action="<%=request.getContextPath()%>/UpdateOneDocument"
						method="POST" onSubmit="return check();">
						<table width="75%" border="1" align="center">
							<tr>
								<td>
									更新单个产品的Document
								</td>
							</tr>
							<tr>
								<td>
									<input name="b0" type="submit" value=" 确 定 ">
									<input type="hidden" name="busiType" size="30" value="3" />
								</td>
							</tr>
							<tr>
								<td>更新类别:
								   <select style="width:80; color:#555555; height:20" name="type" size="1">
                                     <option value=""></option>
                                     <option value="phone">手机</option>
                                     <option value="peiJian">配件</option>
                                     <option value="peiJianBase">配件基本</option>
                                     <option value="netBook">上网本</option>
                                     <option value="resource">资讯</option>
                                     <option value="entertainment">娱乐中心</option>
                                   </select> &nbsp;&nbsp;
									ID:
									<input type="text" maxlength="10" size="10" name="id"
										id="id">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>