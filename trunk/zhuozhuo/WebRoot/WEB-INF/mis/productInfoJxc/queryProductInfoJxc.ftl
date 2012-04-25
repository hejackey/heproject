<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">商品分类综合查询</span>
    </div>
    <div id="container">	    
	    <div class="clear"></div>    
			    <!--详细开始-->
			    <form action="${contextPath}/mis/productInfoJxc/queryProductInfoJxc.do" method="post">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">商品名称</td>
			            <td width="35%">			              
			                <@spring.formInput "command.productName" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品编码</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productCode" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">添加人员</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.createBy" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品类型</td>
			            <td width="35%">
			                <@spring.formInput "command.productType" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>			                                       
			          <tr>
			            <td align="right" colspan="10" height="40">
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_define.gif" /> 
			            </td>
			          </tr>
			        </table>			        
			    </div>
			    </form>
			    <!--详细结束-->
			    <#if productInfoJxcs?exists>
				    <div class="selllist">
				    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>			            
				            <th scope="col" class="tabheader" width="4%">序号</th>
				            <th scope="col" class="tabheader" width="6%">商品编码</th>
				            <th scope="col" class="tabheader" width="14%">商品名称</th>
				            <th scope="col" class="tabheader" width="14%">添加人员</th>
				            <th scope="col" class="tabheader" width="6%">销售价</th>
				            <th scope="col" class="tabheader" width="11%">市场价</th>
				            <th scope="col" class="tabheader" width="8%">生产日期</th>            
				          </tr>		  
						  <#list productInfoJxcs as productInfoJxc>
							<tr bgcolor="#FFFFFF">							
				            	<td class="tablist">${productInfoJxc_index}</td><!--获得索引值-->
								<td class="tablist">${productInfoJxc.productCode?default("")}</td>
				            	<td class="tablist"><a href="${contextPath}/mis/productInfoJxc/addorEditProductInfoJxc.do?id=${productInfoJxc.id?default('')}">${productInfoJxc.productName?default("")}</a></td>
				            	<td class="tablist">${productInfoJxc.createBy?default("")}</td>            	
				            	<td class="tablist">${productInfoJxc.priceNet?default("")}</td>
				           		<td class="tablist">${productInfoJxc.priceMarket?default("")}</td>
				            	<td class="tablist">
				            						<#if productInfoJxc.produceDate?if_exists!="">
				            							${productInfoJxc.produceDate?substring(0,10)}
				            						</#if>
				            	</td>
							</tr>
						  </#list>         
						</table>
		   		   </div>
	   		   </#if>			   
   	</div>
</div>
</body>
</html>
