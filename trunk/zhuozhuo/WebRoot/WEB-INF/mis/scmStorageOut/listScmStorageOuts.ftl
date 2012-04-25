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
<script type="text/javascript" src="${contextPath}/js/scmStorageOut/scmStorageOut.js"></script>
<script language="javascript">
	function queryScmStorageOut(){
		document.forms[0].action="${contextPath}/mis/scmStorageOut/listScmStorageOut.do";				
		document.forms[0].submit();
	}		
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">发货单管理</span>
    </div>
    <div id="container">
	    <div class="addsell">
		   	  <!--<div class="left">			
					<a href="${contextPath}/mis/scmStorageOut/addScmStorageOut.do" target="_self"><img src="${contextPath}/images/btn_add.gif" alt="增加发货单"></a>					
					<a href="#" target="_self" onclick="auditScmStorageOuts();"><img src="${contextPath}/images/btn_add.gif" alt="审核"></a>	
			 </div>-->
			 <div class="left"><input type="button" onclick="addScmStorageOut()" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="增加发货单">   	  
			   	  <input type="button" onclick="auditScmStorageOuts();" class="btn120x28" style="cursor:pointer" id="addthsheet" name="addthsheet" value="审核发货单">	
			   	  <input type="button" onclick="deleteScmStorageOuts();" class="btn120x28" style="cursor:pointer" id="deletesheet" name="addthsheet" value="删除发货单">		   	 
			 </div>
			 
	    </div>
    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">
	   	    	<!--	   	    	
		   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
		        	<a href="#" target="_self" onclick="deleteScmStorageOuts();"><img src="${contextPath}/images/btn_del.gif" alt="删除发货单"></a>				
					<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>	            
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	            -->
	           		单据序号：<input name="sheetId" size="16" id="sheetId" type="text" value='${RequestParameters.sheetId?default("")}'/>&nbsp;
   	              	&nbsp;客户ID：<input name="clientId" size="16" id="clientId" type="text" value='${RequestParameters.clientId?default("")}'/>&nbsp;   	             	
   	                仓库：
   	                	<select name="barnId" id="barnId">
				        	   <option value="">请选择</option> 
				        	   <#list barnTypeList?if_exists as barnType>
				        	   		<option value="${barnType.id?if_exists}" 
				        	   			<#if RequestParameters.barnId?if_exists!="">
				        	   				<#if RequestParameters.barnId?if_exists==barnType.id?if_exists>selected</#if>
				        	   			</#if>>
						              	${barnType.barntypename?if_exists}${barnType.parentname?if_exists}
						            </option>
				        	   </#list>   	    
				        </select>
					&nbsp;
   	                单据来源编号<input name="srcSheetId" size="16" id="srcSheetId" type="text" value='${RequestParameters.srcSheetId?default("")}'/>&nbsp;&nbsp;&nbsp;
        	        <input type="button" class="btn48x22" style="cursor:pointer" onclick="queryScmStorageOut()" id="querystockorder" value="查询"> 
			</div>
	      </div>
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="8%" align="left"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>	            
	            <th scope="col" class="tabheader" width="6%">单据序号</th>
	            <th scope="col" class="tabheader" width="14%">仓库</th>
	            <th scope="col" class="tabheader" width="14%">客户ID</th>
	            <th scope="col" class="tabheader" width="6%">用户名称</th>
	            <th scope="col" class="tabheader" width="11%">交货地址</th>
	            <th scope="col" class="tabheader" width="8%">来源单号</th>            
	          </tr>
	          <#if listScmStorageOuts?exists>
		          <#list listScmStorageOuts as scmStorageOut>
						<tr bgcolor="#FFFFFF">
							<td class="tablist"><input type="checkbox" name="id" value='${scmStorageOut.id?default("")}'/>&nbsp;&nbsp;
								${scmStorageOut_index+1}&nbsp;&nbsp;
								<span id="so${scmStorageOut.id?if_exists?html}" style="cursor:pointer" onclick="showScmStorageOutDetail('${scmStorageOut.id?if_exists?html}')" title="1">+展开</span>
							</td>		            	
							<td class="tablist"><a href="${contextPath}/mis/scmStorageOut/getDetailScmStorageOut.do?id=${scmStorageOut.id?default("")}" class="green">${scmStorageOut.sheetId?default("")}</a></td>
							<!-- <td class="tablist"><a href="#">${scmStorageOut.sheetId?default("")}</a></td> -->
			            	<td class="tablist">${scmStorageOut.barnId?default("")}</td>
			            	<td class="tablist">${scmStorageOut.clientId?default("")}</td>            	
			            	<td class="tablist">${scmStorageOut.userId?default("")}</td>
			           		<td class="tablist">${scmStorageOut.toAddress?default("")}</td>
			            	<td class="tablist">${scmStorageOut.srcSheetId?default("")}</td>
						</tr>
						<tr id="detail${scmStorageOut.id?if_exists?html}" style="display:none">
		          			  <td>&nbsp;</td>
					          <td colspan="10">
						          <table id="detail_table_${scmStorageOut.id?if_exists?html}" width="100%">
						                
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
		        	<a href="#" target="_self" onclick="deleteScmStorageOuts();"><img src="${contextPath}/images/btn_del.gif" alt="删除发货单"></a>				
					<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>	            
		            <a href="#"><img src="${contextPath}/images/btn_refresh.gif" alt="刷新"></a> 	            
		            <a href="#"><img src="${contextPath}/images/btn_print.gif" alt="打印"></a> 
	            -->
			</div>     
	    </div>
	</form>
</div>
</body>
</html>
