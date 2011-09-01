<#list model.list?if_exists as list>
${list.id?if_exists}||${list.conValue?if_exists}||${list.hellWorld.str?if_exists}||${list.hellWorld.param?if_exists}</br>
</#list>