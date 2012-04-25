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
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">发货单明细(主表单号：${masterId?default("")})</span>
    </div>
    <div id="container">    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->	   	  
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>	            
	            <th scope="col" class="tabheader" width="4%">明细表ID</th>
	            <th scope="col" class="tabheader" width="6%">产品ID</th>
	            <th scope="col" class="tabheader" width="14%">数量</th>
	            <th scope="col" class="tabheader" width="14%">实际价格</th>
	            <th scope="col" class="tabheader" width="6%">单据来源</th>	            
	            <th scope="col" class="tabheader" width="8%">备注</th>            
	          </tr>
	          
		          <#list scmStorageOutDetails as scmStorageOutDetail>
					<tr bgcolor="#FFFFFF">						
		            	<td class="tablist">${scmStorageOutDetail.did?default("")}</td>
						<td class="tablist">${scmStorageOutDetail.productId?default("")}</td>
		            	<td class="tablist">${scmStorageOutDetail.qty?default("")}</td>
		            	<td class="tablist">${scmStorageOutDetail.factSellPrice?default("")}</td>            	
		            	<td class="tablist">${scmStorageOutDetail.sheetIdOriginal?default("")}</td>		           		
		            	<td class="tablist">${scmStorageOutDetail.remark?default("")}</td>
					</tr>
				  </#list>
			  	          		           
		</table>
	    </div>
	    <!--列表结束-->		
	</form>
</div>
</body>
</html>
