<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/master.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/report/storage/storage.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/My97DatePicker/WdatePicker.js"></script>

<script language="javascript">
<!--
　　javascript:window.history.forward(1);
-->
</script> 
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listBarnTypeStorage.do" class="green">报表中心</a>  > <span class="fb black">分仓商品汇总表</span>
    </div>    
    <form name="form1" action="listBarnTypeStorage.do" method="post">       
    <div id="container">
     <div class="addsell">  
       <!--  	 
   	  <input type="button" onclick="showMoreQuery()" class="btn120x28" style="cursor:pointer" id="morequery" name="morequery" value="更多查询">  
   	  --> 	  
   	  <input type="button" onclick="expBarnTypeStorageToExcel()" class="btn120x28" style="cursor:pointer" id="exportExcel" name="exportExcel" value="导出Excel">
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left ">
   	 	    仓库名称
   	 	  <select name="barnid" id="barnid">
        	<option value="">请选择</option>  
        	 <#list model.barnTypeList?if_exists as list>                             
              <option value="${list.id?if_exists}" <#if model.barnid?if_exists==list.id?if_exists>selected</#if>>${list.barntypename?if_exists}${list.parentname?if_exists}</option>
            </#list>       	    
          </select> 
   	     &nbsp;仓库编码<input name="barntypecode" size="14" id="barntypecode" type="text" value="${model.barntypecode?if_exists}"/>    	                 	                        
   	     &nbsp;开始日期<input name="startTime" class="Wdate" size="14" id="startTime" type="text" value="${model.startTime?if_exists}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>   	                    
   	     &nbsp;结束日期<input name="endTime" class="Wdate" size="14" id="endTime" type="text" value="${model.endTime?if_exists}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
   	   
         <input type="submit" class="btn48x22" style="cursor:pointer" id="queryscmstorage" value="查询"> 	
        </div>          
      </div>
      <!--
      <div class="tooltop nowarp" style="display:none" id="more_query_div">
   	    <div class="left">
   	 	仓库编码<input type="text" size="10" name="barntypecode" id="barntypecode" value="${model.barntypecode?if_exists}">        	 
        </div>        
      </div>-->
      <div class="clear"></div>
      
    <!--上边功能区结束-->
   
    <!--列表开始-->
    <div class="selllist fl_left" style="width:100%">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    	   <tr>
		    <th  scope="col" class="tabheader" width="6%">序号</th>
			<th  scope="col" class="tabheader" width="24%">仓库名称</th>
			<th  scope="col" class="tabheader" width="24%">商品名称</th>
			<th  scope="col" class="tabheader" width="14%">商品编码</th>
			<th  scope="col" class="tabheader" width="6%">商品规格</th>
			<th  scope="col" class="tabheader" width="6%">单位</th>
			<th  scope="col" class="tabheader" width="10%">数量</th>
			<th  scope="col" class="tabheader" width="10%">金额</th>
		  </tr>
		  <#list model.reportList?if_exists as list>
		  <tr>
		  <td class="tablist">${list_index?if_exists+1}</td>
		  <td class="tablist">
		  <#if list.barntypename?if_exists=="">
		     合计
		  <#else>
		  	<#if list.productname?if_exists=="">
		  		小计
		  	<#else>
		  		${list.barntypename?if_exists?html}
		  	</#if>
		  </#if>
		  </td>
		  <td class="tablist">${list.productname?if_exists?html}</td>
		  <td class="tablist">${list.productcode?if_exists?html}</td>		  
		  <td class="tablist">${list.productspecs?if_exists?html}</td>
		  <td class="tablist">${list.productunit?if_exists?html}</td>		  		  
		  <td class="tablist">${list.qty?if_exists?html}</td>
		  <td class="tablist">${list.amt?if_exists?html}</td>		 
		  </tr>
		  </#list>   
		  
</table>

    </div>
    <div class="clear"></div>
    <!--列表结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	   
  <div class="pages fl_left">
  	        <#include "../../inc/page.ftl">
        	
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
    </form>
</div>

</body>
</html>
