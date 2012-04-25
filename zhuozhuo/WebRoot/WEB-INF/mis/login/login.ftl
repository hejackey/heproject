<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${model.context?if_exists}/css/admin.css" />
<title>卓品后台管理系统</title>
</head>
<#if model.result?if_exists=="1">
<script>
<!--
 if(window.parent)window.parent.location.reload();   
//-->
</script>
</#if>
<body onload="getFocus()" onkeyup="if(window.event.keyCode == 13){submitInfo();}" >
<script src="${model.context}/web/js/login.js"></script>
<div class="enter">
<div class="enterbg">
<div class="enternr">
<form id="userInfo" method="post" action="${model.context}/login/submitLogin.do" name="form1">
<div class="enteruser">
  <ul>
    <li>用户名:</li>
	<li><input id="loginName" type="text" name="loginName" class="inputtext" maxlength="18" /></li>
  </ul>
</div>

<div class="enterPassword">
  <ul>
    <li>密　码:</li>
    <li>
      <input id="passwordid" type="password" name="password" class="inputtext" maxlength="16" />
    </li>
  </ul>
</div>

<div class="enteryazheng">
  <ul>
    <li style="padding-top:7px; height:23px;">验证码:</li>
	<li style="width:140px;"><input id="checkcodeid" type="text" name="checkCode" class="inputtext" size="18" maxlength="4" />
	<img src="${model.context}/sltmakeImage" width="60" height="25" style="float:left;margin-top:-28px; margin-left:150px;"/></li>
  </ul>
</div>
<br><br><br>
				  						
<div class="enterbutton">
  <input name="Submit" type="submit" value="登 录" />
</div>
</form>
</div>
</div>
</div>
<script>
<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
