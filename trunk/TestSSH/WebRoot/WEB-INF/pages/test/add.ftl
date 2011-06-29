<html>
<title>test extjs</title>
<head>
<style type="text/css">
		label{
			width:250px;
			display:block;
		}
	</style>
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/default/easyui.css">
	<script type="text/javascript" src="${base}/web/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/extend-easyui-valid.js"></script>

</head>
<body>
<h1>Add helloWorld</h1>

<div style="background:#fafafa;padding:10px;width:350px;height:350px;">
    <form id="form1" name="form1" method="post" action="saveHelloWorld.do">
        <div >
        <#if fieldErrors.get("formErr")?exists>
	        <font color="red">${fieldErrors.get("formErr")?if_exists?replace("[","")?replace("]","")}</font>
		</#if>
        </div>
        <div>
            <label for="name">第一列str：</label>
            <input class="easyui-validatebox" type="text" name="str" id="str" required="true" value="${model.str?if_exists?html}" validType="length[0,50]" missingMessage="非空字段1" invalidMessage="最多50个字符"></input>
        </div>
        <div>
            <label for="email">第二列param：</label>
            <input class="easyui-validatebox" type="text" name="param" id="param" required="true" value="${model.param?if_exists?html}" validType="length[0,50]" missingMessage="非空字段1" invalidMessage="最多50个字符"></input>
        </div>
        
        <div>
            <input type="button" id="tj" name="tj"  value="Submit">
        </div>
    </form>
</div>
<script>

$("#tj").click(function(){
	var flag = true;

	$('#form1 input').each(function () {
	    if ($(this).attr('required') || $(this).attr('validType')) {
		    if (!$(this).validatebox('isValid')) {
		        flag = false;
		        return;
		    }
	    }
	})
    
   // if(flag)    
		document.form1.submit();
	//else
		//alert('表单项有错');
});


</script>
</body>
</html>
