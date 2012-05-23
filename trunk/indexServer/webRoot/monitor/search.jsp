<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="net.sourceforge.limo.LimoUtils" %>
<%@ page import="org.apache.lucene.index.IndexReader"%>
<%@ page import="org.apache.lucene.search.Hits"%>
<%@ page import="org.apache.lucene.document.Document"%>
<%@ page import="org.apache.lucene.analysis.Analyzer"%>
<%@ page import="org.apache.lucene.search.Query"%>
<%@ page import="org.apache.lucene.search.BooleanQuery"%>
<%@ page import="org.apache.lucene.search.IndexSearcher"%>
<%@ page import="org.apache.lucene.queryParser.QueryParser"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%-- performs a search given the "query" parameter and displays the results --%>

<jsp:include page="header.html"/>

<%

String location = request.getParameter("location");
IndexReader reader = (IndexReader) session.getAttribute( "currentreader" );
Long lastmodif = (Long)session.getAttribute( "lastmodif" );

long modified = IndexReader.lastModified( location );
boolean haschangedsince = ( ( lastmodif != null ) && 
                            ( lastmodif.longValue() != modified ) );

if ( haschangedsince ) {
    if ( reader != null ) {
        reader.close();
    }
    
    reader = null;
}

if ( reader == null ) {
    reader = IndexReader.open( location );
}
    
session.setAttribute( "currentindexlocation", location );
session.setAttribute( "currentreader", reader );
session.setAttribute( "lastmodif", new Long( modified ) );

String queryString = new String( request.getParameter( "query" ).getBytes("iso-8859-1"),"utf-8" );
String defaultField = request.getParameter( "default_field" );
String analyzerClassName = request.getParameter( "analyzer" );

if( queryString == null || queryString.equals("") ) {
%>
   <script>
     history.go(-1);
   </script>
<%
}

int startingdoc = 0;
try {startingdoc = Integer.parseInt(request.getParameter( "start" ));}
catch (Exception e){}

Collection fieldNames = reader.getFieldNames(IndexReader.FieldOption.ALL); 

Analyzer analyzer = null;

try {
    Class clazz = Class.forName( analyzerClassName );
    analyzer = (Analyzer) clazz.newInstance();
} catch ( Exception e ) {
%>
<pre>
<%
	e.printStackTrace( new PrintWriter( out ) );
%>
</pre>
<%
    return;
}

try {
    QueryParser parser = new QueryParser(defaultField, analyzer);
    Query query = parser.parse(queryString);
    
    session.setAttribute( "currentQuery", query );
    session.setAttribute( "currentAnalyzer", analyzer );
    
    Query expanded = query.rewrite( reader );
   
    IndexSearcher searcher = new IndexSearcher( reader ); 
  
  	long start = System.currentTimeMillis();
          
    Hits hits = searcher.search( query );
     
    long end = System.currentTimeMillis();
 	
 	long elapsed = end - start;
 	
 	searcher.close();
 	
 	DateFormat format = new SimpleDateFormat( "mm:ss:SSS" );
 	String elapsedString = format.format( new Date( elapsed ) );
 
    int numFields = reader.getFieldNames( IndexReader.FieldOption.INDEXED ).size();
    int numDocs = reader.numDocs();
    String memory = LimoUtils.bytesToHumanReadable( LimoUtils.estimateMemory( expanded, numFields, numDocs ) );

%>

<table width="90%" border="0" align="center">
  <tr> 
    <tr>
    		<td align="center"><jsp:include page="searchbox.jsp"/></td>
    </tr>
</table>

<p>You searched for <%= query %>. 

<%
	// Actually, I remember reading on the Lucene mailinglist that not all queries
	// implement equals(), so this is of dubious use until they do.
	if ( !expanded.equals( query ) ) {
%>
<p>It expanded to a query with <%= LimoUtils.getTermCount( expanded ) %> terms: <%= expanded %>.
<%
	}
%>

<p>Estimated memory consumed by this query: <%= memory %>. 
Query took: <%= elapsedString %> (<%= elapsed %> ms).

<p>Query returned <%= hits.length() %> results.



<%
    int docsinpage = 10;
    
    /** displaying the number of pages **/
    int totalpages =  hits.length()/docsinpage;
    if ((hits.length()%docsinpage)!=0) totalpages++;
    int pagenum = (startingdoc/docsinpage)+1;
    /** keep the last call and modifies only the start parameter **/
    String url = "search.jsp?";
	
	java.util.Enumeration nomsdeparams = request.getParameterNames();
    boolean toAddNotesManuelles = false;

	boolean started = false;

    while (nomsdeparams.hasMoreElements())
    {
		String cle = (String)nomsdeparams.nextElement();
        String value = request.getParameter(cle);
		
		if (cle.equals("start")) continue;
		
		if (started) url+="&";
		url += cle + "="+URLEncoder.encode(value,"ISO-8859-1");
        started = true;        
	}
    %>

<p>
<table border="0" cellpadding="5" cellspacing="0">


<tr> 
		    <td width="15%" valign="bottom"> 
		      <% if (pagenum>1)  { 
 				String urlmoins = url+"&start="+((pagenum-2)*docsinpage);
		      %>
		      <div align="left"><a href="<%=urlmoins%>"><span class="limomedium">Prev.</span></a></div>
		        <%} 
		      else {%>&nbsp;<%}%>
		    </td>
		    <td width="70%" valign="bottom"> 
		      <div align="center"><font>Page <%=pagenum%> of <%=totalpages%> </font></div>
		    </td>
		    <td width="15%" valign="bottom"> 
		      <% if (pagenum<totalpages)  { 
		      String urlplus = url+"&start="+(pagenum*docsinpage);
		      %>
		      <div align="right"><a href="<%=urlplus%>"><span class="limomedium">Next</span></a></div>
		      <%} else {%>&nbsp; <%}%>
</tr>


<tr>
<th>Rank</th>
<th>Score</th>
<th>Doc#</th>
<%

	for ( Iterator iter = fieldNames.iterator(); iter.hasNext(); ) {
		String fieldName = (String) iter.next();
		
%>
<th><%= fieldName %></th>
<%
	}
%>
<th>&nbsp;</th>
</tr>

<%
    
    for ( int index = startingdoc; index < startingdoc+docsinpage && index < hits.length(); index++ ) {
        Document doc = hits.doc( index );
        int id = hits.id( index );
        
%>
<tr class="colorAlternate<%= (index % 2) %>">
<td valign="middle"><%=index%></td>
<td valign="middle"><%= ((double)Math.round((float)1000 * hits.score( index )) / 10.0) %></td>
<td valign="middle"><a href="load.jsp?location=<%= URLEncoder.encode(location,"ISO-8859-1") %>&num=<%= id %>" target="_top"><%= id %></a></td>
<%
		for ( Iterator iter = fieldNames.iterator(); iter.hasNext(); ) {
			String fieldName = (String) iter.next();
			String value =  doc.get( fieldName );
			if ( value == null ) {
				value = "";
			} else {
				value = value.replaceAll( "<", "&lt;" ).replaceAll( ">", "&gt;" );
			}
			
			value = LimoUtils.highlightTerms( query, value, fieldName, analyzer );
%>
<td valign="middle"><%= value %></td>
<%
		}
%>
<td><span class="searchResultMenu"><a href="load.jsp?location=<%= URLEncoder.encode(location,"ISO-8859-1") %>&num=<%= id %>" target="_top">view</a><br><a href="explain.jsp?docId=<%= id %>" target="explainFrame">explain</a><br><a href="reconstruct.jsp?docId=<%= id %>" target="docFrame">reconstruct</a></span></td>
</tr>
<%
    }
%>
</table>
<%
} catch ( BooleanQuery.TooManyClauses e ) {
%>
<pre>
Caught TooManyClauses exception. Maximum clauses is <%= BooleanQuery.getMaxClauseCount() %>. 

You can change this by setting the org.apache.lucene.maxClauseCount system property.

<%
e.printStackTrace( new PrintWriter( out ) );
%>
</pre>
<%
} catch ( Exception e ) {
%>
<pre>
<%
e.printStackTrace( new PrintWriter( out ) );
%>
</pre>
<%
    return;
}
%>
</body>
</html>
