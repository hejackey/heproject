<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../${context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/role/role.js"></script>
<script type="text/javascript" src="../js/string.js"></script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listRole.do" class="green">角色管理</a>  > <span class="fb black"><#if model.id?if_exists!="">修改角色<#else>新增角色</#if></span>
    </div>
    <div id="container">
   
    <div class="clear"></div>
    <form name="form1" method="post" action="saveRole.do">
     <input type="hidden" name="parentid" id="parentid" value="${model.parentid?if_exists}">
     <input type="hidden" name="id" id="id" value="${model.id?if_exists}">
     <input type="hidden" id="hifuse" name="hifuse" value="${model.ifuse?if_exists}">
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
		<div class="left currentname fb fl_left"><#if model.id?if_exists!="">修改角色<#else>新增角色</#if>(注：“<span class="required">*</span>” 表示必填项)</div>        
        <div class="left fl_right textright"><a href="listRole.do" class="green fb">返回上一级</a></div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>           
            <td width="8%" class="header fb">角色名称<span class="required">*</span></td>
            <td width="17%">
              <input type="text" name="roleName" id="roleName"  class="sellinput" value="${model.roleName?if_exists?html}"/>
            </td>
            <td width="8%" class="header fb">角色描述</td>
            <td width="17%">
              <input type="text" name="roleDesc" id="roleDesc" class="sellinput" value="${model.roleDesc?if_exists?html}"/>
            </td>
            <td width="8%" class="header fb">是否可用</td>
            <td width="17%"><select name="status" id="status">              
              <option value="1" <#if model.status?if_exists==1>selected</#if>>可用</option>
              <option value="2" <#if model.status?if_exists==2>selected</#if>>禁用</option> 
              <!--<option value="3" <#if model.status?if_exists==3>selected</#if>>删除</option>-->                
             </select></td>
           
          </tr>
          
          <tr>
            <td colspan="8" class="header fb">
            <#if model.id?if_exists!="">
            <input type="button" class="btn48x22 fl_left" onclick="editRole()" style="cursor:pointer" id="editrole" name="editrole" value="修改"/>            
            <#else>
            <input type="button" class="btn48x22 fl_left" onclick="saveRole();" style="cursor:pointer" id="saverole" name="saverole" value="保存"/>
            </#if>
            </td>
          </tr>
          
        </table>
    </div>
    <div class="tooltop nowarp">
   	    <div class="left currentname fb fl_left">
        	权限分配
		</div>    
			
    </div>
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>           
            <td width="6%" class="header fb">序号</td>
            <td width="10%" class="header fb">系统业务权限 </td>
            <td width="10%" class="header fb">功能模块权限</td>
            <td width="40%" class="header fb">模块操作权限</td>
            <td width="14%" class="header fb"></td>            
          </tr>
          <#list model.moduleList?if_exists as list>
          <tr>
            <#if list.parentid?if_exists=="">
            <td >◎</td>
            <td colspan="4" class="fb"><input type="checkbox" name="f_m_${list.id?if_exists}" onclick="selectRoleAll(this.checked,'moduleid','s_m_${list.id?if_exists}')" id="f_m_${list.id?if_exists}" value="${list.id?if_exists}"/>${list.moduleName?if_exists?html}</td>
            <#else>
           
	            <td colspan="2"  class="header fb">→</td>
	            <#if model.rolePrivList?if_exists?size==0>
	            <td  class="header fb"><input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_0" value="L:${list.id?if_exists}:${list.parentid?if_exists}"/>${list.moduleName?if_exists?html}</td>            
	            <td  class="header fb">
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_1" value="A:${list.id?if_exists}:${list.parentid?if_exists}">新增&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_2" value="E:${list.id?if_exists}:${list.parentid?if_exists}">修改&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_3" value="D:${list.id?if_exists}:${list.parentid?if_exists}">删除&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_4" value="Q:${list.id?if_exists}:${list.parentid?if_exists}">查询&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_5" value="U:${list.id?if_exists}:${list.parentid?if_exists}">启用/禁用&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_7" value="P:${list.id?if_exists}:${list.parentid?if_exists}">打印&nbsp;&nbsp;
	            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_8" value="E:${list.id?if_exists}:${list.parentid?if_exists}">导入导出
	            </td>
	            <td  class="header fb"><input type="checkbox" name="allChose" id="allChose" onclick="selectRolePril(this.checked,'s_m_${list.id?if_exists}',9)" value="${list.id?if_exists}">全选</td>
	            <#else>	                
	                <td  class="header fb">
	                <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_0"
	                 <#list model.rolePrivList?if_exists as rolePriv>            		           
			             <#if rolePriv?if_exists!="" && rolePriv?if_exists=="L:${list.id?if_exists}:${list.parentid?if_exists}">
		                	checked
		                 </#if>
	                 </#list>
	                 value="L:${list.id?if_exists}:${list.parentid?if_exists}"/>${list.moduleName?if_exists?html}
	                </td>            
		            <td  class="header fb">
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_1" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="A:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="A:${list.id?if_exists}:${list.parentid?if_exists}">新增&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_2" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="E:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="E:${list.id?if_exists}:${list.parentid?if_exists}">修改&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_3" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="D:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="D:${list.id?if_exists}:${list.parentid?if_exists}">删除&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_4" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="Q:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="Q:${list.id?if_exists}:${list.parentid?if_exists}">查询&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_5" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="U:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="U:${list.id?if_exists}:${list.parentid?if_exists}">启用/禁用&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_7" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="P:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="P:${list.id?if_exists}:${list.parentid?if_exists}">打印&nbsp;&nbsp;
		            <input type="checkbox" name="moduleid" title="s_m_${list.parentid?if_exists}" id="s_m_${list.id?if_exists}_8" 
		            <#list model.rolePrivList?if_exists as rolePriv>            		           
			            <#if rolePriv?if_exists!="" && rolePriv?if_exists=="E:${list.id?if_exists}:${list.parentid?if_exists}">
			           		checked
			            </#if>
		            </#list>
		            value="E:${list.id?if_exists}:${list.parentid?if_exists}">导入导出
		            </td>
		            <td  class="header fb"><input type="checkbox" name="allChose" id="allChose" onclick="selectRolePril(this.checked,'s_m_${list.id?if_exists}',9)" value="${list.id?if_exists}">全选</td>
	           </#if>   
	        </#if>      
          </tr>
          </#list>
        </table>
    </div>   	     
    <!--详细结束-->
    
    <!--上边功能区开始-->
   
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
    </form>
</div>
<script>
<#if model.result?if_exists=="1">
	alert("新增角色成功");	
</#if>

<#if model.result?if_exists=="2">
	alert("修改角色成功");	
</#if>

<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
