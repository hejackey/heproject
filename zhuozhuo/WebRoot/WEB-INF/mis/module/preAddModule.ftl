<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../${context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/helpCode.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/module/module.js"></script>
<script type="text/javascript" src="../js/string.js"></script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listModule.do" class="green">模块管理</a>  > <span class="fb black"><#if model.id?if_exists!="">修改模块<#else>新增模块</#if></span>
    </div>
    <div id="container">
   
    <div class="clear"></div>
    <form name="form1" method="post" action="saveModule.do">
     <input type="hidden" name="parentid" id="parentid" value="${model.parentid?if_exists}">
     <input type="hidden" name="id" id="id" value="${model.id?if_exists}">
     <input type="hidden" name="hifuse" id="hifuse" value="${model.ifuse?if_exists}">
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
		<div class="left currentname fb fl_left"><#if model.id?if_exists!="">修改模块<#else>新增模块</#if></div>        
        <div class="left fl_right textright"><a href="listModule.do" class="green fb">返回上一级</a></div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>            
            <td width="7%" class="header fb">模块名称<span class="required">*</span></td>
            <td width="18%">
              <input type="text" name="moduleName" id="moduleName" onblur="TextChange(this,moduleCode)" class="sellinput" value="${model.moduleName?if_exists?html}"/>
            </td>
            <td width="7%" class="header fb">模块编号<span class="required">*</span></td>
            <td width="18%">
              <input type="text" name="moduleCode" id="moduleCode" readonly class="sellinput" value="${model.moduleCode?if_exists?html}"/>
            </td>
            <td width="7%" class="header fb">模块类型</td>
            <td width="18%">
            <select name="moduleType" id="moduleType">
              <option value="1" <#if model.moduleType?if_exists==1>selected</#if>>普通</option>
              <option value="2" <#if model.moduleType?if_exists==2>selected</#if>>受限</option>
              <option value="3" <#if model.moduleType?if_exists==3>selected</#if>>开放</option>                 
            </select>             
            </td>
             <td class="header fb">是否记录日志</td>
            <td><select name="forLog" id="forLog">            
              <option value="1" <#if model.forLog?if_exists==1>selected</#if>>是</option>
              <option value="2" <#if model.forLog?if_exists==2>selected</#if>>否</option>             
            </select></td>
          </tr>
          <tr>
             <td class="header fb">是否可用</td>
            <td><select name="ifuse" id="ifuse">            
              <option value="1" <#if model.ifuse?if_exists==1>selected</#if>>是</option>
              <option value="2" <#if model.ifuse?if_exists==2>selected</#if>>否</option>             
            </select></td>
            <td class="header fb">URL</td>
            <td class="header fb"><input type="text" name="url" id="url" class="sellinput" value="${model.url?if_exists?html}"/></td>
            <td class="header fb">显示顺序</td>
            <td><input type="text" name="dispnum" id="dispnum" class="sellinput" value="${model.dispnum?if_exists?html}"/></td>
            
            <td class="header fb">备注</td>
            <td><input type="text" name="remark" id="remark" class="sellinput" value="${model.remark?if_exists?html}"/></td>
          </tr>
          <tr>
            <td colspan="8" class="header fb">
            <#if model.id?if_exists!="">
            <input type="button" class="btn48x22 fl_left" onclick="editModule()" style="cursor:pointer" id="savemodule" name="savemodule" value="修改"/>            
            <#else>
            <input type="button" class="btn48x22 fl_left" onclick="saveModule();" style="cursor:pointer" id="savemodule" name="savemodule" value="保存"/>
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
        &nbsp;&nbsp;&nbsp;&nbsp;模块编码是模块名称简写，不需要手工录入
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
	alert("新增模块成功");	
</#if>

<#if model.result?if_exists=="2">
	alert("修改模块成功");	
</#if>

<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
