<%@ page contentType="text/html; charset=gb2312" %>
<html>
<head>
<title>CMS后台管理系统</title>
<link rel="stylesheet" href="css/main.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="8" topmargin="15" marginwidth="0" marginheight="0">
<table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000" >
  <tr>
    <td align="center" bgcolor="#E8E8E8">
      <table width="50%" border="0" cellpadding="0" cellspacing="0" bordercolor="#000000" class="word12">
        <tr>
          <td height="30" colspan="2" align="center" nowrap><strong>欢迎您进入管理系统！</strong></td>
        </tr>

        <tr>
          <td height="30" colspan="2" align="center" nowrap><strong>您使用的计算机是：<%=request.getRemoteHost()%></strong></td>
        </tr>
        <tr>
          <td height="30" colspan="2" align="center" nowrap><hr width="100%" size="1"></td>
        </tr>
        <tr>
          <td width="1%" align="center" valign="top" nowrap><strong><font color="#FF3300">特别提示：</font></strong></td>
          <td><p>为保护系统安全，如果您准备离开一段时间，那么请注销您的登录或者关闭浏览器。<br></p>
            </td>
        </tr>
        <tr>
          <td height="30" colspan="2" align="center" nowrap><hr width="100%" size="1"></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>