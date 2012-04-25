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
			    <form action="${contextPath}/mis/provider/queryProvider.do" method="post">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">供应商名称</td>
			            <td width="35%">			              
			                <@spring.formInput "command.providerName" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">供应商编号</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.providerCode" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">地址</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.address" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">帐户</td>
			            <td width="35%">
			                <@spring.formInput "command.account" />
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
			    
			    <#if providers?exists>
				    <div class="selllist">
				    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>			            
				            <th scope="col" class="tabheader" width="4%">序号</th>
				            <th scope="col" class="tabheader" width="6%">供应商编号</th>
				            <th scope="col" class="tabheader" width="14%">供应商名称</th>
				            <th scope="col" class="tabheader" width="14%">地址</th>
				            <th scope="col" class="tabheader" width="6%">电话</th>
				            <th scope="col" class="tabheader" width="11%">帐户</th>
				            <th scope="col" class="tabheader" width="8%">内容</th>            
				          </tr>		  
						  <#list providers as provider>
							<tr bgcolor="#FFFFFF">							
				            	<td class="tablist">${provider_index}</td><!--获得索引值-->
								<td class="tablist">${provider.providerCode?default("")}</td>
				            	<td class="tablist"><a href="${contextPath}/mis/provider/addEditProvider.do?id=${provider.id?default('')}">${provider.providerName?default("")}</a></td>
				            	<td class="tablist">${provider.address?default("")}</td>            	
				            	<td class="tablist">${provider.phone?default("")}</td>
				           		<td class="tablist">${provider.account?default("")}</td>
				            	<td class="tablist">${provider.content?default("")}</td>
							</tr>
						  </#list>         
						</table>
		   		   </div>
	   		   </#if>
			    
   	</div>
</div>
</body>
</html>
