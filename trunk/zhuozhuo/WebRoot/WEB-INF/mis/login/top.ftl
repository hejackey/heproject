<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>卓品网站后台管理</title>
<link href="${model.context?if_exists}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${model.context?if_exists}/css/layout.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="head">
	<div id="headcontent">
    	<a href="#" class="home"><img src="${model.context?if_exists}/images/spacer.gif" width="265" height="27" title="返回首页" /></a>
    	<span><b>${model.name?if_exists}</b>，欢迎您进入本系统</span>
        <ul>
            <li><a href="#"><img src="${model.context?if_exists}/images/top_bthome.gif" width="50" height="50" title="返回首页" /></a></li>
            <li><a href="#"><img src="${model.context?if_exists}/images/top_btset.gif" width="50" height="50" title="我的设置" /></a></li>
            <li><a href="#"><img src="${model.context?if_exists}/images/top_bthelp.gif" width="50" height="50" title="帮助" /></a></li>
            <li><a href="${model.context?if_exists}/login/logout.do" target="_top"><img src="${model.context?if_exists}/images/top_btexit.gif" width="50" height="50" title="退出系统" /></a></li>
        </ul>
    </div>
</div>
<div class="clear"></div>
</body>
</html>
