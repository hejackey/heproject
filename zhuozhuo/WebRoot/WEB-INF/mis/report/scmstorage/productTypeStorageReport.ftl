<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/stock/stockorder.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/report/storage/storage.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
<!--
　　javascript:window.history.forward(1);
-->
</script> 
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listProductTypeStorage.do" class="green">报表中心</a>  > <span class="fb black">商品分类汇总表</span>
    </div>    
    <form name="form1" action="listProductTypeStorage.do" method="post">       
    <div id="container">
     <div class="addsell">  
       <!--  	 
   	  <input type="button" onclick="showMoreQuery()" class="btn120x28" style="cursor:pointer" id="morequery" name="morequery" value="更多查询">  
   	  --> 	  
   	  <input type="button" onclick="expProductTypeStorageToExcel()" class="btn120x28" style="cursor:pointer" id="exportExcel" name="exportExcel" value="导出Excel">
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left ">
   	 	    <input name="productType" size="16" id="productType" type="hidden" value="${model.productType?if_exists}"/>
   	     &nbsp;商品类别编码<input name="productTypeName" size="16" id="productTypeName" onchange="changeProductcode()" type="text" value="${model.productTypeName?if_exists}"/>
   	     <img src="${model.context?if_exists}/images/calendar.gif" onclick="queryProductType('${model.context?if_exists}/mis/productType/qByCodeProductType.do')" style="cursor:pointer;">    	         	                 	                       
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
			<th  scope="col" class="tabheader" width="34%">商品类型</th>
			<th  scope="col" class="tabheader" width="15%">上期结存金额</th>
			<th  scope="col" class="tabheader" width="15%">本期收入金额</th>
			<th  scope="col" class="tabheader" width="15%">本期发出金额</th>
			<th  scope="col" class="tabheader" width="15%">本期结存金额</th>
		  </tr>
		  <#assign xjlastamt=0,xjoutamt=0,xjinamt=0> 
		  <#list model.reportList?if_exists as list>
		  	<#assign xjlastamt=xjlastamt+list.lastAmt?if_exists>
		  	<#assign xjoutamt=xjoutamt+list.outAmt?if_exists>
		  	<#assign xjinamt=xjinamt+list.inAmt?if_exists>		   		 
		  <tr>
		  <td class="tablist">${list_index?if_exists+1}</td>
		  <td class="tablist">		 
		  	${list.productTypeName?if_exists?html}		 
		  </td>
		  <td class="tablist">${list.lastAmt?if_exists?html}</td>
		  <td class="tablist">${list.inAmt?if_exists?html}</td>		  
		  <td class="tablist">${list.outAmt?if_exists?html}</td>
		  <td class="tablist">${(list.lastAmt+list.inAmt+list.outAmt)?if_exists?html}</td>		  		  	 
		  </tr>
		  </#list>   
		  <tr>
			  <td class="tablist" colspan="2">小计</td>
			  <td class="tablist">${xjlastamt?if_exists?html}</td>
			  <td class="tablist">${xjinamt?if_exists?html}</td>		  
			  <td class="tablist">${xjoutamt?if_exists?html}</td>
			  <td class="tablist">${(xjlastamt+xjoutamt+xjinamt)?if_exists?html}</td>		  		  	 
		   </tr>		  	
		   <tr>
			  <td class="tablist" colspan="2">合计</td>			  
			  <td class="tablist">${model.hjLastAmt?if_exists?html}</td>
			  <td class="tablist">${model.hjInAmt?if_exists?html}</td>		  
			  <td class="tablist">${model.hjOutAmt?if_exists?html}</td>
			  <td class="tablist">${(model.hjLastAmt+model.hjInAmt+model.hjOutAmt)?if_exists?html}</td>		  		  	 
		   </tr>	
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
