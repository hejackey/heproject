<#import "../spring.ftl" as spring/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<#assign contextPath="/zhuozhuo">
<link href="${contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/layout.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/css/master.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextPath}/js/scmPay/scmPay.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/stock/stockorder.js"></script>
<script type="text/javascript" src="${contextPath}/js/string.js"></script>
<script type="text/javascript" src="${contextPath}/js/mis/common.js"></script>
<script language="javascript">
function formCheck(){
		
	}
</script>
</head> 
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">
    	<#if scmPay?if_exists=="">
    	增加付款单
    	<#else>
    	修改付款单
    	</#if>
    	</span>
    </div>
    <div id="container">
    <div class="addsell">
   	  <div class="left currentname fb">
   	  	<#if scmPay?if_exists=="">
    	增加付款单
    	<#else>
    	修改付款单
    	</#if></div>        
    	<div class="right textright"><a href="listScmPay.do" class="green fb">返回上一级</a></div>
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
    	<form name="form1" action="${contextPath}/mis/scmPay/saveScmPay.do" method="post">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="selldetail">
          <tr>
          <#if scmPay?if_exists!="">          
            <td width="8%" class="header fb">付款单号<span class="required">*</span></td>
            <td width="13%">
              <input type="text" name="sheetId" id="sheetId" readonly class="sellinput" value="${scmPay.sheetId?if_exists}"/>
              <input type="hidden" name="id" id="id" class="sellinput" value="${scmPay.id?if_exists}"/>
            </td>
           </#if>
            <td width="8%" class="header fb">供应商名称<span class="required">*</span></td>
            <td width="12%">
              <input type="hidden" name="providerid" id="providerid" value="<#if scmPay?if_exists!="">${scmPay.providerId?if_exists}</#if>"> 
              <input type="text" name="providerName" id="providerName" value="<#if scmPay?if_exists!="">${scmPay.providername?if_exists}</#if>" <#if scmPay?if_exists!="" && scmPay.providername?if_exists!="">readonly</#if> <#if scmPay?if_exists=="">onblur="queryPayDetail()"</#if> class="sellinput" />              
              <img src="${contextPath}/images/calendar.gif" onclick="queryProvider('${contextPath}/mis/provider/qByNameProvider.do')" style="cursor:pointer;">   
            </td>
            <td class="header fb" width="8%">供应商收款帐号</td>
            <td width="12%">
            	<input type="text" name="account" readonly id="account" class="sellinput" value="<#if scmPay?if_exists!="">${scmPay.account?if_exists}</#if>"/>
			</td>
            <td width="10%" class="header fb">采购部门<span class="required">*</span></td>
            <td width="15%">              
              <!-- <input type="text" name="departmentId" id="departmentId" class="sellinput" /> -->              
              <select name="departmentId" id="departmentId">  
		             	<option value="0">请选择</option>            
			            <#list deptList?if_exists as dept>            
			              <option value="${dept.id?if_exists}" <#if scmPay?if_exists!=""><#if scmPay.departmentId?if_exists==dept.id?if_exists>selected</#if></#if>>              
			              <#if dept.departmentname?if_exists!="">${dept.departmentname?substring(1,dept.departmentname?length)?if_exists?html}</#if>              
			              </option>
			            </#list>          
	            </select>
            </td>
            
          </tr>
          <tr>
            <td class="header fb">付款方式</td>
            <td>
				<select name="paymentType" id="paymentType" >
	            <option value="0" >请选择</option>     
	            <option value="1" <#if scmPay?if_exists!=""><#if scmPay.paymentType?if_exists=="1">selected</#if></#if>>先款后货</option>  
	            <option value="2" <#if scmPay?if_exists!=""><#if scmPay.paymentType?if_exists=="2">selected</#if></#if>>先货后款</option>                      
	            </select>
            </td>
            <td class="header fb">备注</td>
            <td>
                <input type="text" name="remark" id="remark" class="sellinput" value="<#if scmPay?if_exists!="">${scmPay.remark?if_exists}</#if>"/>
            </td>
          </tr>
          
          <tr>
            <td colspan="8">
	            <table width="100%" border="0" cellspacing="0" cellpadding="0">
	            	  <tr>
			            <td colspan="12" class="header fb">
			            	
			            	货单详情<span class="required">*</span>(根据供应商显示货单详情)
			            </td>
			          </tr>
		              <tr>
			                <th scope="col" class="tabheader" width="10%"><input type='checkbox' name='choseall' id='choseall' onclick="selectAll(this.checked,'detailid')"/>序号</th>
			                <th scope="col" class="tabheader" width="12%">采购单单号</th>
			                <th scope="col" class="tabheader" width="20%">供应商编码</th>
			                <th scope="col" class="tabheader" width="20%">供应商名称</th>
			                <th scope="col" class="tabheader" width="14%">本单采购金额</th>			                
			                <th scope="col" class="tabheader" width="14%">付款限期</th>
			                <th scope="col" class="tabheader" width="10%">备注</th>
		              </tr>
		              <tbody id="detail_content">
		              <#list scmPayDetailList?if_exists as list>
		             	 <tr bgcolor='#f9ffe2'>
			                 <td><input type='checkbox' name='detailid' id='detailid' value='${list.id?if_exists}'<#if list.auditSign?if_exists=="1">disabled checked</#if>/>${list_index?if_exists+1}
			                 <input type='hidden' name='arrid' id='arrid' value='${list.id?if_exists}'>
			                 </td>
			                 <td>${list.srcSheetId?if_exists}</td>
			                 <td>${list.providercode?if_exists}</td>
			                 <td>${list.providername?if_exists}</td>
			                 <td>${list.amt?if_exists}</td>
			                 <td>${list.payDate?if_exists}</td>
			                 <td>${list.remark?if_exists}</td>
			             </tr>
		              </#list>
		              </tbody>	                                  
	            </table>
	            <input type="hidden" name="sumAmt" id="sumAmt" class="sellinput" />
            </td>
          </tr>
          <tr>
            <td class="header fb">制单人</td>
            <td>
              <input type="text" name="makerId" id="makerId" class="sellinput" value="<#if scmPay?if_exists!="">${scmPay.makerId?if_exists}</#if>"/>
            </td>
            <td class="header fb">经手人</td>
            <td>
              <input type="text" name="userId" id="userId" class="sellinput" value="<#if scmPay?if_exists!="">${scmPay.userId?if_exists}</#if>"/>
            </td>
            <td class="header fb">审核人</td>
            <td>
              <input type="text" name="auditId" id="auditId" class="sellinput" value="<#if scmPay?if_exists!="">${scmPay.auditId?if_exists}</#if>"/>
            </td>           
          </tr>          
          <tr>
            <td align="center" colspan="10" height="40">
              <#if scmPay?if_exists=="">               
                <input type="button" class="btn48x22 fl_left" onclick="saveScmPay()" style="cursor:pointer" id="savescmpay" name="savescmpay" value="保存"/>
           	  <#else>
                <input type="button" class="btn48x22 fl_left" onclick="editScmPay()" style="cursor:pointer" id="editscmpay" name="editscmpay" value="修改"/>
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