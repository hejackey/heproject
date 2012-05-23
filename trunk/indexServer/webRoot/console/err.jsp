<%@page contentType="text/html; charset=utf-8" language="java" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="images/default.css" type=text/css rel=stylesheet>
</HEAD>
<BODY oncontextmenu=self.event.returnValue=false>
<table width="100%" border="0" height="100%">
<tr align="center"> 
<td>
<form method="post" action="" name="forms[0]">
        <table border="1" bordercolorlight="000000" bordercolordark="FFFFFF" cellspacing="0" bgcolor="#C0EFFE">
          <tr>
<td>
              <table border="0" bgcolor="#00CCFF" cellspacing="0" cellpadding="2" width="350">
                <tr>
                  <td width="342"></td>
<td width="18">
<table border="1" bordercolorlight="666666" bordercolordark="FFFFFF" cellpadding="0" bgcolor="E0E0E0" cellspacing="0" width="18">
<tr>
<td width="16"><b><a href="javascript:history.go(-1)" onMouseOver="window.status='';return true" onMouseOut="window.status='';return true" title="关闭"><font color="000000">×</font></a></b></td>
</tr>
</table>
</td>
</tr>
</table>
<table border="0" width="350" cellpadding="4">
<tr> 
                  <td width="59" align="center" valign="top">&nbsp;</td>
<td width="269">

<%
  String Id=request.getParameter("id");
  if (Id==null)
     out.println("<P>非法操作!请稍后再试试!</P>");
  else
  {
  int Err_id=0;
	Err_id=Integer.parseInt(Id);
  if (Err_id==1)
     out.println("<P>用户名和密码不能为空,请重输!</P>");
  else if(Err_id==2)
	  out.println("<P>用户名或者密码错误,请重输!</P>");
  }
%>

</td>
</tr>
<tr>
<td colspan="2" align="center" valign="top">
<input type="button" name="ok" value="　确 定　" onclick=javascript:history.go(-1)>
</td>
</tr>
</table>
</td>
</tr>
</table>
</form>
</td>
</tr>
</table>

</body>
</html>

<html><script language="JavaScript"></script></html>

