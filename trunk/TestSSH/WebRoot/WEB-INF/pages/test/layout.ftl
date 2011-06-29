<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery EasyUI</title>
	<link rel="stylesheet" type="text/css" href="${base}/web/css/easyui/default/easyui.css">
	<script type="text/javascript" src="${base}/web/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${base}/web/js/easyui/jquery.easyui.min.js"></script>
	<script>
	function addTab(title,href){
		var tt = $('#self_tab_contain');  
	    if (tt.tabs('exists', title)){  
	    	tt.tabs('select', title);  
	    	refreshTab(); 
	    }
	    else {  
	   		 if (href){  
	   			 var content = '<iframe scrolling="yes" frameborder="0"  src="'+href+'" style="width:100%;height:100%;"></iframe>';  
	   		 } else {  
	   			 var content = '未实现';  
	   		 }  
	   
	   		 tt.tabs('add',{  
	    		title:title,  
	   			closable:true,  
	   			content:content  
	   		 });  
	    }  
	}
	
	function refreshTab(){  
	    var refresh_tab = $('#self_tab_contain').tabs('getSelected');  
	    if(refresh_tab && refresh_tab.find('iframe').length > 0){  
		    var _refresh_ifram = refresh_tab.find('iframe')[0];  
	    
	 	   _refresh_ifram.contentWindow.location.href=_refresh_ifram.src;  
	    }  
	}  
	</script>
</head>
<body class="easyui-layout">
	<div id="mymenu" style="width:150px;">
		<div>item1</div>
		<div>item2</div>
	</div>
		<div region="north" title="North Title" split="true" style="height:100px;padding:10px;">
			head 内容
		</div>
		<div region="south" title="South Title" split="true" style="height:100px;padding:10px;background:#efefef;">
			foot 内容
		</div>
		
		<div region="west" split="true" title="导航菜单" style="width:280px;padding1:1px;overflow:hidden;">
			<div class="easyui-accordion" fit="true" border="false">
				<div title="父级菜单一" style="overflow:auto;" selected="true">
					 <div class="nav-item">  
		                <a href="javascript:addTab('子菜单一','list.do')">  
		                    <span>子菜单一  </span>  
		                </a> 
		            </div>  
		            <div class="nav-item">  
		                <a href="javascript:addTab('子菜单二','formValid.do')">  
		                    <span>子菜单二  </span>  
		                </a> 
		            </div>  
		            <div class="nav-item">  
		                <a href="javascript:addTab('子菜单三','add.do')">  
		                    <span>子菜单三  </span>  
		                </a>  
		            </div>
				</div>
				<div title="父级菜单二" style="padding:10px;">
					content2
					<a href="#">addmenu</a>
				</div>
				<div title="父级菜单三">
					content3
				</div>
			</div>
		</div>
		<div region="center" title="Main Title" style="overflow:hidden;">
			<div class="easyui-tabs" fit="true" border="false" id="self_tab_contain">
				<div title="首页" closable="false" style="padding:20px;overflow:hidden;"> 
					<div style="margin-top:20px;">
						欢迎使用帮付宝后台管理系统
					</div>
				</div>
				
			</div>
		</div>
</body>
</html>