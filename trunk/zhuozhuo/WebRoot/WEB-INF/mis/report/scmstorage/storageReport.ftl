<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/master.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/stock/stockorder.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/report/storage/storage.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/jquery-1.3.2.min.js"></script>
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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listScmStorage.do" class="green">报表中心</a>  > <span class="fb black">进销存商品汇总表</span>
    </div>    
    <form name="form1" action="listScmStorage.do" method="post">       
    <div id="container">
     <div class="addsell">  
       <!--  	 
   	  <input type="button" onclick="showMoreQuery()" class="btn120x28" style="cursor:pointer" id="morequery" name="morequery" value="更多查询">  
   	  --> 	  
   	  <input type="button" onclick="expStorageToExcel()" class="btn120x28" style="cursor:pointer" id="exportExcel" name="exportExcel" value="导出Excel">
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left ">
   	 	    商品名称<input name="productname" size="14" id="productname" type="text" value="${model.productname?if_exists}"/> 
   	               &nbsp;商品编码<input name="productcode" size="14" id="productcode" type="text" value="${model.productcode?if_exists}"/>    	                 	                        
   	               &nbsp; 商品类别<input name="productTypeName" size="14" id="productTypeName" onchange="changeProductcode()" type="text" value="${model.productTypeName?if_exists}"/>
   	               <input name="productType" size="12" id="productType" type="hidden" value="${model.productType?if_exists}"/> 
   	               <img src="${model.context?if_exists}/images/calendar.gif" onclick="queryProductType('../../mis/productType/qByCodeProductType.do')" style="cursor:pointer;">    
   	               &nbsp;开始日期<input name="startTime" class="Wdate" size="14" id="startTime" type="text" value="${model.startTime?if_exists}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
   	                    
   	              &nbsp;结束日期<input name="endTime" class="Wdate" size="14" id="endTime" type="text" value="${model.endTime?if_exists}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
   	    <!--           
   	             仓库名称
   	     <select name="barnid" id="barnid">
        	<option value="0">请选择</option>  
        	 <#list model.barnTypeList?if_exists as list>                             
              <option value="${list.id?if_exists}" <#if model.barnid?if_exists==list.id?if_exists>selected</#if>>${list.barntypename?if_exists}${list.parentname?if_exists}</option>
            </#list>       	    
         </select>--> 
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
		    <th rowspan="2" scope="col" class="tabheader" width="6%">序号</th>
		    <th colspan="3" scope="col" class="tabheader" width="30%">货品信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">采购信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">销售信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">总库存信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">入库信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">发库信息</th>
		    <th colspan="2" scope="col" class="tabheader" width="10%">退货信息</th>
		  </tr>
		  <tr>
		    <th scope="col" class="tabheader" width="10%">商品编码</th>
		    <th scope="col" class="tabheader" width="10%">商品名称</th>
		    <th scope="col" class="tabheader" width="10%">商品类型</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		    <th scope="col" class="tabheader" width="5%">数量</th>
		    <th scope="col" class="tabheader" width="5%">金额</th>
		  </tr>            
		  <#assign xjcgqty=0,xjcgamt=0,xjxsqty=0,xjxsamt=0,xjrkqty=0,xjrkamt=0,xjckqty=0,xjckamt=0,xjthqty=0,xjthamt=0,xjhjqty=0,xjhjamt=0>
		  <#list model.reportList?if_exists as list>
		  <tr>
		  <td class="tablist">${list_index?if_exists+1}</td>
		  <td class="tablist">${list.productcode?if_exists?html}</td>
		  <td class="tablist">${list.productname?if_exists?html}</td>
		  <td class="tablist">${list.productTypeName?if_exists?html}</td>
		  <td class="tablist">${list.cgqty?if_exists?html}</td>		  
		  <td class="tablist">${list.cgamt?if_exists?html}</td>
		  <td class="tablist">${list.xsqty?if_exists?html}</td>
		  <td class="tablist">${list.xsamt?if_exists?html}</td>
		  <td class="tablist">${list.hjqty?if_exists?html}</td>
		  <td class="tablist">${list.hjamt?if_exists?html}</td>
		  <td class="tablist">${list.rkqty?if_exists?html}</td>
		  <td class="tablist">${list.rkamt?if_exists?html}</td>
		  <td class="tablist">${list.ckqty?if_exists?html}</td>
		  <td class="tablist">${list.ckamt?if_exists?html}</td>
		  <td class="tablist">${list.thqty?if_exists?html}</td>
		  <td class="tablist">${list.thamt?if_exists?html}</td>		
		  <#assign xjcgqty=xjcgqty+list.cgqty?if_exists>
		  <#assign xjcgamt=xjcgamt+list.cgamt?if_exists>
		  <#assign xjxsqty=xjxsqty+list.xsqty?if_exists>
		  <#assign xjxsamt=xjxsamt+list.xsamt?if_exists>
		  <#assign xjhjqty=xjhjqty+list.hjqty?if_exists>
		  <#assign xjhjamt=xjhjamt+list.hjamt?if_exists>
		  <#assign xjrkqty=xjrkqty+list.rkqty?if_exists>
		  <#assign xjrkamt=xjrkamt+list.rkamt?if_exists>
		  <#assign xjckqty=xjckqty+list.ckqty?if_exists>
		  <#assign xjckamt=xjckamt+list.ckamt?if_exists>
		  <#assign xjthqty=xjthqty+list.thqty?if_exists>
		  <#assign xjthamt=xjthamt+list.thamt?if_exists>
		  </tr>
		  </#list>   
		  <tr>
			  <td class="tablist">小计</td>
			  <td class="tablist" colspan="3">&nbsp;</td>
			  <td class="tablist">${xjcgqty?if_exists}</td>
			  <td class="tablist">${xjcgamt?if_exists}</td>
			  <td class="tablist">${xjxsqty?if_exists}</td>
			  <td class="tablist">${xjxsamt?if_exists}</td>
			  <td class="tablist">${xjhjqty?if_exists}</td>
			  <td class="tablist">${xjhjamt?if_exists}</td>
			  <td class="tablist">${xjrkqty?if_exists}</td>
			  <td class="tablist">${xjrkamt?if_exists}</td>
			  <td class="tablist">${xjckqty?if_exists}</td>
			  <td class="tablist">${xjckamt?if_exists}</td>
			  <td class="tablist">${xjthqty?if_exists}</td>
			  <td class="tablist">${xjthamt?if_exists}</td>
		  </tr>
		  <tr>
			<td class="tablist">合计</td>
			<td class="tablist" colspan="3">&nbsp;</td>
			<td class="tablist">${model.hjcgqty?if_exists}</td>
			<td class="tablist">${model.hjcgamt?if_exists}</td>
			<td class="tablist">${model.hjxsqty?if_exists}</td>
			<td class="tablist">${model.hjxsamt?if_exists}</td>
			<td class="tablist">${model.hjzqty?if_exists}</td> 
			<td class="tablist">${model.hjzamt?if_exists}</td> 
			<td class="tablist">${model.hjrkqty?if_exists}</td>
			<td class="tablist">${model.hjrkamt?if_exists}</td>
			<td class="tablist">${model.hjckqty?if_exists}</td>
			<td class="tablist">${model.hjckamt?if_exists}</td>
			<td class="tablist">${model.hjthqty?if_exists}</td>
			<td class="tablist">${model.hjthamt?if_exists}</td>
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
