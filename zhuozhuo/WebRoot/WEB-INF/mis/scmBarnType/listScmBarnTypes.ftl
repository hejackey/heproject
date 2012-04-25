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
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/string.js"></script>
<script language="javascript">	
		function preAddScmBarnType(type){
			var parentid = $("#parentid").val();	
			var id = $("#id").val();	
			barntypename= $("#barntypename").val();	
			if(type==='1'){//增加同级仓库		
				if(parentid.isEmpty()){
					alert("根节点只允许添加下级部门");
					return;
				}
			}	
			if(type==='2'){//增加下级仓库	
				if(id.isEmpty())
					parentid="0";		
				else
					parentid=id;
			}
			document.forms[0].action="addScmBarnType.do?parentid="+parentid;	
			document.forms[0].submit();
		}	
		//删除仓库类别
		function delScmBarnTypes(){
			var checkCnt = listCheck(document.forms[0].ids);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些仓库类别吗？")==true){
				document.forms[0].action="${contextPath}/mis/scmBarnType/deleteScmBarnType.do";				
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
				alert("没有选择仓库类别，请选择！");
				return false;
			}		
			return checkCnt;
		}		
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">仓库类型列表</span>
    </div>
    <form action=""  method="post">
    <input type="hidden" name="id" id="id" value="${scmBarnType.id?if_exists}">   
    <input type="hidden" name="parentid" id="parentid" value="${scmBarnType.parentid?if_exists}">
    <input type="hidden" name="parentname" id="parentname" value="${scmBarnType.parentname?if_exists}">
    <div id="container">
	    <div class="addsell">
	    	<!--
	   	  	<div class="left"><img id="tjdept" name="tjProductType" onclick="preAddScmBarnType('1')" src="../../images/btn_add.gif" title="增加同级仓库类别" style="cursor:pointer"/>
	   	  		  &nbsp;&nbsp;<img id="xjdept" name="xjProductType" onclick="preAddScmBarnType('2')" src="../../images/btn_add.gif" title="增加下级仓库类别" style="cursor:pointer"/>	   	  		 
	   	  	</div>
	   	  	-->
	   	  	<div class="left">
	   	  			<input type="button" onclick="preAddScmBarnType('1')" class="btn120x28" style="cursor:pointer" id="addsheet" name="addsheet" value="增加同级仓库类别">   	  
			   	  	<input type="button" onclick="preAddScmBarnType('2');" class="btn120x28" style="cursor:pointer" id="addthsheet" name="addthsheet" value="增加下级仓库类别">
			   	  	<input type="button" onclick="delScmBarnTypes();" class="btn120x28" style="cursor:pointer" id="deletesheet" name="deletesheet" value="删除仓库类别">			   	 
			</div>
    	</div>
    	    
	    <div class="clear"></div>	    
	    <!--上边功能区开始-->
	   	  <div class="tooltop nowarp">
	   	    <div class="left fl_left">	
	   	    	<!--   	    	
	   	    	<a href="#" onclick="checkAllCheckBox();return false;"><img src="${contextPath}/images/btn_select.gif" alt="全选"></a>        	
	        	<a href="#" target="_self" onclick="delScmBarnTypes();"><img src="${contextPath}/images/btn_del.gif" alt="删除仓库类别"></a>				
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>	
				-->
				<!--            
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
				d.add(0,-1,'仓库类别管理','listScmBarnType.do');
				<#list scmBarnTypeTreeList?if_exists as scmBarnTypeItem>
					d.add(${scmBarnTypeItem.id?if_exists},${scmBarnTypeItem.parentid?if_exists},'${scmBarnTypeItem.barntypename?if_exists}','listScmBarnType.do?id='+${scmBarnTypeItem.id?if_exists}+'&parentid='+${scmBarnTypeItem.parentid?if_exists});								
				</#list>		
				window.document.write(d);
			</script>    
    </div>
	    <!--列表开始-->
	    <div class="selllist fl_left" style="width:900px">
	    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <th scope="col" class="tabheader nb_left" width="8%"><input type="checkbox" title="全选/取消全选" name="checkbox" id="checkbox" onclick="checkCheckBox(this)"/>&nbsp;全选</th>
	            <th scope="col" class="tabheader" width="4%">序号</th>
	            <th scope="col" class="tabheader" width="8%">仓库类别id</th>
	            <th scope="col" class="tabheader" width="10%">仓库类别编码</th>
	            <th scope="col" class="tabheader" width="20%">仓库类别名称</th>
	            <th scope="col" class="tabheader" width="10%">父节点id</th>
	            <th scope="col" class="tabheader" width="10%">父节点名称</th>
	            <th scope="col" class="tabheader" width="11%">地址</th>
	            <th scope="col" class="tabheader" width="8%">电话</th>            
	          </tr>		  
			  <#list scmBarnTypeList as scmBarnType>
				<tr bgcolor="#FFFFFF">
					<td class="tablist"><input type="checkbox" name="ids" value='${scmBarnType.id?default("")}'/></td>
	            	<td class="tablist">${scmBarnType_index}</td><!--获得索引值-->
	            	<td class="tablist">${scmBarnType.id?default("")}</td>
					<td class="tablist">${scmBarnType.barntypecode?default("")}</td>
	            	<td class="tablist"><a href="${contextPath}/mis/scmBarnType/editScmBarnType.do?id=${scmBarnType.id?default("")}" class="green" >${scmBarnType.barntypename?default("")}</a></td>
	            	<td class="tablist">${scmBarnType.parentid?default("")}</td>            	
	            	<td class="tablist">${scmBarnType.parentname?default("")}</td>
	           		<td class="tablist">${scmBarnType.address?default("")}</td>
	            	<td class="tablist">${scmBarnType.phone?default("")}</td>
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
	        	<a href="#" target="_self" onclick="delScmBarnTypes();"><img src="${contextPath}/images/btn_del.gif" alt="删除仓库类别"></a>				
				<a href="#"><img src="${contextPath}/images/btn_excel.gif" alt="导出excell"></a>	
				--> 
				<!--            
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
