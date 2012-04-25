<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<link href="../css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="../js/user/user.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/string.js"></script>
<script language="javascript">
<!--
　　javascript:window.history.forward(1);
-->
</script> 
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listUser.do" class="green">用户管理</a>  > <span class="fb black">用户列表</span>
    </div>    
    <form name="form1" action="listUser.do" method="post">       
    <div id="container">
     <div class="addsell">
   	  <div class="left"><input type="button" onclick="preAddUser()" class="btn120x28" style="cursor:pointer" id="tjdept" name="tjdept" value="新增用户">   	  
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	<input type="button" class="btn48x22 fl_left" name="setVisible" id="setVisible" style="cursor:pointer" onclick="updateState(1,'userid','updateStatusUser')" value="启用"/>
        	<input type="button" class="btn48x22 fl_left" name="setHidden" id="setHidden" style="cursor:pointer" onclick="updateState(2,'userid','updateStatusUser')" value="禁用"/>
        	<input type="button" class="btn48x22 fl_left" name="setDel" id="setDel" style="cursor:pointer" onclick="updateState(3,'userid','updateStatusUser')" value="删除"/>
		</div>
		<div class="fl_left">
        	用户名:<input name="name" size="16" id="name" type="text" value="${model.name?if_exists}"/>   
        	登录名:<input name="loginName" size="16" id="loginName" type="text" value="${model.loginName?if_exists}"/>   
        	Email:<input name="email" size="16" id="email" type="text" value="${model.email?if_exists}"/>
        	手机号:<input name="handtel" size="16" id="handtel" type="text" value="${model.handtel?if_exists}"/>
        	状态:<select name="status" id="status">
        	<option value="0">请选择</option>
        	<option value="1" <#if model.status?if_exists==1>selected</#if>>可用</option>
        	<option value="2" <#if model.status?if_exists==2>selected</#if>>禁用</option>
        	<option value="3" <#if model.status?if_exists==3>selected</#if>>删除</option>
        	</select> 
        	<input type="button" class="btn48x22" style="cursor:pointer" onclick="queryTadmUser()" id="queryUser" value="查询"> 	
        </div>        
      </div>
      <div class="clear"></div>
      
    <!--上边功能区结束-->
   
    <!--列表开始-->
    <div class="selllist fl_left" style="width:100%">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col" class="tabheader nb_left" width="6%"><input type="checkbox" title="全选/取消全选" onclick="selectAll(this.checked,'userid')" name="allChose" id="allChose" />全选</th>                       
            <th scope="col" class="tabheader" width="8%">登录名</th>
            <th scope="col" class="tabheader" width="8%">用户名</th>
             <th scope="col" class="tabheader" width="6%">昵称</th>
             <th scope="col" class="tabheader" width="6%">状态</th>
             <th scope="col" class="tabheader" width="20%">所属部门</th>
             <th scope="col" class="tabheader" width="10%">所属角色</th>
            <th scope="col" class="tabheader" width="10%">是否允许删除</th>
            <th scope="col" class="tabheader" width="8%">是否管理员</th>
            <th scope="col" class="tabheader" width="6%">手机号</th>
            <th scope="col" class="tabheader" width="6%">EMAIL</th>            
            <th scope="col" class="tabheader nb_right" width="6%">备注</th>
          </tr>
          <#list model.userList?if_exists as list>
          <tr>
            <td class="tablist"><input type="checkbox" <#if list.isAdminUser?if_exists==1>disabled</#if> title="全选/取消全选" name="userid" id="userid" value="${list.id?if_exists}"/>${list_index?if_exists+1}</td>     
            <td class="tablist">
            <#if list.isAdminUser?if_exists==1>
            ${list.loginName?if_exists?html}
            <#else>
            <a href="#" onclick="preEditUser(${list.id?if_exists?html})" class="green">${list.loginName?if_exists?html}</a>
            </#if>
            </td>
            <td class="tablist">${list.name?if_exists?html}</td>
            <td class="tablist">${list.bname?if_exists?html}</td>
            <td class="tablist"><#if list.status?if_exists==1>可用<#elseif list.status?if_exists==2>禁用<#else>删除</#if></td>               
            <td class="tablist"><#if list.deptName?if_exists!="">${list.deptName?substring(1,list.deptName?length)?if_exists?html}</#if></td>
            <td class="tablist">${list.roleName?if_exists?html}</td>
            <td class="tablist" id="td_allow_del" name="td_allow_del"><#if list.allowDel?if_exists==1>是<#else>否</#if></td>  
            <td class="tablist"><#if list.isAdminUser?if_exists==1>是<#else>否</#if></td> 
             <td class="tablist">${list.handtel?if_exists?html}</td>       
            <td class="tablist">${list.email?if_exists?html}</td>     
            <td class="tablist">${list.remark?if_exists?html}</td>            
          </tr>
          </#list>          
</table>

    </div>
    <div class="clear"></div>
    <!--列表结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
  <div class="pages fl_left">
  	        <#include "../inc/page.ftl">
        	
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
    </form>
</div>
<script>
<#if model.result?if_exists=="1">
alert("状态更新成功");
</#if>
</script>
</body>
</html>
