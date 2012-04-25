<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />
<link href="../../css/dtree/dtree.css" rel="StyleSheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/js/mis/list.js"></script>
<script type="text/javascript" src="${contextPath}/js/dtree/dtree.js"></script>
<script type="text/javascript" src="${contextPath}/js/productType/productType.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/string.js"></script>
<script language="javascript">
		
		//删除商品类别
		function delProductTypes(){
			var checkCnt = listCheck(document.forms[0].recordId);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些商品分类吗？")==true){
				document.forms[0].action="${contextPath}/mis/productType/deleteProductType.do";				
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
				alert("没有选择商品类别，请选择！");
				return false;
			}		
			return checkCnt;
		}		
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">商品类别列表</span>
    </div>
    <form action=""  method="post">
    <input type="hidden" name="recordId" id="recordId" value="${productType.recordId?if_exists}">   
    <input type="hidden" name="parentId" id="parentId" value="${productType.parentId?if_exists}">
    <input type="hidden" name="goodsTypeName" id="goodsTypeName" value="${productType.goodsTypeName?if_exists}">
    <div id="container">
    	<!--
	    <div class="addsell">
	   	  	<div class="left"><img id="tjdept" name="tjProductType" onclick="preAddProductType('1')" src="../../images/btn_add.gif" title="增加同级商品类别" style="cursor:pointer"/>
	   	  	 &nbsp;&nbsp;<img id="xjdept" name="xjProductType" onclick="preAddProductType('2')" src="../../images/btn_add.gif" title="增加下级商品类别" style="cursor:pointer"/>
	   	</div>
	   	-->
	   	
	   	<div class="left"><input type="button" onclick="preAddProductType('1')" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="增加同级商品类别">   	  
			   	  <input type="button" onclick="preAddProductType('2');" class="btn120x28" style="cursor:pointer" id="addthsheet" name="addthsheet" value="增加下级商品类别">
			   	  <input type="button" onclick="delProductTypes();" class="btn120x28" style="cursor:pointer" id="deletethsheet" name="deletethsheet" value="删除商品类别">			   	 
		</div>
	   	
    </div>
    	    
	    <div class="clear"></div>	    
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">	
	   	    	<!--   	    	
	   	    		<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        		<a href="#" target="_self" onclick="delProductTypes();"><img src="${contextPath}/images/btn_del.gif" alt="删除商品类别"></a>
					<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a> 
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	             -->  
			</div>
	      </div>
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    <div class="fl_left" style="width:150px">
	    	<script type="text/javascript">		
				d = new dTree('/zhuozhuo','d');
				d.add(0,-1,'商品类别管理','listProductType.do');
				<#list productTypeTreeList?if_exists as productTypeItem>
					d.add(${productTypeItem.recordId?if_exists},${productTypeItem.parentId?if_exists},'${productTypeItem.goodsTypeName?if_exists}','listProductType.do?recordId='+${productTypeItem.recordId?if_exists}+'&parentId='+${productTypeItem.parentId?if_exists});								
				</#list>		
				window.document.write(d);
			</script>    
    	</div>
	    <!--列表开始-->
	    <div class="selllist fl_left" style="width:900px">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="5%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>
	            <th scope="col" class="tabheader" width="4%">序号</th>
	            <th scope="col" class="tabheader" width="6%">商品类别id</th>
	            <th scope="col" class="tabheader" width="6%">商品类别编码</th>
	            <th scope="col" class="tabheader" width="14%">商品类别名称</th>
	            <th scope="col" class="tabheader" width="14%">父节点id</th>
	            <th scope="col" class="tabheader" width="6%">商品类别层级</th>
	            <th scope="col" class="tabheader" width="11%">自定义</th>
	            <th scope="col" class="tabheader" width="8%">备注</th>            
	          </tr>		  
			  <#list productTypesList as product>
				<tr bgcolor="#FFFFFF">
					<td class="tablist"><input type="checkbox" name="recordId" value='${product.recordId?default("")}'/></td>
	            	<td class="tablist">${product_index}</td><!--获得索引值-->
	            	<td class="tablist">${product.recordId?default("")}</td>
					<td class="tablist">${product.goodsTypeCode?default("")}</td>
	            	<td class="tablist"><a href="${contextPath}/mis/productType/editProductType.do?recordId=${product.recordId?default("")}" class="green" >${product.goodsTypeName?default("")}</a></td>
	            	<td class="tablist">${product.parentId?default("")}</td>            	
	            	<td class="tablist">${product.classLevel?default("")}</td>
	           		<td class="tablist">${product.define1?default("")}</td>
	            	<td class="tablist">${product.remark?default("")}</td>
				</tr>
			  </#list>         
		</table>
	    </div>
	    <div class="clear"></div>
	    <!--列表结束--> 
		<div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--
	   	    		<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        		<a href="#" target="_self" onclick="delProductTypes();"><img src="${contextPath}/images/btn_del.gif" alt="删除商品类别"></a>				
					<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	             -->
			</div>     
	    </div>
	    <div class="clear"></div>
	</form>
</div>
</body>
</html>
