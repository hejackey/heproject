<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../${context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/helpCode.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/dept/dept.js"></script>
<script type="text/javascript" src="../js/string.js"></script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listDept.do" class="green">部门管理</a>  > <span class="fb black"><#if model.id?if_exists!="">修改部门<#else>新增部门</#if></span>
    </div>
    <div id="container">
   
    <div class="clear"></div>
    <form name="form1" method="post" action="saveDept.do">
     <input type="hidden" name="parentid" id="parentid" value="${model.parentid?if_exists}">
     <input type="hidden" name="id" id="id" value="${model.id?if_exists}">
     <input type="hidden" id="hifuse" name="hifuse" value="${model.ifuse?if_exists}">
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
		<div class="left currentname fb fl_left"><#if model.id?if_exists!="">修改部门<#else>新增部门</#if></div>        
        <div class="left fl_right textright"><a href="listDept.do" class="green fb">返回上一级</a></div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>
            <td width="8%" class="header fb">上级部门名称</td>
            <td width="17%">
              <input type="text" name="parentname" readonly id="parentname" class="sellinput" value="<#if model.parentname?if_exists?index_of('-')!=-1>${model.parentname?substring(1,model.parentname?length)?if_exists?html}<#else>${model.parentname?if_exists?html}</#if>"/>
            </td>
            <td width="8%" class="header fb">部门名称<span class="required">*</span></td>
            <td width="17%">
              <input type="text" name="departmentname" id="departmentname" onblur="TextChange(this,departmentcode)" class="sellinput" value="${model.departmentname?if_exists?html}"/>
            </td>
            <td width="8%" class="header fb">部门编号<span class="required">*</span></td>
            <td width="17%">
              <input type="text" name="departmentcode" id="departmentcode" readonly class="sellinput" value="${model.departmentcode?if_exists?html}"/>
            </td>
            <td width="7%" class="header fb">层级<span class="required">*</span></td>
            <td width="18%">
              <input type="text" name="levels" id="levels" class="sellinput" readonly value="${model.levels?if_exists?html}"/>
            </td>
          </tr>
          <tr>            
            <td class="header fb">是否可用</td>
            <td><select name="ifuse" id="ifuse">              
              <option value="1" <#if model.ifuse?if_exists==1>selected</#if>>是</option>
              <option value="2" <#if model.ifuse?if_exists==2>selected</#if>>否</option>             
            </select></td>
            <td class="header fb">是否末级</td>
            <td><select name="isleaf">
              <option value="1" <#if model.isleaf?if_exists==1>selected</#if>>是</option>
              <option value="0" <#if model.isleaf?if_exists==0>selected</#if>>否</option>
            </select></td>
            <td class="header fb">备注</td>
            <td><input type="text" name="remark" id="remark" class="sellinput" value="${model.remark?if_exists?html}"/></td>
            <td class="header fb">&nbsp;</td>
            <td class="header fb">
                
            </td>
          </tr>
          <tr>
            <td colspan="8" class="header fb">
            <#if model.id?if_exists!="">
            <input type="button" class="btn48x22 fl_left" onclick="editDept()" style="cursor:pointer" id="editdept" name="editdept" value="修改"/>            
            <#else>
            <input type="button" class="btn48x22 fl_left" onclick="saveDept();" style="cursor:pointer" id="savedept" name="savedept" value="保存"/>
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
     <div class="tooltop nowarp">
   	    <div class="left fl_left">
        &nbsp;&nbsp;&nbsp;&nbsp;部门编码是部门名称简写，不需要手工录入
		</div>        
      </div>
      <div class="tooltop nowarp">
   	    <div class="left fl_left">
        &nbsp;&nbsp;&nbsp;&nbsp;层级指当前新加部门在树形部门所属的第几层级（数值型，如 1）
		</div>        
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
	alert("新增部门成功");	
</#if>

<#if model.result?if_exists=="2">
	alert("修改部门成功");	
</#if>

<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
