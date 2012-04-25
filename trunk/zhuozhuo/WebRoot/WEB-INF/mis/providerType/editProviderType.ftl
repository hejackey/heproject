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
<script type="text/javascript" src="${contextPath}/js/mis/common.js"></script>
<script language="javascript">
function formCheck(){				
		if ( Trim(document.forms[0].providerytpeName.value) == ""){	
				alert("请输入供应商类别名称!");
				document.forms[0].providerytpeName.focus();
				return false;
		}
		if ( Trim(document.forms[0].providerTypeCode.value) == ""){	
				alert("请输入供应商类别编号!");
				document.forms[0].providerTypeCode.focus();
				return false;
		}
		document.forms[0].action="${contextPath}/mis/providerType/updateProviderType.do";
		document.forms[0].submit();
	}
</script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">供应商类别修改</span>
    </div>
    <div id="container">
	    <div class="addsell">
	   		<div class="left currentname fb">修改供应商分类</div>        
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
	            提示：“<span class="required">*</span>” 表示必填项
	        </div>
	      </div>
	      <div class="clear"></div>
    	<!--上边功能区结束-->
    
			    <!--详细开始-->
			    <form action="${contextPath}/mis/providerType/updateProviderType.do" method="post">
			    <input type="hidden" name="recordId" value="${command.recordId?default('')}">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">供应商类别名称<span class="required">*</span></td>
			            <td width="35%">
			            	<@spring.formInput "command.providerytpeName",'onblur=TextChange(this,document.forms[0].providerTypeCode)' />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>
			            <td width="15%" class="header fb">供应商类别编号<span class="required">*</span></td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.providerTypeCode" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">自定义字段</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.define1" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">备 注</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.remark" />
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
