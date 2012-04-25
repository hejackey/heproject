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
		if ( Trim(document.forms[0].barntypename.value) == ""){	
				alert("请输入仓库类别名称!");
				document.forms[0].barntypename.focus();
				return false;
		}
		if ( Trim(document.forms[0].barntypecode.value) == ""){	
				alert("请输入仓库类别编号!");
				document.forms[0].barntypecode.focus();
				return false;
		}
		document.forms[0].action="${contextPath}/mis/scmBarnType/updateScmBarnType.do";
		document.forms[0].submit();
	}
</script>
</head>

<body>
<div id="rightcontent">
	<div id="breadcrumb">
    	您所在的位置：<a href="#" class="green">首页</a> > <a href="#" class="green">菜单名称</a>  > <span class="fb black">仓库类型修改</span>
    </div>
    <div id="container">
	    <div class="addsell">
	   		<div class="left currentname fb">修改仓库分类</div>        
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
			    <form action="${contextPath}/mis/scmBarnType/updateScmBarnType.do" method="post">
			    <input type="hidden" name="id" value="${command.id?default('')}">
			    <input type="hidden" name="parentid" value="${command.parentid?default('')}">
			    <div class="selllist">
			    	<table width="90%" border="0" cellspacing="0" cellpadding="0" class="selldetail" align="center">
			          <tr>
			            <td width="15%" class="header fb">仓库类别名称<span class="required">*</span></td>
			            <td width="35%">
			            	<@spring.formInput "command.barntypename",'onblur=TextChange(this,document.forms[0].barntypecode)' />
		    				<@spring.showErrors "", "color:red;"/>			            	
			            </td>
			            <td width="15%" class="header fb">仓库类别编号<span class="required">*</span></td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.barntypecode" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>            
			          </tr>
			          <tr>
			            <td width="15%" class="header fb">地址</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.address" />
		    				<@spring.showErrors "", "color:red;"/>
			            </td>
			            <td width="15%" class="header fb">电话</td>
			            <td width="35%">			            	
			            	<@spring.formInput "command.phone" />
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
