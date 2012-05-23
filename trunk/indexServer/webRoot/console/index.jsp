<%@ page contentType="text/html; charset=gb2312" %>
<%
   if ( session.getValue("UserName") == null ){
      response.sendRedirect("login.jsp");  
   }
%>
<html>
<head>
<title>索引文件管理</title>
</head>
<frameset rows="72,100%" cols="*" border="1" framespacing="0">
  <frame src="topFrame.html" name="topframes" scrolling="NO">
  <frameset id="bMainFrame" cols="160,11,*,0" frameborder="NO" border="0" framespacing="0" rows="*">
    <frame name="leftFrame" noresize src="left.jsp" bordercolor="FFFFFF">
    <frame src="roll.html" noresize name="middleFrame" scrolling="NO">
    <frame src="right.jsp" noresize name="rightframe">
  </frameset>
</frameset>
</html>