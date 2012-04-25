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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">商品管理</a>  > <span class="fb black">商品列表</span>
    </div>
    <div id="container">
	    
	    <form name="form1" action="qByProductCodeProductInfoJxc.do" method="post">
	    <!--上边功能区开始-->
	   	<input type="hidden" id="seq" name="seq" value="${model.seq?if_exists}">
	   	<input type="hidden" id="productCode" name="productCode" value="${model.productCode?if_exists}">
	   	<input type="hidden" id="querytype" name="querytype" value="${model.querytype?if_exists}">
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            
	            <th scope="col" class="tabheader" width="4%">序号</th>
	            <th scope="col" class="tabheader" width="6%">商品编号</th>
	            <th scope="col" class="tabheader" width="14%">商品名称</th>
	            <th scope="col" class="tabheader" width="14%">商品规格</th>
	            <th scope="col" class="tabheader" width="6%">商品进价</th>
	            <th scope="col" class="tabheader" width="11%">商品条形码</th>
	            <th scope="col" class="tabheader" width="8%">备注</th>            
	          </tr>
	          
		          <#list model.productList?if_exists as product>
					<tr bgcolor="#FFFFFF">						
		            	<td class="tablist">${product_index+1}</td><!--获得索引值-->
						<td class="tablist" id="code_${product.id?if_exists}">${product.productCode?default("")}</td>
		            	<td class="tablist" style="cursor:pointer" ondblclick="choseProduct('${product.id?if_exists}')" id="name_${product.id?if_exists}">${product.productName?default("")}</td>
		            	<td class="tablist" id="specs_${product.id?if_exists}">${product.productSpecs?default("")}</td>            	
		            	<td class="tablist" id="purchase_${product.id?if_exists}">${product.pricePurchase?default("")}</td>
		           		<td class="tablist" id="productentry_${product.id?if_exists}">${product.productentry?default("")}</td>
		            	<td class="tablist" id="remark_${product.id?if_exists}">${product.remark?default("")}</td>
					</tr>
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
