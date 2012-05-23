<%@ page contentType="text/html; charset=gb2312"%>
<html>
	<head>
		<title>上网本索引管理控台</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link rel="stylesheet" href="css/main.css" type="text/css">
		<script src="js/util.js" type="text/javascript"></script>
		<script src="js/string.js" type="text/javascript"></script>
		<script src="js/validate.js" type="text/javascript"></script>
		<script language="JavaScript" type="text/JavaScript">
	function check() {
		var choosecount = 0;
		for (n = 0; n < document.f1.infotype.length; n++) {
			if (document.f1.infotype[n].checked == true) {
				choosecount += 1;
				break;
			}
		}

		if (choosecount == 0) {
			alert('您没有选择索引操作，请选择!');
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
						<img SRC="images/loading.gif" width="300" height="200">
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
						action="<%=request.getContextPath()%>/NetComputerIndexServlet"
						method="POST" onSubmit="return check();">
						<table width="75%" width="75%" border="1" align="center"
							class="word12">
							<tr>
								<td>
									上网本索引管理
								</td>
							</tr>
							<tr>
								<td>
									<input name="b0" type="submit" value=" 确 定 ">
								</td>
							</tr>
							<tr>
								<td>
									生成上网本索引
									<input type="radio" name="infotype" value="CREATE_INDEX_PAGE">
								</td>
							</tr>
							<tr>
								<td>
									更新上网本索引
									<input type="radio" name="infotype" value="UPDATE_INDEX_PAGE" checked="true">
								</td>
							</tr>
							<tr>
								<td>
									优化上网本索引
									<input type="radio" name="infotype" value="OPTIMIZE_INDEX_PAGE">
								</td>
							</tr>
							<tr>
								<td>
									备份上网本索引
									<input type="radio" name="infotype" value="COPY_INDEX_PAGE">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>