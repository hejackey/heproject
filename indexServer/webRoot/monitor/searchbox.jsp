<%@page contentType="text/html;charset=utf-8"%>

<%@ page import="org.apache.lucene.index.IndexReader" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.apache.lucene.analysis.*" %>
<%@ page import="org.apache.lucene.analysis.de.GermanAnalyzer" %>
<%@ page import="org.apache.lucene.analysis.ru.RussianAnalyzer" %>
<%@ page import="org.apache.lucene.analysis.standard.StandardAnalyzer" %>
<%@ page import="jeasy.analysis.MMAnalyzer" %>
<%

IndexReader reader = (IndexReader)session.getAttribute("currentreader");
String location = request.getParameter( "location" );
String queryString = request.getParameter( "query" );

%>

<form action="searchFrameset.jsp" method="get" target="_top">
    <input type="hidden" name="location" value="<%= location %>">
    <!-- TODO: query needs to be escaped for form safety -->
    <input type="text" size="40" value="<%= queryString == null ? "" : new String( queryString.getBytes("iso-8859-1"),"utf-8" ) %>" name="query"> 
    <input type="submit" value="Search Index"><br/>
    
    Default field: <select name="default_field">
<%
for ( Iterator fields = reader.getFieldNames(IndexReader.FieldOption.INDEXED).iterator(); fields.hasNext(); ) {
    String field = (String) fields.next();
%>
    <option <%= field.equals( request.getParameter( "default_field" ) ) ? "selected=\"true\"" : "" %>><%= field %></option>
<%
}
%>
    </select>
    
    Analyzer: 
    <select name="analyzer">
<%

Class[] defaultAnalyzers = new Class[] {
    GermanAnalyzer.class,
    RussianAnalyzer.class,
    SimpleAnalyzer.class,
    StandardAnalyzer.class,
    StopAnalyzer.class,
    WhitespaceAnalyzer.class,
	MMAnalyzer.class
};

String selectedAnalyzer = request.getParameter( "analyzer" );
if ( selectedAnalyzer == null || "".equals( selectedAnalyzer ) ) {
	selectedAnalyzer = StandardAnalyzer.class.getName();
}

for ( int index = 0; index < defaultAnalyzers.length; index++ ) {
    Class analyzer = defaultAnalyzers[ index ];
%>
    <option <%= analyzer.getName().equals( selectedAnalyzer ) ? "selected=\"true\"" : "" %>><%= analyzer.getName() %></option>
<%
}
%>
    </select>

</form>

<HR>