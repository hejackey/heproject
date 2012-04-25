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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">供应商管理</a>  > <span class="fb black">供应商列表</span>
    </div>
    <div id="container">
	    
	    <form name="form1" action="qByNameProvider.do" method="post">
	    <!--上边功能区开始-->
	   	<input type="hidden" id="providerName" name="providerName" value="${model.providerName?if_exists}">
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            
	            <th scope="col" class="tabheader" width="4%">序号</th>
	            <th scope="col" class="tabheader" width="6%">供应商编号</th>
	            <th scope="col" class="tabheader" width="14%">供应商名称</th>
	            <th scope="col" class="tabheader" width="14%">地址</th>
	            <th scope="col" class="tabheader" width="6%">电话</th>
	            <th scope="col" class="tabheader" width="11%">帐户</th>
	            <th scope="col" class="tabheader" width="8%">联系人</th>            
	          </tr>
	          
		          <#list model.providerList?if_exists as provider>
					<tr bgcolor="#FFFFFF">						
		            	<td class="tablist">${provider_index+1}</td><!--获得索引值-->
						<td class="tablist">${provider.providerCode?default("")}</td>
		            	<td class="tablist" style="cursor:pointer" ondblclick="choseProvider('${provider.id?if_exists}')" id="name_${provider.id?if_exists}">${provider.providerName?default("")}</td>
		            	<td class="tablist" id="address_${provider.id?if_exists}">${provider.address?default("")}</td>            	
		            	<td class="tablist" id="phone_${provider.id?if_exists}">${provider.phone?default("")}</td>
		           		<td class="tablist" id="account_${provider.id?if_exists}">${provider.account?default("")}</td>
		            	<td class="tablist" id="businessMan_${provider.id?if_exists}">${provider.businessMan?default("")}</td>
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
