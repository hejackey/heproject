<%@page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*" errorPage="" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>用户登陆</title>
</head>

<body>
<div align="center">
<center>
  <table>
     <td width="216" height="21" style="font-size: 9pt">
       <img src="images/huntertitle01.gif" border="0" width="206" height="29">
     </td>
        
     <tr>
       <td width="216" height="122" style="font-size: 9pt">
       <form name="form" action="chklogin.jsp" method="post" >
         <table height="120" cellSpacing="0" cellPadding="0" width="85%" align="center" border="0">
           <tr>
             <td align="right" width="40%" height="32" style="font-size: 9pt">用户名：</td>
             <td width="60%" height="32" style="font-size: 9pt">
                <input style="BORDER-RIGHT: #005dce 1px solid; BORDER-TOP: #005dce 1px solid; FONT-SIZE: 10pt; BORDER-LEFT: #005dce 1px solid; BORDER-BOTTOM: #005dce 1px solid; BACKGROUND-COLOR: #dbeafb" size="9" name=username>
             </td>
           </tr>
           <tr>
             <td align="right" width="40%" height="32" style="font-size: 9pt">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>                                
             <td width="60%" height="32" style="font-size: 9pt">
              <input style="BORDER-RIGHT: #005dce 1px solid; BORDER-TOP: #005dce 1px solid; FONT-SIZE: 10pt; BORDER-LEFT: #005dce 1px solid; BORDER-BOTTOM: #005dce 1px solid; BACKGROUND-COLOR: #dbeafb" type="password" size="9"  name=password>
             </td>
           </tr>
           <tr>
             <td colSpan="2" height="32" style="font-size: 9pt">
               <p align="center">
               <input type="submit" name="Submit" value="确 定" class="buttonface" style="BORDER-RIGHT: #005dce 1px solid; BORDER-TOP: #005dce 1px solid; FONT-SIZE: 10pt; BORDER-LEFT: #005dce 1px solid; BORDER-BOTTOM: #005dce 1px solid; BACKGROUND-COLOR: #dbeafb"> &nbsp;&nbsp;&nbsp;&nbsp; 
               <input type="reset" name="Submit2" value="重 写" class="buttonface" style="BORDER-RIGHT: #005dce 1px solid; BORDER-TOP: #005dce 1px solid; FONT-SIZE: 10pt; BORDER-LEFT: #005dce 1px solid; BORDER-BOTTOM: #005dce 1px solid; BACKGROUND-COLOR: #dbeafb">
             </td>
           </tr>
         </table>
         </form>
       </td>
     </tr>
  </table>
</center>
</div> 
</body>
</html>
