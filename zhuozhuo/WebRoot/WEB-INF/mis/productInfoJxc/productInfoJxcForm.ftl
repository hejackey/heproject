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
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/stock/stockorder.js"></script>
<script type="text/javascript" src="${contextPath}/js/mis/common.js"></script>
<script language="javascript">
function formCheck(){	
					
		if ( Trim(document.forms[0].productName.value) == ""){	
				alert("请输入商品名称!");
				document.forms[0].productName.focus();
				return false;
		}
		if ( Trim(document.forms[0].productCode.value) == ""){	
				alert("请输入商品编码!");
				document.forms[0].productCode.focus();
				return false;
		}
		if(!(IsNumeric(document.forms[0].productClass.value))){
				alert("商品等级请输入数字");
				document.forms[0].productClass.focus();
				return false;
		}
		
		if(IsNumeric(document.forms[0].productClass.value)){
				if(document.forms[0].productClass.value.length > 1){
					alert("商品等级请输入一位数字");
					document.forms[0].productClass.focus();
					return false;
				}				
		}
		
		
		
		
		if(!(IsNumeric(document.forms[0].productWeight.value))){
				alert("请输入商品重量数字!");
				document.forms[0].productWeight.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].productVolume.value))){
				alert("请输入请输入商品体积数字!");
				document.forms[0].productVolume.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].factStorage.value))){
				alert("请输入请输入实际库存数字!");
				document.forms[0].factStorage.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].leastOrder.value))){
				alert("请输入请输入最小订货量数字!");
				document.forms[0].leastOrder.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].productIncrement.value))){
				alert("请输入请输入商品增值!");
				document.forms[0].productIncrement.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].pricePurchase.value))){
				alert("请输入请输入进价!");
				document.forms[0].pricePurchase.focus();
				return false;
		}
		
		
		
		if(!(IsNumeric(document.forms[0].costPrice.value))){
				alert("请输入请输入成本价!");
				document.forms[0].costPrice.focus();
				return false;
		}		
		
		
		
		if ( Trim(document.forms[0].priceNet.value) == ""){	
				alert("请输入销售价!");
				document.forms[0].priceNet.focus();
				return false;
		}
		
		if(!(IsNumeric(document.forms[0].priceNet.value))){
				alert("销售价请输入数字");
				document.forms[0].priceNet.focus();
				return false;
		}
		
		if ( Trim(document.forms[0].priceMarket.value) == ""){	
				alert("请输入市场价!");
				document.forms[0].priceMarket.focus();
				return false;
		}
		
		if(!(IsNumeric(document.forms[0].priceMarket.value))){
				alert("市场价请输入数字");
				document.forms[0].priceMarket.focus();
				return false;
		}
		
		if ( Trim(document.forms[0].createBy.value) == ""){	
				alert("请输入添加人员!");
				document.forms[0].createBy.focus();
				return false;
		}
		
		document.forms[0].action="${contextPath}/mis/productInfoJxc/saveProductInfoJxc.do";
		document.forms[0].submit();
	}


<!--
function queryProductType(url){	
	var code=$("#productTypeName").val();	
	window.open(url+"?goodsTypeCode="+encodeURIComponent(code),"_blank");
}

function changeProductcode(){
	if($("#productTypeName").val()=="")
		$("#productType").val("");
	}
-->
</script>

</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">商品列表</span>
    </div>
    <div id="container">
	    <div class="addsell">
	   		<div class="left currentname fb">新增商品</div>        
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
	            提示：“<span class="required">*</span>”表示必填项
	        </div>
	      </div>
	      <div class="clear"></div>
    	<!--上边功能区结束-->
    
			    <!--详细开始-->
			    <form action="${contextPath}/mis/productInfoJxc/saveProductInfoJxc.do" method="post">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">商品名称<span class="required">*</span></td>
			            <td width="35%">			            		            	
			            	<@spring.formInput "command.productName",'onblur=TextChange(this,document.forms[0].productCode)' />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>			            
			            <td width="15%" class="header fb">商品编码<span class="required">*</span></td>
			            <td width="35%">			                
			                <@spring.formInput "command.productCode"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品等级<span class="required">*</span></td>
			            <td width="35%">			                
			                <@spring.formInput "command.productClass"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品单位<span class="required">*</span></td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productUnit"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品规格</span></td>
			            <td width="35%">			                
			                <@spring.formInput "command.productSpecs"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品类型</td>
			            <td width="35%">
			            	<!--			            	
				            	<@spring.formInput "command.productType" 'onchange="changeProductcode()"' />			            	
			    				<@spring.showErrors "", "color:red;"/>	
			    				<input name="productTypeName" size="14" id="productTypeName" onchange="changeProductcode()" type="text" value=""/>
	   	               			<input name="productType" size="12" id="productType" type="hidden" value="sssssss"/> 
	   	               			<img src="${contextPath}/images/calendar.gif" onclick="queryProductType('${contextPath}/mis/productType/qByCodeProductType.do')" style="cursor:pointer;">
   	               			-->
   	               			<input name="productTypeName" size="14" id="productTypeName" onchange="changeProductcode()" type="text" value="${command.productTypeName?if_exists}"/>
   	               			<input name="productType" size="12" id="productType" type="hidden" value="${command.productType?if_exists}"/> 
   	               			<img src="${contextPath}/images/calendar.gif" onclick="queryProductType('${contextPath}/mis/productType/qByCodeProductType.do')" style="cursor:pointer;">     		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品性能</td>
			            <td width="35%">			                
			                <@spring.formInput "command.productProperty"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品重量</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productWeight"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品体积</td>
			            <td width="35%">			                
			                <@spring.formInput "command.productVolume"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品颜色</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productCorlor"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品尺寸</td>
			            <td width="35%">			                
			                <@spring.formInput "command.porductSize"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">实际库存</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.factStorage"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">成本计算</td>
			            <td width="35%">			                
			                <@spring.formInput "command.costCalculate"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品条码</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productentry"  />
		    				<@spring.showErrors "", "color:red;"/>		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">称重编码</td>
			            <td width="35%">			                
			                <@spring.formInput "command.weightilyCode"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">最小订货量</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.leastOrder"  />
		    				<@spring.showErrors "", "color:red;"/>		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">进价</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.pricePurchase"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td> 
			            <td width="15%" class="header fb">批次标记</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.batchMark"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">生产日期</td>
			            <td width="35%">
			                <@spring.formInput "command.produceDate",'onClick=WdatePicker({dateFmt:"yyyy-MM-dd",readOnly:true}) class="Wdate" size="22"'/>
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">商品增值</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.productIncrement"  />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>            
			          </tr>			          
			          <tr>
			            <td width="15%" class="header fb">成本价</td>
			            <td width="35%">			                
			                <@spring.formInput "command.costPrice"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">销售价<span class="required">*</span></td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.priceNet"  />
		    				<@spring.showErrors "", "color:red;"/>		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">市场价<span class="required">*</span></td>
			            <td width="35%">			                
			                <@spring.formInput "command.priceMarket"  />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">添加人员<span class="required">*</span></td>
			            <td width="35%">			            	
			            	 <@spring.formInput "command.createBy"  />
		    				<@spring.showErrors "", "color:red;"/>	            	
			            </td>            
			          </tr>			          
			          <tr>
			            <td width="15%" class="header fb">备注</td>
			            <td colspan=3>			                
			                <@spring.formInput "command.remark" ,' size="110"' />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>			                      
			          </tr>                    
			          <tr>
			            <td align="center" colspan="10" height="40">
			                <!-- 
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_define.gif" />&nbsp;&nbsp;
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_cancel.gif" />
			                  -->
			                <!-- <input type="submit" value="保 存" > -->
			                <input type="button" value="保 存"  onclick="formCheck()">  
			            </td>
			          </tr>
			        </table>
			    </div>
			    </form>
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
