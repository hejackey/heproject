<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<link href="../css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="../js/dtree/dtree.js"></script>
<script type="text/javascript" src="../js/module/module.js"></script>
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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listModule.do" class="green">模块管理</a>  > <span class="fb black">模块列表</span>
    </div>    
    <form name="form1" action="listModule.do" method="post">    
    <input type="hidden" name="q_id" id="q_id" value="${model.q_id?if_exists}">
   
    <input type="hidden" name="q_parentid" id="q_parentid" value="${model.q_parentid?if_exists}">
    <div id="container">
    <div class="addsell">
   	  <div class="left"><input type="button" class="btn120x28" id="tjdept" name="tjdept" onclick="preAddModule('1')" style="cursor:pointer" value="新增同级模块"/>
   	  &nbsp;&nbsp;&nbsp;<input type="button" class="btn120x28" id="xjdept" name="xjdept" onclick="preAddModule('2')" style="cursor:pointer" value="新增下级模块"/>        
    
    </div>
    <div class="clear"></div>
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	<input type="button" class="btn48x22 fl_left" name="setVisible" id="setVisible" style="cursor:pointer" onclick="updateState(1,'moduleid','updateStatusModule')" value="启用"/>
        	<input type="button" class="btn48x22 fl_left" name="setHidden" id="setHidden" style="cursor:pointer" onclick="updateState(2,'moduleid','updateStatusModule')" value="禁用"/>
		</div>
		<div class="fl_left">
        	模块编号:<input name="q_modulecode" size="16" id="q_modulecode" type="text" value="${model.q_modulecode?if_exists}"/>   
        	模块名称:<input name="q_modulename" size="16" id="q_modulename" type="text" value="${model.q_modulename?if_exists}"/>   
        	模块类型:<select name="q_moduletype" id="q_moduletype">
        	<option value="0">请选择</option>
        	<option value="1" <#if model.q_moduletype?if_exists==1>selected</#if>>普通</option>
        	<option value="2" <#if model.q_moduletype?if_exists==2>selected</#if>>受限</option>
        	<option value="3" <#if model.q_moduletype?if_exists==3>selected</#if>>开放</option>
        	</select> 
        	是否记录日志:<select name="q_forlog" id="q_forlog">
        	<option value="0">请选择</option>
        	<option value="1" <#if model.q_forlog?if_exists==1>selected</#if>>是</option>
        	<option value="2" <#if model.q_forlog?if_exists==2>selected</#if>>否</option>
        	</select> 
        	是否可用:<select name="q_ifuse" id="q_ifuse">
        	<option value="0">请选择</option>
        	<option value="1" <#if model.q_ifuse?if_exists==1>selected</#if>>是</option>
        	<option value="2" <#if model.q_ifuse?if_exists==2>selected</#if>>否</option>
        	</select> 
        	<input type="button" class="btn48x22" style="cursor:pointer" onclick="queryTadmModule()" id="queryModule" value="查询"> 	
        </div>        
      </div>
      <div class="clear"></div>
      
    <!--上边功能区结束-->
   <div class="fl_left" style="width:15%">
    	<script type="text/javascript">		
		d = new dTree('${model.context?if_exists}','d');
		d.add('',-1,'模块管理','listModule.do');
		<#list model.moduleTreeList?if_exists as moduleList>
			<#if moduleList.parentid?if_exists!="">
			d.add(${moduleList.id?if_exists},${moduleList.parentid?if_exists},'${moduleList.moduleName?if_exists}','listModule.do?isQuery=n&q_id='+${moduleList.id?if_exists}+'&q_parentid='+${moduleList.parentid?if_exists});
			<#else>
			d.add(${moduleList.id?if_exists},'','${moduleList.moduleName?if_exists}','listModule.do?isQuery=n&q_id='+${moduleList.id?if_exists}+'&q_parentid=');
			</#if>
		</#list>		
		document.write(d);
		</script>
    
    </div>
    <!--列表开始-->
    <div class="selllist fl_left" style="width:85%">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col" class="tabheader nb_left" width="6%"><input type="checkbox" title="全选/取消全选" onclick="selectAll(this.checked,'moduleid')" name="allChose" id="allChose" />全选</th>
                       
            <th scope="col" class="tabheader" width="6%">模块编号</th>
            <th scope="col" class="tabheader" width="10%">模块名称</th>
             <th scope="col" class="tabheader" width="7%">模块类型</th>
             <th scope="col" class="tabheader" width="20%">URL</th>
            <th scope="col" class="tabheader" width="20%">上级模块名称</th>
            <th scope="col" class="tabheader" width="9%">是否记录日志</th>
            <th scope="col" class="tabheader" width="7%">是否可用</th>
            <th scope="col" class="tabheader" width="7%">显示顺序</th>            
            <th scope="col" class="tabheader nb_right" width="8%">备注</th>
          </tr>
          <#list model.moduleList?if_exists as list>
          <tr>
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="moduleid" id="moduleid" value="${list.id?if_exists}"/>${list_index?if_exists+1}</td>     
            <td class="tablist"><a href="#" onclick="preEditModule(${list.id?if_exists?html})" class="green">${list.moduleCode?if_exists?html}</a></td>
            <td class="tablist">${list.moduleName?if_exists?html}</td>
            <td class="tablist"><#if list.moduleType?if_exists==1>普通<#elseif list.moduleType?if_exists==2>受限<#else>开放</#if></td>
            <td class="tablist">${list.url?if_exists?html}</td>
            <td class="tablist"><#if list.parentname?if_exists!="">${list.parentname?substring(1,list.parentname?length)?if_exists?html}</#if></td>
            <td class="tablist"><#if list.forLog?if_exists==1>是<#else>否</#if></td>  
             <td class="tablist">
            <#if list.ifuse?if_exists==1>
            	可用
            <#else>
            	禁用
            </#if>
            </td>       
            <td class="tablist">${list.dispnum?if_exists?html}</td>     
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
