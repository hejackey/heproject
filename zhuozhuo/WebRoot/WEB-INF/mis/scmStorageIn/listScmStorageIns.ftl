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
<script type="text/javascript" src="${contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/scmStorageIn/scmStorageIn.js"></script>
<script language="javascript">

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
				alert("没有选择入库单(退货单)，请选择！");
				return false;
			}		
			return checkCnt;
		}			
</script>
<#if type="enterWarehouse">
<script language="javascript">
		function queryScmStorageIn(){
		//alert("enterWarehouse");		
		//document.forms[0].action="${contextPath}/mis/scmStorageIn/listScmStorageIn.do?type=returnedPurchase";		
		document.forms[0].action="${contextPath}/mis/scmStorageIn/listScmStorageIn.do?type=enterWarehouse";			
		document.forms[0].submit();			
		}

		//审核并流转入库单
		function auditScmScmStorageIns(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要审核这些入库单吗？")==true){
				document.forms[0].action="${contextPath}/mis/scmStorageIn/auditScmStorageIn.do?type=enterWarehouse";				
				document.forms[0].submit();
			}
		}
				
		//删除入库单
		function deleteScmStorageIns(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些入库单吗？")==true){
				document.forms[0].action="${contextPath}/mis/scmStorageIn/deleteScmStorageIn.do?type=enterWarehouse";				
				document.forms[0].submit();
			}
		}		
</script>
<#else>
<script language="javascript">	
		function queryScmStorageIn(){
		//alert("returnedPurchase");		
		document.forms[0].action="${contextPath}/mis/scmStorageIn/listScmStorageIn.do?type=returnedPurchase";		
		//document.forms[0].action="${contextPath}/mis/scmStorageIn/listScmStorageIn.do?type=enterWarehouse";			
		document.forms[0].submit();			
		}
			
		//删除退货单
		function deleteScmStorageIns(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些退货单吗？")==true){
				document.forms[0].action="${contextPath}/mis/scmStorageIn/deleteScmStorageIn.do?type=returnedPurchase";				
				document.forms[0].submit();
			}
		}		
</script>
</#if>
<script language="javascript">		
		//增加入库单
		function addScmStorageIn(){			
				document.forms[0].action="${contextPath}/mis/scmStorageIn/addScmStorageIn.do?type=enterWarehouse";				
				document.forms[0].submit();
			}
		function auditTHD(){//审核退货单								
				var checkCnt = listCheck(document.forms[0].id);
				if(checkCnt <= 0)
				return;
				if(confirm("要审核这些退货单吗？")==true){
					document.forms[0].action="${contextPath}/mis/scmStorageIn/auditTHDScmStorageIn.do";						
					document.forms[0].submit();
				}
			}
		function addScmStorageIn1(){			
				document.forms[0].action="${contextPath}/mis/scmStorageIn/addScmStorageIn.do?type=returnedPurchase";				
				document.forms[0].submit();
			}				
</script>

</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > 
    	<#if type="enterWarehouse">
    		<span class="fb black">入库单</span>
    	<#else>
    		<span class="fb black">退货单</span>
    	</#if>
    </div>
    <div id="container">
	    <div class="addsell">
		   	  <div class="left">
		   	  		<#if type="enterWarehouse">						
						<input type="button" onclick="addScmStorageIn()" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="增加入库单">  	  
			   	  		<input type="button" onclick="auditScmScmStorageIns();" class="btn120x28" style="cursor:pointer" id="addthsheet" name="addthsheet" value="审核入库单">
			   	  		<input type="button" onclick="deleteScmStorageIns();" class="btn120x28" style="cursor:pointer" id="deletesheet" name="deletesheet" value="删除入库单">
					<#else>
						<input type="button" onclick="addScmStorageIn1()" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="增加退货单"> 
						<input type="button" onclick="auditTHD();" class="btn120x28" style="cursor:pointer" id="auditTHD" name="auditTHD" value="审核退货单">
						<input type="button" onclick="deleteScmStorageIns();" class="btn120x28" style="cursor:pointer" id="deletesheet" name="deletesheet" value="删除退货单"> 						
					</#if>
			 </div>
	    </div>    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">	
	   	    	<!--   	    	
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>
	   	    	<#if type="enterWarehouse">        	
	        		<a href="#" target="_self" onclick="deleteScmStorageIns();"><img src="${contextPath}/images/btn_del.gif" alt="删除入库单"></a>
	        	<#else>	
	        		<a href="#" target="_self" onclick="deleteScmStorageIns();"><img src="${contextPath}/images/btn_del.gif" alt="删除退货单"></a>	
	        	</#if>		
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>
				-->
				<!--             
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	             -->
	             	单据序号：<input name="sheetId" size="16" id="sheetId" type="text" value='${RequestParameters.sheetId?default("")}'/>&nbsp;&nbsp;
	             	 
	             	部门名称：
	             			<select name="departmentId" id="departmentId">  
				            <option value="">请选择</option> 
				        	   <#list deptList?if_exists as dept>
				        	   		<option value="${dept.id?if_exists}" 
				        	   			<#if RequestParameters.departmentId?if_exists!="">
				        	   				<#if RequestParameters.departmentId?if_exists==dept.id?if_exists>selected</#if>
				        	   			</#if>>
						              	<#if dept.departmentname?if_exists!="">${dept.departmentname?substring(1,dept.departmentname?length)?if_exists?html}</#if>
						            </option>
				        	   </#list>
				         <select>
					&nbsp;  	             	
   	                付款开始日期：<input name="startTime" class="Wdate" size="14" id="startTime" type="text" value="${scmStorageInQ.startTime?default("")}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>&nbsp; 	                    
   	                付款结束日期：<input name="endTime" class="Wdate" size="14" id="endTime" type="text" value="${scmStorageInQ.endTime?default("")}"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
        	        <input type="button" class="btn48x22" style="cursor:pointer" onclick="queryScmStorageIn()"  value="查 询">  
			</div>
	      </div>
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="7%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>	            
	            <th scope="col" class="tabheader" width="6%">单据序号</th>
	            <th scope="col" class="tabheader" width="14%">供应商名称</th>
	            <th scope="col" class="tabheader" width="14%">部门名称</th>
	            <th scope="col" class="tabheader" width="6%">总数量</th>
	            <th scope="col" class="tabheader" width="11%">总金额</th>
	            <th scope="col" class="tabheader" width="8%">付款日期</th>            
	          </tr>
	          <#if listScmStorageIns?exists>
		          <#list listScmStorageIns as scmStorageIn>
					<tr bgcolor="#FFFFFF">
						<td class="tablist">
								<input type="checkbox" name="id" value='${scmStorageIn.id?default("")}'/>&nbsp;&nbsp;
								${scmStorageIn_index+1}&nbsp;&nbsp;
								<span id="si${scmStorageIn.id?if_exists?html}" style="cursor:pointer" onclick="showScmStorageInDetail('${scmStorageIn.id?if_exists?html}')" title="1">+展开</span>
						</td>
						<#if type="enterWarehouse">
							<td class="tablist"><a href="${contextPath}/mis/scmStorageIn/editScmStorageIn.do?type=enterWarehouse&id=${scmStorageIn.id?default("")}" class="green" >${scmStorageIn.sheetId?default("")}</a></td>
						<#else>
							<td class="tablist"><a href="${contextPath}/mis/scmStorageIn/editScmStorageIn.do?type=returnedPurchase&id=${scmStorageIn.id?default("")}" class="green" >${scmStorageIn.sheetId?default("")}</a></td>
						</#if>           	
		            	<td class="tablist">${scmStorageIn.providerid?default("")}</td>
		            	<td class="tablist">${scmStorageIn.departmentId?default("")}</td>            	
		            	<td class="tablist">${scmStorageIn.sumQty?default("")}</td>
		           		<td class="tablist">${scmStorageIn.sumAmt?default("")}</td>
		            	<td class="tablist">${scmStorageIn.payDate?default("")}</td>
					</tr>
					<tr id="detail${scmStorageIn.id?if_exists?html}" style="display:none">
		          			  <td>&nbsp;</td>
					          <td colspan="10">
						          <table id="detail_table_${scmStorageIn.id?if_exists?html}" width="100%">
						                
						          </table>
					          </td>
					    </tr>
				  </#list>
			  </#if>
			  	          		           
		</table>
	    </div>
	    <!--列表结束--> 
		<div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a> 
	   	    	<#if type="enterWarehouse">       	
	        		<a href="#" target="_self" onclick="deleteScmStorageIns();"><img src="${contextPath}/images/btn_del.gif" alt="删除入库单"></a>
	        	<#else>	
	        		<a href="#" target="_self" onclick="deleteScmStorageIns();"><img src="${contextPath}/images/btn_del.gif" alt="删除退货单"></a>
	        	</#if>			
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
