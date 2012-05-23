<%@page contentType="text/html;charset=utf-8"%>
<%@ page import="org.apache.lucene.index.IndexReader,java.util.*,java.io.*" %>

<html>
<body>
<jsp:include page="header.html" />
<%-- This page adds a new index to the list of available indices 
  It is called from the index.jsp page
--%>
<%
	String name = request.getParameter("name");
	String location = request.getParameter("loc");
	
	boolean isok = true;
	if (name==null || location==null)isok=false;
	

  // convert into an absolute path if necessary
  File indexFile = new File(location);
  if (indexFile.isAbsolute()==false){
    location = getServletConfig().getServletContext().getRealPath(location);
  }
	
	// checks if proper index
	isok = IndexReader.indexExists(location);
	
	if (isok) {
	
	//name = name.replace(' ','_');

 	String realpath =  getServletConfig().getServletContext().getRealPath("limo.properties");
 	Properties p = new Properties();
 	File file = new File(realpath);
 	if (file.exists()) {
		FileInputStream fis = new FileInputStream(realpath);
    		p.load(fis);
    		fis.close();
    	}
    	
    	// adds the current index 	
    	p.setProperty(name,location);
    	
    	// saves the file
    	try {
		FileOutputStream fos = new FileOutputStream(realpath);
    		p.store(fos,"List of Lucene indexes accessible for LIMO");
    		fos.close();
    		
    		%><CENTER> <i><%=location%></i> as been added as <span class=limomedium><%=name%></span></CENTER><%
    		
    	}catch (Exception exception){
    		%><CENTER> Impossible to modify ( <%=realpath%> )</CENTER> <%
    	}
    	
    	}
    	
    	else {
    	// BAD index
    	%>
    	<CENTER> Impossible to load the index ( <%=location%> )</CENTER> 
    	<%
    	}

%>
<br>

<jsp:include page="addIndexForm.html" />

</body>
</html>