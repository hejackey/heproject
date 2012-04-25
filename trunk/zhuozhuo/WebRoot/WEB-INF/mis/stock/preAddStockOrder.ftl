<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
<link href="../css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="../js/stock/stockorder.js"></script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">采购单列表</a>  > <span class="fb black">
    	<#if model.stockopentype?if_exists==1>
	    	<#if model.sheetid?if_exists=="">
	    	新增采购单    	
	    	<#else>
	    	修改采购单
	    	</#if>
    	<#else>
    		<#if model.sheetid?if_exists=="">
	    	新增退货单  	
	    	<#else>
	    	修改退货单
	    	</#if>    	
    	</#if></span>
    </div>
    <div id="container">
    <div class="addsell">
   	  <div class="left currentname fb">
   	  <#if model.stockopentype?if_exists==1>
	    	<#if model.sheetid?if_exists=="">
	    	新增采购单    	
	    	<#else>
	    	修改采购单
	    	</#if>
    	<#else>
    		<#if model.sheetid?if_exists=="">
	    	新增退货单  	
	    	<#else>
	    	修改退货单
	    	</#if>    	
    	</#if></div>        
    	<div class="right textright"><a href="listStockOrder.do?stockopentype=${model.stockopentype?if_exists}" class="green fb">返回上一级</a></div>
    </div>
    <div class="clear"></div>
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    
        <div class="left fl_right textright">
            提示：“<span class="required">*</span>” 表示必填项
        </div>
      </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    <form name="form1" method="post" action="saveStockOrder.do">
    <input type="hidden" name="stockopentype" id="stockopentype" value="${model.stockopentype?if_exists}">
    <input type="hidden" name="sheetid" id="sheetid" value="${model.sheetid?if_exists}">
    <input type="hidden" name="id" id="id" value="${model.id?if_exists}">    
    <!--详细开始-->
    <div class="selllist">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>
           
            <td width="11%" class="header fb">供应商</td>
            <td width="16%">
             <input type="text" size="22" name="providerName" id="providerName" value="${model.providerName?if_exists}">
             <img src="../images/calendar.gif" onclick="queryProvider('${model.context?if_exists}/mis/provider/qByNameProvider.do')" style="cursor:pointer;">    
			<input type="hidden" name="providerid" id="providerid" value="${model.providerid?if_exists}"> 
            </td>
             <td class="header fb" width="10%">合同编号</td>
            <td width="13%"><input type="text" size="22" name="bargaincode" id="bargaincode" value="${model.bargaincode?if_exists}"></td>
            <td width="7%" class="header fb">采购部门</td>
            <td width="18%">
              <select name="departmentid" id="departmentid">  
            <option value="0">请选择</option>    
            <#list model.deptList?if_exists as list>            
              <option value="${list.id?if_exists}" <#if model.departmentid?if_exists==list.id?if_exists>selected</#if>>              
              <#if list.departmentname?if_exists!="">${list.departmentname?substring(1,list.departmentname?length)?if_exists?html}</#if>              
              </option>
            </#list>                        
            </select>
            </td>
            <td class="header fb" width="11%">是否已经收货</td>
            <td width="12%">             
           <select name="isreceive" id="isreceive">              
              <option value="2" <#if model.isreceive?if_exists==2>selected</#if>>否</option>      
              <option value="1" <#if model.isreceive?if_exists==1>selected</#if>>是</option>                     
            </select> </td>
            
          </tr>
          <tr>           
            <td width="8%" class="header fb">预计到货时间</td>
            <td><input type="text" size="22" class="Wdate" name="pretodate" id="pretodate" value="<#if model.pretodate?if_exists!="">${model.pretodate?if_exists}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
            <td class="header fb">收货日期</td>
            <td><input type="text" size="22" class="Wdate" name="todate" id="todate" value="<#if model.todate?if_exists!="">${model.todate?if_exists}</#if>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
            <td class="header fb">仓库</td>
            <td><select name="barnid" id="barnid" onchange="choseBarnType('${model.context?if_exists}')">
            <option value="0" >请选择</option>     
            <#list model.barnTypeList?if_exists as list>                             
              <option value="${list.id?if_exists}" <#if model.barnid?if_exists==list.id?if_exists>selected</#if>>${list.barntypename?if_exists}${list.parentname?if_exists}</option>
            </#list>                     
            </select></td>
             <td class="header fb">备注</td>
            <td class=""><input type="text" size="22" name="memo" id="memo" value="${model.memo?if_exists?html}"></td>           
          </tr>
          <tr>
           
          </tr>
          <tr>
            <td class="header fb">支付日期</td>
            <td><input type="text" size="22" class="Wdate" name="paydate" id="paydate" value="${model.paydate?if_exists}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>            
            <td class="header fb">供应商地址</td>
            <td>			<input type="text" size="22" readonly name="address" id="address" value="${model.address?if_exists}" ></td>
            <td class="header fb">电话</td>
            <td><input type="text" size="22" name="phone" readonly id="phone" value="${model.phone?if_exists}"></td>
            
            <td class="header fb">联系人</td>
            <td><input type="text" size="22" name="businessman" readonly id="businessman" value="${model.businessman?if_exists}"></td>
           
          </tr>
          <tr>
            <td colspan="8" class="header fb"><span class="fl_right">增加行数：
				<input type="text" name="colCount" id="colCount" class="sellinput" style="width:50px;" />             
            	<input type="button" align="middle" style="cursor:pointer" onclick="addProductTr()" id="addTr" name="addTr" value="新增"/>
			</span>货单详情</td>
          </tr>
          <tr>
            <td colspan="8">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="productTable">
              <tr>
                <th scope="col" class="tabheader02" width="5%">序号</th>
                <th scope="col" class="tabheader02" width="15%">商品编码</th>
                <th scope="col" class="tabheader02" width="14%">商品名称</th>
                <th scope="col" class="tabheader02" width="12%">规格</th>
                <th scope="col" class="tabheader02" width="10%">数量</th>
                <th scope="col" class="tabheader02" width="10%">采购单价（含税）</th>
                <th scope="col" class="tabheader02" width="8%">税率</th>
                <th scope="col" class="tabheader02" width="10%">总金额</th>
                <th scope="col" class="tabheader02" width="6%">条形码</th>
                <th scope="col" class="tabheader02" width="9%">备注</th>
                <th scope="col" class="tabheader02" width="3%">删除</th>
              </tr>
              <tbody id="td_row">
              <#if model.stockOrderDetailList?if_exists?size!=0>
              <#list model.stockOrderDetailList?if_exists as list>
              <tr bgcolor="#f9ffe2" class="trclass">
                <td class="seq">${list_index?if_exists+1}</td>
                <td>
                <input type="hidden" name="stockOrderDetail.aproductid" id="productid${list_index?if_exists+1}" class="sellinput" value="${list.productid?if_exists}"/>
                <input type="text" name="productcode" id="productcode${list_index?if_exists+1}" class="sellinput productcode" value="${list.productcode?if_exists}"/>
                
                <img src="../images/calendar.gif" id="href${list_index?if_exists+1}" onclick="queryProduct(${list_index?if_exists+1})" style="cursor:pointer;"  title="${list_index?if_exists+1}" >                    
                </td>
                <td><input type="text" name="productname" readonly id="productname${list_index?if_exists+1}" class="sellinput productname" value="${list.productname?if_exists}"/></td>
                <td><input type="text" name="productspecs" readonly id="productspecs${list_index?if_exists+1}" class="sellinput productspecs" value="${list.productspecs?if_exists}"/></td>
                <td><input type="text" name="stockOrderDetail.aqty" id="amount${list_index?if_exists+1}" title="${list_index?if_exists+1}" onchange="calSumPrice(${list_index?if_exists+1})" value="${list.qty?if_exists}" class="sellinput amount" /></td>
                <td><input type="text" name="stockOrderDetail.afactinprice" id="productpurchase${list_index?if_exists+1}" title="${list_index?if_exists+1}" onchange="calSumPrice(${list_index?if_exists+1})" value="${list.factinprice?if_exists}" class="productpurchase sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.ataxrate" id="taxrate${list_index?if_exists+1}" title="${list_index?if_exists+1}" onchange="calSumPrice(${list_index?if_exists+1})" value="${list.factinprice?if_exists}" class="sellinput taxrate" /></td>
                <td><input type="text" name="stockOrderDetail.aamt" id="sumprice${list_index?if_exists+1}" readonly class="sellinput sumprice" value="${list.taxrate?if_exists}"/></td>
                <td><input name="productentry" type="text" class="sellinput productentry${list_index?if_exists+1}" readonly id="productentry" value="${list.amt?if_exists}" /></td>
                <td align="center"><input type="text" name="stockOrderDetail.aremark" id="remark${list_index?if_exists+1}" readonly class="sellinput remark" value="${list.remark?if_exists}"/></td>
                <td align="center" class="nb_right"><a href="javascript:;" onclick="removetr(this)"><img src="../images/icon_del.gif" width="16" height="16" title="删除该条信息" /></a></td>
              </tr>
              </#list>
              <#else>              
              <tr bgcolor="#f9ffe2" class="trclass">
                <td class="seq">1</td>
                <td>
                <input type="hidden" name="stockOrderDetail.aproductid" id="productid1" class="sellinput" value=""/>
                <input type="text" name="productcode" id="productcode1" class="sellinput productcode"/>
                <img src="../images/calendar.gif" id="href1" onclick="queryProduct(1)" style="cursor:pointer;"  title="1" >                    
                </td>
                <td><input type="text" name="productname" readonly id="productname1" class="sellinput productname" /></td>
                <td><input type="text" name="productspecs" readonly id="productspecs1" class="sellinput productspecs" /></td>
                <td><input type="text" name="stockOrderDetail.aqty" id="amount1" title="1" onchange="calSumPrice(1)"  class="sellinput amount" /></td>
                <td><input type="text" name="stockOrderDetail.afactinprice" id="productpurchase1" title="1" onchange="calSumPrice(1)" class="productpurchase sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.ataxrate" id="taxrate1" title="1" onchange="calSumPrice(1)" class="sellinput taxrate" /></td>
                <td><input type="text" name="stockOrderDetail.aamt" id="sumprice1" readonly class="sellinput sumprice" value=""/></td>
                <td><input name="productentry" type="text" class="sellinput productentry1" readonly id="productentry" value="" /></td>
                <td align="center"><input type="text" name="stockOrderDetail.aremark" id="remark1" readonly class="sellinput remark" /></td>
                <td align="center" class="nb_right"><a href="javascript:;" onclick="removetr(this)"><img src="../images/icon_del.gif" width="16" height="16" title="删除该条信息" /></a></td>
              </tr>
              <tr bgcolor="#ffffdf" class="trclass">
                <td class="seq">2</td>
                <td>
                <input type="hidden" name="stockOrderDetail.aproductid" id="productid2" class="sellinput"/>
                <input type="text" name="productcode" id="productcode2" class="sellinput"/>
                <img src="../images/calendar.gif" id="href2" onclick="queryProduct(2)" style="cursor:pointer;"  title="2" >
                </td>
                <td><input type="text" name="productname" readonly id="productname2" class="sellinput" /></td>
                <td><input type="text" name="productspecs" readonly id="productspecs2" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.aqty" id="amount2" title="2" onchange="calSumPrice(2)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.afactinprice" id="productpurchase2" title="2" onchange="calSumPrice(2)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.ataxrate" id="taxrate2" title="2" onchange="calSumPrice(2)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.aamt" id="sumprice2" readonly class="sellinput" /></td>
                <td><input name="productentry" type="text" readonly class="sellinput" id="productentry2" value="" /></td>
                <td align="center"><input type="text" name="stockOrderDetail.aremark" readonly id="remark2" class="sellinput" /></td>
                <td align="center" class="nb_right">
                <a href="javascript:;" onclick="removetr(this)">
                <img src="../images/icon_del.gif" width="16" height="16" title="删除该条信息" /></a></td>
              </tr>
              <tr bgcolor="#f9ffe2" class="trclass">
                <td class="seq">3</td>
                <td>
                <input type="hidden" name="stockOrderDetail.aproductid" id="productid3" class="sellinput"/>
                <input type="text" name="productcode" id="productcode3" class="sellinput" title="3" />
                <img src="../images/calendar.gif" id="href3"  onclick="queryProduct(3)" style="cursor:pointer;"  title="3" >
                </td>
                <td><input type="text" name="productname" readonly id="productname3" class="sellinput" /></td>
                <td><input type="text" name="productspecs" readonly id="productspecs3" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.aqty" id="amount3" title="3" onchange="calSumPrice(3)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.afactinprice" id="productpurchase3" title="3" onchange="calSumPrice(3)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.ataxrate" id="taxrate3" title="3" onchange="calSumPrice(3)" class="sellinput" /></td>
                <td><input type="text" name="stockOrderDetail.aamt" id="sumprice3"readonly  class="sellinput" /></td>
                <td><input name="productentry" type="text" class="sellinput" readonly id="productentry3" value="" /></td>
                <td align="center"><input type="text" name="stockOrderDetail.aremark" id="remark3" readonly class="sellinput" /></td>
                <td align="center" class="nb_right"><a href="javascript:;" onclick="removetr(this)"><img src="../images/icon_del.gif" width="16" height="16" title="删除该条信息" /></a></td>
              </tr>             
              </#if>
              </tbody>
              <tr bgcolor="">
                <td>合计</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td class="fb" id="hjAmount">${model.sumqty?if_exists}</td>
                <td><input type="hidden" name="sumqty" id="sumqty" value="${model.sumqty?if_exists}"></td>
                <td>&nbsp;</td>
                <td class="fb" id="hjSum">${model.sumamt?if_exists} 元</td>
                <td><input type="hidden" name="sumamt" id="sumamt" value="${model.sumamt?if_exists}"></td>
                <td>&nbsp;</td>
                <td class="nb_right">&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td class="header fb">制单人</td>
            <td>
              <input type="text" size="22" readonly name="makerName" id="makerName" value="${model.makerName?if_exists}">
              <input type="hidden" size="22" name="makerid" id="makerid" value="${model.makerid?if_exists}">
            </td>
            <td class="header fb">经办人</td>
            <td>
            <select name="userid" id="userid">  
            <option value="0">请选择</option>    
            <#list model.userList?if_exists as list>            
              <option value="${list.id?if_exists}" <#if model.userid?if_exists==list.id?if_exists>selected</#if>>              
              ${list.name?if_exists?html}          
              </option>
            </#list>                        
            </select>            
            </td>
            <td class="header fb">审核人</td>
            <td>
			<input type="text" size="22" name="auditid" id="auditid" value="${model.auditid?if_exists}">
            </td>
            <td class="header fb">&nbsp;</td>
            <td>&nbsp;            </td>
          </tr><tr>
            <td class="header fb">本公司地址</td>
            <td>
              sdfasdf
            </td>
            <td class="header fb">联系电话</td>
            <td>
              12312312
            </td>
            <td class="header fb" >库房地址</td>
            <td id="barnAddress">${model.barnAddress?if_exists}</td>
            <input type="hidden" name="toaddress" id="toaddress" value="${model.toaddress?if_exists}">
            <td class="header fb">库房电话</td>
            <td  id="barnPhone">
              ${model.barnPhone?if_exists}
            </td>
          </tr>
          <tr>
            <td align="center" colspan="10" height="40">
                <#if model.id?if_exists!="">
	                <#if model.auditid?if_exists==""||model.sheetstate?if_exists!=1>
		            <input type="button" class="btn48x22 fl_left" onclick="editStockOrder()" style="cursor:pointer" id="editstockorder" name="editstockorder" value="修改"/>
		            </#if>            
	            <#else>
	            <input type="button" class="btn48x22 fl_left" onclick="saveStockOrder()" style="cursor:pointer" id="savestockorder" name="savestockorder" value="保存"/>
	            </#if>
            </td>
          </tr>
        </table>
    </div>
    </form>
    <!--详细结束-->
    
    <!--上边功能区开始-->
   	  <div class="tooltop nowarp">
   	    <div class="right fl_left">
        	注：点击<img src="../images/calendar.gif">查询供应商或商品信息
		</div>
   	  </div>
      <div class="clear"></div>
    <!--上边功能区结束-->
    
    </div>
</div>
<script>
<#if model.result?if_exists=="1">
	alert("新增采购单成功");	
</#if>

<#if model.result?if_exists=="2">
	alert("修改采购单成功");	
</#if>

<#if model.errors?if_exists!="">
alert("${model.errors?if_exists}");
</#if>
</script>
</body>
</html>
