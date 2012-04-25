<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/js/mis/list.js"></script>
<script language="javascript">
		//增加供应商
		function addProvider(){
			document.forms[0].action="${contextPath}/mis/provider/addEditProvider.do";				
			document.forms[0].submit();
		}
				
		//查询商品
		function queryProviders(){
			document.forms[0].action="${contextPath}/mis/provider/listProvider.do";				
			document.forms[0].submit();
		}
		
		//删除供应商
		function deleteProviders(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些供应商吗？")==true){
				document.forms[0].action="${contextPath}/mis/provider/deleteProvider.do";				
				document.forms[0].submit();
			}
		}
		
		function listCheck(selectData){
			var selDataFlg = false;
			var checkCnt = 0;
		
			if(typeof(selectData.length) == "undefined"){
				if(selectData.checked){
					selDataFlg = true;
					checkCnt++;
				}
			}else{
				var iCnt = 0;
				while(iCnt < selectData.length){
					if(selectData[iCnt].checked){
						selDataFlg = true;
						checkCnt++;
					}
					iCnt++;
				}
			}
		
			if(!selDataFlg){
				alert("没有选择供应商，请选择！");
				return false;
			}
		
			return checkCnt;
		}			
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">供应商列表</span>
    </div>
    <div id="container">
	    <div class="addsell">
		   	  <div class="left">			
					<!-- <a href="${contextPath}/mis/provider/addEditProvider.do" target="_self"><img src="${contextPath}/images/btn_add.gif" alt="增加供应商"></a> -->
					<input type="button" onclick="addProvider();" class="btn120x28" style="cursor:pointer" id="addProvider" name="addProvider" value="增加供应商">	
			   	  	<input type="button" onclick="deleteProviders();" class="btn120x28" style="cursor:pointer" id="deleteProvider" name="deleteProvider" value="删除供应商">
			 </div>
	    </div>
    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        	<a href="#" target="_self" onclick="deleteProviders();"><img src="${contextPath}/images/btn_del.gif" alt="删除供应商"></a>				
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>
				-->				
				  供应商名称：<input name="providerName" size="16" id="providerName" type="text" value='${RequestParameters.providerName?default("")}'/>&nbsp;
   	              供应商编号：<input name="providerCode" size="16" id="providerCode" type="text" value='${RequestParameters.providerCode?default("")}'/>&nbsp;   	             
   	              地址：<input name="address" size="16" id="address" type="text" value='${RequestParameters.address?default("")}'/>&nbsp;&nbsp;&nbsp;&nbsp;
   	              帐户：<input name="account" size="16" id="account" type="text" value='${RequestParameters.account?default("")}'/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn48x22" style="cursor:pointer" onclick="queryProviders()" value="查询">
				<!--             
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	             -->
			</div>
	      </div>
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="4%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>
	            <th scope="col" class="tabheader" width="4%">序号</th>
	            <th scope="col" class="tabheader" width="6%">供应商编号</th>
	            <th scope="col" class="tabheader" width="14%">供应商名称</th>
	            <th scope="col" class="tabheader" width="14%">地址</th>
	            <th scope="col" class="tabheader" width="6%">电话</th>
	            <th scope="col" class="tabheader" width="11%">帐户</th>
	            <th scope="col" class="tabheader" width="8%">内容</th>            
	          </tr>
	          
		          <#list listProviders as provider>
					<tr bgcolor="#FFFFFF">
						<td class="tablist"><input type="checkbox" name="id" value='${provider.id?default("")}'/></td>
		            	<td class="tablist">${provider_index}</td><!--获得索引值-->
						<td class="tablist">${provider.providerCode?default("")}</td>
		            	<td class="tablist"><a href="${contextPath}/mis/provider/addEditProvider.do?id=${provider.id?default("")}" class="green" >${provider.providerName?default("")}</a></td>
		            	<td class="tablist">${provider.address?default("")}</td>            	
		            	<td class="tablist">${provider.phone?default("")}</td>
		           		<td class="tablist">${provider.account?default("")}</td>
		            	<td class="tablist">${provider.content?default("")}</td>
					</tr>
				  </#list>
			  	          		           
		</table>
	    </div>
	    <!--列表结束--> 
		<div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        	<a href="#" target="_self" onclick="deleteProviders();"><img src="${contextPath}/images/btn_del.gif" alt="删除供应商"></a>				
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>
				-->	
				<!--             
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	             -->
			</div>     
	    </div>
	</form>
</div>
</body>
</html>
