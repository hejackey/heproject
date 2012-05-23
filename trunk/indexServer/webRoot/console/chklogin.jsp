<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*" errorPage="" %>

<% 
 String User_Name=request.getParameter("username");
 String Pass_Word=request.getParameter("password");
  if ( User_Name.equals("") || Pass_Word.equals("") ) {
     response.sendRedirect("err.jsp?id=1");
  }
  else {
     if( !User_Name.equals("moobao") || !Pass_Word.equals("moobao") ) {
         response.sendRedirect("err.jsp?id=2");
     }
     else {
         session.putValue("UserName",User_Name);
         response.sendRedirect("index.jsp");
     }
  }
%>
