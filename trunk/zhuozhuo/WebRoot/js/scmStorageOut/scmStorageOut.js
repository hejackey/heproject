var contextPath="/zhuozhuo";
//展示明细
		function showScmStorageOutDetail(id){					
			if($("#so"+id).html()=="+展开"&& $("#so"+id).attr('title')=="1"){	
			var title="<tr><td width='8%' class='detailtabheader'>序号</td><td width='12%'  class='detailtabheader'>产品名称</td><td width='20%'  class='detailtabheader'>数量</td>"+
					  "<td width='20%'  class='detailtabheader'>实际价格</td><td width='10%' class='detailtabheader'>金额</td><td width='10%' class='detailtabheader'>单据来源</td>"+
					  "<td width='10%' class='detailtabheader'>备注</td></tr>";   
			
			$.get("showDetailScmStorageOut.do?id="+id,function(data){				
				if(data!=null && data!=""){
					var objList=eval('(' + data + ')');			
					var count = objList.length;
					var content="";
					
					if(count>0){
						for(i=0;i<count;i++){
							var productId=objList[i].productId;
							if(typeof productId=='undefined')
								productId='';
								
							var productname=objList[i].productname;
							if(typeof productname=='undefined')
								productname='';
							
							var qty=objList[i].qty;						
							if(typeof qty =='undefined')
								qty='';
							
							var factSellPrice=objList[i].factSellPrice;						
							if(typeof factSellPrice =='undefined')
								factSellPrice='';
							
							var amt=objList[i].amt;						
							if(typeof amt =='undefined')
								amt='';
							
							var sheetIdOriginal=objList[i].sheetIdOriginal;						
							if(typeof sheetIdOriginal =='undefined')
								sheetIdOriginal='';
							
							var remark=objList[i].remark;						
							if(typeof remark =='undefined')
								remark='';
							
							content+="<tr><td class='tablist'>"+parseInt(i+1)+"</td><td class='tablist'>"+productname+"</td><td class='tablist'>"+qty+"</td>"+
									  "<td class='tablist'>"+factSellPrice+"</td><td class='tablist'>"+amt+"</td>"+
									  "<td class='tablist'>"+sheetIdOriginal+"</td>"+"<td class='tablist'>"+remark+"</td></tr>";
						}
						
						title=title+content;
						
						$("#detail_table_"+id).html(title);
						$("#detail"+id).css("display","");
						$("#so"+id).html("-收缩");
						$("#so"+id).attr('title',"1");
					}
				}
			});
			
		}
		else if($("#so"+id).attr('title')=="2"){
			$("#detail"+id).css("display","");
			$("#so"+id).html("-收缩");
			$("#so"+id).attr('title',"1");
		}
		else{
			$("#detail"+id).css("display","none");
			$("#so"+id).html("+展开");
			$("#so"+id).attr('title',"2");
		}
		
}

			
		//审核并流转
		function auditScmStorageOuts(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要审核并流转这些发货单吗？")==true){
				document.forms[0].action="auditScmStorageOut.do";				
				document.forms[0].submit();
			}
		}
		
		//增加发货单
		function addScmStorageOut(){
			document.forms[0].action="addScmStorageOut.do";				
				document.forms[0].submit();
		}
			
		//删除发货单
		function deleteScmStorageOuts(){
			var checkCnt = listCheck(document.forms[0].id);
			if(checkCnt <= 0)
				return;
			if(confirm("要删除这些发货单吗？")==true){
				document.forms[0].action="deleteScmStorageOut.do";				
				document.forms[0].submit();
			}
		}
		
		function listCheck(selectData){
			var selDataFlg = false;
			var checkCnt = 0;
		
			if(typeof(selectData.length) == "undefined"){
				if(selectData.checked){
					selDataFlg = true;
					checkCnt++;
				}
			}else{
				var iCnt = 0;
				while(iCnt < selectData.length){
					if(selectData[iCnt].checked){
						selDataFlg = true;
						checkCnt++;
					}
					iCnt++;
				}
			}
		
			if(!selDataFlg){
				alert("没有选择发货单，请选择！");
				return false;
			}
		
			return checkCnt;
		}			



//删除一行
	function deleteTR(obj){		
		$("#"+obj).remove();		
	}

//增加明细表的内容
function addDetail(context){	
	var addRowCount = $("#rowCount").val();
	if(addRowCount==null || addRowCount==""){
		alert('请输入新增行数');
		return ;
	}
	if(isNaN(addRowCount)){
		$("#rowCount").val(1);
		addRowCount=1;
	}	
	
	var rowId = $("#product_table tr").eq($("#product_table tr").length-2).attr("id");		
	if(isNaN(rowId)||rowId==null||rowId=="")
		rowId=0;
		
	str="";
	for(var i=0;i<addRowCount;i++){
		str+="<tr bgcolor='#f9ffe2' id='"+(parseInt(rowId)+i+1)+"'><td id='seq"+(parseInt(rowId)+i+1)+"'>"+(parseInt(rowId)+i+1)+"</td>" +
			"<td><input type='text' name='productcode' id='productcode"+(parseInt(rowId)+i+1)+"' class='sellinput'/>" +
			"<img src='"+context+"/images/calendar.gif' onclick=queryProductOut('"+(parseInt(rowId)+i+1)+"','"+context+"/mis/productInfoJxc/qByProductCodeProductInfoJxc.do') "+
			"style='cursor:pointer;'></td>"+			                
			"<td><input type='text' name='productname' readonly id='productname"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
            "<td><input type='text' name='productspecs' readonly id='productspecs"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
            "<td><input type='text' name='qty' onchange='calStorageOutSumPrice("+(parseInt(rowId)+i+1)+")' id='amount"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='factSellPrice' onchange='calStorageOutSumPrice("+(parseInt(rowId)+i+1)+")' id='productpurchase"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+                
	        "<td><input type='text' name='amt' readonly id='sumprice"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='productentry' readonly id='productentry"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<td><input type='text' name='sheetIdOriginal' id='sheetIdOriginal"+(parseInt(rowId)+i+1)+"' class='sellinput' /></td>"+
	        "<input type='hidden' name='aremark' id='remark"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<input type='hidden' name='taxrate' id='taxrate"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<input type='hidden' name='productId' id='productid"+(parseInt(rowId)+i+1)+"' class='sellinput' />"+
	        "<td align='center' class='nb_right'><a href='#'><img src='"+context+"/images/icon_del.gif' width='16' height='16' title='删除该条信息'"+
	        "onclick='deleteTR("+(parseInt(rowId)+i+1)+")' /></a></td></tr>"	
  
	}
	
	$("#product_table tr").eq($("#product_table tr").length-2).after(str);	
}

function choseBarnType(context){
	var id=$("#barnId").find('option:selected').val();
	
	$.get(context+"/barn/getByIdBarnType.do?id="+id+"&d="+new Date().getTime(),function(data){
		try{
			if(data!=null && data!=""){
				
				var obj=eval('(' + data + ')');			
				if(typeof obj.address == 'undefined')
					$("#barnAddress").html("&nbsp;");
				else
					$("#barnAddress").html(obj.address);
				
				if(typeof obj.phone == 'undefined')
					$("#barnPhone").html("&nbsp;");
				else
					$("#barnPhone").html(obj.phone);
				
			}
			else{
				$("#barnAddress").html("");
				$("#barnPhone").html("");
			}
		}
		catch(e){
			alert(e);
		}
	});
}

function queryProductOut(id,url){
	var pcode=$("#productcode"+id).val();
	
	window.open(url+"?seq="+id+"&querytype=storageout&productCode="+encodeURIComponent(pcode),"_blank");
}          

function calStorageOutHjSum(){
	var num = $("#product_table tr").length-2;
	var max=0;
	for(j=0;j<num;j++){
		var rowId=$("#product_table tr").eq(j+1).attr("id");
		if(parseInt(rowId)>max)
				max = parseInt(rowId);
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

function calStorageOutSumPrice(id){	
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
	
	calStorageOutHjSum();
}

function saveStorageOut(){
	document.form1.submit();
}

function editStorageOut(){
	document.form1.action="editScmStorageOut.do";
	document.form1.submit();
}