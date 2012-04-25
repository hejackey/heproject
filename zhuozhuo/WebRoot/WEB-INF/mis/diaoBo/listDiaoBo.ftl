<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="../css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<link href="../css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="../js/DiaoBo/DiaoBo.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/string.js"></script>

</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="listDiaoBo.do" class="green">调拨单列表</a>  > <span class="fb black">调拨单列表</span>
    </div>    
    <form name="form1" action="listDiaoBo.do" method="post">       
    <div id="container">
     <div class="addsell">
   	  <div class="left"><input type="button" onclick="preAddDiaoBo(1)" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="新增调拨单">   	  
   	  <!--<input type="button" onclick="preAddDiaoBo(2)" class="btn120x28" style="cursor:pointer" id="addthsheet" name="addthsheet" value="新增退货单">-->
   	  <input type="button" onclick="updateState(1,'aid','updateStatusDiaoBo')" class="btn120x28" style="cursor:pointer" id="checksheet" name="checksheet" value="审核单据">
   	  <input type="button" onclick="showMoreQuery()" class="btn120x28" style="cursor:pointer" id="morequery" name="morequery" value="更多查询">
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left ">
   	 	    单据编号<input name="qsheetid" size="16" id="qsheetid" type="text" value="${model.qsheetid?if_exists}"/> 
   	               &nbsp;商品名称<input name="productName" size="16" id="productName" type="text" value="${model.productName?if_exists}"/>
   	             <input name="productType" size="16" id="productType" type="hidden" value="${model.productType?if_exists}"/>
   	               &nbsp;商品类别编码<input name="productTypeName" size="16" id="productTypeName" onchange="changeProductcode()" type="text" value="${model.productTypeName?if_exists}"/> 
   	               <img src="../images/calendar.gif" onclick="queryProductType('../mis/productType/qByCodeProductType.do')" style="cursor:pointer;">    
   	               &nbsp;单据来源编号<input name="srcsheetid" size="16" id="srcsheetid" type="text" value="${model.srcsheetid?if_exists}"/>            
   	              &nbsp;供应商<input type="text" size="16" name="providerName" id="providerName" value="${model.providerName?if_exists}">
   	              &nbsp;单据金额<input type="text" size="10" name="minAmt" id="minAmt" value="${model.minAmt?if_exists}">  
   	              到
   	     <input type="text" size="10" name="maxAmt" id="maxAmt" value="${model.maxAmt?if_exists}">            
		 
        	<input type="button" class="btn48x22" style="cursor:pointer" onclick="queryDiaoBo()" id="queryDiaoBo" value="查询"> 	
        </div>        
      </div>
      <div class="tooltop nowarp" style="display:none" id="more_query_div">
   	    <div class="left">
   	 	 单据状态<select name="sheetstate" id="sheetstate">  
            <option value="0">请选择</option>    
	        <option value="1" <#if model.sheetstate?if_exists==1>selected</#if>>已审核</option>
	        <option value="2" <#if model.sheetstate?if_exists==2>selected</#if>>未审核</option>                                 
            </select>           
		   &nbsp;&nbsp;&nbsp;&nbsp;经办人<select name="userid" id="userid">  
            <option value="0">请选择</option>    
            <#list model.userList?if_exists as list>            
              <option value="${list.id?if_exists}" <#if model.userid?if_exists==list.id?if_exists>selected</#if>>              
              ${list.name?if_exists?html}          
              </option>
            </#list>                        
            </select>         	 
        </div>        
      </div>
      <div class="clear"></div>
      
    <!--上边功能区结束-->
   
    <!--列表开始-->
    <div class="selllist fl_left" style="width:100%">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th scope="col" class="tabheader nb_left" width="9%"><input type="checkbox" title="全选/取消全选" onclick="selectAll(this.checked,'aid')"  name="chosesheetid" id="chosesheetid"/>全选</th>  
            <th scope="col" class="tabheader nb_left" width="11%">单据编号</th>                       
            <th scope="col" class="tabheader" width="12%">供应商</th>
            <th scope="col" class="tabheader" width="10%">经办人</th>                        
            <th scope="col" class="tabheader" width="5%">本单金额</th>
            <th scope="col" class="tabheader" width="5%">制单人</th>
            <th scope="col" class="tabheader" width="6%">单据状态</th>
            <th scope="col" class="tabheader" width="10%">单据来源编号</th>
            <th scope="col" class="tabheader" width="13%">开单日期</th>  
            <th scope="col" class="tabheader" width="7%">支付日期</th>  
            <th scope="col" class="tabheader" width="7%">预计交货日期</th>
            <th scope="col" class="tabheader" width="7%">交货日期</th>            
          </tr>
          <#list model.diaoBoList?if_exists as list>
          <tr>
            <td class="tablist">
            <input type="checkbox" title="全选/取消全选" name="aid" id="aid" value="${list.id?if_exists}"/>
            ${list_index?if_exists+1}&nbsp;&nbsp;<span id="zk${list.id?if_exists?html}" style="cursor:pointer" onclick="showDiaoBoDetail('${list.id?if_exists?html}','${list.sheetid?if_exists?html}')" title="1">+展开</span></td>
            <td class="tablist"><a href="#" onclick="preEditDiaoBo('${list.id?if_exists?html}')" class="green">${list.sheetid?if_exists?html}</a></td>
            <td class="tablist">${list.providerName?if_exists}</td>
            <td class="tablist">${list.userName?if_exists}</td>
            <td class="tablist">${list.sumamt?if_exists}</td>
            <td class="tablist">${list.makerName?if_exists}</td>
            <td class="tablist"><#if list.sheetstate?if_exists==1>已审核<#else>未审核</#if></td>
            <td class="tablist">${list.srcsheetid?if_exists}</td>
            <td class="tablist">${list.createdate?if_exists}</td>
            <td class="tablist">${list.paydate?if_exists}</td>     
            <td class="tablist">${list.pretodate?if_exists?html}</td>
            <td class="tablist">${list.todate?if_exists?html}</td>                              
          </tr>
	          <tr id="detail${list.id?if_exists?html}" style="display:none">
	          <td>&nbsp;</td>
	          <td colspan="10">
		          <table id="detail_table_${list.id?if_exists?html}">
		                
		          </table>
	          </td>
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
