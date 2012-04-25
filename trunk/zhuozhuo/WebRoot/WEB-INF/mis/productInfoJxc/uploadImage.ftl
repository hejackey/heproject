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
<script type="text/javascript" src="${contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/ajaxupload.3.2.js"></script>
<script type="text/javascript">
		
</script>
</head>
<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">上传商品图片</span>
    </div>
    <div id="container">
	    <div class="addsell">
	   		<div class="left currentname fb">更新商品</div>        
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
	      </div>
	      <div class="clear"></div>
    	<!--上边功能区结束-->
    
			    <!--详细开始-->			    
			    
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">商品名称</td>
			            <td width="35%">
		    				${command.productName?default("")}		            	
			            </td>			            
			            <td width="15%" class="header fb">商品编码</td>
			            <td width="35%"> 
		    				${command.productCode?default("")}
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品等级</td>
			            <td width="35%">			                
			                ${command.productClass?default("")}
			            </td>
			            <td width="15%" class="header fb">商品单位</td>
			            <td width="35%">			            	
			            	${command.productUnit?default("")}			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品规格</span></td>
			            <td width="35%">			                
			                ${command.productSpecs?default("")}
			            </td>
			            <td width="15%" class="header fb">商品类型</td>
			            <td width="35%">
		    				${command.productType?default("")}		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品性能</td>
			            <td width="35%">			                
			                ${command.productProperty?default("")}
			            </td>
			            <td width="15%" class="header fb">商品重量</td>
			            <td width="35%">			            	
			            	${command.productWeight?default("")}			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品体积</td>
			            <td width="35%">			                
			                ${command.productVolume?default("")}
			            </td>
			            <td width="15%" class="header fb">商品颜色</td>
			            <td width="35%">			            	
			            	${command.productCorlor?default("")}			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">商品尺寸</td>
			            <td width="35%">			                
			                ${command.porductSize?default("")}
			            </td>
			            <td width="15%" class="header fb">实际库存</td>
			            <td width="35%">			            	
			            	${command.factStorage?default("")}		            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">成本计算</td>
			            <td width="35%">			                
			                ${command.costCalculate?default("")}
			            </td>
			            <td width="15%" class="header fb">商品条码</td>
			            <td width="35%">			            	
			            	${command.productentry?default("")}	            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">称重编码</td>
			            <td width="35%">			                
			                ${command.weightilyCode?default("")}
			            </td>
			            <td width="15%" class="header fb">最小订货量</td>
			            <td width="35%">			            	
			            	${command.leastOrder?default("")}	            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">进价</td>
			            <td width="35%">			            	
			            	${command.pricePurchase?default("")}		            	
			            </td> 
			            <td width="15%" class="header fb">批次标记</td>
			            <td width="35%">			            	
			            	${command.batchMark?default("")}			            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">生产日期</td>
			            <td width="35%">
			                ${command.produceDate?default("")}
			            </td>
			            <td width="15%" class="header fb">商品增值</td>
			            <td width="35%">			            	
			            	${command.productIncrement?default("")}		            	
			            </td>            
			          </tr>			          
			          <tr>
			            <td width="15%" class="header fb">成本价</td>
			            <td width="35%">			                
			                ${command.costPrice?default("")}
			            </td>
			            <td width="15%" class="header fb">销售价</td>
			            <td width="35%">			            	
			            	${command.priceNet?default("")}	            	
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">市场价</td>
			            <td width="35%">			                
			                ${command.priceMarket?default("")}
			            </td>
			            <td width="15%" class="header fb">添加人员</td>
			            <td width="35%">			            	
			            	${command.createBy?default("")}	            	
			            </td>            
			          </tr>			          
			          <tr>
			            <td width="15%" class="header fb">备注</td>
			            <td colspan=3>			                
			                ${command.remark?default("")}
			            </td>			                      
			          </tr>
			          <!--                    
			          <tr>
			            <td align="center" colspan="10" height="40">
			            	<!-- 
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_define.gif" />&nbsp;&nbsp;
			                <input type="image" name="imageField" id="imageField" src="${contextPath}/images/btn_cancel.gif" />
			                 -->
			                 <input type="submit" value="保 存" >
			            </td>
			          </tr>
			          -->
			          <tr>
			            <td align="left" colspan="10" height="40">
			                <form name="myform" action="${contextPath}/mis/productInfoJxc/saveImageProductInfoJxc.do" method="post"	enctype="multipart/form-data">
								上传文件:								
								<input type="file" name="imageFile">&nbsp;&nbsp;
								<input type="hidden" name="id" value='${command.id?default("")}'>	
								<input type="hidden" name="productType" value='${command.productType?default("")}'>
								<input type="hidden" name="productName" value='${command.productName?default("")}'>	
								<input type="hidden" name="productCode" value='${command.productCode?default("")}'>					
								<input type="submit" name="submit" value="上传">
							</form>
			            </td>
			          </tr>
			        </table>
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
