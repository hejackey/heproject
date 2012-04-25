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
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/string.js"></script>
<script type="text/javascript" src="${contextPath}/js/stock/stockorder.js"></script>
<script language="javascript">	

		//增加商品
		function addProduct(){			
			document.forms[0].action="${contextPath}/mis/productInfoJxc/addorEditProductInfoJxc.do";				
			document.forms[0].submit();
		}
	
		//查询商品
		function queryProduct(){			
			document.forms[0].action="${contextPath}/mis/productInfoJxc/listProductInfoJxc.do";				
			document.forms[0].submit();
		}
		
		//删除商品
		function delProductInfoJxcs(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些商品吗？")==true){
				document.forms[0].action="${contextPath}/mis/productInfoJxc/deleteProductInfoJxc.do";				
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
				alert("没有选择商品，请选择！");
				return false;
			}
		
			return checkCnt;
		}		
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">商品列表</span>
    </div>
    <div id="container">
	    <div class="addsell">
		   	  <div class="left">			
					<!-- <a href="${contextPath}/mis/productInfoJxc/addorEditProductInfoJxc.do" target="_self"><img src="${contextPath}/images/btn_add.gif" alt="增加商品"></a> -->
					<input type="button" onclick="addProduct();" class="btn120x28" style="cursor:pointer" id="addProductInfoJxc" name="addProductInfoJxc" value="增加商品">	
			   	  	<input type="button" onclick="delProductInfoJxcs();" class="btn120x28" style="cursor:pointer" id="deleteProductInfoJxc" name="deleteProductInfoJxc" value="删除商品">
			 </div>
	    </div>
    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--	   	    	
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        	<a href="#" target="_self" onclick="delProductInfoJxcs();"><img src="${contextPath}/images/btn_del.gif" alt="删除商品"></a>				
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>
				-->				
				<input name="productType" size="16" id="productType" type="hidden" value='${RequestParameters.productType?default("")}'/>
				商品名称：<input name="productName" size="16" id="productName" type="text" value='${RequestParameters.productName?default("")}'/>&nbsp;   	                 	             
   	              商品类别编码：<input name="productTypeName" size="16" id="productTypeName" onchange="changeProductcode()" type="text" value='${RequestParameters.productTypeName?default("")}'/> 
   	            <img src="${contextPath}/images/calendar.gif" onclick="queryProductType('${contextPath}/mis/productType/qByCodeProductType.do')" style="cursor:pointer;"> &nbsp;&nbsp;
   	              商品编码：<input name="productCode" size="16" id="productCode" type="text" value='${RequestParameters.productCode?default("")}'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	            <input type="button" class="btn48x22" style="cursor:pointer" onclick="queryProduct()" id="querystockorder" value="查询"> 
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
	            <th scope="col" class="tabheader" width="6%">商品编码</th>
	            <th scope="col" class="tabheader" width="14%">商品名称</th>
	            <th scope="col" class="tabheader" width="14%">商品等级</th>
	            <th scope="col" class="tabheader" width="6%">商品单位</th>
	            <th scope="col" class="tabheader" width="11%">产品规格</th>
	            <th scope="col" class="tabheader" width="8%">上传商品图片</th>            
	          </tr>	
	          <#list productInfoJxcList as productInfoJxc>
				<tr bgcolor="#FFFFFF">
					<td class="tablist"><input type="checkbox" name="id" value='${productInfoJxc.id?default("")}'/></td>
	            	<td class="tablist">${productInfoJxc_index}</td><!--获得索引值-->
					<td class="tablist">${productInfoJxc.productCode?default("")}</td>
	            	<td class="tablist"><a href="${contextPath}/mis/productInfoJxc/addorEditProductInfoJxc.do?id=${productInfoJxc.id?default("")}" class="green" >${productInfoJxc.productName?default("")}</a></td>
	            	<td class="tablist">${productInfoJxc.productClass?default("")}</td>            	
	            	<td class="tablist">${productInfoJxc.productUnit?default("")}</td>
	           		<td class="tablist">${productInfoJxc.productSpecs?default("")}</td>
	            	<td class="tablist"><a href="${contextPath}/mis/productInfoJxc/uploadImageProductInfoJxc.do?id=${productInfoJxc.id?default("")}">上传${productInfoJxc.productName?default("")}图片</a></td>
				</tr>
			  </#list>		           
		</table>
	    </div>
	    <!--列表结束--> 
		<div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        	<a href="#" target="_self" onclick="delProductInfoJxcs();"><img src="${contextPath}/images/btn_del.gif" alt="删除商品"></a>				
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
