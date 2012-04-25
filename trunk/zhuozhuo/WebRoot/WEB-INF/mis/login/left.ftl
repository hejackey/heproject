<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
</head>

<body bgcolor="#f2f2f2">
<div id="leftmenu">
    <h1 class="fb f14">系统功能区</h1>
    <ul id="meun" class=" f14">
    <#assign i=0>
    <#list model.role.moduleList?if_exists as list>
    	<#if list.parentid?if_exists=="">
    		<#list model.role.rolePrivList?if_exists as rolePriv>    
    				<#assign priv=rolePriv?substring(rolePriv?index_of(":")+1)>    				
    				
    				<#if priv?substring(priv?index_of(":")+1)?if_exists==list.id?if_exists>
    					<#assign i=0>
    					<li><span class="n01">${list.moduleName?if_exists?html}</span></li>
    					<#break>    					
    				</#if>
    			  
	        </#list>       		
        <#else>
       		<#list model.role.rolePrivList?if_exists as rolePriv>            		           
			    <#if rolePriv?if_exists!="" && rolePriv?if_exists=="L:${list.id?if_exists}:${list.parentid?if_exists}">
			       <#assign i=i+1>
		           <li class="f12"><a href="${model.context?if_exists}/${list.url?if_exists?html}" target="mainFrame">${list.moduleName?if_exists?html}</a></li>
		        </#if>
	        </#list>	       	       
        </#if>
    </#list>
    <!--
        <li>
            <a href="#" class="aa"><span class="n01">基础数据管理</span></a>
            <ul class="f12">
            <li><a href="mis/productType/listProductType.do" target="mainFrame">商品分类</a></li>
                <li><a href="mis/productType/queryProductType.do" target="mainFrame">商品分类综合查询</a></li>               
                <li><a href="mis/productInfoJxc/listProductInfoJxc.do" target="mainFrame">商品管理</a></li>
                <li><a href="mis/productInfoJxc/queryProductInfoJxc.do" target="mainFrame">商品综合查询</a></li>
                <li><a href="mis/provider/listProvider.do" target="mainFrame">供应商管理</a></li>
                <li><a href="mis/provider/queryProvider.do" target="mainFrame">供应商综合查询</a></li>
                <li><a href="mis/providerType/listProviderType.do" target="mainFrame">供应商类别管理</a></li>
                <li><a href="mis/scmBarnType/listScmBarnType.do" target="mainFrame">仓库管理</a></li>
                <li><a href="mis/scmStorageOut/listScmStorageOut.do" target="mainFrame">发货单管理</a></li>
                <li><a href="mis/scmPay/listScmPay.do" target="mainFrame">付款单管理</a></li>
                <li><a href="mis/scmStorageIn/listScmStorageIn.do?type=enterWarehouse" target="mainFrame">入库单管理</a></li>
                <li><a href="mis/scmStorageIn/listScmStorageIn.do?type=returnedPurchase" target="mainFrame">退货单管理</a></li>
                <li><a href="dept/listDept.do?type=navigate" target="mainFrame">部门管理</a></li>
                <li><a href="module/listModule.do?type=navigate" target="mainFrame">模块管理</a></li>
                <li class="bb"><a href="user/listUser.do?type=navigate" target="mainFrame">用户管理</a></li>
                <li><a href="role/listRole.do?type=navigate" target="mainFrame">角色权限管理</a></li>
                <li><a href="stock/listStockOrder.do?type=navigate" target="mainFrame">采购单管理</a></li>
                <li><a href="report/storage/listScmStorage.do" target="mainFrame">进销存商品汇总表</a></li>
                <li><a href="report/storage/listBarnTypeStorage.do" target="mainFrame">分仓商品汇总表</a></li>
                <li><a href="report/storage/listProductTypeStorage.do" target="mainFrame">商品类型汇总表</a></li>
            </ul>           
        </li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        <li><a href="#"><span class="n01">菜单名称</span></a></li>
        -->
    </ul>
<div class="clear"></div>    
</div>
</body>
</html>
