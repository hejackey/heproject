<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.lucene.index.IndexReader,org.apache.lucene.document.DateField,java.util.*" %>
<%@ page import="java.net.URLEncoder" %>

<html>
<body>
<jsp:include page="header.html" />
<%-- This page tries to load an index and find information about it --%>
<%
String location = request.getParameter("location");
/* Loads this index */
boolean exists = IndexReader.indexExists(location);
if (exists==false) // since the index.jsp has already verified this should not be necessary
{
    return;
}


int doc2display = 0;
String temp = request.getParameter("num");
if (temp != null)
{
    try {doc2display = Integer.parseInt(temp);} catch (Exception e){doc2display = 0;}
    
}

String currentindexlocation = (String)session.getAttribute("currentindexlocation");
IndexReader reader = (IndexReader)session.getAttribute("currentreader");
Long lastmodif = (Long)session.getAttribute("lastmodif");

// gets the last modification date and compare it to the one we had previously
long modified = IndexReader.lastModified(location);
boolean haschangedsince = ( (lastmodif!=null) && (lastmodif.longValue()!=modified));

// another one has been selected or the index has changed since
if ( (location.equals(currentindexlocation)==false)|| haschangedsince )
{
    // we close the old one if necessary
    if (reader!=null)reader.close();
    reader = null;
}

// we lost it or need the new one
if (reader==null)reader = IndexReader.open(location);

// anyway
session.setAttribute("currentindexlocation",location);
session.setAttribute("currentreader",reader);
session.setAttribute("lastmodif",new Long(modified));

int numdocs = reader.numDocs();

String datemodif = new Date(modified).toString();

ArrayList fieldslist = new ArrayList(reader.getFieldNames(IndexReader.FieldOption.ALL));
HashSet indexset = new HashSet(reader.getFieldNames(IndexReader.FieldOption.INDEXED));
int numFields = fieldslist.size();
int numIndexedFields = indexset.size();
%>

<p><a href="indexFiles.jsp?location=<%= location %>">Index Files</a></p>
<table width="90%" border="0" align="center">
  <tr> 
    <tr>
    		<td colspan="3" align="center"><jsp:include page="searchbox.jsp"/></td>
    </tr>
  <tr>
    <td width="474" valign="top"> 
      <table width="100%" border="0" valign="top">
        <tr > 
          <td width="52%" class="tabtitlewhite">Location: </td>
          <td width="48%"><%= location %>
           <% if (IndexReader.isLocked(location)){%><img src="pics/loksh.gif" width="29" height="35"><%}%>
          </td>
        </tr>
        <tr > 
          <td width="52%">&nbsp;</td>
          <td width="48%">&nbsp;</td>
        </tr>
        <tr > 
          <td width="52%" class="tabtitlewhite">Last Modified:</td>
          <td width="48%"><%= datemodif%></td>
        </tr>
        <tr>
        	  <td width="52%" class="tabtitlewhite">Index Version Number:</td>
        	  <td width="*"><%=  IndexReader.getCurrentVersion( location ) %></td>
        </tr>
        <tr>
        	  <td width="52%" class="tabtitlewhite">Has Deletions:</td>
        	  <td width="*"><%= reader.hasDeletions() ? "Yes" : "No" %></td>
        </tr>
        <tr>      
          <td width="52%">&nbsp;</td>
          <td width="48%">&nbsp;</td>
        </tr>
        <tr > 
          <td width="52%" class="tabtitlewhite">Number of Documents:</td>
          <td width="48%"><%= numdocs%></td>
        </tr>
        <tr>
          <td width="52%" class="tabtitlewhite">Number of Fields:</td>
          <td width="*"><%= numFields %></td>
        </tr>
        <tr>
          <td width="52%" class="tabtitlewhite">Number of Indexed Fields:</td>
          <td width="*"><%= numIndexedFields %></td>
        </tr>
        <tr>
          <td width="52%" class="tabtitlewhite">Number of Unindexed Fields:</td>
          <td width="*"><%= (numFields - numIndexedFields) %></td>
        </tr>
      </table>
      <br>
      <%-- Display of the Field Summary --%>
      <table width="100%" border="0" bordercolor="#000000" cellspacing="2" cellpadding="5">
        <tr> 
          <td colspan="4" class="tabtitlewhite">Field Summary</td>
        </tr>
        <tr> 
          <td> 
            <div align="center"><b><font face="Arial, Helvetica, sans-serif">Name</font></b></div>
          </td>
          <td> 
            <div align="center"><b><font face="Arial, Helvetica, sans-serif">Indexed 
              ?</font></b></div>
          </td>
        </tr>
        <%-- Loop on fields --%>
        <% for (int fieldsnum=0;fieldsnum<fieldslist.size();fieldsnum++)
    { 
        String fieldname = (String)fieldslist.get(fieldsnum);
        /** A possible bug in Lucene due to a TermInfo constructor */
        if (fieldname.equals(""))continue;

        boolean indexed = indexset.contains(fieldname);
    %>
        <tr> 
          <td class="sumfname"> <%=fieldname%> </td>
          <%if (indexed){%><td class="isIndexed"> yes </td><%}
            else {%><td class="isNotIndexed"> no </td><%}%>
        </tr>
        <%
    }
%>
      </table>
    </td>
    <td width="75">&nbsp;</td>
    <td width="696" valign="top"> 
      <jsp:include page="docDisplayer.jsp"> 
      <jsp:param name="location" value="<%=location%>" />
      <jsp:param name="doc" value="<%=doc2display%>" />
      </jsp:include>
    </td>
  </tr>
</table>
</body>
</html>
