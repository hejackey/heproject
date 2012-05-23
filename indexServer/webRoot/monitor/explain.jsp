<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.lucene.index.IndexReader"%>
<%@ page import="org.apache.lucene.search.IndexSearcher"%>
<%@ page import="org.apache.lucene.search.Query"%>
<%@ page import="org.apache.lucene.search.Explanation"%>

<%

IndexReader reader = (IndexReader) session.getAttribute( "currentreader" );
Query query = (Query) session.getAttribute( "currentQuery" );
String docId = request.getParameter( "docId" );

%>

<html>
<body>


<%

if ( reader != null && query != null && docId != null ) {

	IndexSearcher searcher = new IndexSearcher( reader );

	Explanation explanation = searcher.explain( query, Integer.parseInt( docId ) );
	
	searcher.close();
	
%>

<h4>Query explaination for document <%= docId %></h4>

<%= 	explanation.toHtml() %>

<%

} else {
%>
<h4>Select a search result to explain</h4>

<%
}
%>
</body>
</html>
