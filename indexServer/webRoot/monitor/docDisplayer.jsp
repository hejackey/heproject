<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.lucene.index.IndexReader,org.apache.lucene.document.DateField,java.util.*" %>

<%-- displays the content of a given document 
     opens the Reader and prints the document (or the first one in case of problem)
--%>

<%
String location = request.getParameter("location");


/** load.jsp should have opened everything before 
    this is left in case we want to access it differently
**/

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

/****************************/

String docnumstring = request.getParameter("doc");
int docnum=0; // the document to display
if (docnumstring!=null){
    try{docnum = Integer.parseInt(docnumstring);} catch (Exception e){docnum=0;}
}

int numdocsavailable = reader.numDocs();

if (numdocsavailable==0)
{
    // TODO display a message
    return;
} 

else if (docnum>numdocsavailable)
{
    // TODO display a message
    return;
} 
%>

<!-- Navigation Bar-->
<%
String url = "load.jsp?location="+java.net.URLEncoder.encode(location,"ISO-8859-1")+"&num=";
%>

<br>

<table width="70%" border="0" align="center">
  <tr> 
    <td width="15%" valign="bottom"> 
      <% if (docnum>0)  { %>
      <div align="left"><A HREF=<%=url+(docnum-1)%>><span class="limomedium">Prev.</span></A> 
        <%} else {%>&nbsp;<%}%>
      </div>
    </td>
    <td width="54%" valign="bottom"> 
      <div align="center"><font face="Arial, Helvetica, sans-serif" size="+2">Document <%=docnum+1%> of <%=numdocsavailable%> </font></div>
    </td>
    <td width="15%" valign="bottom"> 
      <% if (docnum<numdocsavailable-1)  { %>
      <div align="right"><A HREF=<%=url+(docnum+1)%>><span class="limomedium">Next</span></A> 
      <%} else {%>&nbsp; <%}%>
      </div>
  </tr>
</table>

<br>

<table width="100%" border="0"><%

org.apache.lucene.document.Document doc = reader.document(docnum);
Enumeration fields = doc.fields();
while (fields.hasMoreElements())
{
    org.apache.lucene.document.Field field =  (org.apache.lucene.document.Field)fields.nextElement();
    boolean fieldstored = field.isStored();
    boolean fieldtokenized = field.isTokenized();
    boolean fieldindexed = field.isIndexed();
    String namefield  = field.name();
    String valuefield  = field.stringValue();

    // limitation on size
    if (valuefield.length()>400){valuefield = valuefield.substring(0,400)+"... ";}
    // do not display html 
    valuefield=valuefield.replaceAll("<","&lt;");
    valuefield=valuefield.replaceAll(">","&gt;");
    
    %>  <tr> 
    <td width="10%" class=tabtitlegrey><font><%=namefield%></font></td>
    <td width="10%"><span class="<%=fieldtokenized%>">tokens</span></td>
    <td width="10%"><span class="<%=fieldindexed%>">index</span></td>
    <td width="*%" class="fieldcontent"><%=valuefield%></td>
  </tr>
<%
}
%>

</table>