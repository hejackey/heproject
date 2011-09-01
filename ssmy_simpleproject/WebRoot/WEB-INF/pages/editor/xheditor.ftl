<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>xhEditor demo1 : 默认模式</title>
<link rel="stylesheet" href="${base}/web/css/xheditor/common.css" type="text/css" media="screen" />
<script type="text/javascript" src="${base}/web/js/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${base}/web/js/xheditor/xheditor-1.1.8-zh-cn.min.js"></script>
</head>
<body>
<div id="header-nav">
	<ul>
		<li><a href="demo01.html"><span>默认模式</span></a></li>
		<li><a href="demo02.html"><span>自定义按钮</span></a></li>
		<li><a href="demo03.html"><span>皮肤选择</span></a></li>
		<li><a href="demo04.html"><span>其它选项</span></a></li>
		<li><a href="demo05.html"><span>API交互</span></a></li>
		<li><a href="demo06.html"><span>非utf-8编码调用</span></a></li>
		<li><a href="demo07.html"><span>UBB可视化</span></a></li>
		<li><a href="demo08.html"><span>Ajax上传</span></a></li>
		<li><a href="demo09.html"><span>插件扩展</span></a></li>
		<li><a href="demo10.html"><span>iframe调用上传</span></a></li>
		<li><a href="demo11.html"><span>异步加载</span></a></li>
		<li><a href="demo12.html"><span>远程抓图</span></a></li>
		<li><a href="../wizard.html" target="_blank"><span>生成代码</span></a></li>
	</ul>
</div>
<form method="post" action="saveXheditor.do">
	<h3>xhEditor demo1 : 默认模式</h3>
	1,xheditor(默认完全):<br />
	<textarea id="conValue" name="conValue" class="xheditor" rows="12" cols="80" style="width: 80%">
&lt;p&gt;当前实例调用的HTML代码为：&lt;/p&gt;&lt;p&gt;&amp;lt;textarea id=&quot;elm1&quot; name=&quot;elm1&quot; &lt;span style=&quot;color:#ff0000;&quot;&gt;class=&quot;xheditor&quot;&lt;/span&gt; rows=&quot;12&quot; cols=&quot;80&quot; style=&quot;width: 80%&quot;&amp;gt;&lt;/p&gt;
	</textarea><br /><br />
	
	<input type="submit" name="save" value="Submit" />
	<input type="reset" name="reset" value="Reset" />
</form>
</body>
</html>