<%@ page contentType="text/html; charset=gb2312"%>
<%
request.setCharacterEncoding("GBK");
%>
<html>
	<head>
		<title>索引操作执行结果</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
		<META HTTP-EQUIV="Expires" CONTENT="-1">
		<link rel="stylesheet" href="css/main.css" type="text/css">
	</head>
	<body bgcolor="#FFFFFF" text="#000000" leftmargin="8" topmargin="15"
		marginwidth="0" marginheight="0">
		<table width="100%" height="100%" border="1" cellpadding="0"
			cellspacing="0" bordercolor="#000000">
			<tr>
				<td align="center" bgcolor="#E8E8E8" valign="top">
					<br />
					<br />
					<table width="75%" border="1" align="center">
						<tr>
							<td>
								<%
									String res = (String) request.getParameter("MESSAGE");
									String[] errs = res.split(":");
									for (int i = 0; i < errs.length; i++) {
										String[] tmps = errs[i].split(" ");
										out.println("操作名称: " + tmps[0]
										+ "&nbsp&nbsp&nbsp 状态: <font color='blue'>" + tmps[1]
										+ "</font><br>");
									}
								%>
							</td>
						</tr>
						<tr>
							<td>
								<input name="submit" type="button" value=" 返 回 "
									onClick="window.history.back(-1);">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>