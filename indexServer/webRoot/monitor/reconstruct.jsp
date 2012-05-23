<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.lucene.index.IndexReader"%>
<%@ page import="org.apache.lucene.analysis.Analyzer"%>
<%@ page import="org.apache.lucene.search.Query"%>
<%@ page import="net.sourceforge.limo.LimoUtils" %>
<%@ page import="net.sourceforge.limo.ReconstructedDocument" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
<link rel="stylesheet" href="./limo.css" type="text/css">
</head>
<body>

<%

IndexReader reader = (IndexReader) session.getAttribute( "currentreader" );
String docId = request.getParameter( "docId" );
Query query = (Query) session.getAttribute( "currentQuery" );
Analyzer analyzer = (Analyzer) session.getAttribute( "currentAnalyzer" );

if ( reader != null && docId != null ) {

	ReconstructedDocument proxy = LimoUtils.reconstructDocument( Integer.parseInt( docId ), reader );
%>
<table border="1" cellpadding="3" cellspacing="0">
<%
	for ( Iterator fields = proxy.getFields().iterator(); fields.hasNext(); ) {
		String field = (String) fields.next();
		
		String reconstructed = proxy.isReconstructed( field ) ? "<span class=\"reconstructed\">(reconstructed)</span>" : "";
		String value = proxy.getValue( field );
		value = value.replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" );
		
		if ( query != null && analyzer != null ) {
			Query rewrite = query.rewrite( reader );
			value = LimoUtils.highlightTerms( rewrite, value, field, analyzer );
		} 
%>
<tr>
<td valign="top"><%= field %> <%= reconstructed %></td>
<td valign="top"><%= value %></td>
</tr>
<%
	}
%>
</table>
<%
} else {
%>

<h4>Select a document to reconstruct</h4>
<%
}
%>
</body>
</html>		
		