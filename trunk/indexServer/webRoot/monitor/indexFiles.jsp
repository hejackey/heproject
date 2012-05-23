<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="net.sourceforge.limo.LimoUtils" %>
<%@ page import="net.sourceforge.limo.IndexFile" %>
<%@ page import="java.util.List" %>

<jsp:include page="header.html"/>

<%

String location = request.getParameter("location");
session.setAttribute( "currentindexlocation", location );
List indexFiles = LimoUtils.getIndexFiles( location );
%>

<h2>Index File List</h2>

<p>The following are the index files for the Lucene Index stored at <code><%= location %></code>.</p>

<p>For information about the file types and formats, please refer to the 
<a href="http://jakarta.apache.org/lucene/docs/fileformats.html">Lucene Index File Formats 
documentation</a>.</p>

<p><b>Total Index Size:</b> <%= LimoUtils.getIndexSize( location ) %>.  

<table border="0" cellspacing="0" cellpadding="3">
<tr>
<th>File</th><th>Type</th><th>Size</th><th>Last Modified</th>
</tr>

<%

for ( int index = 0; index < indexFiles.size(); index++ ) {
	IndexFile indexFile = (IndexFile) indexFiles.get( index );
%>
<tr class="colorAlternate<%= (index % 2) %>">
<td><%= indexFile.getName() %></td>
<td><%= indexFile.getType() %></td>
<td><%= indexFile.getSize() %></td>
<td><%= indexFile.getLastModified() %></td>
</tr>
<%
}
%>
</table>
</body>
</html>