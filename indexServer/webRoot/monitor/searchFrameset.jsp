<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.net.URLEncoder" %>

<html>
<head>
<title>LIMO: Search Results</title>
</head>

<%

String location = new String( request.getParameter("location").getBytes("iso-8859-1"),"utf-8" );
String queryString = new String( request.getParameter( "query" ).getBytes("iso-8859-1"),"utf-8" );
String defaultField = new String( request.getParameter( "default_field" ).getBytes("iso-8859-1"),"utf-8" );
String analyzerClassName =new String( request.getParameter( "analyzer" ).getBytes("iso-8859-1"),"utf-8" );
//out.println( queryString );
%>

<frameset rows="80%,20%">
	<frame src="search.jsp?location=<%= location %>&query=<%= queryString %>&default_field=<%= defaultField %>&analyzer=<%= analyzerClassName %>">

	<frameset cols="50%,50%">
		<frame src="explain.jsp" name="explainFrame">
		<frame src="reconstruct.jsp?location=<%= location %>" name="docFrame">
	</frameset>
</frameset>

</html>