function queryProvider(url){
	var pname=$("#providerName").val();
	
	window.open(url+"?providerName="+encodeURIComponent(pname),"_blank");
}

function choseProvider(providerid){
	var pname = $("#name_"+providerid).html();
	var pphone = $("#phone_"+providerid).html();
	var paddress = $("#address_"+providerid).html();
	var pbusinessMan = $("#businessMan_"+providerid).html();
	var account = $("#account_"+providerid).html();
		
	$("#providerName",opener.document).focus();
	$("#providerName", opener.document).val(pname);
	$("#providerid", opener.document).val(providerid);
	$("#address", opener.document).val(pphone);
	$("#phone", opener.document).val(paddress);
	$("#businessman", opener.document).val(pbusinessMan);
	$("#account", opener.document).val(account);
	
	
	
	window.opener=null;        
	window.open("","_self");   
	window.close();	
}

function choseBarnType(context){
	var id=$("#barnid").find('option:selected').val();
	
	$.get(context+"/barn/getByIdBarnType.do?id="+id+"&d="+new Date().getTime(),function(data){
		try{
			if(data!=null && data!=""){
				var obj=eval('(' + data + ')');				
				$("#barnAddress").html(obj.address);
				$("#barnPhone").html(obj.phone);
				
				$("#toaddress").val(obj.address);
			}
			else{
				$("#barnAddress").html("");
				$("#barnPhone").html("");
				$("#toaddress").val("");
			}
		}
		catch(e){
			alert(e);
		}
	});
}

function addProductTr(){
	var num = $("#productTable tr").length-2;
	var count=0;
	for(j=0;j<num;j++){
		var title=$("#productTable tr").eq(j+1).find("img").attr("title");
		if(parseInt(title)>count)
			count = parseInt(title);
	}
	
	var colCount=$("#colCount").val();
	if(isNaN(colCount)){
		$("#colCount").val(0);
		return ;
	}
	
	for(i=0;i<colCount;i++){	
		$("#productTable tr").eq(1)
		            .clone(true)   
		            .find(".seq").text(num+i+1).end()
		            .find("#productcode1").attr("id","productcode"+parseInt(count+i+1)).end()	
		            .find("#productname1").attr("id","productname"+parseInt(count+i+1)).end()		
		            .find("#productspecs1").attr("id","productspecs"+parseInt(count+i+1)).end()	
		            .find("#productid1").attr("id","productid"+parseInt(count+i+1)).end()
		            
		            .find("#productentry1").attr("id","productentry"+parseInt(count+i+1)).end()	
		            .find("#remark1").attr("id","remark"+parseInt(count+i+1)).end()	
		            .find("#href1").attr("onclick","").end()            		           	            		           	            		            
		            .find("#href1").click(function(){queryProduct(this.title);}).end()	            
					.find("#href1").attr("title",parseInt(count+i+1)).end()
								
					.find("#amount1").attr("onchange","").end()            		           	            		           	            		            
		            .find("#amount1").change(function(){calSumPrice(this.title);}).end()	            
					.find("#amount1").attr("title",parseInt(count+i+1)).end()
					
					.find("#productpurchase1").attr("onchange","").end()            		           	            		           	            		            
		            .find("#productpurchase1").change(function(){calSumPrice(this.title);}).end()	            
					.find("#productpurchase1").attr("title",parseInt(count+i+1)).end()
					
					.find("#taxrate1").attr("onchange","").end()            		           	            		           	            		            
		            .find("#taxrate1").change(function(){calSumPrice(this.title);}).end()	            
					.find("#taxrate1").attr("title",parseInt(count+i+1)).end()
								
				    .find("#amount1").attr("id","amount"+parseInt(count+i+1)).end()	
		            .find("#productpurchase1").attr("id","productpurchase"+parseInt(count+i+1)).end()	
		            .find("#taxrate1").attr("id","taxrate"+parseInt(count+i+1)).end()	
		            .find("#sumprice1").attr("id","sumprice"+parseInt(count+i+1)).end()	
		            .find("#href1").attr("id","href"+parseInt(count+i+1)).end()	            		           	            
		            .appendTo($("#td_row"));   
		           
	}
	
	calHjSum();	
}

function removetr(id){
	$(id).parents(".trclass").remove();	
	calHjSum();	
	
	var count=$("#productTable tr").length-2;	
	for(i=0;i<count;i++){
		$("#productTable tr").eq(i+1)
		            	               
		            .find(".seq").text(i+1).end()		            		           
	}	
	
}

function queryProduct(id){
	var pcode=$("#productcode"+id).val();
	
	window.open("../mis/productInfoJxc/qByProductCodeProductInfoJxc.do?seq="+id+"&productCode="+encodeURIComponent(pcode),"_blank");
}

function choseProduct(pid){
	var pname = $("#name_"+pid).html();
	var pcode = $("#code_"+pid).html();
	var pspecs = $("#specs_"+pid).html();
	var purchase = $("#purchase_"+pid).html();
	var productentry = $("#productentry_"+pid).html();
	var remark = $("#remark_"+pid).html();
	var seq = $("#seq").val();
	
	$("#productcode"+seq, opener.document).val(pcode);
	$("#productname"+seq, opener.document).val(pname);
	$("#productspecs"+seq, opener.document).val(pspecs);
	$("#amount"+seq, opener.document).val(1);
	$("#productpurchase"+seq, opener.document).val(purchase);
	$("#taxrate"+seq, opener.document).val(0);
	$("#sumprice"+seq, opener.document).val(purchase);
	$("#productentry"+seq, opener.document).val(productentry);
	$("#remark"+seq, opener.document).val(remark);
	$("#productid"+seq, opener.document).val(pid);
	
	if($("#querytype").val()=="storageout"){
		window.opener.calStorageOutHjSum();
	}
	else{
		window.opener.calHjSum();
	}
	
	window.opener=null;        
	window.open("","_self");   
	window.close();	
}

function calSumPrice(id){	
	var amount=$("#amount"+id).val();	
	var purchase=$("#productpurchase"+id).val();	
	var taxrate=$("#taxrate"+id).val();
	
	if(isNaN(amount)){
		amount=1;
		$("#amount"+id).val(1);		
	}
	
	if(isNaN(purchase)){
		purchase=1;
		$("#productpurchase"+id).val(1);		
	}
	if(isNaN(taxrate)){
		taxrate=0;
		$("#taxrate"+id).val(0);		
	}
	
	var sumPrice=parseInt(amount)*parseFloat(purchase)*(parseFloat(1+parseFloat(taxrate)));
	$("#sumprice"+id).val(Math.round(sumPrice*100)/100);
	
	calHjSum();
}

function calHjSum(){	
	var num = $("#productTable tr").length-2;
	var max=0;
	for(j=0;j<num;j++){
		var title=$("#productTable tr").eq(j+1).find("img").attr("title");
		if(parseInt(title)>max)
				max = parseInt(title);
	}
	
	var hjAmount=0;
	var hjSum=0;
	
	for(var i=0;i<=max;i++){
		if($("#amount"+parseInt(i+1)).val()==null || $("#amount"+parseInt(i+1)).val()=="")
			hjAmount+=0;
		else
			hjAmount+=parseInt($("#amount"+parseInt(i+1)).val());
		
		if($("#sumprice"+parseInt(i+1)).val()==null || $("#sumprice"+parseInt(i+1)).val()=="")
			hjSum+=0;
		else
			hjSum+=parseFloat($("#sumprice"+parseFloat(i+1)).val());
			
	}
	
	$("#hjAmount").html(hjAmount);
	$("#hjSum").html(hjSum);
	$("#sumqty").val(hjAmount);
	$("#sumamt").val(hjSum);
}

function saveStockOrder(){
	document.form1.submit();
}

function showStockOrderDetail(id,sheetid){
	if($("#zk"+id).html()=="+展开"&& $("#zk"+id).attr('title')=="1"){	
		var title="<tr><td width='8%' class='detailtabheader'>序号</td><td width='12%'  class='detailtabheader'>单据编号</td><td width='20%'  class='detailtabheader'>货品名称</td>"+
				  "<td width='20%'  class='detailtabheader'>货品类别</td><td width='10%' class='detailtabheader'>单位</td><td width='10%' class='detailtabheader'>实际进价</td>"+
				  "<td width='10%' class='detailtabheader'>实际金额</td><td width='10%' class='detailtabheader'>备注</td></tr>";   
		
		$.get("getDetailBySheetIdStockOrder.do?id="+id,function(data){
			if(data!=null && data!=""){
				var objList=eval('(' + data + ')');			
				var count = objList.length;
				var content="";
				
				if(count>0){
					for(i=0;i<count;i++){
						var pname=objList[i].productname;
						if(typeof pname=='undefined')
							pname='';
						
						var ptname=objList[i].productTypeName;						
						if(typeof ptname =='undefined')
							ptname='';
						
						var punit=objList[i].productunit;						
						if(typeof punit =='undefined')
							punit='';
						
						var factinprice=objList[i].factinprice;						
						if(typeof factinprice =='undefined')
							factinprice='';
						
						var amt=objList[i].amt;						
						if(typeof amt =='undefined')
							amt='';
						
						var remark=objList[i].remark;						
						if(typeof remark =='undefined')
							remark='';
						
						content+="<tr><td class='tablist'>"+parseInt(i+1)+"</td><td class='tablist'>"+sheetid+"</td><td class='tablist'>"+pname+"</td>"+
								  "<td class='tablist'>"+ptname+"</td><td class='tablist'>"+punit+"</td>"+
								  "<td class='tablist'>"+factinprice+"</td>"+"<td class='tablist'>"+amt+"</td><td class='tablist'>"+remark+"</td></tr>";
					}
					
					title=title+content;
					
					$("#detail_table_"+id).html(title);
					$("#detail"+id).css("display","");
					$("#zk"+id).html("-收缩");
					$("#zk"+id).attr('title',"1");
				}
			}
		});	
	}
	else if($("#zk"+id).attr('title')=="2"){
		$("#detail"+id).css("display","");
		$("#zk"+id).html("-收缩");
		$("#zk"+id).attr('title',"1");
	}
	else{
		$("#detail"+id).css("display","none");
		$("#zk"+id).html("+展开");
		$("#zk"+id).attr('title',"2");
	}
}

function preAddStockOrder(type){
	document.form1.action="preAddStockOrder.do?stockopentype="+type;
	document.form1.submit();
}

function queryStockOrder(){
	if(isNaN($("#minAmt").val()) || isNaN($("#maxAmt").val())){
		alert("查询单据金额必须是数字");
		return ;
	}
	
	document.form1.submit();
}

function choseProductType(id){	
	var pname = $("#name_"+id).html();	

	$("#productType", opener.document).val(id);
	$("#productTypeName", opener.document).val(pname);	
	
	window.opener=null;        
	window.open("","_self");   
	window.close();	
}

function choseProductTypeName(id){	
	var pname = $("#typeName_"+id).html();	

	$("#productType", opener.document).val(id);
	$("#productTypeName", opener.document).val(pname);	
	
	window.opener=null;        
	window.open("","_self");   
	window.close();	
}



function queryProductType(url){
	var code=$("#productTypeName").val();
	//alert(code);
	
	window.open(url+"?goodsTypeCode="+encodeURIComponent(code),"_blank");
}

function changeProductcode(){
	if($("#productTypeName").val()=="")
		$("#productType").val("");
}

function preEditStockOrder(id){
	document.form1.action="preEditStockOrder.do?id="+id;
	document.form1.submit();
}

function editStockOrder(){
	document.form1.action="editStockOrder.do";
	document.form1.submit();
}

function showMoreQuery(){
	if($("#morequery").val()=="更多查询"){
		$("#more_query_div").css("display","");
		$("#morequery").val("隐藏更多查询");
	}
	else{
		$("#more_query_div").css("display","none");
		$("#morequery").val("更多查询");
	}
}
         
function exportToExcel(){
	document.form1.action="exportToExcelStockOrder.do";
	document.form1.submit();
	document.form1.action="listStockOrder.do";
}