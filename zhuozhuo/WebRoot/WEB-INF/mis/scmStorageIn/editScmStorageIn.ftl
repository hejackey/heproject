<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/js/helpCode.js"></script>
<script type="text/javascript" src="${contextPath}/js/scmStorageOut/scmStorageOut.js"></script>
<script type="text/javascript" src="${contextPath}/js/scmStorageIn/scmStorageIn.js"></script>
<script type="text/javascript" src="${contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/mis/common.js"></script>
<script language="javascript">

function formCheck(){		
		document.forms[0].action="${contextPath}/mis/scmStorageIn/updateScmStorageIn.do?type=${type}";
		document.forms[0].submit();
	}

	function queryProvider(){
		var pname=$("#providerName").val();		
		window.open("${contextPath}/mis/provider/qByNameProvider.do?providerName="+encodeURIComponent(pname),"_blank");
	}
	
	function choseBarnType(context){
	var id=$("#barnId").find('option:selected').val();
	
	$.get(context+"/barn/getByIdBarnType.do?id="+id+"&d="+new Date().getTime(),function(data){
		try{
			if(data!=null && data!=""){
				var obj=eval('(' + data + ')');				
				$("#barnAddress").html(obj.address);
				$("#barnPhone").html(obj.phone);
				
				$("#toaddress").val(obj.address);
			}
			else{
				$("#barnAddress").html("");
				$("#barnPhone").html("");
				$("#toaddress").val("");
			}
		}
		catch(e){
			alert(e);
		}
	});
}
	
</script>
</head> 
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">增加入库单</span>
    </div>
    <div id="container">
    <div class="addsell">
   	  <div class="left currentname fb">增加入库单</div>        
    	<!-- <div class="right textright"><a href="#" class="green fb">返回上一级</a></div> -->
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
   	    	<!-- 
	        	<input name="" type="image" src="${contextPath}/images/btn_del.gif" />
	            <input name="" type="image" src="${contextPath}/images/btn_refresh.gif" />
	            <input name="" type="image" src="${contextPath}/images/btn_print.gif" />
             -->
		</div>
        <div class="left fl_right textright">
            提示："<span class="required">*</span>" 表示必填项
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<form action="${contextPath}/mis/scmStorageIn/updateScmStorageIn.do?type=${type}" method="post">
    		<input type="hidden" name="id" id="id" value="<#if scmStorageIn?if_exists!="">${scmStorageIn.id?if_exists}</#if>">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>
            <td width="10%" class="header fb">入库单号<span class="required">*</span></td>
            <td width="12%">
              <input type="text" name="sheetId" id="sheetId" class="sellinput" value="${scmStorageIn.sheetId?if_exists}" readOnly="true"/>
            </td>
            <td width="10%" class="header fb">供应商<span class="required">*</span></td>
            <td width="15%">              
               <input type="text" size="22" name="providerName" id="providerName" value="${providerName?if_exists}">
               <img src="${contextPath}/images/calendar.gif" onclick="queryProvider()" style="cursor:pointer;">    
			   <input type="hidden" name="providerid" id="providerid" value="${scmStorageIn.providerid?if_exists}">               
            </td>
            <td width="10%" class="header fb">部门<span class="required">*</span></td>
            <td width="15%">
	            <select name="departmentId" id="departmentId">  
		             	<option value="0">请选择</option>            
			            <#list deptList?if_exists as dept>            
			              <option value="${dept.id?if_exists}" <#if scmStorageIn?if_exists!=""><#if scmStorageIn.departmentId?if_exists==dept.id?if_exists>selected</#if></#if>>              
			              <#if dept.departmentname?if_exists!="">${dept.departmentname?substring(1,dept.departmentname?length)?if_exists?html}</#if>              
			              </option>
			            </#list>          
	             </select>
            </td>
            <td width="10%" class="header fb">入库日期<span class="required">*</span></td>
            <td width="15%">
              <input type="text" name="payDate" id="payDate"  onClick=WdatePicker({dateFmt:"yyyy-MM-dd",readOnly:true}) class="Wdate" size="22"  value="${scmStorageIn.payDate?if_exists}"/>
            </td>
          </tr>
          <tr>
            <td class="header fb">入库类型</td>
            <td>
            	<input type="text" name="storageInType" id="storageInType" class="sellinput"  value="${scmStorageIn.storageInType?if_exists}"/>
            </td>
            <td class="header fb">仓库</td>
            <td>
            	<!-- <input type="text" name="barnId" id="barnId" class="sellinput" /> -->            	
	            <select name="barnId" id="barnId" onchange="choseBarnType('${contextPath}')">
		            <option value="0" >请选择</option>     
		            <#list barnTypeList?if_exists as list>                             
			              <option value="${list.id?if_exists}" <#if scmStorageIn?if_exists!=""><#if scmStorageIn.barnId?if_exists==list.id?if_exists>selected</#if></#if>>
			              	${list.barntypename?if_exists}${list.parentname?if_exists}
			              </option>
		            </#list>                     
	            </select>
			</td>
            <td class="header fb">运输方式</td>
            <td>
				<input type="text" name="transModeCode" id="transModeCode" class="sellinput"  value="${scmStorageIn.transModeCode?if_exists}" />
            </td>
            <td class="header fb">合同编号</td>
            <td>
            	<input type="text" name="bargainCode" id="bargainCode" class="sellinput" value="${scmStorageIn.bargainCode?if_exists}" />
            </td>
          </tr>         
          
          <tr>
            <td colspan="8">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0" id="product_table">
	            	  <tr>
			            <td colspan="14" class="header fb">
			            	<span class="fl_right">
			            		增加行数：
			            		<input type="text" name="rowCount" id="rowCount" class="sellinput" style="width:50px;" />         		
			            		<input type="button" value="新增"  onclick="addSSIDetail('${contextPath}')">
			            	</span>
			            	货单详情<span class="required">*</span>
			            </td>
			          </tr>
		              <tr>
			                <th scope="col" class="tabheader" width="4%">序号</th>			                
			                <th scope="col" class="tabheader" width="10%">商品编码</th>
			                <th scope="col" class="tabheader" width="10%">商品名称</th>
			                <th scope="col" class="tabheader" width="10%">规格</th>			                
			                <th scope="col" class="tabheader" width="7%">数量 </th>
			                <th scope="col" class="tabheader" width="8%">单价</th>
			                <th scope="col" class="tabheader" width="10%">总金额</th>
			                <th scope="col" class="tabheader" width="8%">条形码</th>			               
			                <th scope="col" class="tabheader" width="6%">原单据号</th>			                
			                <th scope="col" class="tabheader nb_right" width="6%">删除</th>
		              </tr>
		              <#if scmStorageInDetailList?if_exists?size==0>
		              <tr bgcolor="#f9ffe2" id="1">
			                <td id='seq1'>1</td>			                
			                <td>
			                	<input type="text" name="productcode" id="productcode1" class="sellinput" /><img src="${contextPath}/images/calendar.gif" id="href1" onclick="queryProductOut(1,'${contextPath}/mis/productInfoJxc/qByProductCodeProductInfoJxc.do')" style="cursor:pointer;"  title="1" >   
			                </td>			                
			                <td><input type="text" name="productname" readonly id="productname1" class="sellinput" /></td>
			                <td><input type="text" name="productspecs" readonly id="productspecs1" class="sellinput" /></td>
			                <td><input type="text" name="qty" id="amount1" onchange="calStorageOutSumPrice(1)" class="sellinput" /></td>
			                <td><input type="text" name="factSellPrice" onchange="calStorageOutSumPrice(1)" id="productpurchase1" class="sellinput" /></td>                
			                <td><input type="text" name="amt" readonly id="sumprice1" class="sellinput" /></td>
			                <td><input type="text" name="productentry" readonly id="productentry1" class="sellinput" /></td>
			                <td><input type="text" name="sheetIdOriginal" id="sheetIdOriginal1" class="sellinput" /></td>
			                <!--<input type="hidden" name="remark" id="remark1" class="sellinput" />-->
			                <input type="hidden" name="taxrate" id="taxrate1" class="sellinput" />
			                <input type="hidden" name="productId" id="productid1" class="sellinput" />
			                <td align="center" class="nb_right"><a href="#"><img src="${contextPath}/images/icon_del.gif" width="16" height="16" title="删除该条信息"  onclick="deleteDetailTR(1)" /></a></td>
		              </tr>
		              <#else>
		              		<#list scmStorageInDetailList?if_exists as list>
		              			<tr bgcolor="#f9ffe2" id="${list_index?if_exists+1}">
					                <td id='seq${list_index?if_exists+1}'>${list_index?if_exists+1}</td>			                
					                <td><input type="text" name="productcode" value="${list.productcode?if_exists}" id="productcode${list_index?if_exists+1}" class="sellinput" /><img src="${contextPath}/images/calendar.gif" onclick="queryProductOut(${list_index?if_exists+1},'${contextPath}/mis/productInfoJxc/qByProductCodeProductInfoJxc.do')" style="cursor:pointer;" >   
					                </td>			                
					                <td><input type="text" name="productname" value="${list.productname?if_exists}" readonly id="productname${list_index?if_exists+1}" class="sellinput" /></td>
					                <td><input type="text" name="productspecs" value="${list.productspecs?if_exists}" readonly id="productspecs${list_index?if_exists+1}" class="sellinput" /></td>
					                <td><input type="text" name="qty" value="${list.qty?if_exists}" id="amount${list_index?if_exists+1}" onchange="calStorageOutSumPrice(${list_index?if_exists+1})" class="sellinput" /></td>
					                <td><input type="text" name="factSellPrice" value="${list.factSellPrice?if_exists}" onchange="calStorageOutSumPrice(${list_index?if_exists+1})" id="productpurchase${list_index?if_exists+1}" class="sellinput" /></td>                
					                <td><input type="text" name="amt" value="${list.amt?if_exists}" readonly id="sumprice${list_index?if_exists+1}" class="sellinput" /></td>
					                <td><input type="text" name="productentry" value="${list.productentry?if_exists}" readonly id="productentry${list_index?if_exists+1}" class="sellinput" /></td>
					                <td><input type="text" name="sheetIdOriginal" value="${list.sheetIdOriginal?if_exists}" id="sheetIdOriginal${list_index?if_exists+1}" class="sellinput" /></td>
					                <!-- <input type="hidden" name="remark" value="${list.remark?if_exists}" id="remark${list_index?if_exists+1}" class="sellinput" /> -->
					                <input type="hidden" name="taxrate" value="${list.taxrate?if_exists}" id="taxrate${list_index?if_exists+1}" class="sellinput" />
					                <input type="hidden" name="productId" value="${list.productId?if_exists}" id="productid${list_index?if_exists+1}" class="sellinput" />
					                <td align="center" class="nb_right"><a href="#"><img src="${contextPath}/images/icon_del.gif" width="16" height="16" title="删除该条信息"  onclick="deleteTR(${list_index?if_exists+1})" /></a></td>
			             		</tr>
		              		</#list>
		              </#if>	
		              <tr bgcolor="">
			                <td>合计</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                
			                <td class="fb" id="hjAmount">${scmStorageIn.sumQty?if_exists}&nbsp;</td>
			                <input type="hidden" name="sumQty" id="sumqty" value="${scmStorageIn.sumQty?if_exists}">
			                <td>&nbsp;</td>
			                <td class="fb" id="hjSum">${scmStorageIn.sumAmt?if_exists} 元</td>
			                <input type="hidden" name="sumAmt" id="sumamt" value="${scmStorageIn.sumAmt?if_exists}">
			                <td>&nbsp;</td>
			                <td>&nbsp;</td>
			                <td class="nb_right">&nbsp;</td>
		              </tr>	                                  
	            </table>
            </td>
          </tr>
          <tr>
            <td class="header fb">制单人</td>
            <td>
              <input type="text" name="makerId" id="makerId" class="sellinput" />
            </td>
            <td class="header fb">经手人</td>
            <td>
              	<!-- <input type="text" name="userid" id="userid" class="sellinput" /> -->
              	<select name="userid" id="userid">  
	            <option value="0">请选择</option>    
	            <#list userList?if_exists as list>            
	              <option value="${list.id?if_exists}">              
	              ${list.name?if_exists?html}          
	              </option>
	            </#list>
            </td>
            <td class="header fb">审核人</td>
            <td>
              <input type="text" name="auditId" id="auditId" class="sellinput" />
            </td>
            <td class="header fb">收货人</td>
            <td>
              <input type="text" name="textfield" id="textfield" class="sellinput" />
            </td>
          </tr>
          <tr>
            <td class="header fb">联系电话</td>
            <td>
              <input type="text" name="textfield" id="textfield" class="sellinput" />
            </td>
            <td class="header fb">本公司地址</td>
            <td>
              <!-- <input type="text" name="textfield" id="textfield" class="sellinput" /> -->
              	北京市朝阳区东三环北路42号中基新东方
            </td>
            <td class="header fb">库房地址</td>
            <td>
              <input type="text" name="textfield" id="textfield" class="sellinput" />
            </td>
            <td class="header fb">质检人</td>
            <td>
              <input type="text" name="textfield" id="textfield" class="sellinput" />
            </td>
          </tr>
          <tr>
            <td class="header fb">到货时间</td>
            <td>
              <input type="text" name="textfield" id="textfield"  onClick=WdatePicker({dateFmt:"yyyy-MM-dd",readOnly:true}) class="Wdate" size="22" />
            </td>
            <td class="header fb">到货地点</td>
            <td>
              <input type="text" name="toAddress" id="toAddress" class="sellinput" />
            </td>
            <td class="header fb">运费</td>
            <td>
              <input type="text" name="textfield" id="textfield" class="sellinput" />
            </td>
            <td class="header fb"></td>
            <td>
              <!-- <input type="text" name="sheetState" id="sheetState" class="sellinput" /> -->
            </td>
          </tr>          
          <tr>
            <td align="center" colspan="10" height="40">
            	<!-- 
                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_define.gif" />&nbsp;&nbsp;<input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_cancel.gif" />
                 -->
                 <!-- <input type="submit" value="保 存" > --> 
                 <!-- <input type="button" value="保 存"  onclick="formCheck()">  -->
                 <input type="button" class="btn48x22 fl_left" onclick="formCheck()" style="cursor:pointer" id="savestoragein" name="savestoragein" value="保存"/>
            </td>
          </tr>
        </table>
        </form>
    </div>
    <!--详细结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="right fl_left">
   	    	<!-- 
	        	<input name="" type="image" src="${contextPath}/images/btn_del.gif" />
	            <input name="" type="image" src="${contextPath}/images/btn_refresh.gif" />
	            <input name="" type="image" src="${contextPath}/images/btn_print.gif" />
             -->
		</div>
   	  </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
</div>
</body>
</html>