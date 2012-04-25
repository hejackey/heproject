<!-- 显示分页信息 start -->
<div id="page"  class="pageNum">
 <#assign itemfrom = model.pageInfo.getPage()?if_exists-1*model.pageInfo.getPageSize()?if_exists + 1>              
        <#if (model.pageInfo.getPage()>6)><#assign pfrom = model.pageInfo.getPage()-5><#assign pto = model.pageInfo.getPage() + 4>								<#if (pto>model.pageInfo.getPages())><#assign pto = model.pageInfo.getPages()>								</#if>							<#else>								<#if (model.pageInfo.getPages()>10)><#assign pfrom = 1><#assign pto = 10>								<#else><#assign pfrom = 1><#assign pto = model.pageInfo.getPages()>								</#if>							</#if>	
		显示 <font color="red">${model.pageInfo.start?if_exists} - ${model.pageInfo.end?if_exists}</font> 条    
		共 <font color="red">${model.pageInfo.pages?if_exists}</font> 页 
		<font color="red">${model.pageInfo.count?if_exists}</font> 条
<#if model.pageInfo.page == 1>首页
<#else><a href="#" onclick="goPage(1)">首页</a>
</#if>
<#foreach p in pfrom..pto>	
	<#if p != model.pageInfo.getPage()>	
		<a href="#" onclick="goPage(${p})"><font color="red">${p?if_exists}</font></a>
	<#else>	  
		<font color="red">${p?if_exists}</font>
	</#if>
</#foreach>
<#if model.pageInfo.page == model.pageInfo.pages> 末页	
<#else><a href="#" onclick="goPage(${model.pageInfo.pages?if_exists})">末页</a>
</#if>
到第
<#foreach p in pfrom..pto>	
	<#if p == model.pageInfo.getPage()>	
		<input id="pageNext" name="pageInfo.page" type="text" size="2" value="${p?if_exists}"/>	
	</#if>
</#foreach>

  页
    <label>
    <input type="button" class="btn48x22" style="cursor:pointer" name="Submit" value="GO" onclick="goPage(document.getElementById('pageNext').value);">
   </label>
</div>  

<input name="pageInfo.pageSize" type="hidden" value="${model.pageInfo.pageSize?if_exists}"/>
<#if model.pageInfo.pages?if_exists <= 0><script>
document.getElementById('page').style.display="none";
</script>
</#if><script>

//pageInputObj,输入页码的文本框对象
//pages,总页数
function goPage(pageNum)
{ 
	if( !isInts(pageNum) )
	{
		alert("请正确输入要跳转的页码！");
		document.getElementById('pageNext').value = "";
		document.getElementById('pageNext').focus();
		return;
	}
	if( pageNum>0 )
	{
		if(pageNum > ${model.pageInfo.pages?if_exists} || pageNum < 1){
			alert("页码超出范围");
			document.getElementById('pageNext').focus();
			return;
		}else{
			document.getElementById('pageNext').value = pageNum;
			document.form1.submit();
		}
	}else{
		alert("请正确输入要跳转的页码！");
		document.getElementById('pageNext').value = "";
		document.getElementById('pageNext').focus();
	}
}
function isInts(str) 
{
    if (str == "")
    {
      	return false;
    }
    var r = /^[0-9]+$/;
    return r.test(str);
}
</script>
<!-- 显示分页信息 end -->
