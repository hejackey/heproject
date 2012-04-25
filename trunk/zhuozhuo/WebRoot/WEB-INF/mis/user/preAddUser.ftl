<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/user/user.js"></script>
<script type="text/javascript" src="../js/string.js"></script>
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
 <!--
<link href="${model.context?if_exists}/css/swfupload/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/swfupload/swfupload.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/swfupload/swfupload.swfobject.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/swfupload/swfupload.queue.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/swfupload/fileprogress.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/swfupload/handlers.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/upload.js"></script>
-->
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listUser.do" class="green">用户管理</a>  > <span class="fb black"><#if model.id?if_exists!="">修改用户<#else>新增用户</#if></span>
    </div>
    <div id="container">
   
    <div class="clear"></div>
    <form name="form1" method="post" action="saveUser.do">
     
     <input type="hidden" name="id" id="id" value="${model.id?if_exists}">
     <input type="hidden" name="hpasswd" id="id" value="${model.password?if_exists}">
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
		<div class="left currentname fb fl_left"><#if model.id?if_exists!="">修改用户<#else>新增用户</#if></div>        
        <div class="left fl_right textright"><a href="listUser.do" class="green fb">返回上一级</a></div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>            
            <td width="7%" class="header fb">登录名称<span class="required">*</span></td>
            <td width="18%">
              <input type="text" name="loginName" id="loginName" class="sellinput" value="${model.loginName?if_exists?html}"/>
            </td>
            <td width="7%" class="header fb">用户姓名<span class="required">*</span></td>
            <td width="18%">
              <input type="text" name="name" id="name" class="sellinput" value="${model.name?if_exists?html}"/>
            </td>
            <td width="7%" class="header fb">密码<span class="required">*</span></td>
            <td width="18%">
            <input type="password" name="password" id="password" class="sellinput" value="${model.password?if_exists?html}"/>                     
            </td>
             <td class="header fb">有效期</td>
            <td>
             <input type="text" size="22" class="Wdate" name="expireDate" id="expireDate" value="<#if model.expireDate?if_exists!="">${model.expireDate[0..18]?if_exists}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})">
             </td>
          </tr>
          <tr>
           <td class="header fb">是否管理员</td>
            <td>
            <select name="isAdminUser" id="isAdminUser">         
              <option value="2" <#if model.isAdminUser?if_exists==2>selected</#if>>否</option>                  
              <option value="1" <#if model.isAdminUser?if_exists==1>selected</#if>>是</option>                       
            </select>
            </td>             
            <td class="header fb">是否可用</td>
            <td>
            <select name="status" id="status">                          
              <option value="1" <#if model.status?if_exists==1>selected</#if>>可用</option>
              <option value="2" <#if model.status?if_exists==2>selected</#if>>禁用</option> 
              <option value="3" <#if model.status?if_exists==3>selected</#if>>删除</option>            
            </select>
            </td>
            <td class="header fb">是否可删除</td>
            <td>
            <select name="allowDel" id="allowDel">                          
              <option value="1" <#if model.allowDel?if_exists==1>selected</#if>>是</option>
              <option value="2" <#if model.allowDel?if_exists==2>selected</#if>>否</option>           
            </select>
            </td>
            <td class="header fb">性别</td>
            <td>
            <select name="sex" id="sex">                          
              <option value="1" <#if model.sex?if_exists==1>selected</#if>>男</option>
              <option value="2" <#if model.sex?if_exists==2>selected</#if>>女</option>           
            </select>
            </td>
         </tr>
         <tr>   
            <td class="header fb">手机</td>
            <td><input type="text" name="handtel" id="handtel" class="sellinput" value="${model.handtel?if_exists?html}"/></td>
            <td class="header fb">电话</td>
            <td><input type="text" name="telephone" id="telephone" class="sellinput" value="${model.telephone?if_exists?html}"/></td>            
            <td class="header fb">电子邮件</td>
            <td><input type="text" name="email" id="email" class="sellinput" value="${model.email?if_exists?html}"/></td>
            <td class="header fb">昵称</td>
            <td><input type="text" name="bname" id="bname" class="sellinput" value="${model.bname?if_exists?html}"/></td>            
          </tr>
          <tr>           
            <td class="header fb">所属角色<span class="required">*</span></td>
            <td class="header fb">
            <select name="role.id" id="roleid">  
             <option value="0">请选择</option>            
            <#list model.roleList?if_exists as list>            
              <option value="${list.id?if_exists}" <#if model.role.id?if_exists==list.id?if_exists>selected</#if>>${list.roleName?if_exists?html}</option>
            </#list>          
            </select>			
			</td>
            <td class="header fb">地址</td>
            <td><input type="text" name="address" id="address" class="sellinput" value="${model.address?if_exists?html}"/></td>
            
            <td class="header fb">备注</td>
            <td><input type="text" name="remark" id="remark" class="sellinput" value="${model.remark?if_exists?html}"/></td>          
          </tr>
          <tr>
            <td class="header fb">所属部门<span class="required">*</span></td>
            <td colspan="2">
            <select name="dept.id" id="deptid">  
             <option value="0">请选择</option>            
            <#list model.deptList?if_exists as list>            
              <option value="${list.id?if_exists}" <#if model.dept.id?if_exists==list.id?if_exists>selected</#if>>              
              <#if list.departmentname?if_exists!="">${list.departmentname?substring(1,list.departmentname?length)?if_exists?html}</#if>              
              </option>
            </#list>          
            </select>            
            </td>
          </tr>
          <tr>
            <td colspan="8" class="header fb">
            <#if model.id?if_exists!="">
            <input type="button" class="btn48x22 fl_left" onclick="editUser()" style="cursor:pointer" id="saveuser" name="saveuser" value="修改"/>            
            <#else>
            <input type="button" class="btn48x22 fl_left" onclick="saveUser();" style="cursor:pointer" id="saveuser" name="saveuser" value="保存"/>
            </#if>
            </td>
          </tr>
          
        </table>
    </div>
    <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	注：“<span class="required">*</span>” 表示必填项
		</div>       		
      </div>        
    <!--详细结束-->
    
    <!--上边功能区开始-->
   
      <div class="clear"></div>
    <!--上边功能区结束-->
    <!--
    <p> This page demonstrates the SWFObject plugin.  Do each of the following (one at a time) to see the plugin work: </p>
			
		
		<div id="divSWFUploadUI">
			<div class="fieldset  flash" id="fsUploadProgress">
			<span class="legend">Upload Queue</span>
			</div>
			<p id="divStatus">0 Files Uploaded</p>
			<p>
				<span id="spanButtonPlaceholder"></span>
				<input id="btnCancel" type="button" value="Cancel All Uploads" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
				<br />
			</p>
		</div>
		<noscript>
			<div style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px;">
				We're sorry.  SWFUpload could not load.  You must have JavaScript enabled to enjoy SWFUpload.
			</div>
		</noscript>
		<div id="divLoadingContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
			SWFUpload is loading. Please wait a moment...
		</div>
		<div id="divLongLoading" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
			SWFUpload is taking a long time to load or the load has failed.  Please make sure that the Flash Plugin is enabled and that a working version of the Adobe Flash Player is installed.
		</div>
		<div id="divAlternateContent" class="content" style="background-color: #FFFF66; border-top: solid 4px #FF9966; border-bottom: solid 4px #FF9966; margin: 10px 25px; padding: 10px 15px; display: none;">
			We're sorry.  SWFUpload could not load.  You may need to install or upgrade Flash Player.
			Visit the <a href="http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash">Adobe website</a> to get the Flash Player.
		</div>
		-->
    </div>
    </form>
</div>
<script>
<#if model.result?if_exists=="1">
	alert("新增用户成功");	
</#if>

<#if model.result?if_exists=="2">
	alert("修改用户成功");	
</#if>

<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
