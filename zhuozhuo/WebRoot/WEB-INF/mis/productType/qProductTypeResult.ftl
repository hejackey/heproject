<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>

<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${model.context?if_exists}/js/mis/list.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/stock/stockorder.js"></script>
<script type="text/javascript" src="${model.context?if_exists}/js/jquery-1.3.2.min.js"></script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">商品类别管理</a>  > <span class="fb black">商品类别列表</span>
    </div>
    <div id="container">
	    
	    <form name="form1" action="qByCodeProductType.do" method="post">
	    <!--上边功能区开始-->
	   	<input type="hidden" id="providerName" name="providerName" value="${model.providerName?if_exists}">
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            
	            <th scope="col" class="tabheader" width="10%">序号</th>
	            <th scope="col" class="tabheader" width="50%">商品类别编号</th>
	            <th scope="col" class="tabheader" width="40%">商品类别名称</th>
	                     
	          </tr>
	          
		          <#list model.productTypeList?if_exists as list>
					<tr bgcolor="#FFFFFF">						
		            	<td class="tablist">${list_index+1}</td><!--获得索引值-->						
		            	<td class="tablist" style="cursor:pointer" ondblclick="choseProductType('${list.recordId?if_exists}')" id="name_${list.recordId?if_exists}">${list.goodsTypeCode?default("")}</td>
		            	<td class="tablist">${list.goodsTypeName?default("")}</td>
		            <tr>
				  </#list>
			  	          		           
		</table>
	    </div>
	    <div class="clear"></div>
	    <!--列表结束--> 
		 <div class="tooltop nowarp">   	   
  			<div class="pages fl_left">
  	           <#include "../inc/page.ftl">        	
        	</div>
      		</div>
      	<div class="clear"></div>
	</form>
</div>
</body>
</html>
