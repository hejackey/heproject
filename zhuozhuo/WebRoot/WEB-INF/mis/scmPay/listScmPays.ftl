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
<script type="text/javascript" src="${contextPath}/js/scmPay/scmPay.js"></script>
<script language="javascript">			
		//查询付款单
		function queryScmPays(){							
				document.forms[0].action="${contextPath}/mis/scmPay/listScmPay.do";				
				document.forms[0].submit();
		}
		
		//删除付款单
		function addScmPay(){			
				document.forms[0].action="${contextPath}/mis/scmPay/addScmPay.do";				
				document.forms[0].submit();
		}

	
		//删除付款单
		function deleteScmPays(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些付款单吗？")==true){
				document.forms[0].action="${contextPath}/mis/scmPay/deleteScmPay.do";				
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
				alert("没有选择付款单，请选择！");
				return false;
			}
		
			return checkCnt;
		}			
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">付款单</span>
    </div>
    <div id="container">
	    <div class="addsell">
		   	  <div class="left">			
					<!-- <a href="${contextPath}/mis/scmPay/addScmPay.do" target="_self"><img src="${contextPath}/images/btn_add.gif" alt="增加付款单"></a>	-->				
					<input type="button" onclick="addScmPay();" class="btn120x28" style="cursor:pointer" id="addScmPay" name="addScmPay" value="增加付款单">	
			   	  	<input type="button" onclick="deleteScmPays();" class="btn120x28" style="cursor:pointer" id="deleteScmPay" name="deleteScmPay" value="删除付款单">
			 </div>
	    </div>
    
	    <div class="clear"></div>
	    <form method="post">
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">
	            	单据号：<input name="sheetId" size="16" id="sheetId" type="text" value='${RequestParameters.sheetId?default("")}'/>&nbsp;
   	              	&nbsp;用户名称：
   	              		<select name="userId" id="userId">  
				            <option value="">请选择</option> 
				        	   <#list userList?if_exists as user>
				        	   		<option value="${user.id?if_exists}" 
				        	   			<#if RequestParameters.userId?if_exists!="">
				        	   				<#if RequestParameters.userId?if_exists==user.id?if_exists>selected</#if>
				        	   			</#if>>
						              	${user.name?if_exists}
						            </option>
				        	   </#list>
				         <select>
					&nbsp;   	             	
   	                支票号：<input name="checkCode" size="16" id="checkCode" type="text" value='${RequestParameters.checkCode?default("")}'/>&nbsp;&nbsp;&nbsp;
        	        <input type="button" class="btn48x22" style="cursor:pointer" onclick="queryScmPays()"   value="查 询"> 
			</div>
	      </div>
      	<div class="clear"></div>
	    <!--上边功能区结束-->
	    
	    <!--列表开始-->
	    <div class="selllist">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="7%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>	            
	            <th scope="col" class="tabheader" width="6%">付款单编码</th>
	            <th scope="col" class="tabheader" width="14%">单据号</th>
	            <th scope="col" class="tabheader" width="14%">用户名称</th>
	            <th scope="col" class="tabheader" width="6%">支票号</th>
	            <th scope="col" class="tabheader" width="11%">本单金额</th>	              
	          </tr>
	          <#if listScmPays?exists>
		          <#list listScmPays as scmPay>
					<tr bgcolor="#FFFFFF">
						<td class="tablist">
								<input type="checkbox" name="id" value='${scmPay.id?default("")}'/>&nbsp;&nbsp;
								${scmPay_index+1}&nbsp;&nbsp;
								<span id="sp${scmPay.id?if_exists?html}" style="cursor:pointer" onclick="showScmPayDetail('${scmPay.id?if_exists?html}')" title="1">+展开</span>
						</td>		            	
						<td class="tablist">${scmPay.providerId?default("")}</td>
		            	<!-- <td class="tablist"><a href="${contextPath}/mis/scmPay/editScmPay.do?id=${scmPay.id?default("")}">${scmPay.sheetId?default("")}</a></td> -->
		            	<td class="tablist"><a href="preEditScmPay.do?id=${scmPay.id?default("")}"  class="green" >${scmPay.sheetId?default("")}</a></td>
		            	<td class="tablist">${scmPay.userId?default("")}</td>            	
		            	<td class="tablist">${scmPay.checkCode?default("")}</td>
		           		<td class="tablist">${scmPay.sumAmt?default("")}</td>		            	
					</tr>
					<tr id="detail${scmPay.id?if_exists?html}" style="display:none">
		          			  <td>&nbsp;</td>
					          <td colspan="10">
						          <table id="detail_table_${scmPay.id?if_exists?html}" width="100%">
						                
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
		        	<a href="#" target="_self" onclick="deleteScmPays();"><img src="${contextPath}/images/btn_del.gif" alt="删除付款单"></a>				
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
