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
			    <form action="${contextPath}/mis/productType/queryProductType.do" method="post">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">父节点名称</td>
			            <td width="35%">			              
			                <@spring.formInput "command.parentName" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">货品类别编码</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.goodsTypeCode" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">货品类别名称</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.goodsTypeName" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">货品级别</td>
			            <td width="35%">
			                <@spring.formInput "command.classLevel" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>			                                       
			          <tr>
			            <td align="right" colspan="10" height="40">
			            	<!-- 
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_define.gif" /> 
			                 -->
			                 <input type="submit" value="保 存" >
			            </td>
			          </tr>
			        </table>			        
			    </div>
			    </form>
			    <!--详细结束-->
			   <#if productTypes?exists>
				    <div class="selllist">
				    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>			            
				            <th scope="col" class="tabheader" width="4%">序号</th>
				            <th scope="col" class="tabheader" width="6%">部品类别编码</th>
				            <th scope="col" class="tabheader" width="14%">商品类别名称</th>
				            <th scope="col" class="tabheader" width="14%">父节点名称</th>
				            <th scope="col" class="tabheader" width="6%">商品类别层级</th>
				            <th scope="col" class="tabheader" width="11%">自定义</th>
				            <th scope="col" class="tabheader" width="8%">备注</th>            
				          </tr>		  
						  <#list productTypes as product>
							<tr bgcolor="#FFFFFF">							
				            	<td class="tablist">${product_index}</td><!--获得索引值-->
								<td class="tablist">${product.goodsTypeCode?default("")}</td>
				            	<td class="tablist"><a href="${contextPath}/mis/productType/editProductType.do?recordId=${product.recordId?default("")}">${product.goodsTypeName?default("")}</a></td>
				            	<td class="tablist">${product.parentName?default("")}</td>            	
				            	<td class="tablist">${product.classLevel?default("")}</td>
				           		<td class="tablist">${product.define1?default("")}</td>
				            	<td class="tablist">${product.remark?default("")}</td>
							</tr>
						  </#list>         
						</table>
		   		   </div>
	   		   </#if>
   	</div>
</div>
</body>
</html>
