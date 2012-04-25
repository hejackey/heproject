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
<script type="text/javascript" src="../js/dept/dept.js"></script>
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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listDept.do" class="green">部门管理</a>  > <span class="fb black">部门列表</span>
    </div>    
    <form name="form1" action="listDept.do" method="post">    
    <input type="hidden" name="q_id" id="q_id" value="${model.q_id?if_exists}">
   
    <input type="hidden" name="q_parentid" id="q_parentid" value="${model.q_parentid?if_exists}">
    <div id="container">
    <div class="addsell">
   	  <div class="left"><input type="button" class="btn120x28" id="tjdept" name="tjdept" onclick="preAddDept('1')" style="cursor:pointer" value="新增同级部门"/>
   	  &nbsp;&nbsp;&nbsp;<input type="button" class="btn120x28" id="xjdept" name="xjdept" onclick="preAddDept('2')" style="cursor:pointer" value="新增下级部门"/>   </div>        
    
    </div>
    <div class="clear"></div>
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	<input type="button" class="btn48x22 fl_left" name="setVisible" id="setVisible" style="cursor:pointer" onclick="updateState(1,'deptid','updateStatusDept')" value="启用"/>
        	<input type="button" class="btn48x22 fl_left" name="setHidden" id="setHidden" style="cursor:pointer" onclick="updateState(2,'deptid','updateStatusDept')" value="禁用"/>
		</div>
		<div class="fl_left">
        	部门编号:<input name="q_depcode" id="q_depcode" type="text" value="${model.q_depcode?if_exists}"/>   
        	&nbsp;&nbsp;&nbsp;&nbsp;部门名称:<input name="q_depname" id="q_depname" type="text" value="${model.q_depname?if_exists}"/>   
        	&nbsp;&nbsp;&nbsp;&nbsp;是否可用:<select name="q_ifuse" id="q_ifuse">
        	<option value="100">请选择</option>
        	<option value="1" <#if model.q_ifuse?if_exists==1>selected</#if>>启用</option>
        	<option value="2" <#if model.q_ifuse?if_exists==2>selected</#if>>禁用</option>
        	</select> 
        	&nbsp;&nbsp;&nbsp;&nbsp;<input class="btn48x22" type="button" style="cursor:pointer" onclick="queryTadmDept()" id="queryDept" value="查询"> 	
        </div>        
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
   <div class="fl_left" style="width:15%">
    	<script type="text/javascript">		
		d = new dTree('${model.context?if_exists}','d');
		d.add(10000,-1,'部门管理','listDept.do');
		<#list model.deptTreeList?if_exists as deptList>
			d.add(${deptList.id?if_exists},${deptList.parentid?if_exists},'${deptList.departmentname?if_exists}','listDept.do?isQuery=n&q_id='+${deptList.id?if_exists}+'&q_parentid='+${deptList.parentid?if_exists});
		</#list>		
		document.write(d);
		</script>
    
    </div>
    <!--列表开始-->
    <div class="selllist fl_left" style="width:85%">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col" class="tabheader nb_left" width="6%"><input type="checkbox" title="全选/取消全选" onclick="selectAll(this.checked,'deptid')" name="allChose" id="allChose" />全选</th>
            <th scope="col" class="tabheader" width="4%" >序号</th>
            <th scope="col" class="tabheader" width="6%">状态</th>
            <th scope="col" class="tabheader" width="10%">部门编号</th>
            <th scope="col" class="tabheader" width="25%">部门名称</th>
            <th scope="col" class="tabheader" width="25%">上级部门名称</th>
            <th scope="col" class="tabheader" width="6%">层级</th>
            <th scope="col" class="tabheader" width="7%">是否末级</th>
            <th scope="col" class="tabheader nb_right" width="11%">备注</th>
          </tr>
          <#list model.listDept?if_exists as list>
          <tr>
            <td class="tablist"><input type="checkbox" title="全选/取消全选" name="deptid" id="deptid" value="${list.id?if_exists}"/></td>
            <td class="tablist">${list_index?if_exists+1}</td>
            <td class="tablist">
            <#if list.ifuse?if_exists==1>
            	可用
            <#else>
            	禁用
            </#if>
            </td>
            <td class="tablist"><a href="#" onclick="preEditDept(${list.id?if_exists?html})" class="green">${list.departmentcode?if_exists?html}</a></td>
            <td class="tablist">${list.departmentname?if_exists?html}</td>
            <td class="tablist"><#if list.parentname?if_exists!="">${list.parentname?substring(1,list.parentname?length)?if_exists?html}</#if></td>
            <td class="tablist">${list.levels?if_exists?html}</td>
            <td class="tablist"><#if list.isleaf?if_exists==1>是<#else>否</#if></td>            
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
<!--
<#if model.result?if_exists=="1">
//alert("状态更新成功");
</#if>
-->
</script>
</body>
</html>
