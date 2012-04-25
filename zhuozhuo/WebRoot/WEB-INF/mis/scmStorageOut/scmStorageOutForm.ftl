<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${contextPath}/js/scmStorageOut/scmStorageOut.js"></script>
<script type="text/javascript" src="${contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/mis/common.js"></script>
<script language="javascript">
		
</script>
</head> 
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">
    	<#if storageOut?if_exists=="">
    	增加发货单
    	<#else>
    	修改发货单
    	</#if>
    	</span>
    </div>
    <div id="container">
    <div class="addsell">
   	  <div class="left currentname fb">
   	  <#if storageOut?if_exists=="">
   	  	增加发货单
   	  <#else>
    	修改发货单
      </#if></div>        
    	<div class="right textright"><a href="listScmStorageOut.do" class="green fb">返回上一级</a></div>
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="left fl_left">
        	
		</div>
        <div class="left fl_right textright">
            提示："<span class="required">*</span>" 表示必填项
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    <!--详细开始-->
    <div class="selllist">
    	<form name="form1" action="${contextPath}/mis/scmStorageOut/saveScmStorageOut.do" method="post">
    	<input type="hidden" name="id" id="id" value="<#if storageOut?if_exists!="">${storageOut.id?if_exists}</#if>">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>
          <#if storageOut?if_exists!="">
            <td width="7%" class="header fb">发货单号<span class="required">*</span></td>
            <td width="18%">            
              <input type="text" name="sheetId" id="sheetId" class="sellinput" value="${storageOut.sheetId?if_exists}"/>
            </td>
           </#if>
            <td width="7%" class="header fb">客户<span class="required">*</span></td>
            <td width="15%">
              <input type="text" name="clientId" id="clientId" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.clientId?if_exists}</#if>"/>
            </td>
            <td width="7%" class="header fb">部门<span class="required">*</span></td>
            <td width="20%">
              <!-- <input type="text" name="departmentId" id="departmentId" class="sellinput" /> -->
	              <select name="departmentId" id="departmentId">  
		             		<option value="0">请选择</option>            
			            <#list deptList?if_exists as dept>            
			              <option value="${dept.id?if_exists}" <#if storageOut?if_exists!=""><#if storageOut.departmentId?if_exists==dept.id?if_exists>selected</#if></#if>>              
			              <#if dept.departmentname?if_exists!="">${dept.departmentname?substring(1,dept.departmentname?length)?if_exists?html}</#if>              
			              </option>
			            </#list>          
	               </select>	            
            </td>
             <td width="7%" class="header fb">发货日期</td>
            <td>
            	<input type="text" name="gatherDate" id="gatherDate"  onClick=WdatePicker({dateFmt:"yyyy-MM-dd",readOnly:true}) class="Wdate" size="22" value="<#if storageOut?if_exists!="">${storageOut.gatherDate?if_exists}</#if>"/>
            </td>
            <#if storageOut?if_exists=="">
            <td width="7%" class="header fb">&nbsp;</td>
            <td width="18%">            
              &nbsp;
            </td>
           </#if>
          </tr>
          <tr>
           <td class="header fb">运输方式</td>
            <td>
                <input type="text" name="transModeCode" id="transModeCode" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.transModeCode?if_exists}</#if>"/>
            </td>
         	 <td class="header fb">仓库</td>
            <td>				
				<!-- <input type="text" name="barnId" id="barnId" class="sellinput" /> -->
            	<select name="barnId" id="barnId" onchange="choseBarnType('${contextPath}')">
	            <option value="0" >请选择</option>     
	            <#list barnTypeList?if_exists as list>                             
	              <option value="${list.id?if_exists}" <#if storageOut?if_exists!=""><#if storageOut.barnId?if_exists==list.id?if_exists>selected</#if></#if>>
	              ${list.barntypename?if_exists}${list.parentname?if_exists}
	              </option>
	            </#list>                     
	            </select>
            </td>
           
            <td class="header fb">出库类型</td>
            <td>
            	<select name="storageOutType" id="storageOutType" >
	            <option value="0" >请选择</option>     
	            <option value="1" <#if storageOut?if_exists!=""><#if storageOut.storageOutType?if_exists=="1">selected</#if></#if>>正品出库</option>  
	            <option value="2" <#if storageOut?if_exists!=""><#if storageOut.storageOutType?if_exists=="2">selected</#if></#if>>赠品出库</option>                      
	            </select>
			</td>
            <td width="7%" class="header fb">来源单号</td>
            <td width="18%">            
              <input type="text" name="srcSheetId" id="srcSheetId" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.srcSheetId?if_exists}</#if>"/>
            </td>           
          </tr>
          
          <tr>
            <td colspan="8">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0" id="product_table">
	            	  <tr>
			            <td colspan="12" class="header fb">
			            	<span class="fl_right">增加行数：			   
			            		<input type="text" name="rowCount" id="rowCount" class="sellinput" style="width:50px;" />              		
			            		<input type="button" value="新增"  onclick="addDetail('${contextPath}')">
			            	</span>
			            	货单详情<span class="required">*</span>
			            </td>
			          </tr>
		              <tr>
			                <th scope="col" class="tabheader" width="5%">序号</th>			                
			                <th scope="col" class="tabheader" width="14%">商品编码</th>
			                <th scope="col" class="tabheader" width="18%">商品名称</th>
			                <th scope="col" class="tabheader" width="10%">规格</th>
			                <th scope="col" class="tabheader" width="7%">数量</th>
			                <th scope="col" class="tabheader" width="5%">单价</th>
			                <th scope="col" class="tabheader" width="8%">总金额</th>
			                <th scope="col" class="tabheader" width="14%">条形码</th>			               
			                <th scope="col" class="tabheader" width="10%">来源单号</th>
			                <th scope="col" class="tabheader nb_right" width="5%">删除</th>
		              </tr>
		              <#if scmStorageOutDetails?if_exists?size==0>
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
			                <input type="hidden" name="aremark" id="remark1" class="sellinput" />
			                <input type="hidden" name="taxrate" id="taxrate1" class="sellinput" />
			                <input type="hidden" name="productId" id="productid1" class="sellinput" />
			                <td align="center" class="nb_right"><a href="#"><img src="${contextPath}/images/icon_del.gif" width="16" height="16" title="删除该条信息"  onclick="deleteTR(1)" /></a></td>
		              </tr>	
		              <#else>
			              <#list scmStorageOutDetails?if_exists as list>
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
				                <input type="hidden" name="aremark" value="${list.remark?if_exists}" id="remark${list_index?if_exists+1}" class="sellinput" />
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
                
                <td class="fb" id="hjAmount"><#if storageOut?if_exists!="">${storageOut.sumQty?if_exists}</#if>&nbsp;</td>
                <input type="hidden" name="sumQty" id="sumqty" value="<#if storageOut?if_exists!="">${storageOut.sumQty?if_exists}</#if>">
                <td>&nbsp;</td>
                <td class="fb" id="hjSum"><#if storageOut?if_exists!="">${storageOut.sumAmt?if_exists}</#if> 元</td>
                <input type="hidden" name="sumAmt" id="sumamt" value="<#if storageOut?if_exists!="">${storageOut.sumAmt?if_exists}</#if>">
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
              <input type="text" name="makerId" id="makerId" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.makerId?if_exists}</#if>"/>
            </td>
            <td class="header fb">经手人</td>
            <td>
              <input type="text" name="userId" id="userId" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.userId?if_exists}</#if>"/>
            </td>
            <td class="header fb">审核人</td>
            <td>
              <input type="text" name="auditId" id="auditId" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.auditId?if_exists}</#if>"/>
            </td>
            <td class="header fb">收货人</td>
            <td>
              <input type="text" name="gatherName" id="gatherName" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.gatherName?if_exists}</#if>"/>
            </td>
          </tr>
          <tr>
         	 <td class="header fb">质检人</td>
            <td>
              <input type="text" name="qualiterid" id="qualiterid" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.qualiterid?if_exists}</#if>"/>
            </td>
            <td class="header fb">到货时间</td>
            <td>
              <input type="text" name="toDate" id="toDate" onClick=WdatePicker({dateFmt:"yyyy-MM-dd",readOnly:true}) class="Wdate" size="22" value="<#if storageOut?if_exists!="">${storageOut.toDate?if_exists}</#if>"/>
            </td>
            <td class="header fb">到货地点</td>
            <td>
              <input type="text" name="toAddress" id="toAddress" class="sellinput" value="<#if storageOut?if_exists!="">${storageOut.toAddress?if_exists}</#if>"/>
            </td>
            <td class="header fb" >库房地址</td>
            <td id="barnAddress"><#if storageOut?if_exists!="">${storageOut.barnAddress?if_exists}</#if>&nbsp;</td>

          </tr>
          <tr>			           
            <td class="header fb">库房电话</td>
            <td  id="barnPhone">
              <#if storageOut?if_exists!="">${storageOut.barnPhone?if_exists}</#if>&nbsp;
            </td>
            <td class="header fb">本公司地址</td>
            <td>
          		    阿斯顿丰盛的
            </td>
            <td class="header fb"></td>
            <td>
              23213
            </td>
          </tr>
          <tr>
            <td align="center" colspan="10" height="40">
            <#if storageOut?if_exists=="">               
                <input type="button" class="btn48x22 fl_left" onclick="saveStorageOut()" style="cursor:pointer" id="savestorageout" name="savestorageout" value="保存"/>
            <#else>
            <input type="button" class="btn48x22 fl_left" onclick="editStorageOut()" style="cursor:pointer" id="editstorageout" name="editstorageout" value="修改"/>
            </#if> 
            </td>
          </tr>
        </table>
        </form>
    </div>
    <!--详细结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="right fl_left">
        	
		</div>
   	  </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
</div>
</body>
</html>

