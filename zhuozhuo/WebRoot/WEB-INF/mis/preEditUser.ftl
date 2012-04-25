<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<#import "spring.ftl" as spring/> 
<html>
  <head>
    <meta http-equiv=Content-Type content="text/html; charset=utf-8">
    <title>中文编辑用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
    <form action="editUser.do" method="post">
    <table align="center" bgcolor="#008800" border="0" cellspacing="2" cellpadding="3"  width="600">
		    <tr bgcolor="#CCCCCC">
		    	<td colspan="2" align="center">用户信息修改</td>
		    </tr>
		    <tr bgcolor="#FFFF88">
		    	<td>Name: <@spring.bind	"command.name"/></td>
		    	<td><input type="text" name="name" value="${spring.status.value?default("")}"/></td>
		    </tr>
		    <tr bgcolor="#FFFF88">
		    	<td>mail: <@spring.bind	"command.mail"/></td>
		    	<td><input type="text" name="mail" value="${spring.status.value?default("")}"/></td>
		    </tr>
		    <tr bgcolor="#FFFF88">
		    	<td>telephone: <@spring.bind	"command.telephone"/></td>
		    	<td><input type="text" name="telephone" value="${spring.status.value?default("")}"/></td>
		    </tr>		    
		    <tr bgcolor="#FFFF88" >
		    	<td colspan="2" align="left">
		    		<div>
		    			内容:<@spring.bind	"command.content"/>
						<input id="content" name="content" value='${spring.status.value?default("")}'  type="hidden" />
						<!-- <iframe id="content___Frame" frameborder="0" height="200" scrolling="no" width="600" src="/fckeditor/fckeditor/editor/fckeditor.html?InstanceName=content&amp;Toolbar=Basic"> </iframe>-->
						<iframe id="content___Frame" frameborder="0" height="300" scrolling="no" width="600" src="/fckeditor/fckeditor/editor/fckeditor.html?InstanceName=content&amp;Toolbar=Default"> </iframe>
					</div>
				</td>
		    </tr>
		    <tr bgcolor="#FFFF88" >
		    	<td colspan="2" align="center">
		    		<@spring.bind "command.id"/>
	    			<input type="hidden" name="id" value="${spring.status.value?default("")}"/>
		    		<input type="submit" value="编辑后提交"/></td>
		    </tr>
		</table>		
	</form>
  </body>
</html>				 