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
	<script type="text/javascript" src="${base}/web/js/jquery/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/extend-easyui-valid.js"></script>

</head>
<body>
<h1>Form Demo</h1>

<div style="background:#fafafa;padding:10px;width:350px;height:350px;">
    <form id="form1" name="form1" method="post">
        <div>
            <label for="name">必输项通过required属性实现：</label>
            <input class="easyui-validatebox" type="text" name="str" id="str" required="true" missingMessage="非空字段1"></input>
        </div>
        <div>
            <label for="email">验证email</label>
            <input class="easyui-validatebox" type="text" name="email" id="email" required="true" validType="email" missingMessage="非空字段1" invalidMessage="错误的email地址1"></input>
        </div>
        <div>
            <label for="email">验证手机号</label>
            <input class="easyui-validatebox" type="text" name="mobile" id="mobile" validType="mobile" missingMessage="请输入手机号码" invalidMessage="请输入正确的手机号码"></input>
        </div>
        <div>
            <label for="email">限制长度</label>
            <input class="easyui-validatebox" type="text" name="len" id="len" required="true" validType="length[1,6]"  missingMessage="非空字段1" invalidMessage="字段长度1到6位字符"></input>
        </div>
        <div>
            <label for="email">验证中文汉字</label>
            <input class="easyui-validatebox" type="text" name="chs" id="chs" required="true" validType="chs"  missingMessage="只能输入中文" invalidMessage="只能输入中文1"></input>
        </div>
        <div>
            <label for="email">验证数字字母混合密码6位</label>
            <input class="easyui-validatebox" type="password" name="pwd" id="pwd" validType="pwd" ></input>
        </div>
        <div>
            <label for="email">验证身份证</label>
            <input class="easyui-validatebox" type="text" name="idcard" id="idcard" validType="idcard" ></input>
        </div>
        <div>
            <label for="email">验证数字</label>
            <input class="easyui-validatebox" type="text" name="number" id="number" validType="number" ></input>
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
    
    if(flag)    
		document.form1.submit();
	else
		alert('表单项有错');
});


</script>
</body>
</html>
