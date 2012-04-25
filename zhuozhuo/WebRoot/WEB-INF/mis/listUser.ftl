<#import "../spring.ftl" as spring/>
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript">
		function isDel()		{
			if(confirm("要删除这个买家用户吗？")){
				return true;
			}else{
				return false;
			}
		}
</script>
  </head>
  <body>
    <center>
  		<font size="4"><b>买家用户列表</b></font>
	</center>
	<table align="center" bgcolor="#008800" border="0" cellspacing="2" cellpadding="3">
		<tr bgcolor="#CCCCCC">
			<td><b>USER NAME</b></td>
			<td><b>MAIL ADDRESS</b></td>
			<td><b>TELEPHONE</b></td>
	  		<td><a href="preAddUser.do">增加买家</a></td>
		</tr>
	<#list userList as user>
		<tr bgcolor="#FFFF88">
			<td>${user.name?default("")}</td>
	  		<td>${user.mail?default("")}</td>
    		<td>${user.telephone?default("")}</td>
    		<td><a href="preEditUser.do?id=${user.id}">修改</a> <a href="deleteUser.do?id=${user.id}" onclick="isDel()">删除</a></td>
		</tr>
	</#list>
	</table>
  </body>
</html>				 