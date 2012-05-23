<%@page contentType="text/html;charset=utf-8" %>
<%@ page import="org.apache.lucene.index.IndexReader,org.apache.lucene.document.DateField,java.util.*,java.io.*" %>
<%@ page import="javax.servlet.*,javax.servlet.http.*" %>

<html>
<body>
<jsp:include page="header.html" />
<%-- This page indicates which indexes are available --%>
<%
// parses the list of parameters and checks wether the values corresponds to valid Indexes
ArrayList indexes = new ArrayList();

	try {
 	Properties p = new Properties();
 	String realpath =  getServletConfig().getServletContext().getRealPath("/limo.properties");
	FileInputStream fis = new FileInputStream(realpath);
    	p.load(fis);
    	Iterator iter = p.keySet().iterator();
    	while (iter.hasNext()){
    	    String cle = (String)iter.next();
    	    String value = p.getProperty(cle);
    	    if (IndexReader.indexExists(value))
    	    {
    	        indexes.add(new String[]{cle,value});
    	    }
    	}
    	fis.close();
    	}catch (Exception exceptionparams){}

%>
<br>
<table width="50%" border="1" cellspacing="0" align="center" bordercolor="#333333" bgcolor="#FFFFFF">
  <tr> 
    <td> 
      <%-- Display of the available indexes--%>
      <%  // at least 1 index available
    if (indexes.size()>=1){
    
        if (indexes.size()==1){%>
      <CENTER>
        <H2>1 index found </H2>
      </CENTER>
      <%}

        else {%>
      <CENTER>
        <H2><%=indexes.size()%> indexes found </H2>
      </CENTER>
      <ul>
        <%}

        for (int i=0;i<indexes.size();i++)
        {
           String[] namevalue = (String[])indexes.get(i);
           String encodedlocation = java.net.URLEncoder.encode(namevalue[1],"ISO-8859-1");
            %>
        <li><A href = "load.jsp?location=<%=encodedlocation%>"><%=namevalue[0]%></A> 
        </li>
        <%
        }
}
    // pas d'index
    else {
    %>
        <CENTER>
          <H2>No index available</H2>
        </CENTER>
        <%
    }
%>
      </ul>
      <br>
    </td>
  </tr>
</table>
<br>

<jsp:include page="addIndexForm.html" />
</body>
</html>
